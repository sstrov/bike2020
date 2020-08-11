package fscms.mods.conectr.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.vo.RoleVO;
import fscms.mods.conectr.service.ConectrStatsMenuService;
import fscms.mods.conectr.vo.ConectrStatsMenuSearchVO;
import fscms.mods.conectr.vo.ConectrStatsMenuVO;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import fscms.mods.user.service.UserMenuService;
import fscms.mods.user.vo.UserMenuVO;

@Controller
@RequestMapping(value = "/{admURI}")
public class ConectrStatsMenuController {
	
	static Logger logger = LogManager.getLogger(ConectrStatsMenuController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	@Resource(name = "conectrStatsMenuService")
	private ConectrStatsMenuService conectrStatsMenuService;
	
	@Resource(name = "userMenuService")
	private UserMenuService userMenuService;
	
	@RequestMapping(value = "/stat/menu.do")
	public String statMenu(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ConectrStatsMenuSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			List<ConectrStatsMenuVO> itemList = conectrStatsMenuService.selectList(searchVO);
			model.addAttribute("itemList", itemList);
			
			UserMenuVO userMenuVO = new UserMenuVO();
			userMenuVO.setMenuDp(1);
			userMenuVO.setUseAt("Y");
			List<UserMenuVO> mList = userMenuService.selectList(userMenuVO);
			
			List<UserMenuVO> contentList = new ArrayList<UserMenuVO>();
			model.addAttribute("contentList", this.getContentList(mList, contentList));
		} catch (Exception e) {
			logger.error("접속자 통계 메뉴 조회 오류 (오류코드 : C0002)");
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("role",   role);
		model.addAttribute("admURI", admURI);
		return "mngr/conectr/stats_menu";
	}
	
	private List<UserMenuVO> getContentList(List<UserMenuVO> itemList, List<UserMenuVO> contentList) throws Exception {
		
		if(itemList != null && itemList.size() > 0) {
			for (UserMenuVO item : itemList) {
				contentList.add(item);
				
				UserMenuVO userMenuVO = new UserMenuVO();
				userMenuVO.setMenuUpperSn(item.getMenuSn());
				userMenuVO.setUseAt("Y");
				
				List<UserMenuVO> mList = userMenuService.selectList(userMenuVO);
				contentList = this.getContentList(mList, contentList);
			}
		}
		
		return contentList;
	}

}
