package fscms.mods.mngr.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.mngr.vo.MngrRoleSearchVO;
import fscms.mods.mngr.vo.MngrRoleVO;

@Mapper("mngrRoleMapper")
public interface MngrRoleMapper {

	MngrRoleVO selectObj(MngrRoleVO vo) throws Exception;

	List<MngrRoleVO> selectList(MngrRoleVO vo) throws Exception;

	int selectTCount(MngrRoleSearchVO searchVO) throws Exception;

	List<MngrRoleVO> selectPageList(MngrRoleSearchVO searchVO) throws Exception;

	int selectMaxO(MngrRoleVO vo) throws Exception;

	void insertInfo(MngrRoleVO vo) throws Exception;

	void updateInfo(MngrRoleVO vo) throws Exception;

	void deleteInfo(MngrRoleVO vo) throws Exception;

}
