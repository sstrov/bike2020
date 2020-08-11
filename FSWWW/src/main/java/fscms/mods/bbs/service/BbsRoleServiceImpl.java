package fscms.mods.bbs.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.bbs.vo.BbsRoleVO;

@Service("bbsRoleService")
public class BbsRoleServiceImpl extends EgovAbstractServiceImpl implements BbsRoleService {
	
	@Resource(name = "bbsRoleMapper")
	private BbsRoleMapper bbsRoleDAO;
	
	@Override
	public BbsRoleVO selectObj(BbsRoleVO vo) throws Exception {
		return bbsRoleDAO.selectObj(vo);
	}

	@Override
	public List<BbsRoleVO> selectList(BbsRoleVO vo) throws Exception {
		return bbsRoleDAO.selectList(vo); 
	}

}
