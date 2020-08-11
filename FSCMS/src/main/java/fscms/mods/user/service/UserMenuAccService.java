package fscms.mods.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.user.vo.UserMenuAccVO;

public interface UserMenuAccService {
	
	UserMenuAccVO selectObj(UserMenuAccVO vo) throws Exception;
	
	List<UserMenuAccVO> selectList(UserMenuAccVO vo) throws Exception;
	
	void insertInfo(HttpServletRequest request, UserMenuAccVO vo, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, String bfData, UserMenuAccVO vo, String mngrId) throws Exception;

}
