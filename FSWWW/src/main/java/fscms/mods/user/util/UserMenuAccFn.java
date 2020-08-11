package fscms.mods.user.util;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.JsonConnHelper;

@Component
public class UserMenuAccFn {
	
	static Logger logger = LogManager.getLogger(UserMenuAccFn.class);
	
	/**
	 * @Method Name	: use
	 * @작성일		: 2019. 10. 8.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 관리자 메뉴 활성여부 체크
	 */
	public static boolean use(String currMenuSn, String menuSn) {
		boolean rtn = false;
		
		JSONObject jsonObject;
		try {
			jsonObject = new JsonConnHelper().getJsonFile("/www/menu/json_navi_" + currMenuSn);
		
			if(jsonObject != null) {
				for(Object k : jsonObject.keySet()) {
					String key = jsonObject.get(k).toString();
					
					if(menuSn.equals(key)) {
						rtn = true;
					}
				}
				jsonObject.clear();
			}
		} catch (IOException | ParseException e) {
			logger.error("역할 관련 설정파일이 없습니다. (오류코드 : C0021)");
			throw new WrongApproachException("C0021");
		}
		
		return rtn;
	}

}
