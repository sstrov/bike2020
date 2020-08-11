package fscms.mods.bbs.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.bbs.vo.BbsCmSearchVO;
import fscms.mods.bbs.vo.BbsCmVO;

@Mapper("bbsCmMapper")
public interface BbsCmMapper {
	
	BbsCmVO selectObj(BbsCmVO vo) throws Exception;
	
	List<BbsCmVO> selectList(BbsCmSearchVO searchVO) throws Exception;
	
	int selectTCount(BbsCmSearchVO searchVO) throws Exception;
	
	List<BbsCmVO> selectPageList(BbsCmSearchVO searchVO) throws Exception;
	
	int selectSumCmntCnt(BbsCmVO vo) throws Exception;
	
	void insertInfo(BbsCmVO vo) throws Exception;
	
	void updateInfo(BbsCmVO vo) throws Exception;
	
	void updateOrder(BbsCmVO vo) throws Exception;
	
	void deleteInfo(BbsCmVO vo) throws Exception;

}
