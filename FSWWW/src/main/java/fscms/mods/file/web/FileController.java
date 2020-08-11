package fscms.mods.file.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import fscms.cmm.exception.NotAllowFileExtException;
import fscms.cmm.exception.OverflowFileSizeException;
import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.util.FsFuncCmmHelper;
import fscms.cmm.util.PropConnHelper;
import fscms.cmm.util.ThumbHelper;
import fscms.cmm.util.UploadHelper;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.UploadFileVO;
import fscms.mods.atchmnfl.service.AtchmnflService;
import fscms.mods.atchmnfl.vo.AtchmnflVO;
import fscms.mods.user.vo.UserVO;
import net.sf.json.JSONArray;

@Controller
public class FileController {
	
	static Logger logger = LogManager.getLogger(FileController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "atchmnflService")
	private AtchmnflService atchmnflService;
	
	@Autowired
	private UploadHelper uploadHelper;
	
	@Autowired
	private ThumbHelper thumbHelper;
	
	/**
	 * @Method Name	: cmmFileImgUpload
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 이미지 첨부파일 업로드
	 */
	@RequestMapping(value = "/cmm/file/imgUpload.do", method = RequestMethod.POST)
	public String cmmFileImgUpload(
			MultipartHttpServletRequest request,
			@RequestParam(value = "upKey", required = true) String upKey,
			Boolean refAt,
			ModelMap model) {
		
		String msg         = "";
		String handlerName = request.getParameter("handlerName");
		int limitMegaByte  = (StringUtils.isNotEmpty(request.getParameter("limitMegaByte")))?
				Integer.parseInt(request.getParameter("limitMegaByte")) : 10;
		
		UploadFileVO uploadFile = null;
		try {
			uploadFile = uploadHelper.upload(request, upKey, "uploadFile", limitMegaByte, "");
			
			if(uploadFile != null) {
				msg = "업로드가 완료 되었습니다,";
			} else {
				msg = "업로드 실패 하였습니다.";
			}
		} catch (NotAllowFileExtException e) {
			msg = "쉘 파일접근을 허용하지 않습니다.";
		} catch (OverflowFileSizeException e) {
			msg = "업로드 용량을 초과하였습니다.";
		} catch (IOException e) {
			msg = "파일생성에 오류가 발생 하였습니다.";
		}
		
		model.addAttribute("uploadFile",  uploadFile);
		model.addAttribute("msg",         msg);
		model.addAttribute("handlerName", handlerName);
		model.addAttribute("upKey",       upKey);
		model.addAttribute("target",      (StringUtils.isNotEmpty(request.getParameter("target")))? request.getParameter("target") : "parent");
		return "cmm/file_upload/success";
	}
	
	/**
	 * @Method Name	: cmmFileUploadTmp
	 * @작성일		: 2019. 10. 24.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 멀티파일 업로드 + 썸네일 생성
	 */
	@RequestMapping(value = "/cmm/file/uploadTmp.do", method = RequestMethod.POST)
	public String cmmFileUploadTmp(
			MultipartHttpServletRequest request,
			@RequestParam(value = "uniqueId", required = true) String uniqueId,
			@RequestParam(value = "limitMegaByte", required = false) Integer limitMegaByte,
			@RequestParam(value = "upKey", required = true) String upKey,
			@RequestParam(value = "type", required = true) String type,
			Boolean refAt,
			ModelMap model) {
		
		if(limitMegaByte == null) { limitMegaByte = 10; }
		
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		String msg = "";
		try {
			UploadFileVO uploadFile = uploadHelper.upload(request, upKey, "file", limitMegaByte, "tmp/" + fsFuncCmmHelper.getToDate("yyyyMMdd", null));
			
			if(uploadFile != null) {
				@SuppressWarnings("unchecked")
				List<AtchmnflVO> fileList = (List<AtchmnflVO>) request.getSession().getAttribute("file_" + uniqueId);
				
				List<AtchmnflVO> itemList = new ArrayList<AtchmnflVO>();
				if(fileList != null && fileList.size() > 0) {
					for (AtchmnflVO item : fileList) {
						itemList.add(item);
					}
				}
				
				AtchmnflVO atchmnflVO = new AtchmnflVO();
				atchmnflVO.setAccTy(upKey);
				atchmnflVO.setAtchmnflNm(uploadFile.getFileName().replaceAll("." + uploadFile.getFileExt(), ""));
				atchmnflVO.setAtchmnflRealNm(uploadFile.getServerFileName());
				atchmnflVO.setAtchmnflSize((int) uploadFile.getFileSize());
				atchmnflVO.setAtchmnflTy(uploadFile.getFileType());
				atchmnflVO.setAtchmnflExtsn(uploadFile.getFileExt());
				atchmnflVO.setFlpth(uploadHelper.getRelativePath(upKey, ""));
				atchmnflVO.setAtchmnflRm(thumbHelper.isImageFile(uploadFile.getFileExt())? "img" : "file");
				itemList.add(atchmnflVO);
				
				// 썸네일 생성
				thumbHelper.createImageMng(uploadFile.getAbsolutePath(), atchmnflVO.getAtchmnflRealNm(), atchmnflVO.getAtchmnflExtsn(), 100, 100, null);
				
				request.getSession().setAttribute("file_" + uniqueId, itemList);
				
				msg = "업로드가 완료 되었습니다.";
			} else {
				msg = "업로드 실패 하였습니다.";
			}
		} catch (NotAllowFileExtException e) {
			msg = "쉘 파일접근을 허용하지 않습니다.";
		} catch (OverflowFileSizeException e) {
			msg = "업로드 용량을 초과하였습니다.";
		} catch (IOException e) {
			msg = "파일생성에 오류가 발생 하였습니다.";
		}
		
		model.addAttribute("msg", msg);
		return "cmm/file_upload/success";
	}
	
	/**
	 * @Method Name	: cmmFileGetList
	 * @작성일		: 2019. 10. 24.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 첨부파일 목록 조회
	 */
	@RequestMapping(value = "/cmm/file/getList.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String cmmFileGetList(
			HttpServletRequest request,
			@RequestParam(value = "uniqueId", required = true) String uniqueId,
			@RequestParam(value = "fileEtc", required = false) String fileEtc,
			Boolean refAt) {
		
		@SuppressWarnings("unchecked")
		List<AtchmnflVO> fileList = (List<AtchmnflVO>) request.getSession().getAttribute("file_" + uniqueId);
		
		List<AtchmnflVO> itemList = new ArrayList<AtchmnflVO>();
		if(fileList != null && fileList.size() > 0) {
			for (AtchmnflVO item : fileList) {
				if(StringUtils.isNotEmpty(fileEtc) && !fileEtc.equals(item.getAtchmnflRm())) { continue; }
				
				itemList.add(item);
			}
		}
		
		return JSONArray.fromObject(itemList).toString();
	}
	
	/**
	 * @Method Name	: cmmFileDelFile
	 * @작성일		: 2019. 10. 24.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 파일 정보 삭제
	 */
	@RequestMapping(value = "/cmm/file/delFile.do", method = RequestMethod.POST)
	public @ResponseBody String cmmFileDelFile(
			HttpServletRequest request,
			@RequestParam(value = "fileKey", required = false) Integer fileKey,
			@RequestParam(value = "fileSNm", required = false) String fileSNm,
			@RequestParam(value = "uniqueId", required = true) String uniqueId,
			@RequestParam(value = "upKey", required = false) String upKey,
			UserVO userSession,
			Boolean refAtAjax) {
		
		PropConnHelper propConnHelper = new PropConnHelper();
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		JsonVO jsonVO = new JsonVO();
		
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		try {
			if(fileKey != null && fileKey > 0) {
				// 키 값이 있으면 DB + 파일 썸네일 삭제
				AtchmnflVO atchmnflVO = new AtchmnflVO();
				atchmnflVO.setAtchmnflSn(fileKey);
				AtchmnflVO file = atchmnflService.selectObj(atchmnflVO);
				
				if(file != null) {
					if(StringUtils.equals(file.getAtchmnflRealNm(), fileSNm)) {
						// 파일명이 동일할때만 삭제
						String defaultPath = propConnHelper.getConn("uploadPath", "uploadPath.defaultPath");
						
						// 썸네일 및 본파일 삭제
						Files.walk(Paths.get(defaultPath + "/" + file.getFlpth())).forEach(filePath -> {
							if (Files.isRegularFile(filePath) && filePath.toString().indexOf(file.getAtchmnflRealNm()) != -1) {
								File thumgFile = new File(filePath.toString());
								thumgFile.delete();
							}
						});
						
						String bfData = JSONArray.fromObject(file).toString();
						atchmnflService.deleteInfo(request, bfData, file, userSession.getUserId());
					}
				}
			} else {
				String tmpPath = uploadHelper.getAbsoluteUploadPath(upKey, "tmp/" + fsFuncCmmHelper.getToDate("yyyyMMdd", null));
				
				// 썸네일 및 본파일 삭제
				Files.walk(Paths.get(tmpPath)).forEach(filePath -> {
					if (Files.isRegularFile(filePath) && filePath.toString().indexOf(fileSNm) != -1) {
						File thumgFile = new File(filePath.toString());
						thumgFile.delete();
					}
				});
			}
			
			// 세션 재설정
			@SuppressWarnings("unchecked")
			List<AtchmnflVO> fileList = (List<AtchmnflVO>) request.getSession().getAttribute("file_" + uniqueId);
			
			List<AtchmnflVO> tmpList = new ArrayList<AtchmnflVO>();
			if(fileList != null && fileList.size() > 0) {
				for (AtchmnflVO item : fileList) {
					if(!StringUtils.equals(item.getAtchmnflRealNm(), fileSNm)) {
						tmpList.add(item);
					}
				}
			}
			
			request.getSession().setAttribute("file_" + uniqueId, tmpList);
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0004"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(jsonVO).toString();
	}
	
	/**
	 * @Method Name	: getBrowser
	 * @작성일		: 2019. 12. 1.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 브라우저 구분
	 */
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}
	
	/**
	 * @Method Name	: setDisposition
	 * @작성일		: 2019. 12. 1.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: Disposition 지정하기.
	 */
	private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}
	
	/**
	 * @Method Name	: cmmFileDown
	 * @작성일		: 2019. 12. 1.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 첨부파일 다운로드
	 */
	@RequestMapping(value = "/cmm/fileDown.do")
	public void cmmFileDown(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "key", required = true) int key,
			@RequestParam(value = "type", required = true) String type,
			Boolean refAt) {
		
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			AtchmnflVO atchmnflVO = new AtchmnflVO();
			atchmnflVO.setAtchmnflSn(key);
			atchmnflVO.setAccTy(type);
			AtchmnflVO atchmnfl = atchmnflService.selectObj(atchmnflVO);
			
			if(atchmnfl != null) {
				String defaultPath = new PropConnHelper().getConn("uploadPath", "uploadPath.defaultPath");
				String fileFullPath = defaultPath + atchmnfl.getFlpth() + "/" + atchmnfl.getAtchmnflRealNm();
				
				File uFile = new File(fileFullPath);
				long fSize = uFile.length();
				
				if(fSize > 0) {
					String mimetype = "application/x-msdownload";
					
					response.setContentType(mimetype);
					
					String fileNm = atchmnfl.getAtchmnflNm();
					if(StringUtils.isNotEmpty(atchmnfl.getAtchmnflExtsn()) && fileNm.indexOf(atchmnfl.getAtchmnflExtsn()) == -1) {
						fileNm += "." + atchmnfl.getAtchmnflExtsn();
					}
					setDisposition(fileNm, request, response);
					
					in = new BufferedInputStream(new FileInputStream(uFile));
					out = new BufferedOutputStream(response.getOutputStream());

					FileCopyUtils.copy(in, out);
					out.flush();
				} else {
					response.setContentType("application/x-msdownload");

					PrintWriter printwriter = response.getWriter();
					
					String refUrl = request.getHeader("referer");
					
					printwriter.println("<html>");
					printwriter.println("<script type=\"text/javascript\">");
					printwriter.println("alert(\"첨부파일이 존재하지 않습니다.\");");
					printwriter.println("document.location.href='" + refUrl + "';");
					printwriter.println("</script>");
					printwriter.println("</html>");
					
					printwriter.flush();
					printwriter.close();
				}
			} else {
				response.setContentType("application/x-msdownload");

				PrintWriter printwriter = response.getWriter();
				
				String refUrl = request.getHeader("referer");
				
				printwriter.println("<html>");
				printwriter.println("<script type=\"text/javascript\">");
				printwriter.println("alert(\"등록되지 않은 첨부파일 입니다.\");");
				printwriter.println("document.location.href='" + refUrl + "';");
				printwriter.println("</script>");
				printwriter.println("</html>");
				
				printwriter.flush();
				printwriter.close();
			}
		} catch (Exception e) {
			logger.error("첨부파일 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
	}

}
