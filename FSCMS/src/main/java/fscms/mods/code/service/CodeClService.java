package fscms.mods.code.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.code.vo.CodeClSearchVO;
import fscms.mods.code.vo.CodeClVO;

public interface CodeClService {
	
	CodeClVO selectObj(CodeClVO vo) throws Exception;
	
	int selectTCount(CodeClSearchVO searchVO) throws Exception;
	
	List<CodeClVO> selectPageList(CodeClSearchVO searchVO) throws Exception;
	
	void insertInfo(HttpServletRequest request, CodeClVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, CodeClVO vo, String mngrId) throws Exception;

}
