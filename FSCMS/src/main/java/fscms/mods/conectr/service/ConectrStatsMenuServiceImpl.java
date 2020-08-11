package fscms.mods.conectr.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.conectr.vo.ConectrStatsMenuSearchVO;
import fscms.mods.conectr.vo.ConectrStatsMenuVO;

@Service("conectrStatsMenuService")
public class ConectrStatsMenuServiceImpl extends EgovAbstractServiceImpl implements ConectrStatsMenuService {
	
	@Resource(name = "conectrStatsMenuMapper")
	private ConectrStatsMenuMapper conectrStatsMenuDAO;

	@Override
	public List<ConectrStatsMenuVO> selectList(ConectrStatsMenuSearchVO searchVO) throws Exception {
		return conectrStatsMenuDAO.selectList(searchVO);
	}

}
