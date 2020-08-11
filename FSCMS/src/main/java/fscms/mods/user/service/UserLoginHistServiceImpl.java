package fscms.mods.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.mngr.vo.MngrLoginHistVO;
import fscms.mods.user.vo.UserLoginHistSearchVO;
import fscms.mods.user.vo.UserLoginHistVO;

@Service("userLoginHistService")
public class UserLoginHistServiceImpl  extends EgovAbstractServiceImpl implements UserLoginHistService {
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;
	
	@Resource(name = "userLoginHistMapper")
	private UserLoginHistMapper userLoginHistDAO;
	
	@Override
	public int selectTCount(UserLoginHistSearchVO searchVO) throws Exception {
		return userLoginHistDAO.selectTCount(searchVO);
	}

	@Override
	public List<MngrLoginHistVO> selectPageList(UserLoginHistSearchVO searchVO) throws Exception {
		return userLoginHistDAO.selectPageList(searchVO);
	}

	@Override
	public UserLoginHistVO selectObj(UserLoginHistVO userLoginHistVO) throws Exception {
		return userLoginHistDAO.selectObj(userLoginHistVO);
	}

}
