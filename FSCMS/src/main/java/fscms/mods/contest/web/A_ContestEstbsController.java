package fscms.mods.contest.web;

import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
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
import fscms.cmm.util.DownloadHelper;
import fscms.cmm.util.FsFuncCmmHelper;
import fscms.cmm.util.UploadHelper;
import fscms.cmm.util.excel.ExcelUtil;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.RoleVO;
import fscms.mods.atchmnfl.service.AtchmnflService;
import fscms.mods.code.util.CodeHelper;
import fscms.mods.contest.service.ContestEstbsService;
import fscms.mods.contest.service.ContestService;
import fscms.mods.contest.vo.ContestEstbsSearchVO;
import fscms.mods.contest.vo.ContestEstbsVO;
import fscms.mods.contest.vo.ContestSearchVO;
import fscms.mods.contest.vo.ContestVO;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_ContestEstbsController {

	static Logger logger = LogManager.getLogger(A_ContestEstbsController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "atchmnflService")
	private AtchmnflService atchmnflService;
	
	@Autowired
	private DownloadHelper downloadHelper;
	
	@Autowired
	private CodeHelper codeHelper;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	@Autowired
	private UploadHelper uploadHelper;
	
	@Resource(name = "contestEstbsService")
	private ContestEstbsService contestEstbsService;
	
	@Resource(name = "contestService")
	private ContestService contestService;
	
	@RequestMapping(value = "/contest/estbs/list.do")
	public String contestList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ContestEstbsSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		model.addAttribute("toDate", new FsFuncCmmHelper().getToDate("yyyyMMddHHmm", null));
		model.addAttribute("role",   role);
		model.addAttribute("admURI", admURI);
		return "mngr/contest/estbs_list";
	}
	
	/**
	 * @Method Name	: aUserLoginHistGetList
	 * @작성일		: 2020. 06. 25.
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: 관리자_로그인_이력 목록 조회
	 */
	@RequestMapping(value = "/contest/estbs/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String contestGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ContestEstbsSearchVO searchVO,
			MngrVO admSession,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<ContestEstbsVO> itemList = null;
		try {
			int tCount = contestEstbsService.selectTCount(searchVO);
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			itemList = contestEstbsService.selectPageList(searchVO);
			
			if(itemList != null && itemList.size() > 0) {
				itemList.get(0).settCount(tCount);
			}
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			System.out.println("exception : " + e);
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(itemList).toString();
	}
	
	/**
	 * @Method Name	: aContestEstbsForm
	 * @작성일		: 2020. 07. 13.
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: 공모전_설정 등록/수정 화면
	 */
	@RequestMapping(value = "/contest/estbs/form.do")
	public String aContestEstbsForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ContestEstbsSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		ContestEstbsVO item = new ContestEstbsVO();
		try {
			if(searchVO.getContestEstbsSn() != null && searchVO.getContestEstbsSn() > 0) {
				// 일련번호가 있으면 수정
				ContestEstbsVO contestEstbsVO = new ContestEstbsVO();
				contestEstbsVO.setContestEstbsSn(searchVO.getContestEstbsSn());
				item = contestEstbsService.selectObj(contestEstbsVO);
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
		
		model.addAttribute("role",     role);
		model.addAttribute("item",     item);
		model.addAttribute("admURI",   admURI);
		return "mngr/contest/estbs_form";
	}
	
	/**
	 * @Method Name	: aContestEstbsInsert
	 * @작성일		: 2020. 07. 13.
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: 공모전_설정 정보 저장
	 */
	@RequestMapping(value = "/contest/estbs/insert.do", method = RequestMethod.POST)
	public String aContestEstbsInsert(
			MultipartHttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") ContestEstbsVO vo,
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
			contestEstbsService.insertInfo(request, vo, admSession.getMngrId());
		} catch (Exception e) {
			logger.error("공모전_설정 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0001");
		}
		
		model.addAttribute("url", "/" + admURI + "/contest/estbs/list.do?key=" + key);
		model.addAttribute("msg", "저장 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aContestEstbsUpdate
	 * @작성일		: 2020. 07. 13.
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: 공모전_설정 정보 수정
	 */
	@RequestMapping(value = "/contest/estbs/update.do", method = RequestMethod.POST)
	public String aContestEstbsUpdate(
			MultipartHttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ContestEstbsSearchVO searchVO,
			@ModelAttribute("vo") ContestEstbsVO vo,
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
			ContestEstbsVO contestEstbsVO = new ContestEstbsVO();
			contestEstbsVO.setContestEstbsSn(contestEstbsSn);
			ContestEstbsVO item = contestEstbsService.selectObj(contestEstbsVO);
			
			if(item != null) {
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				vo.setUpdtNm(admSession.getMngrId());
				contestEstbsService.updateInfo(request, bfData, vo, admSession.getMngrId());
			}
		} catch (Exception e) {
			logger.error("공모전_설정 정보 수정 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/contest/estbs/form.do?key=" + key + "&contestEstbsSn=" + contestEstbsSn);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aContestEstbsDeleteForList
	 * @작성일		: 2020. 07. 13.
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: 공모전_설정 선택정보 삭제
	 */
	@RequestMapping(value = "/contest/estbs/deleteForList.do", method = RequestMethod.POST)
	public String aContestEstbsDeleteForList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ContestEstbsSearchVO searchVO,
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
					
					ContestEstbsVO contestEstbsVO = new ContestEstbsVO();
					contestEstbsVO.setContestEstbsSn(Integer.parseInt(itemKey));
					ContestEstbsVO item = contestEstbsService.selectObj(contestEstbsVO);
					
					if(item != null) {
						item.setUpdtDe(null);
						String bfData = JSONArray.fromObject(item).toString();
						contestEstbsService.deleteInfo(request, bfData, item, admSession.getMngrId());
					}
				}
			} catch (Exception e) {
				logger.error("공모전_설정 정보 삭제 오류 : " + e.getMessage());
				throw new WrongApproachException("C0004");
			}
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/contest/estbs/list.do?key=" + key);
		model.addAttribute("msg",    "선택한 정보를 삭제 하였습니다.");
		return "success";
	}
	
	@RequestMapping(value = "/contest/estbs/excel.do")
	public void contestExcel(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ContestEstbsSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		try {
			
			
			ContestEstbsSearchVO contestEstbsVO = new ContestEstbsSearchVO();
			contestEstbsVO.setContestEstbsSn(searchVO.getContestEstbsSn());
			ContestEstbsVO estbsVO = contestEstbsService.selectObj(contestEstbsVO);
			
			ContestSearchVO vo = new ContestSearchVO();
			vo.setContestEstbsSn(searchVO.getContestEstbsSn());
			List<ContestVO> itemList = contestService.selectExcelList(vo);
			
			
			//워크북 생성
			Workbook wb = new HSSFWorkbook();
			Sheet sheet = (Sheet) wb.createSheet("신청자 목록");
			Row row = null;
			Cell cell = null;
			int rowNo = 1;
			
			// 테이블 헤더용 스타일
			CellStyle headStyle = wb.createCellStyle();
			// 가는 경계선을 가집니다.
			headStyle.setBorderTop(BorderStyle.THIN);
			headStyle.setBorderBottom(BorderStyle.THIN);
			headStyle.setBorderLeft(BorderStyle.THIN);
			headStyle.setBorderRight(BorderStyle.THIN);
			
			// 배경색은 노란색입니다.
			headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
			headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			Font font = wb.createFont();
			font.setFontHeightInPoints((short) 12);
			headStyle.setFont(font);

			// 데이터는 가운데 정렬합니다.
			headStyle.setAlignment(HorizontalAlignment.CENTER);

			// 데이터용 경계 스타일 테두리만 지정
			CellStyle bodyStyle = wb.createCellStyle();
			bodyStyle.setBorderTop(BorderStyle.THIN);
			bodyStyle.setBorderBottom(BorderStyle.THIN);
			bodyStyle.setBorderLeft(BorderStyle.THIN);
			bodyStyle.setBorderRight(BorderStyle.THIN);
			// 데이터 부분 생성
			// 위에 선택된 필드가 틀리면 헤더를 새로 생성해야함.
			
			CellStyle mainStyle = wb.createCellStyle();
			Font f = wb.createFont();
			f.setFontHeightInPoints((short) 15);
			mainStyle.setFont(f);  //사이즈
			mainStyle.setAlignment(HorizontalAlignment.CENTER);
			
			row = (Row) sheet.createRow(0); 
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,8));
			cell = row.createCell(0);
			cell.setCellStyle(mainStyle);
			cell.setCellValue(estbsVO.getContestEstbsNm());
			
			cell = row.createCell(9);
			//cell.setCellStyle(mainStyle);
			cell.setCellValue("기간 (" + estbsVO.getContestBgnde() + "~" + estbsVO.getContestEndde() + ")");
			
			
			row = (Row) sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(headStyle);
			cell.setCellValue("순번");
			cell = row.createCell(1);
			cell.setCellStyle(headStyle);
			cell.setCellValue("분류");
			cell = row.createCell(2);
			cell.setCellStyle(headStyle);
			cell.setCellValue("제목");
			cell = row.createCell(3);
			cell.setCellStyle(headStyle);
			cell.setCellValue("내용");
			cell = row.createCell(4);
			cell.setCellStyle(headStyle);
			cell.setCellValue("참가자");
			cell = row.createCell(5);
			cell.setCellStyle(headStyle);
			cell.setCellValue("참가일");
			cell = row.createCell(6);
			cell.setCellStyle(headStyle);
			cell.setCellValue("첨부파일명");
			cell = row.createCell(7);
			cell.setCellStyle(headStyle);
			cell.setCellValue("서버첨부파일명");
			cell = row.createCell(8);
			cell.setCellStyle(headStyle);
			cell.setCellValue("비고");
			
			
			if(itemList != null && itemList.size() > 0) {
				for (ContestVO item : itemList) {
					row = (Row) sheet.createRow(rowNo++);
					
					cell = row.createCell(0);
					cell.setCellStyle(bodyStyle);
					//cell.setCellValue(estbsVO.getContestEstbsNm());//공모전 이름
					cell.setCellValue(rowNo-2);//순번
					cell = row.createCell(1);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(item.getContestType());//공모전 타입
					cell = row.createCell(2);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(item.getContestNm());//참가제목
					cell = row.createCell(3);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(item.getContestCn());//내용
					cell = row.createCell(4);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(item.getRegistNm());//참가자ID
					cell = row.createCell(5);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(item.getRegistDe().replace(".0", ""));//참가일
					cell = row.createCell(6);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(item.getOriFile());//첨부파일 
					cell = row.createCell(7);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(item.getAtchmnflImage());//첨부파일_서버명
					cell = row.createCell(8);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue("");					//비고
					
				}
			}
			
			int lastCell = (row == null) ? 0 : row.getLastCellNum();
			for (int colNum = 0; colNum < lastCell; colNum++)
				wb.getSheetAt(0).autoSizeColumn(colNum);
			
			Date today = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
			String todayStr = sf.format(today);
			String fileName = "공모전 신청자 목록_" + todayStr;
			String downFilename = "";
			
			// 컨텐츠 타입과 파일명 지정
			response.setContentType("ms-vnd/excel");
			String header = request.getHeader( "User-Agent" );
			if ( header.indexOf( "MSIE" ) > -1 ) {
				downFilename = URLEncoder.encode( fileName, "UTF-8" ).replaceAll( "\\+", "%20" );
			} else if ( header.indexOf( "Trident" ) > -1 ) { 
				downFilename = URLEncoder.encode( fileName, "UTF-8" ).replaceAll( "\\+", "%20" );
			} else if ( header.indexOf( "Chrome" ) > -1) {
				StringBuffer sb = new StringBuffer();
				for ( int i = 0; i < fileName.length(); i++ ) {
					char c = fileName.charAt( i );
					if ( c > '~' ) {
						sb.append( URLEncoder.encode( "" + c, "UTF-8" ) );
					}
					else {
						sb.append( c );
					}
				}
				downFilename = sb.toString();
			} else if (header.indexOf( "Firefox" ) > -1) {
				downFilename = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
			} else if ( header.indexOf( "Opera" ) > -1 ) {
				downFilename = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
			} else if ( header.indexOf( "Safari" ) > -1 ) {
				downFilename = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
			} else {
				downFilename = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
			}
			
			response.setHeader("Content-Type", "application/vnd.ms-excel;charset=EUC-KR");
			response.setHeader("Content-Disposition", "attachment; filename=" + downFilename + ".xls");
			response.setHeader("Content-Transfer-Encoding", "binary;");
			response.setHeader("Pragma", "no-cache;");
			response.setHeader("Expires", "-1;");
			
			// 엑셀 출력
			wb.write(response.getOutputStream());
			wb.close();
			
		} catch (Exception e) {
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		
	}
}
