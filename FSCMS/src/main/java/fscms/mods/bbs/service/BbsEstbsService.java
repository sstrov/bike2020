package fscms.mods.bbs.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.bbs.vo.BbsEstbsSearchVO;
import fscms.mods.bbs.vo.BbsEstbsVO;

public interface BbsEstbsService {
	
	BbsEstbsVO selectObj(BbsEstbsVO vo) throws Exception;
	
	int selectTCount(BbsEstbsSearchVO searchVO) throws Exception;
	
	List<BbsEstbsVO> selectPageList(BbsEstbsSearchVO searchVO) throws Exception;
	
	void insertInfo(HttpServletRequest request, BbsEstbsVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, BbsEstbsVO vo, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, String bfData, BbsEstbsVO vo, String mngrId) throws Exception;

}
