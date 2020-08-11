package fscms.mods.conectr.web;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.BrowserDetectorHelper;
import fscms.mods.conectr.service.ConectrStatsService;
import fscms.mods.conectr.vo.ConectrStatsVO;

@Controller
public class ConectrStatsController {
	
	static Logger logger = LogManager.getLogger(ConectrStatsController.class);
	
	@Resource(name = "conectrStatsService")
	private ConectrStatsService conectrStatsService;
	
	/**
	 * @Method Name	: conectrStatsInsert
	 * @작성일		: 2020. 1. 27.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 접속자 통계 정보 저장
	 */
	@RequestMapping(value = "/conectr/stats/insert.do")
	public @ResponseBody void conectrStatsInsert(
			HttpServletRequest request,
			ModelMap model) {
		
		try {
			Calendar today = Calendar.getInstance();
			
			int w = today.get(Calendar.DAY_OF_WEEK);
			String[] week = {"일", "월", "화", "수", "목", "금", "토"};
			
			ConectrStatsVO conectrStatsVO = new ConectrStatsVO();
			conectrStatsVO.setStatsYear(today.get(Calendar.YEAR));
			conectrStatsVO.setStatsMonth(today.get(Calendar.MONDAY) + 1);
			conectrStatsVO.setStatsDate(today.get(Calendar.DATE));;
			conectrStatsVO.setStatsHour(today.get(Calendar.HOUR_OF_DAY));
			conectrStatsVO.setStatsMin(today.get(Calendar.MINUTE));
			conectrStatsVO.setStatsSec(today.get(Calendar.SECOND));
			conectrStatsVO.setStatsWeek(week[w - 1]);
			conectrStatsVO.setStatsOpersysm(request.getHeader("user-agent"));
			conectrStatsVO.setStatsLocale(request.getLocale().toString());
			conectrStatsVO.setStatsRef(request.getHeader("referer"));
			conectrStatsVO.setRegistIp(request.getRemoteAddr());
			
			if(request.getHeader("user-agent") != null && request.getHeader("user-agent").indexOf("/") != -1) {
				BrowserDetectorHelper browserDetector = new BrowserDetectorHelper(request.getHeader("user-agent"));
				conectrStatsVO.setStatsBrwsrNm(browserDetector.getBrowserName());
				conectrStatsVO.setStatsBrwsrVer(String.valueOf(browserDetector.getBrowserVersion()));
				conectrStatsVO.setStatsPltfom(browserDetector.getBrowserPlatform());
			}
			
			conectrStatsService.insertInfo(conectrStatsVO);
		} catch (Exception e) {
			logger.error("접속자 통계 저장 오류 (오류코드 : C0001)");
			throw new WrongApproachException("C0001");
		}
	}

}
