package fscms.mods.bbs.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.bbs.vo.BbsFieldVO;

public interface BbsFieldService {
	
	BbsFieldVO selectObj(BbsFieldVO vo) throws Exception;
	
	List<BbsFieldVO> selectList(BbsFieldVO vo) throws Exception;
	
	void insertInfo(HttpServletRequest request, BbsFieldVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, BbsFieldVO vo, String mngrId) throws Exception;
	
	void updateOrder(HttpServletRequest request, String bfData, BbsFieldVO vo, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, String bfData, BbsFieldVO vo, String mngrId) throws Exception;

}
