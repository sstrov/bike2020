package fscms.mods.bbs.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.bbs.vo.BbsThumbVO;

@Service("bbsThumbService")
public class BbsThumbServiceImpl extends EgovAbstractServiceImpl implements BbsThumbService {
	
	@Resource(name = "bbsThumbMapper")
	private BbsThumbMapper bbsThumbDAO;
	
	@Override
	public List<BbsThumbVO> selectList(BbsThumbVO vo) throws Exception {
		return bbsThumbDAO.selectList(vo);
	}

}
