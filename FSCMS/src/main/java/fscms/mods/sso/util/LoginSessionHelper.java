package fscms.mods.sso.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginSessionHelper implements HttpSessionListener {
	
	Logger logger = LogManager.getLogger(LoginSessionHelper.class);
	
	private static A_SsoHelper a_SsoHelper = A_SsoHelper.getInstance();

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// 세션 생성
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// 세션 종료
		try {
			a_SsoHelper.removeSession(event.getSession());
		} catch (Exception e) {
			logger.error("세션초기화 오류");
		}
	}

}
