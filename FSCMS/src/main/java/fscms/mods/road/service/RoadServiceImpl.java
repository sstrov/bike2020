package fscms.mods.road.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.road.vo.RoadVO;
import net.sf.json.JSONArray;

@Service("roadService")
public class RoadServiceImpl extends EgovAbstractServiceImpl implements RoadService {

	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;
	
	@Resource(name = "roadMapper")
	private RoadMapper roadDAO;

	@Override
	public List<RoadVO> selectRoadAllList(RoadVO roadVO) throws Exception {
		return roadDAO.selectRoadAllList(roadVO);
	}

	@Override
	public List<RoadVO> selectRoadList(RoadVO roadVO) throws Exception {
		return roadDAO.selectRoadList(roadVO);
	}

	@Override
	public List<RoadVO> selectAuthList(RoadVO roadVO) throws Exception {
		return roadDAO.selectAuthList(roadVO);
	}

	@Override
	public List<RoadVO> selectAuthConfirmList(RoadVO vo) throws Exception {
		return roadDAO.selectAuthConfirmList(vo);
	}

	@Override
	public void insertAuthConfirm(HttpServletRequest request, RoadVO vo, String mngrId) throws Exception {
		roadDAO.insertAuthConfirm(vo);
		
		RoadVO roadVO = new RoadVO();
		roadVO.setUserSn(vo.getUserSn());
		roadVO.setNoteSn(vo.getNoteSn());
		roadVO.setAuthCd(vo.getAuthCd());
		roadVO.setRoadCd(vo.getRoadCd());
		String aftData = JSONArray.fromObject(this.selectObj(vo)).toString();
		
		dataChghstHelper.setInsert(request, "FS_AUTH_WEB", null, aftData, null, mngrId);
	}

	@Override
	public RoadVO selectObj(RoadVO vo) throws Exception {
		return roadDAO.selectObj(vo);
	}

	@Override
	public List<RoadVO> selectConfirmList(RoadVO searchVO) throws Exception {
		return roadDAO.selectConfirmList(searchVO);
	}
	
	@Override
	public void deleteAuthConfirm(HttpServletRequest request, String bfData, RoadVO vo, String mngrId) throws Exception{
		roadDAO.deleteAuthConfirm(vo);
		
		dataChghstHelper.setInsert(request, "FS_AUTH_WEB", bfData, null, null, mngrId);
	}

	@Override
	public RoadVO selectAuthObj(RoadVO vo) throws Exception {
		return roadDAO.selectAuthObj(vo);
	}

}
