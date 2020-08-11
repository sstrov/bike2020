package fscms.mods.mngr.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.mngr.vo.MngrSearchVO;
import fscms.mods.mngr.vo.MngrVO;

@Mapper("mngrMapper")
public interface MngrMapper {
	
	MngrVO selectObj(MngrVO vo) throws Exception;
	
	int selectTCount(MngrSearchVO searchVO) throws Exception;
	
	List<MngrVO> selectPageList(MngrSearchVO searchVO) throws Exception;
	
	void insertInfo(MngrVO vo) throws Exception;
	
	void updateInfo(MngrVO vo) throws Exception;
	
	void deleteInfo(MngrVO vo) throws Exception;

}
