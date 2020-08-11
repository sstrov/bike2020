package fscms.mods.bbs.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.bbs.vo.BbsCtgryVO;

@Mapper("bbsCtgryMapper")
public interface BbsCtgryMapper {
	
	BbsCtgryVO selectObj(BbsCtgryVO vo) throws Exception;
	
	List<BbsCtgryVO> selectList(BbsCtgryVO vo) throws Exception;
	
	void insertInfo(BbsCtgryVO vo) throws Exception;
	
	void updateInfo(BbsCtgryVO vo) throws Exception;
	
	void deleteInfo(BbsCtgryVO vo) throws Exception;

}
