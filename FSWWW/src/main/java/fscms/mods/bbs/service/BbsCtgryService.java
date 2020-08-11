package fscms.mods.bbs.service;

import java.util.List;

import fscms.mods.bbs.vo.BbsCtgryVO;

public interface BbsCtgryService {
	
	List<BbsCtgryVO> selectList(BbsCtgryVO vo) throws Exception;

}
