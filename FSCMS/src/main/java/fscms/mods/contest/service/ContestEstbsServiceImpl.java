package fscms.mods.contest.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.cmm.util.FsFuncCmmHelper;
import fscms.cmm.util.ThumbHelper;
import fscms.cmm.util.UploadHelper;
import fscms.cmm.vo.UploadFileVO;
import fscms.mods.banner.service.BannerServiceImpl;
import fscms.mods.contest.vo.ContestEstbsSearchVO;
import fscms.mods.contest.vo.ContestEstbsVO;
import fscms.mods.contest.vo.ContestSearchVO;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.mngr.vo.MngrLoginHistVO;
import net.sf.json.JSONArray;

@Service("contestEstbsService")
public class ContestEstbsServiceImpl extends EgovAbstractServiceImpl implements ContestEstbsService{

	static Logger logger = LogManager.getLogger(BannerServiceImpl.class);
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Resource(name = "contestEstbsMapper")
	private ContestEstbsMapper contestEstbsDAO;
	
	@Autowired
	private UploadHelper uploadHelper;
	
	@Autowired
	private ThumbHelper thumbHelper;
	
	@Override
	public int selectTCount(ContestEstbsSearchVO searchVO) throws Exception {
		return contestEstbsDAO.selectTCount(searchVO);
	}

	@Override
	public List<ContestEstbsVO> selectPageList(ContestEstbsSearchVO searchVO) throws Exception {
		return contestEstbsDAO.selectPageList(searchVO);
	}

	@Override
	public ContestEstbsVO selectObj(ContestEstbsVO contestEstbsVO) throws Exception {
		return contestEstbsDAO.selectObj(contestEstbsVO);
	}

	@Override
	public void insertInfo(MultipartHttpServletRequest request, ContestEstbsVO vo, String mngrId) throws Exception {
		contestEstbsDAO.insertInfo(vo);
		
		// 첨부파일 업로드
		this.setFile(request, vo);
		
		dataChghstHelper.setInsert(request, "FS_CONTEST_ESTBS", null, this.getData(vo.getContestEstbsSn()), null, mngrId);
		
	}
	
	@Override
	public void updateInfo(MultipartHttpServletRequest request, String bfData, ContestEstbsVO vo, String mngrId) throws Exception {
		contestEstbsDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			// 첨부파일 업로드
			this.setFile(request, vo);
			dataChghstHelper.setInsert(request, "FS_CONTEST_ESTBS", bfData, this.getData(vo.getContestEstbsSn()), null, mngrId);
		}
	}
	
	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, ContestEstbsVO vo, String mngrId)
			throws Exception {
		contestEstbsDAO.deleteInfo(vo);
		
		// 배너 폴더 전체 삭제
		File deleteFolder = new File(new FsFuncCmmHelper().getAppAbsolutePath() + vo.getFlpth());
		File[] deleteFolderList = deleteFolder.listFiles();
		
		if(deleteFolderList != null && deleteFolderList.length > 0) {
			for(int j = 0; j < deleteFolderList.length; j++) {
				deleteFolderList[j].delete();
			}
		} 
		
		if(deleteFolder.exists()) {
			new Thread() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					logger.info("####### 폴더 전체를 삭제합니다.");
					deleteFolder.delete();
				}					
			}.start();
		}
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_CONTEST_ESTBS", bfData, null, null, mngrId);
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		ContestEstbsVO contestEstbsVO = new ContestEstbsVO();
		contestEstbsVO.setContestEstbsSn(key);
		ContestEstbsVO item = this.selectObj(contestEstbsVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}
	
	// 첨부파일 업로드
	private void setFile(MultipartHttpServletRequest request, ContestEstbsVO vo) throws Exception {
		
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		if(StringUtils.isNotEmpty(request.getParameter("fileDelAt_img"))/* || StringUtils.isNotEmpty(request.getParameter("fileDelAt_bg"))*/) {
			ContestEstbsVO contestEstbsVO = new ContestEstbsVO();
			contestEstbsVO.setContestEstbsSn(vo.getContestEstbsSn());
			ContestEstbsVO item = this.selectObj(contestEstbsVO);
			
			if(item != null) {
				String rootPath = fsFuncCmmHelper.getAppAbsolutePath() + item.getFlpth();
				
				if(StringUtils.isNotEmpty(request.getParameter("fileDelAt_img"))) {
					// 이미지 첨부 삭제
					contestEstbsVO = new ContestEstbsVO();
					contestEstbsVO.setContestEstbsSn(vo.getContestEstbsSn());
					contestEstbsVO.setContestBgnde(vo.getContestBgnde());
					contestEstbsVO.setContestEndde(vo.getContestEndde());
					contestEstbsVO.setAtchmnflImage("");
					this.updateInfo(request, null, contestEstbsVO, null);
					
					// 파일 제거
					File file = new File(rootPath + "/", FilenameUtils.getName(item.getAtchmnflImage()));
					file.delete();
					// 썸네일 제거
					
					String fileNm = "200x100_" + item.getAtchmnflImage();
					file = new File(rootPath + "/", FilenameUtils.getName(fileNm));
					if(file.exists()) {
						file.delete();
					}
				}
				
				/*if(StringUtils.isNotEmpty(request.getParameter("fileDelAt_bg"))) {
					// 배경이미지 첨부 삭제
					contestEstbsVO = new ContestEstbsVO();
					contestEstbsVO.setContestEstbsSn(vo.getContestEstbsSn());
					contestEstbsVO.setContestBgnde(vo.getContestBgnde());
					contestEstbsVO.setContestEndde(vo.getContestEndde());
					contestEstbsVO.setAtchmnflImage("");
					this.updateInfo(request, null, contestEstbsVO, null);
					
					// 파일 제거
					File file = new File(rootPath + "/" + item.getAtchmnflImage());
					file.delete();
					// 썸네일 제거
					file = new File(rootPath + "/200x100_" + item.getAtchmnflImage());
					file.delete();
				}*/
			}
		}
		
		if(request.getFile("uploadFile") != null && !request.getFile("uploadFile").isEmpty()) {
			// 이미지 첨부
			UploadFileVO uploadFile = uploadHelper.upload(request, "contestEstbs", "uploadFile", 10, "" + vo.getContestEstbsSn());
			
			if(uploadFile != null) {
				ContestEstbsVO contestEstbsVO = new ContestEstbsVO();
				contestEstbsVO.setContestEstbsSn(vo.getContestEstbsSn());
				contestEstbsVO.setContestBgnde(vo.getContestBgnde());
				contestEstbsVO.setContestEndde(vo.getContestEndde());
				contestEstbsVO.setAtchmnflImage(uploadFile.getServerFileName());
				contestEstbsVO.setFlpth(uploadFile.getRelativePath());
				this.updateInfo(request, null, contestEstbsVO, null);
				
				// 썸네일 생성
				String realAbPath = uploadHelper.getAbsoluteUploadPath("contestEstbs", "" + vo.getContestEstbsSn());
				thumbHelper.createImageMng(realAbPath, uploadFile.getServerFileName(), uploadFile.getFileExt(), 200, 100, null);
			}
		}
		
		/*if(request.getFile("uploadFile_bg") != null && !request.getFile("uploadFile_bg").isEmpty()) {
			UploadFileVO uploadFile = uploadHelper.upload(request, "contestEstbs", "uploadFile_bg", 10, "" + vo.getContestEstbsSn());
			
			if(uploadFile != null) {
				ContestEstbsVO contestEstbsVO = new ContestEstbsVO();
				contestEstbsVO.setContestEstbsSn(vo.getContestEstbsSn());
				contestEstbsVO.setContestBgnde(vo.getContestBgnde());
				contestEstbsVO.setContestEndde(vo.getContestEndde());
				contestEstbsVO.setAtchmnflImage(uploadFile.getServerFileName());
				contestEstbsVO.setFlpath(uploadFile.getRelativePath());
				this.updateInfo(request, null, contestEstbsVO, null);
				
				// 썸네일 생성
				String realAbPath = uploadHelper.getAbsoluteUploadPath("contestEstbs", "" + vo.getContestEstbsSn());
				thumbHelper.createImageMng(realAbPath, uploadFile.getServerFileName(), uploadFile.getFileExt(), 200, 100, null);
			}
		}*/
	}
		
}
