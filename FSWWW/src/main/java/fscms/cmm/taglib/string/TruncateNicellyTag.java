package fscms.cmm.taglib.string;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

@SuppressWarnings("serial")
public class TruncateNicellyTag extends BodyTagSupport {
	
	private int len;
	private String tail;
	private String value;

	public int doEndTag() throws JspException {
		if(value == null || value.trim().length() == 0) {
			if(bodyContent == null)
				return EVAL_PAGE;
			value = bodyContent.getString();
		}

		if(value == null || value.trim().length() == 0)
			return 	EVAL_PAGE;

		value = value.trim();
		JspWriter out = pageContext.getOut();
		String tmpTail = tail;

		if (tail == null || tail.trim().length()==0)
			tmpTail = "..";

		char a;
		int i = 0;
		int realLen = 0;

		for (i = 0; i < value.length() && realLen < len; i++) {
			a = value.charAt(i);
			realLen += ((a & 0xFF00) == 0)? 1 : 2;
		}

		String returnValue = value.substring(0, i);
		try {
			if(value.length() > returnValue.length()) {
				out.print(returnValue + tmpTail);
			} else {
				out.print(returnValue);
			}
		} catch (IOException e) {
			throw new JspException(e.toString());
		}

		reset();
		return EVAL_PAGE;
	}

	public void reset() {
		value = "";
	}

	public static int realLength(String s) {
		return s.getBytes().length;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTail() {
		return tail;
	}

	public void setTail(String tail) {
		this.tail = tail;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

}
