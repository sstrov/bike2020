package fscms.cmm.taglib.string;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

@SuppressWarnings("serial")
public class MarkTag extends BodyTagSupport {
	
	private String value = "";
	private String searchWord = "";
	private String highlightColor = "";

	public int doEndTag() throws JspException {

		if(searchWord == null || searchWord.length() == 0) {
			return EVAL_PAGE;
		}
		if(value == null || value.trim().length() == 0) {
			if(bodyContent == null)
				return EVAL_PAGE;
			value = bodyContent.getString();
		}

		if(highlightColor == null || highlightColor.length() == 0) {
			highlightColor = "#FF0000";
		}

		if(searchWord != null && searchWord.length() > 0) {
			value = value.replaceAll(searchWord, "<font color='" +
					highlightColor + "'>" + searchWord + "</font>");
		}

		try {
			pageContext.getOut().print(value);
		} catch (IOException e) {
			throw new JspException(e.toString());
		}

		value = "";
		return EVAL_PAGE;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
