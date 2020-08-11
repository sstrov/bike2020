package fscms.mods.bbs.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.bbs.vo.BbsThumbVO;
import fscms.mods.data.util.DataChghstHelper;
import net.sf.json.JSONArray;

@Service("bbsThumbService")
public class BbsThumbServiceImpl extends EgovAbstractServiceImpl implements BbsThumbService {
	
	@Resource(name = "bbsThumbMapper")
	private BbsThumbMapper bbsThumbDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public BbsThumbVO selectObj(BbsThumbVO vo) throws Exception {
		return bbsThumbDAO.selectObj(vo);
	}

	@Override
	public List<BbsThumbVO> selectList(BbsThumbVO vo) throws Exception {
		return bbsThumbDAO.selectList(vo);
	}

	@Override
	public void insertInfo(HttpServletRequest request, BbsThumbVO vo, String mngrId) throws Exception {
		bbsThumbDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_BBS_THUMB", null, this.getData(vo.getBbsThumbSn()), null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, BbsThumbVO vo, String mngrId) throws Exception {
		bbsThumbDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BBS_THUMB", bfData, this.getData(vo.getBbsThumbSn()), null, mngrId);
		}
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, BbsThumbVO vo, String mngrId) throws Exception {
		bbsThumbDAO.deleteInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BBS_THUMB", bfData, null, null, mngrId);
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		BbsThumbVO bbsThumbVO = new BbsThumbVO();
		bbsThumbVO.setBbsThumbSn(key);
		BbsThumbVO item = this.selectObj(bbsThumbVO);
		
		if(item != null) {
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
