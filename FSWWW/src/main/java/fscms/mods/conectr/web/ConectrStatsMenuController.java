package fscms.mods.conectr.web;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.BrowserDetectorHelper;
import fscms.mods.conectr.service.ConectrStatsMenuService;
import fscms.mods.conectr.vo.ConectrStatsMenuVO;

@Controller
public class ConectrStatsMenuController {
	
	static Logger logger = LogManager.getLogger(ConectrStatsMenuController.class);
	
	@Resource(name = "conectrStatsMenuService")
	private ConectrStatsMenuService conectrStatsMenuService;
	
	/**
	 * @Method Name	: conectrStatsInsert
	 * @작성일		: 2020. 1. 27.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 접속자 통계 정보 저장
	 */
	@RequestMapping(value = "/conectr/stats/menu/insert.do")
	public @ResponseBody void conectrStatsMenuInsert(
			HttpServletRequest request,
			@RequestParam(value = "menuSn", required = true) String menuSn,
			ModelMap model) {
		
		try {
			Calendar today = Calendar.getInstance();
			
			int w = today.get(Calendar.DAY_OF_WEEK);
			String[] week = {"일", "월", "화", "수", "목", "금", "토"};
			
			ConectrStatsMenuVO conectrStatsMenuVO = new ConectrStatsMenuVO();
			conectrStatsMenuVO.setMenuSn(menuSn);
			conectrStatsMenuVO.setStatsYear(today.get(Calendar.YEAR));
			conectrStatsMenuVO.setStatsMonth(today.get(Calendar.MONDAY) + 1);
			conectrStatsMenuVO.setStatsDate(today.get(Calendar.DATE));;
			conectrStatsMenuVO.setStatsHour(today.get(Calendar.HOUR_OF_DAY));
			conectrStatsMenuVO.setStatsMin(today.get(Calendar.MINUTE));
			conectrStatsMenuVO.setStatsSec(today.get(Calendar.SECOND));
			conectrStatsMenuVO.setStatsWeek(week[w - 1]);
			conectrStatsMenuVO.setStatsOpersysm(request.getHeader("user-agent"));
			conectrStatsMenuVO.setStatsLocale(request.getLocale().toString());
			conectrStatsMenuVO.setStatsRef(request.getHeader("referer"));
			conectrStatsMenuVO.setRegistIp(request.getRemoteAddr());
			
			if(request.getHeader("user-agent") != null && request.getHeader("user-agent").indexOf("/") != -1) {
				BrowserDetectorHelper browserDetector = new BrowserDetectorHelper(request.getHeader("user-agent"));
				conectrStatsMenuVO.setStatsBrwsrNm(browserDetector.getBrowserName());
				conectrStatsMenuVO.setStatsBrwsrVer(String.valueOf(browserDetector.getBrowserVersion()));
				conectrStatsMenuVO.setStatsPltfom(browserDetector.getBrowserPlatform());
			}
			
			conectrStatsMenuService.insertInfo(conectrStatsMenuVO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("접속자 통계 메뉴 저장 오류 (오류코드 : C0001)");
			throw new WrongApproachException("C0001");
		}
	}

}
