package fscms.mods.sso.util;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fscms.cmm.util.BrowserDetectorHelper;
import fscms.mods.mngr.util.MngrLoginHistSQL;
import fscms.mods.mngr.vo.MngrLoginHistVO;
import fscms.mods.mngr.vo.MngrVO;

public class A_SsoHelper {
	
	private static A_SsoHelper loginManager = null;
	
	@SuppressWarnings("rawtypes")
	private static Hashtable loginUsers = new Hashtable();
	
	private A_SsoHelper() {
		super();
	}
	
	public static synchronized A_SsoHelper getInstance() {
		if(loginManager == null) {
			loginManager = new A_SsoHelper();
		}
		
		return loginManager;
	}
	
	/**
	 * @Method Name	: setSession_adm
	 * @작성일   		: 2018. 11. 27.
	 * @작성자   		: Edmund.J
	 * @변경이력  		:
	 * @Method 설명 	: 세션 정보 저장
	 */
	@SuppressWarnings("unchecked")
	public void setSession_adm(HttpServletRequest request, MngrVO mngr) throws Exception {
		HttpSession session = request.getSession();
		
		loginUsers.put("adm_" + session.getId(), mngr.getMngrSn());
		
		// 로그인 내역 정보 저장
		MngrLoginHistVO mngrLoginHistVO = new MngrLoginHistVO();
		mngrLoginHistVO.setMngrSn(mngr.getMngrSn());
		mngrLoginHistVO.setSesionId(session.getId());
		mngrLoginHistVO.setLoginIp(request.getRemoteAddr());
		if(request.getHeader("user-agent") != null && request.getHeader("user-agent").indexOf("/") != -1) {
			BrowserDetectorHelper browserDetector = new BrowserDetectorHelper(request.getHeader("user-agent"));
			mngrLoginHistVO.setLoginBrwsrNm(browserDetector.getBrowserName());
			mngrLoginHistVO.setLoginBrwsrVer(String.valueOf(browserDetector.getBrowserVersion()));
			mngrLoginHistVO.setLoginPltfom(browserDetector.getBrowserPlatform());
		}
		
		// 로그인 내역 정보 저장 Class
		new MngrLoginHistSQL().insertLogin(mngrLoginHistVO);

		System.out.println("관리자 접속 수: " + getUserCount());
	}
	
	/**
	 * @Method Name	: removeSession
	 * @작성일   		: 2018. 11. 27.
	 * @작성자   		: Edmund.J
	 * @변경이력  		:
	 * @Method 설명 	: 세션 정보 삭제
	 * @param session
	 */
	public void removeSession(HttpSession session) throws Exception {
		if(session != null) {
			loginUsers.remove("adm_" + session.getId());
			
			// 로그인 내역 로그아웃 정보 저장
			MngrLoginHistVO vo = new MngrLoginHistVO();
			vo.setSesionId(session.getId());
			
			// 로그인 내역 정보 수정 Class
			new MngrLoginHistSQL().updateLogOut(vo);
			
			System.out.println("관리자 접속 수: " + getUserCount());
		}
	}
	
	/**
	 * @Method Name	: getUserCount
	 * @작성일   		: 2018. 11. 27.
	 * @작성자   		: Edmund.J
	 * @변경이력  		:
	 * @Method 설명 	: 현재 접속자
	 */
	@SuppressWarnings("rawtypes")
	public int getUserCount() {
		int count = 0;
		Enumeration e = loginUsers.keys();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			if(key.indexOf("adm_") != -1) {
				count++;
			}
		}
		return count;
	}

}
