package fscms.mods.mngr.service;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.mngr.vo.MngrEstbsVO;

public interface MngrEstbsService {
	
	MngrEstbsVO selectObj() throws Exception;
	
	void insertInfo(HttpServletRequest request, MngrEstbsVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, MngrEstbsVO vo, String mngrId) throws Exception;

}
