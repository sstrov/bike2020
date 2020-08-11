package fscms.mods.bbs.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.bbs.vo.BbsFieldVO;

@Mapper("bbsFieldMapper")
public interface BbsFieldMapper {
	
	List<BbsFieldVO> selectList(BbsFieldVO vo) throws Exception;
	
}
