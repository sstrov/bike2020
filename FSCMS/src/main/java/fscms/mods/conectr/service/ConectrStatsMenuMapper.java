package fscms.mods.conectr.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.conectr.vo.ConectrStatsMenuSearchVO;
import fscms.mods.conectr.vo.ConectrStatsMenuVO;

@Mapper("conectrStatsMenuMapper")
public interface ConectrStatsMenuMapper {
	
	List<ConectrStatsMenuVO> selectList(ConectrStatsMenuSearchVO searchVO) throws Exception;

}
