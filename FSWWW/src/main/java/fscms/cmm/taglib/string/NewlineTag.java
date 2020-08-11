package fscms.cmm.taglib.string;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
public class NewlineTag extends BodyTagSupport {
	
	Logger logger = LogManager.getLogger(NewlineTag.class);
	
	private String value = "";
	public int doEndTag() throws JspException {
		if(value == null || value.trim().length() == 0) {
			if(bodyContent == null)
				return EVAL_PAGE;
			value = bodyContent.getString();
		}

		value = value.replaceAll("\n", "<br/>");

		try {
			pageContext.getOut().print(value);
		} catch (IOException e) {
			logger.error("페이지 설정 오류");
		}

		value = "";
		return EVAL_PAGE;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
