package fscms.mods.bbs.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.bbs.vo.BbsCtgrySubVO;;

@Mapper("bbsCtgrySubMapper")
public interface BbsCtgrySubMapper {
	
	BbsCtgrySubVO selectObj(BbsCtgrySubVO vo) throws Exception;
	
	List<BbsCtgrySubVO> selectList(BbsCtgrySubVO vo) throws Exception;
	
	void insertInfo(BbsCtgrySubVO vo) throws Exception;
	
	void updateInfo(BbsCtgrySubVO vo) throws Exception;
	
	void deleteInfo(BbsCtgrySubVO vo) throws Exception;

}
