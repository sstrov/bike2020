package fscms.mods.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.user.vo.UserMenuVO;

public interface UserMenuService {
	
	UserMenuVO selectObj(UserMenuVO vo) throws Exception;
	
	List<UserMenuVO> selectList(UserMenuVO vo) throws Exception;
	
	List<UserMenuVO> selectList_C(UserMenuVO vo) throws Exception;
	
	List<UserMenuVO> selectList_acc(UserMenuVO vo) throws Exception;
	
	int selectMaxO(UserMenuVO vo) throws Exception;
	
	int selectMaxG(UserMenuVO vo) throws Exception;
	
	void insertInfo(HttpServletRequest request, UserMenuVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, UserMenuVO vo, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, UserMenuVO vo, String mngrId) throws Exception;

}
