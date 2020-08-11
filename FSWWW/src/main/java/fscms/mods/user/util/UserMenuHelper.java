package fscms.mods.user.util;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import fscms.cmm.util.JsonConnHelper;
import fscms.mods.user.service.UserMenuService;
import fscms.mods.user.vo.UserMenuVO;

@Component
public class UserMenuHelper {
	
	static Logger logger = LogManager.getLogger(UserMenuHelper.class);
	
	@Resource(name = "userMenuService")
	private UserMenuService userMenuService;
	
	
	/**
	 * @Method Name	: getJson
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 메뉴 정보 조회 - JSON
	 */
	public UserMenuVO getJson(String menuKey) {
		
		JSONObject jsonObject = null;
		try {
			jsonObject = new JsonConnHelper().getJsonFile("/www/menu/json_menu_" + menuKey);
			 
			if(jsonObject != null) {
				String accKey    = (String) jsonObject.get("accKey");
				String fKey      = (String) jsonObject.get("fKey");
				String fNm       = (String) jsonObject.get("fNm");
				String pKey      = (String) jsonObject.get("pKey");
				String pNm       = (String) jsonObject.get("pNm");
				String menuG     = (String) jsonObject.get("menuG");
				String nm        = (String) jsonObject.get("nm");
				String page      = (String) jsonObject.get("page");
				String target    = (String) jsonObject.get("target");
				String tag       = (String) jsonObject.get("tag");
				String menuClass = (String) jsonObject.get("class");
				String fileImage = (String) jsonObject.get("fileImage");
				String filePath  = (String) jsonObject.get("filePath");
				String actAt     = (String) jsonObject.get("actAt");
				String useAt     = (String) jsonObject.get("useAt");
				String link      = (String) jsonObject.get("link");
				String subImg    = (String) jsonObject.get("subImg");
				jsonObject.clear();
				
				UserMenuVO menu = new UserMenuVO();
				menu.setMenuSn(menuKey);
				menu.setAccSn((StringUtils.isNotEmpty(accKey) && !"null".equals(accKey))? Integer.parseInt(accKey) : null);
				menu.setMenuBestSn(fKey);
				if(StringUtils.isNotEmpty(pKey) && !"null".equals(pKey)) {
					menu.setMenuUpperSn(pKey);
				}
				menu.setMenuDp(Integer.parseInt(menuG));
				menu.setMenuNm(nm);
				menu.setfNm(fNm);
				menu.setpNm(pNm);
				menu.setMenuCnncTy(page);
				menu.setMenuTrgt(target);
				menu.setMenuTag((StringUtils.isNotEmpty(tag) && !StringUtils.equals(tag, "null"))? tag : "");
				menu.setMenuClass((StringUtils.isNotEmpty(menuClass) && !StringUtils.equals(menuClass, "null"))? menuClass : "");
				menu.setMenuFileImage((StringUtils.isNotEmpty(fileImage) && !StringUtils.equals(fileImage, "null"))? fileImage : "");
				menu.setMenuFlpth((StringUtils.isNotEmpty(filePath) && !StringUtils.equals(filePath, "null"))? filePath : "");
				menu.setActvtyAt(actAt);
				menu.setUseAt(useAt);
				menu.setMenuLink(link);
				menu.setSubImg((StringUtils.isNotEmpty(subImg) && !StringUtils.equals(subImg, "null"))? subImg : "");
				
				return menu;
			}
		} catch (IOException | ParseException e) {
			return null;
		}
		return null;
	}
	
}
