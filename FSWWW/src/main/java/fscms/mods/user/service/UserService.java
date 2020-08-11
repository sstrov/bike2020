package fscms.mods.user.service;

import java.util.List;

import fscms.mods.user.vo.UserSearchVO;
import fscms.mods.user.vo.UserVO;

public interface UserService {
	
	UserVO selectObj(UserVO vo) throws Exception;
	
	int selectTCount(UserSearchVO searchVO) throws Exception;
	
	List<UserVO> selectPageList(UserSearchVO searchVO) throws Exception;
	
}
