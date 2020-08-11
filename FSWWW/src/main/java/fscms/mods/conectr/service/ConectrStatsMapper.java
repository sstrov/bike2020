package fscms.mods.conectr.service;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.conectr.vo.ConectrStatsVO;

@Mapper("conectrStatsMapper")
public interface ConectrStatsMapper {
	
	void insertInfo(ConectrStatsVO vo) throws Exception;

}
