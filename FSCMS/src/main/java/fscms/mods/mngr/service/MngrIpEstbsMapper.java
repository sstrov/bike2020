package fscms.mods.mngr.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.mngr.vo.MngrIpEstbsVO;

@Mapper("mngrIpEstbsMapper")
public interface MngrIpEstbsMapper {
	
	MngrIpEstbsVO selectObj(MngrIpEstbsVO vo) throws Exception;
	
	List<MngrIpEstbsVO> selectList(MngrIpEstbsVO vo) throws Exception;
	
	void insertInfo(MngrIpEstbsVO vo) throws Exception;
	
	void updateInfo(MngrIpEstbsVO vo) throws Exception;
	
	void deleteInfo(MngrIpEstbsVO vo) throws Exception;

}
