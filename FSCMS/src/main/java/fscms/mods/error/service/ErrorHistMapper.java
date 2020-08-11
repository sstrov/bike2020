package fscms.mods.error.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.error.vo.ErrorHistSearchVO;
import fscms.mods.error.vo.ErrorHistVO;

@Mapper("errorHistMapper")
public interface ErrorHistMapper {
	
	ErrorHistVO selectObj(ErrorHistVO vo) throws Exception;
	
	int selectTCount(ErrorHistSearchVO searchVO) throws Exception;
	
	List<ErrorHistVO> selectPageList(ErrorHistSearchVO searchVO) throws Exception;
	
	void insertInfo(ErrorHistVO vo) throws Exception;

}
