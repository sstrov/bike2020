package fscms.mods.conectr.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.conectr.vo.ConectrStatsMenuVO;

@Service("conectrStatsMenuService")
public class ConectrStatsMenuServiceImpl extends EgovAbstractServiceImpl implements ConectrStatsMenuService {
	
	@Resource(name = "conectrStatsMenuMapper")
	private ConectrStatsMenuMapper conectrStatsMenuDAO;

	@Override
	public void insertInfo(ConectrStatsMenuVO vo) throws Exception {
		conectrStatsMenuDAO.insertInfo(vo);
	}

}
