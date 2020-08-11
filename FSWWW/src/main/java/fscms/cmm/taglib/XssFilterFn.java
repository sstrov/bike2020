package fscms.cmm.taglib;

import org.springframework.stereotype.Component;

import fscms.cmm.util.FsFuncCmmHelper;

@Component
public class XssFilterFn {
	
	/**
	 * @Method Name	: decode
	 * @작성일   		: 2019. 3. 15.
	 * @작성자   		: Edmund.J
	 * @변경이력  		:
	 * @Method 설명 	: 필터 디코더 JSTL
	 */
	public static String decode(String str) {
		return new FsFuncCmmHelper().decodeHtmlSpecialChars(str);
	}
	
	/**
	 * @Method Name	: cleanText
	 * @작성일		: 2019. 10. 1.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 모든 HTML 태그 필터링
	 */
	public static String cleanText(String str) {
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		return fsFuncCmmHelper.removeTag(fsFuncCmmHelper.decodeHtmlSpecialChars(str));
	}

}
