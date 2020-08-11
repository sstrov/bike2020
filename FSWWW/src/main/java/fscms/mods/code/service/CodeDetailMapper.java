package fscms.mods.code.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.code.vo.CodeDetailVO;

@Mapper("codeDetailMapper")
public interface CodeDetailMapper {
	
	CodeDetailVO selectObj(CodeDetailVO vo) throws Exception;
	
	List<CodeDetailVO> selectList(CodeDetailVO vo) throws Exception;
	
}
