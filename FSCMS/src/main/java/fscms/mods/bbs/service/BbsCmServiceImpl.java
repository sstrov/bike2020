package fscms.mods.bbs.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.bbs.vo.BbsCmSearchVO;
import fscms.mods.bbs.vo.BbsCmVO;
import fscms.mods.bbs.vo.BbsEstbsVO;
import fscms.mods.data.util.DataChghstHelper;
import net.sf.json.JSONArray;

@Service("bbsCmService")
public class BbsCmServiceImpl extends EgovAbstractServiceImpl implements BbsCmService {
	
	@Resource(name = "bbsCmMapper")
	private BbsCmMapper bbsCmDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public BbsCmVO selectObj(BbsCmVO vo) throws Exception {
		return bbsCmDAO.selectObj(vo);
	}

	@Override
	public List<BbsCmVO> selectList(BbsCmSearchVO searchVO) throws Exception {
		return bbsCmDAO.selectList(searchVO);
	}

	@Override
	public int selectTCount(BbsCmSearchVO searchVO) throws Exception {
		return bbsCmDAO.selectTCount(searchVO);
	}

	@Override
	public List<BbsCmVO> selectPageList(BbsCmSearchVO searchVO) throws Exception {
		return bbsCmDAO.selectPageList(searchVO);
	}

	@Override
	public int selectSumCmntCnt(BbsCmVO vo) throws Exception {
		return bbsCmDAO.selectSumCmntCnt(vo);
	}

	@Override
	public void insertInfo(HttpServletRequest request, BbsCmVO vo, String mngrId) throws Exception {
		bbsCmDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_BBS_CM", null, this.getData(vo.getBbsCmSn()), null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, BbsCmVO vo, String mngrId) throws Exception {
		bbsCmDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BBS_CM", bfData, this.getData(vo.getBbsCmSn()), null, mngrId);
		}
	}

	@Override
	public void updateOrder(HttpServletRequest request, String bfData, BbsCmVO vo, String mngrId) throws Exception {
		bbsCmDAO.updateOrder(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BBS_CM", bfData, this.getData(vo.getBbsCmSn()), null, mngrId);
		}
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, BbsCmVO vo, String mngrId) throws Exception {
		bbsCmDAO.deleteInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BBS_CM", bfData, null, null, mngrId);
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		BbsCmVO bbsCmVO = new BbsCmVO();
		bbsCmVO.setBbsCmSn(key);
		BbsCmVO item = this.selectObj(bbsCmVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
