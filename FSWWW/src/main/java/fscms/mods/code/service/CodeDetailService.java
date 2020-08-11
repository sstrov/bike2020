package fscms.mods.code.service;

import java.util.List;

import fscms.mods.code.vo.CodeDetailVO;

public interface CodeDetailService {
	
	CodeDetailVO selectObj(CodeDetailVO vo) throws Exception;
	
	List<CodeDetailVO> selectList(CodeDetailVO vo) throws Exception;
	
}
