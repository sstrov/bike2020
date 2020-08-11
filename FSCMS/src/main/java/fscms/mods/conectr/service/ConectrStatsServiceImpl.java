package fscms.mods.conectr.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.conectr.vo.ConectrStatsSearchVO;
import fscms.mods.conectr.vo.ConectrStatsVO;

@Service("conectrStatsService")
public class ConectrStatsServiceImpl extends EgovAbstractServiceImpl implements ConectrStatsService {
	
	@Resource(name = "conectrStatsMapper")
	private ConectrStatsMapper conectrStatsDAO;

	@Override
	public List<ConectrStatsVO> selectYear(ConectrStatsSearchVO searchVO) throws Exception {
		return conectrStatsDAO.selectYear(searchVO);
	}

	@Override
	public List<ConectrStatsVO> selectMonth(ConectrStatsSearchVO searchVO) throws Exception {
		return conectrStatsDAO.selectMonth(searchVO);
	}

	@Override
	public List<ConectrStatsVO> selectDate(ConectrStatsSearchVO searchVO) throws Exception {
		return conectrStatsDAO.selectDate(searchVO);
	}

	@Override
	public List<ConectrStatsVO> selectHour(ConectrStatsSearchVO searchVO) throws Exception {
		return conectrStatsDAO.selectHour(searchVO);
	}

	@Override
	public List<ConectrStatsVO> selectWeek(ConectrStatsSearchVO searchVO) throws Exception {
		return conectrStatsDAO.selectWeek(searchVO);
	}

	@Override
	public int selectTCount(ConectrStatsSearchVO searchVO) throws Exception {
		return conectrStatsDAO.selectTCount(searchVO);
	}

	@Override
	public List<ConectrStatsVO> selectBrowNm(ConectrStatsSearchVO searchVO) throws Exception {
		return conectrStatsDAO.selectBrowNm(searchVO);
	}

}
