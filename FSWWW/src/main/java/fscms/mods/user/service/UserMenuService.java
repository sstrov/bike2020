package fscms.mods.user.service;

import java.util.List;

import fscms.mods.user.vo.UserMenuVO;

public interface UserMenuService {
	
	UserMenuVO selectObj(UserMenuVO vo) throws Exception;
	
	List<UserMenuVO> selectList(UserMenuVO vo) throws Exception;
	
}
