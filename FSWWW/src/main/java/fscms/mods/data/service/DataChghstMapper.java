package fscms.mods.data.service;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.data.vo.DataChghstVO;

@Mapper("dataChghstMapper")
public interface DataChghstMapper {
	
	DataChghstVO selectObj(DataChghstVO vo) throws Exception;
	
	void insertInfo(DataChghstVO vo) throws Exception;

}
