package fscms.mods.bbs.service;

import java.util.List;

import fscms.mods.bbs.vo.BbsRoleVO;

public interface BbsRoleService {
	
	BbsRoleVO selectObj(BbsRoleVO vo) throws Exception;
	
	List<BbsRoleVO> selectList(BbsRoleVO vo) throws Exception;
	
}
