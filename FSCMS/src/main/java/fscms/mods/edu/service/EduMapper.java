package fscms.mods.edu.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.edu.vo.EduSearchVO;
import fscms.mods.edu.vo.EduVO;

@Mapper("eduMapper")
public interface EduMapper {

	int selectTCount(EduSearchVO searchVO) throws Exception;

	List<EduVO> selectPageList(EduSearchVO searchVO) throws Exception;

	EduVO selectObj(EduVO vo) throws Exception;

	void insertInfo(EduVO vo) throws Exception;

	void updateInfo(EduVO vo) throws Exception;

	void deleteInfo(EduVO item) throws Exception;

}
