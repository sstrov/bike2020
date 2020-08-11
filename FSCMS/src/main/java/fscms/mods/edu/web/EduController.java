package fscms.mods.edu.web;

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
import fscms.cmm.util.UploadHelper;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.RoleVO;
import fscms.mods.atchmnfl.service.AtchmnflService;
import fscms.mods.atchmnfl.vo.AtchmnflVO;
import fscms.mods.code.util.CodeHelper;
import fscms.mods.edu.service.EduService;
import fscms.mods.edu.vo.EduSearchVO;
import fscms.mods.edu.vo.EduVO;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class EduController {

static Logger logger = LogManager.getLogger(EduController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "atchmnflService")
	private AtchmnflService atchmnflService;
	
	@Autowired
	private CodeHelper codeHelper;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	@Autowired
	private UploadHelper uploadHelper;
	
	@Resource(name = "eduService")
	private EduService eduService;
	
	/**
	 * @Method Name	: aEduList
	 * @작성일		: 2019. 10. 23.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 교육일정 목록 화면
	 */
	@RequestMapping(value = "/edu/list.do")
	public String aEduList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") EduSearchVO searchVO,
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
		return "mngr/edu/list";
	}
	
	/**
	 * @Method Name	: aEduGetList
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 교육일정 정보 목록 조회
	 */
	@RequestMapping(value = "/edu/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aEduGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") EduSearchVO searchVO,
			MngrVO admSession,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<EduVO> itemList = null;
		try {
			int tCount = eduService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			itemList = eduService.selectPageList(searchVO);
			
			if(itemList != null && itemList.size() > 0) {
				itemList.get(0).settCount(tCount);
			}
		} catch (Exception e) {
			System.out.println("eeee : "+e);
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(itemList).toString();
	}
	
	
	/**
	 * @Method Name	: aEduForm
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자 등록/수정 화면
	 */
	@RequestMapping(value = "/edu/form.do")
	public String aEduForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") EduSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		EduVO item = new EduVO();
		try {
			if(searchVO.getEduSn() != null && searchVO.getEduSn() > 0) {
				// 일련번호가 있으면 수정
				EduVO eduVO = new EduVO();
				eduVO.setEduSn(searchVO.getEduSn());
				item = eduService.selectObj(eduVO);
				
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

		model.addAttribute("role",         role);
		model.addAttribute("item",         item);
		model.addAttribute("admURI",       admURI);
		return "mngr/edu/form";
	}
	
	/**
	 * @Method Name	: aEduInsert
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 교육일정 정보 저장
	 */
	@RequestMapping(value = "/edu/insert.do", method = RequestMethod.POST)
	public String aEduInsert(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") EduVO vo,
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
			vo.setHostOrdr(rtOrdr(vo.getEduHost()));
			eduService.insertInfo(request, vo, admSession.getMngrId());
			
		} catch (Exception e) {
			logger.error("교육일정 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0001");
		}
		
		model.addAttribute("url", "/" + admURI + "/edu/list.do?key=" + key);
		model.addAttribute("msg", "저장 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aEduUpdate
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 교육일정 정보 수정
	 */
	@RequestMapping(value = "/edu/update.do", method = RequestMethod.POST)
	public String aEduUpdate(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") EduSearchVO searchVO,
			@ModelAttribute("vo") EduVO vo,
			@RequestParam(value = "eduSn", required = true) int eduSn,
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
			EduVO eduVO = new EduVO();
			eduVO.setEduSn(eduSn);
			EduVO item = eduService.selectObj(eduVO);
			
			if(item != null) {
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				vo.setHostOrdr(rtOrdr(vo.getEduHost()));
				eduService.updateInfo(request, bfData, vo, admSession.getMngrId());
				
			}
		} catch (Exception e) {
			logger.error("교육일정 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/edu/form.do?key=" + key + "&eduSn=" + eduSn);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aEduDeleteForList
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자 선택정보 삭제
	 */
	@RequestMapping(value = "/edu/deleteForList.do", method = RequestMethod.POST)
	public String aEduDeleteForList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") EduSearchVO searchVO,
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
					
					EduVO eduVO = new EduVO();
					eduVO.setEduSn(Integer.parseInt(itemKey));
					EduVO item = eduService.selectObj(eduVO);
					
					if(item != null) {
						item.setUpdtDe(null);
						String bfData = JSONArray.fromObject(item).toString();
						eduService.deleteInfo(request, bfData, item, admSession.getMngrId());
					}
				}
			} catch (Exception e) {
				logger.error("교육일정 정보 삭제 오류 : " + e.getMessage());
				throw new WrongApproachException("C0004");
			}
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/edu/list.do?key=" + key);
		model.addAttribute("msg",    "선택한 정보를 삭제 하였습니다.");
		return "success";
	}
	
	private Integer rtOrdr(String host) {
		Integer rt;
		if(host.equals("서울")) {
			rt = 1;
		}else if(host.equals("부산")){
			rt = 2;
		}else if(host.equals("대구")){
			rt = 3;
		}else if(host.equals("인천")){
			rt = 4;
		}else if(host.equals("광주")){
			rt = 5;
		}else if(host.equals("대전")){
			rt = 6;
		}else if(host.equals("울산")){
			rt = 7;
		}else if(host.equals("세종")){
			rt = 8;
		}else if(host.equals("경기")){
			rt = 9;
		}else if(host.equals("강원")){
			rt = 10;
		}else if(host.equals("충북")){
			rt = 11;
		}else if(host.equals("충남")){
			rt = 12;
		}else if(host.equals("전북")){
			rt = 13;
		}else if(host.equals("전남")){
			rt = 14;
		}else if(host.equals("경북")){
			rt = 15;
		}else if(host.equals("경남")){
			rt = 16;
		}else if(host.equals("제주")){
			rt = 17;
		}else if(host.equals("도로교통공단")){
			rt = 18;
		}else {
			rt = 999;
		}
		return rt;
	}
}
