package fscms.mods.bbs.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.bbs.vo.BbsEstbsVO;

@Service("bbsEstbsService")
public class BbsEstbsServiceImpl extends EgovAbstractServiceImpl implements BbsEstbsService {
	
	@Resource(name = "bbsEstbsMapper")
	private BbsEstbsMapper bbsEstbsDAO;
	
	@Override
	public BbsEstbsVO selectObj(BbsEstbsVO vo) throws Exception {
		return bbsEstbsDAO.selectObj(vo);
	}

}
