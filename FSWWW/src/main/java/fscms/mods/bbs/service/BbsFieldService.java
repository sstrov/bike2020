package fscms.mods.bbs.service;

import java.util.List;

import fscms.mods.bbs.vo.BbsFieldVO;

public interface BbsFieldService {
	
	List<BbsFieldVO> selectList(BbsFieldVO vo) throws Exception;
	
}
