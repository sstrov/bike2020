package fscms.mods.bbs.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.bbs.vo.BbsCtgryVO;

@Service("bbsCtgryService")
public class BbsCtgryServiceImpl extends EgovAbstractServiceImpl implements BbsCtgryService {
	
	@Resource(name = "bbsCtgryMapper")
	private BbsCtgryMapper bbsCtgryDAO;
	
	@Override
	public List<BbsCtgryVO> selectList(BbsCtgryVO vo) throws Exception {
		return bbsCtgryDAO.selectList(vo);
	}

}
