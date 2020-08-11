package fscms.mods.sso.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.util.EgovCryptoARIAHelper;
import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.util.CryptoHelper;
import fscms.cmm.util.JsonConnHelper;
import fscms.cmm.util.PropConnHelper;
import fscms.cmm.vo.JsonVO;
import fscms.mods.mngr.service.MngrLoginHistService;
import fscms.mods.mngr.service.MngrService;
import fscms.mods.mngr.vo.MngrLoginHistSearchVO;
import fscms.mods.mngr.vo.MngrLoginHistVO;
import fscms.mods.mngr.vo.MngrVO;
import fscms.mods.sso.util.A_SsoHelper;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_SsoController {
	
	static Logger logger = LogManager.getLogger(A_SsoController.class);
	
	private static A_SsoHelper loginManager = A_SsoHelper.getInstance();
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "mngrService")
	private MngrService mngrService;
	
	@Resource(name = "mngrLoginHistService")
	private MngrLoginHistService mngrLoginHistService;
	
	@Resource(name = "egovCryptoARIAHelper")
	private EgovCryptoARIAHelper egovCryptoARIAHelper;
	
	/**
	 * @Method Name	: aSsoForm
	 * @작성일		: 2019. 10. 2.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 관리자 로그인 페이지
	 */
	@RequestMapping(value = "/sso/form.do")
	public String aSsoForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			MngrVO admSession,
			ModelMap model) {
		
		if(admSession != null && StringUtils.isNotEmpty(admSession.getMngrId())) {
			return "redirect:/" + admURI + "/index.do";
		}
		
		model.addAttribute("admURI", admURI);
		model.addAttribute("refer",  "/" + admURI + "/index.do");
		return "mngr/sso/form";
	}
	
	/**
	 * @Method Name	: aSsoLogin
	 * @작성일		: 2019. 10. 4.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 관리자 로그인 확인
	 */
	@RequestMapping(value = "/sso/login.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aSsoLogin(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "userPw", required = true) String userPw,
			@RequestParam(value = "priAt", required = false) String priAt,
			Boolean refAt,
			ModelMap model) {
		
		PropConnHelper propConnHelper = new PropConnHelper();
		MngrVO mngr = new MngrVO();
		JsonVO jsonVO = new JsonVO();
		
		JSONObject jsonObject;
		try {
			jsonObject = new JsonConnHelper().getJsonFile("/site");
		} catch (IOException | org.json.simple.parser.ParseException e) {
			logger.error("설정파일 가져오기 오류 : " + e.getMessage());
			throw new WrongApproachException("C0021");
		}
		
		String idNotFound = null;
		String pwNotFound = null;
		String loginLock = null;
		try {
			idNotFound = propConnHelper.getMessage(propConnHelper.getConn("globals", "Globals.language"), "sso.id.notfound");
			pwNotFound = propConnHelper.getMessage(propConnHelper.getConn("globals", "Globals.language"), "sso.pw.notfound");
			loginLock  = propConnHelper.getMessage(propConnHelper.getConn("globals", "Globals.language"), "sso.lock");
		} catch (IOException e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSystemException(logger, "C0011"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		MngrVO mngrVO = new MngrVO();
		MngrVO item = null;
		try {
			mngrVO.setMngrId(egovCryptoARIAHelper.encrypt(userId));
			mngrVO.setMngrSsoAt("N");
			item = mngrService.selectObj(mngrVO);
		} catch (UnsupportedEncodingException e) {
			System.out.println("eeeee : " + e);
			jsonVO.setMsg(a_UtilsHelper.getAjaxSystemException(logger, "C0011"));
			return JSONArray.fromObject(jsonVO).toString();
		} catch (Exception e) {
			System.out.println("eeeee : " + e);
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		if(item == null || StringUtils.isEmpty(item.getMngrId())) {
			mngrVO.setMngrSn(null);
			jsonVO.setState("200");
			jsonVO.setMsg(idNotFound);
		} else {
			mngr        = item;
			int userKey = item.getMngrSn();
			
			String limitCntStr = (String) jsonObject.get("loginFailrLockCo");
			int limitCnt       = (StringUtils.isNotEmpty(limitCntStr) && !StringUtils.equals(limitCntStr, "0")? Integer.parseInt(limitCntStr) : 0);
			
			if(limitCnt > 0 && limitCnt <= item.getLockCo()) {
				// 차단시 시간체크하여 잠김여부 체크
				SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
	
				try {
					Date d1 = sdf.parse(item.getLockDe());
					Date d2 = new Date();
					
					long diff = d2.getTime() - d1.getTime();
					long min = diff / (1000 * 60);
					
					if(min <= 30) {
						// 30분간 잠금 설정
						jsonVO.setState("200");
						jsonVO.setMsg(loginLock);
						return JSONArray.fromObject(jsonVO).toString();
					}
				} catch (ParseException e) {
					jsonVO.setMsg(a_UtilsHelper.getAjaxSystemException(logger, "B0011"));
					return JSONArray.fromObject(jsonVO).toString();
				}
			}
			
			String encPw = null;
			try {
				encPw = CryptoHelper.encrypt(userPw, item.getMngrSalt());
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				jsonVO.setMsg(a_UtilsHelper.getAjaxSystemException(logger, "C0012"));
				return JSONArray.fromObject(jsonVO).toString();
			}
			if(!StringUtils.equals(encPw, item.getMngrPw())) {
				// 비밀번호 불일치
				mngr.setMngrSn(null);
				jsonVO.setState("200");
				jsonVO.setMsg(pwNotFound);
			}
			
			try {
				if(StringUtils.isNotEmpty(jsonVO.getState()) && StringUtils.equals(jsonVO.getState(), "200")) {
					// 일정회수 로그인 실패 시 로그인 30분간 차단
					if(limitCnt > item.getLockCo() + 1) {
						// 차단 횟수 증가
						mngrVO = new MngrVO();
						mngrVO.setMngrSn(userKey);
						mngrVO.setLockCo(item.getLockCo() + 1);
						mngrVO.setMngrSalt(item.getMngrSalt());
						
						item.setUpdtDe(null);
						String bfData = JSONArray.fromObject(item).toString();
						mngrService.updateInfo(request, bfData, mngrVO, item.getMngrId());
					} else {
						// 차단 시간 연장
						mngrVO = new MngrVO();
						mngrVO.setMngrSn(userKey);
						mngrVO.setLockCo(item.getLockCo() + 1);
						mngrVO.setLockDe("Y");
						mngrVO.setMngrSalt(item.getMngrSalt());
						
						item.setUpdtDe(null);
						String bfData = JSONArray.fromObject(item).toString();
						mngrService.updateInfo(request, bfData, mngrVO, item.getMngrId());
					}
				} else {
					// 차단 초기화
					mngrVO = new MngrVO();
					mngrVO.setMngrSn(userKey);
					mngrVO.setLockCo(0);
					mngrVO.setLockDe("N");
					mngrVO.setMngrSalt(item.getMngrSalt());
					
					item.setUpdtDe(null);
					String bfData = JSONArray.fromObject(item).toString();
					mngrService.updateInfo(request, bfData, mngrVO, item.getMngrId());
				}
			} catch (Exception e) {
				jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0003"));
				return JSONArray.fromObject(jsonVO).toString();
			}
			
			if(mngr.getMngrSn() != null) {
				String loginPrivateAt = (String) jsonObject.get("dplctLoginAt");
				
				if("Y".equals(loginPrivateAt)) {
					try {
						if(StringUtils.isEmpty(priAt)) {
							// 중복 로그인 방지
							MngrLoginHistSearchVO mngrLoginHistSearchVO = new MngrLoginHistSearchVO();
							mngrLoginHistSearchVO.setMngrSn(mngr.getMngrSn());
							mngrLoginHistSearchVO.setSc_privateAt("Y");
							List<MngrLoginHistVO> logList = mngrLoginHistService.selectList(mngrLoginHistSearchVO);
							
							if(logList != null && logList.size() > 0) {
								
								String priStr = "";
								for (MngrLoginHistVO log : logList) {
									priStr += ((StringUtils.isNotEmpty(priStr))? "," : "") + log.getLoginIp() + " (" + log.getLoginBgnde() + ")";
								}
								
								String msg = propConnHelper.getMessage(propConnHelper.getConn("globals", "Globals.language"), "sso.private");
								if(StringUtils.isNotEmpty(msg)) {
									msg = msg.replace("{1}", priStr);
								}
								// 중복 경고
								jsonVO.setState("200");
								jsonVO.setMsg(msg);
								return JSONArray.fromObject(jsonVO).toString();
							}
						} else {
							// 중복 제거
							MngrLoginHistVO mngrLoginHistVO = new MngrLoginHistVO();
							mngrLoginHistVO.setMngrSn(mngr.getMngrSn());
							mngrLoginHistService.updateEDate(mngrLoginHistVO);
						}
					} catch (Exception e) {
						jsonVO.setMsg(a_UtilsHelper.getAjaxSystemException(logger, "C0032"));
						return JSONArray.fromObject(jsonVO).toString();
					}
				}
				
				try {
					// 관리자 정보가 있으면 제거
					SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
					Date now = new Date();
					
					MngrLoginHistVO mngrLoginHistVO = new MngrLoginHistVO();
					mngrLoginHistVO.setMngrSn(mngr.getMngrSn());
					mngrLoginHistVO.setSesionId(request.getSession().getId());
					mngrLoginHistVO.setLoginBgnde(sdf.format(now).substring(0, 7) + "-01");
					mngrLoginHistVO.setLoginEndde(sdf.format(now));
					MngrLoginHistVO mngrLoginHist = mngrLoginHistService.selectObj(mngrLoginHistVO);
					
					if(mngrLoginHist != null) {
						mngrLoginHistVO = new MngrLoginHistVO();
						mngrLoginHistVO.setMngrSn(mngrLoginHist.getMngrSn());
						mngrLoginHistService.updateEDate(mngrLoginHistVO);
					}
				
					// 사용자 정보가 있으면 세션 등록
					request.getSession(false).invalidate();
					loginManager.setSession_adm(request, mngr);
					request.getSession().setAttribute("admSession", mngr);
				} catch (ClassNotFoundException | IOException | NamingException e) {
					jsonVO.setMsg(a_UtilsHelper.getAjaxSystemException(logger, "D0001"));
					return JSONArray.fromObject(jsonVO).toString();
				} catch (Exception e) {
					jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0001"));
					return JSONArray.fromObject(jsonVO).toString();
				}
			}
		}
		
		return JSONArray.fromObject(jsonVO).toString();
	}
	
	/**
	 * @Method Name	: aSsoLogout
	 * @작성일		: 2019. 10. 8.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자 로그아웃
	 */
	@RequestMapping(value = "/sso/logout.do")
	public String aSsoLogout(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			ModelMap model) {
		
		// 세션 초기화
		request.getSession().invalidate();
		request.getSession().setAttribute("admSession", null);
		
		return "redirect:/" + admURI + "/sso/form.do";
	}

}
