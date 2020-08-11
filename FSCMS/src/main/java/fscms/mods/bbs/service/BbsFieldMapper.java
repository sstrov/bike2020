package fscms.mods.bbs.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.bbs.vo.BbsFieldVO;

@Mapper("bbsFieldMapper")
public interface BbsFieldMapper {
	
	BbsFieldVO selectObj(BbsFieldVO vo) throws Exception;
	
	List<BbsFieldVO> selectList(BbsFieldVO vo) throws Exception;
	
	void insertInfo(BbsFieldVO vo) throws Exception;
	
	void updateInfo(BbsFieldVO vo) throws Exception;
	
	void updateOrder(BbsFieldVO vo) throws Exception;
	
	void deleteInfo(BbsFieldVO vo) throws Exception;

}
