package fscms.mods.user.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import fscms.mods.road.service.RoadService;
import fscms.mods.road.vo.RoadVO;
import fscms.mods.auth.service.AuthNoteService;
import fscms.mods.auth.service.AuthUploadService;
import fscms.mods.auth.vo.AuthNoteSearchVO;
import fscms.mods.auth.vo.AuthNoteVO;
import fscms.mods.auth.vo.AuthUploadSearchVO;
import fscms.mods.auth.vo.AuthUploadVO;
import fscms.mods.user.service.UserRoleService;
import fscms.mods.user.service.UserService;
import fscms.mods.user.vo.UserSearchVO;
import fscms.mods.user.vo.UserVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_UserController {
	
	static Logger logger = LogManager.getLogger(A_UserController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "userRoleService")
	private UserRoleService userRoleService;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	@Resource(name = "egovCryptoARIAHelper")
	private EgovCryptoARIAHelper egovCryptoARIAHelper;
	
	@Resource(name = "indvdlinfoAccesHistHelper")
	private IndvdlinfoAccesHistHelper indvdlinfoAccesHistHelper;
	
	@Resource(name = "authUploadService")
	private AuthUploadService authUploadService;
	
	@Resource(name = "authNoteService")
	private AuthNoteService authNoteService;
	
	@Resource(name = "roadService")
	private RoadService roadService;
	
	/**
	 * @Method Name	: aUserList
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자 목록 화면
	 */
	@RequestMapping(value = "/user/list.do")
	public String aUserList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") UserSearchVO searchVO,
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
		return "mngr/user/list";
	}
	
	/**
	 * @Method Name	: aUserList
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자 목록 화면
	 */
	@RequestMapping(value = "/user/dropList.do")
	public String aUserDropList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") UserSearchVO searchVO,
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
		return "mngr/user/drop_list";
	}
	
	/**
	 * @Method Name	: aUserGetList
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자 정보 목록 조회
	 */
	@RequestMapping(value = "/user/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aUserGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") UserSearchVO searchVO,
			MngrVO admSession,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<UserVO> itemList = null;
		if(StringUtils.isNotEmpty(searchVO.getSw())) {
			try {
				searchVO.setSw_entry(egovCryptoARIAHelper.encrypt(searchVO.getSw()));
			} catch (UnsupportedEncodingException e) {
				searchVO.setSw_entry(searchVO.getSw());
			}
		}
		try {
			int tCount = userService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			itemList = userService.selectPageList(searchVO);
			
			if(itemList != null && itemList.size() > 0) {
				itemList.get(0).settCount(tCount);
				
				String cn = "";
				for (UserVO item : itemList) {
					cn += ((StringUtils.isNotEmpty(cn))? ", " : "") + item.getUserId();
				}
				
				if(StringUtils.isNotEmpty(cn)) {
					// 개인정보_접근_이력 저장
					String indvdlinfoAccesHistCn = "사용자 (" + cn + ") 정보 목록을 조회 하였습니다.";
					indvdlinfoAccesHistHelper.setInsert(request, null, admSession.getMngrId(), indvdlinfoAccesHistCn, null);
				}
			}
		} catch (Exception e) {
			System.out.println("eeee : "+e);
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(itemList).toString();
	}
	
	/**
	 * @Method Name	: aUserForm
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자 등록/수정 화면
	 */
	@RequestMapping(value = "/user/form.do")
	public String aUserForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") UserSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		UserVO item = new UserVO();
		List<AuthNoteVO> noteList = null;
		try {
			if(searchVO.getUserSn() != null && searchVO.getUserSn() > 0) {
				// 일련번호가 있으면 수정
				UserVO userVO = new UserVO();
				userVO.setUserSn(searchVO.getUserSn());
				item = userService.selectObj(userVO);
				
				AuthNoteSearchVO nsVO = new AuthNoteSearchVO();
				nsVO.setUserSn(searchVO.getUserSn());
				// 개인정보_접근_이력 저장
				String indvdlinfoAccesHistCn = "사용자 (" + item.getUserId() + " | " + item.getUserNm() + ") 정보를 상세조회 하였습니다.";
				indvdlinfoAccesHistHelper.setInsert(request, null, admSession.getMngrId(), indvdlinfoAccesHistCn, null);
				
				if(item != null) {
					noteList = authNoteService.selectNoteList(nsVO);
				}
			} else {
				// 등록 일때는 등록 권한 필요
				if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
					logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
					throw new WrongApproachException("C0041");
				}
			}
			
			model.addAttribute("roleList", userRoleService.selectList(null));
		} catch (Exception e) {
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		List<RoadVO> roadList = null;
		List<RoadVO> centerList = null;
		List<RoadVO> confirmWebList = null;
		List<AuthUploadVO> auth = null;
		List<RoadVO> confirmAuth = new ArrayList<RoadVO>();
		List<RoadVO> confirmWeb = new ArrayList<RoadVO>();
		
		try {
			RoadVO roadVO = new RoadVO();
			roadList = roadService.selectRoadList(roadVO); 
			centerList = roadService.selectAuthList(roadVO);
			
			roadVO.setUserSn(item.getUserSn());
			roadVO.setNoteSn(item.getNoteSn());
			confirmWebList = roadService.selectConfirmList(roadVO);
		
			if(item.getUserSn() != null && StringUtils.isNotEmpty(item.getNoteSn())) {
				AuthUploadSearchVO sVO = new AuthUploadSearchVO();
				sVO.setNoteSn(item.getNoteSn());
				sVO.setRecordCountPerPage(9999);
				auth = authUploadService.selectPageList(sVO);
	
				/* 1.그랜드슬램						G00
				 * 2.국토종주 							C00
				 * 3.4대강 							400
				 * 4.구간별		
				 * roadCd						authCode
				 * ROAD_TYPE_NATION_001 아라		 
				 * ROAD_TYPE_NATION_002 남한강  		R05
				 * ROAD_TYPE_NATION_003 낙동강 		R04
				 * ROAD_TYPE_NATION_004 새재 			S01
				 * ROAD_TYPE_NATION_005 금강 			R02
				 * ROAD_TYPE_NATION_006 한강 			R01
				 * ROAD_TYPE_NATION_007 영산강 		R03
				 * ROAD_TYPE_NATION_008 북한강 		R06
				 * ROAD_TYPE_NATION_009 섬진강 		R07
				 * ROAD_TYPE_NATION_010 오천 			O01
				 * ROAD_TYPE_NATION_011 동해안(강원) 	D01
				 * ROAD_TYPE_NATION_012 제주 			J01
				 * ROAD_TYPE_NATION_013 동해안(경북)	D02
				*/
				
				String au;
				String skwd = "";
				String sub = "";
				for(int i=0;i<auth.size();i++) {
					au = auth.get(i).getAuthSn().substring(0,3);
					RoadVO rs = new RoadVO();
					List<RoadVO> rrr = new ArrayList<RoadVO>();
					if(au.equals("G00")) {//그랜드슬램
						/*if(StringUtils.isNotEmpty(skwd) && skwd != "") {
							skwd += ",";
						}*/
						/*skwd += "'ROAD_TYPE_NATION_001', 'ROAD_TYPE_NATION_002', 'ROAD_TYPE_NATION_003', 'ROAD_TYPE_NATION_004', 'ROAD_TYPE_NATION_005'"
								+ ", 'ROAD_TYPE_NATION_006', 'ROAD_TYPE_NATION_007', 'ROAD_TYPE_NATION_008', 'ROAD_TYPE_NATION_009', 'ROAD_TYPE_NATION_010'"
								+ ", 'ROAD_TYPE_NATION_011', 'ROAD_TYPE_NATION_012', 'ROAD_TYPE_NATION_013'";*/
						rs.setAuthType("0");
						rrr = roadService.selectAuthConfirmList(rs);
						confirmAuth.addAll(rrr);
					}else if(au.equals("C00")) {//국토종주   아라, 한강, 새재, 낙동강
						/*if(StringUtils.isNotEmpty(skwd) && skwd != "") {
							skwd += ",";
						}
						skwd += "'ROAD_TYPE_NATION_001', 'ROAD_TYPE_NATION_004', 'ROAD_TYPE_NATION_006', 'ROAD_TYPE_NATION_003'";*/
						rs.setAuthType("2");
						rrr = roadService.selectAuthConfirmList(rs);
						confirmAuth.addAll(rrr);
					}else if(au.equals("400")) {//4대강
						/*if(StringUtils.isNotEmpty(skwd) && skwd != "") {
							skwd += ",";
						}
						skwd += "'ROAD_TYPE_NATION_006', 'ROAD_TYPE_NATION_005', 'ROAD_TYPE_NATION_007', 'ROAD_TYPE_NATION_003'";  //한강, 금강, 영산강, 낙동강
						sub = "AUTH_CD NOT IN ('552747000C', '542866000A')";*/
						rs.setAuthType("3");
						rrr = roadService.selectAuthConfirmList(rs);
						confirmAuth.addAll(rrr);
					}else if(au.equals("R05")) {//남한강
						/*if(StringUtils.isNotEmpty(skwd) && skwd != "") {
							skwd += ",";
						}
						skwd += "'ROAD_TYPE_NATION_002'";*/
						rs.setAuthType("0");
						rs.setRoadCd("ROAD_TYPE_NATION_002");
						rrr = roadService.selectAuthConfirmList(rs);
						confirmAuth.addAll(rrr);
					}else if(au.equals("R04")) {//낙동강
						/*if(StringUtils.isNotEmpty(skwd) && skwd != "") {
							skwd += ",";
						}
						skwd += "'ROAD_TYPE_NATION_003'";*/
						rs.setAuthType("0");
						rs.setRoadCd("ROAD_TYPE_NATION_003");
						rrr = roadService.selectAuthConfirmList(rs);
						confirmAuth.addAll(rrr);
					}else if(au.equals("S01")) {//새재
						/*if(StringUtils.isNotEmpty(skwd) && skwd != "") {
							skwd += ",";
						}
						skwd += "'ROAD_TYPE_NATION_004'";*/
						rs.setAuthType("0");
						rs.setRoadCd("ROAD_TYPE_NATION_004");
						rrr = roadService.selectAuthConfirmList(rs);
						confirmAuth.addAll(rrr);
					}else if(au.equals("R02")) {//금강
						/*if(StringUtils.isNotEmpty(skwd) && skwd != "") {
							skwd += ",";
						}
						skwd += "'ROAD_TYPE_NATION_005'";*/
						rs.setAuthType("0");
						rs.setRoadCd("ROAD_TYPE_NATION_005");
						rrr = roadService.selectAuthConfirmList(rs);
						confirmAuth.addAll(rrr);
					}else if(au.equals("R01")) {//한강
						/*if(StringUtils.isNotEmpty(skwd) && skwd != "") {
							skwd += ",";
						}
						skwd += "'ROAD_TYPE_NATION_006'";*/
						rs.setAuthType("0");
						rs.setRoadCd("ROAD_TYPE_NATION_006");
						rrr = roadService.selectAuthConfirmList(rs);
						confirmAuth.addAll(rrr);
					}else if(au.equals("R03")) {//영산강
						/*if(StringUtils.isNotEmpty(skwd) && skwd != "") {
							skwd += ",";
						}
						skwd += "'ROAD_TYPE_NATION_007'";*/
						rs.setAuthType("0");
						rs.setRoadCd("ROAD_TYPE_NATION_007");
						rrr = roadService.selectAuthConfirmList(rs);
						confirmAuth.addAll(rrr);
					}else if(au.equals("R06")) {//북한강
						/*if(StringUtils.isNotEmpty(skwd) && skwd != "") {
							skwd += ",";
						}
						skwd += "'ROAD_TYPE_NATION_008'";*/
						rs.setAuthType("0");
						rs.setRoadCd("ROAD_TYPE_NATION_008");
						rrr = roadService.selectAuthConfirmList(rs);
						confirmAuth.addAll(rrr);
					}else if(au.equals("R07")) {//섬진강
						/*if(StringUtils.isNotEmpty(skwd) && skwd != "") {
							skwd += ",";
						}
						skwd += "'ROAD_TYPE_NATION_009'";*/
						rs.setAuthType("0");
						rs.setRoadCd("ROAD_TYPE_NATION_009");
						rrr = roadService.selectAuthConfirmList(rs);
						confirmAuth.addAll(rrr);
					}else if(au.equals("O01")) {//오천
						/*if(StringUtils.isNotEmpty(skwd) && skwd != "") {
							skwd += ",";
						}
						skwd += "'ROAD_TYPE_NATION_010'";*/
						rs.setAuthType("0");
						rs.setRoadCd("ROAD_TYPE_NATION_010");
						rrr = roadService.selectAuthConfirmList(rs);
						confirmAuth.addAll(rrr);
					}else if(au.equals("D01")) {//동해안(강원)
						/*if(StringUtils.isNotEmpty(skwd) && skwd != "") {
							skwd += ",";
						}
						skwd += "'ROAD_TYPE_NATION_011'";*/
						rs.setAuthType("0");
						rs.setRoadCd("ROAD_TYPE_NATION_011");
						rrr = roadService.selectAuthConfirmList(rs);
						confirmAuth.addAll(rrr);
					}else if(au.equals("J01")) {//제주
						/*if(StringUtils.isNotEmpty(skwd) && skwd != "") {
							skwd += ",";
						}
						skwd += "'ROAD_TYPE_NATION_012'";*/
						rs.setAuthType("0");
						rs.setRoadCd("ROAD_TYPE_NATION_012");
						rrr = roadService.selectAuthConfirmList(rs);
						confirmAuth.addAll(rrr);
					}else if(au.equals("D02")) {//동해안(경북)
						/*if(StringUtils.isNotEmpty(skwd) && skwd != "") {
							skwd += ",";
						}
						skwd += "'ROAD_TYPE_NATION_013'";*/
						rs.setAuthType("0");
						rs.setRoadCd("ROAD_TYPE_NATION_013");
						rrr = roadService.selectAuthConfirmList(rs);
						confirmAuth.addAll(rrr);
					}
				}
				/*RoadVO rs = new RoadVO();
				rs.setSc("ROAD_CD");
				rs.setSw(skwd);
				
				if(auth.size() > 0) {
					confirmAuth = roadService.selectAuthConfirmList(rs);
				}*/
				
				RoadVO getData = new RoadVO();
				for(int n=0; n<confirmWebList.size();n++) {
					String rc = confirmWebList.get(n).getRoadCd();
					String ac = confirmWebList.get(n).getAuthCd();
					RoadVO rv = new RoadVO();
					rv.setRoadCd(rc);
					rv.setAuthCd(ac);
					getData = roadService.selectAuthObj(rv);
					
					confirmWeb.add(getData);
				}
					
				//confirmWeb = roadService.selectAuthConfirmList(rv);
			
				for(int f = 0; f < centerList.size(); f++) {
					String a1 = centerList.get(f).getAuthCd();
					String r1 = centerList.get(f).getRoadCd();
					System.out.println("--------------------------");
					System.out.println(confirmAuth.size());
					for(int g=0; g<confirmAuth.size(); g++) {
						String a2 = confirmAuth.get(g).getAuthCd();
						String r2 = confirmAuth.get(g).getRoadCd();
						if(a1.equals(a2) && r1.equals(r2)) {
							centerList.get(f).setChecked("checked");
							centerList.get(f).setDisabled("disabled");
						}
					}
					for(int h=0; h<confirmWeb.size();h++) {
						String a3 = confirmWeb.get(h).getAuthCd();
						String r3 = confirmWeb.get(h).getRoadCd();
						if(a1.equals(a3) && r1.equals(r3)) {
							centerList.get(f).setChecked("checked");
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("eeeee : " + e);
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		
		
		model.addAttribute("confirmWeb",   confirmWeb);
		model.addAttribute("authList",     confirmAuth);
		model.addAttribute("centerList",   centerList);
		model.addAttribute("roadList",     roadList);
		model.addAttribute("noteList",     noteList);
		model.addAttribute("role",         role);
		model.addAttribute("item",         item);
		model.addAttribute("admURI",       admURI);
		try {
			model.addAttribute("defPw",  new PropConnHelper().getConn("fs_config", "Fs_config.defaultPassword"));
		} catch (IOException e) {
			logger.error("비밀번호 셋팅 설정파일 없음 : " + e.getMessage());
			throw new WrongApproachException("C0021");
		}
		return "mngr/user/form";
	}
	
	/**
	 * @Method Name	: aUserIsexistId
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자 명 중복 체크
	 */
	@RequestMapping(value = "/user/isExistId.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aUserIsexistId(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "userId", required = true) String userId,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		try {
			PropConnHelper propConnHelper = new PropConnHelper();
			
			UserVO userVO = new UserVO();
			userVO.setUserId(userId);
			UserVO item = userService.selectObj(userVO);
			
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
	 * @Method Name	: aUserInsert
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자 정보 저장
	 */
	@RequestMapping(value = "/user/insert.do", method = RequestMethod.POST)
	public String aUserInsert(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") UserVO vo,
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
			vo.setUserSttus("1");
			vo.setUserSalt(CryptoHelper.generateSalt());
			userService.insertInfo(request, vo, admSession.getMngrId());
			
			if(StringUtils.isNotEmpty(vo.getNoteSn())) {
				AuthNoteSearchVO searchVO = new AuthNoteSearchVO();
				
				searchVO.setUserSn(vo.getUserSn());
				
				List<AuthNoteVO> noteList = authNoteService.selectNoteList(searchVO);
				String bfData = JSONArray.fromObject(noteList).toString();
				
				AuthNoteVO authVO = new AuthNoteVO();
				authVO.setUserSn(vo.getUserSn());
				authVO.setNoteSn(vo.getNoteSn());
				if(noteList.size() < 1) {
					authVO.setAuthYn("Y");
				}
				
				authNoteService.insertNote(request, bfData, authVO, admSession.getMngrId());
				
			}
		
		} catch (Exception e) {
			logger.error("사용자 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0001");
		}
		
		model.addAttribute("url", "/" + admURI + "/user/list.do?key=" + key);
		model.addAttribute("msg", "저장 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aUserUpdate
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자 정보 수정
	 */
	@RequestMapping(value = "/user/update.do", method = RequestMethod.POST)
	public String aUserUpdate(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") UserSearchVO searchVO,
			@ModelAttribute("vo") UserVO vo,
			@RequestParam(value = "userSn", required = true) int userSn,
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
			UserVO userVO = new UserVO();
			userVO.setUserSn(userSn);
			UserVO item = userService.selectObj(userVO);
			
			if(item != null) {
				vo.setUserSalt(item.getUserSalt());
				
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				userService.updateInfo(request, bfData, vo, admSession.getMngrId());
				
				if(StringUtils.isNotEmpty(vo.getNoteSn())) {
					AuthNoteSearchVO asVO = new AuthNoteSearchVO();
					
					asVO.setUserSn(vo.getUserSn());
					
					List<AuthNoteVO> noteList = authNoteService.selectNoteList(asVO);
					bfData = JSONArray.fromObject(noteList).toString();
					
					AuthNoteVO authVO = new AuthNoteVO();
					authVO.setUserSn(vo.getUserSn());
					authVO.setNoteSn(vo.getNoteSn());
					if(noteList.size() < 1) {
						authVO.setAuthYn("Y");
					}
					
					authNoteService.insertNote(request, bfData, authVO, admSession.getMngrId());
					
				}
			}
		} catch (Exception e) {
			logger.error("사용자 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/user/form.do?key=" + key + "&userSn=" + userSn);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aUserDeleteForList
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자 선택정보 삭제
	 */
	@RequestMapping(value = "/user/deleteForList.do", method = RequestMethod.POST)
	public String aUserDeleteForList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") UserSearchVO searchVO,
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
					
					UserVO userVO = new UserVO();
					userVO.setUserSn(Integer.parseInt(itemKey));
					UserVO item = userService.selectObj(userVO);
					
					if(item != null) {
						item.setUpdtDe(null);
						String bfData = JSONArray.fromObject(item).toString();
						userService.deleteInfo(request, bfData, item, admSession.getMngrId());
					}
				}
			} catch (Exception e) {
				logger.error("사용자 정보 삭제 오류 : " + e.getMessage());
				throw new WrongApproachException("C0004");
			}
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/user/list.do?key=" + key);
		model.addAttribute("msg",    "선택한 정보를 삭제 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aUserPwReset
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자 비밀번호 초기화
	 */
	@RequestMapping(value = "/user/pwReset.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aUserPwReset(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "userSn", required = true) int userSn,
			@RequestParam(value = "userId", required = true) String userId,
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
			UserVO userVO = new UserVO();
			userVO.setUserSn(userSn);
			UserVO item = userService.selectObj(userVO);
			
			if(item != null) {
				PropConnHelper propConnHelper = new PropConnHelper();
				
				userVO.setUserSalt(item.getUserSalt());
				userVO.setUserPw(propConnHelper.getConn("fs_config", "Fs_config.defaultPassword"));
				
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				userService.updateInfo(request, bfData, userVO, admSession.getMngrId());
			}
			
			return JSONArray.fromObject(jsonVO).toString();
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0003"));
			return JSONArray.fromObject(jsonVO).toString();
		}
	}
	
	/**
	 * @Method Name	: aUserUnlockLogin
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자 로그인 잠금 해제
	 */
	@RequestMapping(value = "/user/unlockLogin.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aUserUnlockLogin(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "userSn", required = true) int userSn,
			@RequestParam(value = "userId", required = true) String userId,
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
			UserVO userVO = new UserVO();
			userVO.setUserSn(userSn);
			UserVO item = userService.selectObj(userVO);
			
			if(item != null) {
				userVO.setLockCo(0);
				userVO.setLockDe("N");
				userVO.setUserSalt(item.getUserSalt());
				
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				userService.updateInfo(request, bfData, userVO, admSession.getMngrId());
			}
			
			return JSONArray.fromObject(jsonVO).toString();
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0003"));
			return JSONArray.fromObject(jsonVO).toString();
		}
	}
	
}
