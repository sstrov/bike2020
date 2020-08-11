package fscms.mods.auth.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.auth.vo.AuthNoteSearchVO;
import fscms.mods.auth.vo.AuthNoteVO;

@Mapper("authNoteMapper")
public interface AuthNoteMapper {

	AuthNoteVO selectObj(AuthNoteVO vo) throws Exception;

	void deleteAuthNote(AuthNoteVO vo) throws Exception;

	List<AuthNoteVO> checkNoteSn(AuthNoteSearchVO searchVO) throws Exception;

	List<AuthNoteVO> selectNoteList(AuthNoteSearchVO searchVO) throws Exception;

	void insertNote(AuthNoteVO vo) throws Exception;

	void updateAuthNote(AuthNoteVO vo) throws Exception;

}
