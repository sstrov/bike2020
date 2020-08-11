package fscms.mods.contest.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.banner.vo.BannerEstbsVO;
import fscms.mods.contest.vo.ContestEstbsSearchVO;
import fscms.mods.contest.vo.ContestEstbsVO;
import fscms.mods.contest.vo.ContestSearchVO;
import fscms.mods.mngr.vo.MngrLoginHistVO;

@Mapper("contestEstbsMapper")
public interface ContestEstbsMapper {

	int selectTCount(ContestEstbsSearchVO searchVO) throws Exception;

	List<ContestEstbsVO> selectPageList(ContestEstbsSearchVO searchVO) throws Exception;

	ContestEstbsVO selectObj(ContestEstbsVO contestEstbsVO) throws Exception;

	void insertInfo(ContestEstbsVO vo) throws Exception;

	void updateInfo(ContestEstbsVO vo) throws Exception;

	void deleteInfo(ContestEstbsVO vo) throws Exception;

}
