package fscms.mods.app.web;

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

import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.vo.RoleVO;
import fscms.mods.app.service.AppVersionService;
import fscms.mods.app.vo.AppVersionVO;
import fscms.mods.atchmnfl.service.AtchmnflService;
import fscms.mods.code.util.CodeHelper;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import fscms.mods.popup.web.A_PopupController;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class AppVersionController {
	
	static Logger logger = LogManager.getLogger(A_PopupController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "atchmnflService")
	private AtchmnflService atchmnflService;
	
	@Autowired
	private CodeHelper codeHelper;
	
	@Resource(name = "appVersionService")
	private AppVersionService appVersionService;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	
	@RequestMapping(value = "/app/version/form.do")
	public String appVersionForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") AppVersionVO vo,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		AppVersionVO item = null;
		try {
			item = appVersionService.selectObj(vo);
		} catch (Exception e) {
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		
		model.addAttribute("role",   role);
		model.addAttribute("admURI", admURI);
		model.addAttribute("item", item);
		return "mngr/app/version/form";
	}
	
	@RequestMapping(value = "/app/version/update.do")
	public String appVersionUpdate(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") AppVersionVO vo,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		AppVersionVO item = null;
		try {
			item = appVersionService.selectObj(vo);
			if(item != null) {
			String bfData = JSONArray.fromObject(item).toString();
				vo.setUpdtId(admSession.getMngrId());
				appVersionService.updateAppVersionInfo(request, bfData, vo, admSession.getMngrId());
			}
		}catch (Exception e) {
			logger.error("데이터 수정 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		
		model.addAttribute("role",   role);
		model.addAttribute("admURI", admURI);
		model.addAttribute("url", "/" + admURI + "/app/version/form.do?key=" + key);
		model.addAttribute("msg", "수정 하였습니다.");
		return "success";
	}
}
