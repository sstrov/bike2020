package fscms.mods.banner.service;

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
import fscms.mods.banner.vo.BannerSearchVO;
import fscms.mods.banner.vo.BannerVO;
import fscms.mods.data.util.DataChghstHelper;
import net.sf.json.JSONArray;

@Service("bannerService")
public class BannerServiceImpl extends EgovAbstractServiceImpl implements BannerService {
	
	static Logger logger = LogManager.getLogger(BannerServiceImpl.class);
	
	@Resource(name = "bannerMapper")
	private BannerMapper bannerDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;
	
	@Autowired
	private UploadHelper uploadHelper;
	
	@Autowired
	private ThumbHelper thumbHelper;

	@Override
	public BannerVO selectObj(BannerVO vo) throws Exception {
		return bannerDAO.selectObj(vo);
	}

	@Override
	public List<BannerVO> selectList(BannerVO vo) throws Exception {
		return bannerDAO.selectList(vo);
	}

	@Override
	public int selectTCount(BannerSearchVO searchVO) throws Exception {
		return bannerDAO.selectTCount(searchVO);
	}

	@Override
	public List<BannerVO> selectPageList(BannerSearchVO searchVO) throws Exception {
		return bannerDAO.selectPageList(searchVO);
	}

	@Override
	public void insertInfo(MultipartHttpServletRequest request, BannerVO vo, String mngrId) throws Exception {
		// 순서 조정
		BannerVO bannerVO = new BannerVO();
		bannerVO.setBannerEstbsSn(vo.getBannerEstbsSn());
		bannerVO.setBannerOrdr(1);
		this.updateOrder(bannerVO);
		
		// 최근에 등록된 정보가 1번 으로 입력되도록
		vo.setBannerOrdr(1);
		bannerDAO.insertInfo(vo);
		
		// 첨부파일 업로드
		this.setFile(request, vo);
		
		dataChghstHelper.setInsert(request, "FS_BANNER", null, this.getData(vo.getBannerSn()), null, mngrId);
	}

	@Override
	public void updateInfo(MultipartHttpServletRequest request, String bfData, BannerVO vo, String mngrId) throws Exception {
		bannerDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			// 첨부파일 업로드
			this.setFile(request, vo);
			
			dataChghstHelper.setInsert(request, "FS_BANNER", bfData, this.getData(vo.getBannerSn()), null, mngrId);
		}
	}
	
	@Override
	public void updateInfo(HttpServletRequest request, String bfData, BannerVO vo, String mngrId) throws Exception {
		bannerDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BANNER", bfData, this.getData(vo.getBannerSn()), null, mngrId);
		}
	}

	@Override
	public void updateOrder(BannerVO vo) throws Exception {
		bannerDAO.updateOrder(vo);
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, BannerVO vo, String mngrId) throws Exception {
		bannerDAO.deleteInfo(vo);
		
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
			dataChghstHelper.setInsert(request, "FS_BANNER", bfData, null, null, mngrId);
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		BannerVO bannerVO = new BannerVO();
		bannerVO.setBannerSn(key);
		BannerVO item = this.selectObj(bannerVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}
	
	// 첨부파일 업로드
	private void setFile(MultipartHttpServletRequest request, BannerVO vo) throws Exception {
		
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		if(StringUtils.isNotEmpty(request.getParameter("fileDelAt_img")) || StringUtils.isNotEmpty(request.getParameter("fileDelAt_bg"))) {
			BannerVO bannerVO = new BannerVO();
			bannerVO.setBannerSn(vo.getBannerSn());
			BannerVO item = this.selectObj(bannerVO);
			
			if(item != null) {
				String rootPath = fsFuncCmmHelper.getAppAbsolutePath() + item.getFlpth();
				
				if(StringUtils.isNotEmpty(request.getParameter("fileDelAt_img"))) {
					// 이미지 첨부 삭제
					bannerVO = new BannerVO();
					bannerVO.setBannerSn(vo.getBannerSn());
					bannerVO.setBannerBgnde(vo.getBannerBgnde());
					bannerVO.setBannerEndde(vo.getBannerEndde());
					bannerVO.setAtchmnflImage("");
					this.updateInfo(request, null, bannerVO, null);
					
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
				
				if(StringUtils.isNotEmpty(request.getParameter("fileDelAt_bg"))) {
					// 배경이미지 첨부 삭제
					bannerVO = new BannerVO();
					bannerVO.setBannerSn(vo.getBannerSn());
					bannerVO.setBannerBgnde(vo.getBannerBgnde());
					bannerVO.setBannerEndde(vo.getBannerEndde());
					bannerVO.setAtchmnflBcrnImage("");
					this.updateInfo(request, null, bannerVO, null);
					
					// 파일 제거
					File file = new File(rootPath + "/" + item.getAtchmnflBcrnImage());
					file.delete();
					// 썸네일 제거
					file = new File(rootPath + "/200x100_" + item.getAtchmnflBcrnImage());
					file.delete();
				}
			}
		}
		
		if(request.getFile("uploadFile") != null && !request.getFile("uploadFile").isEmpty()) {
			// 이미지 첨부
			UploadFileVO uploadFile = uploadHelper.upload(request, "banner", "uploadFile", 10, "" + vo.getBannerSn());
			
			if(uploadFile != null) {
				BannerVO bannerVO = new BannerVO();
				bannerVO.setBannerSn(vo.getBannerSn());
				bannerVO.setBannerBgnde(vo.getBannerBgnde());
				bannerVO.setBannerEndde(vo.getBannerEndde());
				bannerVO.setAtchmnflImage(uploadFile.getServerFileName());
				bannerVO.setFlpth(uploadFile.getRelativePath());
				this.updateInfo(request, null, bannerVO, null);
				
				// 썸네일 생성
				String realAbPath = uploadHelper.getAbsoluteUploadPath("banner", "" + vo.getBannerSn());
				thumbHelper.createImageMng(realAbPath, uploadFile.getServerFileName(), uploadFile.getFileExt(), 200, 100, null);
			}
		}
		
		if(request.getFile("uploadFile_bg") != null && !request.getFile("uploadFile_bg").isEmpty()) {
			UploadFileVO uploadFile = uploadHelper.upload(request, "banner", "uploadFile_bg", 10, "" + vo.getBannerSn());
			
			if(uploadFile != null) {
				BannerVO bannerVO = new BannerVO();
				bannerVO.setBannerSn(vo.getBannerSn());
				bannerVO.setBannerBgnde(vo.getBannerBgnde());
				bannerVO.setBannerEndde(vo.getBannerEndde());
				bannerVO.setAtchmnflBcrnImage(uploadFile.getServerFileName());
				bannerVO.setFlpth(uploadFile.getRelativePath());
				this.updateInfo(request, null, bannerVO, null);
				
				// 썸네일 생성
				String realAbPath = uploadHelper.getAbsoluteUploadPath("banner", "" + vo.getBannerSn());
				thumbHelper.createImageMng(realAbPath, uploadFile.getServerFileName(), uploadFile.getFileExt(), 200, 100, null);
			}
		}
	}

}
