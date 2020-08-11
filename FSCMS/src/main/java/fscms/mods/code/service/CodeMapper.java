package fscms.mods.code.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.code.vo.CodeSearchVO;
import fscms.mods.code.vo.CodeVO;

@Mapper("codeMapper")
public interface CodeMapper {
	
	CodeVO selectObj(CodeVO vo) throws Exception;
	
	List<CodeVO> selectList(CodeVO vo) throws Exception;
	
	int selectTCount(CodeSearchVO searchVO) throws Exception;
	
	List<CodeVO> selectPageList(CodeSearchVO searchVO) throws Exception;
	
	void insertInfo(CodeVO vo) throws Exception;
	
	void updateInfo(CodeVO vo) throws Exception;

}
