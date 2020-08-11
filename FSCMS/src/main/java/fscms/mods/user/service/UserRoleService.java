package fscms.mods.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.user.vo.UserRoleSearchVO;
import fscms.mods.user.vo.UserRoleVO;

public interface UserRoleService {
	
	UserRoleVO selectObj(UserRoleVO vo) throws Exception;
	
	List<UserRoleVO> selectList(UserRoleVO vo) throws Exception;
	
	int selectTCount(UserRoleSearchVO searchVO) throws Exception;
	
	List<UserRoleVO> selectPageList(UserRoleSearchVO searchVO) throws Exception;
	
	int selectMaxO(UserRoleVO vo) throws Exception;
	
	void insertInfo(HttpServletRequest request, UserRoleVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, UserRoleVO vo, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, String bfData, UserRoleVO vo, String mngrId) throws Exception;

}
