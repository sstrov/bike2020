package fscms.mods.error.service;

import fscms.mods.error.vo.ErrorHistVO;

public interface ErrorHistService {
	
	ErrorHistVO selectObj(ErrorHistVO vo) throws Exception;
	
	void insertInfo(ErrorHistVO vo) throws Exception;

}