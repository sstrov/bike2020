package fscms.mods.road.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.road.vo.RoadVO;

public interface RoadService {

	List<RoadVO> selectRoadAllList(RoadVO roadVO) throws Exception;

	List<RoadVO> selectRoadList(RoadVO roadVO) throws Exception;

	List<RoadVO> selectAuthList(RoadVO roadVO) throws Exception;

	List<RoadVO> selectAuthConfirmList(RoadVO vo) throws Exception;

	void insertAuthConfirm(HttpServletRequest request, RoadVO vo, String mngrId) throws Exception;

	RoadVO selectObj(RoadVO vo) throws Exception;

	void deleteAuthConfirm(HttpServletRequest request, String bfData, RoadVO vo, String mngrId) throws Exception;

	List<RoadVO> selectConfirmList(RoadVO searchVO) throws Exception;

	RoadVO selectAuthObj(RoadVO vo) throws Exception;

}
