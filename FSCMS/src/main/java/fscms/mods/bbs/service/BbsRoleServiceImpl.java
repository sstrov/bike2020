package fscms.mods.bbs.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.bbs.vo.BbsRoleVO;
import fscms.mods.data.util.DataChghstHelper;
import net.sf.json.JSONArray;

@Service("bbsRoleService")
public class BbsRoleServiceImpl extends EgovAbstractServiceImpl implements BbsRoleService {
	
	@Resource(name = "bbsRoleMapper")
	private BbsRoleMapper bbsRoleDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public BbsRoleVO selectObj(BbsRoleVO vo) throws Exception {
		return bbsRoleDAO.selectObj(vo);
	}

	@Override
	public List<BbsRoleVO> selectList(BbsRoleVO vo) throws Exception {
		return bbsRoleDAO.selectList(vo); 
	}

	@Override
	public void insertInfo(HttpServletRequest request, BbsRoleVO vo, String mngrId) throws Exception {
		bbsRoleDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_BBS_ROLE", null, this.getData(vo.getBbsRoleSn()), null, mngrId);
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, BbsRoleVO vo, String mngrId) throws Exception {
		bbsRoleDAO.deleteInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BBS_ROLE", bfData, null, null, mngrId);
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		BbsRoleVO bbsRoleVO = new BbsRoleVO();
		bbsRoleVO.setBbsRoleSn(key);
		BbsRoleVO item = this.selectObj(bbsRoleVO);
		
		if(item != null) {
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
