package fscms.mods.auth.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.auth.vo.AuthUploadSearchVO;
import fscms.mods.auth.vo.AuthUploadVO;

@Mapper("authUploadMapper")
public interface AuthUploadMapper {

	int selectTCount(AuthUploadSearchVO searchVO) throws Exception;

	List<AuthUploadVO> selectPageList(AuthUploadSearchVO searchVO) throws Exception;

	AuthUploadVO selectObj(AuthUploadVO vo) throws Exception;

	void insertExcelInfo(AuthUploadVO vo) throws Exception;

	List<AuthUploadVO> selectNoteList(AuthUploadSearchVO searchVO) throws Exception;

	void updateAuth(AuthUploadVO vo) throws Exception;

	List<AuthUploadVO> getObj(AuthUploadVO vo) throws Exception;

	int selectTCountWeb(AuthUploadSearchVO searchVO) throws Exception;

	List<AuthUploadVO> selectPageListWeb(AuthUploadSearchVO searchVO) throws Exception;

}
