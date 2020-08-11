package fscms.mods.file.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fscms.cmm.util.FsFuncCmmHelper;
import fscms.cmm.util.ThumbHelper;
import fscms.cmm.util.UploadHelper;
import fscms.mods.atchmnfl.service.AtchmnflService;
import fscms.mods.atchmnfl.vo.AtchmnflVO;
import net.sf.json.JSONArray;

@Component
public class FileHelper {
	
	@Resource(name = "atchmnflService")
	private AtchmnflService atchmnflService;
	
	@Autowired
	private UploadHelper uploadHelper;
	
	@Autowired
	private ThumbHelper thumbHelper;
	
	public int insertFile(
			HttpServletRequest request,
			String accTy,
			String accSn,
			String uniqueId,
			String atchmnflRm,
			String thumbs,
			String thumbNms,
			String mngrId) throws Exception {
		
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		String tmpPath    = uploadHelper.getAbsoluteUploadPath(accTy, "tmp/" + fsFuncCmmHelper.getToDate("yyyyMMdd", null));
		String realAbPath = uploadHelper.getAbsoluteUploadPath(accTy, null) + "/" + accSn;
		String realPath   = uploadHelper.getRelativePath(accTy, null) + "/" + accSn;
		
		// 업로드디렉토리체크후 없으면 생성
		File dirPath = new File(realAbPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		
		@SuppressWarnings("unchecked")
		List<AtchmnflVO> fileList = (List<AtchmnflVO>) request.getSession().getAttribute("file_" + uniqueId);
		
		String tmpFileKey = "";
		if(fileList != null && fileList.size() > 0) {
			for (AtchmnflVO item : fileList) {
				if(item.getAtchmnflSn() != null) {
					tmpFileKey += ((StringUtils.isNotEmpty(tmpFileKey))? "," : "") + item.getAtchmnflSn();
				}
			}
		}
		
		// 파일 정보 삭제
		AtchmnflVO atchmnflVO = new AtchmnflVO();
		atchmnflVO.setAccTy(accTy);
		atchmnflVO.setAccSn(accSn);
		if(StringUtils.isNotEmpty(tmpFileKey)) {
			String[] splitFileKey = tmpFileKey.split(",");
			Integer[] fileKeyArr = new Integer[splitFileKey.length];
			
			if(splitFileKey != null && splitFileKey.length > 0) {
				int i = 0;
				for (String key : splitFileKey) {
					if(StringUtils.isNotEmpty(key)) {
						fileKeyArr[i] = Integer.parseInt(key);
						i++;
					}
				}
			}
			
			atchmnflVO.setNotKey(fileKeyArr);
		}
		List<AtchmnflVO> atchmnflList = atchmnflService.selectList(atchmnflVO);
		
		if(atchmnflList != null && atchmnflList.size() > 0) {
			for (AtchmnflVO atchmnfl : atchmnflList) {
				
				String bfData = JSONArray.fromObject(atchmnfl).toString();
				atchmnflService.deleteInfo(request, bfData, atchmnfl, mngrId);
			}
		}
		
		int cnt = 0;
		if(fileList != null && fileList.size() > 0) {
			for (AtchmnflVO item : fileList) {
				if(item.getAtchmnflSn() != null) {
					// 키가 있으면 건너띄기
					continue;
				}
				
				if(StringUtils.isNotEmpty(atchmnflRm) && !StringUtils.equals(item.getAtchmnflRm(), atchmnflRm)) { cnt++; }
				
				File file = new File(tmpPath + "/" + item.getAtchmnflRealNm());
				
				// 썸네일 항목이 있으면 썸네일 이동 또는 생성
				if(thumbHelper.isImageFile(item.getAtchmnflExtsn()) && StringUtils.isNotEmpty(thumbs)) {
					String[] thumb = thumbs.split(",");
					String[] thumbNm = (StringUtils.isNotEmpty(thumbNms))? thumbNms.split(",") : null;
					
					if(thumb != null && thumb.length > 0) {
						int i = 0;
						for (String t : thumb) {
							String tNm = t;
							if(StringUtils.isNotEmpty(thumbNms)) {
								tNm = thumbNm[i];
							}
							File thumgFile = new File(tmpPath + "/" + tNm + "_" + item.getAtchmnflRealNm());
							if(thumgFile.isFile()) {
								// 썸네일 파일이 있으면 이동 후 삭제
								String inFile  = tmpPath + "/" + tNm + "_" + item.getAtchmnflRealNm();
								String outFile = realAbPath + "/" + tNm + "_" + item.getAtchmnflRealNm();
								this.fileCopy(inFile, outFile);
								thumgFile.delete();
							} else {
								String outFile = realAbPath + "/" + tNm + "_" + item.getAtchmnflRealNm();
								File outFileObj = new File(outFile);
								
								if(!outFileObj.isFile()) {
									// 썸네일 파일이 없으면 바로 생성
									String[] wh = t.split("x");
									
									if(wh != null && wh.length == 2) {
										int w = Integer.parseInt(wh[0]);
										int h = Integer.parseInt(wh[1]);
										thumbHelper.createImageMng(tmpPath, item.getAtchmnflRealNm(), item.getAtchmnflExtsn(), w, h, tNm);
										
										String inFile  = tmpPath + "/" + tNm + "_" + item.getAtchmnflRealNm();
										
										File inFileObj = new File(inFile);
										if(inFileObj.isFile()) {
											this.fileCopy(inFile, outFile);
											thumgFile.delete();
										}
									}
								}
							}
							
							i++;
						}
					}
				}
				
				if(file.isFile()) {
					// 파일이 있으면 이동
					String inFile  = tmpPath + "/" + item.getAtchmnflRealNm();
					String outFile = realAbPath + "/" + item.getAtchmnflRealNm();
					this.fileCopy(inFile, outFile);
					file.delete();
				}
				
				// 파일 저장
				atchmnflVO = item;
				atchmnflVO.setAccSn(accSn);
				atchmnflVO.setAtchmnflOrdr(1);
				atchmnflVO.setFlpth(realPath);
				atchmnflVO.setReprsntThumbAt("N");
				atchmnflService.insertInfo(request, atchmnflVO, mngrId);
			}
			
			// 파일 순번 재설정
			String fileRepFlag  = request.getParameter("fileRepFlag");			// 대표여부
			String[] fileChkKey = request.getParameterValues("fileChkKey");		// 파일 일련번호
			String[] fileName   = request.getParameterValues("fileName");		// 파일 명
			
			if(fileChkKey != null && fileChkKey.length > 0) {
				for(int i=0; i<fileChkKey.length; i++) {
					if(StringUtils.isEmpty(fileChkKey[i])) { continue; }
					
					String repAt = "N";
					if((StringUtils.isEmpty(fileRepFlag) && i == 0) || (StringUtils.isNotEmpty(fileRepFlag) && fileRepFlag.equals(fileChkKey[i]))) {
						repAt = "Y";
					}
					
					atchmnflVO = new AtchmnflVO();
					atchmnflVO.setAccTy(accTy);
					atchmnflVO.setAccSn(accSn);
					atchmnflVO.setAtchmnflRealNm(fileChkKey[i]);
					AtchmnflVO item = atchmnflService.selectObj(atchmnflVO);
					
					if(item != null) {
						atchmnflVO = new AtchmnflVO();
						atchmnflVO.setAtchmnflSn(item.getAtchmnflSn());
						atchmnflVO.setAtchmnflOrdr((i + 1));
						atchmnflVO.setAtchmnflNm(fileName[i]);
						atchmnflVO.setReprsntThumbAt(repAt);
						
						String bfData = JSONArray.fromObject(item).toString();
						atchmnflService.updateInfo(request, bfData, atchmnflVO, mngrId);
					}
				}
			}
		}
		
		return cnt;
	}
	
	public void fileCopy(String inFile, String outFile) throws IOException {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(inFile);
			fos = new FileOutputStream(outFile);
			
			int data = 0;
			while((data = fis.read()) != -1) {
				fos.write(data);
			}
			
			fis.close();
			fos.close();
		} finally {
			fis.close();
			fos.close();
		}
	}

}
