package fscms.mods.user.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.util.EgovCryptoARIAHelper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.cmm.util.CryptoHelper;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.indvdlinfo.util.IndvdlinfoAccesHistHelper;
import fscms.mods.auth.vo.AuthUploadVO;
import fscms.mods.user.vo.UserSearchVO;
import fscms.mods.user.vo.UserVO;
import net.sf.json.JSONArray;

@Service("userService")
public class UserServiceImpl extends EgovAbstractServiceImpl implements UserService {
	
	static Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Resource(name = "userMapper")
	private UserMapper userDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;
	
	@Resource(name = "egovCryptoARIAHelper")
	private EgovCryptoARIAHelper egovCryptoARIAHelper;
	
	@Resource(name = "indvdlinfoAccesHistHelper")
	private IndvdlinfoAccesHistHelper indvdlinfoAccesHistHelper;

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

	@Override
	public void insertInfo(HttpServletRequest request, UserVO vo, String mngrId) throws Exception {
		String userId = vo.getUserId();
		String userNm = vo.getUserNm();
		if(StringUtils.isNotEmpty(vo.getUserId())) {
			vo.setUserId(egovCryptoARIAHelper.encrypt(vo.getUserId()));
		}
		
		if(StringUtils.isNotEmpty(vo.getUserPw())) {
			vo.setUserPw(CryptoHelper.encrypt(vo.getUserPw(), vo.getUserSalt()));
		}
		
		if(StringUtils.isNotEmpty(vo.getUserNm())) {
			vo.setUserNm(egovCryptoARIAHelper.encrypt(vo.getUserNm()));
		}
		
		if(StringUtils.isNotEmpty(vo.getUserTelno())) {
			vo.setUserTelno(egovCryptoARIAHelper.encrypt(vo.getUserTelno(), vo.getUserSalt()));
		}
		if(StringUtils.isNotEmpty(vo.getUserMbtlnum())) {
			vo.setUserMbtlnum(egovCryptoARIAHelper.encrypt(vo.getUserMbtlnum(), vo.getUserSalt()));
		}
		if(StringUtils.isNotEmpty(vo.getUserEmail())) {
			vo.setUserEmail(egovCryptoARIAHelper.encrypt(vo.getUserEmail(), vo.getUserSalt()));
		}
		if(StringUtils.isNotEmpty(vo.getUserZip())) {
			vo.setUserZip(egovCryptoARIAHelper.encrypt(vo.getUserZip(), vo.getUserSalt()));
		}
		if(StringUtils.isNotEmpty(vo.getUserAdresBass())) {
			vo.setUserAdresBass(egovCryptoARIAHelper.encrypt(vo.getUserAdresBass(), vo.getUserSalt()));
		}
		if(StringUtils.isNotEmpty(vo.getUserAdresDetail())) {
			vo.setUserAdresDetail(egovCryptoARIAHelper.encrypt(vo.getUserAdresDetail(), vo.getUserSalt()));
		}
		
		userDAO.insertInfo(vo);
		
		Integer dataChghstSn = dataChghstHelper.setInsert(request, "FS_USER", null, this.getData(vo.getUserSn()), null, mngrId);
		
		if(dataChghstSn != null) {
			// 개인정보_접근_이력 저장
			String indvdlinfoAccesHistCn = "사용자 (" + userId + " | " + userNm + ") 정보를 등록 하였습니다.";
			indvdlinfoAccesHistHelper.setInsert(request, null, mngrId, indvdlinfoAccesHistCn, dataChghstSn);
		}
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, UserVO vo, String mngrId) throws Exception {
		UserVO userVO = new UserVO();
		userVO.setUserSn(vo.getUserSn());
		UserVO item = this.selectObj(userVO);
		
		if(item != null) {
			String userId = item.getUserId();
			String userNm = item.getUserNm();
			if(StringUtils.isNotEmpty(vo.getUserId())) {
				vo.setUserId(egovCryptoARIAHelper.encrypt(vo.getUserId()));
			}
			
			if(StringUtils.isNotEmpty(vo.getUserPw())) {
				vo.setUserPw(CryptoHelper.encrypt(vo.getUserPw(), vo.getUserSalt()));
			}
			
			if(StringUtils.isNotEmpty(vo.getUserNm())) {
				vo.setUserNm(egovCryptoARIAHelper.encrypt(vo.getUserNm()));
			}
			
			if(StringUtils.isNotEmpty(vo.getUserTelno())) {
				vo.setUserTelno(egovCryptoARIAHelper.encrypt(vo.getUserTelno(), vo.getUserSalt()));
			}
			if(StringUtils.isNotEmpty(vo.getUserMbtlnum())) {
				vo.setUserMbtlnum(egovCryptoARIAHelper.encrypt(vo.getUserMbtlnum(), vo.getUserSalt()));
			}
			if(StringUtils.isNotEmpty(vo.getUserEmail())) {
				vo.setUserEmail(egovCryptoARIAHelper.encrypt(vo.getUserEmail(), vo.getUserSalt()));
			}
			if(StringUtils.isNotEmpty(vo.getUserZip())) {
				vo.setUserZip(egovCryptoARIAHelper.encrypt(vo.getUserZip(), vo.getUserSalt()));
			}
			if(StringUtils.isNotEmpty(vo.getUserAdresBass())) {
				vo.setUserAdresBass(egovCryptoARIAHelper.encrypt(vo.getUserAdresBass(), vo.getUserSalt()));
			}
			if(StringUtils.isNotEmpty(vo.getUserAdresDetail())) {
				vo.setUserAdresDetail(egovCryptoARIAHelper.encrypt(vo.getUserAdresDetail(), vo.getUserSalt()));
			}
			
			userDAO.updateInfo(vo);
			
			if(StringUtils.isNotEmpty(bfData)) {
				Integer dataChghstSn = dataChghstHelper.setInsert(request, "FS_USER", bfData, this.getData(vo.getUserSn()), null, mngrId);
				
				if(dataChghstSn != null) {
					// 개인정보_접근_이력 저장
					String indvdlinfoAccesHistCn = "사용자 (" + userId + " | " + userNm + ") 정보를 수정 하였습니다.";
					indvdlinfoAccesHistHelper.setInsert(request, null, mngrId, indvdlinfoAccesHistCn, dataChghstSn);
				}
			}
		}
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, UserVO vo, String mngrId) throws Exception {
		userDAO.deleteInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			Integer dataChghstSn = dataChghstHelper.setInsert(request, "FS_USER", bfData, null, null, mngrId);
			
			if(dataChghstSn != null) {
				// 개인정보_접근_이력 저장
				String indvdlinfoAccesHistCn = "사용자 (" + vo.getUserId() + " | " + vo.getUserNm() + ") 정보를 삭제 하였습니다.";
				indvdlinfoAccesHistHelper.setInsert(request, null, mngrId, indvdlinfoAccesHistCn, dataChghstSn);
			}
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		UserVO userVO = new UserVO();
		userVO.setUserSn(key);
		UserVO item = this.selectObj(userVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

	@Override
	public void deleteNote(HttpServletRequest request, String bfData, UserVO vo, String mngrId) throws Exception {
		userDAO.deleteNote(vo);
		
		
		Integer dataChghstSn = dataChghstHelper.setInsert(request, "FS_USER", bfData, this.getData(vo.getUserSn()), null, mngrId);
		if(dataChghstSn != null) {
			// 개인정보_접근_이력 저장
			String indvdlinfoAccesHistCn = "사용자 (" + vo.getUserId() + " | " + vo.getUserNm() + ") 정보를 수정 하였습니다.";
			indvdlinfoAccesHistHelper.setInsert(request, null, mngrId, indvdlinfoAccesHistCn, dataChghstSn);
		}
		
	}

}
