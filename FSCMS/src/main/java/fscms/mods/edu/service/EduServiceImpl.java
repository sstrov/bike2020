package fscms.mods.edu.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.cmm.util.UploadHelper;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.edu.vo.EduSearchVO;
import fscms.mods.edu.vo.EduVO;
import fscms.mods.file.util.FileHelper;
import net.sf.json.JSONArray;

@Service("eduService")
public class EduServiceImpl extends EgovAbstractServiceImpl implements EduService{

	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;
	
	@Autowired
	private UploadHelper uploadHelper;
	
	@Autowired
	private FileHelper fileHelper;
	
	@Resource(name = "eduMapper")
	private EduMapper eduDAO;

	@Override
	public int selectTCount(EduSearchVO searchVO) throws Exception {
		return eduDAO.selectTCount(searchVO);
	}

	@Override
	public List<EduVO> selectPageList(EduSearchVO searchVO) throws Exception {
		return eduDAO.selectPageList(searchVO);
	}

	@Override
	public EduVO selectObj(EduVO vo) throws Exception {
		return eduDAO.selectObj(vo);
	}

	@Override
	public void insertInfo(HttpServletRequest request, EduVO vo, String mngrId) throws Exception {
		eduDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_EDU", null, this.getData(vo.getEduSn()), null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, EduVO vo, String mngrId) throws Exception {
		eduDAO.updateInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_EDU", bfData, this.getData(vo.getEduSn()), null, mngrId);
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, EduVO item, String mngrId) throws Exception {
		eduDAO.deleteInfo(item);
		
		dataChghstHelper.setInsert(request, "FS_EDU", bfData, null, null, mngrId);
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		EduVO eduVO = new EduVO();
		eduVO.setEduSn(key);
		EduVO item = this.selectObj(eduVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}
}
