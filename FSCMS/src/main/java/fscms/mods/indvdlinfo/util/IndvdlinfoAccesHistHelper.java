package fscms.mods.indvdlinfo.util;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import fscms.cmm.util.BrowserDetectorHelper;
import fscms.mods.indvdlinfo.service.IndvdlinfoAccesHistService;
import fscms.mods.indvdlinfo.vo.IndvdlinfoAccesHistVO;

@Component
public class IndvdlinfoAccesHistHelper {
	
	@Resource(name = "indvdlinfoAccesHistService")
	private IndvdlinfoAccesHistService indvdlinfoAccesHistService;
	
	/**
	 * @throws UnsupportedEncodingException 
	 * @Method Name	: setInsert
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 개인정보_접근_이력 정보 저장
	 */
	public void setInsert(
			HttpServletRequest request,
			String userId,
			String mngrId,
			String cn,
			Integer dataChghstSn) throws Exception {
		
		IndvdlinfoAccesHistVO indvdlinfoAccesHistVO = new IndvdlinfoAccesHistVO();
		indvdlinfoAccesHistVO.setDataChghstSn(dataChghstSn);
		indvdlinfoAccesHistVO.setUserId(userId);
		indvdlinfoAccesHistVO.setMngrId(mngrId);
		indvdlinfoAccesHistVO.setAccesHistCn(cn);
		indvdlinfoAccesHistVO.setRegistIp((request != null)? request.getRemoteAddr() : "127.0.0.1");
		
		if(request != null && request.getHeader("user-agent") != null
				&& request.getHeader("user-agent").indexOf("/") != -1) {
			
			BrowserDetectorHelper browserDetector = new BrowserDetectorHelper(request.getHeader("user-agent"));
			
			indvdlinfoAccesHistVO.setRegistPltfom(browserDetector.getBrowserPlatform());
			indvdlinfoAccesHistVO.setRegistBrwsr(browserDetector.getBrowserName());
		}
		indvdlinfoAccesHistService.insertInfo(indvdlinfoAccesHistVO);
	}

}
