package fscms.mods.site.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.FsFuncCmmHelper;
import fscms.cmm.util.JsonConnHelper;
import fscms.mods.mngr.service.MngrEstbsService;
import fscms.mods.mngr.service.MngrIpEstbsService;
import fscms.mods.mngr.vo.MngrEstbsVO;
import fscms.mods.mngr.vo.MngrIpEstbsVO;
import fscms.mods.site.service.SiteService;
import fscms.mods.site.vo.SiteVO;

@Component
public class SiteHelper {
	
	static Logger logger = LogManager.getLogger(SiteHelper.class);
	
	@Resource(name = "siteService")
	private SiteService siteService;
	
	@Resource(name = "mngrEstbsService")
	private MngrEstbsService mngrEstbsService;
	
	@Resource(name = "mngrIpEstbsService")
	private MngrIpEstbsService mngrIpEstbsService;
	
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
			jsonObject.clear();
		}
		
		return siteVO;
	}
	
	/**
	 * @Method Name	: setJson
	 * @작성일		: 2019. 10. 8.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사이트 JSON 생성
	 */
	public void setJson() throws Exception {
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		String rootPath = fsFuncCmmHelper.getAppAbsolutePath() + "/repository";
		SiteVO site     = siteService.selectObj();
		
		if(site != null) {
			// 관리자 아이피 차단 설정 구간
			MngrEstbsVO estbs              = mngrEstbsService.selectObj();
			List<MngrIpEstbsVO> mngrIpList = mngrIpEstbsService.selectList(null);
			
			String mngrIpPerm   = "";	// 허용
			String mngrIpIntrcp = "";	// 차단
			
			if(mngrIpList != null && mngrIpList.size() > 0) {
				for (MngrIpEstbsVO item : mngrIpList) {
					if(StringUtils.equals(item.getIpEstbsTy(), "1")) {
						mngrIpPerm += ((StringUtils.isNotEmpty(mngrIpPerm))? "," : "") + item.getRegistIp();
					} else {
						mngrIpIntrcp += ((StringUtils.isNotEmpty(mngrIpIntrcp))? "," : "") + item.getRegistIp();
					}
				}
			}
			
			BufferedWriter bw = null;
			try {
				String str = "{\n" +
						"\t\"name\"            : \"" + site.getSiteNm() + "\",\n" +
						"\t\"roleSn\"          : \"" + site.getRoleSn() + "\",\n" +
						"\t\"actAt\"           : \"" + site.getActvtyAt() + "\",\n" +
						"\t\"snsAt\"           : \"" + site.getSnsUseAt() + "\",\n" +
						"\t\"openDe\"          : \"" + site.getOpenDe() + "\",\n" +
						"\t\"dplctLoginAt\"    : \"" + site.getDplctLoginAt() + "\",\n" +
						"\t\"loginFailrLockCo\": \"" + site.getLoginFailrLockCo() + "\",\n" +
						"\t\"mngrIpUseAt\"     : \"" + ((estbs != null && StringUtils.isNotEmpty(estbs.getIpIntrcpAt()))? estbs.getIpIntrcpAt() : "Y") + "\",\n" +
						"\t\"mngrIpBlocks\"    : \"" + mngrIpPerm + "\",\n" +
						"\t\"mngrIpNones\"     : \"" + mngrIpIntrcp + "\"\n" +
					"}";
				
				File file = new File(rootPath + "/site.json");
				
				bw = new BufferedWriter(fsFuncCmmHelper.setFileWriter(file.getPath()));
				bw.write(str);
				bw.close();
			} finally {
				bw.close();
			}
		}
	}

}
