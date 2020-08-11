package fscms.mods.bbs.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import egovframework.com.cmm.util.EgovCryptoARIAHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.util.FsFuncCmmHelper;
import fscms.cmm.util.SessionCookieHelper;
import fscms.cmm.util.UploadHelper;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.RoleVO;
import fscms.mods.atchmnfl.service.AtchmnflService;
import fscms.mods.atchmnfl.vo.AtchmnflVO;
import fscms.mods.bbs.service.BbsCtgryService;
import fscms.mods.bbs.service.BbsCtgrySubService;
import fscms.mods.bbs.service.BbsEstbsService;
import fscms.mods.bbs.service.BbsFieldService;
import fscms.mods.bbs.service.BbsService;
import fscms.mods.bbs.vo.BbsCtgrySubVO;
import fscms.mods.bbs.vo.BbsCtgryVO;
import fscms.mods.bbs.vo.BbsEstbsSearchVO;
import fscms.mods.bbs.vo.BbsEstbsVO;
import fscms.mods.bbs.vo.BbsFieldVO;
import fscms.mods.bbs.vo.BbsSearchVO;
import fscms.mods.bbs.vo.BbsVO;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.util.MngrMenuHelper;
import fscms.mods.mngr.vo.MngrMenuVO;
import fscms.mods.mngr.vo.MngrVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_BbsController {
	
	static Logger logger = LogManager.getLogger(A_BbsController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	@Resource(name = "egovCryptoARIAHelper")
	private EgovCryptoARIAHelper egovCryptoARIAHelper;
	
	@Resource(name = "atchmnflService")
	private AtchmnflService atchmnflService;
	
	@Resource(name = "bbsService")
	private BbsService bbsService;
	
	@Resource(name = "bbsCtgryService")
	private BbsCtgryService bbsCtgryService;
	
	@Resource(name = "bbsCtgrySubService")
	private BbsCtgrySubService bbsCtgrySubService;
	
	@Resource(name = "bbsEstbsService")
	private BbsEstbsService bbsEstbsService;
	
	@Resource(name = "bbsFieldService")
	private BbsFieldService bbsFieldService;
	
	@Autowired
	private MngrMenuHelper mngrMenuHelper;
	
	@Autowired
	private UploadHelper uploadHelper;
	
	/**
	 * @Method Name	: aBbsList
	 * @작성일		: 2019. 12. 1.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판 목록 화면
	 */
	@RequestMapping(value = "/bbs/list.do")
	public String aBbsList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BbsSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		MngrMenuVO menu = mngrMenuHelper.getJson(key);
		if(menu == null || !StringUtils.equals(menu.getMenuCnncTy(), "3") || menu.getAccSn() == null) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			// 게시판_설정 정보 조회
			BbsEstbsVO bbsEstbsVO = new BbsEstbsVO();
			bbsEstbsVO.setBbsEstbsSn(menu.getAccSn());
			BbsEstbsVO bbsEstbs = bbsEstbsService.selectObj(bbsEstbsVO);
			model.addAttribute("bbsEstbs", bbsEstbs);
			
			if(bbsEstbs == null) {
				logger.error("게시판_설정 정보가 없습니다. (오류코드 : C0041)");
				throw new WrongApproachException("C0041");
			}
			
			// 게시판_필드 검색 목록 조회
			BbsFieldVO bbsFieldVO = new BbsFieldVO();
			bbsFieldVO.setBbsEstbsSn(bbsEstbs.getBbsEstbsSn());
			bbsFieldVO.setFieldSearchAt("Y");
			List<BbsFieldVO> searchList = bbsFieldService.selectList(bbsFieldVO);
			model.addAttribute("searchList", searchList);
			
			// 게시판_필드 검색 목록 조회
			BbsCtgryVO bbsCtgryVO = new BbsCtgryVO();
			bbsCtgryVO.setBbsEstbsSn(bbsEstbs.getBbsEstbsSn());
			List<BbsCtgryVO> ctgryList = bbsCtgryService.selectList(bbsCtgryVO);
			model.addAttribute("ctgryList", ctgryList);
			
			// 게시판_필드 리스트 정보 조회
			bbsFieldVO.setFieldSearchAt(null);
			bbsFieldVO.setFieldListAt("Y");
			bbsFieldVO.setOrderBy("LIST_ORDR ASC");
			List<BbsFieldVO> fieldList = bbsFieldService.selectList(bbsFieldVO);
			model.addAttribute("fieldList", fieldList);
			
			// 게시판 목록 조회
			searchVO.setBbsEstbsSn(bbsEstbs.getBbsEstbsSn());
			searchVO.setBbsSn(null);
			searchVO.setBbsUpperSn(null);
			
			if(StringUtils.isNotEmpty(searchVO.getSw())) {
				try {
					searchVO.setSw_entry(egovCryptoARIAHelper.encrypt(searchVO.getSw()));
				} catch (UnsupportedEncodingException e) {
					searchVO.setSw_entry(searchVO.getSw());
				}
			}
			List<BbsVO> notiList = null;
			if(searchVO.getPageIndex() <= 1) {
				searchVO.setNoticeAt("Y");
				notiList = bbsService.selectList(searchVO);
			}
			searchVO.setNoticeAt("N");
			int tCount = bbsService.selectTCount(searchVO);
			if(menu.getAccSn() == 17) {
				searchVO.checkSYearAndSMonth();
			}
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			if(menu.getAccSn() == 17) {
				
				paginationInfo.setPageSize(5);
				paginationInfo.setTotalRecordCount(10000);
			}
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			List<BbsVO> brdList = bbsService.selectPageList(searchVO);
			model.addAttribute("tCount",         tCount);
			model.addAttribute("notiList",       notiList);
			model.addAttribute("brdList",        brdList);
			model.addAttribute("paginationInfo", paginationInfo);
		} catch (Exception e) {
			logger.error("게시판 정보 조회 오류 (오류코드 : C0002)");
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("role",   role);
		model.addAttribute("admURI", admURI);
		if(searchVO.getBbsEstbsSn() == 17) {
			return "mngr/bbs/date";
		}else {
			return "mngr/bbs/list";
		}
		
		
	}
	
	/**
	 * @Method Name	: aBbsView
	 * @작성일		: 2019. 12. 1.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판 상세보기 화면
	 */
	@RequestMapping(value = "/bbs/view.do")
	public String aBbsView(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BbsSearchVO searchVO,
			@RequestParam(value = "bbsSn", required = true) int bbsSn,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		MngrMenuVO menu = mngrMenuHelper.getJson(key);
		if(menu == null || !StringUtils.equals(menu.getMenuCnncTy(), "3") || menu.getAccSn() == null) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		BbsVO item = new BbsVO();
		try {
			// 게시판_설정 정보 조회
			BbsEstbsVO bbsEstbsVO = new BbsEstbsVO();
			bbsEstbsVO.setBbsEstbsSn(menu.getAccSn());
			BbsEstbsVO bbsEstbs = bbsEstbsService.selectObj(bbsEstbsVO);
			model.addAttribute("bbsEstbs", bbsEstbs);
			
			if(bbsEstbs == null) {
				logger.error("게시판_설정 정보가 없습니다. (오류코드 : C0041)");
				throw new WrongApproachException("C0041");
			}
			
			// 게시판_필드 보기 정보 조회
			BbsFieldVO bbsFieldVO = new BbsFieldVO();
			bbsFieldVO.setBbsEstbsSn(bbsEstbs.getBbsEstbsSn());
			bbsFieldVO.setFieldViewAt("Y");
			bbsFieldVO.setOrderBy("VIEW_ORDR ASC");
			List<BbsFieldVO> fieldList = bbsFieldService.selectList(bbsFieldVO);
			model.addAttribute("fieldList", fieldList);
			
			BbsVO bbsVO = new BbsVO();
			bbsVO.setBbsSn(bbsSn);
			bbsVO.setBbsEstbsSn(bbsEstbs.getBbsEstbsSn());
			item = bbsService.selectObj(bbsVO);
			
			if(item == null) {
				logger.error("게시판 정보가 없습니다. (오류코드 : C0041)");
				throw new WrongApproachException("C0041");
			}
			
			// 첨부 목록 가져오기
			AtchmnflVO atchmnflVO = new AtchmnflVO();
			atchmnflVO.setAccTy("bbs");
			atchmnflVO.setAccSn("" + item.getBbsSn());
			List<AtchmnflVO> fileList = atchmnflService.selectList(atchmnflVO);
			model.addAttribute("fileList", fileList);
			
			// 조회수 증가
			boolean viewChk = false;
			if(StringUtils.equals(bbsEstbs.getRdcntDplctAt(), "N")) {
				@SuppressWarnings("static-access")
				String brdViewCntAt = new SessionCookieHelper().getCookie(request, "BRD_VIEW_" + item.getBbsSn());
				
				if(StringUtils.isNotEmpty(brdViewCntAt) && StringUtils.equals(brdViewCntAt, "Y")) {
					viewChk = true;
				}
			}
			
			if(!viewChk) {
				if(StringUtils.equals(bbsEstbs.getRdcntDplctAt(), "N")) {
					// 중복불가 일때 쿠키 저장
					Cookie viewsCookie = new Cookie("BRD_VIEW_" + item.getBbsSn(), "Y");
					viewsCookie.setMaxAge(60 * 60 * 24 * 1);	// 24시간 동안 쿠키 저장
					viewsCookie.setPath("/");
					viewsCookie.setDomain(request.getServerName());
					response.addCookie(viewsCookie);
				}
				
				item.setRdcnt(item.getRdcnt() + 1);
				
				bbsVO = new BbsVO();
				bbsVO.setBbsSn(item.getBbsSn());
				bbsVO.setRdcnt(item.getRdcnt());
				bbsService.updateInfo(request, null, bbsVO, null, null);
			}
		} catch (Exception e) {
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("role",   role);
		model.addAttribute("item",   item);
		model.addAttribute("admURI", admURI);
		return "mngr/bbs/view";
	}
	
	/**
	 * @Method Name	: aBbsForm
	 * @작성일		: 2019. 12. 1.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판 등록/수정 화면
	 */
	@RequestMapping(value = "/bbs/form.do")
	public String aBbsForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BbsSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		MngrMenuVO menu = mngrMenuHelper.getJson(key);
		if(menu == null || !StringUtils.equals(menu.getMenuCnncTy(), "3") || menu.getAccSn() == null) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		BbsVO item = new BbsVO();
		try {
			// 게시판_설정 정보 조회
			BbsEstbsVO bbsEstbsVO = new BbsEstbsVO();
			bbsEstbsVO.setBbsEstbsSn(menu.getAccSn());
			BbsEstbsVO bbsEstbs = bbsEstbsService.selectObj(bbsEstbsVO);
			model.addAttribute("bbsEstbs", bbsEstbs);
			
			if(bbsEstbs == null) {
				logger.error("게시판_설정 정보가 없습니다. (오류코드 : C0041)");
				throw new WrongApproachException("C0041");
			}
			
			@SuppressWarnings("static-access")
			Long uniqueId = uploadHelper.getUniqueID();
			model.addAttribute("uniqueId",  uniqueId);
			request.getSession().setAttribute("file_" + uniqueId, null);
			// 이중 로봇 방지
			request.getSession().setAttribute("brd_" + uniqueId, true);
			
			// 게시판_필드 보기 정보 조회
			BbsFieldVO bbsFieldVO = new BbsFieldVO();
			bbsFieldVO.setBbsEstbsSn(bbsEstbs.getBbsEstbsSn());
			bbsFieldVO.setFieldViewAt("Y");
			bbsFieldVO.setOrderBy("VIEW_ORDR ASC");
			List<BbsFieldVO> fieldList = bbsFieldService.selectList(bbsFieldVO);
			model.addAttribute("fieldList", fieldList);
			
			item.setRegisterNm(admSession.getMngrNm());
			
			if(searchVO.getBbsSn() != null && searchVO.getBbsSn() > 0) {
				// 일련번호가 있으면 수정
				BbsVO bbsVO = new BbsVO();
				bbsVO.setBbsSn(searchVO.getBbsSn());
				bbsVO.setBbsEstbsSn(bbsEstbs.getBbsEstbsSn());
				item = bbsService.selectObj(bbsVO);
				
				// 파일 목록 가져오기
				AtchmnflVO atchmnflVO = new AtchmnflVO();
				atchmnflVO.setAccTy("bbs");
				atchmnflVO.setAccSn("" + item.getBbsSn());
				List<AtchmnflVO> fileList = atchmnflService.selectList(atchmnflVO);
				
				request.getSession().setAttribute("file_" + uniqueId, (fileList != null && fileList.size() > 0)? fileList : null);
			} else if(searchVO.getBbsUpperSn() != null && searchVO.getBbsUpperSn() > 0) {
				// 답변
				BbsVO bbsVO = new BbsVO();
				bbsVO.setBbsSn(searchVO.getBbsUpperSn());
				bbsVO.setBbsEstbsSn(bbsEstbs.getBbsEstbsSn());
				BbsVO reply = bbsService.selectObj(bbsVO);
				model.addAttribute("reply", reply);
			}
			
			BbsCtgryVO bbsCtgryVO = new BbsCtgryVO();
			bbsCtgryVO.setBbsEstbsSn(bbsEstbs.getBbsEstbsSn());
			List<BbsCtgryVO> cateList = bbsCtgryService.selectList(bbsCtgryVO);
			model.addAttribute("cateList", cateList);
		} catch (Exception e) {
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("role",   role);
		model.addAttribute("item",   item);
		model.addAttribute("admURI", admURI);
		return "mngr/bbs/form";
	}
	
	/**
	 * @Method Name	: aBbsInsert
	 * @작성일		: 2019. 12. 1.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판 정보 저장
	 */
	@RequestMapping(value = "/bbs/insert.do", method = RequestMethod.POST)
	public String aBbsInsert(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") BbsVO vo,
			@RequestParam(value = "uniqueId", required = true) String uniqueId,
			MngrVO admSession,
			String key,
			Boolean refAt,
			ModelMap model) {
		
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		MngrMenuVO menu = mngrMenuHelper.getJson(key);
		if(menu == null || !StringUtils.equals(menu.getMenuCnncTy(), "3") || menu.getAccSn() == null) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			// 게시판_설정 정보 조회
			BbsEstbsVO bbsEstbsVO = new BbsEstbsVO();
			bbsEstbsVO.setBbsEstbsSn(menu.getAccSn());
			BbsEstbsVO bbsEstbs = bbsEstbsService.selectObj(bbsEstbsVO);
			model.addAttribute("bbsEstbs", bbsEstbs);
			
			if(bbsEstbs == null) {
				logger.error("게시판_설정 정보가 없습니다. (오류코드 : C0041)");
				throw new WrongApproachException("C0041");
			}
			
			Boolean brdAt = (Boolean) request.getSession().getAttribute("brd_" + uniqueId);
			if(brdAt == null || !brdAt) {
				logger.error("잘못된 접근 (오류코드 : D0002)");
				throw new WrongApproachException("D0002");
			}
			
			// 순서 가져오기
			BbsVO bbsVO = new BbsVO();
			if(vo.getBbsUpperSn() != null) {
				bbsVO.setBbsSn(vo.getBbsUpperSn());
				BbsVO bbs = bbsService.selectObj(bbsVO);
				
				if(bbs == null) {
					logger.error("상위 게시판 정보 없음 (오류코드 : C0002)");
					throw new WrongApproachException("C0002");
				}
				
				vo.setBbsBestSn(bbs.getBbsBestSn());
				vo.setBbsOrdr(bbs.getBbsOrdr() + 1);
				vo.setBbsDp(bbs.getBbsDp() + 1);
				
				// 하위 순서 변경
				bbsVO = new BbsVO();
				bbsVO.setBbsBestSn(bbs.getBbsBestSn());
				bbsVO.setBbsOrdr(vo.getBbsOrdr());
				bbsService.updateOrder(request, null, bbsVO, null);
			} else {
				vo.setBbsOrdr(1);
				vo.setBbsDp(1);
			}
			
			vo.setBbsEstbsSn(bbsEstbs.getBbsEstbsSn());
			vo.setMngrId(admSession.getMngrId());
			vo.setRegistIp(request.getRemoteAddr());
			vo.setUpdtIp(request.getRemoteAddr());
			vo.setRdcnt(0);
			vo.setCmCo(0);
			
			if(StringUtils.isEmpty(vo.getNoticeAt())) {
				vo.setNoticeAt("N");
			}
			if(StringUtils.equals(bbsEstbs.getSecretAtAlways(), "Y")) {
				// 항상 비밀글 여부
				vo.setSecretAt("Y");
			}
			if(StringUtils.isEmpty(vo.getSecretAt()) || StringUtils.equals(vo.getNoticeAt(), "Y")) {
				vo.setSecretAt("N");
			}
			
			bbsService.insertInfo(request, vo, admSession.getMngrId(), uniqueId);
			
			bbsVO = new BbsVO();
			// 임시경로 변경
			String tmpPath  = uploadHelper.getRelativePath("bbs", "tmp/" + fsFuncCmmHelper.getToDate("yyyyMMdd", null));	// 업로드 임시 경로
			String realPath = uploadHelper.getRelativePath("bbs", null) + "/" + vo.getBbsSn();								// 업로드 실제 경로
			
			if(vo.getBbsBestSn() == null) {
				bbsVO.setBbsBestSn(vo.getBbsSn());
			}
			bbsVO.setBbsCn(vo.getBbsCn().replaceAll(tmpPath, realPath));
			bbsVO.setBbsSn(vo.getBbsSn());
			bbsService.updateInfo(request, null, bbsVO, null, null);
		} catch (Exception e) {
			logger.error("게시판 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0001");
		}
		
		model.addAttribute("url", "/" + admURI + "/bbs/list.do?key=" + key);
		model.addAttribute("msg", "저장 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aBbsUpdate
	 * @작성일		: 2019. 12. 1.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판 정보 수정
	 */
	@RequestMapping(value = "/bbs/update.do", method = RequestMethod.POST)
	public String aBbsUpdate(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BbsSearchVO searchVO,
			@ModelAttribute("vo") BbsVO vo,
			@RequestParam(value = "uniqueId", required = true) String uniqueId,
			@RequestParam(value = "bbsSn", required = true) int bbsSn,
			MngrVO admSession,
			String key,
			Boolean refAt,
			ModelMap model) {
		
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		MngrMenuVO menu = mngrMenuHelper.getJson(key);
		if(menu == null || !StringUtils.equals(menu.getMenuCnncTy(), "3") || menu.getAccSn() == null) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			// 게시판_설정 정보 조회
			BbsEstbsVO bbsEstbsVO = new BbsEstbsVO();
			bbsEstbsVO.setBbsEstbsSn(menu.getAccSn());
			BbsEstbsVO bbsEstbs = bbsEstbsService.selectObj(bbsEstbsVO);
			model.addAttribute("bbsEstbs", bbsEstbs);
			
			if(bbsEstbs == null) {
				logger.error("게시판_설정 정보가 없습니다. (오류코드 : C0041)");
				throw new WrongApproachException("C0041");
			}
			
			Boolean brdAt = (Boolean) request.getSession().getAttribute("brd_" + uniqueId);
			if(brdAt == null || !brdAt) {
				logger.error("잘못된 접근 (오류코드 : D0002)");
				throw new WrongApproachException("D0002");
			}
						
			BbsVO bbsVO = new BbsVO();
			bbsVO.setBbsSn(bbsSn);
			BbsVO item = bbsService.selectObj(bbsVO);
			
			if(item != null) {
				vo.setUpdtIp(request.getRemoteAddr());
				
				if(StringUtils.isEmpty(vo.getNoticeAt())) {
					vo.setNoticeAt("N");
				}
				if(StringUtils.equals(bbsEstbs.getSecretAtAlways(), "Y")) {
					// 항상 비밀글 여부
					vo.setSecretAt("Y");
				}
				if(StringUtils.isEmpty(vo.getSecretAt()) || StringUtils.equals(vo.getNoticeAt(), "Y")) {
					vo.setSecretAt("N");
				}
				
				// 임시경로 변경
				String tmpPath  = uploadHelper.getRelativePath("bbs", "tmp/" + fsFuncCmmHelper.getToDate("yyyyMMdd", null));	// 업로드 임시 경로
				String realPath = uploadHelper.getRelativePath("bbs", null) + "/" + vo.getBbsSn();								// 업로드 실제 경로
				vo.setBbsCn(vo.getBbsCn().replaceAll(tmpPath, realPath));
				
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				bbsService.updateInfo(request, bfData, vo, admSession.getMngrId(), uniqueId);
			}
		} catch (Exception e) {
			logger.error("게시판 정보 수정 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/bbs/form.do?key=" + key + "&bbsSn=" + bbsSn);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aBbsDelete
	 * @작성일		: 2019. 12. 1.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판 정보 삭제
	 */
	@RequestMapping(value = "/bbs/delete.do", method = RequestMethod.POST)
	public String aBbsDelete(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BbsSearchVO searchVO,
			@ModelAttribute("vo") BbsVO vo,
			@RequestParam(value = "bbsSn", required = true) int bbsSn,
			MngrVO admSession,
			String key,
			Boolean refAt,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		MngrMenuVO menu = mngrMenuHelper.getJson(key);
		if(menu == null || !StringUtils.equals(menu.getMenuCnncTy(), "3") || menu.getAccSn() == null) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			// 게시판_설정 정보 조회
			BbsEstbsVO bbsEstbsVO = new BbsEstbsVO();
			bbsEstbsVO.setBbsEstbsSn(menu.getAccSn());
			BbsEstbsVO bbsEstbs = bbsEstbsService.selectObj(bbsEstbsVO);
			model.addAttribute("bbsEstbs", bbsEstbs);
			
			if(bbsEstbs == null) {
				logger.error("게시판_설정 정보가 없습니다. (오류코드 : C0041)");
				throw new WrongApproachException("C0041");
			}
			
			BbsVO bbsVO = new BbsVO();
			bbsVO.setBbsSn(bbsSn);
			BbsVO item = bbsService.selectObj(bbsVO);
			
			if(item != null) {
				bbsVO.setDeleteAt("Y");
				
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				bbsService.updateInfo(request, bfData, bbsVO, admSession.getMngrId(), null);
			}
		} catch (Exception e) {
			logger.error("게시판 정보 수정 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url", "/" + admURI + "/bbs/view.do?key=" + key + "&bbsSn=" + bbsSn);
		model.addAttribute("msg", "삭제 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aBbsUse
	 * @작성일		: 2019. 12. 1.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판 정보 복원
	 */
	@RequestMapping(value = "/bbs/use.do", method = RequestMethod.POST)
	public String aBbsUse(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BbsSearchVO searchVO,
			@ModelAttribute("vo") BbsVO vo,
			@RequestParam(value = "bbsSn", required = true) int bbsSn,
			MngrVO admSession,
			String key,
			Boolean refAt,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		MngrMenuVO menu = mngrMenuHelper.getJson(key);
		if(menu == null || !StringUtils.equals(menu.getMenuCnncTy(), "3") || menu.getAccSn() == null) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			// 게시판_설정 정보 조회
			BbsEstbsVO bbsEstbsVO = new BbsEstbsVO();
			bbsEstbsVO.setBbsEstbsSn(menu.getAccSn());
			BbsEstbsVO bbsEstbs = bbsEstbsService.selectObj(bbsEstbsVO);
			model.addAttribute("bbsEstbs", bbsEstbs);
			
			if(bbsEstbs == null) {
				logger.error("게시판_설정 정보가 없습니다. (오류코드 : C0041)");
				throw new WrongApproachException("C0041");
			}
			
			BbsVO bbsVO = new BbsVO();
			bbsVO.setBbsSn(bbsSn);
			BbsVO item = bbsService.selectObj(bbsVO);
			
			if(item != null) {
				bbsVO.setDeleteAt("N");
				
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				bbsService.updateInfo(request, bfData, bbsVO, admSession.getMngrId(), null);
			}
		} catch (Exception e) {
			logger.error("게시판 정보 수정 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url", "/" + admURI + "/bbs/view.do?key=" + key + "&bbsSn=" + bbsSn);
		model.addAttribute("msg", "복원 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aBbsDeleteAll
	 * @작성일		: 2019. 12. 1.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판 완전 삭제
	 */
	@RequestMapping(value = "/bbs/deleteAll.do", method = RequestMethod.POST)
	public String aBbsDeleteAll(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BbsSearchVO searchVO,
			@ModelAttribute("vo") BbsVO vo,
			@RequestParam(value = "bbsSn", required = true) int bbsSn,
			MngrVO admSession,
			String key,
			Boolean refAt,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		MngrMenuVO menu = mngrMenuHelper.getJson(key);
		if(menu == null || !StringUtils.equals(menu.getMenuCnncTy(), "3") || menu.getAccSn() == null) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			// 게시판_설정 정보 조회
			BbsEstbsVO bbsEstbsVO = new BbsEstbsVO();
			bbsEstbsVO.setBbsEstbsSn(menu.getAccSn());
			BbsEstbsVO bbsEstbs = bbsEstbsService.selectObj(bbsEstbsVO);
			model.addAttribute("bbsEstbs", bbsEstbs);
			
			if(bbsEstbs == null) {
				logger.error("게시판_설정 정보가 없습니다. (오류코드 : C0041)");
				throw new WrongApproachException("C0041");
			}
			
			BbsVO bbsVO = new BbsVO();
			bbsVO.setBbsSn(bbsSn);
			BbsVO item = bbsService.selectObj(bbsVO);
			
			if(item != null) {
				bbsService.deleteInfo(request, item, admSession.getMngrId());
			}
		} catch (Exception e) {
			logger.error("게시판 정보 삭제 오류 : " + e.getMessage());
			throw new WrongApproachException("C0004");
		}
		
		model.addAttribute("url", "/" + admURI + "/bbs/list.do?key=" + key);
		model.addAttribute("msg", "정보를 완전삭제 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aBbsDeleteForList
	 * @작성일		: 2019. 12. 1.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판 선택목록 삭제
	 */
	@RequestMapping(value = "/bbs/deleteForList.do", method = RequestMethod.POST)
	public String aBbsDeleteForList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BbsSearchVO searchVO,
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
		
		MngrMenuVO menu = mngrMenuHelper.getJson(key);
		if(menu == null || !StringUtils.equals(menu.getMenuCnncTy(), "3") || menu.getAccSn() == null) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		if(chkKey != null && chkKey.length > 0) {
			try {
				// 게시판_설정 정보 조회
				BbsEstbsVO bbsEstbsVO = new BbsEstbsVO();
				bbsEstbsVO.setBbsEstbsSn(menu.getAccSn());
				BbsEstbsVO bbsEstbs = bbsEstbsService.selectObj(bbsEstbsVO);
				model.addAttribute("bbsEstbs", bbsEstbs);
				
				if(bbsEstbs == null) {
					logger.error("게시판_설정 정보가 없습니다. (오류코드 : C0041)");
					throw new WrongApproachException("C0041");
				}
				
				for (String itemKey : chkKey) {
					if(StringUtils.isEmpty(itemKey)) {
						continue;
					}
					
					BbsVO bbsVO = new BbsVO();
					bbsVO.setBbsSn(Integer.parseInt(itemKey));
					BbsVO item = bbsService.selectObj(bbsVO);
					
					if(item != null) {
						bbsService.deleteInfo(request, item, admSession.getMngrId());
					}
				}
			} catch (Exception e) {
				logger.error("게시판 정보 삭제 오류 : " + e.getMessage());
				throw new WrongApproachException("C0004");
			}
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/bbs/list.do?key=" + key);
		model.addAttribute("msg",    "선택한 정보를 삭제 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aBbsEstbsGetList
	 * @작성일		: 2019. 11. 20.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판_설정 목록 조회
	 */
	@RequestMapping(value = "/bbs/getCtgrySubList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aBbsEstbsGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "bbsCtgrySn", required = true) int bbsCtgrySn,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<BbsCtgrySubVO> itemList = null;
		BbsCtgrySubVO vo = new BbsCtgrySubVO();
		
		try {
			
			if(bbsCtgrySn+"" != null) {
				vo.setBbsCtgrySn(bbsCtgrySn);
				System.out.println("dlvmdlvm");
				itemList = bbsCtgrySubService.selectList(vo);
				
			}
			/*int tCount = bbsEstbsService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			itemList = bbsEstbsService.selectPageList(searchVO);
			
			if(itemList != null && itemList.size() > 0) {
				itemList.get(0).settCount(tCount);
			}*/
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(itemList).toString();
	}
		

}
