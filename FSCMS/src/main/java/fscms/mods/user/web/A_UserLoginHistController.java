package fscms.mods.user.web;

import java.io.UnsupportedEncodingException;
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

import egovframework.com.cmm.util.EgovCryptoARIAHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.RoleVO;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrLoginHistSearchVO;
import fscms.mods.mngr.vo.MngrLoginHistVO;
import fscms.mods.mngr.vo.MngrVO;
import fscms.mods.mngr.web.A_MngrLoginHistController;
import fscms.mods.user.service.UserLoginHistService;
import fscms.mods.user.vo.UserLoginHistSearchVO;
import fscms.mods.user.vo.UserLoginHistVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_UserLoginHistController {

static Logger logger = LogManager.getLogger(A_MngrLoginHistController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	@Resource(name = "userLoginHistService")
	private UserLoginHistService userLoginHistService;
	
	@Resource(name = "egovCryptoARIAHelper")
	private EgovCryptoARIAHelper egovCryptoARIAHelper;
	
	/**
	 * @Method Name	: aUserLoginHistList
	 * @작성일		: 2020. 06. 25.
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: 회원_로그인_이력 목록 화면
	 */
	@RequestMapping(value = "/user/login/hist/list.do")
	public String aUserLoginHistList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") UserLoginHistSearchVO searchVO,
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
		return "mngr/user/login_hist_list";
	}
	
	/**
	 * @Method Name	: aUserLoginHistGetList
	 * @작성일		: 2020. 06. 25.
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: 관리자_로그인_이력 목록 조회
	 */
	@RequestMapping(value = "/user/login/hist/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aMngrLoginHistGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") UserLoginHistSearchVO searchVO,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<MngrLoginHistVO> itemList = null;
		if(StringUtils.isNotEmpty(searchVO.getSw())) {
			try {
				searchVO.setSw_entry(egovCryptoARIAHelper.encrypt(searchVO.getSw()));
			} catch (UnsupportedEncodingException e) {
				searchVO.setSw_entry(searchVO.getSw());
			}
		}
		try {
			int tCount = userLoginHistService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			itemList = userLoginHistService.selectPageList(searchVO);
			
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
	 * @Method Name	: aMngrLoginHistView
	 * @작성일		: 2019. 10. 11.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자_로그인_이력 상세보기
	 */
	@RequestMapping(value = "/user/login/hist/view.do")
	public String aMngrLoginHistView(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") UserLoginHistSearchVO searchVO,
			@RequestParam(value = "loginHistSn", required = true) int loginHistSn,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		UserLoginHistVO item = new UserLoginHistVO();
		try {
			// 일련번호가 있으면 수정
			UserLoginHistVO userLoginHistVO = new UserLoginHistVO();
			userLoginHistVO.setLoginHistSn(loginHistSn);
			item = userLoginHistService.selectObj(userLoginHistVO);
		} catch (Exception e) {
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("item",   item);
		model.addAttribute("admURI", admURI);
		return "mngr/user/login_hist_view";
	}
}
