package fscms.mods.conectr.service;

import java.util.List;

import fscms.mods.conectr.vo.ConectrStatsSearchVO;
import fscms.mods.conectr.vo.ConectrStatsVO;

public interface ConectrStatsService {
	
	List<ConectrStatsVO> selectYear(ConectrStatsSearchVO searchVO) throws Exception;
	
	List<ConectrStatsVO> selectMonth(ConectrStatsSearchVO searchVO) throws Exception;
	
	List<ConectrStatsVO> selectDate(ConectrStatsSearchVO searchVO) throws Exception;
	
	List<ConectrStatsVO> selectHour(ConectrStatsSearchVO searchVO) throws Exception;
	
	List<ConectrStatsVO> selectWeek(ConectrStatsSearchVO searchVO) throws Exception;
	
	int selectTCount(ConectrStatsSearchVO searchVO) throws Exception;
	
	List<ConectrStatsVO> selectBrowNm(ConectrStatsSearchVO searchVO) throws Exception;

}