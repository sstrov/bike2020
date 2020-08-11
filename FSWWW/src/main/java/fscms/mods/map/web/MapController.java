package fscms.mods.map.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.mods.atchmnfl.vo.AtchmnflVO;
import fscms.mods.map.vo.MapSearchVO;
import fscms.mods.user.util.UserMenuHelper;
import fscms.mods.user.vo.UserMenuVO;
import fscms.mods.user.vo.UserVO;

@Controller
public class MapController {

	static Logger logger = LogManager.getLogger(MapController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "userMenuHelper")
	private UserMenuHelper userMenuHelper;
	
	/**
	 * @Method Name	: mapOpen
	 * @작성일		: 2019. 12. 3.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 지도 호출 화면
	 */
	@RequestMapping(value = "/map/roadMap.do")
	public String mapOpen(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("searchVO") MapSearchVO searchVO,
			UserVO userSession,
			String key,
			ModelMap model) {
		
		UserMenuVO menu = userMenuHelper.getJson(key);
		
		if(menu == null || StringUtils.equals(menu.getUseAt(), "N")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("지도 호출 오류 (오류코드 : C0002)");
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("userSession", userSession);
		return "www/map/map";
	}
}
