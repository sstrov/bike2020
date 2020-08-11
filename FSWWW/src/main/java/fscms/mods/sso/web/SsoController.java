package fscms.mods.sso.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SsoController {
	
	@RequestMapping(value = "/sso/logout.do")
	public String ssoLogout(
			HttpServletRequest request,
			ModelMap model) {
		
		// 세션 초기화
		request.getSession().invalidate();
		request.getSession().setAttribute("userSession", null);
		
		return "redirect:/index.do";
	}

}
