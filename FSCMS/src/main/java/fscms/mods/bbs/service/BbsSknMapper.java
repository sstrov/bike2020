package fscms.mods.bbs.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.bbs.vo.BbsSknVO;

@Mapper("bbsSknMapper")
public interface BbsSknMapper {
	
	BbsSknVO selectObj(BbsSknVO vo) throws Exception;
	
	List<BbsSknVO> selectList(BbsSknVO vo) throws Exception;
	
	List<BbsSknVO> selectList_C(BbsSknVO vo) throws Exception;
	
	int selectMaxKey() throws Exception;
	
	int selectMaxO(BbsSknVO vo) throws Exception;
	
	void insertInfo(BbsSknVO vo) throws Exception;
	
	void updateInfo(BbsSknVO vo) throws Exception;
	
	void deleteInfo(BbsSknVO vo) throws Exception;

}
