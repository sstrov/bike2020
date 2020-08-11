package fscms.mods.code.web;

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
import fscms.mods.code.service.CodeClService;
import fscms.mods.code.vo.CodeClSearchVO;
import fscms.mods.code.vo.CodeClVO;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_CodeClController {
	
	static Logger logger = LogManager.getLogger(A_CodeClController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "codeClService")
	private CodeClService codeClService;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	/**
	 * @Method Name	: aCodeClList
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 코드_분류 목록 화면
	 */
	@RequestMapping(value = "/code/cl/list.do")
	public String aCodeClList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") CodeClSearchVO searchVO,
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
		return "mngr/code/cl_list";
	}
	
	/**
	 * @Method Name	: aCodeClGetList
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 코드_분류 목록 조회
	 */
	@RequestMapping(value = "/code/cl/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aCodeClGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") CodeClSearchVO searchVO,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<CodeClVO> itemList = null;
		try {
			// 사용여부
			if(StringUtils.isNotEmpty(searchVO.getSc_useAt())) {
				String[] arr = searchVO.getSc_useAt().split(",");
				searchVO.setSc_useAtArr(arr);
			}
			
			int tCount = codeClService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			itemList = codeClService.selectPageList(searchVO);
			
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
	 * @Method Name	: aCodeClForm
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 코드_분류 등록/수정 화면
	 */
	@RequestMapping(value = "/code/cl/form.do")
	public String aCodeClForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") CodeClSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		CodeClVO item = new CodeClVO();
		try {
			if(StringUtils.isNotEmpty(searchVO.getCodeClId())) {
				// 일련번호가 있으면 수정
				CodeClVO codeClVO = new CodeClVO();
				codeClVO.setCodeClId(searchVO.getCodeClId());
				item = codeClService.selectObj(codeClVO);
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
		
		model.addAttribute("role",   role);
		model.addAttribute("item",   item);
		model.addAttribute("admURI", admURI);
		return "mngr/code/cl_form";
	}
	
	/**
	 * @Method Name	: aCodeClIsexistId
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 코드_분류 명 중복 체크
	 */
	@RequestMapping(value = "/code/cl/isExistId.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aCodeClIsexistId(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "codeClId", required = true) String codeClId,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		try {
			PropConnHelper propConnHelper = new PropConnHelper();
			
			CodeClVO codeClVO = new CodeClVO();
			codeClVO.setCodeClId(codeClId);
			CodeClVO item = codeClService.selectObj(codeClVO);
			
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
	 * @Method Name	: aCodeClInsert
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 코드_분류 정보 저장
	 */
	@RequestMapping(value = "/code/cl/insert.do", method = RequestMethod.POST)
	public String aCodeClInsert(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") CodeClVO vo,
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
			codeClService.insertInfo(request, vo, admSession.getMngrId());
		} catch (Exception e) {
			logger.error("코드_분류 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0001");
		}
		
		model.addAttribute("url", "/" + admURI + "/code/cl/list.do?key=" + key);
		model.addAttribute("msg", "저장 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aCodeClUpdate
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 코드_분류 정보 수정
	 */
	@RequestMapping(value = "/code/cl/update.do", method = RequestMethod.POST)
	public String aCodeClUpdate(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") CodeClSearchVO searchVO,
			@ModelAttribute("vo") CodeClVO vo,
			@RequestParam(value = "codeClId", required = true) String codeClId,
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
			CodeClVO codeClVO = new CodeClVO();
			codeClVO.setCodeClId(codeClId);
			CodeClVO item = codeClService.selectObj(codeClVO);
			
			if(item != null) {
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				codeClService.updateInfo(request, bfData, vo, admSession.getMngrId());
			}
		} catch (Exception e) {
			logger.error("코드_분류 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/code/cl/form.do?key=" + key + "&codeClId=" + codeClId);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aCodeClDeleteForList
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 코드_분류 선택정보 삭제
	 */
	@RequestMapping(value = "/code/cl/deleteForList.do", method = RequestMethod.POST)
	public String aCodeClDeleteForList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") CodeClSearchVO searchVO,
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
					
					CodeClVO codeClVO = new CodeClVO();
					codeClVO.setCodeClId(itemKey);
					CodeClVO item = codeClService.selectObj(codeClVO);
					
					if(item != null) {
						codeClVO.setUseAt("N");
						
						item.setUpdtDe(null);
						String bfData = JSONArray.fromObject(item).toString();
						codeClService.updateInfo(request, bfData, codeClVO, admSession.getMngrId());
					}
				}
			} catch (Exception e) {
				logger.error("관리자 정보 삭제 오류 : " + e.getMessage());
				throw new WrongApproachException("C0004");
			}
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/code/cl/list.do?key=" + key);
		model.addAttribute("msg",    "선택한 정보를 사용안함 처리 하였습니다.");
		return "success";
	}

}
