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
import fscms.mods.code.service.CodeDetailService;
import fscms.mods.code.service.CodeService;
import fscms.mods.code.vo.CodeDetailVO;
import fscms.mods.code.vo.CodeSearchVO;
import fscms.mods.code.vo.CodeVO;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_CodeController {
	
	static Logger logger = LogManager.getLogger(A_CodeController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "codeService")
	private CodeService codeService;
	
	@Resource(name = "codeDetailService")
	private CodeDetailService codeDetailService;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	/**
	 * @Method Name	: aCodeList
	 * @작성일		: 2019. 10. 11.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 코드 목록 화면
	 */
	@RequestMapping(value = "/code/list.do")
	public String aCodeList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") CodeSearchVO searchVO,
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
		return "mngr/code/list";
	}
	
	/**
	 * @Method Name	: aCodeGetList
	 * @작성일		: 2019. 10. 11.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 코드 목록 조회
	 */
	@RequestMapping(value = "/code/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aCodeGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") CodeSearchVO searchVO,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<CodeVO> itemList = null;
		try {
			// 사용여부
			if(StringUtils.isNotEmpty(searchVO.getSc_useAt())) {
				String[] arr = searchVO.getSc_useAt().split(",");
				searchVO.setSc_useAtArr(arr);
			}
			
			int tCount = codeService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			itemList = codeService.selectPageList(searchVO);
			
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
	 * @Method Name	: aCodeForm
	 * @작성일		: 2019. 10. 11.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 코드 등록/수정 화면
	 */
	@RequestMapping(value = "/code/form.do")
	public String aCodeForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") CodeSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		CodeVO item = new CodeVO();
		try {
			if(StringUtils.isNotEmpty(searchVO.getCodeId())) {
				// 일련번호가 있으면 수정
				CodeVO codeVO = new CodeVO();
				codeVO.setCodeId(searchVO.getCodeId());
				item = codeService.selectObj(codeVO);
				
				// 코드_상세 정보 목록 가져오기
				CodeDetailVO codeDetailVO = new CodeDetailVO();
				codeDetailVO.setCodeId(item.getCodeId());
				codeDetailVO.setDeleteAt("N");
				model.addAttribute("detailList", codeDetailService.selectList(codeDetailVO));
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
		return "mngr/code/form";
	}
	
	/**
	 * @Method Name	: aCodeIsexistId
	 * @작성일		: 2019. 10. 11.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 코드 명 중복 체크
	 */
	@RequestMapping(value = "/code/isExistId.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aCodeIsexistId(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "codeId", required = true) String codeId,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		try {
			PropConnHelper propConnHelper = new PropConnHelper();
			
			CodeVO codeVO = new CodeVO();
			codeVO.setCodeId(codeId);
			CodeVO item = codeService.selectObj(codeVO);
			
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
	 * @Method Name	: aCodeInsert
	 * @작성일		: 2019. 10. 11.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 코드 정보 저장
	 */
	@RequestMapping(value = "/code/insert.do", method = RequestMethod.POST)
	public String aCodeInsert(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") CodeVO vo,
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
			codeService.insertInfo(request, vo, admSession.getMngrId());
		} catch (Exception e) {
			logger.error("코드_분류 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0001");
		}
		
		model.addAttribute("url", "/" + admURI + "/code/list.do?key=" + key);
		model.addAttribute("msg", "저장 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aCodeUpdate
	 * @작성일		: 2019. 10. 11.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 코드 정보 수정
	 */
	@RequestMapping(value = "/code/update.do", method = RequestMethod.POST)
	public String aCodeUpdate(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") CodeSearchVO searchVO,
			@ModelAttribute("vo") CodeVO vo,
			@RequestParam(value = "codeId", required = true) String codeId,
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
			CodeVO codeVO = new CodeVO();
			codeVO.setCodeId(codeId);
			CodeVO item = codeService.selectObj(codeVO);
			
			if(item != null) {
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				codeService.updateInfo(request, bfData, vo, admSession.getMngrId(), true);
			}
		} catch (Exception e) {
			logger.error("코드_분류 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/code/form.do?key=" + key + "&codeId=" + codeId);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aCodeDeleteForList
	 * @작성일		: 2019. 10. 11.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 코드 선택정보 삭제
	 */
	@RequestMapping(value = "/code/deleteForList.do", method = RequestMethod.POST)
	public String aCodeDeleteForList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") CodeSearchVO searchVO,
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
					
					CodeVO codeVO = new CodeVO();
					codeVO.setCodeId(itemKey);
					CodeVO item = codeService.selectObj(codeVO);
					
					if(item != null) {
						codeVO.setUseAt("N");
						
						item.setUpdtDe(null);
						String bfData = JSONArray.fromObject(item).toString();
						codeService.updateInfo(request, bfData, codeVO, admSession.getMngrId(), false);
					}
				}
			} catch (Exception e) {
				logger.error("관리자 정보 삭제 오류 : " + e.getMessage());
				throw new WrongApproachException("C0004");
			}
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/code/list.do?key=" + key);
		model.addAttribute("msg",    "선택한 정보를 사용안함 처리 하였습니다.");
		return "success";
	}

}
