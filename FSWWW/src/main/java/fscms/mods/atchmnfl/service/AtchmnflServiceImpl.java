package fscms.mods.atchmnfl.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.atchmnfl.vo.AtchmnflVO;
import fscms.mods.data.util.DataChghstHelper;
import net.sf.json.JSONArray;

@Service("atchmnflService")
public class AtchmnflServiceImpl extends EgovAbstractServiceImpl implements AtchmnflService {
	
	@Resource(name = "atchmnflMapper")
	private AtchmnflMapper atchmnflDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public AtchmnflVO selectObj(AtchmnflVO vo) throws Exception {
		return atchmnflDAO.selectObj(vo);
	}

	@Override
	public List<AtchmnflVO> selectList(AtchmnflVO vo) throws Exception {
		return atchmnflDAO.selectList(vo);
	}

	@Override
	public void insertInfo(HttpServletRequest request, AtchmnflVO vo, String userId)
			throws Exception {
		atchmnflDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_ATCHMNFL", null, this.getData(vo.getAtchmnflSn()), userId, null);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, AtchmnflVO vo, String userId)
			throws Exception {
		atchmnflDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_ATCHMNFL", bfData, this.getData(vo.getAtchmnflSn()), userId, null);
		}
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, AtchmnflVO vo, String userId)
			throws Exception {
		atchmnflDAO.deleteInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_ATCHMNFL", bfData, null, userId, null);
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		AtchmnflVO atchmnflVO = new AtchmnflVO();
		atchmnflVO.setAtchmnflSn(key);
		AtchmnflVO item = this.selectObj(atchmnflVO);
		
		if(item != null) {
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
