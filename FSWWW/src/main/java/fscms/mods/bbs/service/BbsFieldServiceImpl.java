package fscms.mods.bbs.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.bbs.vo.BbsFieldVO;

@Service("bbsFieldService")
public class BbsFieldServiceImpl extends EgovAbstractServiceImpl implements BbsFieldService {
	
	@Resource(name = "bbsFieldMapper")
	private BbsFieldMapper bbsFieldDAO;
	
	@Override
	public List<BbsFieldVO> selectList(BbsFieldVO vo) throws Exception {
		return bbsFieldDAO.selectList(vo);
	}

}
