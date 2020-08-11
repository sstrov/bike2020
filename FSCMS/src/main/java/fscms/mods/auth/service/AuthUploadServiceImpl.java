package fscms.mods.auth.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.util.EgovCryptoARIAHelper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.app.service.AppVersionMapper;
import fscms.mods.banner.vo.BannerEstbsVO;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.auth.vo.AuthUploadSearchVO;
import fscms.mods.auth.vo.AuthUploadVO;
import fscms.mods.user.service.UserServiceImpl;
import fscms.mods.user.vo.UserVO;
import net.sf.json.JSONArray;

@Service("authUploadService")
public class AuthUploadServiceImpl extends EgovAbstractServiceImpl implements AuthUploadService{

	static Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;
	
	@Resource(name = "authUploadMapper")
	private AuthUploadMapper authUploadDAO;
	
	@Resource(name = "egovCryptoARIAHelper")
	private EgovCryptoARIAHelper egovCryptoARIAHelper;
	
	@Override
	public int selectTCount(AuthUploadSearchVO searchVO) throws Exception {
		return authUploadDAO.selectTCount(searchVO);
	}

	@Override
	public List<AuthUploadVO> selectPageList(AuthUploadSearchVO searchVO) throws Exception {
		return authUploadDAO.selectPageList(searchVO);
	}

	@Override
	public void insertExcelInfo(MultipartHttpServletRequest request, Object object, List<AuthUploadVO> itemList, String mngrId)
			throws Exception {
		for(int i=0;i<itemList.size();i++) {
			
			AuthUploadVO vo = new AuthUploadVO();
			vo = itemList.get(i);
			authUploadDAO.insertExcelInfo(vo);
		}
		
		AuthUploadSearchVO searchVO = new AuthUploadSearchVO();
		itemList = this.selectPageList(searchVO);
		
		dataChghstHelper.setInsert(request, "FS_AUTH_UPLOAD", null, JSONArray.fromObject(itemList).toString(), null, mngrId);
	}
	
	@Override
	public AuthUploadVO selectObj(AuthUploadVO vo) throws Exception {
		return authUploadDAO.selectObj(vo);
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		AuthUploadVO authUploadVO = new AuthUploadVO();
		authUploadVO.setAuSn(key);
		AuthUploadVO item = this.selectObj(authUploadVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

	@Override
	public List<AuthUploadVO> selectNoteList(AuthUploadSearchVO searchVO) throws Exception {
		return authUploadDAO.selectNoteList(searchVO);
	}

	//
	@Override
	public List<AuthUploadVO> getObj(AuthUploadVO vo) throws Exception {
		return authUploadDAO.getObj(vo);
	}

	@Override
	public void updateAuth(HttpServletRequest request, List<AuthUploadVO> bData, AuthUploadVO vo, String mngrId) throws Exception {
		authUploadDAO.updateAuth(vo);
		
		List<AuthUploadVO> itemList = this.getObj(vo);
		
		dataChghstHelper.setInsert(request, "FS_AUTH_UPLOAD", JSONArray.fromObject(bData).toString(), JSONArray.fromObject(itemList).toString(), null, mngrId);
	}

	@Override
	public int selectTCountWeb(AuthUploadSearchVO searchVO) throws Exception {
		return authUploadDAO.selectTCountWeb(searchVO);
	}

	@Override
	public List<AuthUploadVO> selectPageListWeb(AuthUploadSearchVO searchVO) throws Exception {
		List<AuthUploadVO> itemList = authUploadDAO.selectPageListWeb(searchVO);
		for(AuthUploadVO item : itemList) {
			if(StringUtils.isNotEmpty(item.getUserNm())) {
				try {
					item.setUserNm(egovCryptoARIAHelper.decrypt(item.getUserNm()));
				} catch (Exception e) {
					logger.error("복호화 오류");
				}
			}
			
			if(StringUtils.isNotEmpty(item.getUserId())) {
				try {
					item.setUserId(egovCryptoARIAHelper.decrypt(item.getUserId()));
				} catch (Exception e) {
					logger.error("복호화 오류");
				}
			}
			
		}
		return itemList;
	}

}
