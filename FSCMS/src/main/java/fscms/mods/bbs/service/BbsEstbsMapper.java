package fscms.mods.bbs.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.bbs.vo.BbsEstbsSearchVO;
import fscms.mods.bbs.vo.BbsEstbsVO;

@Mapper("bbsEstbsMapper")
public interface BbsEstbsMapper {
	
	BbsEstbsVO selectObj(BbsEstbsVO vo) throws Exception;
	
	int selectTCount(BbsEstbsSearchVO searchVO) throws Exception;
	
	List<BbsEstbsVO> selectPageList(BbsEstbsSearchVO searchVO) throws Exception;
	
	void insertInfo(BbsEstbsVO vo) throws Exception;
	
	void updateInfo(BbsEstbsVO vo) throws Exception;
	
	void deleteInfo(BbsEstbsVO vo) throws Exception;

}
