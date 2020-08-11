package fscms.mods.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.util.EgovCryptoARIAHelper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.user.vo.UserSearchVO;
import fscms.mods.user.vo.UserVO;

@Service("userService")
public class UserServiceImpl extends EgovAbstractServiceImpl implements UserService {
	
	static Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Resource(name = "userMapper")
	private UserMapper userDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;
	
	@Resource(name = "egovCryptoARIAHelper")
	private EgovCryptoARIAHelper egovCryptoARIAHelper;
	
	@Override
	public UserVO selectObj(UserVO vo) throws Exception {
		UserVO item = userDAO.selectObj(vo);
		
		if(item != null) {
			if(StringUtils.isNotEmpty(item.getUserId())) {
				try {
					item.setUserId(egovCryptoARIAHelper.decrypt(item.getUserId()));
				} catch (Exception e) {
					logger.error("복호화 오류");
				}
			}
			
			if(StringUtils.isNotEmpty(item.getUserNm())) {
				try {
					item.setUserNm(egovCryptoARIAHelper.decrypt(item.getUserNm()));
				} catch (Exception e) {
					logger.error("복호화 오류");
				}
			}
			
			if(StringUtils.isNotEmpty(item.getUserTelno())) {
				try {
					item.setUserTelno(egovCryptoARIAHelper.decrypt(item.getUserTelno(), item.getUserSalt()));
				} catch (Exception e) {
					logger.error("복호화 오류");
				}
			}
			
			if(StringUtils.isNotEmpty(item.getUserMbtlnum())) {
				try {
					item.setUserMbtlnum(egovCryptoARIAHelper.decrypt(item.getUserMbtlnum(), item.getUserSalt()));
				} catch (Exception e) {
					logger.error("복호화 오류");
				}
			}
			
			if(StringUtils.isNotEmpty(item.getUserEmail())) {
				try {
					item.setUserEmail(egovCryptoARIAHelper.decrypt(item.getUserEmail(), item.getUserSalt()));
				} catch (Exception e) {
					logger.error("복호화 오류");
				}
			}
			
			if(StringUtils.isNotEmpty(item.getUserZip())) {
				try {
					item.setUserZip(egovCryptoARIAHelper.decrypt(item.getUserZip(), item.getUserSalt()));
				} catch (Exception e) {
					logger.error("복호화 오류");
				}
			}
			
			if(StringUtils.isNotEmpty(item.getUserAdresBass())) {
				try {
					item.setUserAdresBass(egovCryptoARIAHelper.decrypt(item.getUserAdresBass(), item.getUserSalt()));
				} catch (Exception e) {
					logger.error("복호화 오류");
				}
			}
			
			if(StringUtils.isNotEmpty(item.getUserAdresDetail())) {
				try {
					item.setUserAdresDetail(egovCryptoARIAHelper.decrypt(item.getUserAdresDetail(), item.getUserSalt()));
				} catch (Exception e) {
					logger.error("복호화 오류");
				}
			}
		}
		return item;
	}

	@Override
	public int selectTCount(UserSearchVO searchVO) throws Exception {
		return userDAO.selectTCount(searchVO);
	}

	@Override
	public List<UserVO> selectPageList(UserSearchVO searchVO) throws Exception {
		List<UserVO> itemList = userDAO.selectPageList(searchVO);
		
		if(itemList != null && itemList.size() > 0) {
			for (UserVO item : itemList) {
				if(StringUtils.isNotEmpty(item.getUserId())) {
					try {
						item.setUserId(egovCryptoARIAHelper.decrypt(item.getUserId()));
					} catch (Exception e) {
						logger.error("복호화 오류");
					}
				}
				
				if(StringUtils.isNotEmpty(item.getUserNm())) {
					try {
						item.setUserNm(egovCryptoARIAHelper.decrypt(item.getUserNm()));
					} catch (Exception e) {
						logger.error("복호화 오류");
					}
				}
				
				if(StringUtils.isNotEmpty(item.getUserTelno())) {
					try {
						item.setUserTelno(egovCryptoARIAHelper.decrypt(item.getUserTelno(), item.getUserSalt()));
					} catch (Exception e) {
						logger.error("복호화 오류");
					}
				}
				
				if(StringUtils.isNotEmpty(item.getUserMbtlnum())) {
					try {
						item.setUserMbtlnum(egovCryptoARIAHelper.decrypt(item.getUserMbtlnum(), item.getUserSalt()));
					} catch (Exception e) {
						logger.error("복호화 오류");
					}
				}
				
				if(StringUtils.isNotEmpty(item.getUserEmail())) {
					try {
						item.setUserEmail(egovCryptoARIAHelper.decrypt(item.getUserEmail(), item.getUserSalt()));
					} catch (Exception e) {
						logger.error("복호화 오류");
					}
				}
				
				if(StringUtils.isNotEmpty(item.getUserZip())) {
					try {
						item.setUserZip(egovCryptoARIAHelper.decrypt(item.getUserZip(), item.getUserSalt()));
					} catch (Exception e) {
						logger.error("복호화 오류");
					}
				}
				
				if(StringUtils.isNotEmpty(item.getUserAdresBass())) {
					try {
						item.setUserAdresBass(egovCryptoARIAHelper.decrypt(item.getUserAdresBass(), item.getUserSalt()));
					} catch (Exception e) {
						logger.error("복호화 오류");
					}
				}
				
				if(StringUtils.isNotEmpty(item.getUserAdresDetail())) {
					try {
						item.setUserAdresDetail(egovCryptoARIAHelper.decrypt(item.getUserAdresDetail(), item.getUserSalt()));
					} catch (Exception e) {
						logger.error("복호화 오류");
					}
				}
			}
		}
		return itemList;
	}

}
