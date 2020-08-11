package fscms.mods.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.user.util.UserMenuHelper;
import fscms.mods.user.vo.UserMenuVO;

@Service("userMenuService")
public class UserMenuServiceImpl extends EgovAbstractServiceImpl implements UserMenuService {
	
	@Resource(name = "userMenuMapper")
	private UserMenuMapper userMenuDAO;
	
	@Resource(name = "userMenuHelper")
	private UserMenuHelper userMenuHelper;
	
	@Override
	public UserMenuVO selectObj(UserMenuVO vo) throws Exception {
		return userMenuDAO.selectObj(vo);
	}

	@Override
	public List<UserMenuVO> selectList(UserMenuVO vo) throws Exception {
		return userMenuDAO.selectList(vo);
	}

}
