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
import org.springframework.web.bind.annotation.RequestMethod;

import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.util.FsFuncCmmHelper;
import fscms.cmm.vo.RoleVO;
import fscms.mods.conectr.service.ConectrStatsService;
import fscms.mods.conectr.vo.ConectrStatsSearchVO;
import fscms.mods.conectr.vo.ConectrStatsVO;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;

@Controller
@RequestMapping(value = "/{admURI}")
public class ConectrStatsController {
	
	static Logger logger = LogManager.getLogger(ConectrStatsController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	@Resource(name = "conectrStatsService")
	private ConectrStatsService conectrStatsService;
	
	/**
	 * @Method Name	: conectrStatsInsert
	 * @작성일		: 2020. 1. 27.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 접속자 통계 정보 저장
	 */
	@RequestMapping(value = "/stat/tot.do", method = RequestMethod.GET)
	public String statTot(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ConectrStatsSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
			String tDate = fsFuncCmmHelper.getToDate("yyyyMMddHH", null);
			
			if(StringUtils.isEmpty(searchVO.getSc_year())) {
				searchVO.setSc_year(tDate.substring(0, 4));
			}
			
			if(StringUtils.isEmpty(searchVO.getSc_month())) {
				searchVO.setSc_month(tDate.substring(4, 6));
			}
			
			if(StringUtils.isEmpty(searchVO.getSc_date())) {
				searchVO.setSc_date(tDate.substring(6, 8));
			}
			
			// 현재 년도
			String tYear  = tDate.substring(0, 4);
			String tMonth = tDate.substring(4, 6);
			String tDay   = tDate.substring(6, 8);
			String tHour  = tDate.substring(8, 10);
			
			// 최소 년도
			String minYear = "2020";
			
			// 선택된 달의 마지막 일자
			int maxDay = fsFuncCmmHelper.getMaxDay(searchVO.getSc_year() + searchVO.getSc_month() + searchVO.getSc_date());
			
			// 접속자 통계 - 월별
			ConectrStatsSearchVO conectrStatsSearchVO = new ConectrStatsSearchVO();
			conectrStatsSearchVO.setSc_year(searchVO.getSc_year());
			List<ConectrStatsVO> monthList = conectrStatsService.selectMonth(conectrStatsSearchVO);
			
			// 접속자 통계 - 일별
			conectrStatsSearchVO.setSc_month(searchVO.getSc_month());
			List<ConectrStatsVO> dateList = conectrStatsService.selectDate(conectrStatsSearchVO);
			
			// 접속자 통계 - 시간대별
			conectrStatsSearchVO.setSc_date(searchVO.getSc_date());
			List<ConectrStatsVO> hourList = conectrStatsService.selectHour(conectrStatsSearchVO);
			
			model.addAttribute("monthList", monthList);
			model.addAttribute("dateList",  dateList);
			model.addAttribute("hourList",  hourList);
			model.addAttribute("minYear",   minYear);
			model.addAttribute("maxDay",    maxDay);
			model.addAttribute("tYear",     tYear);
			model.addAttribute("tMonth",    tMonth);
			model.addAttribute("tDay",      tDay);
			model.addAttribute("tHour",     tHour);
		} catch (Exception e) {
			logger.error("접속자 통계 조회 오류 (오류코드 : C0002)");
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("role",   role);
		model.addAttribute("admURI", admURI);
		return "mngr/conectr/stats_tot";
	}
	
	@RequestMapping(value = "/stat/year.do", method = RequestMethod.GET)
	public String statYear(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			// 접속자 통계 - 년별
			List<ConectrStatsVO> yearList = conectrStatsService.selectYear(null);
			
			model.addAttribute("yearList", yearList);
		} catch (Exception e) {
			logger.error("접속자 통계 조회 오류 (오류코드 : C0002)");
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("role",   role);
		model.addAttribute("admURI", admURI);
		return "mngr/conectr/stats_year";
	}
	
	@RequestMapping(value = "/stat/month.do", method = RequestMethod.GET)
	public String statMonth(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ConectrStatsSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
			String tDate = fsFuncCmmHelper.getToDate("yyyyMMddHH", null);
			
			if(StringUtils.isEmpty(searchVO.getSc_year())) {
				searchVO.setSc_year(tDate.substring(0, 4));
			}
			
			// 현재 년도
			String tYear  = tDate.substring(0, 4);
			String tMonth = tDate.substring(4, 6);
			
			// 최소 년도
			String minYear = "2020";
			
			// 접속자 통계 - 월별
			ConectrStatsSearchVO conectrStatsSearchVO = new ConectrStatsSearchVO();
			conectrStatsSearchVO.setSc_year(searchVO.getSc_year());
			List<ConectrStatsVO> monthList = conectrStatsService.selectMonth(conectrStatsSearchVO);
			
			model.addAttribute("monthList", monthList);
			model.addAttribute("minYear",   minYear);
			model.addAttribute("tYear",     tYear);
			model.addAttribute("tMonth",    tMonth);
		} catch (Exception e) {
			logger.error("접속자 통계 조회 오류 (오류코드 : C0002)");
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("role",   role);
		model.addAttribute("admURI", admURI);
		return "mngr/conectr/stats_month";
	}
	
	@RequestMapping(value = "/stat/day.do", method = RequestMethod.GET)
	public String statDay(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ConectrStatsSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
			String tDate = fsFuncCmmHelper.getToDate("yyyyMMddHH", null);
			
			if(StringUtils.isEmpty(searchVO.getSc_year())) {
				searchVO.setSc_year(tDate.substring(0, 4));
			}
			
			if(StringUtils.isEmpty(searchVO.getSc_month())) {
				searchVO.setSc_month(tDate.substring(4, 6));
			}
			
			// 현재 년도
			String tYear  = tDate.substring(0, 4);
			String tMonth = tDate.substring(4, 6);
			String tDay   = tDate.substring(6, 8);
			
			// 최소 년도
			String minYear = "2020";
			
			// 선택된 달의 마지막 일자
			int maxDay = fsFuncCmmHelper.getMaxDay(searchVO.getSc_year() + searchVO.getSc_month() + "01");
			
			ConectrStatsSearchVO conectrStatsSearchVO = new ConectrStatsSearchVO();
			conectrStatsSearchVO.setSc_year(searchVO.getSc_year());
			conectrStatsSearchVO.setSc_month(searchVO.getSc_month());
			List<ConectrStatsVO> dateList = conectrStatsService.selectDate(conectrStatsSearchVO);
			
			model.addAttribute("dateList",  dateList);
			model.addAttribute("minYear",   minYear);
			model.addAttribute("maxDay",    maxDay);
			model.addAttribute("tYear",     tYear);
			model.addAttribute("tMonth",    tMonth);
			model.addAttribute("tDay",      tDay);
		} catch (Exception e) {
			logger.error("접속자 통계 조회 오류 (오류코드 : C0002)");
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("role",   role);
		model.addAttribute("admURI", admURI);
		return "mngr/conectr/stats_day";
	}
	
	@RequestMapping(value = "/stat/week.do", method = RequestMethod.GET)
	public String statWeek(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") ConectrStatsSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			List<ConectrStatsVO> itemList = conectrStatsService.selectWeek(searchVO);
			
			List<String> weekList = new ArrayList<String>();
			weekList.add("일");
			weekList.add("월");
			weekList.add("화");
			weekList.add("수");
			weekList.add("목");
			weekList.add("금");
			weekList.add("토");
			
			Calendar today = Calendar.getInstance();
			int cWeek = today.get(Calendar.DAY_OF_WEEK);
			
			model.addAttribute("itemList", itemList);
			model.addAttribute("weekList", weekList);
			model.addAttribute("cWeek",    cWeek - 1);
		} catch (Exception e) {
			logger.error("접속자 통계 조회 오류 (오류코드 : C0002)");
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("role",   role);
		model.addAttribute("admURI", admURI);
		return "mngr/conectr/stats_week";
	}

}
