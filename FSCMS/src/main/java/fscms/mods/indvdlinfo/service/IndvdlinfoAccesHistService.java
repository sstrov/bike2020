package fscms.mods.indvdlinfo.service;

import java.util.List;

import fscms.mods.indvdlinfo.vo.IndvdlinfoAccesHistSearchVO;
import fscms.mods.indvdlinfo.vo.IndvdlinfoAccesHistVO;

public interface IndvdlinfoAccesHistService {
	
	IndvdlinfoAccesHistVO selectObj(IndvdlinfoAccesHistVO vo) throws Exception;
	
	int selectTCount(IndvdlinfoAccesHistSearchVO searchVO) throws Exception;
	
	List<IndvdlinfoAccesHistVO> selectPageList(IndvdlinfoAccesHistSearchVO searchVO) throws Exception;
	
	void insertInfo(IndvdlinfoAccesHistVO vo) throws Exception;

}
