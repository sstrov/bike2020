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
import fscms.mods.contest.vo.ContestSearchVO;
import fscms.mods.contest.vo.ContestVO;
import fscms.mods.data.util.DataChghstHelper;
import net.sf.json.JSONArray;

@Service("contestService")
public class ContestServiceImpl extends EgovAbstractServiceImpl implements ContestService{

static Logger logger = LogManager.getLogger(BannerServiceImpl.class);
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Autowired
	private UploadHelper uploadHelper;
	
	@Autowired
	private ThumbHelper thumbHelper;

	@Resource(name = "contestMapper")
	private ContestMapper contestDAO;
	
	@Override
	public int selectTCount(ContestSearchVO searchVO) throws Exception {
		return contestDAO.selectTCount(searchVO);
	}

	@Override
	public List<ContestVO> selectPageList(ContestSearchVO searchVO) throws Exception {
		return contestDAO.selectPageList(searchVO);
	}

	@Override
	public ContestVO selectObj(ContestVO contestVO) throws Exception {
		return contestDAO.selectObj(contestVO);
	}

	@Override
	public void insertInfo(MultipartHttpServletRequest request, ContestVO vo, String mngrId) throws Exception {
		contestDAO.insertInfo(vo);
		
		// 첨부파일 업로드
		this.setFile(request, vo);
		
		dataChghstHelper.setInsert(request, "FS_CONTEST", null, this.getData(vo.getContestSn()), null, mngrId);
	}

	@Override
	public void updateInfo(MultipartHttpServletRequest request, String bfData, ContestVO vo, String mngrId)
			throws Exception {
		contestDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			// 첨부파일 업로드
			this.setFile(request, vo);
			dataChghstHelper.setInsert(request, "FS_CONTEST", bfData, this.getData(vo.getContestSn()), null, mngrId);
		}
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, ContestVO vo, String mngrId) throws Exception {
		contestDAO.deleteInfo(vo);
		
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
			dataChghstHelper.setInsert(request, "FS_CONTEST", bfData, null, null, mngrId);
		}
	}
	
	private void setFile(MultipartHttpServletRequest request, ContestVO vo) throws Exception {
		
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		if(StringUtils.isNotEmpty(request.getParameter("fileDelAt_img"))/* || StringUtils.isNotEmpty(request.getParameter("fileDelAt_bg"))*/) {
			ContestVO contestVO = new ContestVO();
			contestVO.setContestSn(vo.getContestSn());
			ContestVO item = this.selectObj(contestVO);
			
			if(item != null) {
				String rootPath = fsFuncCmmHelper.getAppAbsolutePath() + item.getFlpth();
				
				if(StringUtils.isNotEmpty(request.getParameter("fileDelAt_img"))) {
					// 이미지 첨부 삭제
					contestVO = new ContestVO();
					contestVO.setContestSn(vo.getContestSn());
					contestVO.setAtchmnflImage("");
					this.updateInfo(request, null, contestVO, null);
					
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
				
			}
		}
		
		if(request.getFile("uploadFile") != null && !request.getFile("uploadFile").isEmpty()) {
			// 이미지 첨부
			UploadFileVO uploadFile = uploadHelper.upload(request, "contest", "uploadFile", 10, "" + vo.getContestSn());
			
			if(uploadFile != null) {
				ContestVO contestVO = new ContestVO();
				contestVO.setContestSn(vo.getContestSn());
				contestVO.setAtchmnflImage(uploadFile.getServerFileName());
				contestVO.setFlpth(uploadFile.getRelativePath());
				contestVO.setOriFile(uploadFile.getFileName());
				contestVO.setUseAt("Y");
				this.updateInfo(request, null, contestVO, null);
				
				// 썸네일 생성
				String realAbPath = uploadHelper.getAbsoluteUploadPath("contest", "" + vo.getContestSn());
				thumbHelper.createImageMng(realAbPath, uploadFile.getServerFileName(), uploadFile.getFileExt(), 200, 100, null);
			}
		}
		
	}

	private String getData(int key) throws Exception {
		String rtnData = null;
		
		ContestVO contestVO = new ContestVO();
		contestVO.setContestSn(key);
		ContestVO item = this.selectObj(contestVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

	@Override
	public List<ContestVO> selectExcelList(ContestSearchVO searchVO) throws Exception {
		return contestDAO.selectExcelList(searchVO);
	}

	
}
