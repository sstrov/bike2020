package fscms.cmm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public class FsFuncCmmHelper {
	
	/**
	 * @Method Name	: getURISegment
	 * @작성일		: 2019. 10. 2.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: URI 세그먼트 정보를 가져온다.
	 *                idx : 값의 번째에 해당하는 세그먼트 정보
	 */
	public String getURISegment(HttpServletRequest request, int idx) {
		Path path = Paths.get(request.getRequestURI());
		return path.getName(idx).toString();
	}
	
	/**
	 * @Method Name	: getAppAbsolutePath
	 * @작성일   		: 2019. 3. 14.
	 * @작성자   		: Edmund.J
	 * @변경이력  		:
	 * @Method 설명 	: 웹 AP 절대경로 조회
	 */
	public String getAppAbsolutePath() throws IOException {
		return new PropConnHelper().getConn("uploadPath", "uploadPath.defaultPath");
	}
	
	/**
	 * @Method Name	: getSerialNo
	 * @작성일		: 2019. 10. 4.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 일련번호 생성  (날짜 + 타임스템프)
	 */
	public String getSerialNo() throws InterruptedException {
		Thread.sleep(10);
		
		SimpleDateFormat sdf = new SimpleDateFormat ("yyMMdd", Locale.KOREA);
		Date nowDate = new Date();

		return sdf.format(nowDate) + ("" + System.currentTimeMillis()).substring(5, 12);
	}
	
	/**
	 * @Method Name	: checkFileExists
	 * @작성일		: 2019. 10. 7.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 파일 존재여부 확인
	 */
	public boolean checkFileExists(File file) {
		if(file.exists()) {
			if(file.isFile() && file.canRead()) {
				// 파일이 있으며, 읽을 수 있을때 true
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * @Method Name	: getRegPattern
	 * @작성일		: 2019. 10. 7.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 정규식 패턴 가져오기
	 */
	public String getRegPattern(String type, Integer num) {
		String rtn = "";
		
		if("active".equals(type)) {
			rtn = "<!--@active-->([^.]+)<!--@active-->";
		} else if("child".equals(type)) {
			rtn = "<!--@child-->([^.]+)<!--@child-->";
		} else if("childCls".equals(type)) {
			rtn = "<!--@childCls-->([^.]+)<!--@childCls-->";
		} else if("icon".equals(type)) {
			rtn = "<!--@icon-->([^.]+)<!--@icon-->";
		} else if(type.equals(num + "depth")) {
			rtn = "<!--@" + num + "depth-->([^*]+)<!--@" + num + "depth-->";
		} else if(type.equals(num + "depth_l")) {
			rtn = "<!--@" + num + "depthList-->([^*]+)<!--@" + num + "depthList-->";
		} else if("naviMenu".equals(type)) {
			rtn = "<!--@naviMenu-->([^.]+)<!--@naviMenu-->";
		}
		
		return rtn;
	}
	
	public String cleanXSS(String s) {
		s = s.replaceAll("\t", "");
		s = s.replaceAll("\"", "&quot;");
		s = s.replaceAll("\'", "&quos;");
		s = s.replaceAll("<", "&lt;");
		s = s.replaceAll(">", "&gt;");
		//s = s.replaceAll("eval\\((.*)\\)", "");
		//s = s.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		//s = s.replaceAll("script", "");
		return s;
	}
	
	/**
	 * @Method Name	: decodeHtmlSpecialChars
	 * @작성일		: 2019. 10. 23.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: XSS 필터 제거
	 */
	public String decodeHtmlSpecialChars(String s) {
		s = s.replaceAll("&amp;", "&");
		s = s.replaceAll("&quot;", "\"");
		s = s.replaceAll("&quos;", "\'");
		s = s.replaceAll("&lt;", "<");
		s = s.replaceAll("&gt;", ">");
		return s;
	}
	
	/**
	 * 모든 HTML 태그를 제거하고 반환한다.
	 * 
	 * @param html
	 */
	public String removeTag(String html) {
		return html.replaceAll("<(/)?([a-zA-Z0-9]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
	}
	
	/**
	 * @Method Name	: getToDate
	 * @작성일		: 2019. 10. 8.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 날짜 정보 가져오기
	 */
	public String getToDate(String pattern, Long date) {
		SimpleDateFormat sdf  = new SimpleDateFormat(pattern);
		
		Date now = new Date();
		if(date != null) {
			now = new Date(date);
		}
		
		return sdf.format(now);
	}
	
	public int getMaxDay(String date) throws ParseException {
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
		
		Date tdate = sdf.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(tdate);
		
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * @Method Name	: getFileRead
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 파일 읽기 - 인코딩 설정 (기본 UTF-8)
	 */
	public Reader getFileRead(File file) throws IOException {
		if(file.exists()) {
			return new InputStreamReader(new FileInputStream(file), new PropConnHelper().getConn("fs_config", "Fs_config.charset"));
		} else {
			return null;
		}
	}
	
	/**
	 * @Method Name	: setFileWriter
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 파일 쓰기 - 인코딩 설정 (기본 UTF-8)
	 */
	public Writer setFileWriter(String path) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		return new OutputStreamWriter(new FileOutputStream(path), new PropConnHelper().getConn("fs_config", "Fs_config.charset"));
	}

}
