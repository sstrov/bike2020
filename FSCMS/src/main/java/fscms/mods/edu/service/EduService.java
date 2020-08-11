package fscms.mods.edu.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.edu.vo.EduSearchVO;
import fscms.mods.edu.vo.EduVO;

public interface EduService {

	int selectTCount(EduSearchVO searchVO) throws Exception;

	List<EduVO> selectPageList(EduSearchVO searchVO) throws Exception;

	EduVO selectObj(EduVO eduVO) throws Exception;

	void insertInfo(HttpServletRequest request, EduVO vo, String mngrId) throws Exception;

	void updateInfo(HttpServletRequest request, String bfData, EduVO vo, String mngrId) throws Exception;

	void deleteInfo(HttpServletRequest request, String bfData, EduVO item, String mngrId) throws Exception;

}
