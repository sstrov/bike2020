package fscms.mods.site.web;

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
import fscms.cmm.vo.RoleVO;
import fscms.mods.code.util.CodeHelper;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import fscms.mods.site.service.SiteService;
import fscms.mods.site.vo.SiteVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_SiteController {
	
	static Logger logger = LogManager.getLogger(A_SiteController.class);
	
	@Resource(name = "codeHelper")
	private CodeHelper codeHelper;
	
	@Resource(name = "siteService")
	private SiteService siteService;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	/**
	 * @Method Name	: aSiteForm
	 * @작성일		: 2019. 10. 8.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사이트 관리 등록 폼
	 */
	@RequestMapping(value = "/site/form.do")
	public String aSiteForm(
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
		
		SiteVO item;
		try {
			item = siteService.selectObj();
			
			// 활성 상태 코드
			model.addAttribute("actList", codeHelper.getList("SIT001"));
		} catch (Exception e) {
			logger.error("사이트 정보 조회 오류");
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("role",   role);
		model.addAttribute("item",   item);
		model.addAttribute("admURI", admURI);
		return "mngr/site/form";
	}
	
	/**
	 * @Method Name	: aSiteUpdate
	 * @작성일		: 2019. 10. 8.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사이트 정보 저장
	 */
	@RequestMapping(value = "/site/save.do", method = RequestMethod.POST)
	public String aSiteSave(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") SiteVO vo,
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
			SiteVO item = siteService.selectObj();
			
			if(item != null) {
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				siteService.updateInfo(request, bfData, vo, admSession.getMngrId());
			} else {
				siteService.insertInfo(request, vo, admSession.getMngrId());
			}
		} catch (Exception e) {
			logger.error("사이트 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0001");
		}
		
		model.addAttribute("url", "/" + admURI + "/site/form.do?key=" + key);
		model.addAttribute("msg", "저장 하였습니다.");
		return "success";
	}

}
