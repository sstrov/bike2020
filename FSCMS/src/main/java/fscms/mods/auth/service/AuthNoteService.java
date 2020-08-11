package fscms.mods.auth.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.auth.vo.AuthNoteSearchVO;
import fscms.mods.auth.vo.AuthNoteVO;
import fscms.mods.auth.vo.AuthUploadVO;

public interface AuthNoteService {

	AuthNoteVO selectObj(AuthNoteVO vo) throws Exception;

	void deleteAuthNote(AuthNoteVO vo) throws Exception;

	List<AuthNoteVO> checkNoteSn(AuthNoteSearchVO searchVO) throws Exception;

	List<AuthNoteVO> selectNoteList(AuthNoteSearchVO searchVO) throws Exception;

	void insertNote(HttpServletRequest request, String bfData, AuthNoteVO vo, String mngrId) throws Exception;

	void updateAuthNote(HttpServletRequest request, String bfData, AuthNoteVO vo, String mngrId) throws Exception;

}
