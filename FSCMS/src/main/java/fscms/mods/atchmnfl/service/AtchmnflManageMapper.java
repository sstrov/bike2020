package fscms.mods.atchmnfl.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.atchmnfl.vo.AtchmnflManageSearchVO;
import fscms.mods.atchmnfl.vo.AtchmnflManageVO;

@Mapper("atchmnflManageMapper")
public interface AtchmnflManageMapper {
	
	AtchmnflManageVO selectObj(AtchmnflManageVO vo) throws Exception;
	
	int selectTCount(AtchmnflManageSearchVO searchVO) throws Exception;
	
	List<AtchmnflManageVO> selectPageList(AtchmnflManageSearchVO searchVO) throws Exception;
	
	int selectMaxKey() throws Exception;
	
	void insertInfo(AtchmnflManageVO vo) throws Exception;
	
	void deleteInfo(AtchmnflManageVO vo) throws Exception;

}
