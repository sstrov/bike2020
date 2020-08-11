package fscms.mods.mngr.web;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.util.EgovCryptoARIAHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.util.CryptoHelper;
import fscms.cmm.util.PropConnHelper;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.RoleVO;
import fscms.mods.indvdlinfo.util.IndvdlinfoAccesHistHelper;
import fscms.mods.mngr.service.MngrRoleService;
import fscms.mods.mngr.service.MngrService;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrSearchVO;
import fscms.mods.mngr.vo.MngrVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_MngrController {
	
	static Logger logger = LogManager.getLogger(A_MngrController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "mngrService")
	private MngrService mngrService;
	
	@Resource(name = "mngrRoleService")
	private MngrRoleService mngrRoleService;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	@Resource(name = "egovCryptoARIAHelper")
	private EgovCryptoARIAHelper egovCryptoARIAHelper;
	
	@Resource(name = "indvdlinfoAccesHistHelper")
	private IndvdlinfoAccesHistHelper indvdlinfoAccesHistHelper;
	
	/**
	 * @Method Name	: aMngrPasswordForm
	 * @작성일		: 2019. 10. 11.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자 비밀번호 변경 화면
	 */
	@RequestMapping(value = "/mngr/password/form.do")
	public String aMngrPasswordForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			MngrVO admSession,
			ModelMap model) {
		
		model.addAttribute("admURI", admURI);
		return "mngr/mngr/password_form";
	}
	
	/**
	 * @Method Name	: aMngrPasswordSave
	 * @작성일		: 2019. 10. 11.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자 비밀번호 변경 처리
	 */
	@RequestMapping(value = "/mngr/password/save.do", method = RequestMethod.POST)
	public String aMngrPasswordSave(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "old_mngrPw", required = true) String old_mngrPw,
			@RequestParam(value = "mngrPw", required = true) String mngrPw,
			MngrVO admSession,
			Boolean refAt,
			ModelMap model) {
		
		try {
			MngrVO mngrVO = new MngrVO();
			mngrVO.setMngrId(egovCryptoARIAHelper.encrypt(admSession.getMngrId()));
			MngrVO item = mngrService.selectObj(mngrVO);
			
			if(item == null) {
				model.addAttribute("url", "/" + admURI + "/mngr/password/form.do");
				model.addAttribute("msg", "잘못된 접근 입니다.");
				return "success";
			}
			
			String encPw = CryptoHelper.encrypt(old_mngrPw, item.getMngrSalt());
			if(!StringUtils.equals(encPw, item.getMngrPw())) {
				model.addAttribute("url", "/" + admURI + "/mngr/password/form.do");
				model.addAttribute("msg", "현재 비밀번호 정보가 다릅니다.");
				return "success";
			}
			
			mngrVO.setMngrSn(item.getMngrSn());
			mngrVO.setMngrPw(mngrPw);
			mngrVO.setMngrSalt(item.getMngrSalt());
			
			item.setUpdtDe(null);
			String bfData = JSONArray.fromObject(item).toString();
			mngrService.updateInfo(request, bfData, mngrVO, admSession.getMngrId());
		} catch (Exception e) {
			logger.error("관리자 비밀번호 변경 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("url", "/" + admURI + "/mngr/password/form.do");
		model.addAttribute("msg", "변경 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aMngrList
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자 목록 화면
	 */
	@RequestMapping(value = "/mngr/list.do")
	public String aMngrList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") MngrSearchVO searchVO,
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
		return "mngr/mngr/list";
	}
	
	/**
	 * @Method Name	: aMngrGetList
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자 정보 목록 조회
	 */
	@RequestMapping(value = "/mngr/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aMngrGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") MngrSearchVO searchVO,
			MngrVO admSession,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<MngrVO> itemList = null;
		if(StringUtils.isNotEmpty(searchVO.getSw())) {
			try {
				searchVO.setSw_entry(egovCryptoARIAHelper.encrypt(searchVO.getSw()));
			} catch (UnsupportedEncodingException e) {
				searchVO.setSw_entry(searchVO.getSw());
			}
		}
		try {
			int tCount = mngrService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			itemList = mngrService.selectPageList(searchVO);
			
			if(itemList != null && itemList.size() > 0) {
				itemList.get(0).settCount(tCount);
				
				String cn = "";
				for (MngrVO item : itemList) {
					cn += ((StringUtils.isNotEmpty(cn))? ", " : "") + item.getMngrId();
				}
				
				if(StringUtils.isNotEmpty(cn)) {
					// 개인정보_접근_이력 저장
					String indvdlinfoAccesHistCn = "관리자 (" + cn + ") 정보 목록을 조회 하였습니다.";
					indvdlinfoAccesHistHelper.setInsert(request, null, admSession.getMngrId(), indvdlinfoAccesHistCn, null);
				}
			}
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(itemList).toString();
	}
	
	/**
	 * @Method Name	: aMngrForm
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자 등록/수정 화면
	 */
	@RequestMapping(value = "/mngr/form.do")
	public String aMngrForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") MngrSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		MngrVO item = new MngrVO();
		try {
			if(searchVO.getMngrSn() != null && searchVO.getMngrSn() > 0) {
				// 일련번호가 있으면 수정
				MngrVO mngrVO = new MngrVO();
				mngrVO.setMngrSn(searchVO.getMngrSn());
				item = mngrService.selectObj(mngrVO);
				
				// 개인정보_접근_이력 저장
				String indvdlinfoAccesHistCn = "관리자 (" + item.getMngrId() + " | " + item.getMngrNm() + ") 정보를 상세조회 하였습니다.";
				indvdlinfoAccesHistHelper.setInsert(request, null, admSession.getMngrId(), indvdlinfoAccesHistCn, null);
			} else {
				// 등록 일때는 등록 권한 필요
				if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
					logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
					throw new WrongApproachException("C0041");
				}
			}
			
			model.addAttribute("roleList", mngrRoleService.selectList(null));
		} catch (Exception e) {
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("role",   role);
		model.addAttribute("item",   item);
		model.addAttribute("admURI", admURI);
		try {
			model.addAttribute("defPw",  new PropConnHelper().getConn("fs_config", "Fs_config.defaultPassword"));
		} catch (IOException e) {
			logger.error("비밀번호 셋팅 설정파일 없음 : " + e.getMessage());
			throw new WrongApproachException("C0021");
		}
		return "mngr/mngr/form";
	}
	
	/**
	 * @Method Name	: aMngrIsexistId
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자 명 중복 체크
	 */
	@RequestMapping(value = "/mngr/isExistId.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aMngrIsexistId(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "mngrId", required = true) String mngrId,
			@RequestParam(value = "accSn", required = false) Integer accSn,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		try {
			PropConnHelper propConnHelper = new PropConnHelper();
			
			MngrVO mngrVO = new MngrVO();
			mngrVO.setMngrId(mngrId);
			mngrVO.setAccSn(accSn);
			MngrVO item = mngrService.selectObj(mngrVO);
			
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
	 * @Method Name	: aMngrInsert
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자 정보 저장
	 */
	@RequestMapping(value = "/mngr/insert.do", method = RequestMethod.POST)
	public String aMngrInsert(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") MngrVO vo,
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
			vo.setMngrSalt(CryptoHelper.generateSalt());
			mngrService.insertInfo(request, vo, admSession.getMngrId());
		} catch (Exception e) {
			logger.error("관리자 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0001");
		}
		
		model.addAttribute("url", "/" + admURI + "/mngr/list.do?key=" + key);
		model.addAttribute("msg", "저장 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aMngrUpdate
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자 정보 수정
	 */
	@RequestMapping(value = "/mngr/update.do", method = RequestMethod.POST)
	public String aMngrUpdate(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") MngrSearchVO searchVO,
			@ModelAttribute("vo") MngrVO vo,
			@RequestParam(value = "mngrSn", required = true) int mngrSn,
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
			MngrVO mngrVO = new MngrVO();
			mngrVO.setMngrSn(mngrSn);
			MngrVO item = mngrService.selectObj(mngrVO);
			
			if(item != null) {
				vo.setMngrSalt(item.getMngrSalt());
				
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				mngrService.updateInfo(request, bfData, vo, admSession.getMngrId());
			}
		} catch (Exception e) {
			logger.error("관리자 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/mngr/form.do?key=" + key + "&mngrSn=" + mngrSn);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aMngrDeleteForList
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자 선택정보 삭제
	 */
	@RequestMapping(value = "/mngr/deleteForList.do", method = RequestMethod.POST)
	public String aMngrDeleteForList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") MngrSearchVO searchVO,
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
					
					MngrVO mngrVO = new MngrVO();
					mngrVO.setMngrSn(Integer.parseInt(itemKey));
					MngrVO item = mngrService.selectObj(mngrVO);
					
					if(item != null) {
						item.setUpdtDe(null);
						String bfData = JSONArray.fromObject(item).toString();
						mngrService.deleteInfo(request, bfData, item, admSession.getMngrId());
					}
				}
			} catch (Exception e) {
				logger.error("관리자 정보 삭제 오류 : " + e.getMessage());
				throw new WrongApproachException("C0004");
			}
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/mngr/list.do?key=" + key);
		model.addAttribute("msg",    "선택한 정보를 삭제 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aMngrPwReset
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자 비밀번호 초기화
	 */
	@RequestMapping(value = "/mngr/pwReset.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aMngrPwReset(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "mngrSn", required = true) int mngrSn,
			@RequestParam(value = "mngrId", required = true) String mngrId,
			MngrVO admSession,
			String key,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		try {
			MngrVO mngrVO = new MngrVO();
			mngrVO.setMngrSn(mngrSn);
			MngrVO item = mngrService.selectObj(mngrVO);
			
			if(item != null) {
				PropConnHelper propConnHelper = new PropConnHelper();
				
				mngrVO.setMngrSalt(item.getMngrSalt());
				mngrVO.setMngrPw(propConnHelper.getConn("fs_config", "Fs_config.defaultPassword"));
				
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				mngrService.updateInfo(request, bfData, mngrVO, admSession.getMngrId());
			}
			
			return JSONArray.fromObject(jsonVO).toString();
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0003"));
			return JSONArray.fromObject(jsonVO).toString();
		}
	}
	
	/**
	 * @Method Name	: aMngrUnlockLogin
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자 로그인 잠금 해제
	 */
	@RequestMapping(value = "/mngr/unlockLogin.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aMngrUnlockLogin(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "mngrSn", required = true) int mngrSn,
			@RequestParam(value = "mngrId", required = true) String mngrId,
			MngrVO admSession,
			String key,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		try {
			MngrVO mngrVO = new MngrVO();
			mngrVO.setMngrSn(mngrSn);
			MngrVO item = mngrService.selectObj(mngrVO);
			
			if(item != null) {
				mngrVO.setLockCo(0);
				mngrVO.setLockDe("N");
				mngrVO.setMngrSalt(item.getMngrSalt());
				
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				mngrService.updateInfo(request, bfData, mngrVO, admSession.getMngrId());
			}
			
			return JSONArray.fromObject(jsonVO).toString();
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0003"));
			return JSONArray.fromObject(jsonVO).toString();
		}
	}

}
