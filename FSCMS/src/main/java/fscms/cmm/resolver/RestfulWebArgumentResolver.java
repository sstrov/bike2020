package fscms.cmm.resolver;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.FsFuncCmmHelper;
import fscms.cmm.util.PropConnHelper;
import fscms.mods.mngr.vo.MngrVO;

public class RestfulWebArgumentResolver implements WebArgumentResolver {
	
	static Logger logger = LogManager.getLogger(RestfulWebArgumentResolver.class);
	
	private static FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
	private static PropConnHelper propConnHelper = new PropConnHelper();
	
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
			
			if(StringUtils.equals(paramName, "admSession")) {
				// 관리자 세션 정보 조회
				MngrVO admSession = (MngrVO) request.getSession().getAttribute("admSession");
				return admSession;
			}
			
			if(StringUtils.equals(paramName, "admChk")) {
				// 관리자 경로 체크
				String path    = fsFuncCmmHelper.getURISegment(request, 0);
				String admPath = null;
				try {
					admPath = propConnHelper.getConn("fs_config", "Fs_config.admUrl");
				} catch (IOException e) {
					logger.error("잘못된 접근");
					throw new WrongApproachException("B0001");
				}
				
				if(!path.equals(admPath)) {
					logger.error("잘못된 접근");
					throw new WrongApproachException("B0002");
				}
				
				return true;
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
			
			// 리퍼러 체크 - 외부 연결 차단 (ajax 전용)
			if(StringUtils.equals(paramName, "refAtAjax")) {
				String ref = request.getHeader("referer");
				
				// 관리자 세션 정보 조회
				MngrVO admSession = (MngrVO) request.getSession().getAttribute("admSession");
				
				if(admSession == null || admSession.getMngrSn() == null) {
					return false;
				}
				
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