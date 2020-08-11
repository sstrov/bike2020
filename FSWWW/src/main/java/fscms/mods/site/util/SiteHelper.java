package fscms.mods.site.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.JsonConnHelper;
import fscms.mods.site.service.SiteService;
import fscms.mods.site.vo.SiteVO;

@Component
public class SiteHelper {
	
	static Logger logger = LogManager.getLogger(SiteHelper.class);
	
	@Resource(name = "siteService")
	private SiteService siteService;
	
	/**
	 * @Method Name	: getSite
	 * @작성일		: 2019. 10. 8.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사이트 정보 가져오기
	 */
	public SiteVO getSite() throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {
		JSONObject jsonObject = new JsonConnHelper().getJsonFile("/site");
		
		SiteVO siteVO = new SiteVO();
		if(jsonObject == null) {
			logger.error("사이트 설정파일이 없습니다. (오류코드 : C0021)");
			throw new WrongApproachException("C0021");
		} else {
			String roleKey  = (String) jsonObject.get("roleKey");
			String openDe = (String) jsonObject.get("openDe");
			
			siteVO.setSiteNm((String) jsonObject.get("name"));
			siteVO.setRoleSn((StringUtils.isNotEmpty(roleKey) && !StringUtils.equals(roleKey, "null"))? Integer.parseInt(roleKey) : null);
			siteVO.setOpenDe((StringUtils.isNotEmpty(openDe) && !StringUtils.equals(openDe, "null"))? openDe : null);
			siteVO.setSnsUseAt((String) jsonObject.get("snsAt"));
			jsonObject.clear();
		}
		
		return siteVO;
	}
	
}
