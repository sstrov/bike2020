package fscms.mods.data.util;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import fscms.cmm.util.BrowserDetectorHelper;
import fscms.mods.data.service.DataChghstService;
import fscms.mods.data.vo.DataChghstVO;

@Component
public class DataChghstHelper {
	
	@Resource(name = "dataChghstService")
	private DataChghstService dataChghstService;
	
	/**
	 * @throws UnsupportedEncodingException 
	 * @Method Name	: setInsert
	 * @작성일		: 2019. 10. 4.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 데이터_변경이력 정보 저장
	 */
	public Integer setInsert(
			HttpServletRequest request,
			String tableNm,
			String bfchgData,
			String aftchData,
			String userId,
			String mngrId) throws Exception {
		
		if(StringUtils.isEmpty(bfchgData)
				|| StringUtils.isEmpty(aftchData)
				|| !StringUtils.equals(bfchgData, aftchData)) {
			
			DataChghstVO dataChghstVO = new DataChghstVO();
			dataChghstVO.setUserId(userId);
			dataChghstVO.setMngrId(mngrId);
			dataChghstVO.setTableNm(tableNm);
			dataChghstVO.setBfchgData(bfchgData);
			dataChghstVO.setAftchData(aftchData);
			dataChghstVO.setRegistIp((request != null)? request.getRemoteAddr() : "127.0.0.1");
			
			if(request != null && request.getHeader("user-agent") != null
					&& request.getHeader("user-agent").indexOf("/") != -1) {
				
				BrowserDetectorHelper browserDetector = new BrowserDetectorHelper(request.getHeader("user-agent"));
				
				dataChghstVO.setRegistPltfom(browserDetector.getBrowserPlatform());
				dataChghstVO.setRegistBrwsr(browserDetector.getBrowserName());
			}
			
			dataChghstService.insertInfo(dataChghstVO);
			return dataChghstVO.getDataChghstSn();
		}
		
		return null;
	}

}
