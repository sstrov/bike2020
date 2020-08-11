package fscms.mods.cntnts.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import fscms.cmm.util.FsFuncCmmHelper;
import fscms.cmm.util.UploadHelper;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.RoleVO;
import fscms.mods.atchmnfl.service.AtchmnflService;
import fscms.mods.atchmnfl.vo.AtchmnflVO;
import fscms.mods.cntnts.service.CntntsChghstService;
import fscms.mods.cntnts.vo.CntntsChghstSearchVO;
import fscms.mods.cntnts.vo.CntntsChghstVO;
import fscms.mods.file.util.FileHelper;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import fscms.mods.user.service.UserMenuService;
import fscms.mods.user.util.UserMenuHelper;
import fscms.mods.user.vo.UserMenuVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class CntntsController {
	
	static Logger logger = LogManager.getLogger(CntntsController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	@Resource(name = "atchmnflService")
	private AtchmnflService atchmnflService;
	
	@Resource(name = "userMenuService")
	private UserMenuService userMenuService;
	
	@Resource(name = "cntntsChghstService")
	private CntntsChghstService cntntsChghstService;
	
	@Autowired
	private UserMenuHelper userMenuHelper;
	
	@Autowired
	private UploadHelper uploadHelper;
	
	@Autowired
	private FileHelper fileHelper;
	
	@RequestMapping(value = "/cntnts/list.do")
	public String cntntsList(
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
		
		try {
			UserMenuVO userMenuVO = new UserMenuVO();
			userMenuVO.setMenuDp(1);
			userMenuVO.setUseAt("Y");
			List<UserMenuVO> mList = userMenuService.selectList(userMenuVO);
			
			List<UserMenuVO> contentList = new ArrayList<UserMenuVO>();
			model.addAttribute("contentList", this.getContentList(mList, contentList));
		} catch (Exception e) {
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("role",   role);
		model.addAttribute("admURI", admURI);
		return "mngr/cntnts/list";
	}
	
	private List<UserMenuVO> getContentList(List<UserMenuVO> itemList, List<UserMenuVO> contentList) throws Exception {
		
		if(itemList != null && itemList.size() > 0) {
			for (UserMenuVO item : itemList) {
				contentList.add(item);
				
				UserMenuVO userMenuVO = new UserMenuVO();
				userMenuVO.setMenuUpperSn(item.getMenuSn());
				userMenuVO.setUseAt("Y");
				
				List<UserMenuVO> mList = userMenuService.selectList(userMenuVO);
				contentList = this.getContentList(mList, contentList);
			}
		}
		
		return contentList;
	}
	
	@RequestMapping(value = "/cntnts/form.do")
	public String cntntsForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "menuSn", required = true) String menuSn,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			String rootPath = fsFuncCmmHelper.getAppAbsolutePath() + "/repository/www/content";
			
			// 메뉴 정보 여부 확인
			UserMenuVO userMenu = userMenuHelper.getJson(menuSn);
			String fileNm = menuSn + ".jsp";
			File jspFile = new File(rootPath + "/", fileNm);
			if(!fsFuncCmmHelper.checkFileExists(jspFile)) {
				logger.info("잘못된 경로 접근 - 컨텐츠 파일이 없습니다.");
				throw new WrongApproachException("C0021");
			}
			
			// 파일 컨텐츠 내용 가져오기
			char[] ch = new char[(int) jspFile.length()];
			BufferedReader br = new BufferedReader(new FileReader(jspFile));
			br.read(ch);
			
			StringBuilder str = new StringBuilder();
			str.append(ch);
			
			br.close();
			
			String content = str.toString();
			// 유니코드 문자열 삭제 처리 (해당 처리를하지 않으면 특수문자가 추가되어 실행에 오류를 낼 수 있다.)
			content = content.replaceAll("[^\\u0009\\u000A\\u000D\\u0020-\\uD7FF\\uE000-\\uFFFD\\u10000-\\u10FFF]+", "");
			// 페이지 상단 인코딩 설정 제거
			String patten = "<%@ page([^.]+)pageEncoding=\"UTF-8\"%>";
			Pattern pt = Pattern.compile(patten);
			Matcher mc = pt.matcher(content);
			if(mc.find()) {
				content = mc.replaceAll("");
			}
			
			// 변경이력 체크하여 새로 수정된 내용이면 변경이력에 저장
			CntntsChghstSearchVO cntntsChghstSearchVO = new CntntsChghstSearchVO();
			cntntsChghstSearchVO.setMenuSn(menuSn);
			cntntsChghstSearchVO.setRegistDe(fsFuncCmmHelper.getToDate("yyyyMMddHHmmss", jspFile.lastModified()));
			int cntntsChghstCount = cntntsChghstService.selectTCount(cntntsChghstSearchVO);
			
			if(cntntsChghstCount == 0) {
				CntntsChghstVO cntntsChghstVO = new CntntsChghstVO();
				cntntsChghstVO.setMenuSn(menuSn);
				cntntsChghstVO.setCntntsChghstCn(fsFuncCmmHelper.cleanXSS(content));
				cntntsChghstVO.setRegistId("FTP");
				cntntsChghstVO.setRegistIp("127.0.0.1");
				cntntsChghstVO.setRegistDe(cntntsChghstSearchVO.getRegistDe());
				cntntsChghstService.insertInfo(request, cntntsChghstVO, admSession.getMngrId());
			}
			
			// 콘텐츠 목록 가져오기
			UserMenuVO userMenuVO = new UserMenuVO();
			userMenuVO.setMenuDp(1);
			userMenuVO.setActvtyAt("Y");
			List<UserMenuVO> mList = userMenuService.selectList(userMenuVO);
			
			// 파일 목록 가져오기
			AtchmnflVO atchmnflVO = new AtchmnflVO();
			atchmnflVO.setAccTy("content");
			atchmnflVO.setAccSn(menuSn);
			List<AtchmnflVO> fileList = atchmnflService.selectList(atchmnflVO);
			
			@SuppressWarnings("static-access")
			Long uniqueId = uploadHelper.getUniqueID();
			request.getSession().setAttribute("file_" + uniqueId, (fileList != null && fileList.size() > 0)? fileList : null);
			
			model.addAttribute("contentList", this.getContentList(mList, new ArrayList<UserMenuVO>()));
			model.addAttribute("uniqueId",    uniqueId);
			model.addAttribute("content",     content);
			model.addAttribute("item",        userMenu);
		} catch (Exception e) {
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("role",     role);
		model.addAttribute("admURI",   admURI);
		return "mngr/cntnts/form";
	}
	
	/**
	 * @Method Name	: cntntsGetObj
	 * @작성일		: 2020. 1. 22.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 다른 콘텐츠 정보 가져오기
	 */
	@RequestMapping(value = "/cntnts/getObj.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String cntntsGetObj(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "menuSn", required = true) String menuSn,
			MngrVO admSession,
			Boolean refAtAjax) {
		
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		try {
			String rootPath = fsFuncCmmHelper.getAppAbsolutePath() + "/repository/www/content";
			
			// 메뉴 정보 여부 확인
			String fileNm = menuSn + ".jsp";
			File jspFile = new File(rootPath + "/", fileNm);
			if(!fsFuncCmmHelper.checkFileExists(jspFile)) {
				logger.info("잘못된 경로 접근 - 컨텐츠 파일이 없습니다.");
				throw new WrongApproachException("C0021");
			}
			
			// 파일 컨텐츠 내용 가져오기
			char[] ch = new char[(int) jspFile.length()];
			BufferedReader br = new BufferedReader(new FileReader(jspFile));
			br.read(ch);
			
			StringBuilder str = new StringBuilder();
			str.append(ch);
			
			br.close();
			
			String content = str.toString();
			// 유니코드 문자열 삭제 처리 (해당 처리를하지 않으면 특수문자가 추가되어 실행에 오류를 낼 수 있다.)
			content = content.replaceAll("[^\\u0009\\u000A\\u000D\\u0020-\\uD7FF\\uE000-\\uFFFD\\u10000-\\u10FFF]+", "");
			// 페이지 상단 인코딩 설정 제거
			String patten = "<%@ page([^.]+)pageEncoding=\"UTF-8\"%>";
			Pattern pt = Pattern.compile(patten);
			Matcher mc = pt.matcher(content);
			if(mc.find()) {
				content = mc.replaceAll("");
			}
			
			jsonVO.setState("200");
			jsonVO.setMsg(content);
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(jsonVO).toString();
	}
	
	/**
	 * @Method Name	: cntntsChghstGetObj
	 * @작성일		: 2020. 1. 22.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 변경내역에서 데이터 가져오기
	 */
	@RequestMapping(value = "/cntnts/chghst/getObj.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String cntntsChghstGetObj(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "cntntsChghstSn", required = true) int cntntsChghstSn,
			MngrVO admSession,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		try {
			CntntsChghstVO cntntsChghstVO = new CntntsChghstVO();
			cntntsChghstVO.setCntntsChghstSn(cntntsChghstSn);
			CntntsChghstVO item = cntntsChghstService.selectObj(cntntsChghstVO);
			
			String content = item.getCntntsChghstCn();
			// 유니코드 문자열 삭제 처리 (해당 처리를하지 않으면 특수문자가 추가되어 실행에 오류를 낼 수 있다.)
			content = content.replaceAll("[^\\u0009\\u000A\\u000D\\u0020-\\uD7FF\\uE000-\\uFFFD\\u10000-\\u10FFF]+", "");
			// 페이지 상단 인코딩 설정 제거
			String patten = "<%@ page([^.]+)pageEncoding=\"UTF-8\"%>";
			Pattern pt = Pattern.compile(patten);
			Matcher mc = pt.matcher(content);
			if(mc.find()) {
				content = mc.replaceAll("");
			}
			
			jsonVO.setState("200");
			jsonVO.setMsg(content);
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(jsonVO).toString();
	}
	
	@RequestMapping(value = "/cntnts/chghst/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String cntntsChghstGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") CntntsChghstSearchVO searchVO,
			@RequestParam(value = "menuSn", required = true) String menuSn,
			MngrVO admSession,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<CntntsChghstVO> itemList = null;
		try {
			searchVO.setMenuSn(menuSn);
			int tCount = cntntsChghstService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			itemList = cntntsChghstService.selectPageList(searchVO);
			
			if(itemList != null && itemList.size() > 0) {
				itemList.get(0).settCount(tCount);
			}
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(itemList).toString();
	}
	
	@RequestMapping(value = "/cntnts/update.do", method = RequestMethod.POST)
	public String cntntsUpdate(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") CntntsChghstVO vo,
			@RequestParam(value = "menuSn", required = true) String menuSn,
			@RequestParam(value = "uniqueId", required = true) String uniqueId,
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
			FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
			
			// 임시경로 변경
			String tmpPath  = uploadHelper.getRelativePath("content", "tmp/" + fsFuncCmmHelper.getToDate("yyyyMMdd", null));	// 업로드 임시 경로
			String realPath = uploadHelper.getRelativePath("content", null) + "/" + menuSn;										// 업로드 실제 경로
			vo.setCntntsChghstCn(vo.getCntntsChghstCn().replaceAll(tmpPath, realPath));
			
			vo.setRegistId(admSession.getMngrId());
			vo.setRegistIp(request.getRemoteAddr());
			cntntsChghstService.insertInfo(request, vo, admSession.getMngrId());
			
			// 컨텐츠 파일 등록
			String rootPath = fsFuncCmmHelper.getAppAbsolutePath() + "/repository/www/content";
			
			vo.setCntntsChghstCn("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n" + fsFuncCmmHelper.decodeHtmlSpecialChars(vo.getCntntsChghstCn()));
			
			String fileNm = menuSn + ".jsp";
			File file = new File(rootPath + "/", fileNm);
			FileWriter fw = new FileWriter(file);
			fw.write(vo.getCntntsChghstCn());
			fw.flush();
			fw.close();
			
			// 파일 저장 (마지막 파라메터정보는 썸네일 정보를 보내는 것이며, 가로x세로 형식으로 전송하며, 콤마구분으로 다중 전송이 가능)
			fileHelper.insertFile(request, "content", menuSn, uniqueId, null, "100x100", null, admSession.getMngrId());
		} catch (Exception e) {
			logger.error("정보 수정 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/cntnts/form.do?key=" + key + "&menuSn=" + menuSn);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}

}
