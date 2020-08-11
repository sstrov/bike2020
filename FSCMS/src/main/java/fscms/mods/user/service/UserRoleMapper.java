package fscms.mods.user.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.user.vo.UserRoleSearchVO;
import fscms.mods.user.vo.UserRoleVO;

@Mapper("userRoleMapper")
public interface UserRoleMapper {
	
	UserRoleVO selectObj(UserRoleVO vo) throws Exception;
	
	List<UserRoleVO> selectList(UserRoleVO vo) throws Exception;
	
	int selectTCount(UserRoleSearchVO searchVO) throws Exception;
	
	List<UserRoleVO> selectPageList(UserRoleSearchVO searchVO) throws Exception;
	
	int selectMaxO(UserRoleVO vo) throws Exception;
	
	void insertInfo(UserRoleVO vo) throws Exception;
	
	void updateInfo(UserRoleVO vo) throws Exception;
	
	void deleteInfo(UserRoleVO vo) throws Exception;

}
