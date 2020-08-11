package fscms.cmm.util;

import javax.annotation.Resource;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.EgovMessageSource;

@Service("a_UtilsHelper")
public class A_UtilHelper {
	
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	/**
	 * @Method Name	: getAjaxRoleException
	 * @작성일		: 2019. 10. 10.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 
	 */
	public String getAjaxRoleException(Logger logger) {
		logger.error("접근 권한 오류 : C0041");
		
		return egovMessageSource.getMessageArgs("sso.roleNotPerm", new Object[] {"C0041"});
	}
	
	/**
	 * @Method Name	: getAjaxSsoExfired
	 * @작성일		: 2019. 10. 4.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 관리자 Ajax 조회 시 로그인 체크 여부에 따른 메시지 전달
	 */
	public String getAjaxSsoExfired(Logger logger) {
		logger.error("로그인정보 종료 : A0011");
		
		return egovMessageSource.getMessageArgs("sso.exfired", new Object[] {"A0011"});
	}
	
	/**
	 * @Method Name	: getAjaxSqlException
	 * @작성일		: 2019. 10. 4.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 관리자 Ajax 데이터 처리 오류
	 */
	public String getAjaxSqlException(Logger logger, String code) {
		logger.error("데이터 처리중 오류가 발생 하였습니다. : " + code);
		
		return egovMessageSource.getMessageArgs("sql.process.error", new Object[] {code});
	}
	
	/**
	 * @Method Name	: getAjaxSystemException
	 * @작성일		: 2019. 10. 4.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 관리자 Ajax 시스템 오류 발생
	 */
	public String getAjaxSystemException(Logger logger, String code) {
		logger.error("시스템 오류가 발생하였습니다. : " + code);
		
		return egovMessageSource.getMessageArgs("sys.error", new Object[] {code});
	}

}
