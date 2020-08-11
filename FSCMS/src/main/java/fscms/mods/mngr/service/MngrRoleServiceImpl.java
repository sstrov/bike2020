package fscms.mods.mngr.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.mngr.util.MngrMenuHelper;
import fscms.mods.mngr.vo.MngrMenuAccVO;
import fscms.mods.mngr.vo.MngrRoleSearchVO;
import fscms.mods.mngr.vo.MngrRoleVO;
import net.sf.json.JSONArray;

@Service("mngrRoleService")
public class MngrRoleServiceImpl extends EgovAbstractServiceImpl implements MngrRoleService {
	
	@Resource(name = "mngrRoleMapper")
	private MngrRoleMapper mngrRoleDAO;
	
	@Resource(name = "mngrMenuHelper")
	private MngrMenuHelper mngrMenuHelper;
	
	@Resource(name = "mngrMenuAccService")
	private MngrMenuAccService mngrMenuAccService;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public MngrRoleVO selectObj(MngrRoleVO vo) throws Exception {
		return mngrRoleDAO.selectObj(vo);
	}

	@Override
	public List<MngrRoleVO> selectList(MngrRoleVO vo) throws Exception {
		return mngrRoleDAO.selectList(vo);
	}

	@Override
	public int selectTCount(MngrRoleSearchVO searchVO) throws Exception {
		return mngrRoleDAO.selectTCount(searchVO);
	}

	@Override
	public List<MngrRoleVO> selectPageList(MngrRoleSearchVO searchVO) throws Exception {
		return mngrRoleDAO.selectPageList(searchVO);
	}

	@Override
	public int selectMaxO(MngrRoleVO vo) throws Exception {
		return mngrRoleDAO.selectMaxO(vo);
	}

	@Override
	public void insertInfo(HttpServletRequest request, MngrRoleVO vo, String mngrId) throws Exception {
		mngrRoleDAO.insertInfo(vo);
		
		// 권한 설정
		mngrMenuHelper.setAcc(request, mngrId, vo.getRoleSn());
		mngrMenuHelper.setMenu();
		
		dataChghstHelper.setInsert(request, "FS_MNGR_MENU", null, this.getData(vo.getRoleSn()), null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, MngrRoleVO vo, String mngrId)
			throws Exception {
		mngrRoleDAO.updateInfo(vo);
		
		// 권한 설정
		mngrMenuHelper.setAcc(request, mngrId, vo.getRoleSn());
		mngrMenuHelper.setMenu();
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_MNGR_MENU", bfData, this.getData(vo.getRoleSn()), null, mngrId);
		}
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, MngrRoleVO vo, String mngrId) throws Exception {
		// 관리자_메뉴_연결 정보 삭제
		MngrMenuAccVO mngrMenuAccVO = new MngrMenuAccVO();
		mngrMenuAccVO.setRoleSn(vo.getRoleSn());
		List<MngrMenuAccVO> accList = mngrMenuAccService.selectList(mngrMenuAccVO);
		
		if(accList != null && accList.size() > 0) {
			for (MngrMenuAccVO item : accList) {
				String accBfData = JSONArray.fromObject(item).toString();
				mngrMenuAccService.deleteInfo(request, accBfData, item, mngrId);
			}
		}
		
		mngrRoleDAO.deleteInfo(vo);
		
		// 권한 설정
		mngrMenuHelper.setAcc(request, mngrId, vo.getRoleSn());
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_MNGR_MENU", bfData, null, null, mngrId);
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		MngrRoleVO mngrRoleVO = new MngrRoleVO();
		mngrRoleVO.setRoleSn(key);
		MngrRoleVO item = this.selectObj(mngrRoleVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
