package fscms.mods.contest.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import fscms.mods.contest.vo.ContestEstbsSearchVO;
import fscms.mods.contest.vo.ContestEstbsVO;
import fscms.mods.mngr.vo.MngrLoginHistVO;

public interface ContestEstbsService {

	int selectTCount(ContestEstbsSearchVO searchVO) throws Exception;

	List<ContestEstbsVO> selectPageList(ContestEstbsSearchVO searchVO) throws Exception;

	ContestEstbsVO selectObj(ContestEstbsVO contestEstbsVO) throws Exception;

	void insertInfo(MultipartHttpServletRequest request, ContestEstbsVO vo, String mngrId) throws Exception;

	void updateInfo(MultipartHttpServletRequest request, String bfData, ContestEstbsVO vo, String mngrId) throws Exception;

	void deleteInfo(HttpServletRequest request, String bfData, ContestEstbsVO vo, String mngrId) throws Exception;

}
