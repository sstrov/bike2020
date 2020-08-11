package fscms.mods.mngr.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.mngr.vo.MngrMenuVO;

@Mapper("mngrMenuMapper")
public interface MngrMenuMapper {
	
	MngrMenuVO selectObj(MngrMenuVO vo) throws Exception;
	
	List<MngrMenuVO> selectList(MngrMenuVO vo) throws Exception;
	
	List<MngrMenuVO> selectList_C(MngrMenuVO vo) throws Exception;
	
	List<MngrMenuVO> selectList_acc(MngrMenuVO vo) throws Exception;
	
	int selectMaxO(MngrMenuVO vo) throws Exception;
	
	void insertInfo(MngrMenuVO vo) throws Exception;
	
	void updateInfo(MngrMenuVO vo) throws Exception;
	
	void deleteInfo(MngrMenuVO vo) throws Exception;

}
