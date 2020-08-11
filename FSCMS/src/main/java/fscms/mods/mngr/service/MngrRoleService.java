package fscms.mods.mngr.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.mngr.vo.MngrRoleSearchVO;
import fscms.mods.mngr.vo.MngrRoleVO;

public interface MngrRoleService {
	
	MngrRoleVO selectObj(MngrRoleVO vo) throws Exception;
	
	List<MngrRoleVO> selectList(MngrRoleVO vo) throws Exception;
	
	int selectTCount(MngrRoleSearchVO searchVO) throws Exception;
	
	List<MngrRoleVO> selectPageList(MngrRoleSearchVO searchVO) throws Exception;
	
	int selectMaxO(MngrRoleVO vo) throws Exception;
	
	void insertInfo(HttpServletRequest request, MngrRoleVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, MngrRoleVO vo, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, String bfData, MngrRoleVO vo, String mngrId) throws Exception;

}
