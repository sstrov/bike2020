package fscms.cmm.aop;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CheckAspect {
	
	Logger logger = LogManager.getLogger(CheckAspect.class);
	
	@Before("within(@org.springframework.stereotype.Controller *)")
	public void beforeSessionCheck(JoinPoint joinPoint) throws Throwable {
		
		Object[] args = joinPoint.getArgs();
		for (Object obj : args) {
			if (obj instanceof HttpServletRequest) {
				HttpServletRequest request = (HttpServletRequest) obj;
			
				String pageName = request.getRequestURI();
				
			}
		}
	}

}
