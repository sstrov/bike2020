package fscms.mods.error.service;

import java.util.List;

import fscms.mods.error.vo.ErrorHistSearchVO;
import fscms.mods.error.vo.ErrorHistVO;

public interface ErrorHistService {
	
	ErrorHistVO selectObj(ErrorHistVO vo) throws Exception;
	
	int selectTCount(ErrorHistSearchVO searchVO) throws Exception;
	
	List<ErrorHistVO> selectPageList(ErrorHistSearchVO searchVO) throws Exception;
	
	void insertInfo(ErrorHistVO vo) throws Exception;

}