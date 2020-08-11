package fscms.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContentController {
	
	@RequestMapping(value = "/content.do")
	public String content(
			HttpServletRequest request,
			ModelMap model) {
		
		return "www/content";
	}

}
