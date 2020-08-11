package fscms.mods.bbs.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.bbs.vo.BbsSearchVO;
import fscms.mods.bbs.vo.BbsVO;

public interface BbsService {
	
	BbsVO selectObj(BbsVO vo) throws Exception;
	
	BbsVO selectRnum(BbsVO vo) throws Exception;
	
	List<BbsVO> selectList(BbsSearchVO searchVO) throws Exception;
	
	int selectTCount(BbsSearchVO searchVO) throws Exception;
	
	List<BbsVO> selectPageList(BbsSearchVO searchVO) throws Exception;
	
	void insertInfo(HttpServletRequest request, BbsVO vo, String userId, String uniqueId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, BbsVO vo, String userId, String uniqueId) throws Exception;
	
	void updateOrder(HttpServletRequest request, String bfData, BbsVO vo, String userId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, BbsVO vo, String userId) throws Exception;

}
