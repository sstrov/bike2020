package fscms.mods.atchmnfl.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.atchmnfl.vo.AtchmnflManageSearchVO;
import fscms.mods.atchmnfl.vo.AtchmnflManageVO;

public interface AtchmnflManageService {
	
	AtchmnflManageVO selectObj(AtchmnflManageVO vo) throws Exception;
	
	int selectTCount(AtchmnflManageSearchVO searchVO) throws Exception;
	
	List<AtchmnflManageVO> selectPageList(AtchmnflManageSearchVO searchVO) throws Exception;
	
	int selectMaxKey() throws Exception;
	
	void insertInfo(HttpServletRequest request, AtchmnflManageVO vo, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, String bfData, AtchmnflManageVO vo, String mngrId) throws Exception;

}
