package fscms.mods.mngr.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.mngr.vo.MngrIpEstbsVO;

public interface MngrIpEstbsService {
	
	MngrIpEstbsVO selectObj(MngrIpEstbsVO vo) throws Exception;
	
	List<MngrIpEstbsVO> selectList(MngrIpEstbsVO vo) throws Exception;
	
	void insertInfo(HttpServletRequest request, MngrIpEstbsVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, MngrIpEstbsVO vo, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, String bfData, MngrIpEstbsVO vo, String mngrId) throws Exception;

}
