package fscms.mods.code.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.code.vo.CodeSearchVO;
import fscms.mods.code.vo.CodeVO;

public interface CodeService {
	
	CodeVO selectObj(CodeVO vo) throws Exception;
	
	List<CodeVO> selectList(CodeVO vo) throws Exception;
	
	int selectTCount(CodeSearchVO searchVO) throws Exception;
	
	List<CodeVO> selectPageList(CodeSearchVO searchVO) throws Exception;
	
	void insertInfo(HttpServletRequest request, CodeVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, CodeVO vo, String mngrId, boolean detailAt) throws Exception;

}