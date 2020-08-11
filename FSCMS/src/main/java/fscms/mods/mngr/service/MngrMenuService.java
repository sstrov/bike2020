package fscms.mods.mngr.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.mngr.vo.MngrMenuVO;

public interface MngrMenuService {
	
	MngrMenuVO selectObj(MngrMenuVO vo) throws Exception;
	
	List<MngrMenuVO> selectList(MngrMenuVO vo) throws Exception;
	
	List<MngrMenuVO> selectList_C(MngrMenuVO vo) throws Exception;
	
	List<MngrMenuVO> selectList_acc(MngrMenuVO vo) throws Exception;
	
	int selectMaxO(MngrMenuVO vo) throws Exception;
	
	void insertInfo(HttpServletRequest request, MngrMenuVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, MngrMenuVO vo, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, MngrMenuVO vo, String mngrId) throws Exception;

}
