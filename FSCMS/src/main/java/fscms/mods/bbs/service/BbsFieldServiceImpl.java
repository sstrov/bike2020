package fscms.mods.bbs.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.bbs.vo.BbsFieldVO;
import fscms.mods.data.util.DataChghstHelper;
import net.sf.json.JSONArray;

@Service("bbsFieldService")
public class BbsFieldServiceImpl extends EgovAbstractServiceImpl implements BbsFieldService {
	
	@Resource(name = "bbsFieldMapper")
	private BbsFieldMapper bbsFieldDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public BbsFieldVO selectObj(BbsFieldVO vo) throws Exception {
		return bbsFieldDAO.selectObj(vo);
	}

	@Override
	public List<BbsFieldVO> selectList(BbsFieldVO vo) throws Exception {
		return bbsFieldDAO.selectList(vo);
	}

	@Override
	public void insertInfo(HttpServletRequest request, BbsFieldVO vo, String mngrId) throws Exception {
		bbsFieldDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_BBS_FIELD", null, this.getData(vo.getBbsEstbsSn()), null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, BbsFieldVO vo, String mngrId) throws Exception {
		bbsFieldDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BBS_FIELD", bfData, this.getData(vo.getBbsEstbsSn()), null, mngrId);
		}
	}

	@Override
	public void updateOrder(HttpServletRequest request, String bfData, BbsFieldVO vo, String mngrId) throws Exception {
		bbsFieldDAO.updateOrder(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BBS_FIELD", bfData, this.getData(vo.getBbsEstbsSn()), null, mngrId);
		}
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, BbsFieldVO vo, String mngrId) throws Exception {
		bbsFieldDAO.deleteInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BBS_FIELD", bfData, null, null, mngrId);
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		BbsFieldVO bbsFieldVO = new BbsFieldVO();
		bbsFieldVO.setBbsFieldSn(key);
		BbsFieldVO item = this.selectObj(bbsFieldVO);
		
		if(item != null) {
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
