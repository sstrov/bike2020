package fscms.mods.bbs.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.bbs.vo.BbsRoleVO;

@Mapper("bbsRoleMapper")
public interface BbsRoleMapper {
	
	BbsRoleVO selectObj(BbsRoleVO vo) throws Exception;
	
	List<BbsRoleVO> selectList(BbsRoleVO vo) throws Exception;

}
