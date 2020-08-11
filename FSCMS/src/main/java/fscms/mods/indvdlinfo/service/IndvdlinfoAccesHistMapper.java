package fscms.mods.indvdlinfo.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.indvdlinfo.vo.IndvdlinfoAccesHistSearchVO;
import fscms.mods.indvdlinfo.vo.IndvdlinfoAccesHistVO;

@Mapper("indvdlinfoAccesHistMapper")
public interface IndvdlinfoAccesHistMapper {
	
	IndvdlinfoAccesHistVO selectObj(IndvdlinfoAccesHistVO vo) throws Exception;
	
	int selectTCount(IndvdlinfoAccesHistSearchVO searchVO) throws Exception;
	
	List<IndvdlinfoAccesHistVO> selectPageList(IndvdlinfoAccesHistSearchVO searchVO) throws Exception;
	
	void insertInfo(IndvdlinfoAccesHistVO vo) throws Exception;

}
