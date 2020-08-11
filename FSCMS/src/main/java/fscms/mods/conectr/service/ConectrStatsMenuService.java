package fscms.mods.conectr.service;

import java.util.List;

import fscms.mods.conectr.vo.ConectrStatsMenuSearchVO;
import fscms.mods.conectr.vo.ConectrStatsMenuVO;

public interface ConectrStatsMenuService {
	
	List<ConectrStatsMenuVO> selectList(ConectrStatsMenuSearchVO searchVO) throws Exception;

}
