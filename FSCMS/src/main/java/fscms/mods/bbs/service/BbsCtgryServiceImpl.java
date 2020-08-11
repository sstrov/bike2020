package fscms.mods.bbs.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.bbs.vo.BbsCtgryVO;
import fscms.mods.data.util.DataChghstHelper;
import net.sf.json.JSONArray;

@Service("bbsCtgryService")
public class BbsCtgryServiceImpl extends EgovAbstractServiceImpl implements BbsCtgryService {
	
	@Resource(name = "bbsCtgryMapper")
	private BbsCtgryMapper bbsCtgryDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public BbsCtgryVO selectObj(BbsCtgryVO vo) throws Exception {
		return bbsCtgryDAO.selectObj(vo);
	}

	@Override
	public List<BbsCtgryVO> selectList(BbsCtgryVO vo) throws Exception {
		return bbsCtgryDAO.selectList(vo);
	}

	@Override
	public void insertInfo(HttpServletRequest request, BbsCtgryVO vo, String mngrId) throws Exception {
		bbsCtgryDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_BBS_CTGRY", null, this.getData(vo.getBbsCtgrySn()), null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, BbsCtgryVO vo, String mngrId) throws Exception {
		bbsCtgryDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BBS_CTGRY", bfData, this.getData(vo.getBbsCtgrySn()), null, mngrId);
		}
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, BbsCtgryVO vo, String mngrId) throws Exception {
		bbsCtgryDAO.deleteInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BBS_CTGRY", bfData, null, null, mngrId);
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		BbsCtgryVO bbsCtgryVO = new BbsCtgryVO();
		bbsCtgryVO.setBbsCtgrySn(key);
		BbsCtgryVO item = this.selectObj(bbsCtgryVO);
		
		if(item != null) {
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
