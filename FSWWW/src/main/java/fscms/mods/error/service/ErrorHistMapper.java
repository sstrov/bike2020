package fscms.mods.error.service;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.error.vo.ErrorHistVO;

@Mapper("errorHistMapper")
public interface ErrorHistMapper {
	
	ErrorHistVO selectObj(ErrorHistVO vo) throws Exception;
	
	void insertInfo(ErrorHistVO vo) throws Exception;

}
