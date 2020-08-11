package fscms.mods.bbs.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.bbs.vo.BbsThumbVO;

@Mapper("bbsThumbMapper")
public interface BbsThumbMapper {
	
	BbsThumbVO selectObj(BbsThumbVO vo) throws Exception;
	
	List<BbsThumbVO> selectList(BbsThumbVO vo) throws Exception;
	
	void insertInfo(BbsThumbVO vo) throws Exception;
	
	void updateInfo(BbsThumbVO vo) throws Exception;
	
	void deleteInfo(BbsThumbVO vo) throws Exception;

}
