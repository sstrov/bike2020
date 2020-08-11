package fscms.mods.bbs.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.bbs.vo.BbsThumbVO;

@Mapper("bbsThumbMapper")
public interface BbsThumbMapper {
	
	List<BbsThumbVO> selectList(BbsThumbVO vo) throws Exception;

}
