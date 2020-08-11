package fscms.mods.road.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.vo.JsonVO;
import fscms.mods.atchmnfl.service.AtchmnflService;
import fscms.mods.code.util.CodeHelper;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import fscms.mods.popup.web.A_PopupController;
import fscms.mods.road.service.RoadService;
import fscms.mods.road.vo.RoadVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/{admURI}")
public class RoadController {

	static Logger logger = LogManager.getLogger(A_PopupController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "atchmnflService")
	private AtchmnflService atchmnflService;
	
	@Autowired
	private CodeHelper codeHelper;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	@Resource(name = "roadService")
	private RoadService roadService;
	
	/**
	 * @Method Name	: authUploadUpdateAuth
	 * @작성일		: 
	 * @작성자		: 
	 * @변경이력		:
	 * @Method 설명	: 인증수첩_인증 수정
	 */
	@RequestMapping(value = "/road/auth/authConfirm", produces = "application/json; charset=UTF-8")
	public @ResponseBody String authUploadUpdateAuth(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") RoadVO vo,
			@RequestParam("chkbox") String[] chkbox,
			@RequestParam("auth") String[] auth,
			//@RequestParam(value = "auth", required = true) String auth,
			@RequestParam(value = "userSn", required = true) int userSn,
			@RequestParam(value = "noteSn", required = true) String noteSn,
			Boolean refAtAjax,
			MngrVO admSession
			) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONObject.fromObject(jsonVO).toString();
		}
		boolean ck = true;
		
		try {
			RoadVO searchVO = new RoadVO();
			searchVO.setUserSn(userSn);
			searchVO.setNoteSn(noteSn);
			
			List<RoadVO> itemList = roadService.selectConfirmList(searchVO);
			String bfData = JSONArray.fromObject(itemList).toString();
			
			roadService.deleteAuthConfirm(request, bfData, searchVO, admSession.getMngrId());
			for(int i=0; i < chkbox.length; i++) {
				String sp[] = chkbox[i].split(",");
				String ac = sp[0];
				String rc = sp[1];
				for(int t=0; t<auth.length; t++) {
					if(chkbox[i].equals(auth[t])) {
						ck = false;
					}
				}
				if(ck) {
					RoadVO roadVO = new RoadVO();
					roadVO.setUserSn(userSn);
					roadVO.setNoteSn(noteSn);
					roadVO.setAuthCd(ac);
					roadVO.setRoadCd(rc);
					roadService.insertAuthConfirm(request, roadVO, admSession.getMngrId());
				}
			}
			//roadService.insertRoadAuth();
			jsonVO.setMsg("등록이 완료되었습니다.");
			return JSONObject.fromObject(jsonVO).toString();
		} catch (Exception e) {
			System.out.println("exc : " + e);
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONObject.fromObject(jsonVO).toString();
		}
	}
}
