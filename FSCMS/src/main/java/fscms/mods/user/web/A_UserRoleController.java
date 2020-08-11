package fscms.mods.user.web;

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
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import fscms.mods.user.service.UserMenuService;
import fscms.mods.user.service.UserRoleService;
import fscms.mods.user.vo.UserMenuVO;
import fscms.mods.user.vo.UserRoleSearchVO;
import fscms.mods.user.vo.UserRoleVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_UserRoleController {
	
	static Logger logger = LogManager.getLogger(A_UserRoleController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "userMenuService")
	private UserMenuService userMenuService;
	
	@Resource(name = "userRoleService")
	private UserRoleService userRoleService;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	/**
	 * @Method Name	: aUserRoleList
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자_역할 목록 화면
	 */
	@RequestMapping(value = "/user/role/list.do")
	public String aUserRoleList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") UserRoleSearchVO searchVO,
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
		return "mngr/user/role_list";
	}
	
	/**
	 * @Method Name	: aUserRoleGetList
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자_역할 정보 목록 조회
	 */
	@RequestMapping(value = "/user/role/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aUserRoleGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") UserRoleSearchVO searchVO,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<UserRoleVO> itemList = null;
		try {
			int tCount = userRoleService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			itemList = userRoleService.selectPageList(searchVO);
			
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
	 * @Method Name	: aUserRoleForm
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자_역할 등록/수정 화면
	 */
	@RequestMapping(value = "/user/role/form.do")
	public String aUserRoleForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") UserRoleSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		UserRoleVO item = new UserRoleVO();
		try {
			if(searchVO.getRoleSn() != null && searchVO.getRoleSn() > 0) {
				// 일련번호가 있으면 수정
				UserRoleVO userRoleVO = new UserRoleVO();
				userRoleVO.setRoleSn(searchVO.getRoleSn());
				item = userRoleService.selectObj(userRoleVO);
			} else {
				// 등록 일때는 등록 권한 필요
				if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
					logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
					throw new WrongApproachException("C0041");
				}
			}
			
			// 콘텐츠 목록 가져오기
			UserMenuVO userMenuVO = new UserMenuVO();
			userMenuVO.setMenuDp(1);
			userMenuVO.setUseAt("Y");
			
			List<UserMenuVO> mList = null;
			List<UserMenuVO> contentList = new ArrayList<UserMenuVO>();
			if(item != null && item.getRoleSn() != null) {
				userMenuVO.setSc_roleSn(item.getRoleSn());
				mList = userMenuService.selectList_acc(userMenuVO);
				model.addAttribute("contentList", this.getContentList(mList, contentList, item.getRoleSn()));
			} else {
				mList = userMenuService.selectList(userMenuVO);
				model.addAttribute("contentList", this.getContentList(mList, contentList, null));
			}
		} catch (Exception e) {
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("role",   role);
		model.addAttribute("item",   item);
		model.addAttribute("admURI", admURI);
		return "mngr/user/role_form";
	}
	
	/**
	 * @Method Name	: getContentList
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 콘텐츠 목록 가져오기
	 */
	private List<UserMenuVO> getContentList(List<UserMenuVO> itemList, List<UserMenuVO> contentList, Integer roleSn) throws Exception {
		
		if(itemList != null && itemList.size() > 0) {
			for (UserMenuVO item : itemList) {
				contentList.add(item);
				
				UserMenuVO userMenuVO = new UserMenuVO();
				userMenuVO.setMenuUpperSn(item.getMenuSn());
				userMenuVO.setUseAt("Y");
				
				if(roleSn != null && roleSn > 0) {
					userMenuVO.setSc_roleSn(roleSn);
					List<UserMenuVO> mList = userMenuService.selectList_acc(userMenuVO);
					contentList = this.getContentList(mList, contentList, roleSn);
				} else {
					List<UserMenuVO> mList = userMenuService.selectList(userMenuVO);
					contentList = this.getContentList(mList, contentList, null);
				}
			}
		}
		
		return contentList;
	}
	
	/**
	 * @Method Name	: aUserRoleIsexistId
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자_역할 명 중복 체크
	 */
	@RequestMapping(value = "/user/role/isExistId.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aUserRoleIsexistId(
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
			
			UserRoleVO userRoleVO = new UserRoleVO();
			userRoleVO.setRoleNm(roleNm);
			UserRoleVO item = userRoleService.selectObj(userRoleVO);
			
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
	 * @Method Name	: aUserRoleInsert
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자_역할 정보 저장
	 */
	@RequestMapping(value = "/user/role/insert.do", method = RequestMethod.POST)
	public String aUserRoleInsert(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") UserRoleVO vo,
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
			int maxO = userRoleService.selectMaxO(null);
			
			vo.setRoleOrdr(maxO + 1);
			userRoleService.insertInfo(request, vo, admSession.getMngrId());
		} catch (Exception e) {
			logger.error("사용자_역할 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0001");
		}
		
		model.addAttribute("url", "/" + admURI + "/user/role/list.do?key=" + key);
		model.addAttribute("msg", "저장 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aUserRoleUpdate
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자_역할 정보 수정
	 */
	@RequestMapping(value = "/user/role/update.do", method = RequestMethod.POST)
	public String aUserRoleUpdate(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") UserRoleSearchVO searchVO,
			@ModelAttribute("vo") UserRoleVO vo,
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
			UserRoleVO userRoleVO = new UserRoleVO();
			userRoleVO.setRoleSn(roleSn);
			UserRoleVO item = userRoleService.selectObj(userRoleVO);
			
			if(item != null) {
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				userRoleService.updateInfo(request, bfData, vo, admSession.getMngrId());
			}
		} catch (Exception e) {
			logger.error("사용자_역할 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/user/role/form.do?key=" + key + "&roleSn=" + roleSn);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aUserRoleDeleteForList
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자_역할 선택정보 삭제
	 */
	@RequestMapping(value = "/user/role/deleteForList.do", method = RequestMethod.POST)
	public String aUserRoleDeleteForList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") UserRoleSearchVO searchVO,
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
					
					UserRoleVO userRoleVO = new UserRoleVO();
					userRoleVO.setRoleSn(Integer.parseInt(itemKey));
					UserRoleVO item = userRoleService.selectObj(userRoleVO);
					
					if(item != null) {
						item.setUpdtDe(null);
						String bfData = JSONArray.fromObject(item).toString();
						userRoleService.deleteInfo(request, bfData, item, admSession.getMngrId());
					}
				}
			} catch (Exception e) {
				logger.error("사용자_역할 정보 삭제 오류 : " + e.getMessage());
				throw new WrongApproachException("C0004");
			}
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/user/role/list.do?key=" + key);
		model.addAttribute("msg",    "선택한 정보를 삭제 하였습니다.");
		return "success";
	}

}
