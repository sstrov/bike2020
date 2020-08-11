package fscms.mods.popup.service;

import java.io.BufferedWriter;
import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.cmm.util.FsFuncCmmHelper;
import fscms.cmm.util.UploadHelper;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.file.util.FileHelper;
import fscms.mods.popup.vo.PopupSearchVO;
import fscms.mods.popup.vo.PopupVO;
import net.sf.json.JSONArray;

@Service("popupService")
public class PopupServiceImpl extends EgovAbstractServiceImpl implements PopupService {
	
	@Resource(name = "popupMapper")
	private PopupMapper popupDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;
	
	@Autowired
	private UploadHelper uploadHelper;
	
	@Autowired
	private FileHelper fileHelper;

	@Override
	public PopupVO selectObj(PopupVO vo) throws Exception {
		return popupDAO.selectObj(vo);
	}

	@Override
	public List<PopupVO> selectList(PopupVO vo) throws Exception {
		return popupDAO.selectList(vo);
	}

	@Override
	public int selectTCount(PopupSearchVO searchVO) throws Exception {
		return popupDAO.selectTCount(searchVO);
	}

	@Override
	public List<PopupVO> selectPageList(PopupSearchVO searchVO) throws Exception {
		return popupDAO.selectPageList(searchVO);
	}

	@Override
	public void insertInfo(HttpServletRequest request, PopupVO vo, String uniqueId, String mngrId) throws Exception {
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		popupDAO.insertInfo(vo);
		
		// 첨부파일 임시경로 제거
		String tmpPath  = uploadHelper.getRelativePath("popup", "tmp/" + fsFuncCmmHelper.getToDate("yyyyMMdd", null));	// 업로드 임시 경로
		String realPath = uploadHelper.getRelativePath("popup", null) + "/" + vo.getPopupSn();									// 업로드 실제 경로
		
		PopupVO popupVO = new PopupVO();
		popupVO.setPopupSn(vo.getPopupSn());
		popupVO.setPopupCn(vo.getPopupCn().replaceAll(tmpPath, realPath));
		popupVO.setPopupBgnde(vo.getPopupBgnde());
		popupVO.setPopupEndde(vo.getPopupEndde());
		this.updateInfo(request, null, popupVO, uniqueId, mngrId);
		
		dataChghstHelper.setInsert(request, "FS_POPUP", null, this.getData(vo.getPopupSn()), null, mngrId);
		
		// 파일 저장 (마지막 파라메터정보는 썸네일 정보를 보내는 것이며, 가로x세로 형식으로 전송하며, 콤마구분으로 다중 전송이 가능)
		fileHelper.insertFile(request, "popup", "" + vo.getPopupSn(), uniqueId, null, "100x100", null, mngrId);
		
		// 팝업 파일 생성
		String rootPath = fsFuncCmmHelper.getAppAbsolutePath() + "/repository/www/popup";
		
		File deleteFolder = new File(rootPath, FilenameUtils.getName(""));
		if(!deleteFolder.exists()) {
			// root 경로가 없으면 생성
			deleteFolder.mkdirs();
		}
		
		String popupCnt = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n" +
				"<!DOCTYPE html>\n" +
				"<html lang=\"ko\"><head><meta charset=\"utf-8\" />\n" +
				"<title>" + vo.getPopupNm() + "</title>\n" +
				"<meta name=\"author\" content=\"Edmund.J\"></head><body>\n" +
				"<div id=\"cnt_popup\">" + fsFuncCmmHelper.decodeHtmlSpecialChars(vo.getPopupCn()) + "</div>\n\n" +
				"<script>\n" +
				"var key    = " + vo.getPopupSn() + ";\n" +
				"var autoAt = '" + vo.getAtmcSizeAt() + "';\n" +
				"var posNum = '${ param.pos }';\n" +
				"</script>\n" +
				"<%@ include file=\"/WEB-INF/jsp/cmm/popup/view.jsp\" %>" +
				"</body></html>";
		
		File file = new File(rootPath + "/", FilenameUtils.getName(vo.getPopupSn() + ".jsp"));
		BufferedWriter bw = null;
		try {
			file.createNewFile();
		
			bw = new BufferedWriter(fsFuncCmmHelper.setFileWriter(file.getPath()));
			bw.write(popupCnt);
			bw.close();
		} finally {
			bw.close();
		}
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, PopupVO vo, String uniqueId, String mngrId) throws Exception {
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		if(StringUtils.isNotEmpty(vo.getPopupCn())) {
			// 첨부파일 임시경로 제거
			String tmpPath  = uploadHelper.getRelativePath("popup", "tmp/" + fsFuncCmmHelper.getToDate("yyyyMMdd", null));	// 업로드 임시 경로
			String realPath = uploadHelper.getRelativePath("popup", null) + "/" + vo.getPopupSn();									// 업로드 실제 경로
			vo.setPopupCn(vo.getPopupCn().replaceAll(tmpPath, realPath));
		}
		popupDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_POPUP", bfData, this.getData(vo.getPopupSn()), null, mngrId);
			
			// 파일 저장 (마지막 파라메터정보는 썸네일 정보를 보내는 것이며, 가로x세로 형식으로 전송하며, 콤마구분으로 다중 전송이 가능)
			fileHelper.insertFile(request, "popup", "" + vo.getPopupSn(), uniqueId, null, "100x100", null, mngrId);
			
			// 팝업 파일 생성
			String rootPath = fsFuncCmmHelper.getAppAbsolutePath() + "/repository/www/popup";
			
			File deleteFolder = new File(rootPath, FilenameUtils.getName(""));
			if(!deleteFolder.exists()) {
				// root 경로가 없으면 생성
				deleteFolder.mkdirs();
			}
			
			String popupCnt = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n" +
					"<!DOCTYPE html>\n" +
					"<html lang=\"ko\"><head><meta charset=\"utf-8\" />\n" +
					"<title>" + vo.getPopupNm() + "</title>\n" +
					"<meta name=\"author\" content=\"Edmund.J\"></head><body>\n" +
					"<div id=\"cnt_popup\">" + fsFuncCmmHelper.decodeHtmlSpecialChars(vo.getPopupCn()) + "</div>\n\n" +
					"<script>\n" +
					"var key    = " + vo.getPopupSn() + ";\n" +
					"var autoAt = '" + vo.getAtmcSizeAt() + "';\n" +
					"var posNum = '${ param.pos }';\n" +
					"</script>\n" +
					"<%@ include file=\"/WEB-INF/jsp/cmm/popup/view.jsp\" %>" +
					"</body></html>";
			
			File file = new File(rootPath + "/", FilenameUtils.getName(vo.getPopupSn() + ".jsp"));
			BufferedWriter bw = null;
			try {
				if(!file.exists()) {
					file.createNewFile();
				}
			
				bw = new BufferedWriter(fsFuncCmmHelper.setFileWriter(file.getPath()));
				bw.write(popupCnt);
				bw.close();
			} finally {
				bw.close();
			}
		}
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, PopupVO vo, String mngrId) throws Exception {
		popupDAO.deleteInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_POPUP", bfData, null, null, mngrId);
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		PopupVO popupVO = new PopupVO();
		popupVO.setPopupSn(key);
		PopupVO item = this.selectObj(popupVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
