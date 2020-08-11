package fscms.mods.bbs.web;

import java.io.File;
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
import fscms.mods.bbs.service.BbsSknService;
import fscms.mods.bbs.vo.BbsSknVO;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_BbsSknController {
	
	static Logger logger = LogManager.getLogger(A_BbsSknController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "bbsSknService")
	private BbsSknService bbsSknService;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	/**
	 * @Method Name	: aBbsSknForm
	 * @작성일		: 2019. 11. 14.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판_스킨 등록 화면
	 */
	@RequestMapping(value = "/bbs/skn/form.do")
	public String aBbsSknForm(
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
		return "mngr/bbs/skn_form";
	}
	
	/**
	 * @Method Name	: aBbsSknGetObj
	 * @작성일		: 2019. 11. 14.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판_스킨 정보 조회
	 */
	@RequestMapping(value = "/bbs/skn/getObj.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aBbsSknGetObj(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "bbsSknSn", required = true) int bbsSknSn,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		BbsSknVO item = null;
		try {
			BbsSknVO bbsSknVO = new BbsSknVO();
			bbsSknVO.setBbsSknSn(bbsSknSn);
			item = bbsSknService.selectObj(bbsSknVO);
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(item).toString();
	}
	
	/**
	 * @Method Name	: aBbsSknGetList
	 * @작성일		: 2019. 11. 14.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판_스킨 정보 목록 조회
	 */
	@RequestMapping(value = "/bbs/skn/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aBbsSknGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "bbsSknUpperSn", required = false) Integer bbsSknUpperSn,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<BbsSknVO> itemList = null;
		try {
			BbsSknVO bbsSknVO = new BbsSknVO();
			
			if(bbsSknUpperSn != null && bbsSknUpperSn > 0) {
				bbsSknVO.setBbsSknUpperSn(bbsSknUpperSn);
				itemList = bbsSknService.selectList(bbsSknVO);
			} else {
				bbsSknVO.setBbsSknDp(1);
				itemList = bbsSknService.selectList_C(bbsSknVO);
			}
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(itemList).toString();
	}
	
	/**
	 * @Method Name	: aBbsSknSetInsert
	 * @작성일		: 2019. 11. 14.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판_스킨 정보 저장
	 */
	@RequestMapping(value = "/bbs/skn/setInsert.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aBbsSknSetInsert(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "bbsSknSn", required = false) Integer bbsSknSn,
			@RequestParam(value = "bbsSknNm", required = true) String bbsSknNm,
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
		
		int sn = 0;
		try {
			sn = bbsSknService.selectMaxKey();
			
			if(bbsSknSn != null && bbsSknSn > 0) {
				BbsSknVO bbsSknVO = new BbsSknVO();
				bbsSknVO.setBbsSknSn(bbsSknSn);
				BbsSknVO item = bbsSknService.selectObj(bbsSknVO);
				
				if(item != null) {
					bbsSknVO = new BbsSknVO();
					bbsSknVO.setBbsSknUpperSn(item.getBbsSknSn());
					int maxO = bbsSknService.selectMaxO(bbsSknVO);
					
					bbsSknVO = new BbsSknVO();
					bbsSknVO.setBbsSknSn(sn);
					bbsSknVO.setBbsSknBestSn(item.getBbsSknBestSn());
					bbsSknVO.setBbsSknUpperSn(item.getBbsSknSn());
					bbsSknVO.setBbsSknOrdr(maxO + 1);
					bbsSknVO.setBbsSknDp(item.getBbsSknDp() + 1);
					bbsSknVO.setBbsSknNm(bbsSknNm);
					bbsSknVO.setCmmnViewAt("Y");
					bbsSknVO.setUseAt("Y");
					bbsSknService.insertInfo(request, bbsSknVO, admSession.getMngrId());
				}
			} else {
				BbsSknVO bbsSknVO = new BbsSknVO();
				bbsSknVO.setBbsSknDp(1);
				int maxO = bbsSknService.selectMaxO(bbsSknVO);
				
				bbsSknVO = new BbsSknVO();
				bbsSknVO.setBbsSknSn(sn);
				bbsSknVO.setBbsSknBestSn(sn);
				bbsSknVO.setBbsSknUpperSn(null);
				bbsSknVO.setBbsSknOrdr(maxO + 1);
				bbsSknVO.setBbsSknDp(1);
				bbsSknVO.setBbsSknNm(bbsSknNm);
				bbsSknVO.setCmmnViewAt("Y");
				bbsSknVO.setUseAt("Y");
				bbsSknService.insertInfo(request, bbsSknVO, admSession.getMngrId());
			}
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0001"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		jsonVO.setKey("" + sn);
		return JSONArray.fromObject(jsonVO).toString();
	}
	
	/**
	 * @Method Name	: aBbsSknSetInfo
	 * @작성일		: 2019. 11. 14.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판_스킨 정보 수정
	 */
	@RequestMapping(value = "/bbs/skn/setInfo.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aBbsSknSetInfo(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") BbsSknVO vo,
			@RequestParam(value = "bbsSknSn", required = true) int bbsSknSn,
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
			BbsSknVO bbsSknVO = new BbsSknVO();
			bbsSknVO.setBbsSknSn(bbsSknSn);
			BbsSknVO item = bbsSknService.selectObj(bbsSknVO);
			
			if(item != null) {
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				bbsSknService.updateInfo(request, bfData, vo, admSession.getMngrId());
			}
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0003"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(jsonVO).toString();
	}
	
	/**
	 * @Method Name	: aBbsSknSetName
	 * @작성일		: 2019. 11. 14.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판_스킨 이름 수정
	 */
	@RequestMapping(value = "/bbs/skn/setName.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aBbsSknSetName(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "bbsSknSn", required = true) int bbsSknSn,
			@RequestParam(value = "bbsSknNm", required = true) String bbsSknNm,
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
			BbsSknVO bbsSknVO = new BbsSknVO();
			bbsSknVO.setBbsSknSn(bbsSknSn);
			BbsSknVO item = bbsSknService.selectObj(bbsSknVO);
			
			if(item != null) {
				bbsSknVO.setBbsSknNm(bbsSknNm);
				
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				bbsSknService.updateInfo(request, bfData, bbsSknVO, admSession.getMngrId());
			}
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0003"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(jsonVO).toString();
	}
	
	/**
	 * @Method Name	: aBbsSknSetOrder
	 * @작성일		: 2019. 11. 14.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판_스킨 순서 변경
	 */
	@RequestMapping(value = "/bbs/skn/setOrder.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aBbsSknSetOrder(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "bbsSknSn", required = true) int bbsSknSn,
			@RequestParam(value = "bbsSknUpperSn", required = false) Integer bbsSknUpperSn,
			@RequestParam(value = "bbsSknOrdr", required = true) int bbsSknOrdr,
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
			BbsSknVO bbsSknVO = new BbsSknVO();
			bbsSknVO.setBbsSknSn(bbsSknSn);
			BbsSknVO item = bbsSknService.selectObj(bbsSknVO);
			
			if(item != null) {
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				
				bbsSknVO = new BbsSknVO();
				bbsSknVO.setBbsSknBestSn((item.getBbsSknDp() > 1)? item.getBbsSknBestSn() : null);
				bbsSknVO.setBbsSknDp(item.getBbsSknDp());
				List<BbsSknVO> menuList = bbsSknService.selectList(bbsSknVO);
				
				if(menuList != null && menuList.size() > 0) {
					int i = 1;
					
					for (BbsSknVO menu : menuList) {
						if(menu.getBbsSknSn() == item.getBbsSknSn()) {
							continue;
						}
						
						bbsSknVO = new BbsSknVO();
						bbsSknVO.setBbsSknSn(item.getBbsSknSn());
						bbsSknVO.setBbsSknOrdr(i);
						if(menu.getBbsSknDp() == 1) {
							bbsSknVO.setBbsSknUpperSn(null);
						}
						
						bbsSknService.updateInfo(request, bfData, bbsSknVO, admSession.getMngrId());
						
						i++;
					}
				}
				
				BbsSknVO tMenu = new BbsSknVO();
				
				if(bbsSknUpperSn != null) {
					// 상위 일련번호가 있으면 상위 정보로 이동
					bbsSknVO = new BbsSknVO();
					bbsSknVO.setBbsSknSn(bbsSknUpperSn);
					tMenu = bbsSknService.selectObj(bbsSknVO);
					
					bbsSknVO = new BbsSknVO();
					bbsSknVO.setBbsSknUpperSn(bbsSknUpperSn);
				} else {
					bbsSknVO = new BbsSknVO();
					bbsSknVO.setBbsSknDp(1);
				}
				
				menuList = bbsSknService.selectList(bbsSknVO);
				
				if(menuList != null && menuList.size() > 0) {
					int i = 1;
					
					for (BbsSknVO menu : menuList) {
						if(menu.getBbsSknSn() == item.getBbsSknSn() || bbsSknOrdr < 0) {
							continue;
						}
						
						if(i == (bbsSknOrdr + 1)) {
							// 변경된 순번과 i값이 같으면 건너띄기 (i값 증가)
							i++;
						}
						
						bbsSknVO = new BbsSknVO();
						bbsSknVO.setBbsSknSn(menu.getBbsSknSn());
						bbsSknVO.setBbsSknOrdr(i);
						if(menu.getBbsSknDp() == 1) {
							bbsSknVO.setBbsSknUpperSn(null);
						}
						
						bbsSknService.updateInfo(request, bfData, bbsSknVO, admSession.getMngrId());
						
						i++;
					}
				}
				
				int fKey     = (tMenu.getBbsSknSn() != null && bbsSknUpperSn != null && tMenu.getBbsSknDp() > 0)? tMenu.getBbsSknBestSn() : item.getBbsSknSn();
				Integer pKey = (tMenu.getBbsSknSn() != null && bbsSknUpperSn != null)? tMenu.getBbsSknSn() : null;
				int g        = (tMenu.getBbsSknSn() != null && bbsSknUpperSn != null && tMenu.getBbsSknDp() > 0)? tMenu.getBbsSknDp() + 1 : 1;
				
				bbsSknVO = new BbsSknVO();
				bbsSknVO.setBbsSknSn(item.getBbsSknSn());
				bbsSknVO.setBbsSknBestSn(fKey);
				bbsSknVO.setBbsSknUpperSn(pKey);
				bbsSknVO.setBbsSknOrdr(bbsSknOrdr + 1);
				bbsSknVO.setBbsSknDp(g);
				if(bbsSknVO.getBbsSknDp() == 1) {
					bbsSknVO.setBbsSknUpperSn(null);
				}
				bbsSknService.updateInfo(request, bfData, bbsSknVO, admSession.getMngrId());
				
				// 메뉴 정보 재조회
				bbsSknVO = new BbsSknVO();
				bbsSknVO.setBbsSknSn(bbsSknSn);
				item = bbsSknService.selectObj(bbsSknVO);
				
				if(item != null) {
					bbsSknVO = new BbsSknVO();
					bbsSknVO.setBbsSknUpperSn(item.getBbsSknSn());
					List<BbsSknVO> childList = bbsSknService.selectList(bbsSknVO);
					
					if(childList != null && childList.size() > 0) {
						for (BbsSknVO child : childList) {
							this.setChildOrder(request, admSession, item, child, item.getBbsSknDp() + 1);
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
	 * @작성일		: 2019. 11. 14.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 하위 게시판_스킨 순서 변경
	 */
	private String setChildOrder(HttpServletRequest request, MngrVO admSession, BbsSknVO item, BbsSknVO child, int bbsSknDp) {
		JsonVO jsonVO = new JsonVO();
		try {
			BbsSknVO bbsSknVO = new BbsSknVO();
			bbsSknVO.setBbsSknUpperSn(child.getBbsSknSn());
			List<BbsSknVO> childList = bbsSknService.selectList(bbsSknVO);
			
			if(childList != null && childList.size() > 0) {
				for (BbsSknVO menu : childList) {
					this.setChildOrder(request, admSession, item, menu, item.getBbsSknDp() + 1);
				}
			}
			
			bbsSknVO = new BbsSknVO();
			bbsSknVO.setBbsSknSn(child.getBbsSknSn());
			bbsSknVO.setBbsSknBestSn(item.getBbsSknBestSn());
			bbsSknVO.setBbsSknDp(bbsSknDp);
			
			child.setUpdtDe(null);
			String bfData = JSONArray.fromObject(child).toString();
			bbsSknService.updateInfo(request, bfData, bbsSknVO, admSession.getMngrId());
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0003"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(jsonVO).toString();
	}
	
	/**
	 * @Method Name	: aBbsSknDeletion
	 * @작성일		: 2019. 11. 14.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판_스킨 정보 삭제
	 */
	@RequestMapping(value = "/bbs/skn/deletion.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aBbsSknDeletion(
			HttpServletRequest request,
			@RequestParam(value = "bbsSknSn", required = true) int bbsSknSn,
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
			BbsSknVO bbsSknVO = new BbsSknVO();
			bbsSknVO.setBbsSknSn(bbsSknSn);
			BbsSknVO item = bbsSknService.selectObj(bbsSknVO);
			
			if(item != null) {
				bbsSknService.deleteInfo(request, item, admSession.getMngrId());
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
	 * @Method Name	: aBbsSknSetFile
	 * @작성일		: 2019. 11. 14.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판_스킨 이미지 저장
	 */
	@RequestMapping(value = "/bbs/skn/setFile.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aBbsSknSetFile(
			HttpServletRequest request,
			@RequestParam(value = "bbsSknSn", required = true) int bbsSknSn,
			@RequestParam(value = "fileNm", required = false) String fileNm,
			@RequestParam(value = "filePath", required = false) String filePath,
			MngrVO admSession,
			String key,
			Boolean refAtAjax) throws Exception {
		
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
			BbsSknVO bbsSknVO = new BbsSknVO();
			bbsSknVO.setBbsSknSn(bbsSknSn);
			BbsSknVO item = bbsSknService.selectObj(bbsSknVO);
			
			if(item != null) {
				if(StringUtils.isNotEmpty(item.getAtchmnflImage())) {
					String cPath = new FsFuncCmmHelper().getAppAbsolutePath();
					
					File file = new File(cPath + item.getFlpth() + "/" + item.getAtchmnflImage());
					file.delete();
				}
				
				bbsSknVO.setAtchmnflImage(fileNm);
				bbsSknVO.setFlpth(filePath);
				
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				bbsSknService.updateInfo(request, bfData, bbsSknVO, admSession.getMngrId());
			} else {
				jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
				return JSONArray.fromObject(jsonVO).toString();
			}
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0003"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(jsonVO).toString();
	}

}
