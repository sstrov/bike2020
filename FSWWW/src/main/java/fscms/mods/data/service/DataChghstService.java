package fscms.mods.data.service;

import fscms.mods.data.vo.DataChghstVO;

public interface DataChghstService {
	
	DataChghstVO selectObj(DataChghstVO vo) throws Exception;
	
	void insertInfo(DataChghstVO vo) throws Exception;

}
