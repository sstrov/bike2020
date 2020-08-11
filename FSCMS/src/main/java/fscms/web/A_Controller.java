package fscms.web;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.FsFuncCmmHelper;
import fscms.mods.bbs.service.BbsService;
import fscms.mods.bbs.vo.BbsSearchVO;
import fscms.mods.conectr.service.ConectrStatsService;
import fscms.mods.conectr.vo.ConectrStatsSearchVO;
import fscms.mods.conectr.vo.ConectrStatsVO;
import fscms.mods.mngr.service.MngrMenuService;
import fscms.mods.mngr.util.MngrMenuHelper;
import fscms.mods.mngr.vo.MngrMenuVO;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_Controller {
	
	static Logger logger = LogManager.getLogger(A_Controller.class);
	
	@Resource(name = "mngrMenuHelper")
	private MngrMenuHelper mngrMenuHelper;
	
	@Resource(name = "mngrMenuService")
	private MngrMenuService mngrMenuService;
	
	@Resource(name = "conectrStatsService")
	private ConectrStatsService conectrStatsService;
	
	@Resource(name = "bbsService")
	private BbsService bbsService;
	
	/**
	 * @Method Name	: aIndex
	 * @작성일		: 2019. 10. 4.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 관리자 메인 페이지
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/index.do")
	public String aIndex(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			ModelMap model) {
		
		try {
			FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
			
			String toDate  = fsFuncCmmHelper.getToDate("yyyy-MM-dd", null);
			String toMonth = fsFuncCmmHelper.getToDate("MM", null);
			
			ConectrStatsSearchVO conectrStatsSearchVO = new ConectrStatsSearchVO();
			conectrStatsSearchVO.setSc_year(fsFuncCmmHelper.getToDate("yyyy", null));
			conectrStatsSearchVO.setSc_month(toMonth);
			List<ConectrStatsVO> toStatList = conectrStatsService.selectDate(conectrStatsSearchVO);
			List<ConectrStatsVO> toBrowList = conectrStatsService.selectBrowNm(conectrStatsSearchVO);
			
			Calendar cal = Calendar.getInstance();
			cal.add(cal.MONTH, -1);
			String beforeMonth = fsFuncCmmHelper.getToDate("MM", cal.getTimeInMillis());
			conectrStatsSearchVO.setSc_month(beforeMonth);
			List<ConectrStatsVO> beStatList = conectrStatsService.selectDate(conectrStatsSearchVO);
			
			conectrStatsSearchVO.setSc_wDateS(toDate);
			conectrStatsSearchVO.setSc_wDateE(toDate);
			int todateTCount = conectrStatsService.selectTCount(conectrStatsSearchVO);
			
			conectrStatsSearchVO.setSc_wDateS(null);
			conectrStatsSearchVO.setSc_wDateE(null);
			int tCount = conectrStatsService.selectTCount(conectrStatsSearchVO);
			
			// 금일 등록 게시글 수
			BbsSearchVO bbsSearchVO = new BbsSearchVO();
			bbsSearchVO.setDeleteAt("N");
			bbsSearchVO.setSc_wDateS(toDate);
			bbsSearchVO.setSc_wDateE(toDate);
			int bbsToDateCount = bbsService.selectTCount(bbsSearchVO);
			
			model.addAttribute("toStatList",     toStatList);
			model.addAttribute("toBrowList",     toBrowList);
			model.addAttribute("beforeMonth",    beforeMonth);
			model.addAttribute("beStatList",     beStatList);
			model.addAttribute("todateTCount",   todateTCount);
			model.addAttribute("tCount",         tCount);
			model.addAttribute("bbsToDateCount", bbsToDateCount);
		} catch (Exception e) {
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		return "mngr/main";
	}
	
	/**
	 * @Method Name	: aSub
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 서브 링크 연결
	 */
	@RequestMapping(value = "/sub.do")
	public String aSub(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			String key,
			ModelMap model) {
		
		MngrMenuVO menu = mngrMenuHelper.getJson(key);
		
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
	private String childMenu(MngrMenuVO menu) {
		try {
			MngrMenuVO mngrMenuVO = new MngrMenuVO();
			mngrMenuVO.setMenuUpperSn(menu.getMenuSn());
			mngrMenuVO.setUseAt("Y");
			List<MngrMenuVO> itemList = mngrMenuService.selectList(mngrMenuVO);
			
			if(itemList != null && itemList.size() > 0) {
				for (MngrMenuVO item : itemList) {
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
