package fscms.mods.mngr.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.mngr.vo.MngrMenuAccVO;
import net.sf.json.JSONArray;

@Service("mngrMenuAccService")
public class MngrMenuAccServiceImpl extends EgovAbstractServiceImpl implements MngrMenuAccService {
	
	@Resource(name = "mngrMenuAccMapper")
	private MngrMenuAccMapper mngrMenuAccDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public MngrMenuAccVO selectObj(MngrMenuAccVO vo) throws Exception {
		return mngrMenuAccDAO.selectObj(vo);
	}

	@Override
	public List<MngrMenuAccVO> selectList(MngrMenuAccVO vo) throws Exception {
		return mngrMenuAccDAO.selectList(vo);
	}

	@Override
	public void insertInfo(HttpServletRequest request, MngrMenuAccVO vo, String mngrId) throws Exception {
		if(StringUtils.isEmpty(vo.getAuthorRedng())) {
			vo.setAuthorRedng("N");
		}
		if(StringUtils.isEmpty(vo.getAuthorRegist())) {
			vo.setAuthorRegist("N");
		}
		mngrMenuAccDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_MNGR_MENU_ACC", null, this.getData(vo.getMenuAccSn()), null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, MngrMenuAccVO vo, String mngrId) throws Exception {
		if(StringUtils.isEmpty(vo.getAuthorRedng())) {
			vo.setAuthorRedng("N");
		}
		if(StringUtils.isEmpty(vo.getAuthorRegist())) {
			vo.setAuthorRegist("N");
		}
		mngrMenuAccDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_MNGR_MENU_ACC", bfData, this.getData(vo.getMenuAccSn()), null, mngrId);
		}
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, MngrMenuAccVO vo, String mngrId) throws Exception {
		mngrMenuAccDAO.deleteInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_MNGR_MENU_ACC", bfData, null, null, mngrId);
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		MngrMenuAccVO mngrMenuAccVO = new MngrMenuAccVO();
		mngrMenuAccVO.setMenuAccSn(key);
		MngrMenuAccVO item = this.selectObj(mngrMenuAccVO);
		
		if(item != null) {
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
