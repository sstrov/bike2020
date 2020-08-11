package fscms.mods.road.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.road.vo.RoadVO;

@Mapper("roadMapper")
public interface RoadMapper {

	List<RoadVO> selectRoadAllList(RoadVO roadVO) throws Exception;

	List<RoadVO> selectRoadList(RoadVO roadVO) throws Exception;

	List<RoadVO> selectAuthList(RoadVO roadVO) throws Exception;

	List<RoadVO> selectAuthConfirmList(RoadVO vo) throws Exception;

	void insertAuthConfirm(RoadVO vo) throws Exception;

	RoadVO selectObj(RoadVO vo) throws Exception;

	void deleteAuthConfirm(RoadVO vo) throws Exception;

	List<RoadVO> selectConfirmList(RoadVO searchVO) throws Exception;

	RoadVO selectAuthObj(RoadVO vo) throws Exception;

}
