package fscms.mods.code.service;

import java.util.List;

import fscms.mods.code.vo.CodeVO;

public interface CodeService {
	
	List<CodeVO> selectList(CodeVO vo) throws Exception;

}