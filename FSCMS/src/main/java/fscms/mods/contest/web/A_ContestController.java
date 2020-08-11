package fscms.mods.contest.web;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.util.FsFuncCmmHelper;
import fscms.cmm.util.UploadHelper;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.RoleVO;
import fscms.mods.atchmnfl.service.AtchmnflService;
import fscms.mods.code.util.CodeHelper;
import fscms.mods.contest.service.ContestEstbsService;
import fscms.mods.contest.service.ContestService;
import fscms.mods.contest.vo.ContestEstbsVO;
import fscms.mods.contest.vo.ContestSearchVO;
import fscms.mods.contest.vo.ContestVO;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrLoginHistVO;
import fscms.mods.mngr.vo.MngrVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_ContestController {

	static Logger logger = LogManager.getLogger(A_ContestController.class);
	
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
	
	@Resource(name = "contestService")
	private ContestService contestService;
	
	@Resource(name = "contestEstbsService")
	private ContestEstbsService contestEstbsService;
	
	@RequestMapping(value = "/contest/list.do")
	public String contestMngList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ContestSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		JsonVO jsonVO = new JsonVO();
		ContestEstbsVO item = new ContestEstbsVO();
		ContestEstbsVO vo = new ContestEstbsVO();
		if( searchVO.getContestEstbsSn() != null ) {
			try {
				vo.setContestEstbsSn(searchVO.getContestEstbsSn());
				item = contestEstbsService.selectObj(vo);
			} catch (Exception e) {
				jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
				return JSONArray.fromObject(jsonVO).toString();
			}
		}
		
		model.addAttribute("toDate", new FsFuncCmmHelper().getToDate("yyyyMMddHHmmss", null));
		model.addAttribute("item",   item);
		model.addAttribute("role",   role);
		model.addAttribute("admURI", admURI);
		return "mngr/contest/list";
	}
	
	/**
	 * @Method Name	: aUserLoginHistGetList
	 * @작성일		: 2020. 06. 25.
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: 관리자_로그인_이력 목록 조회
	 */
	@RequestMapping(value = "/contest/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String contestGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ContestSearchVO searchVO,
			MngrVO admSession,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<ContestVO> itemList = null;
		
		try {
			int tCount = contestService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			itemList = contestService.selectPageList(searchVO);
			
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
	 * @Method Name	: aContestForm
	 * @작성일		: 2020. 07. 13.
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: 공모전_설정 등록/수정 화면
	 */
	@RequestMapping(value = "/contest/form.do")
	public String aContestForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ContestSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		ContestEstbsVO preItem = new ContestEstbsVO();
		ContestVO item = new ContestVO();
		try {
			if(searchVO.getContestEstbsSn() != null && searchVO.getContestEstbsSn() > 0) {
				ContestEstbsVO contestEstbsVO = new ContestEstbsVO();
				contestEstbsVO.setContestEstbsSn(searchVO.getContestEstbsSn());
				preItem = contestEstbsService.selectObj(contestEstbsVO);
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
				Date td = new Date();
				Date bd = format.parse(preItem.getContestBgnde());
				Date ed = format.parse(preItem.getContestEndde());
				
				//
				if(td.compareTo(bd) < 0) {
					model.addAttribute("url", "/" + admURI + "/contest/list.do?key=" + key + "&contestEstbsSn=" + searchVO.getContestEstbsSn());
					model.addAttribute("msg", "공모전 시작 전입니다.");
					return "success";
				} else if(td.compareTo(ed) > 0) {
					model.addAttribute("url", "/" + admURI + "/contest/list.do?key=" + key + "&contestEstbsSn=" + searchVO.getContestEstbsSn());
					model.addAttribute("msg", "공모전이 마감되었습니다.");
					return "success";
				}
				
				if(preItem != null) {
					if(searchVO.getContestSn() != null && searchVO.getContestSn() > 0) {
						ContestVO contestVO = new ContestVO();
						contestVO.setContestSn(searchVO.getContestSn());
						item = contestService.selectObj(contestVO);
					} else {
						// 등록 일때는 등록 권한 필요
						if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
							logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
							throw new WrongApproachException("C0041");
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("exception :"+e);
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("preItem",  preItem);
		model.addAttribute("role",     role);
		model.addAttribute("item",     item);
		model.addAttribute("admURI",   admURI);
		return "mngr/contest/form";
	}
	
	/**
	 * @Method Name	: aContestInsert
	 * @작성일		: 2020. 07. 13.
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: 배너_설정 정보 저장
	 */
	@RequestMapping(value = "/contest/insert.do", method = RequestMethod.POST)
	public String aContestInsert(
			MultipartHttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") ContestVO vo,
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
			vo.setRegistNm(admSession.getMngrId());
			contestService.insertInfo(request, vo, admSession.getMngrId());
		} catch (Exception e) {
			logger.error("공모전_저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0001");
		}
		
		model.addAttribute("url", "/" + admURI + "/contest/list.do?key=" + key + "&contestEstbsSn=" + vo.getContestEstbsSn());
		model.addAttribute("msg", "저장 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aContestUpdate
	 * @작성일		: 2020. 07. 13.
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: 공모전_설정 정보 수정
	 */
	@RequestMapping(value = "/contest/update.do", method = RequestMethod.POST)
	public String aContestUpdate(
			MultipartHttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ContestSearchVO searchVO,
			@ModelAttribute("vo") ContestVO vo,
			@RequestParam(value = "contestSn", required = true) int contestSn,
			@RequestParam(value = "contestEstbsSn", required = true) int contestEstbsSn,
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
			ContestVO contestVO = new ContestVO();
			contestVO.setContestSn(contestSn);
			ContestVO item = contestService.selectObj(contestVO);
			
			if(item != null) {
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				vo.setUpdtNm(admSession.getMngrId());
				contestService.updateInfo(request, bfData, vo, admSession.getMngrId());
			}
		} catch (Exception e) {
			logger.error("공모전_수정 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/contest/form.do?key=" + key + "&contestSn=" + contestSn + "&contestEstbsSn=" + contestEstbsSn);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aContestDeleteForList
	 * @작성일		: 2020. 07. 13.
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: 공모전_설정 선택정보 삭제
	 */
	@RequestMapping(value = "/contest/deleteForList.do", method = RequestMethod.POST)
	public String aContestDeleteForList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ContestSearchVO searchVO,
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
					
					ContestVO contestVO = new ContestVO();
					contestVO.setContestSn(Integer.parseInt(itemKey));
					ContestVO item = contestService.selectObj(contestVO);
					
					if(item != null) {
						item.setUpdtDe(null);
						String bfData = JSONArray.fromObject(item).toString();
						contestService.deleteInfo(request, bfData, item, admSession.getMngrId());
					}
				}
			} catch (Exception e) {
				logger.error("공모전_정보 삭제 오류 : " + e.getMessage());
				throw new WrongApproachException("C0004");
			}
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/contest/list.do?key=" + key + "&contestEstbsSn=" + searchVO.getContestEstbsSn());
		model.addAttribute("msg",    "선택한 정보를 삭제 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aContestUpdate
	 * @작성일		: 2020. 07. 13.
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: 공모전_설정 정보 수정
	 */
	@RequestMapping(value = "/contest/cancel.do", method = RequestMethod.POST)
	public String aContestCancel(
			MultipartHttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ContestSearchVO searchVO,
			@ModelAttribute("vo") ContestVO vo,
			@RequestParam(value = "contestSn", required = true) int contestSn,
			@RequestParam(value = "contestEstbsSn", required = true) int contestEstbsSn,
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
			ContestVO contestVO = new ContestVO();
			contestVO.setContestSn(contestSn);
			ContestVO item = contestService.selectObj(contestVO);
			
			if(item != null) {
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				vo.setUpdtNm(admSession.getMngrId());
				vo.setUseAt("N");
				contestService.updateInfo(request, bfData, vo, admSession.getMngrId());
			}
		} catch (Exception e) {
			logger.error("공모전_설정 정보 수정 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/contest/list.do?key=" + key + "&contestEstbsSn=" + contestEstbsSn);
		model.addAttribute("msg",    "취소 되었습니다.");
		return "success";
	}
}
