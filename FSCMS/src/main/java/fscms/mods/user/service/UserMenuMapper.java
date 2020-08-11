package fscms.mods.user.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.user.vo.UserMenuVO;

@Mapper("userMenuMapper")
public interface UserMenuMapper {
	
	UserMenuVO selectObj(UserMenuVO vo) throws Exception;
	
	List<UserMenuVO> selectList(UserMenuVO vo) throws Exception;
	
	List<UserMenuVO> selectList_C(UserMenuVO vo) throws Exception;
	
	List<UserMenuVO> selectList_acc(UserMenuVO vo) throws Exception;
	
	int selectMaxO(UserMenuVO vo) throws Exception;
	
	int selectMaxG(UserMenuVO vo) throws Exception;
	
	void insertInfo(UserMenuVO vo) throws Exception;
	
	void updateInfo(UserMenuVO vo) throws Exception;
	
	void deleteInfo(UserMenuVO vo) throws Exception;

}
