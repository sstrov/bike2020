package fscms.mods.mngr.service;

import java.util.List;

import fscms.mods.mngr.vo.MngrLoginHistSearchVO;
import fscms.mods.mngr.vo.MngrLoginHistVO;

public interface MngrLoginHistService {
	
	MngrLoginHistVO selectObj(MngrLoginHistVO vo) throws Exception;
	
	int selectTCount(MngrLoginHistSearchVO searchVO) throws Exception;
	
	List<MngrLoginHistVO> selectList(MngrLoginHistSearchVO searchVO) throws Exception;
	
	List<MngrLoginHistVO> selectPageList(MngrLoginHistSearchVO searchVO) throws Exception;
	
	void updateEDate(MngrLoginHistVO vo) throws Exception;

}
