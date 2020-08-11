package fscms.cmm.taglib.string;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.time.FastDateFormat;

@SuppressWarnings("serial")
public class NewDateChkTag extends BodyTagSupport {
	
	private static final FastDateFormat fdf = FastDateFormat.getInstance("yyyyMMddHHmmss");

	private Integer number;

	public int doEndTag() throws JspException {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.HOUR, -number);

		String chkDate = fdf.format(date);

		JspWriter out = pageContext.getOut();
		try {
			out.print(chkDate);
		} catch (IOException e) {
			throw new JspException(e.toString());
		}

		return EVAL_PAGE;
	}

	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}

}
