package fscms.mods.code.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.code.vo.CodeClSearchVO;
import fscms.mods.code.vo.CodeClVO;

@Mapper("codeClMapper")
public interface CodeClMapper {
	
	CodeClVO selectObj(CodeClVO vo) throws Exception;
	
	int selectTCount(CodeClSearchVO searchVO) throws Exception;
	
	List<CodeClVO> selectPageList(CodeClSearchVO searchVO) throws Exception;
	
	void insertInfo(CodeClVO vo) throws Exception;
	
	void updateInfo(CodeClVO vo) throws Exception;

}
