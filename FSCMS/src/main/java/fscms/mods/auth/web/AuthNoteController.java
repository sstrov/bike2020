package fscms.mods.auth.web;

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

import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.util.UploadHelper;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.RoleVO;
import fscms.mods.atchmnfl.service.AtchmnflService;
import fscms.mods.auth.service.AuthNoteService;
import fscms.mods.auth.service.AuthNoteServiceImpl;
import fscms.mods.auth.vo.AuthNoteSearchVO;
import fscms.mods.auth.vo.AuthNoteVO;
import fscms.mods.auth.vo.AuthUploadSearchVO;
import fscms.mods.code.util.CodeHelper;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import fscms.mods.user.service.UserService;
import fscms.mods.user.vo.UserSearchVO;
import fscms.mods.user.vo.UserVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/{admURI}")
public class AuthNoteController {

	static Logger logger = LogManager.getLogger(AuthNoteController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "atchmnflService")
	private AtchmnflService atchmnflService;
	
	@Autowired
	private UploadHelper uploadHelper;
	
	@Autowired
	private CodeHelper codeHelper;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	@Resource(name = "authNoteService")
	private AuthNoteService authNoteService;
	
	@Resource(name = "userService")
	private UserService userService;
	
	/**
	 * @Method Name	: checkNoteSn
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 인증노트 확인
	 */
	@RequestMapping(value = "/auth/note/checkNoteSn.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String aAuthNoteCheckNoteSn(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@RequestParam(value = "noteSn", required = true) String noteSn,
			@RequestParam(value = "userNm", required = true) String userNm,
			MngrVO admSession,
			String key,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		if(noteSn.length() > 11 && noteSn.length() < 14) {
			if(checkValidNotebooSn(noteSn)){
				try {
					
					AuthNoteVO vo = new AuthNoteVO();
					vo.setNoteSn(noteSn);
					AuthNoteVO item = authNoteService.selectObj(vo);
					
					if(item != null) {
						jsonVO.setMsg("이미 사용중인 인증수첩 번호입니다.");
						jsonVO.setState("N");
						return JSONObject.fromObject(jsonVO).toString();
					}
					
					AuthNoteSearchVO searchVO = new AuthNoteSearchVO();
					searchVO.setNoteSn(noteSn);
					searchVO.setUserNm(userNm);
					List<AuthNoteVO> auth = authNoteService.checkNoteSn(searchVO);
					
					if(auth.size() > 0) {
						if(userNm.equals(auth.get(0).getUserNm())) {
							jsonVO.setState("Y");
							return JSONObject.fromObject(jsonVO).toString();
						}else {
							jsonVO.setMsg("사용자의 이름이 일치하지 않습니다.");
							jsonVO.setState("N");
							return JSONObject.fromObject(jsonVO).toString();
						}
					}else {
						jsonVO.setState("Y");
						return JSONObject.fromObject(jsonVO).toString();
					}
				} catch (Exception e) {
					//System.out.println("eeeeee" + e);
					jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0003"));
					return JSONObject.fromObject(jsonVO).toString();
				}
			}else {
				jsonVO.setMsg("유효하지 않은 인증번호입니다.");
				jsonVO.setState("N");
				return JSONObject.fromObject(jsonVO).toString();
			}
		}else {
			jsonVO.setMsg("유효하지 않은 인증번호입니다.");
			jsonVO.setState("N");
			return JSONObject.fromObject(jsonVO).toString();
		}
	}
	
	
	
	private boolean checkValidNotebooSn(String notebook_sn) {
		String prefix = notebook_sn.substring(0, 2);
		String suffix = notebook_sn.substring(2);
		String yearfix = notebook_sn.substring(2,6);
//		String monthfix = notebook_sn.substring(6,8);
//		String dayfix = notebook_sn.substring(8,10);

		boolean isEng = true;
		boolean isNum = true;

		for(int i=0;i<prefix.length();i++){
            char c=prefix.charAt(i);
            if( ( 0xAC00 <= c && c <= 0xD7A3 ) || ( 0x3131 <= c && c <= 0x318E ) ){
            	isEng = false;
            }else if( ( 0x61 <= c && c <= 0x7A ) || ( 0x41 <= c && c <= 0x5A ) ){
            }else if( 0x30 <= c && c <= 0x39 ){
            	isEng = false;
            }else{
            	isEng = false;
            }
		}
		for(int j=0;j<suffix.length();j++){
            char c=suffix.charAt(j);
            if( ( 0xAC00 <= c && c <= 0xD7A3 ) || ( 0x3131 <= c && c <= 0x318E ) ){
            	isNum = false;
            }else if( ( 0x61 <= c && c <= 0x7A ) || ( 0x41 <= c && c <= 0x5A ) ){
            	isNum = false;
            }else if( 0x30 <= c && c <= 0x39 ){
            }else{
            	isNum = false;
            }
		}

		if(isNum) {
			int year = Integer.parseInt(yearfix);
			if(2030<=year || year<2001){
				isNum = false;
			}
		}


		if(isEng && isNum) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * @Method Name	: authListGetList
	 * @작성일		: 2019. 10. 11.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 노트삭제
	 */
	@RequestMapping(value = "/auth/note/deleteNoteSn.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String authNoteDelete(
			HttpServletRequest request,
			@PathVariable String admURI,
			@RequestParam(value = "noteSn", required = true) String noteSn,
			@RequestParam(value = "userSn", required = true) int userSn,
			MngrVO admSession,
			Boolean refAtAjax) {
		
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		if(StringUtils.isEmpty(noteSn)) {
			jsonVO.setMsg("수첩번호가 없습니다.");
			return JSONObject.fromObject(jsonVO).toString();
		}
		AuthNoteVO item = null;
		AuthNoteVO vo = new AuthNoteVO();
		vo.setNoteSn(noteSn);
		vo.setUserSn(userSn);
		
		try {
			
			item = authNoteService.selectObj(vo);
			
			if(item != null) {
				authNoteService.deleteAuthNote(vo);
				if(item.getAuthYn().equals("Y")) {
					
					
					UserVO userVO = new UserVO();
					userVO.setUserSn(userSn);
					userVO.setNoteSn("");
					String bfData = JSONObject.fromObject(userService.selectObj(userVO)).toString();
					
					
					userService.deleteNote(request, bfData, userVO, admSession.getMngrId());
				}
				
			}
			jsonVO.setMsg("삭제가 완료되었습니다.");
			return JSONObject.fromObject(jsonVO).toString();
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONObject.fromObject(jsonVO).toString();
		}
	}
	
	@RequestMapping(value = "/auth/note/registAuthNoteSn.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String authNoteadd(
			HttpServletRequest request,
			@PathVariable String admURI,
			@RequestParam(value = "noteSn", required = true) String noteSn,
			@RequestParam(value = "userSn", required = true) int userSn,
			MngrVO admSession,
			Boolean refAtAjax) {
		
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		if(StringUtils.isEmpty(noteSn)) {
			jsonVO.setMsg("수첩번호가 없습니다.");
			return JSONObject.fromObject(jsonVO).toString();
		}
		List<AuthNoteVO> itemList = null;
		AuthNoteSearchVO searchVO = new AuthNoteSearchVO();
		searchVO.setUserSn(userSn);
		try {
			
			itemList = authNoteService.selectNoteList(searchVO);
			
			if(itemList != null) {
				for(int i=0;i<itemList.size();i++) {
					AuthNoteVO a = new AuthNoteVO();
					AuthNoteVO vo = new AuthNoteVO();
					a = itemList.get(i);
					if(a.getNoteSn().equals(noteSn)) {
						vo.setAuthYn("Y");
					}else {
						vo.setAuthYn("N");
					}
					vo.setNoteSn(a.getNoteSn());
					vo.setUserSn(userSn);
					
					String bfData = JSONObject.fromObject(a).toString();
					authNoteService.updateAuthNote(request, bfData, vo, admSession.getMngrId());
					
					
				}
			}
			jsonVO.setMsg("인증수첩이 변경되었습니다.");
			return JSONObject.fromObject(jsonVO).toString();
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONObject.fromObject(jsonVO).toString();
		}
	}
	
	/**
	 * @Method Name	: aAuthList
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자 목록 화면
	 */
	@RequestMapping(value = "/auth/list.do")
	public String aAuthList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") AuthUploadSearchVO searchVO,
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
		return "mngr/noteAuth/list";
	}
	
	
}
