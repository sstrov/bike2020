package fscms.mods.mngr.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.mngr.vo.MngrMenuAccVO;

public interface MngrMenuAccService {
	
	MngrMenuAccVO selectObj(MngrMenuAccVO vo) throws Exception;
	
	List<MngrMenuAccVO> selectList(MngrMenuAccVO vo) throws Exception;
	
	void insertInfo(HttpServletRequest request, MngrMenuAccVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, MngrMenuAccVO vo, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, String bfData, MngrMenuAccVO vo, String mngrId) throws Exception;

}
