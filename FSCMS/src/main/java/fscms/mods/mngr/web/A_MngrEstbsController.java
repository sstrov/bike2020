package fscms.mods.mngr.web;

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
import fscms.cmm.vo.RoleVO;
import fscms.mods.mngr.service.MngrEstbsService;
import fscms.mods.mngr.service.MngrIpEstbsService;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrEstbsVO;
import fscms.mods.mngr.vo.MngrIpEstbsVO;
import fscms.mods.mngr.vo.MngrVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_MngrEstbsController {
	
	static Logger logger = LogManager.getLogger(A_MngrEstbsController.class);
	
	@Resource(name = "mngrEstbsService")
	private MngrEstbsService mngrEstbsService;
	
	@Resource(name = "mngrIpEstbsService")
	private MngrIpEstbsService mngrIpEstbsService;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	/**
	 * @Method Name	: aMngrEstbsForm
	 * @작성일		: 2019. 10. 17.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자_설정 등록 폼
	 */
	@RequestMapping(value = "/mngr/estbs/form.do")
	public String aMngrEstbsForm(
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
		
		MngrEstbsVO item;
		try {
			item = mngrEstbsService.selectObj();
			
			List<MngrIpEstbsVO> ipList = mngrIpEstbsService.selectList(null);
			model.addAttribute("ipList", ipList);
		} catch (Exception e) {
			logger.error("관리자_설정 조회 오류");
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("role",   role);
		model.addAttribute("item",   item);
		model.addAttribute("admURI", admURI);
		return "mngr/mngr/estbs_form";
	}
	
	/**
	 * @Method Name	: aMngrEstbsSave
	 * @작성일		: 2019. 10. 17.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 관리자_설정 정보 저장
	 */
	@RequestMapping(value = "/mngr/estbs/save.do", method = RequestMethod.POST)
	public String aMngrEstbsSave(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") MngrEstbsVO vo,
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
			MngrEstbsVO item = mngrEstbsService.selectObj();
			
			if(item != null) {
				String bfData = JSONArray.fromObject(item).toString();
				mngrEstbsService.updateInfo(request, bfData, vo, admSession.getMngrId());
			} else {
				mngrEstbsService.insertInfo(request, vo, admSession.getMngrId());
			}
		} catch (Exception e) {
			logger.error("관리자_설정 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0001");
		}
		
		model.addAttribute("url", "/" + admURI + "/mngr/estbs/form.do?key=" + key);
		model.addAttribute("msg", "저장 하였습니다.");
		return "success";
	}

}
