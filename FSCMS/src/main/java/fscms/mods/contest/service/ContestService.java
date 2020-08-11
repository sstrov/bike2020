package fscms.mods.contest.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import fscms.mods.contest.vo.ContestEstbsSearchVO;
import fscms.mods.contest.vo.ContestSearchVO;
import fscms.mods.contest.vo.ContestVO;
import fscms.mods.mngr.vo.MngrLoginHistVO;

public interface ContestService {

	int selectTCount(ContestSearchVO searchVO) throws Exception;

	List<ContestVO> selectPageList(ContestSearchVO searchVO) throws Exception;

	ContestVO selectObj(ContestVO contestVO) throws Exception;

	void insertInfo(MultipartHttpServletRequest request, ContestVO vo, String mngrId) throws Exception;

	void updateInfo(MultipartHttpServletRequest request, String bfData, ContestVO vo, String mngrId) throws Exception;

	void deleteInfo(HttpServletRequest request, String bfData, ContestVO vo, String mngrId) throws Exception;

	List<ContestVO> selectExcelList(ContestSearchVO searchVO) throws Exception;


}
