package fscms.mods.conectr.service;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.conectr.vo.ConectrStatsMenuVO;

@Mapper("conectrStatsMenuMapper")
public interface ConectrStatsMenuMapper {
	
	void insertInfo(ConectrStatsMenuVO vo) throws Exception;

}
