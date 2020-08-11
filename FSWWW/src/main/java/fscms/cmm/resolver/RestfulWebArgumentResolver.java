package fscms.cmm.resolver;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import fscms.cmm.exception.WrongApproachException;
import fscms.mods.user.vo.UserVO;

public class RestfulWebArgumentResolver implements WebArgumentResolver {
	
	static Logger logger = LogManager.getLogger(RestfulWebArgumentResolver.class);
	
	@Override
	public Object resolveArgument(MethodParameter arg0, NativeWebRequest arg1) {
		HttpServletRequest request = (HttpServletRequest) arg1.getNativeRequest();
		
		String paramName = arg0.getParameterName();
		if(StringUtils.isNotEmpty(paramName)) {
			
			// 메뉴 키
			if(StringUtils.equals(paramName, "key")) {
				String key = (String) request.getParameter("key");
				return key;
			}
			
			if(StringUtils.equals(paramName, "userSession")) {
				// 사용자 세션 정보 조회
				UserVO userSession = (UserVO) request.getSession().getAttribute("userSession");
				return userSession;
			}
			
			// 리퍼러 체크 - 외부 연결 차단
			if(StringUtils.equals(paramName, "refAt")) {
				String ref = request.getHeader("referer");
				
				if(StringUtils.isEmpty(ref)) {
					logger.error("잘못된 접근");
					throw new WrongApproachException("D0002");
				}
				
				ref = ref.replaceAll("(?i:https?://([^/]+)/.*)", "$1");
				if(ref.indexOf(request.getServerName()) == -1) {
					// 정보가 다르면 사이트 표시하지 않음
					logger.error("잘못된 접근");
					throw new WrongApproachException("D0001");
				}
				
				return true;
			}
			
		}
		return UNRESOLVED;
	}

}