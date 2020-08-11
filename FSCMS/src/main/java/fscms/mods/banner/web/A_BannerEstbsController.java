package fscms.mods.banner.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.RoleVO;
import fscms.mods.banner.service.BannerEstbsService;
import fscms.mods.banner.vo.BannerEstbsSearchVO;
import fscms.mods.banner.vo.BannerEstbsVO;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_BannerEstbsController {
	
	static Logger logger = LogManager.getLogger(A_BannerEstbsController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "bannerEstbsService")
	private BannerEstbsService bannerEstbsService;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	/**
	 * @Method Name	: aBannerEstbsList
	 * @작성일		: 2019. 10. 24.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 배너_설정 목록 화면
	 */
	@RequestMapping(value = "/banner/estbs/list.do")
	public String aBannerEstbsList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BannerEstbsSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		model.addAttribute("role",   role);
		model.addAttribute("admURI", admURI);
		return "mngr/banner/estbs_list";
	}
	
	/**
	 * @Method Name	: aBannerEstbsGetList
	 * @작성일		: 2019. 10. 24.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 배너_설정 정보 목록 조회
	 */
	@RequestMapping(value = "/banner/estbs/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aBannerEstbsGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BannerEstbsSearchVO searchVO,
			MngrVO admSession,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<BannerEstbsVO> itemList = null;
		try {
			int tCount = bannerEstbsService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			itemList = bannerEstbsService.selectPageList(searchVO);
			
			if(itemList != null && itemList.size() > 0) {
				itemList.get(0).settCount(tCount);
			}
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(itemList).toString();
	}
	
	/**
	 * @Method Name	: aBannerEstbsForm
	 * @작성일		: 2019. 10. 25.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 배너_설정 등록/수정 화면
	 */
	@RequestMapping(value = "/banner/estbs/form.do")
	public String aBannerEstbsForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BannerEstbsSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		BannerEstbsVO item = new BannerEstbsVO();
		try {
			if(searchVO.getBannerEstbsSn() != null && searchVO.getBannerEstbsSn() > 0) {
				// 일련번호가 있으면 수정
				BannerEstbsVO bannerEstbsVO = new BannerEstbsVO();
				bannerEstbsVO.setBannerEstbsSn(searchVO.getBannerEstbsSn());
				item = bannerEstbsService.selectObj(bannerEstbsVO);
			} else {
				// 등록 일때는 등록 권한 필요
				if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
					logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
					throw new WrongApproachException("C0041");
				}
			}
		} catch (Exception e) {
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("role",     role);
		model.addAttribute("item",     item);
		model.addAttribute("admURI",   admURI);
		return "mngr/banner/estbs_form";
	}
	
	/**
	 * @Method Name	: aBannerEstbsInsert
	 * @작성일		: 2019. 10. 25.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 배너_설정 정보 저장
	 */
	@RequestMapping(value = "/banner/estbs/insert.do", method = RequestMethod.POST)
	public String aBannerEstbsInsert(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") BannerEstbsVO vo,
			MngrVO admSession,
			String key,
			Boolean refAt,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			bannerEstbsService.insertInfo(request, vo, admSession.getMngrId());
		} catch (Exception e) {
			logger.error("배너_설정 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0001");
		}
		
		model.addAttribute("url", "/" + admURI + "/banner/estbs/list.do?key=" + key);
		model.addAttribute("msg", "저장 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aBannerEstbsUpdate
	 * @작성일		: 2019. 10. 25.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 배너_설정 정보 수정
	 */
	@RequestMapping(value = "/banner/estbs/update.do", method = RequestMethod.POST)
	public String aBannerEstbsUpdate(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BannerEstbsSearchVO searchVO,
			@ModelAttribute("vo") BannerEstbsVO vo,
			@RequestParam(value = "bannerEstbsSn", required = true) int bannerEstbsSn,
			MngrVO admSession,
			String key,
			Boolean refAt,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			BannerEstbsVO bannerEstbsVO = new BannerEstbsVO();
			bannerEstbsVO.setBannerEstbsSn(bannerEstbsSn);
			BannerEstbsVO item = bannerEstbsService.selectObj(bannerEstbsVO);
			
			if(item != null) {
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				bannerEstbsService.updateInfo(request, bfData, vo, admSession.getMngrId());
			}
		} catch (Exception e) {
			logger.error("배너_설정 정보 수정 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/banner/estbs/form.do?key=" + key + "&bannerEstbsSn=" + bannerEstbsSn);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: abannerEstbsDeleteForList
	 * @작성일		: 2019. 10. 25.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 배너_설정 선택정보 삭제
	 */
	@RequestMapping(value = "/banner/estbs/deleteForList.do", method = RequestMethod.POST)
	public String abannerEstbsDeleteForList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BannerEstbsSearchVO searchVO,
			@RequestParam("chkKey") String[] chkKey,
			MngrVO admSession,
			String key,
			Boolean refAt,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		if(chkKey != null && chkKey.length > 0) {
			try {
				for (String itemKey : chkKey) {
					if(StringUtils.isEmpty(itemKey)) {
						continue;
					}
					
					BannerEstbsVO bannerEstbsVO = new BannerEstbsVO();
					bannerEstbsVO.setBannerEstbsSn(Integer.parseInt(itemKey));
					BannerEstbsVO item = bannerEstbsService.selectObj(bannerEstbsVO);
					
					if(item != null) {
						item.setUpdtDe(null);
						String bfData = JSONArray.fromObject(item).toString();
						bannerEstbsService.deleteInfo(request, bfData, item, admSession.getMngrId());
					}
				}
			} catch (Exception e) {
				logger.error("배너_설정 정보 삭제 오류 : " + e.getMessage());
				throw new WrongApproachException("C0004");
			}
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/banner/estbs/list.do?key=" + key);
		model.addAttribute("msg",    "선택한 정보를 삭제 하였습니다.");
		return "success";
	}

}
