package fscms.mods.bbs.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.cmm.util.CryptoHelper;
import fscms.cmm.util.UploadHelper;
import fscms.mods.atchmnfl.service.AtchmnflService;
import fscms.mods.atchmnfl.vo.AtchmnflVO;
import fscms.mods.bbs.vo.BbsCmSearchVO;
import fscms.mods.bbs.vo.BbsCmVO;
import fscms.mods.bbs.vo.BbsSearchVO;
import fscms.mods.bbs.vo.BbsThumbVO;
import fscms.mods.bbs.vo.BbsVO;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.file.util.FileHelper;
import net.sf.json.JSONArray;

@Service("bbsService")
public class BbsServiceImpl extends EgovAbstractServiceImpl implements BbsService {
	
	@Resource(name = "bbsMapper")
	private BbsMapper bbsDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public BbsVO selectObj(BbsVO vo) throws Exception {
		return bbsDAO.selectObj(vo);
	}

	@Override
	public BbsVO selectRnum(BbsVO vo) throws Exception {
		return bbsDAO.selectRnum(vo);
	}
	
	@Override
	public List<BbsVO> selectList(BbsSearchVO searchVO) throws Exception {
		return bbsDAO.selectList(searchVO);
	}

	@Override
	public int selectTCount(BbsSearchVO searchVO) throws Exception {
		return bbsDAO.selectTCount(searchVO);
	}
	
	@Override
	public List<BbsVO> selectPageList(BbsSearchVO searchVO) throws Exception {
		return bbsDAO.selectPageList(searchVO);
	}
	
	@Resource(name = "bbsThumbService")
	private BbsThumbService bbsThumbService;
	
	@Autowired
	private FileHelper fileHelper;

	@Override
	public void insertInfo(HttpServletRequest request, BbsVO vo, String userId, String uniqueId) throws Exception {
		
		if(StringUtils.isNotEmpty(vo.getRegistPw())) {
			vo.setRegistPw(CryptoHelper.encrypt(vo.getRegistPw(), vo.getBbsSalt()));
		}
		bbsDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_BBS", null, this.getData(vo.getBbsSn()), userId, null);
		
		BbsThumbVO bbsThumbVO = new BbsThumbVO();
		bbsThumbVO.setBbsEstbsSn(vo.getBbsEstbsSn());
		List<BbsThumbVO> thumbList = bbsThumbService.selectList(bbsThumbVO);
		
		String thumbs   = "100x100";
		String thumbNms = "100x100";
		if(thumbList != null && thumbList.size() > 0) {
			for (BbsThumbVO thumb : thumbList) {
				thumbs   += ((StringUtils.isNotEmpty(thumbs))? "," : "") + thumb.getWidthSize() + "x" + thumb.getVrticlSize();
				thumbNms += ((StringUtils.isNotEmpty(thumbNms))? "," : "") + thumb.getThumbNm();
			}
		}
		
		// 파일 저장 (마지막 파라메터정보는 썸네일 정보를 보내는 것이며, 가로x세로 형식으로 전송하며, 콤마구분으로 다중 전송이 가능)
		fileHelper.insertFile(request, "bbs", "" + vo.getBbsSn(), uniqueId, null, thumbs, thumbNms, userId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, BbsVO vo, String userId, String uniqueId) throws Exception {
		
		if(StringUtils.isNotEmpty(vo.getRegistPw())) {
			vo.setRegistPw(CryptoHelper.encrypt(vo.getRegistPw(), vo.getBbsSalt()));
		}
		
		bbsDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BBS", bfData, this.getData(vo.getBbsSn()), userId, null);
		}
		
		if(StringUtils.isNotEmpty(uniqueId)) {
			BbsThumbVO bbsThumbVO = new BbsThumbVO();
			bbsThumbVO.setBbsEstbsSn(vo.getBbsEstbsSn());
			List<BbsThumbVO> thumbList = bbsThumbService.selectList(bbsThumbVO);
			
			String thumbs   = "100x100";
			String thumbNms = "100x100";
			if(thumbList != null && thumbList.size() > 0) {
				for (BbsThumbVO thumb : thumbList) {
					thumbs   += ((StringUtils.isNotEmpty(thumbs))? "," : "") + thumb.getWidthSize() + "x" + thumb.getVrticlSize();
					thumbNms += ((StringUtils.isNotEmpty(thumbNms))? "," : "") + thumb.getThumbNm();
				}
			}
			
			// 파일 저장 (마지막 파라메터정보는 썸네일 정보를 보내는 것이며, 가로x세로 형식으로 전송하며, 콤마구분으로 다중 전송이 가능)
			fileHelper.insertFile(request, "bbs", "" + vo.getBbsSn(), uniqueId, null, thumbs, thumbNms, userId);
		}
	}

	@Override
	public void updateOrder(HttpServletRequest request, String bfData, BbsVO vo, String userId) throws Exception {
		bbsDAO.updateOrder(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BBS", bfData, this.getData(vo.getBbsSn()), userId, null);
		}
	}
	
	@Resource(name = "bbsCmService")
	private BbsCmService bbsCmService;
	
	@Resource(name = "atchmnflService")
	private AtchmnflService atchmnflService;
	
	@Autowired
	private UploadHelper uploadHelper;

	@Override
	public void deleteInfo(HttpServletRequest request, BbsVO vo, String userId) throws Exception {
		
		// 하위 목록이 있으면 삭제
		BbsSearchVO bbsSearchVO = new BbsSearchVO();
		bbsSearchVO.setBbsUpperSn(vo.getBbsSn());
		List<BbsVO> childList = this.selectList(bbsSearchVO);
		
		if(childList != null && childList.size() > 0) {
			// 하위 정보 삭제
			this.deleteChildren(request, childList, userId);
		}
		
		this.deleteProc(request, vo, userId);
	}
	
	/**
	 * @Method Name	: deleteChildren
	 * @작성일		: 2019. 12. 1.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 하위 목록 조회 후 삭제
	 */
	private void deleteChildren(HttpServletRequest request, List<BbsVO> itemList, String userId) throws Exception {
		
		if(itemList != null && itemList.size() > 0) {
			for (BbsVO item : itemList) {
				BbsSearchVO bbsSearchVO = new BbsSearchVO();
				bbsSearchVO.setBbsUpperSn(item.getBbsSn());
				List<BbsVO> childList = this.selectList(bbsSearchVO);
				
				if(childList != null && childList.size() > 0) {
					// 하위 정보 삭제
					this.deleteChildren(request, childList, userId);
				}
				
				this.deleteProc(request, item, userId);
			}
		}
	}
	
	/**
	 * @Method Name	: deleteProc
	 * @작성일		: 2019. 12. 1.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 정보 삭제
	 */
	private void deleteProc(HttpServletRequest request, BbsVO bbs, String userId) throws Exception {
		
		// 댓글 정보 삭제
		BbsCmSearchVO bbsCmSearchVO = new BbsCmSearchVO();
		bbsCmSearchVO.setBbsSn(bbs.getBbsSn());
		List<BbsCmVO> cmList = bbsCmService.selectList(bbsCmSearchVO);
		
		if(cmList != null && cmList.size() > 0) {
			for (BbsCmVO cm : cmList) {
				String bfData_cm = JSONArray.fromObject(cm).toString();
				bbsCmService.deleteInfo(request, bfData_cm, cm, userId);
			}
		}
		
		// 첨부파일 정보 삭제
		AtchmnflVO atchmnflVO = new AtchmnflVO();
		atchmnflVO.setAccTy("bbs");
		atchmnflVO.setAccSn("" + bbs.getBbsSn());
		List<AtchmnflVO> fileList = atchmnflService.selectList(atchmnflVO);
		
		if(fileList != null && fileList.size() > 0) {
			for (AtchmnflVO file : fileList) {
				String bfData_file = JSONArray.fromObject(file).toString();
				atchmnflService.deleteInfo(request, bfData_file, file, userId);
			}
		}
		
		bbsDAO.deleteInfo(bbs);
		
		String bfData = JSONArray.fromObject(bbs).toString();
		dataChghstHelper.setInsert(request, "FS_BBS", bfData, null, userId, null);
		
		// 폴더 전체 삭제
		String realAbPath = uploadHelper.getAbsoluteUploadPath("bbs", null) + "/" + bbs.getBbsSn();
		File deleteFolder = new File(realAbPath);
		File[] deleteFolderList = deleteFolder.listFiles();
		
		if(deleteFolderList != null && deleteFolderList.length > 0) {
			for(int j = 0; j < deleteFolderList.length; j++) {
				deleteFolderList[j].delete();
			}
		} 
		
		if(deleteFolder.exists()) {
			deleteFolder.delete();
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		BbsVO bbsVO = new BbsVO();
		bbsVO.setBbsEstbsSn(key);
		BbsVO item = this.selectObj(bbsVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
