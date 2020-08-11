package fscms.mods.auth.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import au.com.bytecode.opencsv.CSVReader;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.util.UploadHelper;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.RoleVO;
import fscms.mods.atchmnfl.service.AtchmnflService;
import fscms.mods.code.util.CodeHelper;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import fscms.mods.auth.service.AuthUploadService;
import fscms.mods.auth.vo.AuthUploadSearchVO;
import fscms.mods.auth.vo.AuthUploadVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/{admURI}")
public class AuthUploadController {

	static Logger logger = LogManager.getLogger(AuthUploadController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "atchmnflService")
	private AtchmnflService atchmnflService;
	
	@Autowired
	private UploadHelper uploadHelper;
	
	@Autowired
	private CodeHelper codeHelper;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	@Resource(name = "authUploadService")
	private AuthUploadService authUploadService;
	
	
	/**
	 * @Method Name	: authList
	 * @작성일		: 2019. 10. 11.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: DB업로드 목록
	 */
	@RequestMapping(value = "/auth/upload/list.do")
	public String authUploadList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") AuthUploadSearchVO searchVO,
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
		return "mngr/upload/list";
	}
	
	/**
	 * @Method Name	: authUploadListGetList
	 * @작성일		: 
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: DB업로드 목록 조회
	 */
	@RequestMapping(value = "/auth/upload/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String authUploadGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			@ModelAttribute("searchVO") AuthUploadSearchVO searchVO,
			Boolean refAtAjax) {
		
		if(StringUtils.isNotEmpty(searchVO.getSw()) && StringUtils.isNotEmpty(searchVO.getSc_sub())) {
			searchVO.setSc(searchVO.getSc_sub());
		}
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<AuthUploadVO> itemList = null;
		
		try {
			int tCount = authUploadService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			itemList = authUploadService.selectPageList(searchVO);
			
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
	 * @Method Name	: authUploadListGetList
	 * @작성일		: 
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: DB업로드 목록 조회
	 */
	@RequestMapping(value = "/auth/web/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String authWebGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			@ModelAttribute("searchVO") AuthUploadSearchVO searchVO,
			Boolean refAtAjax) {
		
		if(StringUtils.isNotEmpty(searchVO.getSw()) && StringUtils.isNotEmpty(searchVO.getSc_sub())) {
			searchVO.setSc(searchVO.getSc_sub());
		}
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<AuthUploadVO> itemList = null;
		
		try {
			int tCount = authUploadService.selectTCountWeb(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			itemList = authUploadService.selectPageListWeb(searchVO);
			
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
	 * @Method Name	: authUploadExcel
	 * @작성일		: 2019. 10. 11.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: DB업로드 엑셀 업로드
	 */
	@RequestMapping(value = "/auth/upload/excel.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String authUploadExcel(
			MultipartHttpServletRequest request,
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
		
		JsonVO jsonVO = new JsonVO();
		if(request.getFile("uploadFile") != null && !request.getFile("uploadFile").isEmpty()) {
			
			MultipartFile mFile = request.getFile("uploadFile"); 
			
			try {
				
				CSVReader csv = new CSVReader(new InputStreamReader(mFile.getInputStream(), "iso-8859-1"));
				String nextLine[];
				List<AuthUploadVO> itemList = new ArrayList<AuthUploadVO>();
				int cnt = 0;
				while((nextLine = csv.readNext()) != null) {
					if(cnt > 0) {
						for(int t=0; t<nextLine.length; t++)
							System.out.println(new String(nextLine[t].getBytes("iso-8859-1"), "x-windows-949"));
						
						AuthUploadVO vo = new AuthUploadVO();
						
						vo.setUserNm(new String(nextLine[1].getBytes("iso-8859-1"), "euc-kr"));
						vo.setUserBirth(new String(nextLine[2].getBytes("iso-8859-1"), "euc-kr"));
						vo.setUserSex(new String(nextLine[3].getBytes("iso-8859-1"), "euc-kr"));
						vo.setUserTelno(new String(nextLine[4].getBytes("iso-8859-1"), "euc-kr"));
						vo.setUserZip(new String(nextLine[5].getBytes("iso-8859-1"), "euc-kr"));
						vo.setUserAdresBass(new String(nextLine[6].getBytes("iso-8859-1"), "euc-kr"));
						vo.setUserEmail(new String(nextLine[7].getBytes("iso-8859-1"), "euc-kr"));
						vo.setNoteSn(new String(nextLine[8].getBytes("iso-8859-1"), "euc-kr"));
						vo.setAuthSn(new String(nextLine[9].getBytes("iso-8859-1"), "euc-kr"));
						vo.setCourseNm(new String(nextLine[10].getBytes("iso-8859-1"), "euc-kr"));
						vo.setCourseStart(new String(nextLine[11].getBytes("iso-8859-1"), "euc-kr"));
						vo.setAuthStatus(new String(nextLine[12].getBytes("iso-8859-1"), "euc-kr"));
						vo.setPubDe(new String(nextLine[13].getBytes("iso-8859-1"), "euc-kr"));
						vo.setAuthCenter(new String(nextLine[14].getBytes("iso-8859-1"), "euc-kr"));
						
						itemList.add(vo);
					}
					cnt++;
				}
				
				authUploadService.insertExcelInfo(request, null, itemList, admSession.getMngrId());
				/*InputStreamReader reader = new InputStreamReader(mFile.getInputStream(), "iso-8859-1");
				BufferedReader br = new BufferedReader(reader);
				
				List<String[]> excelList = new ArrayList<String[]>();
				String line = ""; 
				while ((line = br.readLine()) != null) {
					String[] arr = line.split(",");
					excelList.add(arr);
				}
				br.close();
				
				List<AuthUploadVO> itemList = new ArrayList<AuthUploadVO>();
				
				for(int i=1;i<excelList.size();i++) {
					String a[] = excelList.get(i);
					//System.out.println(itemList.get(i).length);
					
					for(int t=1;t<a.length;t++) {
						//System.out.println(new String(a[t].getBytes("iso-8859-1"), "euc-kr"));
						if(a[i].indexOf("\"")==0){

						}

					}	
						AuthUploadVO vo = new AuthUploadVO();
						
						vo.setUserNm(new String(a[1].getBytes("iso-8859-1"), "euc-kr"));
						vo.setUserBirth(new String(a[2].getBytes("iso-8859-1"), "euc-kr"));
						vo.setUserSex(new String(a[3].getBytes("iso-8859-1"), "euc-kr"));
						vo.setUserTelno(new String(a[4].getBytes("iso-8859-1"), "euc-kr"));
						vo.setUserZip(new String(a[5].getBytes("iso-8859-1"), "euc-kr"));
						vo.setUserAdresBass(new String(a[6].getBytes("iso-8859-1"), "euc-kr"));
						vo.setUserEmail(new String(a[7].getBytes("iso-8859-1"), "euc-kr"));
						vo.setNoteSn(new String(a[8].getBytes("iso-8859-1"), "euc-kr"));
						vo.setAuthSn(new String(a[9].getBytes("iso-8859-1"), "euc-kr"));
						vo.setCourseNm(new String(a[10].getBytes("iso-8859-1"), "euc-kr"));
						vo.setCourseStart(new String(a[11].getBytes("iso-8859-1"), "euc-kr"));
						vo.setAuthStatus(new String(a[12].getBytes("iso-8859-1"), "euc-kr"));
						if(vo.getAuthStatus().equals("승인")) {
							vo.setAuthStatus("Y");
						}else {
							vo.setAuthStatus("N");
						}
						vo.setPubDe(new String(a[13].getBytes("iso-8859-1"), "euc-kr"));
						vo.setAuthCenter(new String(a[14].getBytes("iso-8859-1"), "euc-kr"));
						
						itemList.add(vo);
						
					//}
				}*/
				
				jsonVO.setMsg("업로드가 완료 되었습니다.");
				return JSONObject.fromObject(jsonVO).toString();
			}catch (FileNotFoundException e) {
				e.printStackTrace(); 
				jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0001"));
				return JSONObject.fromObject(jsonVO).toString();
			} catch (IOException e) {
				e.printStackTrace(); 
				jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0001"));
				return JSONObject.fromObject(jsonVO).toString();
			} catch (Exception e) {
				e.printStackTrace();
				jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0001"));
				return JSONObject.fromObject(jsonVO).toString();
			}
		}
		
		jsonVO.setMsg("파일이 없습니다.");
		return JSONObject.fromObject(jsonVO).toString();
	}
	
	/**
	 * @Method Name	: authUploadListGetNoteList
	 * @작성일		: 
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: 인증수첩 목록 조회
	 */
	@RequestMapping(value = "/auth/upload/getNoteList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String authUploadListGetNoteList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") AuthUploadSearchVO searchVO,
			Boolean refAtAjax) {
		
		if(StringUtils.isNotEmpty(searchVO.getSw()) && StringUtils.isNotEmpty(searchVO.getSc_sub())) {
			searchVO.setSc(searchVO.getSc_sub());
		}
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<AuthUploadVO> itemList = null;
		if(StringUtils.isNotEmpty(searchVO.getSw())) {
			searchVO.setNoteSn(searchVO.getSw());
		}
		
		try {
			itemList = authUploadService.selectNoteList(searchVO);
			
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(itemList).toString();
	}
	
	/**
	 * @Method Name	: authUploadUpdateAuth
	 * @작성일		: 
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: 인증수첩_인증 수정
	 */
	@RequestMapping(value = "/auth/upload/updateAuth.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String authUploadUpdateAuth(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "auSn", required = true) int auSn,
			@RequestParam(value = "cName", required = true) String cName,
			@RequestParam(value = "noteSn", required = true) String noteSn,
			@RequestParam(value = "oldData", required = true) String oldData,
			Boolean refAtAjax,
			MngrVO admSession
			) {
		
		JsonVO jsonVO = new JsonVO();
		
		AuthUploadVO vo = new AuthUploadVO();
		vo.setAuSn(auSn);
		vo.setUserNm(cName);
		vo.setNoteSn(noteSn);
		vo.setOldData(oldData);
		
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONObject.fromObject(jsonVO).toString();
		}
		try {
			List<AuthUploadVO> bData = authUploadService.getObj(vo);
			
			if(bData != null && bData.size() > 0) {
				authUploadService.updateAuth(request, bData, vo, admSession.getMngrId());
			}
			jsonVO.setMsg("수정이 완료되었습니다.");
			return JSONObject.fromObject(jsonVO).toString();
		} catch (Exception e) {
			//System.out.println("exc : " + e);
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONObject.fromObject(jsonVO).toString();
		}
	}
	
}
