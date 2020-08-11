package fscms.mods.bbs.service;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import fscms.mods.bbs.vo.BbsCtgrySubVO;

public interface BbsCtgrySubService {
	
	BbsCtgrySubVO selectObj(BbsCtgrySubVO vo) throws Exception;
	
	List<BbsCtgrySubVO> selectList(BbsCtgrySubVO vo) throws Exception;
	
	void insertInfo(HttpServletRequest request, BbsCtgrySubVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, BbsCtgrySubVO vo, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, String bfData, BbsCtgrySubVO vo, String mngrId) throws Exception;

	void insertSubCtgry(HttpServletRequest request, BbsCtgrySubVO cvo, String mngrId) throws Exception;

	void updateSubCtgry(HttpServletRequest request, BbsCtgrySubVO cvo, String mngrId) throws Exception;

}
