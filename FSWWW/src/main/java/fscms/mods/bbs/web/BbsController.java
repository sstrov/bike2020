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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.util.EgovCryptoARIAHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.util.CryptoHelper;
import fscms.cmm.util.FsFuncCmmHelper;
import fscms.cmm.util.PropConnHelper;
import fscms.cmm.util.SessionCookieHelper;
import fscms.cmm.util.UploadHelper;
import fscms.cmm.vo.JsonVO;
import fscms.mods.atchmnfl.service.AtchmnflService;
import fscms.mods.atchmnfl.vo.AtchmnflVO;
import fscms.mods.bbs.service.BbsCtgryService;
import fscms.mods.bbs.service.BbsEstbsService;
import fscms.mods.bbs.service.BbsFieldService;
import fscms.mods.bbs.service.BbsService;
import fscms.mods.bbs.util.BbsPowHelper;
import fscms.mods.bbs.vo.BbsCtgryVO;
import fscms.mods.bbs.vo.BbsEstbsVO;
import fscms.mods.bbs.vo.BbsFieldVO;
import fscms.mods.bbs.vo.BbsSearchVO;
import fscms.mods.bbs.vo.BbsVO;
import fscms.mods.user.util.UserMenuHelper;
import fscms.mods.user.vo.UserMenuVO;
import fscms.mods.user.vo.UserVO;
import net.sf.json.JSONArray;

@Controller
public class BbsController {
	
	static Logger logger = LogManager.getLogger(BbsController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "userMenuHelper")
	private UserMenuHelper userMenuHelper;
	
	@Resource(name = "egovCryptoARIAHelper")
	private EgovCryptoARIAHelper egovCryptoARIAHelper;
	
	@Resource(name = "atchmnflService")
	private AtchmnflService atchmnflService;
	
	@Resource(name = "bbsService")
	private BbsService bbsService;
	
	@Resource(name = "bbsCtgryService")
	private BbsCtgryService bbsCtgryService;
	
	@Resource(name = "bbsEstbsService")
	private BbsEstbsService bbsEstbsService;
	
	@Resource(name = "bbsFieldService")
	private BbsFieldService bbsFieldService;
	
	@Autowired
	private BbsPowHelper bbsPowHelper;
	
	@Autowired
	private UploadHelper uploadHelper;
	
	/**
	 * @Method Name	: bbsList
	 * @작성일		: 2019. 12. 2.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판 목록 화면
	 */
	@RequestMapping(value = "/bbs/list.do")
	public String bbsList(
			HttpServletRequest request,
			@ModelAttribute("searchVO") BbsSearchVO searchVO,
			UserVO userSession,
			String key,
			ModelMap model) {
		
		UserMenuVO menu = userMenuHelper.getJson(key);
		if(menu == null || !StringUtils.equals(menu.getMenuCnncTy(), "3") || menu.getAccSn() == null) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		BbsEstbsVO bbsEstbs = null;
		try {
			// 게시판_설정 정보 조회
			BbsEstbsVO bbsEstbsVO = new BbsEstbsVO();
			bbsEstbsVO.setBbsEstbsSn(menu.getAccSn());
			bbsEstbs = bbsEstbsService.selectObj(bbsEstbsVO);
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
			
			// 게시판_필드 리스트 정보 조회
			bbsFieldVO.setFieldSearchAt(null);
			bbsFieldVO.setFieldListAt("Y");
			bbsFieldVO.setOrderBy("LIST_ORDR ASC");
			List<BbsFieldVO> fieldList = bbsFieldService.selectList(bbsFieldVO);
			model.addAttribute("fieldList", fieldList);
			
			// 카테고리 검색 선택 시
			boolean cateSearchAt = false;
			if(fieldList != null) {
				for (BbsFieldVO bbsField : fieldList) {
					if("brdCateNm".equals(bbsField.getBbsFieldCode()) && "Y".equals(bbsField.getFieldSearchAt())) {
						cateSearchAt = true;
					}
				}
			}
			
			List<BbsCtgryVO> cateList = null;
			if(cateSearchAt && StringUtils.equals(bbsEstbs.getCtgryAt(), "Y")) {
				BbsCtgryVO bbsCtgryVO = new BbsCtgryVO();
				bbsCtgryVO.setBbsEstbsSn(bbsEstbs.getBbsEstbsSn());
				cateList = bbsCtgryService.selectList(bbsCtgryVO);
			}
			model.addAttribute("cateList", cateList);
			
			// 권한 설정
			boolean powList  = bbsPowHelper.getPow(request, bbsEstbs.getAuthorList(), bbsEstbs.getBbsEstbsSn(), userSession, "LIST");
			boolean powRead  = bbsPowHelper.getPow(request, bbsEstbs.getAuthorRedng(), bbsEstbs.getBbsEstbsSn(), userSession, "READ");
			boolean powWrite = bbsPowHelper.getPow(request, bbsEstbs.getAuthorRegist(), bbsEstbs.getBbsEstbsSn(), userSession, "WRITE");
			model.addAttribute("powList",  powList);
			model.addAttribute("powRead",  powRead);
			model.addAttribute("powWrite", powWrite);
			
			// 게시판 목록 조회
			//searchVO.setMaxList(bbsEstbs.getListIndictCo());
			searchVO.setBbsEstbsSn(bbsEstbs.getBbsEstbsSn());
			searchVO.setBbsSn(null);
			searchVO.setBbsUpperSn(null);
			searchVO.setDeleteAt("N");
			
			if(StringUtils.isNotEmpty(searchVO.getSw())) {
				try {
					searchVO.setSw_entry(egovCryptoARIAHelper.encrypt(searchVO.getSw()));
				} catch (UnsupportedEncodingException e) {
					searchVO.setSw_entry(searchVO.getSw());
				}
			}
			
			List<BbsVO> notiList = null;
			List<BbsVO> brdList  = null;
			PaginationInfo paginationInfo = new PaginationInfo();
			
			int tCount = 0;
			if(powList) {
				if(searchVO.getPageIndex() <= 1) {
					searchVO.setNoticeAt("Y");
					notiList = bbsService.selectList(searchVO);
				}
				
				searchVO.setNoticeAt("N");
				tCount = bbsService.selectTCount(searchVO);
				
				paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
				paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
				paginationInfo.setPageSize(searchVO.getPageSize());
				paginationInfo.setTotalRecordCount(tCount);
				
				searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
				searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
				searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
				brdList = bbsService.selectPageList(searchVO);
			} else {
				paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
				paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
				paginationInfo.setPageSize(searchVO.getPageSize());
				paginationInfo.setTotalRecordCount(tCount);
			}
			
			model.addAttribute("tCount",         tCount);
			model.addAttribute("notiList",       notiList);
			model.addAttribute("brdList",        brdList);
			model.addAttribute("paginationInfo", paginationInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("게시판 정보 조회 오류 (오류코드 : C0002)");
			throw new WrongApproachException("C0002");
		}
		
		return "bbs/" + bbsEstbs.getSknSnDp1Code() + "/" + bbsEstbs.getSknSnDp2Code();
	}
	
	/**
	 * @Method Name	: bbsView
	 * @작성일		: 2019. 12. 2.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판 상세보기
	 */
	@RequestMapping(value = "/bbs/view.do")
	public String bbsView(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("searchVO") BbsSearchVO searchVO,
			@RequestParam(value = "bbsSn", required = true) int bbsSn,
			UserVO userSession,
			String key,
			ModelMap model) {
		
		UserMenuVO menu = userMenuHelper.getJson(key);
		if(menu == null || !StringUtils.equals(menu.getMenuCnncTy(), "3") || menu.getAccSn() == null) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		BbsEstbsVO bbsEstbs = null;
		BbsVO item = new BbsVO();
		try {
			// 게시판_설정 정보 조회
			BbsEstbsVO bbsEstbsVO = new BbsEstbsVO();
			bbsEstbsVO.setBbsEstbsSn(menu.getAccSn());
			bbsEstbs = bbsEstbsService.selectObj(bbsEstbsVO);
			model.addAttribute("bbsEstbs", bbsEstbs);
			
			if(bbsEstbs == null) {
				logger.error("게시판_설정 정보가 없습니다. (오류코드 : C0041)");
				throw new WrongApproachException("C0041");
			}
			
			// 권한 설정
			boolean powRead  = bbsPowHelper.getPow(request, bbsEstbs.getAuthorRedng(), bbsEstbs.getBbsEstbsSn(), userSession, "READ");
			boolean powWrite = bbsPowHelper.getPow(request, bbsEstbs.getAuthorRegist(), bbsEstbs.getBbsEstbsSn(), userSession, "WRITE");
			boolean powReply = bbsPowHelper.getPow(request, bbsEstbs.getAuthorAnswer(), bbsEstbs.getBbsEstbsSn(), userSession, "REPLY");
			
			if(!powRead) {
				model.addAttribute("url", "/index.do");
				model.addAttribute("msg", "접근권한이 없습니다.");
				return "success";
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
			bbsVO.setDeleteAt("N");
			item = bbsService.selectRnum(bbsVO);
			
			if(item == null) {
				logger.error("게시판 정보가 없습니다. (오류코드 : C0041)");
				throw new WrongApproachException("C0041");
			}
			
			if(powWrite) {
				// 쓰기 권한이 있으면 본인글인지 체크
				if(item.getBbsSn() != null) {
					if(StringUtils.isNotEmpty(item.getUserId())) {
						if(userSession == null || userSession.getUserSn() == null || !StringUtils.equals(item.getUserId(), userSession.getUserId())) {
							// 정보가 다르면 쓰기권한 없음
							powWrite = false;
						}
					} else if(StringUtils.isNotEmpty(item.getMngrId())) {
						powWrite = false;
					}
				}
			}
			
			model.addAttribute("powRead",  powRead);
			model.addAttribute("powWrite", powWrite);
			model.addAttribute("powReply", powReply);
			
			if(StringUtils.equals(item.getSecretAt(), "Y")) {
				// 비밀글이면 본인 또는 비밀번호 확인자만 접근
				if(StringUtils.isNotEmpty(item.getRegistPw())) {
					String brdPwAt = (String) request.getSession().getAttribute("brdPw_" + item.getBbsSn());
					if(StringUtils.isEmpty(brdPwAt) || !StringUtils.equals(brdPwAt, "Y")) {
						model.addAttribute("url", "/bbs/list.do?key=" + key);
						model.addAttribute("msg", "해당글에대한 읽기 권한이 없습니다.");
						return "success";
					}
				} else if(StringUtils.isNotEmpty(item.getUserId())) {
					// 사용자 정보가 있으면 본인글 체크
					if(!(StringUtils.isNotEmpty(item.getUserId()) && item.getUserId().equals(userSession.getUserId()))) {
						// 본인글이 아니면 답글 사용자 체크
						if(item.getBbsUpperSn() != null && item.getBbsUpperSn() > 0) {
							bbsVO = new BbsVO();
							bbsVO.setBbsSn(item.getBbsUpperSn());
							BbsVO fBrd = bbsService.selectObj(bbsVO);
							
							if(StringUtils.isNotEmpty(fBrd.getUserId())) {
								if(!(StringUtils.isNotEmpty(fBrd.getUserId()) && fBrd.getUserId().equals(userSession.getUserId()))) {
									model.addAttribute("url", "/bbs/list.do?key=" + key);
									model.addAttribute("msg", "해당글에대한 읽기 권한이 없습니다.");
									return "success";
								}
							}
						} else {
							model.addAttribute("url", "/bbs/list.do?key=" + key);
							model.addAttribute("msg", "해당글에대한 읽기 권한이 없습니다.");
							return "success";
						}
					}
				}
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
			
			// 이전/다음 표시
			if(item.getNextBbsSn() != null && item.getNextBbsSn() > 0) {
				bbsVO = new BbsVO();
				bbsVO.setBbsSn(item.getNextBbsSn());
				BbsVO nextItem = bbsService.selectObj(bbsVO);
				model.addAttribute("nextItem", nextItem);
			}
			
			if(item.getPrevBbsSn() != null && item.getPrevBbsSn() > 0) {
				bbsVO = new BbsVO();
				bbsVO.setBbsSn(item.getPrevBbsSn());
				BbsVO prevItem = bbsService.selectObj(bbsVO);
				model.addAttribute("prevItem", prevItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("게시판 정보 조회 오류 (오류코드 : C0002)");
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("item",   item);
		
		String viewTemplate = "view";
		if(StringUtils.equals(bbsEstbs.getCmmnViewAt(), "N")) {
			viewTemplate = bbsEstbs.getSknSnDp2Code() + "_view";
		}
		return "bbs/" + bbsEstbs.getSknSnDp1Code() + "/" + viewTemplate;
	}
	
	/**
	 * @Method Name	: bbsForm
	 * @작성일		: 2019. 12. 3.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판 등록/수정 화면
	 */
	@RequestMapping(value = "/bbs/form.do")
	public String bbsForm(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("searchVO") BbsSearchVO searchVO,
			UserVO userSession,
			String key,
			ModelMap model) {
		
		UserMenuVO menu = userMenuHelper.getJson(key);
		if(menu == null || !StringUtils.equals(menu.getMenuCnncTy(), "3") || menu.getAccSn() == null) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		BbsEstbsVO bbsEstbs = null;
		BbsVO item = new BbsVO();
		try {
			// 게시판_설정 정보 조회
			BbsEstbsVO bbsEstbsVO = new BbsEstbsVO();
			bbsEstbsVO.setBbsEstbsSn(menu.getAccSn());
			bbsEstbs = bbsEstbsService.selectObj(bbsEstbsVO);
			model.addAttribute("bbsEstbs", bbsEstbs);
			
			if(bbsEstbs == null) {
				logger.error("게시판_설정 정보가 없습니다. (오류코드 : C0041)");
				throw new WrongApproachException("C0041");
			}
			
			// 권한 설정
			boolean powWrite = bbsPowHelper.getPow(request, bbsEstbs.getAuthorRegist(), bbsEstbs.getBbsEstbsSn(), userSession, "WRITE");
			boolean powReply = bbsPowHelper.getPow(request, bbsEstbs.getAuthorAnswer(), bbsEstbs.getBbsEstbsSn(), userSession, "REPLY");
			model.addAttribute("powWrite", powWrite);
			model.addAttribute("powReply", powReply);
			
			if(!powWrite) {
				model.addAttribute("url", "/bbs/list.do?key=" + key);
				model.addAttribute("msg", "쓰기 권한이 없습니다.");
				return "success";
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
			
			if(userSession != null && StringUtils.isNotEmpty(userSession.getUserId())) {
				item.setRegisterNm(userSession.getUserNm());
			}
			
			if(searchVO.getBbsSn() != null && searchVO.getBbsSn() > 0) {
				// 일련번호가 있으면 수정
				// 일련번호가 있으면 수정
				BbsVO bbsVO = new BbsVO();
				bbsVO.setBbsSn(searchVO.getBbsSn());
				bbsVO.setBbsEstbsSn(bbsEstbs.getBbsEstbsSn());
				item = bbsService.selectObj(bbsVO);
				
				if(item == null) {
					logger.error("게시판 정보가 없습니다. (오류코드 : C0041)");
					throw new WrongApproachException("C0041");
				}
				
				// 수정일 시 수정권한 체크
				if(StringUtils.isNotEmpty(item.getUserId())) {
					if(userSession == null || userSession.getUserSn() == null || !StringUtils.equals(item.getUserId(), userSession.getUserId())) {
						// 정보가 다르면 쓰기권한 없음
						powWrite = false;
					}
				} else if(StringUtils.isNotEmpty(item.getRegistPw())) {
					// 비밀번호 체크 기능 추가
					String brdPwAt = (String) request.getSession().getAttribute("brdPw_" + item.getBbsSn());
					if(StringUtils.isEmpty(brdPwAt) || !brdPwAt.equals("Y")) {
						powWrite = false;
					}
				} else {
					powWrite = false;
				}
				
				if(!powWrite) {
					model.addAttribute("url", "/bbs/list.do?key=" + key);
					model.addAttribute("msg", "쓰기 권한이 없습니다.");
					return "success";
				}
				
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
				
				if(reply != null && !powReply) {
					model.addAttribute("url", "/bbs/list.do?key=" + key);
					model.addAttribute("msg", "쓰기 권한이 없습니다.");
					return "success";
				}
				model.addAttribute("reply", reply);
			}
			
			// 카테고리 목록 조회
			BbsCtgryVO bbsCtgryVO = new BbsCtgryVO();
			bbsCtgryVO.setBbsEstbsSn(bbsEstbs.getBbsEstbsSn());
			List<BbsCtgryVO> cateList = bbsCtgryService.selectList(bbsCtgryVO);
			model.addAttribute("cateList", cateList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("게시판 정보 조회 오류 (오류코드 : C0002)");
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("item",        item);
		model.addAttribute("userSession", userSession);
		return "bbs/" + bbsEstbs.getSknSnDp1Code() + "/form";
	}
	
	/**
	 * @Method Name	: bbsInsert
	 * @작성일		: 2019. 12. 4.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판 정보 저장
	 */
	@RequestMapping(value = "/bbs/insert.do", method = RequestMethod.POST)
	public String bbsInsert(
			HttpServletRequest request,
			@ModelAttribute("vo") BbsVO vo,
			@RequestParam(value = "uniqueId", required = true) String uniqueId,
			UserVO userSession,
			String key,
			Boolean refAt,
			ModelMap model) {
		
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		UserMenuVO menu = userMenuHelper.getJson(key);
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
			
			// 권한 설정
			boolean powWrite = bbsPowHelper.getPow(request, bbsEstbs.getAuthorRegist(), bbsEstbs.getBbsEstbsSn(), userSession, "WRITE");
			boolean powReply = bbsPowHelper.getPow(request, bbsEstbs.getAuthorAnswer(), bbsEstbs.getBbsEstbsSn(), userSession, "REPLY");
			model.addAttribute("powWrite", powWrite);
			model.addAttribute("powReply", powReply);
			
			if(!powWrite || (vo.getBbsDp() > 1 && !powReply)) {
				model.addAttribute("url", "/bbs/list.do?key=" + key);
				model.addAttribute("msg", "쓰기 권한이 없습니다.");
				return "success";
			}
			
			String userId = "GUEST";
			if(userSession != null && StringUtils.isNotEmpty(userSession.getUserId())) {
				vo.setUserId(userSession.getUserId());
				userId = userSession.getUserId();
			}
			
			vo.setBbsSalt(CryptoHelper.generateSalt());
			vo.setBbsEstbsSn(bbsEstbs.getBbsEstbsSn());
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
			
			bbsService.insertInfo(request, vo, userId, uniqueId);
			
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
		
		model.addAttribute("url", "/bbs/list.do?key=" + key);
		model.addAttribute("msg", "저장 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aBbsUpdate
	 * @작성일		: 2019. 12. 4.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판 정보 수정
	 */
	@RequestMapping(value = "/bbs/update.do", method = RequestMethod.POST)
	public String aBbsUpdate(
			HttpServletRequest request,
			@ModelAttribute("searchVO") BbsSearchVO searchVO,
			@ModelAttribute("vo") BbsVO vo,
			@RequestParam(value = "uniqueId", required = true) String uniqueId,
			@RequestParam(value = "bbsSn", required = true) int bbsSn,
			UserVO userSession,
			String key,
			Boolean refAt,
			ModelMap model) {
		
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		UserMenuVO menu = userMenuHelper.getJson(key);
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
				// 권한 설정
				boolean powWrite = bbsPowHelper.getPow(request, bbsEstbs.getAuthorRegist(), bbsEstbs.getBbsEstbsSn(), userSession, "WRITE");
				model.addAttribute("powWrite", powWrite);
				
				if(StringUtils.isNotEmpty(item.getUserId())) {
					// 회원 게시글
					if(userSession == null || StringUtils.isEmpty(userSession.getUserId()) || !StringUtils.equals(item.getUserId(), userSession.getUserId())) {
						// 정보가 없거나 다르면 권한없음
						powWrite = false;
					}
				} else if(StringUtils.isNotEmpty(item.getRegistPw())) {
					// 비회원 게시글
					String brdPwAt = (String) request.getSession().getAttribute("brdPw_" + item.getBbsSn());
					if(StringUtils.isEmpty(brdPwAt) || !StringUtils.equals(brdPwAt, "Y")) {
						powWrite = false;
					}
				} else {
					powWrite = false;
				}
				
				if(!powWrite) {
					model.addAttribute("url", "/bbs/list.do?key=" + key);
					model.addAttribute("msg", "쓰기 권한이 없습니다.");
					return "success";
				}
				
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
				
				String userId = "GUEST";
				if(userSession != null && StringUtils.isNotEmpty(userSession.getUserId())) {
					userId = userSession.getUserId();
				}
				
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				bbsService.updateInfo(request, bfData, vo, userId, uniqueId);
			}
		} catch (Exception e) {
			logger.error("게시판 정보 수정 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/bbs/form.do?key=" + key + "&bbsSn=" + bbsSn);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aBbsDelete
	 * @작성일		: 2019. 12. 4.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판 정보 삭제
	 */
	@RequestMapping(value = "/bbs/delete.do", method = RequestMethod.POST)
	public String aBbsDelete(
			HttpServletRequest request,
			@ModelAttribute("searchVO") BbsSearchVO searchVO,
			@ModelAttribute("vo") BbsVO vo,
			@RequestParam(value = "bbsSn", required = true) int bbsSn,
			UserVO userSession,
			String key,
			Boolean refAt,
			ModelMap model) {
		
		UserMenuVO menu = userMenuHelper.getJson(key);
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
				// 권한 설정
				boolean powWrite = bbsPowHelper.getPow(request, bbsEstbs.getAuthorRegist(), bbsEstbs.getBbsEstbsSn(), userSession, "WRITE");
				model.addAttribute("powWrite", powWrite);
				
				if(StringUtils.isNotEmpty(item.getUserId())) {
					// 회원 게시글
					if(userSession == null || StringUtils.isEmpty(userSession.getUserId()) || !StringUtils.equals(item.getUserId(), userSession.getUserId())) {
						// 정보가 없거나 다르면 권한없음
						powWrite = false;
					}
				} else if(StringUtils.isNotEmpty(item.getRegistPw())) {
					// 비회원 게시글
					String brdPwAt = (String) request.getSession().getAttribute("brdPw_" + item.getBbsSn());
					if(StringUtils.isEmpty(brdPwAt) || !StringUtils.equals(brdPwAt, "Y")) {
						powWrite = false;
					}
				} else {
					powWrite = false;
				}
				
				if(!powWrite) {
					model.addAttribute("url", "/bbs/list.do?key=" + key);
					model.addAttribute("msg", "삭제 권한이 없습니다.");
					return "success";
				}
				
				bbsVO.setDeleteAt("Y");
				
				String userId = "GUEST";
				if(userSession != null && StringUtils.isNotEmpty(userSession.getUserId())) {
					userId = userSession.getUserId();
				}
				
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				bbsService.updateInfo(request, bfData, bbsVO, userId, null);
			}
		} catch (Exception e) {
			logger.error("게시판 정보 수정 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url", "/bbs/view.do?key=" + key + "&bbsSn=" + bbsSn);
		model.addAttribute("msg", "삭제 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: bbsIsExistPw
	 * @작성일		: 2019. 12. 4.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판 비밀번호 체크
	 */
	@RequestMapping(value = "/bbs/isExistPw.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String bbsIsExistPw(
			HttpServletRequest request,
			@RequestParam(value = "bbsSn", required = true) int bbsSn,
			@RequestParam(value = "regPw", required = true) String regPw,
			Boolean refAt) {
		
		PropConnHelper propConnHelper = new PropConnHelper();
		JsonVO jsonVO = new JsonVO();
		
		try {
			BbsVO bbsVO = new BbsVO();
			bbsVO.setBbsSn(bbsSn);
			bbsVO.setDeleteAt("N");
			BbsVO item = bbsService.selectObj(bbsVO);
			
			boolean errorChk = false;
			
			if(item != null) {
				request.getSession().setAttribute("brdPw_" + item.getBbsSn(), null);
				
				String encPw = CryptoHelper.encrypt(regPw, item.getBbsSalt());
				if(!StringUtils.equals(encPw, item.getRegistPw())) {
					// 비밀번호 불일치
					if(item.getBbsUpperSn() != null && item.getBbsUpperSn() > 0) {
						bbsVO = new BbsVO();
						bbsVO.setBbsSn(item.getBbsBestSn());
						bbsVO.setDeleteAt("N");
						BbsVO fItem = bbsService.selectObj(bbsVO);
						
						if(fItem != null) {
							encPw = CryptoHelper.encrypt(regPw, fItem.getBbsSalt());
							if(!StringUtils.equals(encPw, fItem.getRegistPw())) {
								request.getSession().setAttribute("brdPw_" + item.getBbsSn(), "Y");
								request.getSession().setAttribute("brdPw_" + fItem.getBbsSn(), "Y");
							} else {
								errorChk = true;
							}
						} else {
							errorChk = true;
						}
					} else {
						errorChk = true;
					}
				} else {
					request.getSession().setAttribute("brdPw_" + item.getBbsSn(), "Y");
				}
			} else {
				errorChk = true;
			}
			
			if(errorChk) {
				jsonVO.setState("200");
				jsonVO.setMsg(propConnHelper.getMessage(propConnHelper.getConn("globals", "Globals.language"), "sso.pw.notfound"));
			}
			
			return JSONArray.fromObject(jsonVO).toString();
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
	}
	
	/**
	 * @Method Name	: bbsList
	 * @작성일		: 2019. 12. 2.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판 목록 화면
	 */
	@RequestMapping(value = "/bbs/road.do")
	public String bbsRoad(
			HttpServletRequest request,
			@ModelAttribute("searchVO") BbsSearchVO searchVO,
			UserVO userSession,
			String key,
			ModelMap model) {
		
		UserMenuVO menu = userMenuHelper.getJson(key);
		if(menu == null) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		BbsEstbsVO bbsEstbs = null;
		try {
			// 게시판_설정 정보 조회
			BbsEstbsVO bbsEstbsVO = new BbsEstbsVO();
			bbsEstbsVO.setBbsEstbsSn(19);
			bbsEstbs = bbsEstbsService.selectObj(bbsEstbsVO);
			model.addAttribute("bbsEstbs", bbsEstbs);
			
			if(bbsEstbs == null) {
				logger.error("게시판_설정 정보가 없습니다. (오류코드 : C0041)");
				throw new WrongApproachException("C0041");
			}
			
/*			// 게시판_필드 검색 목록 조회
			BbsFieldVO bbsFieldVO = new BbsFieldVO();
			bbsFieldVO.setBbsEstbsSn(bbsEstbs.getBbsEstbsSn());
			bbsFieldVO.setFieldSearchAt("Y");
			List<BbsFieldVO> searchList = bbsFieldService.selectList(bbsFieldVO);
			model.addAttribute("searchList", searchList);
			
			// 게시판_필드 리스트 정보 조회
			bbsFieldVO.setFieldSearchAt(null);
			bbsFieldVO.setFieldListAt("Y");
			bbsFieldVO.setOrderBy("LIST_ORDR ASC");
			List<BbsFieldVO> fieldList = bbsFieldService.selectList(bbsFieldVO);
			model.addAttribute("fieldList", fieldList);
			
			// 카테고리 검색 선택 시
			boolean cateSearchAt = false;
			if(fieldList != null) {
				for (BbsFieldVO bbsField : fieldList) {
					if("brdCateNm".equals(bbsField.getBbsFieldCode()) && "Y".equals(bbsField.getFieldSearchAt())) {
						cateSearchAt = true;
					}
				}
			}
			
			List<BbsCtgryVO> cateList = null;
			if(cateSearchAt && StringUtils.equals(bbsEstbs.getCtgryAt(), "Y")) {
				BbsCtgryVO bbsCtgryVO = new BbsCtgryVO();
				bbsCtgryVO.setBbsEstbsSn(bbsEstbs.getBbsEstbsSn());
				cateList = bbsCtgryService.selectList(bbsCtgryVO);
			}
			model.addAttribute("cateList", cateList);
*/			
			// 권한 설정
			boolean powList  = bbsPowHelper.getPow(request, bbsEstbs.getAuthorList(), bbsEstbs.getBbsEstbsSn(), userSession, "LIST");
			boolean powRead  = bbsPowHelper.getPow(request, bbsEstbs.getAuthorRedng(), bbsEstbs.getBbsEstbsSn(), userSession, "READ");
			boolean powWrite = bbsPowHelper.getPow(request, bbsEstbs.getAuthorRegist(), bbsEstbs.getBbsEstbsSn(), userSession, "WRITE");
			model.addAttribute("powList",  powList);
			model.addAttribute("powRead",  powRead);
			model.addAttribute("powWrite", powWrite);
			
			// 게시판 목록 조회
			//searchVO.setMaxList(bbsEstbs.getListIndictCo());
			searchVO.setBbsEstbsSn(bbsEstbs.getBbsEstbsSn());
			searchVO.setBbsSn(null);
			searchVO.setBbsUpperSn(null);
			searchVO.setDeleteAt("N");
			searchVO.setBbsDp(1);
			
			System.out.println("45544564654555555555555555555555");
			System.out.println(searchVO.getBbsCtgrySn());
			System.out.println(searchVO.getBbsCtgrySubSn());
			
			
			if(StringUtils.isNotEmpty(searchVO.getSw())) {
				try {
					searchVO.setSw_entry(egovCryptoARIAHelper.encrypt(searchVO.getSw()));
				} catch (UnsupportedEncodingException e) {
					searchVO.setSw_entry(searchVO.getSw());
				}
			}
			
			List<BbsVO> notiList = null;
			List<BbsVO> brdList  = null;
			PaginationInfo paginationInfo = new PaginationInfo();
			
			int tCount = 0;
			if(powList) {
				
				searchVO.setNoticeAt("N");
				tCount = bbsService.selectTCount(searchVO);
				
				paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
				paginationInfo.setRecordCountPerPage(3);
				paginationInfo.setPageSize(searchVO.getPageSize());
				paginationInfo.setTotalRecordCount(tCount);
				
				searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
				searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
				searchVO.setRecordCountPerPage(3);
				brdList = bbsService.selectPageList(searchVO);
			} else {
				paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
				paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
				paginationInfo.setPageSize(searchVO.getPageSize());
				paginationInfo.setTotalRecordCount(tCount);
			}
			
			model.addAttribute("tCount",         tCount);
			model.addAttribute("notiList",       notiList);
			model.addAttribute("brdList",        brdList);
			model.addAttribute("paginationInfo", paginationInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("게시판 정보 조회 오류 (오류코드 : C0002)");
			throw new WrongApproachException("C0002");
		}
		
		return "bbs/roadView/list";
	}
	
}
