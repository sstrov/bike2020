package fscms.cmm.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @FileName  	: SessionCookieHelper.java
 * @Project   	: FS_CMS
 * @Date      	: 2018. 11. 29.
 * @작성자		: Edmund.J
 * @변경이력    	: 
 * @프로그램 설명 	: 세션/쿠키
 */
public class SessionCookieHelper {
	
	/**
	 * @Method Name	: setSessionAttribute
	 * @작성일   		: 2018. 11. 29.
	 * @작성자   		: Edmund.J
	 * @변경이력  		:
	 * @Method 설명 	: 세션 정보 저장
	 * @param request
	 * @param key
	 * @param val
	 */
	public static void setSessionAttribute(
			HttpServletRequest request,
			String key,
			String val) {
		HttpSession session = request.getSession();
		session.setAttribute(key, val);
	}
	
	/**
	 * @Method Name	: setSessionAttribute
	 * @작성일   		: 2018. 11. 29.
	 * @작성자   		: Edmund.J
	 * @변경이력  		:
	 * @Method 설명 	: 세션 정보 저장 - Object
	 * @param request
	 * @param key
	 * @param obj
	 */
	public static void setSessionAttribute(
			HttpServletRequest request,
			String key,
			Object obj) {
		HttpSession session = request.getSession();
		session.setAttribute(key, obj);
	}
	
	/**
	 * @Method Name	: getSessionAttribute
	 * @작성일   		: 2018. 11. 29.
	 * @작성자   		: Edmund.J
	 * @변경이력  		:
	 * @Method 설명 	: 세션 정보 조회
	 * @param request
	 * @param key
	 * @return
	 */
	public static Object getSessionAttribute(
			HttpServletRequest request,
			String key) {
		HttpSession session = request.getSession();
		return session.getAttribute(key);
	}
	
	/**
	 * @Method Name	: getSessionValuesString
	 * @작성일   		: 2018. 11. 29.
	 * @작성자   		: Edmund.J
	 * @변경이력  		:
	 * @Method 설명 	: 세션 값 조회
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getSessionValuesString(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String returnVal = "";
		
		Enumeration e = session.getAttributeNames();
		while (e.hasMoreElements()) {
			String sessionKey = (String)e.nextElement();
			returnVal = returnVal + "[" + sessionKey + " : " + session.getAttribute(sessionKey) + "]";
		}
		
		return returnVal;
	}
	
	/**
	 * @Method Name	: removeSessionAttribute
	 * @작성일   		: 2018. 11. 29.
	 * @작성자   		: Edmund.J
	 * @변경이력  		:
	 * @Method 설명 	: HttpSession에 존재하는 세션을 주어진 키 값으로 삭제하는 기능
	 * @param request
	 * @param key
	 */
	public static void removeSessionAttribute(
			HttpServletRequest request,
			String key) {
		HttpSession session = request.getSession();
		session.removeAttribute(key);
	}
	
	/**
	 * @Method Name	: setCookie
	 * @작성일   		: 2018. 11. 29.
	 * @작성자   		: Edmund.J
	 * @변경이력  		:
	 * @Method 설명 	:
	 * 쿠키생성 - 입력받은 분만큼 쿠키를 유지되도록 세팅한다.
     * 쿠키의 유효시간을 5분으로 설정 =>(cookie.setMaxAge(60 * 5)
     * 쿠키의 유효시간을 10일로 설정 =>(cookie.setMaxAge(60 * 60 * 24 * 10)
	 * @param response
	 * @param cookieNm- 쿠키명
	 * @param cookieVal - 쿠키값
	 * @param minute - 지속시킬 시간(분)
	 * @throws UnsupportedEncodingException
	 */
	public static void setCookie(
			HttpServletResponse response,
			String cookieNm,
			String cookieVal,
			int minute) throws UnsupportedEncodingException {
		// 특정의 encode 방식을 사용해 캐릭터 라인을 application/x-www-form-urlencoded 형식으로 변환
		// 일반 문자열을 웹에서 통용되는 'x-www-form-urlencoded' 형식으로 변환하는 역할
		String cookieValue = URLEncoder.encode(cookieVal, "utf-8");
		
		// 쿠키생성 - 쿠키의 이름, 쿠키의 값
		Cookie cookie = new Cookie(cookieNm, cookieValue);
		
		// 2011.10.10 보안점검 후속조치
		cookie.setSecure(true);
		
		// 쿠키의 유효시간 설정
		cookie.setMaxAge(60 * minute);
		
		// response 내장 객체를 이용해 쿠키를 전송
		response.addCookie(cookie);
	}
	
	/**
	 * @Method Name	: setCookie
	 * @작성일   		: 2018. 11. 30.
	 * @작성자   		: Edmund.J
	 * @변경이력  		:
	 * @Method 설명 	: 쿠키생성 - 쿠키의 유효시간을 설정하지 않을 경우 쿠키의 생명주기는 브라우저가 종료될 때까지
	 * @param response
	 * @param cookieNm - 쿠키명
	 * @param cookieVal - 쿠키값
	 * @throws UnsupportedEncodingException
	 */
	public static void setCookie(
			HttpServletResponse response,
			String cookieNm,
			String cookieVal) throws UnsupportedEncodingException {
		// 특정의 encode 방식을 사용해 캐릭터 라인을 application/x-www-form-urlencoded 형식으로 변환
		// 일반 문자열을 웹에서 통용되는 'x-www-form-urlencoded' 형식으로 변환하는 역할
		String cookieValue = URLEncoder.encode(cookieVal, "utf-8");
		
		// 쿠키생성 - 쿠키의 이름, 쿠키의 값
		Cookie cookie = new Cookie(cookieNm, cookieValue);
		
		// 2011.10.10 보안점검 후속조치
		cookie.setSecure(true);
		
		// response 내장 객체를 이용해 쿠키를 전송
		response.addCookie(cookie);
	}
	
	/**
	 * @Method Name	: setCookie
	 * @작성일   		: 2018. 11. 30.
	 * @작성자   		: Edmund.J
	 * @변경이력  		:
	 * @Method 설명 	: 쿠키값 삭제 - cookie.setMaxAge(0) - 쿠키의 유효시간을 0으로 설정해 줌으로써 쿠키를 삭제하는 것과 동일한 효과
	 * @param response
	 * @param cookieNm
	 */
	public static void setCookie(
			HttpServletResponse response,
			String cookieNm) {
		// 쿠키생성 - 쿠키의 이름, 쿠키의 값
		Cookie cookie = new Cookie(cookieNm, null);
		
		// 2011.10.10 보안점검 후속조치
		cookie.setSecure(true);
		
		// 쿠키의 유효시간 설정
		cookie.setMaxAge(0);
		
		// response 내장 객체를 이용해 쿠키를 전송
		response.addCookie(cookie);
	}
	
	/**
	 * @Method Name	: getCookie
	 * @작성일   		: 2018. 11. 30.
	 * @작성자   		: Edmund.J
	 * @변경이력  		:
	 * @Method 설명 	: 쿠키값 사용 - 쿠키값을 읽어들인다.
	 * @param request
	 * @param cookieNm
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getCookie(
			HttpServletRequest request,
			String cookieNm) throws UnsupportedEncodingException {
		// 한 도메인에서 여러 개의 쿠키를 사용할 수 있기 때문에 Cookie[] 배열이 반환
		// Cookie를 읽어서 Cookie 배열로 반환
		Cookie[] cookies = request.getCookies();
		
		if(cookies == null) {
			return "";
		}
		
		String cookieValue = null;
		
		// 입력받은 쿠키명으로 비교해서 쿠키값을 얻어낸다.
		for(int i=0; i<cookies.length; i++) {
			if(cookieNm.equals(cookies[i].getName())) {
				// 특별한 encode 방식을 사용해 application/x-www-form-urlencoded 캐릭터 라인을 디코드
				// URLEncoder로 인코딩된 결과를 디코딩하는 클래스
				cookieValue = URLDecoder.decode(cookies[i].getValue(), "utf-8");
				break;
			}
		}
		
		return cookieValue;
	}

}
