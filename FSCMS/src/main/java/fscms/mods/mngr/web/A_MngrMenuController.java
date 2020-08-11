package fscms.mods.mngr.web;

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

import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.util.FsFuncCmmHelper;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.RoleVO;
import fscms.mods.mngr.service.MngrMenuService;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.util.MngrMenuHelper;
import fscms.mods.mngr.vo.MngrMenuVO;
import fscms.mods.mngr.vo.MngrVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_MngrMenuController {
	
	static Logger logger = LogManager.getLogger(A_MngrMenuController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "mngrMenuService")
	private MngrMenuService mngrMenuService;
	
	@Resource(name = "mngrMenuHelper")
	private MngrMenuHelper mngrMenuHelper;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	/**
	 * @Method Name	: mngrMenuForm
	 * @작성일		: 2019. 10. 4.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 관리자_메뉴 등록 화면
	 */
	@RequestMapping(value = "/mngr/menu/form.do", method = RequestMethod.GET)
	public String aMngrMenuForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
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
		return "mngr/mngr/menu_form";
	}
	
	/**
	 * @Method Name	: mngrMenuGetObj
	 * @작성일		: 2019. 10. 4.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 관리자_메뉴 정보 조회
	 */
	@RequestMapping(value = "/mngr/menu/getObj.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aMngrMenuGetObj(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "menuSn", required = true) String menuSn,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		MngrMenuVO item = null;
		try {
			MngrMenuVO mngrMenuVO = new MngrMenuVO();
			mngrMenuVO.setMenuSn(menuSn);
			item = mngrMenuService.selectObj(mngrMenuVO);
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(item).toString();
	}
	
	/**
	 * @Method Name	: aMngrMenuGetList
	 * @작성일		: 2019. 10. 4.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 관리자_메뉴 정보 목록 조회
	 */
	@RequestMapping(value = "/mngr/menu/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aMngrMenuGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<MngrMenuVO> itemList = null;
		try {
			MngrMenuVO mngrMenuVO = new MngrMenuVO();
			mngrMenuVO.setMenuDp(1);
			itemList = mngrMenuService.selectList_C(mngrMenuVO);
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(itemList).toString();
	}
	
	/**
	 * @Method Name	: aMngrMenuSetInsert
	 * @작성일		: 2019. 10. 4.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 관리자_메뉴 정보 저장
	 */
	@RequestMapping(value = "/mngr/menu/setInsert.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aMngrMenuSetInsert(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "menuSn", required = false) String menuSn,
			@RequestParam(value = "menuNm", required = true) String menuNm,
			MngrVO admSession,
			String key,
			Boolean refAtAjax) {
		
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		JsonVO jsonVO = new JsonVO();
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxRoleException(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		String menuKey = null;
		try {
			menuKey = fsFuncCmmHelper.getSerialNo();
			
			if(StringUtils.isNotEmpty(menuSn) && !StringUtils.equals(menuSn, "0")) {
				MngrMenuVO mngrMenuVO = new MngrMenuVO();
				mngrMenuVO.setMenuSn(menuSn);
				MngrMenuVO item = mngrMenuService.selectObj(mngrMenuVO);
				
				if(item != null) {
					mngrMenuVO = new MngrMenuVO();
					mngrMenuVO.setMenuUpperSn(item.getMenuSn());
					int maxO = mngrMenuService.selectMaxO(mngrMenuVO);
					
					mngrMenuVO = new MngrMenuVO();
					mngrMenuVO.setMenuSn(menuKey);
					mngrMenuVO.setMenuBestSn(item.getMenuBestSn());
					mngrMenuVO.setMenuUpperSn(item.getMenuSn());
					mngrMenuVO.setMenuOrdr(maxO + 1);
					mngrMenuVO.setMenuDp(item.getMenuDp() + 1);
					mngrMenuVO.setMenuNm(menuNm);
					mngrMenuVO.setMenuCnncTy("0");
					mngrMenuVO.setMenuItnadrTy("Y");
					mngrMenuVO.setMenuTrgt("_self");
					mngrMenuVO.setActvtyAt("Y");
					mngrMenuVO.setUseAt("Y");
					mngrMenuVO.setMenuLink("/" + admURI + "/sub.do?key=" + menuKey);
					mngrMenuService.insertInfo(request, mngrMenuVO, admSession.getMngrId());
				}
			} else {
				MngrMenuVO mngrMenuVO = new MngrMenuVO();
				mngrMenuVO.setMenuDp(1);
				int maxO = mngrMenuService.selectMaxO(mngrMenuVO);
				
				mngrMenuVO = new MngrMenuVO();
				mngrMenuVO.setMenuSn(menuKey);
				mngrMenuVO.setMenuBestSn(menuKey);
				mngrMenuVO.setMenuUpperSn(null);
				mngrMenuVO.setMenuOrdr(maxO + 1);
				mngrMenuVO.setMenuDp(1);
				mngrMenuVO.setMenuNm(menuNm);
				mngrMenuVO.setMenuCnncTy("0");
				mngrMenuVO.setMenuItnadrTy("Y");
				mngrMenuVO.setMenuTrgt("_self");
				mngrMenuVO.setActvtyAt("Y");
				mngrMenuVO.setUseAt("Y");
				mngrMenuVO.setMenuLink("/" + admURI + "/sub.do?key=" + menuKey);
				mngrMenuService.insertInfo(request, mngrMenuVO, admSession.getMngrId());
			}
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0001"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		jsonVO.setKey(menuKey);
		return JSONArray.fromObject(jsonVO).toString();
	}
	
	/**
	 * @Method Name	: aMngrMenuSetInfo
	 * @작성일		: 2019. 10. 4.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 관리자_메뉴 정보 수정
	 */
	@RequestMapping(value = "/mngr/menu/setInfo.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aMngrMenuSetInfo(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") MngrMenuVO vo,
			@RequestParam(value = "menuSn", required = true) String menuSn,
			MngrVO admSession,
			String key,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxRoleException(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		try {
			MngrMenuVO mngrMenuVO = new MngrMenuVO();
			mngrMenuVO.setMenuSn(menuSn);
			MngrMenuVO item = mngrMenuService.selectObj(mngrMenuVO);
			
			if(item != null) {
				String menuCnncTy = vo.getMenuCnncTy();
				
				if(StringUtils.isNotEmpty(menuCnncTy) && StringUtils.equals(menuCnncTy, "1")) {
					// 연결설정 - 링크
					vo.setMenuLink(((StringUtils.equals(vo.getMenuItnadrTy(), "Y"))? "/" + admURI : "") + vo.getMenuItnadr() + ((StringUtils.equals(vo.getMenuItnadrTy(), "Y"))? "?key=" + menuSn : ""));
					
					if(!StringUtils.equals(vo.getMenuItnadrTy(), "Y")) {
						vo.setMenuItnadrTy("N");
					}
				} else if(StringUtils.isNotEmpty(menuCnncTy) && StringUtils.equals(menuCnncTy, "3")) {
					// 연결설정 - 게시판
					vo.setMenuLink("/" + admURI + "/bbs/list.do?key=" + menuSn);
				} else {
					// 빈메뉴
					vo.setMenuLink("/" + admURI + "/sub.do?key=" + menuSn);
				}
				
				if(StringUtils.isNotEmpty(vo.getMenuParamtr())) {
					// 파라미터 정보가 있으면 경로에 추가
					vo.setMenuLink(vo.getMenuLink() + vo.getMenuParamtr());
				}
				
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				mngrMenuService.updateInfo(request, bfData, vo, admSession.getMngrId());
			}
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0003"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(jsonVO).toString();
	}
	
	/**
	 * @Method Name	: aMngrMenuSetName
	 * @작성일		: 2019. 10. 4.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 관리자_메뉴 이름 수정
	 */
	@RequestMapping(value = "/mngr/menu/setName.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aMngrMenuSetName(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "menuSn", required = true) String menuSn,
			@RequestParam(value = "menuNm", required = true) String menuNm,
			MngrVO admSession,
			String key,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxRoleException(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		try {
			MngrMenuVO mngrMenuVO = new MngrMenuVO();
			mngrMenuVO.setMenuSn(menuSn);
			MngrMenuVO item = mngrMenuService.selectObj(mngrMenuVO);
			
			if(item != null) {
				mngrMenuVO.setMenuNm(menuNm);
				
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				mngrMenuService.updateInfo(request, bfData, mngrMenuVO, admSession.getMngrId());
			}
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0003"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(jsonVO).toString();
	}
	
	/**
	 * @Method Name	: aMngrMenuSetOrder
	 * @작성일		: 2019. 10. 4.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 관리자_메뉴 순서 변경
	 */
	@RequestMapping(value = "/mngr/menu/setOrder.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aMngrMenuSetOrder(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "menuSn", required = true) String menuSn,
			@RequestParam(value = "menuUpperSn", required = false) String menuUpperSn,
			@RequestParam(value = "menuOrdr", required = true) int menuOrdr,
			MngrVO admSession,
			String key,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxRoleException(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		try {
			MngrMenuVO mngrMenuVO = new MngrMenuVO();
			mngrMenuVO.setMenuSn(menuSn);
			MngrMenuVO item = mngrMenuService.selectObj(mngrMenuVO);
			
			if(item != null) {
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				
				mngrMenuVO = new MngrMenuVO();
				mngrMenuVO.setMenuBestSn((item.getMenuDp() > 1)? item.getMenuBestSn() : "");
				mngrMenuVO.setMenuDp(item.getMenuDp());
				List<MngrMenuVO> menuList = mngrMenuService.selectList(mngrMenuVO);
				
				if(menuList != null && menuList.size() > 0) {
					int i = 1;
					
					for (MngrMenuVO menu : menuList) {
						if(StringUtils.equals(menu.getMenuSn(), item.getMenuSn())) {
							continue;
						}
						
						mngrMenuVO = new MngrMenuVO();
						mngrMenuVO.setMenuSn(item.getMenuSn());
						mngrMenuVO.setMenuOrdr(i);
						if(menu.getMenuDp() == 1) {
							mngrMenuVO.setMenuUpperSn(null);
						}
						
						mngrMenuService.updateInfo(request, bfData, mngrMenuVO, admSession.getMngrId());
						
						i++;
					}
				}
				
				MngrMenuVO tMenu = new MngrMenuVO();
				
				if(StringUtils.isNotEmpty(menuUpperSn)) {
					// 상위 일련번호가 있으면 상위 정보로 이동
					mngrMenuVO = new MngrMenuVO();
					mngrMenuVO.setMenuSn(menuUpperSn);
					tMenu = mngrMenuService.selectObj(mngrMenuVO);
					
					mngrMenuVO = new MngrMenuVO();
					mngrMenuVO.setMenuUpperSn(menuUpperSn);
				} else {
					mngrMenuVO = new MngrMenuVO();
					mngrMenuVO.setMenuDp(1);
				}
				
				menuList = mngrMenuService.selectList(mngrMenuVO);
				
				if(menuList != null && menuList.size() > 0) {
					int i = 1;
					
					for (MngrMenuVO menu : menuList) {
						if(StringUtils.equals(menu.getMenuSn(), item.getMenuSn()) || menuOrdr < 0) {
							continue;
						}
						
						if(i == (menuOrdr + 1)) {
							// 변경된 순번과 i값이 같으면 건너띄기 (i값 증가)
							i++;
						}
						
						mngrMenuVO = new MngrMenuVO();
						mngrMenuVO.setMenuSn(menu.getMenuSn());
						mngrMenuVO.setMenuOrdr(i);
						if(menu.getMenuDp() == 1) {
							mngrMenuVO.setMenuUpperSn(null);
						}
						
						mngrMenuService.updateInfo(request, bfData, mngrMenuVO, admSession.getMngrId());
						
						i++;
					}
				}
				
				String fKey = (StringUtils.isNotEmpty(tMenu.getMenuSn()) && StringUtils.isNotEmpty(menuUpperSn) && tMenu.getMenuDp() > 0)? tMenu.getMenuBestSn() : item.getMenuSn();
				String pKey = (StringUtils.isNotEmpty(tMenu.getMenuSn()) && StringUtils.isNotEmpty(menuUpperSn))? tMenu.getMenuSn() : "Y";
				int g       = (StringUtils.isNotEmpty(tMenu.getMenuSn()) && StringUtils.isNotEmpty(menuUpperSn) && tMenu.getMenuDp() > 0)? tMenu.getMenuDp() + 1 : 1;
				
				mngrMenuVO = new MngrMenuVO();
				mngrMenuVO.setMenuSn(item.getMenuSn());
				mngrMenuVO.setMenuBestSn(fKey);
				mngrMenuVO.setMenuUpperSn(pKey);
				mngrMenuVO.setMenuOrdr(menuOrdr + 1);
				mngrMenuVO.setMenuDp(g);
				if(mngrMenuVO.getMenuDp() == 1) {
					mngrMenuVO.setMenuUpperSn(null);
				}
				mngrMenuService.updateInfo(request, bfData, mngrMenuVO, admSession.getMngrId());
				
				// 메뉴 정보 재조회
				mngrMenuVO = new MngrMenuVO();
				mngrMenuVO.setMenuSn(menuSn);
				item = mngrMenuService.selectObj(mngrMenuVO);
				
				if(item != null) {
					mngrMenuVO = new MngrMenuVO();
					mngrMenuVO.setMenuUpperSn(item.getMenuSn());
					List<MngrMenuVO> childList = mngrMenuService.selectList(mngrMenuVO);
					
					if(childList != null && childList.size() > 0) {
						for (MngrMenuVO child : childList) {
							this.setChildOrder(request, admSession, item, child, item.getMenuDp() + 1);
						}
					}
				}
			}
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0003"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(jsonVO).toString();
	}
	
	/**
	 * @Method Name	: setChildOrder
	 * @작성일		: 2019. 10. 4.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 하위 관리자_메뉴 순서 변경
	 */
	private String setChildOrder(HttpServletRequest request, MngrVO admSession, MngrMenuVO item, MngrMenuVO child, int menuDp) {
		JsonVO jsonVO = new JsonVO();
		try {
			MngrMenuVO mngrMenuVO = new MngrMenuVO();
			mngrMenuVO.setMenuUpperSn(child.getMenuSn());
			List<MngrMenuVO> childList = mngrMenuService.selectList(mngrMenuVO);
			
			if(childList != null && childList.size() > 0) {
				for (MngrMenuVO menu : childList) {
					this.setChildOrder(request, admSession, item, menu, item.getMenuDp() + 1);
				}
			}
			
			mngrMenuVO = new MngrMenuVO();
			mngrMenuVO.setMenuSn(child.getMenuSn());
			mngrMenuVO.setMenuBestSn(item.getMenuBestSn());
			mngrMenuVO.setMenuDp(menuDp);
			
			child.setUpdtDe(null);
			String bfData = JSONArray.fromObject(child).toString();
			mngrMenuService.updateInfo(request, bfData, mngrMenuVO, admSession.getMngrId());
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0003"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(jsonVO).toString();
	}
	
	/**
	 * @Method Name	: aMngrMenuDeletion
	 * @작성일		: 2019. 10. 7.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 관리자_메뉴 정보 삭제
	 */
	@RequestMapping(value = "/mngr/menu/deletion.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aMngrMenuDeletion(
			HttpServletRequest request,
			@RequestParam(value = "menuSn", required = true) String menuSn,
			MngrVO admSession,
			String key,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxRoleException(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		try {
			MngrMenuVO mngrMenuVO = new MngrMenuVO();
			mngrMenuVO.setMenuSn(menuSn);
			MngrMenuVO item = mngrMenuService.selectObj(mngrMenuVO);
			
			if(item != null) {
				mngrMenuService.deleteInfo(request, item, admSession.getMngrId());
			} else {
				jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
				return JSONArray.fromObject(jsonVO).toString();
			}
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0004"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(jsonVO).toString();
	}
	
	/**
	 * @Method Name	: aMngrMenuSetBatch
	 * @작성일		: 2019. 10. 7.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 관리자_메뉴 데이터 배치
	 */
	@RequestMapping(value = "/mngr/menu/setBatch.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aMngrMenuSetBatch(
			HttpServletRequest request,
			MngrVO admSession,
			String key,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxRoleException(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		// 배치 프로그램 예정
		try {
			mngrMenuHelper.setMenu();
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSystemException(logger, "C0021"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(jsonVO).toString();
	}

}
