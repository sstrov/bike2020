package fscms.mods.mngr.web;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.util.PropConnHelper;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.RoleVO;
import fscms.mods.mngr.service.MngrMenuService;
import fscms.mods.mngr.service.MngrRoleService;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrMenuVO;
import fscms.mods.mngr.vo.MngrRoleSearchVO;
import fscms.mods.mngr.vo.MngrRoleVO;
import fscms.mods.mngr.vo.MngrVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_MngrRoleController {
	
	static Logger logger = LogManager.getLogger(A_MngrRoleController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "mngrMenuService")
	private MngrMenuService mngrMenuService;
	
	@Resource(name = "mngrRoleService")
	private MngrRoleService mngrRoleService;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	/**
	 * @Method Name	: aMngrRoleList
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자_역할 목록 화면
	 */
	@RequestMapping(value = "/mngr/role/list.do")
	public String aMngrRoleList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") MngrRoleSearchVO searchVO,
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
		return "mngr/mngr/role_list";
	}
	
	/**
	 * @Method Name	: aMngrRoleGetList
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자_역할 정보 목록 조회
	 */
	@RequestMapping(value = "/mngr/role/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aMngrRoleGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") MngrRoleSearchVO searchVO,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<MngrRoleVO> itemList = null;
		try {
			int tCount = mngrRoleService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			itemList = mngrRoleService.selectPageList(searchVO);
			
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
	 * @Method Name	: aMngrRoleForm
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자_역할 등록/수정 화면
	 */
	@RequestMapping(value = "/mngr/role/form.do")
	public String aMngrRoleForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") MngrRoleSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		MngrRoleVO item = new MngrRoleVO();
		try {
			if(searchVO.getRoleSn() != null && searchVO.getRoleSn() > 0) {
				// 일련번호가 있으면 수정
				MngrRoleVO mngrRoleVO = new MngrRoleVO();
				mngrRoleVO.setRoleSn(searchVO.getRoleSn());
				item = mngrRoleService.selectObj(mngrRoleVO);
			} else {
				// 등록 일때는 등록 권한 필요
				if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
					logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
					throw new WrongApproachException("C0041");
				}
			}
			
			// 콘텐츠 목록 가져오기
			MngrMenuVO mngrMenuVO = new MngrMenuVO();
			mngrMenuVO.setMenuDp(1);
			mngrMenuVO.setUseAt("Y");
			
			List<MngrMenuVO> mList = null;
			List<MngrMenuVO> contentList = new ArrayList<MngrMenuVO>();
			if(item != null && item.getRoleSn() != null) {
				mngrMenuVO.setSc_roleSn(item.getRoleSn());
				mList = mngrMenuService.selectList_acc(mngrMenuVO);
				model.addAttribute("contentList", this.getContentList(mList, contentList, item.getRoleSn()));
			} else {
				mList = mngrMenuService.selectList(mngrMenuVO);
				model.addAttribute("contentList", this.getContentList(mList, contentList, null));
			}
		} catch (Exception e) {
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("role",   role);
		model.addAttribute("item",   item);
		model.addAttribute("admURI", admURI);
		return "mngr/mngr/role_form";
	}
	
	/**
	 * @Method Name	: getContentList
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 콘텐츠 목록 가져오기
	 */
	private List<MngrMenuVO> getContentList(List<MngrMenuVO> itemList, List<MngrMenuVO> contentList, Integer roleSn) throws Exception {
		
		if(itemList != null && itemList.size() > 0) {
			for (MngrMenuVO item : itemList) {
				contentList.add(item);
				
				MngrMenuVO mngrMenuVO = new MngrMenuVO();
				mngrMenuVO.setMenuUpperSn(item.getMenuSn());
				mngrMenuVO.setUseAt("Y");
				
				if(roleSn != null && roleSn > 0) {
					mngrMenuVO.setSc_roleSn(roleSn);
					List<MngrMenuVO> mList = mngrMenuService.selectList_acc(mngrMenuVO);
					contentList = this.getContentList(mList, contentList, roleSn);
				} else {
					List<MngrMenuVO> mList = mngrMenuService.selectList(mngrMenuVO);
					contentList = this.getContentList(mList, contentList, null);
				}
			}
		}
		
		return contentList;
	}
	
	/**
	 * @Method Name	: aMngrRoleIsexistId
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자_역할 명 중복 체크
	 */
	@RequestMapping(value = "/mngr/role/isExistId.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aMngrRoleIsexistId(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "roleNm", required = true) String roleNm,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		try {
			PropConnHelper propConnHelper = new PropConnHelper();
			
			MngrRoleVO mngrRoleVO = new MngrRoleVO();
			mngrRoleVO.setRoleNm(roleNm);
			MngrRoleVO item = mngrRoleService.selectObj(mngrRoleVO);
			
			if(item != null) {
				jsonVO.setState("200");
				jsonVO.setMsg(propConnHelper.getMessage(propConnHelper.getConn("globals", "Globals.language"), "is.exist"));
			}
			
			return JSONArray.fromObject(jsonVO).toString();
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
	}
	
	/**
	 * @Method Name	: aMngrRoleInsert
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자_역할 정보 저장
	 */
	@RequestMapping(value = "/mngr/role/insert.do", method = RequestMethod.POST)
	public String aMngrRoleInsert(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") MngrRoleVO vo,
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
			// 최대 순번 가져오기
			int maxO = mngrRoleService.selectMaxO(null);
			
			vo.setRoleOrdr(maxO + 1);
			mngrRoleService.insertInfo(request, vo, admSession.getMngrId());
		} catch (Exception e) {
			logger.error("관리자_역할 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0001");
		}
		
		model.addAttribute("url", "/" + admURI + "/mngr/role/list.do?key=" + key);
		model.addAttribute("msg", "저장 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aMngrRoleUpdate
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자_역할 정보 수정
	 */
	@RequestMapping(value = "/mngr/role/update.do", method = RequestMethod.POST)
	public String aMngrRoleUpdate(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") MngrRoleSearchVO searchVO,
			@ModelAttribute("vo") MngrRoleVO vo,
			@RequestParam(value = "roleSn", required = true) int roleSn,
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
			MngrRoleVO mngrRoleVO = new MngrRoleVO();
			mngrRoleVO.setRoleSn(roleSn);
			MngrRoleVO item = mngrRoleService.selectObj(mngrRoleVO);
			
			if(item != null) {
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				mngrRoleService.updateInfo(request, bfData, vo, admSession.getMngrId());
			}
		} catch (Exception e) {
			logger.error("관리자_역할 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/mngr/role/form.do?key=" + key + "&roleSn=" + roleSn);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aMngrRoleDeleteForList
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자_역할 선택정보 삭제
	 */
	@RequestMapping(value = "/mngr/role/deleteForList.do", method = RequestMethod.POST)
	public String aMngrRoleDeleteForList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") MngrRoleSearchVO searchVO,
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
					
					MngrRoleVO mngrRoleVO = new MngrRoleVO();
					mngrRoleVO.setRoleSn(Integer.parseInt(itemKey));
					MngrRoleVO item = mngrRoleService.selectObj(mngrRoleVO);
					
					if(item != null) {
						item.setUpdtDe(null);
						String bfData = JSONArray.fromObject(item).toString();
						mngrRoleService.deleteInfo(request, bfData, item, admSession.getMngrId());
					}
				}
			} catch (Exception e) {
				logger.error("관리자_역할 정보 삭제 오류 : " + e.getMessage());
				throw new WrongApproachException("C0004");
			}
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/mngr/role/list.do?key=" + key);
		model.addAttribute("msg",    "선택한 정보를 삭제 하였습니다.");
		return "success";
	}
	
}
