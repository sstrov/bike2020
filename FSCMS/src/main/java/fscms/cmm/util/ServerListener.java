package fscms.cmm.util;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fscms.mods.mngr.util.MngrLoginHistSQL;

public class ServerListener implements ServletContextListener {
	
	Logger logger = LogManager.getLogger(ServerListener.class);
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println(".....start: 로그인 세션 정보 초기화.....");
		try {
			new MngrLoginHistSQL().updateLogOutInit();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			logger.error("세션 초기화 오류");
		} catch (NamingException e) {
			logger.error("세션 초기화 오류 : JNDI");
		}
		System.out.println(".....end: 로그인 세션 정보 초기화.....");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println(".....start: 로그인 세션 정보 초기화.....");
		try {
			new MngrLoginHistSQL().updateLogOutInit();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			logger.error("세션 초기화 오류");
		} catch (NamingException e) {
			logger.error("세션 초기화 오류 : JNDI");
		}
		System.out.println(".....end: 로그인 세션 정보 초기화.....");
	}

}
