package fscms.mods.bbs.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.bbs.vo.BbsSknVO;

public interface BbsSknService {
	
	BbsSknVO selectObj(BbsSknVO vo) throws Exception;
	
	List<BbsSknVO> selectList(BbsSknVO vo) throws Exception;
	
	List<BbsSknVO> selectList_C(BbsSknVO vo) throws Exception;
	
	int selectMaxKey() throws Exception;
	
	int selectMaxO(BbsSknVO vo) throws Exception;
	
	void insertInfo(HttpServletRequest request, BbsSknVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, BbsSknVO vo, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, BbsSknVO vo, String mngrId) throws Exception;

}
