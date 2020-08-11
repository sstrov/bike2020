package fscms.cmm.taglib.string;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;

import fscms.cmm.util.FsFuncCmmHelper;

@SuppressWarnings("serial")
public class RemoveTag extends BodyTagSupport {
	
	private String value;
	
	public int doEndTag() throws JspException {
		if(StringUtils.isEmpty(value)) {
			return EVAL_PAGE;
		}

		// xss 필터 적용 해제
		value = new FsFuncCmmHelper().decodeHtmlSpecialChars(value);
		// 정규화를 이용하여 Html 태그 제거
		value = value.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
		
		JspWriter out = pageContext.getOut();
		try {
			out.print(value);
		} catch (IOException e) {
			throw new JspException(e.toString());
		}
		
		return EVAL_PAGE;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
