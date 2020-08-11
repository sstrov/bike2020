package fscms.mods.user.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
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
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import fscms.mods.user.service.UserMenuService;
import fscms.mods.user.util.UserMenuHelper;
import fscms.mods.user.vo.UserMenuVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_UserMenuController {
	
	static Logger logger = LogManager.getLogger(A_UserMenuController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "userMenuService")
	private UserMenuService userMenuService;
	
	@Resource(name = "userMenuHelper")
	private UserMenuHelper userMenuHelper;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	/**
	 * @Method Name	: aUserMenuForm
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자_메뉴 등록 화면
	 */
	@RequestMapping(value = "/user/menu/form.do")
	public String aUserMenuForm(
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
		return "mngr/user/menu_form";
	}
	
	/**
	 * @Method Name	: aUserMenuGetObj
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자_메뉴 정보 조회
	 */
	@RequestMapping(value = "/user/menu/getObj.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aUserMenuGetObj(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "menuSn", required = true) String menuSn,
			Boolean refAtAjax) {
		
		UserMenuVO item = null;
		try {
			JsonVO jsonVO = new JsonVO();
			if(!refAtAjax) {
				// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
				jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
				return JSONArray.fromObject(jsonVO).toString();
			}
			
			UserMenuVO userMenuVO = new UserMenuVO();
			userMenuVO.setMenuSn(menuSn);
			item = userMenuService.selectObj(userMenuVO);
		} catch (Exception e) {
			JsonVO jsonVO = new JsonVO();
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(item).toString();
	}
	
	/**
	 * @Method Name	: aUserMenuGetList
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자_메뉴 정보 목록 조회
	 */
	@RequestMapping(value = "/user/menu/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aUserMenuGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			Boolean refAtAjax) {
		
		List<UserMenuVO> itemList = null;
		try {
			
			JsonVO jsonVO = new JsonVO();
			if(!refAtAjax) {
				// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
				jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
				return JSONArray.fromObject(jsonVO).toString();
			}
			
			UserMenuVO userMenuVO = new UserMenuVO();
			userMenuVO.setMenuDp(1);
			itemList = userMenuService.selectList_C(userMenuVO);
		} catch (Exception e) {
			JsonVO jsonVO = new JsonVO();
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(itemList).toString();
	}
	
	/**
	 * @Method Name	: aUserMenuSetInsert
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자_메뉴 정보 저장
	 */
	@RequestMapping(value = "/user/menu/setInsert.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aUserMenuSetInsert(
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
				UserMenuVO userMenuVO = new UserMenuVO();
				userMenuVO.setMenuSn(menuSn);
				UserMenuVO item = userMenuService.selectObj(userMenuVO);
				
				if(item != null) {
					userMenuVO = new UserMenuVO();
					userMenuVO.setMenuUpperSn(item.getMenuSn());
					int maxO = userMenuService.selectMaxO(userMenuVO);
					
					userMenuVO = new UserMenuVO();
					userMenuVO.setMenuSn(menuKey);
					userMenuVO.setMenuBestSn(item.getMenuBestSn());
					userMenuVO.setMenuUpperSn(item.getMenuSn());
					userMenuVO.setMenuOrdr(maxO + 1);
					userMenuVO.setMenuDp(item.getMenuDp() + 1);
					userMenuVO.setMenuNm(menuNm);
					userMenuVO.setMenuCnncTy("0");
					userMenuVO.setMenuItnadrTy("Y");
					userMenuVO.setMenuTrgt("_self");
					userMenuVO.setActvtyAt("Y");
					userMenuVO.setUseAt("Y");
					userMenuVO.setMenuLink("/sub.do?key=" + menuKey);
					userMenuService.insertInfo(request, userMenuVO, admSession.getMngrId());
				}
			} else {
				UserMenuVO userMenuVO = new UserMenuVO();
				userMenuVO.setMenuDp(1);
				int maxO = userMenuService.selectMaxO(userMenuVO);
				
				userMenuVO = new UserMenuVO();
				userMenuVO.setMenuSn(menuKey);
				userMenuVO.setMenuBestSn(menuKey);
				userMenuVO.setMenuUpperSn(null);
				userMenuVO.setMenuOrdr(maxO + 1);
				userMenuVO.setMenuDp(1);
				userMenuVO.setMenuNm(menuNm);
				userMenuVO.setMenuCnncTy("0");
				userMenuVO.setMenuItnadrTy("Y");
				userMenuVO.setMenuTrgt("_self");
				userMenuVO.setActvtyAt("Y");
				userMenuVO.setUseAt("Y");
				userMenuVO.setMenuLink("/sub.do?key=" + menuKey);
				userMenuService.insertInfo(request, userMenuVO, admSession.getMngrId());
			}
			
			// 콘텐츠 파일 생성
			this.setContent(menuKey);
		} catch (InterruptedException e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSystemException(logger, "B0021"));
			return JSONArray.fromObject(jsonVO).toString();
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0001"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		jsonVO.setKey(menuKey);
		return JSONArray.fromObject(jsonVO).toString();
	}
	
	/**
	 * @Method Name	: aUserMenuSetInfo
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자_메뉴 정보 수정
	 */
	@RequestMapping(value = "/user/menu/setInfo.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aUserMenuSetInfo(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") UserMenuVO vo,
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
			UserMenuVO userMenuVO = new UserMenuVO();
			userMenuVO.setMenuSn(menuSn);
			UserMenuVO item = userMenuService.selectObj(userMenuVO);
			
			if(item != null) {
				String menuCnncTy = vo.getMenuCnncTy();
				
				if(StringUtils.isNotEmpty(menuCnncTy) && StringUtils.equals(menuCnncTy, "1")) {
					// 연결설정 - 링크
					vo.setMenuLink(vo.getMenuItnadr() + ((StringUtils.equals(vo.getMenuItnadrTy(), "Y"))? "?key=" + menuSn : ""));
					
					if(!StringUtils.equals(vo.getMenuItnadrTy(), "Y")) {
						vo.setMenuItnadrTy("N");
					}
				} else if(StringUtils.isNotEmpty(menuCnncTy) && StringUtils.equals(menuCnncTy, "2")) {
					// 연결설정 - 콘텐츠
					vo.setMenuLink("/content.do?key=" + menuSn);
				} else if(StringUtils.isNotEmpty(menuCnncTy) && StringUtils.equals(menuCnncTy, "3")) {
					// 연결설정 - 게시판
					vo.setMenuLink("/bbs/list.do?key=" + menuSn);
				} else {
					// 빈메뉴
					vo.setMenuLink("/sub.do?key=" + menuSn);
				}
				
				if(StringUtils.isNotEmpty(vo.getMenuParamtr())) {
					// 파라미터 정보가 있으면 경로에 추가
					vo.setMenuLink(vo.getMenuLink() + vo.getMenuParamtr());
				}
				
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				userMenuService.updateInfo(request, bfData, vo, admSession.getMngrId());
				
				// 콘텐츠 파일 생성
				this.setContent(item.getMenuSn());
			}
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0003"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(jsonVO).toString();
	}
	
	/**
	 * @Method Name	: setContent
	 * @작성일		: 2019. 11. 21.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 콘텐츠 파일 생성
	 */
	private void setContent(String key) throws IOException {
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		String rootPath = fsFuncCmmHelper.getAppAbsolutePath() + "/repository/www/content";
		
		File deleteFolder = new File(rootPath);
		if(!deleteFolder.exists()) {
			// root 경로가 없으면 생성
			deleteFolder.mkdirs();
		}
		
		File file = new File(rootPath + "/" + key + ".jsp");
		if(!file.exists()) {
			file.createNewFile();
			
			String tmpStr = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n\n" +
					"<div style=\"text-align:center\"><img src=\"/resource/mng/img/img_ready1.gif\" alt=\"콘텐츠 준비중입니다.\" /></div>";
			
			BufferedWriter bw = new BufferedWriter(fsFuncCmmHelper.setFileWriter(file.getPath()));
			bw.write(tmpStr);
			bw.close();
		}
	}
	
	/**
	 * @Method Name	: aUserMenuSetName
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자_메뉴 이름 수정
	 */
	@RequestMapping(value = "/user/menu/setName.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aUserMenuSetName(
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
			UserMenuVO userMenuVO = new UserMenuVO();
			userMenuVO.setMenuSn(menuSn);
			UserMenuVO item = userMenuService.selectObj(userMenuVO);
			
			if(item != null) {
				userMenuVO.setMenuNm(menuNm);
				
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				userMenuService.updateInfo(request, bfData, userMenuVO, admSession.getMngrId());
			}
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0003"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(jsonVO).toString();
	}
	
	/**
	 * @Method Name	: aUserMenuSetOrder
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자_메뉴 순서 변경
	 */
	@RequestMapping(value = "/user/menu/setOrder.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aUserMenuSetOrder(
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
			UserMenuVO userMenuVO = new UserMenuVO();
			userMenuVO.setMenuSn(menuSn);
			UserMenuVO item = userMenuService.selectObj(userMenuVO);
			
			if(item != null) {
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				
				userMenuVO = new UserMenuVO();
				userMenuVO.setMenuBestSn((item.getMenuDp() > 1)? item.getMenuBestSn() : "");
				userMenuVO.setMenuDp(item.getMenuDp());
				List<UserMenuVO> menuList = userMenuService.selectList(userMenuVO);
				
				if(menuList != null && menuList.size() > 0) {
					int i = 1;
					
					for (UserMenuVO menu : menuList) {
						if(StringUtils.equals(menu.getMenuSn(), item.getMenuSn())) {
							continue;
						}
						
						userMenuVO = new UserMenuVO();
						userMenuVO.setMenuSn(item.getMenuSn());
						userMenuVO.setMenuOrdr(i);
						if(menu.getMenuDp() == 1) {
							userMenuVO.setMenuUpperSn(null);
						}
						
						userMenuService.updateInfo(request, bfData, userMenuVO, admSession.getMngrId());
						
						i++;
					}
				}
				
				UserMenuVO tMenu = new UserMenuVO();
				
				if(StringUtils.isNotEmpty(menuUpperSn)) {
					// 상위 일련번호가 있으면 상위 정보로 이동
					userMenuVO = new UserMenuVO();
					userMenuVO.setMenuSn(menuUpperSn);
					tMenu = userMenuService.selectObj(userMenuVO);
					
					userMenuVO = new UserMenuVO();
					userMenuVO.setMenuUpperSn(menuUpperSn);
				} else {
					userMenuVO = new UserMenuVO();
					userMenuVO.setMenuDp(1);
				}
				
				menuList = userMenuService.selectList(userMenuVO);
				
				if(menuList != null && menuList.size() > 0) {
					int i = 1;
					
					for (UserMenuVO menu : menuList) {
						if(StringUtils.equals(menu.getMenuSn(), item.getMenuSn()) || menuOrdr < 0) {
							continue;
						}
						
						if(i == (menuOrdr + 1)) {
							// 변경된 순번과 i값이 같으면 건너띄기 (i값 증가)
							i++;
						}
						
						userMenuVO = new UserMenuVO();
						userMenuVO.setMenuSn(menu.getMenuSn());
						userMenuVO.setMenuOrdr(i);
						if(menu.getMenuDp() == 1) {
							userMenuVO.setMenuUpperSn(null);
						}
						
						userMenuService.updateInfo(request, bfData, userMenuVO, admSession.getMngrId());
						
						i++;
					}
				}
				
				String fKey = (StringUtils.isNotEmpty(tMenu.getMenuSn()) && StringUtils.isNotEmpty(menuUpperSn) && tMenu.getMenuDp() > 0)? tMenu.getMenuBestSn() : item.getMenuSn();
				String pKey = (StringUtils.isNotEmpty(tMenu.getMenuSn()) && StringUtils.isNotEmpty(menuUpperSn))? tMenu.getMenuSn() : "Y";
				int g       = (StringUtils.isNotEmpty(tMenu.getMenuSn()) && StringUtils.isNotEmpty(menuUpperSn) && tMenu.getMenuDp() > 0)? tMenu.getMenuDp() + 1 : 1;
				
				userMenuVO = new UserMenuVO();
				userMenuVO.setMenuSn(item.getMenuSn());
				userMenuVO.setMenuBestSn(fKey);
				userMenuVO.setMenuUpperSn(pKey);
				userMenuVO.setMenuOrdr(menuOrdr + 1);
				userMenuVO.setMenuDp(g);
				if(userMenuVO.getMenuDp() == 1) {
					userMenuVO.setMenuUpperSn(null);
				}
				userMenuService.updateInfo(request, bfData, userMenuVO, admSession.getMngrId());
				
				// 메뉴 정보 재조회
				userMenuVO = new UserMenuVO();
				userMenuVO.setMenuSn(menuSn);
				item = userMenuService.selectObj(userMenuVO);
				
				if(item != null) {
					userMenuVO = new UserMenuVO();
					userMenuVO.setMenuUpperSn(item.getMenuSn());
					List<UserMenuVO> childList = userMenuService.selectList(userMenuVO);
					
					if(childList != null && childList.size() > 0) {
						for (UserMenuVO child : childList) {
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
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 하위 사용자_메뉴 순서 변경
	 */
	private String setChildOrder(HttpServletRequest request, MngrVO admSession, UserMenuVO item, UserMenuVO child, int menuDp) {
		JsonVO jsonVO = new JsonVO();
		try {
			UserMenuVO userMenuVO = new UserMenuVO();
			userMenuVO.setMenuUpperSn(child.getMenuSn());
			List<UserMenuVO> childList = userMenuService.selectList(userMenuVO);
			
			if(childList != null && childList.size() > 0) {
				for (UserMenuVO menu : childList) {
					this.setChildOrder(request, admSession, item, menu, item.getMenuDp() + 1);
				}
			}
			
			userMenuVO = new UserMenuVO();
			userMenuVO.setMenuSn(child.getMenuSn());
			userMenuVO.setMenuBestSn(item.getMenuBestSn());
			userMenuVO.setMenuDp(menuDp);
			
			child.setUpdtDe(null);
			String bfData = JSONArray.fromObject(child).toString();
			userMenuService.updateInfo(request, bfData, userMenuVO, admSession.getMngrId());
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0003"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(jsonVO).toString();
	}
	
	/**
	 * @Method Name	: aUserMenuDeletion
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자_메뉴 정보 삭제
	 */
	@RequestMapping(value = "/user/menu/deletion.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aUserMenuDeletion(
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
			UserMenuVO userMenuVO = new UserMenuVO();
			userMenuVO.setMenuSn(menuSn);
			UserMenuVO item = userMenuService.selectObj(userMenuVO);
			
			if(item != null) {
				userMenuService.deleteInfo(request, item, admSession.getMngrId());
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
	 * @Method Name	: aUserMenuSetFile
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자_메뉴 파일 등록
	 */
	@RequestMapping(value = "/user/menu/setFile.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aUserMenuSetFile(
			HttpServletRequest request,
			@RequestParam(value = "menuSn", required = true) String menuSn,
			@RequestParam(value = "menuFileImage", required = false) String menuFileImage,
			@RequestParam(value = "menuFlpth", required = false) String menuFlpth,
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
			UserMenuVO userMenuVO = new UserMenuVO();
			userMenuVO.setMenuSn(menuSn);
			UserMenuVO item = userMenuService.selectObj(userMenuVO);
			
			if(item != null) {
				userMenuHelper.deleteFile(menuSn);
				
				userMenuVO.setMenuFileImage(menuFileImage);
				userMenuVO.setMenuFlpth(menuFlpth);
				userMenuService.updateInfo(request, null, userMenuVO, admSession.getMngrId());
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
	 * @Method Name	: aUserMenuSetBatch
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자_메뉴 데이터 배치
	 */
	@RequestMapping(value = "/user/menu/setBatch.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aUserMenuSetBatch(
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
			userMenuHelper.setMenu();
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSystemException(logger, "C0021"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(jsonVO).toString();
	}

}
