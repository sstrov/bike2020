package fscms.mods.error.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.RoleVO;
import fscms.mods.error.service.ErrorHistService;
import fscms.mods.error.vo.ErrorHistSearchVO;
import fscms.mods.error.vo.ErrorHistVO;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_ErrorHistController {
	
	static Logger logger = LogManager.getLogger(A_ErrorHistController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "errorHistService")
	private ErrorHistService errorHistService;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	/**
	 * @Method Name	: aErrorHistList
	 * @작성일		: 2019. 10. 17.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 오류_이력 목록 화면
	 */
	@RequestMapping(value = "/error/hist/list.do")
	public String aErrorHistList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ErrorHistSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		model.addAttribute("role",   role);
		model.addAttribute("admURI", admURI);
		return "mngr/error/hist_list";
	}
	
	/**
	 * @Method Name	: aErrorHistGetList
	 * @작성일		: 2019. 10. 17.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 오류_이력 목록 조회
	 */
	@RequestMapping(value = "/error/hist/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aErrorHistGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ErrorHistSearchVO searchVO,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<ErrorHistVO> itemList = null;
		try {
			int tCount = errorHistService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			itemList = errorHistService.selectPageList(searchVO);
			
			if(itemList != null && itemList.size() > 0) {
				itemList.get(0).settCount(tCount);
			}
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(itemList).toString();
	}
	
	/**
	 * @Method Name	: aErrorHistView
	 * @작성일		: 2019. 10. 17.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 오류_이력 상세보기
	 */
	@RequestMapping(value = "/error/hist/view.do")
	public String aErrorHistView(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ErrorHistSearchVO searchVO,
			@RequestParam(value = "errorHistSn", required = true) int errorHistSn,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		ErrorHistVO item = new ErrorHistVO();
		try {
			// 일련번호가 있으면 수정
			ErrorHistVO errorHistVO = new ErrorHistVO();
			errorHistVO.setErrorHistSn(errorHistSn);
			item = errorHistService.selectObj(errorHistVO);
		} catch (Exception e) {
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("item",   item);
		model.addAttribute("admURI", admURI);
		return "mngr/error/hist_view";
	}

}
