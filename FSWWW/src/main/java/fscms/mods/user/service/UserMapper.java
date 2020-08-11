package fscms.mods.user.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.user.vo.UserSearchVO;
import fscms.mods.user.vo.UserVO;

@Mapper("userMapper")
public interface UserMapper {
	
	UserVO selectObj(UserVO vo) throws Exception;
	
	int selectTCount(UserSearchVO searchVO) throws Exception;
	
	List<UserVO> selectPageList(UserSearchVO searchVO) throws Exception;
	
}
