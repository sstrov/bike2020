package fscms.mods.mngr.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.mngr.vo.MngrMenuAccVO;

@Mapper("mngrMenuAccMapper")
public interface MngrMenuAccMapper {
	
	MngrMenuAccVO selectObj(MngrMenuAccVO vo) throws Exception;
	
	List<MngrMenuAccVO> selectList(MngrMenuAccVO vo) throws Exception;
	
	void insertInfo(MngrMenuAccVO vo) throws Exception;
	
	void updateInfo(MngrMenuAccVO vo) throws Exception;
	
	void deleteInfo(MngrMenuAccVO vo) throws Exception;

}
