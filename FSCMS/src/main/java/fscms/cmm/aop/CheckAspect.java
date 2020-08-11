package fscms.cmm.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import egovframework.com.cmm.util.EgovCryptoARIAHelper;
import fscms.cmm.exception.AdminSessionNotFoundException;
import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.JsonConnHelper;
import fscms.cmm.util.PropConnHelper;
import fscms.mods.mngr.service.MngrLoginHistService;
import fscms.mods.mngr.service.MngrService;
import fscms.mods.mngr.util.MngrMenuHelper;
import fscms.mods.mngr.vo.MngrLoginHistVO;
import fscms.mods.mngr.vo.MngrMenuVO;
import fscms.mods.mngr.vo.MngrVO;
import fscms.mods.sso.util.A_SsoHelper;

@Aspect
@Component
public class CheckAspect {
	
	Logger logger = LogManager.getLogger(CheckAspect.class);
	
	private static A_SsoHelper loginManager = A_SsoHelper.getInstance();
	
	@Resource(name = "mngrMenuHelper")
	private MngrMenuHelper mngrMenuHelper;
	
	@Resource(name = "mngrLoginHistService")
	private MngrLoginHistService mngrLoginHistService;
	
	@Resource(name = "mngrService")
	private MngrService mngrService;
	
	@Resource(name = "egovCryptoARIAHelper")
	private EgovCryptoARIAHelper egovCryptoARIAHelper;
	
	@Before("within(@org.springframework.stereotype.Controller *)")
	public void beforeSessionCheck(JoinPoint joinPoint) throws Throwable {
		
		PropConnHelper propConnHelper = new PropConnHelper();
		JsonConnHelper jsonConnHelper = new JsonConnHelper();
		
		Object[] args = joinPoint.getArgs();
		for (Object obj : args) {
			if (obj instanceof HttpServletRequest) {
				HttpServletRequest request = (HttpServletRequest) obj;
			
				String pageName = request.getRequestURI();
				
				// 관리자 첫번째 세그먼트
				String admUrl = propConnHelper.getConn("fs_config", "Fs_config.admUrl");
				
				if(StringUtils.startsWith(pageName, "/" + admUrl)) {
					// 사이트 관리자
					
					JSONObject jsonObject = jsonConnHelper.getJsonFile("/site");
					String ipBlockUseAt   = (String) jsonObject.get("mngrIpUseAt");
					
					// 무조껀 차단 항목
					String ipNones = (String) jsonObject.get("mngrIpNones");
					
					String[] ipArr  = ipNones.split(",");
					boolean ipAt    = false;
					if(ipArr != null && ipArr.length > 0) {
						for (String ip : ipArr) {
							if(StringUtils.isEmpty(ip)) {
								continue;
							}
							
							if(request.getRemoteAddr().indexOf(ip) != -1) {
								jsonObject.clear();
								logger.info("아이피 접근 차단 : 사이트관리자 (오류코드 : C0031)");
								throw new WrongApproachException("C0031");
							}
						}
					}
					
					if(StringUtils.equals("Y", ipBlockUseAt)) {
						// 아이피 차단 사용일때만 실행
						String ipBlocks = (String) jsonObject.get("mngrIpBlocks");
						jsonObject.clear();
						ipArr  = ipBlocks.split(",");
						ipAt    = false;
						if(ipArr != null && ipArr.length > 0) {
							for (String ip : ipArr) {
								if(StringUtils.isEmpty(ip)) {
									continue;
								}
								
								if(request.getRemoteAddr().indexOf(ip) != -1) {
									ipAt = true;
								}
							}
						}
						
						if(!ipAt) {
							logger.info("아이피 접근 차단 : 사이트관리자 (오류코드 : C0031)");
							throw new WrongApproachException("C0031");
						}
					}
					
					if(!StringUtils.startsWith(pageName, "/" + admUrl + "/sso")) {
						MngrVO admSession = (MngrVO) request.getSession().getAttribute("admSession");
						
						if(admSession == null || admSession.getMngrSn() == null) {
							String sso_id = (String) request.getSession().getAttribute("sso_id");
							
							if(StringUtils.isNotEmpty(sso_id)) {
								// 내부 SSO 셋팅값이 넘어오면 세션 생성
								MngrVO mngrVO = new MngrVO();
								mngrVO.setMngrId(egovCryptoARIAHelper.encrypt(sso_id));
								mngrVO.setMngrSsoAt("Y");
								MngrVO item = mngrService.selectObj(mngrVO);
								
								if(item != null) {
									request.getSession(false).invalidate();
									loginManager.setSession_adm(request, item);
									request.getSession().setAttribute("admSession", item);
									admSession = (MngrVO) request.getSession().getAttribute("admSession");
								}
							}
						}
						
						if(admSession == null || admSession.getMngrSn() == null) {
							if(request.getHeader("accept").indexOf("application/json") == -1) {
								logger.info("관리자 세션이 만료 되었습니다.");
								throw new AdminSessionNotFoundException("A0021");
							}
						} else {
							
							SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
							Date now = new Date();
							
							MngrLoginHistVO mngrLoginHistVO = new MngrLoginHistVO();
							mngrLoginHistVO.setMngrSn(admSession.getMngrSn());
							mngrLoginHistVO.setSesionId(request.getSession().getId());
							mngrLoginHistVO.setLoginBgnde(sdf.format(now).substring(0, 7) + "-01");
							mngrLoginHistVO.setLoginEndde(sdf.format(now));
							MngrLoginHistVO mngrLoginHist = mngrLoginHistService.selectObj(mngrLoginHistVO);
							
							if(mngrLoginHist == null || StringUtils.isNotEmpty(mngrLoginHist.getLoginEndde())) {
								// 데이터가 없거나 종료일자가 있으면 종료된 세션처리
								request.getSession().invalidate();
								request.getSession().setAttribute("admSession", null);
								
								logger.info("관리자 세션이 만료 되었습니다.");
								throw new AdminSessionNotFoundException("A0021");
							}
							
							// 관리자 메뉴키 제외목록 처리
							boolean excAt = true;
							String except = new PropConnHelper().getConn("fs_config", "Fs_config.admKeyExcept");
							String[] exceptList = except.split(";");
							
							if(exceptList != null && exceptList.length > 0) {
								for (String exc : exceptList) {
									exc = exc.trim();
									if(StringUtils.isEmpty(exc)) {
										continue;
									}
									
									if(pageName.indexOf(exc) != -1) {
										excAt = false;
									}
								}
							}
							
							if(excAt) {
								String key = request.getParameter("key");
								if(StringUtils.isEmpty(key)) {
									// 키가 없으면 잘못된 접근으로 경고
									logger.info("관리자 KEY 누락 (오류코드 : D0001)");
									throw new WrongApproachException("D0001");
								}
								
								// 현재 메뉴 정보
								MngrMenuVO mngrMenuVO = mngrMenuHelper.getJson(key);
								if(mngrMenuVO == null) {
									logger.info("관리자 KEY 누락 (오류코드 : D0001)");
									throw new WrongApproachException("D0001");
								}
								
								if(StringUtils.equals(mngrMenuVO.getUseAt(), "N")) {
									// 메뉴 사용안함 오류
									logger.info("사용되지 않는 메뉴 입니다. (오류코드 : D0001)");
									throw new WrongApproachException("D0001");
								}
							}
						}
					} else {
						// 사용자 체크
					}
				}
			}
		}
	}

}
