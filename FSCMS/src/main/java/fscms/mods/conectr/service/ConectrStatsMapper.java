package fscms.mods.conectr.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.conectr.vo.ConectrStatsSearchVO;
import fscms.mods.conectr.vo.ConectrStatsVO;

@Mapper("conectrStatsMapper")
public interface ConectrStatsMapper {
	
	List<ConectrStatsVO> selectYear(ConectrStatsSearchVO searchVO) throws Exception;
	
	List<ConectrStatsVO> selectMonth(ConectrStatsSearchVO searchVO) throws Exception;
	
	List<ConectrStatsVO> selectDate(ConectrStatsSearchVO searchVO) throws Exception;
	
	List<ConectrStatsVO> selectHour(ConectrStatsSearchVO searchVO) throws Exception;
	
	List<ConectrStatsVO> selectWeek(ConectrStatsSearchVO searchVO) throws Exception;

	int selectTCount(ConectrStatsSearchVO searchVO) throws Exception;

	List<ConectrStatsVO> selectBrowNm(ConectrStatsSearchVO searchVO) throws Exception;

}
