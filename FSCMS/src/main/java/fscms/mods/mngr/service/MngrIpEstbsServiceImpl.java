package fscms.mods.mngr.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.mngr.vo.MngrIpEstbsVO;
import net.sf.json.JSONArray;

@Service("mngrIpEstbsService")
public class MngrIpEstbsServiceImpl extends EgovAbstractServiceImpl implements MngrIpEstbsService {
	
	@Resource(name = "mngrIpEstbsMapper")
	private MngrIpEstbsMapper mngrIpEstbsDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public MngrIpEstbsVO selectObj(MngrIpEstbsVO vo) throws Exception {
		return mngrIpEstbsDAO.selectObj(vo);
	}

	@Override
	public List<MngrIpEstbsVO> selectList(MngrIpEstbsVO vo) throws Exception {
		return mngrIpEstbsDAO.selectList(vo);
	}

	@Override
	public void insertInfo(HttpServletRequest request, MngrIpEstbsVO vo, String mngrId) throws Exception {
		mngrIpEstbsDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_MNGR_IP_ESTBS", null, this.getData(vo.getIpEstbsSn()), null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, MngrIpEstbsVO vo, String mngrId)
			throws Exception {
		mngrIpEstbsDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_MNGR_IP_ESTBS", bfData, this.getData(vo.getIpEstbsSn()), null, mngrId);
		}
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, MngrIpEstbsVO vo, String mngrId)
			throws Exception {
		mngrIpEstbsDAO.deleteInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_MNGR_IP_ESTBS", bfData, null, null, mngrId);
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		MngrIpEstbsVO mngrIpEstbsVO = new MngrIpEstbsVO();
		mngrIpEstbsVO.setIpEstbsSn(key);
		MngrIpEstbsVO item = this.selectObj(mngrIpEstbsVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
