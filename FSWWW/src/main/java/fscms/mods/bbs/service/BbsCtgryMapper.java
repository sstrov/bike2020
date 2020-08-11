package fscms.mods.bbs.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.bbs.vo.BbsCtgryVO;

@Mapper("bbsCtgryMapper")
public interface BbsCtgryMapper {
	
	List<BbsCtgryVO> selectList(BbsCtgryVO vo) throws Exception;

}
