package fscms.mods.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.auth.vo.AuthUploadVO;
import fscms.mods.user.vo.UserSearchVO;
import fscms.mods.user.vo.UserVO;

public interface UserService {
	
	UserVO selectObj(UserVO vo) throws Exception;
	
	int selectTCount(UserSearchVO searchVO) throws Exception;
	
	List<UserVO> selectPageList(UserSearchVO searchVO) throws Exception;
	
	void insertInfo(HttpServletRequest request, UserVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, UserVO vo, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, String bfData, UserVO vo, String mngrId) throws Exception;

	void deleteNote(HttpServletRequest request, String bfData, UserVO vo, String mngrId) throws Exception;
	
}
