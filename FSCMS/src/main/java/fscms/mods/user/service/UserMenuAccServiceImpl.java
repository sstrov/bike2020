package fscms.mods.user.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.user.vo.UserMenuAccVO;
import net.sf.json.JSONArray;

@Service("userMenuAccService")
public class UserMenuAccServiceImpl extends EgovAbstractServiceImpl implements UserMenuAccService {
	
	@Resource(name = "userMenuAccMapper")
	private UserMenuAccMapper userMenuAccDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public UserMenuAccVO selectObj(UserMenuAccVO vo) throws Exception {
		return userMenuAccDAO.selectObj(vo);
	}

	@Override
	public List<UserMenuAccVO> selectList(UserMenuAccVO vo) throws Exception {
		return userMenuAccDAO.selectList(vo);
	}

	@Override
	public void insertInfo(HttpServletRequest request, UserMenuAccVO vo, String mngrId) throws Exception {
		userMenuAccDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_USER_MENU_ACC", null, this.getData(vo.getMenuAccSn()), null, mngrId);
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, UserMenuAccVO vo, String mngrId)
			throws Exception {
		userMenuAccDAO.deleteInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_USER_MENU_ACC", bfData, null, null, mngrId);
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		UserMenuAccVO userMenuAccVO = new UserMenuAccVO();
		userMenuAccVO.setMenuAccSn(key);
		UserMenuAccVO item = this.selectObj(userMenuAccVO);
		
		if(item != null) {
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
