package fscms.mods.bbs.web;

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

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.RoleVO;
import fscms.mods.bbs.service.BbsCtgryService;
import fscms.mods.bbs.service.BbsCtgrySubService;
import fscms.mods.bbs.service.BbsEstbsService;
import fscms.mods.bbs.service.BbsFieldService;
import fscms.mods.bbs.service.BbsRoleService;
import fscms.mods.bbs.service.BbsSknService;
import fscms.mods.bbs.service.BbsThumbService;
import fscms.mods.bbs.vo.BbsCtgrySubVO;
import fscms.mods.bbs.vo.BbsCtgryVO;
import fscms.mods.bbs.vo.BbsEstbsSearchVO;
import fscms.mods.bbs.vo.BbsEstbsVO;
import fscms.mods.bbs.vo.BbsFieldVO;
import fscms.mods.bbs.vo.BbsRoleVO;
import fscms.mods.bbs.vo.BbsSknVO;
import fscms.mods.bbs.vo.BbsThumbVO;
import fscms.mods.code.util.CodeHelper;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import fscms.mods.user.service.UserRoleService;
import fscms.mods.user.vo.UserRoleVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_BbsEstbsController {
	
	static Logger logger = LogManager.getLogger(A_BbsEstbsController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "bbsCtgryService")
	private BbsCtgryService bbsCtgryService;
	
	@Resource(name = "bbsCtgrySubService")
	private BbsCtgrySubService bbsCtgrySubService;
	
	@Resource(name = "bbsEstbsService")
	private BbsEstbsService bbsEstbsService;
	
	@Resource(name = "bbsSknService")
	private BbsSknService bbsSknService;
	
	@Resource(name = "bbsFieldService")
	private BbsFieldService bbsFieldService;
	
	@Resource(name = "bbsRoleService")
	private BbsRoleService bbsRoleService;
	
	@Resource(name = "bbsThumbService")
	private BbsThumbService bbsThumbService;
	
	@Resource(name = "userRoleService")
	private UserRoleService userRoleService;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	@Autowired
	private CodeHelper codeHelper;
	
	/**
	 * @Method Name	: aBbsEstbsList
	 * @작성일		: 2019. 11. 20.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판_설정 목록 화면
	 */
	@RequestMapping(value = "/bbs/estbs/list.do")
	public String aBbsEstbsList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BbsEstbsSearchVO searchVO,
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
		return "mngr/bbs/estbs_list";
	}
	
	/**
	 * @Method Name	: aBbsEstbsGetList
	 * @작성일		: 2019. 11. 20.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판_설정 목록 조회
	 */
	@RequestMapping(value = "/bbs/estbs/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aBbsEstbsGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BbsEstbsSearchVO searchVO,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<BbsEstbsVO> itemList = null;
		try {
			int tCount = bbsEstbsService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			itemList = bbsEstbsService.selectPageList(searchVO);
			
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
	 * @Method Name	: aBbsEstbsForm
	 * @작성일		: 2019. 11. 30.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판_설정 등록/수정 화면
	 */
	@RequestMapping(value = "/bbs/estbs/form.do")
	public String aBbsEstbsForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BbsEstbsSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		BbsEstbsVO item = new BbsEstbsVO();
		try {
			if(searchVO.getBbsEstbsSn() != null && searchVO.getBbsEstbsSn() > 0) {
				// 일련번호가 있으면 수정
				BbsEstbsVO bbsEstbsVO = new BbsEstbsVO();
				bbsEstbsVO.setBbsEstbsSn(searchVO.getBbsEstbsSn());
				item = bbsEstbsService.selectObj(bbsEstbsVO);
				
				// 필드 정보 가져오기
				BbsFieldVO bbsFieldVO = new BbsFieldVO();
				bbsFieldVO.setBbsEstbsSn(item.getBbsEstbsSn());
				List<BbsFieldVO> fieldList = bbsFieldService.selectList(bbsFieldVO);
				model.addAttribute("fieldList_s", fieldList);
				
				// 필드 목록 순서 가져오기
				bbsFieldVO.setFieldListAt("Y");
				bbsFieldVO.setOrderBy("LIST_ORDR ASC");
				List<BbsFieldVO> fieldList_l = bbsFieldService.selectList(bbsFieldVO);
				model.addAttribute("fieldList_l", fieldList_l);
				
				// 필드 보기 순서 가져오기
				bbsFieldVO = new BbsFieldVO();
				bbsFieldVO.setBbsEstbsSn(item.getBbsEstbsSn());
				bbsFieldVO.setFieldViewAt("Y");
				bbsFieldVO.setOrderBy("VIEW_ORDR ASC");
				List<BbsFieldVO> fieldList_v = bbsFieldService.selectList(bbsFieldVO);
				model.addAttribute("fieldList_v", fieldList_v);
				
				// 카테고리 정보 가져오기
				BbsCtgryVO bbsCtgryVO = new BbsCtgryVO();
				bbsCtgryVO.setBbsEstbsSn(item.getBbsEstbsSn());
				List<BbsCtgryVO> cateList = bbsCtgryService.selectList(bbsCtgryVO);
				model.addAttribute("cateList", cateList);
				
				// 연결 역할 정보 가져오기
				BbsRoleVO bbsRoleVO = new BbsRoleVO();
				bbsRoleVO.setBbsEstbsSn(item.getBbsEstbsSn());
				List<BbsRoleVO> bbsRoleList = bbsRoleService.selectList(bbsRoleVO);
				model.addAttribute("bbsRoleList", bbsRoleList);
				
				// 썸네일 정보 가져오기
				BbsThumbVO bbsThumbVO = new BbsThumbVO();
				bbsThumbVO.setBbsEstbsSn(item.getBbsEstbsSn());
				List<BbsThumbVO> thumbList = bbsThumbService.selectList(bbsThumbVO);
				model.addAttribute("thumbList", thumbList);
			} else {
				// 등록 일때는 등록 권한 필요
				if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
					logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
					throw new WrongApproachException("C0041");
				}
			}
			
			// 스킨 1뎁스 목록 가져오기
			BbsSknVO bbsSknVO = new BbsSknVO();
			bbsSknVO.setBbsSknDp(1);
			bbsSknVO.setUseAt("Y");
			List<BbsSknVO> sknList1 = bbsSknService.selectList(bbsSknVO);
			
			// 스킨 정보가 있으면 하위 스킨 정보 조회
			List<BbsSknVO> sknList2 = null;
			if(item != null && item.getSknSnDp1() != null && item.getSknSnDp1() > 0) {
				bbsSknVO.setBbsSknUpperSn(item.getSknSnDp1());
				bbsSknVO.setBbsSknDp(2);
				bbsSknVO.setUseAt("Y");
				sknList2 = bbsSknService.selectList(bbsSknVO);
			}
			
			// 역할 목록 조회
			List<UserRoleVO> roleWwwList = userRoleService.selectList(null);
			
			model.addAttribute("roleWwwList",   roleWwwList);
			model.addAttribute("regNmTypeList", codeHelper.getList("BBS005"));
			model.addAttribute("roleList",      codeHelper.getList("BBS004"));
			model.addAttribute("fieldList_cd",  codeHelper.getList("BBS002"));
			model.addAttribute("sknList1",      sknList1);
			model.addAttribute("sknList2",      sknList2);
		} catch (Exception e) {
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("role",   role);
		model.addAttribute("item",   item);
		model.addAttribute("admURI", admURI);
		return "mngr/bbs/estbs_form";
	}
	
	/**
	 * @Method Name	: aBbsEstbsInsert
	 * @작성일		: 2019. 11. 30.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판_설정 정보 저장
	 */
	@RequestMapping(value = "/bbs/estbs/insert.do", method = RequestMethod.POST)
	public String aBbsEstbsInsert(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") BbsEstbsVO vo,
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
			bbsEstbsService.insertInfo(request, vo, admSession.getMngrId());
		} catch (Exception e) {
			logger.error("게시판_설정 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0001");
		}
		
		model.addAttribute("url", "/" + admURI + "/bbs/estbs/list.do?key=" + key);
		model.addAttribute("msg", "저장 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aBbsEstbsUpdate
	 * @작성일		: 2019. 11. 30.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판_설정 정보 수정
	 */
	@RequestMapping(value = "/bbs/estbs/update.do", method = RequestMethod.POST)
	public String aBbsEstbsUpdate(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BbsEstbsSearchVO searchVO,
			@ModelAttribute("vo") BbsEstbsVO vo,
			@RequestParam(value = "bbsEstbsSn", required = true) int bbsEstbsSn,
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
			BbsEstbsVO bbsEstbsVO = new BbsEstbsVO();
			bbsEstbsVO.setBbsEstbsSn(bbsEstbsSn);
			BbsEstbsVO item = bbsEstbsService.selectObj(bbsEstbsVO);
			
			if(item != null) {
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				bbsEstbsService.updateInfo(request, bfData, vo, admSession.getMngrId());
			}
		} catch (Exception e) {
			logger.error("게시판_설정 정보 수정 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/bbs/estbs/form.do?key=" + key + "&bbsEstbsSn=" + bbsEstbsSn);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aBbsEstbsDeleteForList
	 * @작성일		: 2019. 11. 30.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판_설정 선택정보 삭제
	 */
	@RequestMapping(value = "/bbs/estbs/deleteForList.do", method = RequestMethod.POST)
	public String aBbsEstbsDeleteForList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BbsEstbsSearchVO searchVO,
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
					
					BbsEstbsVO bbsEstbsVO = new BbsEstbsVO();
					bbsEstbsVO.setBbsEstbsSn(Integer.parseInt(itemKey));
					BbsEstbsVO item = bbsEstbsService.selectObj(bbsEstbsVO);
					
					if(item != null) {
						item.setUpdtDe(null);
						String bfData = JSONArray.fromObject(item).toString();
						bbsEstbsService.deleteInfo(request, bfData, item, admSession.getMngrId());
					}
				}
			} catch (Exception e) {
				logger.error("게시판_설정 정보 삭제 오류 : " + e.getMessage());
				throw new WrongApproachException("C0004");
			}
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/bbs/estbs/list.do?key=" + key);
		model.addAttribute("msg",    "선택한 정보를 삭제 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: addSubCatagry
	 * @작성일		: 2020. 07. 01.
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: 하위 카테고리 추가
	 */
	@RequestMapping(value = "/bbs/estbs/ctgryForm.do")
	public String addSubCatagryForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") BbsEstbsSearchVO searchVO,
			@ModelAttribute("vo") BbsEstbsVO vo,
			//@ModelAttribute("vo") BbsCtgrySubVO vo,
			@RequestParam(value = "bbsEstbsSn", required = true) int bbsEstbsSn,
			@RequestParam(value = "ctgrySn", required = true) int bbsCtgrySn,
			MngrVO admSession,
			String key,
			Boolean refAt,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		BbsCtgrySubVO cvo = new BbsCtgrySubVO();
		BbsCtgryVO item = new BbsCtgryVO();
		List<BbsCtgrySubVO> cateSubList = null;
		try {
			BbsCtgryVO bbsCtgryVO = new BbsCtgryVO();
			bbsCtgryVO.setBbsCtgrySn(bbsCtgrySn);
			item = bbsCtgryService.selectObj(bbsCtgryVO);
			
			if(item != null) {
				cvo.setBbsCtgrySn(bbsCtgrySn);
				cateSubList = bbsCtgrySubService.selectList(cvo);
			}
			/*if(item != null) {
				//String bfData = JSONArray.fromObject(item).toString();
				//bbsCtgrySubService.updateInfo(request, null, cvo, admSession.getMngrId());
				//bbsCtgrySubService.insertInfo(request, cvo, admSession.getMngrId());
			}*/
		} catch (Exception e) {
			logger.error("게시판_카테고리 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		String action = "I";
		if(cateSubList.size() > 0) {
			action = "U";
		}
		
		model.addAttribute("bbsEstbsSn",bbsEstbsSn);
		model.addAttribute("action",	 action);
		model.addAttribute("formAt",	"Y");
		model.addAttribute("item",		item);
		model.addAttribute("cateList",	cateSubList);
		model.addAttribute("role",		role);
		model.addAttribute("admURI",	admURI);
		return "mngr/bbs/ctgry_form";
	}
	
	/**
	 * @Method Name	: addSubCatagry
	 * @작성일		: 2020. 07. 01.
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: 하위 카테고리 추가
	 */
	@RequestMapping(value = "/bbs/estbs/insertCtgry.do", method = RequestMethod.POST)
	public String insertSubCatagry(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			//@ModelAttribute("searchVO") BbsEstbsSearchVO searchVO,
			@ModelAttribute("vo") BbsCtgryVO vo,
			//@ModelAttribute("vo") BbsCtgrySubVO vo,
			@RequestParam(value = "bbsEstbsSn", required = true) int bbsEstbsSn,
			@RequestParam(value = "bbsCtgrySn", required = true) int bbsCtgrySn,
			MngrVO admSession,
			String key,
			Boolean refAt,
			ModelMap model) {
		
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		BbsCtgrySubVO cvo = new BbsCtgrySubVO();
		
		try {
			BbsCtgryVO bbsCtgryVO = new BbsCtgryVO();
			bbsCtgryVO.setBbsCtgrySn(bbsCtgrySn);
			BbsCtgryVO item = bbsCtgryService.selectObj(bbsCtgryVO);
			cvo.setBbsCtgrySn(bbsCtgrySn);
			
			if(item != null) {
				//String bfData = JSONArray.fromObject(item).toString();
				//bbsCtgrySubService.updateInfo(request, null, cvo, admSession.getMngrId());
				bbsCtgrySubService.insertSubCtgry(request, cvo, admSession.getMngrId());
			}
		} catch (Exception e) {
			logger.error("게시판_서브카테고리_입력_오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/bbs/estbs/ctgryForm.do?key=" + key + "&ctgrySn=" + bbsCtgrySn + "&bbsEstbsSn=" + bbsEstbsSn);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: addSubCatagry
	 * @작성일		: 2020. 07. 01.
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: 하위 카테고리 추가
	 */
	@RequestMapping(value = "/bbs/estbs/updateCtgry.do", method = RequestMethod.POST)
	public String updateSubCatagry(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			//@ModelAttribute("searchVO") BbsEstbsSearchVO searchVO,
			@ModelAttribute("vo") BbsCtgryVO vo,
			//@ModelAttribute("vo") BbsCtgrySubVO vo,
			@RequestParam(value = "bbsEstbsSn", required = true) int bbsEstbsSn,
			@RequestParam(value = "bbsCtgrySn", required = true) int bbsCtgrySn,
			MngrVO admSession,
			String key,
			Boolean refAt,
			ModelMap model) {
		
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		BbsCtgrySubVO cvo = new BbsCtgrySubVO();
		
		try {
			BbsCtgryVO bbsCtgryVO = new BbsCtgryVO();
			bbsCtgryVO.setBbsCtgrySn(bbsCtgrySn);
			BbsCtgryVO item = bbsCtgryService.selectObj(bbsCtgryVO);
			cvo.setBbsCtgrySn(bbsCtgrySn);
			
			if(item != null) {
				//String bfData = JSONArray.fromObject(item).toString();
				//bbsCtgrySubService.updateInfo(request, null, cvo, admSession.getMngrId());
				bbsCtgrySubService.updateSubCtgry(request, cvo, admSession.getMngrId());
			}
		} catch (Exception e) {
			logger.error("게시판_서브카테고리_수정_오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/bbs/estbs/ctgryForm.do?key=" + key + "&ctgrySn=" + bbsCtgrySn + "&bbsEstbsSn=" + bbsEstbsSn);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}

}
