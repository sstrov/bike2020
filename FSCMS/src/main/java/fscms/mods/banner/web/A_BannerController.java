package fscms.mods.banner.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.util.FsFuncCmmHelper;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.RoleVO;
import fscms.mods.banner.service.BannerEstbsService;
import fscms.mods.banner.service.BannerService;
import fscms.mods.banner.vo.BannerEstbsVO;
import fscms.mods.banner.vo.BannerSearchVO;
import fscms.mods.banner.vo.BannerVO;
import fscms.mods.code.util.CodeHelper;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_BannerController {
	
	static Logger logger = LogManager.getLogger(A_BannerController.class);
	
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "bannerService")
	private BannerService bannerService;
	
	@Resource(name = "bannerEstbsService")
	private BannerEstbsService bannerEstbsService;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	@Autowired
	private CodeHelper codeHelper;
	
	/**
	 * @Method Name	: aBannerList
	 * @작성일		: 2019. 10. 25.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 배너 목록 화면
	 */
	@RequestMapping(value = "/banner/list.do")
	public String aBannerList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BannerSearchVO searchVO,
			@RequestParam(value = "bannerEstbsSn", required = true) int bannerEstbsSn,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			BannerEstbsVO bannerEstbsVO = new BannerEstbsVO();
			bannerEstbsVO.setBannerEstbsSn(bannerEstbsSn);
			BannerEstbsVO estbs = bannerEstbsService.selectObj(bannerEstbsVO);
			
			if(estbs == null) {
				logger.error("데이터가 삭제 되었거나, 잘못된 접근 입니다. (오류코드 : D0001)");
				throw new WrongApproachException("D0001");
			}
		} catch (Exception e) {
			logger.error("배너_설정 정보 조회 오류");
			throw new WrongApproachException("C0002");
		}
		
		try {
			// 활성 상태 코드
			model.addAttribute("stateList", codeHelper.getList("POP001"));
		} catch (Exception e) {
			logger.error("팝업상태 코드 정보 조회 오류");
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("toDate", new FsFuncCmmHelper().getToDate("yyyyMMddHHmm", null));
		model.addAttribute("role",   role);
		model.addAttribute("admURI", admURI);
		return "mngr/banner/list";
	}
	
	/**
	 * @Method Name	: aBannerGetList
	 * @작성일		: 2019. 10. 25.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 배너 정보 목록 조회
	 */
	@RequestMapping(value = "/banner/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aBannerGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BannerSearchVO searchVO,
			@RequestParam(value = "bannerEstbsSn", required = true) int bannerEstbsSn,
			MngrVO admSession,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		try {
			BannerEstbsVO bannerEstbsVO = new BannerEstbsVO();
			bannerEstbsVO.setBannerEstbsSn(bannerEstbsSn);
			BannerEstbsVO estbs = bannerEstbsService.selectObj(bannerEstbsVO);
			
			if(estbs == null) {
				logger.error("데이터가 삭제 되었거나, 잘못된 접근 입니다. (오류코드 : D0001)");
				jsonVO.setMsg(egovMessageSource.getMessageArgs("info.nodata.msg", null));
				return JSONArray.fromObject(jsonVO).toString();
			}
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<BannerVO> itemList = null;
		try {
			// 노출상태
			if(StringUtils.isNotEmpty(searchVO.getSc_state())) {
				String[] arr = searchVO.getSc_state().split(",");
				searchVO.setSc_stateArr(arr);
			}
			
			int tCount = bannerService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			itemList = bannerService.selectPageList(searchVO);
			
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
	 * @Method Name	: aBannerForm
	 * @작성일		: 2019. 10. 25.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 배너 등록/수정 화면
	 */
	@RequestMapping(value = "/banner/form.do")
	public String aBannerForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BannerSearchVO searchVO,
			@RequestParam(value = "bannerEstbsSn", required = true) int bannerEstbsSn,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		try {
			BannerEstbsVO bannerEstbsVO = new BannerEstbsVO();
			bannerEstbsVO.setBannerEstbsSn(bannerEstbsSn);
			BannerEstbsVO estbs = bannerEstbsService.selectObj(bannerEstbsVO);
			
			if(estbs == null) {
				logger.error("데이터가 삭제 되었거나, 잘못된 접근 입니다. (오류코드 : D0001)");
				throw new WrongApproachException("D0001");
			}
			
			model.addAttribute("estbs", estbs);
		} catch (Exception e) {
			logger.error("배너_설정 정보 조회 오류");
			throw new WrongApproachException("C0002");
		}
		
		BannerVO item = new BannerVO();
		try {
			if(searchVO.getBannerSn() != null && searchVO.getBannerSn() > 0) {
				// 일련번호가 있으면 수정
				BannerVO bannerVO = new BannerVO();
				bannerVO.setBannerSn(searchVO.getBannerSn());
				item = bannerService.selectObj(bannerVO);
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
		
		model.addAttribute("role",   role);
		model.addAttribute("item",   item);
		model.addAttribute("admURI", admURI);
		return "mngr/banner/form";
	}
	
	/**
	 * @Method Name	: aBannerInsert
	 * @작성일		: 2019. 10. 25.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 배너 정보 저장
	 */
	@RequestMapping(value = "/banner/insert.do", method = RequestMethod.POST)
	public String aBannerInsert(
			MultipartHttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") BannerVO vo,
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
			BannerEstbsVO estbs = bannerEstbsService.selectObj(bannerEstbsVO);
			
			if(estbs == null) {
				logger.error("데이터가 삭제 되었거나, 잘못된 접근 입니다. (오류코드 : D0001)");
				throw new WrongApproachException("D0001");
			}
		} catch (Exception e) {
			logger.error("배너_설정 정보 조회 오류");
			throw new WrongApproachException("C0002");
		}
		
		try {
			bannerService.insertInfo(request, vo, admSession.getMngrId());
		} catch (Exception e) {
			logger.error("배너 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0001");
		}
		
		model.addAttribute("url", "/" + admURI + "/banner/list.do?key=" + key + "&bannerEstbsSn=" + bannerEstbsSn);
		model.addAttribute("msg", "저장 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aBannerUpdate
	 * @작성일		: 2019. 10. 25.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 배너 정보 수정
	 */
	@RequestMapping(value = "/banner/update.do", method = RequestMethod.POST)
	public String aBannerUpdate(
			MultipartHttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BannerSearchVO searchVO,
			@ModelAttribute("vo") BannerVO vo,
			@RequestParam(value = "bannerSn", required = true) int bannerSn,
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
			BannerEstbsVO estbs = bannerEstbsService.selectObj(bannerEstbsVO);
			
			if(estbs == null) {
				logger.error("데이터가 삭제 되었거나, 잘못된 접근 입니다. (오류코드 : D0001)");
				throw new WrongApproachException("D0001");
			}
		} catch (Exception e) {
			logger.error("배너_설정 정보 조회 오류");
			throw new WrongApproachException("C0002");
		}
		
		try {
			BannerVO bannerVO = new BannerVO();
			bannerVO.setBannerSn(bannerSn);
			BannerVO item = bannerService.selectObj(bannerVO);
			
			if(item != null) {
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				bannerService.updateInfo(request, bfData, vo, admSession.getMngrId());
			}
		} catch (Exception e) {
			logger.error("배너 정보 수정 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/banner/form.do?key=" + key + "&bannerSn=" + bannerSn);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aBannerMove
	 * @작성일		: 2019. 10. 25.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 배너 순서 변경
	 */
	@RequestMapping(value = "/banner/move.do", method = RequestMethod.POST)
	public String aBannerMove(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BannerSearchVO searchVO,
			@RequestParam(value = "bannerEstbsSn", required = true) int bannerEstbsSn,
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
		
		try {
			BannerEstbsVO bannerEstbsVO = new BannerEstbsVO();
			bannerEstbsVO.setBannerEstbsSn(bannerEstbsSn);
			BannerEstbsVO estbs = bannerEstbsService.selectObj(bannerEstbsVO);
			
			if(estbs == null) {
				logger.error("데이터가 삭제 되었거나, 잘못된 접근 입니다. (오류코드 : D0001)");
				throw new WrongApproachException("D0001");
			}
		} catch (Exception e) {
			logger.error("배너_설정 정보 조회 오류");
			throw new WrongApproachException("C0002");
		}
		
		if(chkKey != null && chkKey.length > 0) {
			try {
				int i = 1;
				for (String itemKey : chkKey) {
					if(StringUtils.isEmpty(itemKey)) {
						continue;
					}
					
					BannerVO bannerVO = new BannerVO();
					bannerVO.setBannerSn(Integer.parseInt(itemKey));
					BannerVO item = bannerService.selectObj(bannerVO);
					
					if(item != null) {
						// 기존 순서 변경 처리
						bannerVO = new BannerVO();
						bannerVO.setBannerEstbsSn(item.getBannerEstbsSn());
						bannerVO.setBannerOrdr(i);
						bannerService.updateOrder(bannerVO);
						
						// 현재 순서 저장
						bannerVO = new BannerVO();
						bannerVO.setBannerSn(item.getBannerSn());
						bannerVO.setBannerBgnde(item.getBannerBgnde());
						bannerVO.setBannerEndde(item.getBannerEndde());
						bannerVO.setBannerOrdr(i);
						bannerService.updateInfo(request, null, bannerVO, null);
						
						i++;
					}
				}
			} catch (Exception e) {
				logger.error("배너 순서 변경 오류 : " + e.getMessage());
				throw new WrongApproachException("C0004");
			}
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/banner/list.do?key=" + key);
		model.addAttribute("msg",    "순서를 변경 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aBannerDeleteForList
	 * @작성일		: 2019. 10. 25.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 배너 선택정보 삭제
	 */
	@RequestMapping(value = "/banner/deleteForList.do", method = RequestMethod.POST)
	public String aBannerDeleteForList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BannerSearchVO searchVO,
			@RequestParam(value = "bannerEstbsSn", required = true) int bannerEstbsSn,
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
		
		try {
			BannerEstbsVO bannerEstbsVO = new BannerEstbsVO();
			bannerEstbsVO.setBannerEstbsSn(bannerEstbsSn);
			BannerEstbsVO estbs = bannerEstbsService.selectObj(bannerEstbsVO);
			
			if(estbs == null) {
				logger.error("데이터가 삭제 되었거나, 잘못된 접근 입니다. (오류코드 : D0001)");
				throw new WrongApproachException("D0001");
			}
		} catch (Exception e) {
			logger.error("배너_설정 정보 조회 오류");
			throw new WrongApproachException("C0002");
		}
		
		if(chkKey != null && chkKey.length > 0) {
			try {
				for (String itemKey : chkKey) {
					if(StringUtils.isEmpty(itemKey)) {
						continue;
					}
					
					BannerVO bannerVO = new BannerVO();
					bannerVO.setBannerSn(Integer.parseInt(itemKey));
					BannerVO item = bannerService.selectObj(bannerVO);
					
					if(item != null) {
						item.setUpdtDe(null);
						String bfData = JSONArray.fromObject(item).toString();
						bannerService.deleteInfo(request, bfData, item, admSession.getMngrId());
					}
				}
			} catch (Exception e) {
				logger.error("배너 정보 삭제 오류 : " + e.getMessage());
				throw new WrongApproachException("C0004");
			}
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/banner/list.do?key=" + key);
		model.addAttribute("msg",    "선택한 정보를 삭제 하였습니다.");
		return "success";
	}

}
