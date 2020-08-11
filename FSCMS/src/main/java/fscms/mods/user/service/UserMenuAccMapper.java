package fscms.mods.user.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.user.vo.UserMenuAccVO;

@Mapper("userMenuAccMapper")
public interface UserMenuAccMapper {
	
	UserMenuAccVO selectObj(UserMenuAccVO vo) throws Exception;
	
	List<UserMenuAccVO> selectList(UserMenuAccVO vo) throws Exception;
	
	void insertInfo(UserMenuAccVO vo) throws Exception;
	
	void deleteInfo(UserMenuAccVO vo) throws Exception;

}
