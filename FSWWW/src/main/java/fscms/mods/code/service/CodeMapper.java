package fscms.mods.code.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.code.vo.CodeVO;

@Mapper("codeMapper")
public interface CodeMapper {
	
	List<CodeVO> selectList(CodeVO vo) throws Exception;

}
