package fscms.mods.atchmnfl.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.atchmnfl.vo.AtchmnflVO;

@Mapper("atchmnflMapper")
public interface AtchmnflMapper {
	
	AtchmnflVO selectObj(AtchmnflVO vo) throws Exception;
	
	List<AtchmnflVO> selectList(AtchmnflVO vo) throws Exception;
	
	void insertInfo(AtchmnflVO vo) throws Exception;
	
	void updateInfo(AtchmnflVO vo) throws Exception;

	void deleteInfo(AtchmnflVO vo) throws Exception;

}
