package fscms.mods.user.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.user.util.UserMenuHelper;
import fscms.mods.user.vo.UserMenuAccVO;
import fscms.mods.user.vo.UserRoleSearchVO;
import fscms.mods.user.vo.UserRoleVO;
import net.sf.json.JSONArray;

@Service("userRoleService")
public class UserRoleServiceImpl extends EgovAbstractServiceImpl implements UserRoleService {
	
	@Resource(name = "userRoleMapper")
	private UserRoleMapper userRoleDAO;
	
	@Resource(name = "userMenuHelper")
	private UserMenuHelper userMenuHelper;
	
	@Resource(name = "userMenuAccService")
	private UserMenuAccService userMenuAccService;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public UserRoleVO selectObj(UserRoleVO vo) throws Exception {
		return userRoleDAO.selectObj(vo);
	}

	@Override
	public List<UserRoleVO> selectList(UserRoleVO vo) throws Exception {
		return userRoleDAO.selectList(vo);
	}

	@Override
	public int selectTCount(UserRoleSearchVO searchVO) throws Exception {
		return userRoleDAO.selectTCount(searchVO);
	}

	@Override
	public List<UserRoleVO> selectPageList(UserRoleSearchVO searchVO) throws Exception {
		return userRoleDAO.selectPageList(searchVO);
	}

	@Override
	public int selectMaxO(UserRoleVO vo) throws Exception {
		return userRoleDAO.selectMaxO(vo);
	}

	@Override
	public void insertInfo(HttpServletRequest request, UserRoleVO vo, String mngrId) throws Exception {
		userRoleDAO.insertInfo(vo);
		
		// 권한 설정
		userMenuHelper.setAcc(request, mngrId, vo.getRoleSn());
		
		dataChghstHelper.setInsert(request, "FS_USER_MENU", null, this.getData(vo.getRoleSn()), null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, UserRoleVO vo, String mngrId)
			throws Exception {
		userRoleDAO.updateInfo(vo);
		
		// 권한 설정
		userMenuHelper.setAcc(request, mngrId, vo.getRoleSn());
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_USER_MENU", bfData, this.getData(vo.getRoleSn()), null, mngrId);
		}
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, UserRoleVO vo, String mngrId)
			throws Exception {
		// 사용자_메뉴_연결 정보 삭제
		UserMenuAccVO userMenuAccVO = new UserMenuAccVO();
		userMenuAccVO.setRoleSn(vo.getRoleSn());
		List<UserMenuAccVO> accList = userMenuAccService.selectList(userMenuAccVO);
		
		if(accList != null && accList.size() > 0) {
			for (UserMenuAccVO item : accList) {
				String accBfData = JSONArray.fromObject(item).toString();
				userMenuAccService.deleteInfo(request, accBfData, item, mngrId);
			}
		}
		
		userRoleDAO.deleteInfo(vo);
		
		// 권한 설정
		userMenuHelper.setAcc(request, mngrId, vo.getRoleSn());
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_USER_MENU", bfData, null, null, mngrId);
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		UserRoleVO userRoleVO = new UserRoleVO();
		userRoleVO.setRoleSn(key);
		UserRoleVO item = this.selectObj(userRoleVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
