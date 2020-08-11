package fscms.mods.mngr.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.JsonConnHelper;
import fscms.cmm.vo.RoleVO;
import fscms.mods.mngr.vo.MngrVO;

@Component
public class MngrMenuAccFn {
	
	static Logger logger = LogManager.getLogger(MngrMenuAccFn.class);
	
	/**
	 * @Method Name	: acc
	 * @작성일		: 2019. 10. 8.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 관리자 메뉴 엑세스 권한 체크
	 */
	public static boolean acc(String menuSn, Integer roleSn) {
		boolean rtn = false;
		
		JSONObject jsonObject;
		try {
			jsonObject = new JsonConnHelper().getJsonFile("/mngr/role/json_role_" + roleSn + "_" + menuSn);
			
			if(jsonObject != null) {
				String powR = jsonObject.get("powR").toString();
				String powW = jsonObject.get("powW").toString();
				
				if(StringUtils.equals(powR, "Y") || StringUtils.equals(powW, "Y")) {
					rtn = true;
				}
				jsonObject.clear();
			}
		} catch (IOException | ParseException e) {
			logger.error("역할 관련 설정파일이 없습니다. (오류코드 : C0021)");
			//throw new WrongApproachException("C0021");
		}
		
		return rtn;
	}
	
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
			jsonObject = new JsonConnHelper().getJsonFile("/mngr/menu/json_navi_" + currMenuSn);
		
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

	public RoleVO checkRole(String key, Integer roleSn) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		MngrVO admSession = (MngrVO) request.getSession().getAttribute("admSession");
		
		if(roleSn == null) {
			roleSn = admSession.getRoleSn();
		}
		
		RoleVO roleVO = new RoleVO();
		
		JSONObject jsonObject;
		try {
			jsonObject = new JsonConnHelper().getJsonFile("/mngr/role/json_role_" + roleSn + "_" + key);
			
			if(jsonObject != null) {
				String powR = jsonObject.get("powR").toString();
				String powW = jsonObject.get("powW").toString();
				
				roleVO.setPowR(StringUtils.equals(powR, "Y")? "Y" : "N");
				roleVO.setPowW(StringUtils.equals(powW, "Y")? "Y" : "N");
				jsonObject.clear();
			}
		} catch (IOException | ParseException e) {
			logger.error("역할 관련 설정파일이 없습니다. (오류코드 : C0021)");
			throw new WrongApproachException("C0021");
		}
		
		return roleVO;
	}

}
