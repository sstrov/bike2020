package fscms.mods.atchmnfl.web;

import java.io.File;
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

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.util.FsFuncCmmHelper;
import fscms.cmm.util.UploadHelper;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.RoleVO;
import fscms.cmm.vo.UploadFileVO;
import fscms.mods.atchmnfl.service.AtchmnflManageService;
import fscms.mods.atchmnfl.service.AtchmnflService;
import fscms.mods.atchmnfl.vo.AtchmnflManageSearchVO;
import fscms.mods.atchmnfl.vo.AtchmnflManageVO;
import fscms.mods.atchmnfl.vo.AtchmnflVO;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class AtchmnflManageController {
	
	static Logger logger = LogManager.getLogger(AtchmnflManageController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "atchmnflService")
	private AtchmnflService atchmnflService;
	
	@Resource(name = "atchmnflManageService")
	private AtchmnflManageService atchmnflManageService;
	
	@Autowired
	private UploadHelper uploadHelper;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	@RequestMapping(value = "/atchmnfl/mngr/list.do")
	public String atchmnflMngrList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") AtchmnflManageSearchVO searchVO,
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
		return "mngr/atchmnfl/list";
	}
	
	@RequestMapping(value = "/atchmnfl/mngr/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String atchmnflMngrGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") AtchmnflManageSearchVO searchVO,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<AtchmnflManageVO> itemList = null;
		try {
			int tCount = atchmnflManageService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			itemList = atchmnflManageService.selectPageList(searchVO);
			
			if(itemList != null && itemList.size() > 0) {
				itemList.get(0).settCount(tCount);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(itemList).toString();
	}
	
	@RequestMapping(value = "/atchmnfl/mngr/form.do")
	public String atchmnflMngrForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") AtchmnflManageSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		AtchmnflManageVO item = new AtchmnflManageVO();
		try {
			if(searchVO.getAtchmnflManageSn() != null && searchVO.getAtchmnflManageSn() > 0) {
				// 일련번호가 있으면 수정
				AtchmnflManageVO atchmnflManageVO = new AtchmnflManageVO();
				atchmnflManageVO.setAtchmnflManageSn(searchVO.getAtchmnflManageSn());
				item = atchmnflManageService.selectObj(atchmnflManageVO);
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
		return "mngr/atchmnfl/form";
	}
	
	@RequestMapping(value = "/atchmnfl/mngr/insert.do", method = RequestMethod.POST)
	public String atchmnflMngrInsert(
			MultipartHttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") AtchmnflManageVO vo,
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
			int maxKey = atchmnflManageService.selectMaxKey();
			
			this.setFile(request, maxKey, admSession.getMngrId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("파일 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0001");
		}
		
		model.addAttribute("url", "/" + admURI + "/atchmnfl/mngr/list.do?key=" + key);
		model.addAttribute("msg", "저장 하였습니다.");
		return "success";
	}
	
	@RequestMapping(value = "/atchmnfl/mngr/update.do", method = RequestMethod.POST)
	public String atchmnflMngrUpdate(
			MultipartHttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") AtchmnflManageSearchVO searchVO,
			@ModelAttribute("vo") AtchmnflManageVO vo,
			@RequestParam(value = "atchmnflManageSn", required = true) int atchmnflManageSn,
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
			AtchmnflManageVO atchmnflManageVO = new AtchmnflManageVO();
			atchmnflManageVO.setAtchmnflManageSn(atchmnflManageSn);
			AtchmnflManageVO item = atchmnflManageService.selectObj(atchmnflManageVO);
			
			if(item != null) {
				this.setFile(request, atchmnflManageSn, admSession.getMngrId());
			}
		} catch (Exception e) {
			logger.error("파일 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/atchmnfl/mngr/form.do?key=" + key + "&atchmnflManageSn=" + atchmnflManageSn);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}
	
	private void setFile(MultipartHttpServletRequest request, int key, String mngrId) throws Exception {
		if(request.getFile("uploadFile") != null && !request.getFile("uploadFile").isEmpty()) {
			AtchmnflManageVO atchmnflManageVO = new AtchmnflManageVO();
			atchmnflManageVO.setAtchmnflManageSn(key);
			AtchmnflManageVO item = atchmnflManageService.selectObj(atchmnflManageVO);
			
			if(item != null && item.getAtchmnflSn() != null) {
				AtchmnflVO atchmnflVO = new AtchmnflVO();
				atchmnflVO.setAtchmnflSn(item.getAtchmnflSn());
				AtchmnflVO file = atchmnflService.selectObj(atchmnflVO);
				
				if(file != null) {
					String rootPath = new FsFuncCmmHelper().getAppAbsolutePath() + file.getFlpth();
					
					// 파일 제거
					File fileObj = new File(rootPath + "/" + file.getAtchmnflRealNm());
					fileObj.delete();
				}
			}
			
			// 이미지 첨부
			UploadFileVO uploadFile = uploadHelper.upload(request, "fileMng", "uploadFile", 10, "" + key);
			
			if(uploadFile == null) {
				logger.info("이미지 첨부 실패");
			} else {
				AtchmnflVO atchmnflVO = new AtchmnflVO();
				atchmnflVO.setAccTy("fileMng");
				atchmnflVO.setAccSn("" + key);
				atchmnflVO.setAtchmnflOrdr(1);
				atchmnflVO.setAtchmnflNm(uploadFile.getFileName().replaceAll("." + uploadFile.getFileExt(), ""));
				atchmnflVO.setAtchmnflRealNm(uploadFile.getServerFileName());
				atchmnflVO.setAtchmnflSize((int) uploadFile.getFileSize());
				atchmnflVO.setAtchmnflTy(uploadFile.getFileType());
				atchmnflVO.setAtchmnflExtsn(uploadFile.getFileExt());
				atchmnflVO.setFlpth(uploadHelper.getRelativePath("fileMng", "") + "/" + key);
				atchmnflVO.setReprsntThumbAt("N");
				
				if(item != null && item.getAtchmnflManageSn() != null) {
					atchmnflVO.setAtchmnflSn(item.getAtchmnflSn());
					atchmnflService.updateInfo(request, null, atchmnflVO, mngrId);
				} else {
					atchmnflService.insertInfo(request, atchmnflVO, mngrId);
					
					atchmnflManageVO = new AtchmnflManageVO();
					atchmnflManageVO.setAtchmnflManageSn(key);
					atchmnflManageVO.setAtchmnflSn(atchmnflVO.getAtchmnflSn());
					atchmnflManageService.insertInfo(request, atchmnflManageVO, mngrId);
				}
			}
		}
	}
	
	@RequestMapping(value = "/atchmnfl/mngr/deleteForList.do", method = RequestMethod.POST)
	public String atchmnflMngrDeleteForList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") AtchmnflManageSearchVO searchVO,
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
				FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
				for (String itemKey : chkKey) {
					if(StringUtils.isEmpty(itemKey)) {
						continue;
					}
					
					AtchmnflManageVO atchmnflManageVO = new AtchmnflManageVO();
					atchmnflManageVO.setAtchmnflManageSn(Integer.parseInt(itemKey));
					AtchmnflManageVO item = atchmnflManageService.selectObj(atchmnflManageVO);
					
					if(item != null) {
						AtchmnflVO atchmnflVO = new AtchmnflVO();
						atchmnflVO.setAtchmnflSn(item.getAtchmnflSn());
						AtchmnflVO file = atchmnflService.selectObj(atchmnflVO);
						
						if(file != null) {
							String rootPath = fsFuncCmmHelper.getAppAbsolutePath() + file.getFlpth();
							
							// 파일 제거
							File fileObj = new File(rootPath + "/" + file.getAtchmnflRealNm());
							fileObj.delete();
							
							String bfData = JSONArray.fromObject(file).toString();
							atchmnflService.deleteInfo(request, bfData, file, admSession.getMngrId());
						}
						
						String bfData = JSONArray.fromObject(item).toString();
						atchmnflManageService.deleteInfo(request, bfData, item, admSession.getMngrId());
					}
				}
			} catch (Exception e) {
				logger.error("파일 정보 삭제 오류 : " + e.getMessage());
				throw new WrongApproachException("C0004");
			}
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/atchmnfl/mngr/list.do?key=" + key);
		model.addAttribute("msg",    "선택한 정보를 사용안함 처리 하였습니다.");
		return "success";
	}

}
