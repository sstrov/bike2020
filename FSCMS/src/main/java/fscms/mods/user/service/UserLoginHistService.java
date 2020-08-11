package fscms.mods.user.service;

import java.util.List;

import fscms.mods.mngr.vo.MngrLoginHistVO;
import fscms.mods.user.vo.UserLoginHistSearchVO;
import fscms.mods.user.vo.UserLoginHistVO;

public interface UserLoginHistService {

	int selectTCount(UserLoginHistSearchVO searchVO) throws Exception;

	List<MngrLoginHistVO> selectPageList(UserLoginHistSearchVO searchVO) throws Exception;

	UserLoginHistVO selectObj(UserLoginHistVO userLoginHistVO) throws Exception;

}
