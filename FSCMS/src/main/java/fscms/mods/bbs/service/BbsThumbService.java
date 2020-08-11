package fscms.mods.bbs.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.bbs.vo.BbsThumbVO;

public interface BbsThumbService {
	
	BbsThumbVO selectObj(BbsThumbVO vo) throws Exception;
	
	List<BbsThumbVO> selectList(BbsThumbVO vo) throws Exception;
	
	void insertInfo(HttpServletRequest request, BbsThumbVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, BbsThumbVO vo, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, String bfData, BbsThumbVO vo, String mngrId) throws Exception;

}
