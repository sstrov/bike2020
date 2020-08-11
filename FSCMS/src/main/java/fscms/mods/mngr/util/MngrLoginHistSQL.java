package fscms.mods.mngr.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import fscms.cmm.util.FsFuncCmmHelper;
import fscms.cmm.util.PropConnHelper;
import fscms.mods.mngr.vo.MngrLoginHistVO;

public class MngrLoginHistSQL {
	
	/**
	 * @throws NamingException 
	 * @Method Name	: insertLogin
	 * @작성일		: 2019. 10. 8.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 로그인 정보 저장
	 */
	public void insertLogin(MngrLoginHistVO vo) throws IOException, ClassNotFoundException, SQLException, NamingException {
		PropConnHelper propConnHelper = new PropConnHelper();
		
		String url = propConnHelper.getConn("globals", "Globals.Url");
		String id  = propConnHelper.getConn("globals", "Globals.UserName");
		String pw  = propConnHelper.getConn("globals", "Globals.Password");
		
		Connection conn         = null;
		//Statement stat          = null;
		PreparedStatement pStmt = null;
		Context initialContext = new InitialContext();
		
		try {
			/*Class.forName(propConnHelper.getConn("globals", "Globals.DriverClassName"));
			conn = DriverManager.getConnection(url, id, pw);*/
			//stat = conn.createStatement();
			DataSource datasource = (DataSource)initialContext.lookup(propConnHelper.getConn("globals", "Globals.jndiJavaName"));
			conn = datasource.getConnection();
			
			String query = "INSERT INTO FS_MNGR_LOGIN_HIST					"
					+ "		(MNGR_SN, SESION_ID, LOGIN_IP,					"
					+ "		LOGIN_BRWSR_NM, LOGIN_BRWSR_VER, LOGIN_PLTFOM,	"
					+ "		LOGIN_BGNDE)									"
					+ "	VALUES												"
					+ "		(?, ?, ?,										"
					+ "		?, ?, ?,										"
					+ "		NOW())											";
			
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, vo.getMngrSn());
			pStmt.setString(2, vo.getSesionId());
			pStmt.setString(3, vo.getLoginIp());
			pStmt.setString(4, vo.getLoginBrwsrNm());
			pStmt.setString(5, vo.getLoginBrwsrVer());
			pStmt.setString(6, vo.getLoginPltfom());
			
			pStmt.executeUpdate();
			pStmt.close();
			conn.close();
			initialContext.close();
			//stat.close();
		} finally {
			if(pStmt != null) { pStmt.close(); }
			if(conn != null) { conn.close(); }
			if(initialContext != null) { initialContext.close(); }
			//if(stat != null) { stat.close(); }
		}
	}
	
	/**
	 * @throws NamingException 
	 * @Method Name	: updateLogOut
	 * @작성일		: 2019. 10. 8.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 로그아웃 정보 저장
	 */
	public void updateLogOut(MngrLoginHistVO vo) throws IOException, ClassNotFoundException, SQLException, NamingException {
		PropConnHelper propConnHelper = new PropConnHelper();
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		String url = propConnHelper.getConn("globals", "Globals.Url");
		String id  = propConnHelper.getConn("globals", "Globals.UserName");
		String pw  = propConnHelper.getConn("globals", "Globals.Password");
		Connection conn = null;
		/*Statement stat  = null;*/
		PreparedStatement pStmt = null;
		
		Context initialContext = new InitialContext();
		
		try {
			/*Class.forName(propConnHelper.getConn("globals", "Globals.DriverClassName"));
			conn = DriverManager.getConnection(url, id, pw);
			stat = conn.createStatement();*/
			DataSource datasource = (DataSource)initialContext.lookup(propConnHelper.getConn("globals", "Globals.jndiJavaName"));
			conn = datasource.getConnection();
			
			String date = fsFuncCmmHelper.getToDate("yyyyMMdd", null);
			
			String query = "UPDATE FS_MNGR_LOGIN_HIST SET LOGIN_ENDDE = NOW() WHERE DATE_FORMAT(LOGIN_BGNDE, '%Y%m%d') = ? AND SESION_ID = ?";
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, date);
			pStmt.setString(2, vo.getSesionId());
			
			pStmt.executeUpdate();
			pStmt.close();
			conn.close();
			initialContext.close();
			/*stat.close();*/
		} finally {
			if(pStmt != null) { pStmt.close(); }
			if(conn != null) { conn.close(); }
			if(initialContext != null) { initialContext.close(); }
		}
	}
	
	/**
	 * @throws NamingException 
	 * @Method Name	: updateLogOutInit
	 * @작성일		: 2019. 10. 8.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 로그아웃 정보 저장 - WAS 재시작 시
	 */
	public void updateLogOutInit() throws IOException, ClassNotFoundException, SQLException, NamingException {
		PropConnHelper propConnHelper = new PropConnHelper();
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		String url = propConnHelper.getConn("globals", "Globals.Url");
		String id  = propConnHelper.getConn("globals", "Globals.UserName");
		String pw  = propConnHelper.getConn("globals", "Globals.Password");
		Connection conn = null;
		//Statement stat  = null;
		PreparedStatement pStmt = null;
		
		Context initialContext = new InitialContext();
		
		try {
			/*Class.forName(propConnHelper.getConn("globals", "Globals.DriverClassName"));
			conn = DriverManager.getConnection(url, id, pw);
			stat = conn.createStatement();*/
			DataSource datasource = (DataSource)initialContext.lookup(propConnHelper.getConn("globals", "Globals.jndiJavaName"));
			conn = datasource.getConnection();
			
			String date = fsFuncCmmHelper.getToDate("yyyyMM", null) + "01";
			
			String query = "UPDATE FS_MNGR_LOGIN_HIST SET LOGIN_ENDDE = NOW() WHERE DATE_FORMAT(LOGIN_BGNDE, '%Y%m%d') >= ? AND LOGIN_ENDDE IS NULL";
			
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, date);
			pStmt.executeUpdate();
			pStmt.close();
			conn.close();
			initialContext.close();
			//stat.close();
		} finally {
			if(pStmt != null) { pStmt.close(); }
			if(conn != null) { conn.close(); }
			if(initialContext != null) { initialContext.close(); }
		}
	}

}
