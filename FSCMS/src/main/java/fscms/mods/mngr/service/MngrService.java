package fscms.mods.mngr.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.mngr.vo.MngrSearchVO;
import fscms.mods.mngr.vo.MngrVO;

public interface MngrService {
	
	MngrVO selectObj(MngrVO vo) throws Exception;
	
	int selectTCount(MngrSearchVO searchVO) throws Exception;
	
	List<MngrVO> selectPageList(MngrSearchVO searchVO) throws Exception;
	
	void insertInfo(HttpServletRequest request, MngrVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, MngrVO vo, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, String bfData, MngrVO vo, String mngrId) throws Exception;

}
