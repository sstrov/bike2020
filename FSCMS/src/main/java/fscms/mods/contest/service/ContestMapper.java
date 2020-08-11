package fscms.mods.contest.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.contest.vo.ContestSearchVO;
import fscms.mods.contest.vo.ContestVO;
import fscms.mods.mngr.vo.MngrLoginHistVO;

@Mapper("contestMapper")
public interface ContestMapper {

	int selectTCount(ContestSearchVO searchVO) throws Exception;

	List<ContestVO> selectPageList(ContestSearchVO searchVO) throws Exception;

	ContestVO selectObj(ContestVO contestVO) throws Exception;

	void insertInfo(ContestVO vo) throws Exception;

	void updateInfo(ContestVO vo) throws Exception;

	void deleteInfo(ContestVO vo) throws Exception;

	List<ContestVO> selectExcelList(ContestSearchVO searchVO) throws Exception;

}
