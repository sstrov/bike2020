package fscms.mods.mngr.service;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.mngr.vo.MngrEstbsVO;

@Mapper("mngrEstbsMapper")
public interface MngrEstbsMapper {
	
	MngrEstbsVO selectObj() throws Exception;
	
	void insertInfo(MngrEstbsVO vo) throws Exception;
	
	void updateInfo(MngrEstbsVO vo) throws Exception;

}
