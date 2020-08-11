package fscms.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import fscms.cmm.exception.WrongApproachException;
import fscms.mods.banner.service.BannerService;
import fscms.mods.banner.vo.BannerVO;
import fscms.mods.bbs.service.BbsService;
import fscms.mods.bbs.vo.BbsSearchVO;
import fscms.mods.bbs.vo.BbsVO;
import fscms.mods.user.service.UserMenuService;
import fscms.mods.user.util.UserMenuHelper;
import fscms.mods.user.vo.UserMenuVO;
import fscms.mods.user.vo.UserVO;

@Controller
public class WwwController {
	
	static Logger logger = LogManager.getLogger(WwwController.class);
	
	@Resource(name = "userMenuHelper")
	private UserMenuHelper userMenuHelper;
	
	@Resource(name = "userMenuService")
	private UserMenuService userMenuService;
	
	@Resource(name = "bbsService")
	private BbsService bbsService;
	
	@Resource(name = "bannerService")
	private BannerService bannerService;
	
	@RequestMapping(value = "/www/banner1.do")
	public String wwwBanner1(
			HttpServletRequest request,
			ModelMap model) {
		try {
			// 상단 팝업
			BannerVO bannerVO = new BannerVO();
			bannerVO.setBannerEstbsSn(1);
			bannerVO.setUseAt("Y");
			bannerVO.setMainAt("Y");
			List<BannerVO> bannerList1 = bannerService.selectList(bannerVO);
			
			model.addAttribute("bannerList1", bannerList1);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		return "www/banner1";
	}
	
	/**
	 * @Method Name	: aIndex
	 * @작성일		: 2019. 10. 4.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 관리자 메인 페이지
	 */
	@RequestMapping(value = "/index.do")
	public String index(
			HttpServletRequest request,
			UserVO userSession,
			ModelMap model) {
		
		try {
			// 상단 팝업
			BannerVO bannerVO = new BannerVO();
			bannerVO.setBannerEstbsSn(1);
			bannerVO.setUseAt("Y");
			bannerVO.setMainAt("Y");
			List<BannerVO> bannerList1 = bannerService.selectList(bannerVO);
			
			// 중간 popupzone
			bannerVO.setBannerEstbsSn(2);
			List<BannerVO> bannerList2 = bannerService.selectList(bannerVO);
			
			// 하단 배너존
			bannerVO.setBannerEstbsSn(3);
			List<BannerVO> bannerList3 = bannerService.selectList(bannerVO);
			
			// 메인 비주얼
			bannerVO.setBannerEstbsSn(4);
			List<BannerVO> bannerList4 = bannerService.selectList(bannerVO);
			
			// 통계 알림
			BbsSearchVO bbsSearchVO = new BbsSearchVO();
			bbsSearchVO.setBbsEstbsSn(1);
			bbsSearchVO.setDeleteAt("N");
			bbsSearchVO.setFirstIndex(0);
			bbsSearchVO.setRecordCountPerPage(5);
			List<BbsVO> bbsList1 = bbsService.selectPageList(bbsSearchVO);
			
			// 통계 자료실
			bbsSearchVO.setBbsEstbsSn(13);
			List<BbsVO> bbsList2 = bbsService.selectPageList(bbsSearchVO);
			
			// 통계 월보
			bbsSearchVO.setBbsEstbsSn(2);
			List<BbsVO> bbsList3 = bbsService.selectPageList(bbsSearchVO);
			
			model.addAttribute("bannerList1", bannerList1);
			model.addAttribute("bannerList2", bannerList2);
			model.addAttribute("bannerList3", bannerList3);
			model.addAttribute("bannerList4", bannerList4);
			model.addAttribute("bbsList1",    bbsList1);
			model.addAttribute("bbsList2",    bbsList2);
			model.addAttribute("bbsList3",    bbsList3);
			model.addAttribute("userSession", userSession);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		return "www/main";
	}
	
	/**
	 * @Method Name	: aSub
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 서브 링크 연결
	 */
	@RequestMapping(value = "/sub.do")
	public String sub(
			HttpServletRequest request,
			String key,
			ModelMap model) {
		
		UserMenuVO menu = userMenuHelper.getJson(key);
		
		if(menu == null) {
			logger.error("메뉴 정보가 없음");
			throw new WrongApproachException("A0001");
		}
		
		if(StringUtils.equals(menu.getMenuCnncTy(), "0")) {
			// 빈메뉴 시 하위메뉴 체크
			return this.childMenu(menu);
		}
		
		return "redirect:" + menu.getMenuLink();
	}
	
	/**
	 * @Method Name	: childMenu
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 하위메뉴 체크
	 */
	private String childMenu(UserMenuVO menu) {
		try {
			UserMenuVO userMenuVO = new UserMenuVO();
			userMenuVO.setMenuUpperSn(menu.getMenuSn());
			userMenuVO.setUseAt("Y");
			List<UserMenuVO> itemList = userMenuService.selectList(userMenuVO);
			
			if(itemList != null && itemList.size() > 0) {
				for (UserMenuVO item : itemList) {
					if(StringUtils.equals(item.getMenuCnncTy(), "0")) {
						return this.childMenu(item);
					}
					
					return "redirect:" + item.getMenuLink();
				}
			}
			
			logger.error("연결할 메뉴가 없습니다.");
			throw new WrongApproachException("A0001");
		} catch (Exception e) {
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
	}

}
