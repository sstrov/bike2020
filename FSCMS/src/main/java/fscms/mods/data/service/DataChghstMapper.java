package fscms.mods.data.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.data.vo.DataChghstSearchVO;
import fscms.mods.data.vo.DataChghstVO;

@Mapper("dataChghstMapper")
public interface DataChghstMapper {
	
	DataChghstVO selectObj(DataChghstVO vo) throws Exception;
	
	int selectTCount(DataChghstSearchVO searchVO) throws Exception;
	
	List<DataChghstVO> selectPageList(DataChghstSearchVO searchVO) throws Exception;
	
	void insertInfo(DataChghstVO vo) throws Exception;

}
