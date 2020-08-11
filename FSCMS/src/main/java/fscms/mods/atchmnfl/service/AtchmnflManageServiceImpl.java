package fscms.mods.atchmnfl.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.atchmnfl.vo.AtchmnflManageSearchVO;
import fscms.mods.atchmnfl.vo.AtchmnflManageVO;
import fscms.mods.data.util.DataChghstHelper;
import net.sf.json.JSONArray;

@Service("atchmnflManageService")
public class AtchmnflManageServiceImpl extends EgovAbstractServiceImpl implements AtchmnflManageService {
	
	@Resource(name = "atchmnflManageMapper")
	private AtchmnflManageMapper atchmnflManageDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public AtchmnflManageVO selectObj(AtchmnflManageVO vo) throws Exception {
		return atchmnflManageDAO.selectObj(vo);
	}

	@Override
	public int selectTCount(AtchmnflManageSearchVO searchVO) throws Exception {
		return atchmnflManageDAO.selectTCount(searchVO);
	}

	@Override
	public List<AtchmnflManageVO> selectPageList(AtchmnflManageSearchVO searchVO) throws Exception {
		return atchmnflManageDAO.selectPageList(searchVO);
	}

	@Override
	public int selectMaxKey() throws Exception {
		return atchmnflManageDAO.selectMaxKey();
	}

	@Override
	public void insertInfo(HttpServletRequest request, AtchmnflManageVO vo, String mngrId) throws Exception {
		atchmnflManageDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "T_CODA_ATCHMNFL_MANAGE", null, this.getData(vo.getAtchmnflManageSn()), null, mngrId);
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, AtchmnflManageVO vo, String mngrId)
			throws Exception {
		atchmnflManageDAO.deleteInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "T_CODA_ATCHMNFL_MANAGE", bfData, null, null, mngrId);
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		AtchmnflManageVO atchmnflManageVO = new AtchmnflManageVO();
		atchmnflManageVO.setAtchmnflManageSn(key);
		AtchmnflManageVO item = this.selectObj(atchmnflManageVO);
		
		if(item != null) {
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
