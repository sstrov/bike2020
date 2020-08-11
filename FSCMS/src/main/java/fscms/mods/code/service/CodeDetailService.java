package fscms.mods.code.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.code.vo.CodeDetailVO;

public interface CodeDetailService {
	
	CodeDetailVO selectObj(CodeDetailVO vo) throws Exception;
	
	List<CodeDetailVO> selectList(CodeDetailVO vo) throws Exception;
	
	void insertInfo(HttpServletRequest request, CodeDetailVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, CodeDetailVO vo, String mngrId) throws Exception;
	
}
