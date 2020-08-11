package fscms.mods.push.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.push.vo.PushSearchVO;
import fscms.mods.push.vo.PushVO;

@Mapper("pushMapper")
public interface PushMapper {

	int selectTCount(PushSearchVO searchVO) throws Exception;

	List<PushVO> selectPageList(PushSearchVO searchVO) throws Exception;

	PushVO selectObj(PushVO pushVO) throws Exception;

	void insertInfo(PushVO vo) throws Exception;

	void updateInfo(PushVO vo) throws Exception;

	void deleteInfo(PushVO vo) throws Exception;

	int selectMaxPushSn(PushVO pushVO) throws Exception;

}
