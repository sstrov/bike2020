package fscms.mods.auth.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import fscms.mods.auth.vo.AuthUploadSearchVO;
import fscms.mods.auth.vo.AuthUploadVO;

public interface AuthUploadService {

	int selectTCount(AuthUploadSearchVO searchVO) throws Exception;

	List<AuthUploadVO> selectPageList(AuthUploadSearchVO searchVO) throws Exception;

	void insertExcelInfo(MultipartHttpServletRequest request, Object object, List<AuthUploadVO> itemList, String mngrId) throws Exception;

	AuthUploadVO selectObj(AuthUploadVO vo) throws Exception;

	List<AuthUploadVO> selectNoteList(AuthUploadSearchVO searchVO) throws Exception;

	List<AuthUploadVO> getObj(AuthUploadVO vo) throws Exception;

	void updateAuth(HttpServletRequest request, List<AuthUploadVO> bData, AuthUploadVO vo, String mngrId) throws Exception;

	int selectTCountWeb(AuthUploadSearchVO searchVO) throws Exception;

	List<AuthUploadVO> selectPageListWeb(AuthUploadSearchVO searchVO) throws Exception; 

}
