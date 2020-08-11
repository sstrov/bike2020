package fscms.mods.user.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.user.vo.UserMenuVO;

@Mapper("userMenuMapper")
public interface UserMenuMapper {
	
	UserMenuVO selectObj(UserMenuVO vo) throws Exception;
	
	List<UserMenuVO> selectList(UserMenuVO vo) throws Exception;
	
}
