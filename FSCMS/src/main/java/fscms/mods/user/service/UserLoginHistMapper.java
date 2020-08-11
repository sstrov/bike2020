package fscms.mods.user.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.mngr.vo.MngrLoginHistVO;
import fscms.mods.user.vo.UserLoginHistSearchVO;
import fscms.mods.user.vo.UserLoginHistVO;

@Mapper("userLoginHistMapper")
public interface UserLoginHistMapper {

	int selectTCount(UserLoginHistSearchVO searchVO) throws Exception;

	List<MngrLoginHistVO> selectPageList(UserLoginHistSearchVO searchVO) throws Exception;

	UserLoginHistVO selectObj(UserLoginHistVO userLoginHistVO) throws Exception;

}
