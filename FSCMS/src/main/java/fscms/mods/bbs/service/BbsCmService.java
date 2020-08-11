package fscms.mods.bbs.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.bbs.vo.BbsCmSearchVO;
import fscms.mods.bbs.vo.BbsCmVO;

public interface BbsCmService {
	
	BbsCmVO selectObj(BbsCmVO vo) throws Exception;
	
	List<BbsCmVO> selectList(BbsCmSearchVO searchVO) throws Exception;
	
	int selectTCount(BbsCmSearchVO searchVO) throws Exception;
	
	List<BbsCmVO> selectPageList(BbsCmSearchVO searchVO) throws Exception;
	
	int selectSumCmntCnt(BbsCmVO vo) throws Exception;
	
	void insertInfo(HttpServletRequest request, BbsCmVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, BbsCmVO vo, String mngrId) throws Exception;
	
	void updateOrder(HttpServletRequest request, String bfData, BbsCmVO vo, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, String bfData, BbsCmVO vo, String mngrId) throws Exception;

}
