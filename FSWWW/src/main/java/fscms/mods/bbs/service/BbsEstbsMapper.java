package fscms.mods.bbs.service;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.bbs.vo.BbsEstbsVO;

@Mapper("bbsEstbsMapper")
public interface BbsEstbsMapper {
	
	BbsEstbsVO selectObj(BbsEstbsVO vo) throws Exception;
	
}
