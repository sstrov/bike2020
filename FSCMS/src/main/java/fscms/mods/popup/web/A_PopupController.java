package fscms.mods.popup.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.util.FsFuncCmmHelper;
import fscms.cmm.util.UploadHelper;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.RoleVO;
import fscms.mods.atchmnfl.service.AtchmnflService;
import fscms.mods.atchmnfl.vo.AtchmnflVO;
import fscms.mods.code.util.CodeHelper;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import fscms.mods.popup.service.PopupService;
import fscms.mods.popup.vo.PopupSearchVO;
import fscms.mods.popup.vo.PopupVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_PopupController {
	
	static Logger logger = LogManager.getLogger(A_PopupController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "atchmnflService")
	private AtchmnflService atchmnflService;
	
	@Autowired
	private CodeHelper codeHelper;
	
	@Resource(name = "popupService")
	private PopupService popupService;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	@Autowired
	private UploadHelper uploadHelper;
	
	/**
	 * @Method Name	: aPopupList
	 * @작성일		: 2019. 10. 23.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 팝업 목록 화면
	 */
	@RequestMapping(value = "/popup/list.do")
	public String aPopupList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") PopupSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			// 활성 상태 코드
			model.addAttribute("stateList", codeHelper.getList("POP001"));
		} catch (Exception e) {
			logger.error("팝업상태 코드 정보 조회 오류");
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("toDate", new FsFuncCmmHelper().getToDate("yyyyMMddHHmm", null));
		model.addAttribute("role",   role);
		model.addAttribute("admURI", admURI);
		return "mngr/popup/list";
	}
	
	/**
	 * @Method Name	: aPopupGetList
	 * @작성일		: 2019. 10. 23.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 팝업 정보 목록 조회
	 */
	@RequestMapping(value = "/popup/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aPopupGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") PopupSearchVO searchVO,
			MngrVO admSession,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<PopupVO> itemList = null;
		try {
			// 노출상태
			if(StringUtils.isNotEmpty(searchVO.getSc_state())) {
				String[] arr = searchVO.getSc_state().split(",");
				searchVO.setSc_stateArr(arr);
			}
			
			int tCount = popupService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			itemList = popupService.selectPageList(searchVO);
			
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
	 * @Method Name	: aPopupForm
	 * @작성일		: 2019. 10. 23.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 팝업 등록/수정 화면
	 */
	@RequestMapping(value = "/popup/form.do")
	public String aPopupForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") PopupSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		PopupVO item = new PopupVO();
		List<AtchmnflVO> fileList = null;
		try {
			if(searchVO.getPopupSn() != null && searchVO.getPopupSn() > 0) {
				// 일련번호가 있으면 수정
				PopupVO popupVO = new PopupVO();
				popupVO.setPopupSn(searchVO.getPopupSn());
				item = popupService.selectObj(popupVO);
				
				// 첨부파일 목록 조회
				AtchmnflVO atchmnflVO = new AtchmnflVO();
				atchmnflVO.setAccTy("popup");
				atchmnflVO.setAccSn("" + item.getPopupSn());
				fileList = atchmnflService.selectList(atchmnflVO);
			} else {
				// 등록 일때는 등록 권한 필요
				if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
					logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
					throw new WrongApproachException("C0041");
				}
			}
		} catch (Exception e) {
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		@SuppressWarnings("static-access")
		Long uniqueId = uploadHelper.getUniqueID();
		request.getSession().setAttribute("file_" + uniqueId, (fileList != null && fileList.size() > 0)? fileList : null);
		
		model.addAttribute("uniqueId", uniqueId);
		model.addAttribute("role",     role);
		model.addAttribute("item",     item);
		model.addAttribute("admURI",   admURI);
		return "mngr/popup/form";
	}
	
	/**
	 * @Method Name	: aPopupInsert
	 * @작성일		: 2019. 10. 23.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 팝업 정보 저장
	 */
	@RequestMapping(value = "/popup/insert.do", method = RequestMethod.POST)
	public String aPopupInsert(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") PopupVO vo,
			@RequestParam(value = "uniqueId", required = true) String uniqueId,
			MngrVO admSession,
			String key,
			Boolean refAt,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			popupService.insertInfo(request, vo, uniqueId, admSession.getMngrId());
		} catch (Exception e) {
			logger.error("팝업 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0001");
		}
		
		model.addAttribute("url", "/" + admURI + "/popup/list.do?key=" + key);
		model.addAttribute("msg", "저장 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aPopupUpdate
	 * @작성일		: 2019. 10. 24.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 팝업 정보 수정
	 */
	@RequestMapping(value = "/popup/update.do", method = RequestMethod.POST)
	public String aPopupUpdate(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") PopupSearchVO searchVO,
			@ModelAttribute("vo") PopupVO vo,
			@RequestParam(value = "popupSn", required = true) int popupSn,
			@RequestParam(value = "uniqueId", required = true) String uniqueId,
			MngrVO admSession,
			String key,
			Boolean refAt,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			PopupVO popupVO = new PopupVO();
			popupVO.setPopupSn(popupSn);
			PopupVO item = popupService.selectObj(popupVO);
			
			if(item != null) {
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				popupService.updateInfo(request, bfData, vo, uniqueId, admSession.getMngrId());
			}
		} catch (Exception e) {
			logger.error("팝업 정보 수정 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/popup/form.do?key=" + key + "&popupSn=" + popupSn);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aPopupDeleteForList
	 * @작성일		: 2019. 10. 24.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 팝업 선택정보 삭제
	 */
	@RequestMapping(value = "/popup/deleteForList.do", method = RequestMethod.POST)
	public String aPopupDeleteForList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") PopupSearchVO searchVO,
			@RequestParam("chkKey") String[] chkKey,
			MngrVO admSession,
			String key,
			Boolean refAt,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		if(chkKey != null && chkKey.length > 0) {
			try {
				for (String itemKey : chkKey) {
					if(StringUtils.isEmpty(itemKey)) {
						continue;
					}
					
					PopupVO popupVO = new PopupVO();
					popupVO.setPopupSn(Integer.parseInt(itemKey));
					PopupVO item = popupService.selectObj(popupVO);
					
					if(item != null) {
						item.setUpdtDe(null);
						String bfData = JSONArray.fromObject(item).toString();
						popupService.deleteInfo(request, bfData, item, admSession.getMngrId());
					}
				}
			} catch (Exception e) {
				logger.error("팝업 정보 삭제 오류 : " + e.getMessage());
				throw new WrongApproachException("C0004");
			}
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/popup/list.do?key=" + key);
		model.addAttribute("msg",    "선택한 정보를 삭제 하였습니다.");
		return "success";
	}

}
