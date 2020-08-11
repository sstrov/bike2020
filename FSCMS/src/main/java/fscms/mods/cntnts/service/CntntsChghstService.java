package fscms.mods.cntnts.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.cntnts.vo.CntntsChghstSearchVO;
import fscms.mods.cntnts.vo.CntntsChghstVO;

public interface CntntsChghstService {
	
	CntntsChghstVO selectObj(CntntsChghstVO vo) throws Exception;
	
	int selectTCount(CntntsChghstSearchVO searchVO) throws Exception;
	
	List<CntntsChghstVO> selectPageList(CntntsChghstSearchVO searchVO) throws Exception;
	
	void insertInfo(HttpServletRequest request, CntntsChghstVO vo, String mngrId) throws Exception;

}
