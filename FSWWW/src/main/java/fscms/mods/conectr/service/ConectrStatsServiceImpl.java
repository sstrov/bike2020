package fscms.mods.conectr.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.conectr.vo.ConectrStatsVO;

@Service("conectrStatsService")
public class ConectrStatsServiceImpl extends EgovAbstractServiceImpl implements ConectrStatsService {
	
	@Resource(name = "conectrStatsMapper")
	private ConectrStatsMapper conectrStatsDAO;

	@Override
	public void insertInfo(ConectrStatsVO vo) throws Exception {
		conectrStatsDAO.insertInfo(vo);
	}

}
