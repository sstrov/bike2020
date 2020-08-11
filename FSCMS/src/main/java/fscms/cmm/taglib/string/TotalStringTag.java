package fscms.cmm.taglib.string;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

@SuppressWarnings("serial")
public class TotalStringTag extends BodyTagSupport {
	
	private int len = 0;
	private String tail;
	private String value;
	private boolean xss = true;
	private boolean newline = false;
	private String searchWord = "";
	private String highlightColor = "";

	public int doEndTag() throws JspException {
		if(value == null || value.trim().length() == 0) {
			if(bodyContent == null)
				return EVAL_PAGE;

			value = bodyContent.getString();
		}

		if(value == null || value.trim().length() == 0)
			return 	EVAL_PAGE;

		value = value.trim();

		String tmpTail = tail;
		if (tail == null || tail.trim().length() == 0)
			tmpTail = "..";

		char a;
		int i = 0;
		int realLen = 0;

		for (i = 0; i < value.length() && realLen < len; i++) {
			a = value.charAt(i);
			realLen += ((a & 0xFF00) == 0)? 1 : 2;
		}

		String returnValue = value.substring(0, i);

		value = (value.length() > returnValue.length())? returnValue + tmpTail
				: returnValue;

		if(xss) {
			value = value.replaceAll("<", "&lt;");
			value = value.replaceAll(">", "&gt;");
		}

		if(searchWord != null && searchWord.length() > 0) {
			if(highlightColor == null || highlightColor.length() == 0)
				highlightColor="#FF0000";
			value = value.replaceAll(searchWord, "<font color='" +
				highlightColor + "'>" + searchWord + "</font>");
		}

		if(newline)
			value = value.replaceAll("\n", "<br/>");

		try {
			pageContext.getOut().print(value);
		} catch (IOException e) {
			throw new JspException(e.toString());
		}
		value = "";
		return EVAL_PAGE;
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

	public boolean isXss() {
		return xss;
	}

	public void setXss(boolean xss) {
		this.xss = xss;
	}


	public String getSearchWord() {
		return searchWord;
	}


	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}


	public String getHighlightColor() {
		return highlightColor;
	}


	public void setHighlightColor(String highlightColor) {
		this.highlightColor = highlightColor;
	}


	public boolean isNewline() {
		return newline;
	}


	public void setNewline(boolean newline) {
		this.newline = newline;
	}

}
