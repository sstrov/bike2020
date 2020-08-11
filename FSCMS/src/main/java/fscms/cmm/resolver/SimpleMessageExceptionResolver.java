package fscms.cmm.resolver;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import fscms.cmm.util.PropConnHelper;
import fscms.mods.error.service.ErrorHistService;
import fscms.mods.error.vo.ErrorHistVO;

public class SimpleMessageExceptionResolver implements HandlerExceptionResolver {
	
	static Logger logger = LogManager.getLogger(SimpleMessageExceptionResolver.class);
	
	@Resource(name = "errorHistService")
	private ErrorHistService errorHistService;
	
	private String viewName;
	private Properties exceptionMappings;

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	public void setExceptionMappings(Properties exceptionMappings) {
		this.exceptionMappings = exceptionMappings;
	}
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception exception) {
		
		StringWriter errors = new StringWriter();
		//exception.printStackTrace(new PrintWriter(errors));
		//exception.printStackTrace();
		
		String exceptionClassName = exception.getClass().getName();
		String messageCode = exceptionMappings.getProperty(exceptionClassName);
		
		PropConnHelper propConnHelper = new PropConnHelper();
		
		ModelAndView mav = new ModelAndView();
		this.viewName = "error/error";
		
		if(messageCode == null) {
			messageCode = "errors.UncaughtException";
			this.viewName = "error/error";
		}
		
		request.setAttribute("javax.servlet.error.exception", exception);
		
		// 관리자 세션정보가 없을시 로그인 페이지 이동
		if(StringUtils.indexOf(exceptionClassName, "AdminSessionNotFoundException") != -1) {
			try {
				String admUrl = propConnHelper.getConn("fs_config", "Fs_config.admUrl");
				return new ModelAndView("redirect:/" + admUrl + "/sso/form.do");
			} catch (IOException e) {
				return new ModelAndView("redirect:/index.do");
			}
		}

		// 잘못된 접근
		if(StringUtils.indexOf(exceptionClassName, "WrongApproachException") != -1) {
			messageCode = "errors.WrongApproachException";
			mav.addObject("backFlag", "Y");
		}
		
		exception.printStackTrace();
		
		try {
			String msg = exception.getMessage();
			if(StringUtils.isNotEmpty(msg) && msg.length() > 200) {
				msg = msg.substring(0, 200);
			}
			// 오류 이력 정보 저장
			if(StringUtils.isNotEmpty(msg)) {
				ErrorHistVO errorHistVO = new ErrorHistVO();
				errorHistVO.setErrorMssage(msg);
				errorHistVO.setErrorCn(errors.toString());
				errorHistVO.setErrorItnadr(request.getHeader("referer"));
				errorHistVO.setRegistIp((request != null)? request.getRemoteAddr() : "127.0.0.1");
				errorHistService.insertInfo(errorHistVO);
			}
		} catch (Exception e1) {
			logger.error("오류_이력 저장 오류");
		}
		
		mav.setViewName("error/error");
		mav.addObject("messageCode", messageCode);
		mav.addObject("exception", exception);
		return mav;
	}

}