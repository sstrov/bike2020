package fscms.mods.cntnts.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.cntnts.vo.CntntsChghstSearchVO;
import fscms.mods.cntnts.vo.CntntsChghstVO;

@Mapper("cntntsMapper")
public interface CntntsChghstMapper {
	
	CntntsChghstVO selectObj(CntntsChghstVO vo) throws Exception;
	
	int selectTCount(CntntsChghstSearchVO searchVO) throws Exception;
	
	List<CntntsChghstVO> selectPageList(CntntsChghstSearchVO searchVO) throws Exception;
	
	void insertInfo(CntntsChghstVO vo) throws Exception;

}
