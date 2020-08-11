package fscms.mods.atchmnfl.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.atchmnfl.vo.AtchmnflVO;

public interface AtchmnflService {
	
	AtchmnflVO selectObj(AtchmnflVO vo) throws Exception;
	
	List<AtchmnflVO> selectList(AtchmnflVO vo) throws Exception;
	
	void insertInfo(HttpServletRequest request, AtchmnflVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, AtchmnflVO vo, String mngrId) throws Exception;

	void deleteInfo(HttpServletRequest request, String bfData, AtchmnflVO vo, String mngrId) throws Exception;

}
