package fscms.cmm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import fscms.cmm.exception.NotAllowFileExtException;
import fscms.cmm.exception.OverflowFileSizeException;
import fscms.cmm.vo.UploadFileVO;

public class UploadHelper {
	
	static Logger logger = LogManager.getLogger(UploadHelper.class);
	
	private static long current = System.currentTimeMillis();
	private List<String>   deniedFileExtensions;
	
	public void setDeniedFileExtensions(List<String> deniedFileExtensions) {
		this.deniedFileExtensions = deniedFileExtensions;
	}
	
	static public synchronized long getUniqueID() {
		return current++;
	}
	
	/**
	 * @method	: upload
	 * @date	: 2014. 8. 19.
	 * @author	: Edmund.J
	 * @throws IOException 
	 * @comment : 단일 파일 업로드
	 */
	public UploadFileVO upload(MultipartHttpServletRequest request,
			String uploadPropertyKey, String inputFileFormName,
			int limitMegaByte, String uploadSubDir) throws IOException {
		
		UploadFileVO uploadFile = null;
		Map<String, MultipartFile> fileMap = request.getFileMap();
		Iterator<String> iterator = request.getFileNames();
		
		while(iterator.hasNext()) {
			String fieldName = iterator.next();
			
			if(StringUtils.equals(fieldName, inputFileFormName)) {
				MultipartFile multipartFile = fileMap.get(fieldName);

				if(!multipartFile.isEmpty()) {
					String fileExt = StringUtils.substringAfterLast(
							multipartFile.getOriginalFilename(), ".");
					
					if(isNotWebShellFile(fileExt) == true) {
						if(isAllowSize(multipartFile, limitMegaByte)) {
							uploadFile = this._upload(request.getSession(),
									multipartFile, uploadPropertyKey, uploadSubDir);
						} else {
							logger.info("파일첨부오류 : 용량 초과");
							throw new OverflowFileSizeException();
						}
					} else {
						logger.info("파일첨부오류 : 등록이 불가능한 확장자");
						throw new NotAllowFileExtException();
					}
				}
				break;
			}
		}
		
		return uploadFile;
	}
	
	/**
	 * 업로드파일을 서버에 저장
	 *
	 * @param multipartFile
	 * @param saveFileName
	 * @param uploadPropertyKey
	 * @param uploadSubDir
	 * @return
	 * @throws IOException 
	 */
	private UploadFileVO _upload(HttpSession httpSession,
			MultipartFile multipartFile,
			String uploadPropertyKey, String uploadSubDir) throws IOException {

		String absolutePath = getAbsoluteUploadPath(uploadPropertyKey, uploadSubDir);  // 업로드절대경로
		String relativePath = getRelativePath(uploadPropertyKey, uploadSubDir); // 상대경로
		
		byte[] buffer = new byte[8192];
		int bytesRead = 0;
		InputStream is = null;
		OutputStream bos = null;
		UploadFileVO uploadFile = null;
		String fileExt = null;
		String serverFileName = null;

		is = multipartFile.getInputStream();

		// 업로드디렉토리체크후 없으면 생성
		File dirPath = new File(absolutePath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

		fileExt = StringUtils.substringAfterLast(
				multipartFile.getOriginalFilename(), "."); // 파일확장자
		
		serverFileName = getUniqueName(absolutePath, fileExt); // 고유파일명생성

		File newFile = new File(absolutePath + "/" + serverFileName);

		try {
			bos = new FileOutputStream(newFile);
	
			while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
	
			bos.close();
			is.close();
		} finally {
			bos.close();
			is.close();
		}

		uploadFile = new UploadFileVO(multipartFile,
				serverFileName.toString(), absolutePath, relativePath);

		return uploadFile;
	}
	
	/**
	 * @method	: getUniqueName
	 * @date	: 2014. 8. 6.
	 * @author	: Edmund.J
	 * @comment : 고유한 파일명 생성
	 */
	public String getUniqueName(String dir, String extension) {
		String uniqueFileName = Long.toString(getUniqueID()) + "." + extension;

		File file = new File(dir + uniqueFileName);
		if (file.exists()) {
			uniqueFileName = getUniqueName(dir, extension);
		}

		return uniqueFileName;
	}
	
	/**
	 * @method	: isNotWebShellFile
	 * @date	: 2014. 8. 19.
	 * @author	: Edmund.J
	 * @comment : 확장자가 웹쉘파일인지 검사한다.
	 */
	public boolean isNotWebShellFile(String fileExt) {
		boolean flag = true;
		int size = deniedFileExtensions.size();
		for (int i = 0; i < size; i++) {
			String deniedExt = deniedFileExtensions.get(i);
			if (deniedExt.equalsIgnoreCase(fileExt)) {
				flag = false;
				break;
			}
		}

		return flag;
	}
	
	/**
	 * 파일크기가 허용용량을 초과하는지 체크
	 *
	 * @param multipartFile
	 * @param limitSize
	 * @return
	 * @throws NotAllowFileExtException
	 */
	public boolean isAllowSize(MultipartFile multipartFile, int limitMegaByte) {
		limitMegaByte = limitMegaByte * 1024 * 1024;
		boolean isAllowSize = true;
		if (!multipartFile.isEmpty())
			if (multipartFile.getSize() > limitMegaByte)
				isAllowSize = false;

		return isAllowSize;
	}
	
	public String getAbsoluteUploadPath(String uploadPropertyKey, String subDirectoryName) throws IOException {

		String propertyValue = new PropConnHelper().getConn("uploadPath", "uploadPath." + uploadPropertyKey);
		String defaultPath   = new PropConnHelper().getConn("uploadPath", "uploadPath.defaultPath");
		String uploadPath    = new PropConnHelper().getConn("uploadPath", "uploadPath.uploadPath");

		if (StringUtils.isEmpty(propertyValue)) {
			logger.error("Property 설정 오류 : 등록되지 않은 정보");
			//throw new PropertyKeyNotFoundException();
		}

		String absoluteUploadPath = defaultPath + uploadPath + "/www" + propertyValue;

		if (StringUtils.isNotEmpty(subDirectoryName)) {
			absoluteUploadPath += "/" + subDirectoryName;
		}

		absoluteUploadPath = StringUtils.replace(absoluteUploadPath, "\\", "/");

		return absoluteUploadPath;
	}

	public String getRelativePath(String uploadPropertyKey, String subDriectoryName) throws IOException {

		String propertyValue = new PropConnHelper().getConn("uploadPath", "uploadPath." + uploadPropertyKey); // property에설정한상대경로
		String uploadPath    = new PropConnHelper().getConn("uploadPath", "uploadPath.uploadPath");

		if (StringUtils.isEmpty(propertyValue)) {
			logger.error("Property 설정 오류 : 등록되지 않은 정보");
			//throw new PropertyKeyNotFoundException();
		}

		String relativePath = uploadPath + "/www" + propertyValue;

		if (StringUtils.isNotEmpty(subDriectoryName)) {
			relativePath += "/" + subDriectoryName;
		}

		return relativePath;
	}

	public static long getCurrent() {
		return current;
	}
	public static void setCurrent(long current) {
		UploadHelper.current = current;
	}

}
