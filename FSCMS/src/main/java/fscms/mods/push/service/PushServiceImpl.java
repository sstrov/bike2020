package fscms.mods.push.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.cmm.util.FsFuncCmmHelper;
import fscms.cmm.util.UploadHelper;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.file.util.FileHelper;
import fscms.mods.push.vo.PushSearchVO;
import fscms.mods.push.vo.PushVO;
import net.sf.json.JSONArray;

@Service("pushService")
public class PushServiceImpl extends EgovAbstractServiceImpl implements PushService {

	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;
	
	@Resource(name = "pushMapper")
	private PushMapper pushDAO;
	
	@Autowired
	private UploadHelper uploadHelper;
	
	@Autowired
	private FileHelper fileHelper;
	
	@Override
	public List<PushVO> selectPageList(PushSearchVO searchVO) throws Exception {
		return pushDAO.selectPageList(searchVO);
	}
	
	@Override
	public int selectTCount(PushSearchVO searchVO) throws Exception {
		return pushDAO.selectTCount(searchVO);
	}

	@Override
	public PushVO selectObj(PushVO pushVO) throws Exception {
		return pushDAO.selectObj(pushVO);
	}

	@Override
	public void insertInfo(HttpServletRequest request, PushVO vo, String uniqueId, String mngrId) throws Exception {
		
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		pushDAO.insertInfo(vo);
		
		// 첨부파일 임시경로 제거
		String tmpPath  = uploadHelper.getRelativePath("push", "tmp/" + fsFuncCmmHelper.getToDate("yyyyMMdd", null));	// 업로드 임시 경로
		String realPath = uploadHelper.getRelativePath("push", null) + "/" + vo.getPushSn();									// 업로드 실제 경로
		
		PushVO pushVO = new PushVO();
		pushVO.setPushSn(vo.getPushSn());
		pushVO.setPushCn(vo.getPushCn().replaceAll(tmpPath, realPath));
		//pushVO.setPopupBgnde(vo.getPopupBgnde());
		//pushVO.setPopupEndde(vo.getPopupEndde());
		this.updateInfo(request, null, pushVO, uniqueId, mngrId);
		
		dataChghstHelper.setInsert(request, "FS_PUSH", null, this.getData(vo.getPushSn()), null, mngrId);
		
		// 파일 저장 (마지막 파라메터정보는 썸네일 정보를 보내는 것이며, 가로x세로 형식으로 전송하며, 콤마구분으로 다중 전송이 가능)
		fileHelper.insertFile(request, "push", "" + vo.getPushSn(), uniqueId, null, "100x100", null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, PushVO vo, String uniqueId, String mngrId)
			throws Exception {
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		if(StringUtils.isNotEmpty(vo.getPushCn())) {
			// 첨부파일 임시경로 제거
			String tmpPath  = uploadHelper.getRelativePath("push", "tmp/" + fsFuncCmmHelper.getToDate("yyyyMMdd", null));	// 업로드 임시 경로
			String realPath = uploadHelper.getRelativePath("push", null) + "/" + vo.getPushSn();							// 업로드 실제 경로
			vo.setPushCn(vo.getPushCn().replaceAll(tmpPath, realPath));
		}
		
		pushDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_PUSH", bfData, this.getData(vo.getPushSn()), null, mngrId);
			
			// 파일 저장 (마지막 파라메터정보는 썸네일 정보를 보내는 것이며, 가로x세로 형식으로 전송하며, 콤마구분으로 다중 전송이 가능)
			fileHelper.insertFile(request, "push", "" + vo.getPushSn(), uniqueId, null, "100x100", null, mngrId);
		}
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, PushVO vo, String mngrId) throws Exception {
		pushDAO.deleteInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_PUSH", bfData, null, null, mngrId);
		}
	}

	private String getData(int key) throws Exception {
		String rtnData = null;
		
		PushVO pushVO = new PushVO();
		pushVO.setPushSn(key);
		PushVO item = this.selectObj(pushVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

	@Override
	public int selectMaxPushSn(PushVO pushVO) throws Exception {
		return pushDAO.selectMaxPushSn(pushVO);
	}
}
