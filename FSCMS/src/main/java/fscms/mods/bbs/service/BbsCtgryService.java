package fscms.mods.bbs.service;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import fscms.mods.bbs.vo.BbsCtgryVO;

public interface BbsCtgryService {
	
	BbsCtgryVO selectObj(BbsCtgryVO vo) throws Exception;
	
	List<BbsCtgryVO> selectList(BbsCtgryVO vo) throws Exception;
	
	void insertInfo(HttpServletRequest request, BbsCtgryVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, BbsCtgryVO vo, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, String bfData, BbsCtgryVO vo, String mngrId) throws Exception;

}
