package fscms.mods.bbs.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.bbs.vo.BbsSearchVO;
import fscms.mods.bbs.vo.BbsVO;

@Mapper("bbsMapper")
public interface BbsMapper {
	
	BbsVO selectObj(BbsVO vo) throws Exception;
	
	BbsVO selectRnum(BbsVO vo) throws Exception;
	
	List<BbsVO> selectList(BbsSearchVO searchVO) throws Exception;
	
	int selectTCount(BbsSearchVO searchVO) throws Exception;
	
	List<BbsVO> selectPageList(BbsSearchVO searchVO) throws Exception;
	
	void insertInfo(BbsVO vo) throws Exception;
	
	void updateInfo(BbsVO vo) throws Exception;
	
	void updateOrder(BbsVO vo) throws Exception;
	
	void deleteInfo(BbsVO vo) throws Exception;

}
