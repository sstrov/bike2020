package fscms.mods.bbs.service;

import java.util.List;

import fscms.mods.bbs.vo.BbsThumbVO;

public interface BbsThumbService {
	
	List<BbsThumbVO> selectList(BbsThumbVO vo) throws Exception;

}
