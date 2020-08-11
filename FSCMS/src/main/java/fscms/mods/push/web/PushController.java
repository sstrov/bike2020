package fscms.mods.push.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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
import fscms.cmm.util.PropConnHelper;
import fscms.cmm.util.UploadHelper;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.RoleVO;
import fscms.mods.atchmnfl.service.AtchmnflService;
import fscms.mods.atchmnfl.vo.AtchmnflVO;
import fscms.mods.code.util.CodeHelper;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import fscms.mods.push.service.PushService;
import fscms.mods.push.vo.PushSearchVO;
import fscms.mods.push.vo.PushVO;
import fscms.mods.user.service.UserService;
import fscms.mods.user.vo.UserSearchVO;
import fscms.mods.user.vo.UserVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class PushController {

	static Logger logger = LogManager.getLogger(PushController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "atchmnflService")
	private AtchmnflService atchmnflService;
	
	@Autowired
	private CodeHelper codeHelper;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	@Autowired
	private UploadHelper uploadHelper;
	
	@Resource(name = "pushService")
	private PushService pushService;
	
	@Resource(name = "userService")
	private UserService userService;

	
	/**
	 * @Method Name	: aPushList
	 * @작성일		: 2019. 10. 23.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 안내발송 목록 화면
	 */
	@RequestMapping(value = "/push/user/list.do")
	public String aPushUserList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") UserSearchVO searchVO,
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
		return "mngr/push/user_list";
	}
	
	/**
	 * @Method Name	: aPushList
	 * @작성일		: 2019. 10. 23.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 안내발송 목록 화면
	 */
	@RequestMapping(value = "/push/list.do")
	public String aPushList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") PushSearchVO searchVO,
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
		return "mngr/push/list";
	}
	
	/**
	 * @Method Name	: aPushGetList
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 안내발송 정보 목록 조회
	 */
	@RequestMapping(value = "/push/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aPushGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") PushSearchVO searchVO,
			MngrVO admSession,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<PushVO> itemList = null;
		try {
			int tCount = pushService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			itemList = pushService.selectPageList(searchVO);
			
			if(itemList != null && itemList.size() > 0) {
				itemList.get(0).settCount(tCount);
			}
		} catch (Exception e) {
			System.out.println("eeee : "+e);
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(itemList).toString();
	}
	
	
	/**
	 * @Method Name	: aPushForm
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자 등록/수정 화면
	 */
	@RequestMapping(value = "/push/form.do")
	public String aPushForm(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") PushSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		PushVO item = new PushVO();
		List<AtchmnflVO> fileList = null;
		String registId = admSession.getMngrId();
		try {
			if(searchVO.getPushSn() != null && searchVO.getPushSn() > 0) {
				// 일련번호가 있으면 수정
				PushVO pushVO = new PushVO();
				pushVO.setPushSn(searchVO.getPushSn());
				item = pushService.selectObj(pushVO);
				
				if(StringUtils.isNotEmpty(item.getRegistId())) {
					registId = item.getRegistId();
				}
				// 첨부파일 목록 조회
				AtchmnflVO atchmnflVO = new AtchmnflVO();
				atchmnflVO.setAccTy("push");
				atchmnflVO.setAccSn("" + item.getPushSn());
				fileList = atchmnflService.selectList(atchmnflVO);
				model.addAttribute("fileList", fileList);
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
		@SuppressWarnings("static-access")
		Long uniqueId = uploadHelper.getUniqueID();
		request.getSession().setAttribute("file_" + uniqueId, (fileList != null && fileList.size() > 0)? fileList : null);
		
		model.addAttribute("registId",     registId);
		model.addAttribute("uniqueId",     uniqueId);
		model.addAttribute("role",         role);
		model.addAttribute("item",         item);
		model.addAttribute("admURI",       admURI);
		return "mngr/push/form";
	}
	
	/**
	 * @Method Name	: aPushForm
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자 등록/수정 화면
	 */
	@RequestMapping(value = "/push/view.do")
	public String aPushView(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") PushSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		PushVO item = new PushVO();
		List<AtchmnflVO> fileList = null;
		String registId = admSession.getMngrId();
		try {
			if(searchVO.getPushSn() != null && searchVO.getPushSn() > 0) {
				// 일련번호가 있으면 수정
				PushVO pushVO = new PushVO();
				pushVO.setPushSn(searchVO.getPushSn());
				item = pushService.selectObj(pushVO);
				
				if(StringUtils.isNotEmpty(item.getRegistId())) {
					registId = item.getRegistId();
				}
				// 첨부파일 목록 조회
				AtchmnflVO atchmnflVO = new AtchmnflVO();
				atchmnflVO.setAccTy("push");
				atchmnflVO.setAccSn("" + item.getPushSn());
				fileList = atchmnflService.selectList(atchmnflVO);
				model.addAttribute("fileList", fileList);
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
		
		@SuppressWarnings("static-access")
		Long uniqueId = uploadHelper.getUniqueID();
		request.getSession().setAttribute("file_" + uniqueId, (fileList != null && fileList.size() > 0)? fileList : null);
		
		model.addAttribute("registId",     registId);
		model.addAttribute("uniqueId",     uniqueId);
		model.addAttribute("role",         role);
		model.addAttribute("item",         item);
		model.addAttribute("admURI",       admURI);
		return "mngr/push/view";
	}
	
	/**
	 * @Method Name	: aPushInsert
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 안내발송 정보 저장
	 */
	@RequestMapping(value = "/push/insert.do", method = RequestMethod.POST)
	public String aPushInsert(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("vo") PushVO vo,
			@RequestParam(value = "uniqueId", required = true) String uniqueId,
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
			pushService.insertInfo(request, vo, uniqueId, admSession.getMngrId());
			
		} catch (Exception e) {
			logger.error("안내발송 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0001");
		}
		
		model.addAttribute("url", "/" + admURI + "/push/list.do?key=" + key);
		model.addAttribute("msg", "저장 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aPushUpdate
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 안내발송 정보 수정
	 */
	@RequestMapping(value = "/push/update.do", method = RequestMethod.POST)
	public String aPushUpdate(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") PushSearchVO searchVO,
			@ModelAttribute("vo") PushVO vo,
			@RequestParam(value = "pushSn", required = true) int pushSn,
			@RequestParam(value = "uniqueId", required = true) String uniqueId,
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
			PushVO pushVO = new PushVO();
			pushVO.setPushSn(pushSn);
			PushVO item = pushService.selectObj(pushVO);
			
			if(item != null) {
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				pushService.updateInfo(request, bfData, vo, uniqueId, admSession.getMngrId());
				
			}
		} catch (Exception e) {
			logger.error("안내발송 정보 저장 오류 : " + e.getMessage());
			throw new WrongApproachException("C0003");
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/push/form.do?key=" + key + "&pushSn=" + pushSn);
		model.addAttribute("msg",    "수정 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aPushDeleteForList
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자 선택정보 삭제
	 */
	@RequestMapping(value = "/push/deleteForList.do", method = RequestMethod.POST)
	public String aPushDeleteForList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") PushSearchVO searchVO,
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
					
					PushVO pushVO = new PushVO();
					pushVO.setPushSn(Integer.parseInt(itemKey));
					PushVO item = pushService.selectObj(pushVO);
					
					if(item != null) {
						item.setUpdtDe(null);
						String bfData = JSONArray.fromObject(item).toString();
						pushService.deleteInfo(request, bfData, item, admSession.getMngrId());
					}
				}
			} catch (Exception e) {
				logger.error("안내발송 정보 삭제 오류 : " + e.getMessage());
				throw new WrongApproachException("C0004");
			}
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/push/list.do?key=" + key);
		model.addAttribute("msg",    "선택한 정보를 삭제 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aPushDeleteForList
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사용자 선택정보 삭제
	 */
	@RequestMapping(value = "/push/delete.do", method = RequestMethod.POST)
	public String aPushDelete(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") PushSearchVO searchVO,
			MngrVO admSession,
			String key,
			Boolean refAt,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowW()) || !StringUtils.equals(role.getPowW(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		if(searchVO.getPushSn() != null && searchVO.getPushSn() > 0) {
			try {
				
				PushVO pushVO = new PushVO();
				pushVO.setPushSn(searchVO.getPushSn());
				PushVO item = pushService.selectObj(pushVO);
				
				if(item != null) {
					item.setUpdtDe(null);
					String bfData = JSONArray.fromObject(item).toString();
					pushService.deleteInfo(request, bfData, item, admSession.getMngrId());
				}
			} catch (Exception e) {
				logger.error("안내발송 정보 삭제 오류 : " + e.getMessage());
				throw new WrongApproachException("C0004");
			}
		}
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/push/list.do?key=" + key);
		model.addAttribute("msg",    "선택한 정보를 삭제 하였습니다.");
		return "success";
	}
	
	/**
	 * @Method Name	: aPushSend
	 * @작성일		: 2019. 10. 16.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 메일/sms 발송
	 */
	@RequestMapping(value = "/push/send.do", method = RequestMethod.POST)
	public String aPushSend(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") UserSearchVO searchVO,
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
		List<UserVO> userList = new ArrayList<UserVO>();
		
		if(chkKey != null && chkKey.length > 0) {
			try {
				for (String itemKey : chkKey) {
					if(StringUtils.isEmpty(itemKey)) {
						continue;
					}
					
					UserVO userVO = new UserVO();
					userVO.setUserSn(Integer.parseInt(itemKey));
					UserVO item = userService.selectObj(userVO);

					if(item != null) {
						userList.add(item);
					}
				}
				
				sendMail(userList);
				/*if(StringUtils.isNotEmpty(item.getUserEmailRecptnAt()) && item.getUserEmailRecptnAt().equals("Y")) {
					mailList.add(item);
				}
				if(StringUtils.isNotEmpty(item.getUserMbtlnumRecptnAt()) && item.getUserMbtlnumRecptnAt().equals("Y")) {
					smsList.add(item);
				}*/
				
				PushVO pushVO = new PushVO();
				
				pushVO.setPushType("S");
				int smsPushSn = pushService.selectMaxPushSn(pushVO);
				pushVO.setPushSn(smsPushSn);
				PushVO sms = pushService.selectObj(pushVO);
				// 첨부 목록 가져오기
				AtchmnflVO atchmnflVO = new AtchmnflVO();
				atchmnflVO.setAccTy("push");
				atchmnflVO.setAccSn("" + smsPushSn);
				List<AtchmnflVO> smsFile = atchmnflService.selectList(atchmnflVO);
				
				
			} catch (Exception e) {
				logger.error("안내발송 조회 오류 : " + e.getMessage());
				throw new WrongApproachException("C0004");
			}
		}
		
		
		model.addAttribute("formAt", "Y");
		model.addAttribute("url",    "/" + admURI + "/push/user/list.do?key=" + key);
		model.addAttribute("msg",    "메일을 전송 하였습니다.");
		return "success";
	}
	
	
	//smtp 메일 전송
	private void sendMail(List<UserVO> userList) {
		System.out.println("------------------------sendmail start-----------------------------------");
		List<UserVO> mailList = new ArrayList<UserVO>();
		try {
			for(int i=0;i<userList.size();i++) {
				UserVO item = userList.get(i);
				if(StringUtils.isNotEmpty(item.getUserEmail()) && item.getUserEmailRecptnAt().equals("Y")) {
					mailList.add(item);
				}
			}
			InternetAddress[] addArray = null;
			addArray = new InternetAddress[mailList.size()];
			for(int m=0;m<mailList.size();m++) {
				addArray[m] = new InternetAddress(mailList.get(m).getUserEmail());
			}
			
			PushVO pushVO = new PushVO();
			pushVO.setPushType("M");
			int mailPushSn = pushService.selectMaxPushSn(pushVO);
			pushVO.setPushSn(mailPushSn);
			PushVO mail = pushService.selectObj(pushVO);
			// 첨부 목록 가져오기
			AtchmnflVO atchmnflVO = new AtchmnflVO();
			atchmnflVO = new AtchmnflVO();
			atchmnflVO.setAccTy("push");
			atchmnflVO.setAccSn("" + mailPushSn);
			List<AtchmnflVO> file = atchmnflService.selectList(atchmnflVO);
			
			// SMTP 서버 정보를 설정한다. 
			Properties props = new Properties(); 
			props.put("mail.smtp.host", "smtp.naver.com"); 
			props.put("mail.smtp.port", 465); 
			props.put("mail.smtp.auth", "true"); 
			
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() { 
				protected PasswordAuthentication getPasswordAuthentication() { 
					return new PasswordAuthentication("firstsmart0102", "firstsmart!@34"); //네이버 계정, pw
				} 
			});
			session.setDebug(true);
		
			MimeMessage message = new MimeMessage(session); 
			message.setFrom(new InternetAddress("firstsmart0102@naver.com")); 
			//message.addRecipient(Message.RecipientType.TO, new InternetAddress("sstrov@naver.com")); 
			message.addRecipients(Message.RecipientType.TO, addArray); //다중전송
			
			message.setSubject("SMTP TEST1111"); // 메일 제목 
			//message.setText("Success!!"); // 메일 내용
			
			// 메일 내용을 설정을 위한 클래스를 설정합니다.
			message.setContent(new MimeMultipart());
			// 메일 내용을 위한 Multipart클래스를 받아온다. (위 new MimeMultipart()로 넣은 클래스입니다.)
			Multipart mp = (Multipart) message.getContent();
			// html 형식으로 본문을 작성해서 바운더리에 넣습니다.
			mp.addBodyPart(getContents(mail.getPushCn()));
			// 첨부 파일을 추가합니다.
			
			for(int f=0;f<file.size();f++) {
				AtchmnflVO fileVO = file.get(f);
				mp.addBodyPart(getFileAttachment( new PropConnHelper().getConn("uploadPath", "uploadPath.defaultPath") + fileVO.getFlpth() + "/" + fileVO.getAtchmnflRealNm() ));
			}
			
			// send the message 
			Transport.send(message); 
			System.out.println("------------------------sendmail end-----------------------------------"); 
		} catch (MessagingException e) {
			e.printStackTrace(); 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 파일을 로컬로 부터 읽어와서 BodyPart 클래스로 만든다. (바운더리 변환)
	private BodyPart getFileAttachment(String filename) throws MessagingException {
		// BodyPart 생성
		BodyPart mbp = new MimeBodyPart();
		// 파일 읽어서 BodyPart에 설정(바운더리 변환)
		File file = new File(filename);
		DataSource source = new FileDataSource(file);
		mbp.setDataHandler(new DataHandler(source));
		mbp.setDisposition(Part.ATTACHMENT);
		mbp.setFileName(file.getName());
	return mbp;
	}
	
	// 메일의 본문 내용 설정
	private BodyPart getContents(String html) throws MessagingException {
		BodyPart mbp = new MimeBodyPart();
		String txt= "<html><head></head><body>";
		txt += html;
		// setText를 이용할 경우 일반 텍스트 내용으로 설정된다.
		// mbp.setText(html);
		// html 형식으로 설정
		txt += "</body></html>";
		mbp.setContent(txt, "text/html; charset=utf-8");
		return mbp;
	}



	
}
