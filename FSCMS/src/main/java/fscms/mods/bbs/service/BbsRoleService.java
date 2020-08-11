package fscms.mods.bbs.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.bbs.vo.BbsRoleVO;

public interface BbsRoleService {
	
	BbsRoleVO selectObj(BbsRoleVO vo) throws Exception;
	
	List<BbsRoleVO> selectList(BbsRoleVO vo) throws Exception;
	
	void insertInfo(HttpServletRequest request, BbsRoleVO vo, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, String bfData, BbsRoleVO vo, String mngrId) throws Exception;
	
}
