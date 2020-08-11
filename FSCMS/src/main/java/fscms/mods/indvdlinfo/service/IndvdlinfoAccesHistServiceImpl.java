package fscms.mods.indvdlinfo.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.util.EgovCryptoARIAHelper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.indvdlinfo.vo.IndvdlinfoAccesHistSearchVO;
import fscms.mods.indvdlinfo.vo.IndvdlinfoAccesHistVO;

@Service("indvdlinfoAccesHistService")
public class IndvdlinfoAccesHistServiceImpl extends EgovAbstractServiceImpl implements IndvdlinfoAccesHistService {
	
	static Logger logger = LogManager.getLogger(IndvdlinfoAccesHistServiceImpl.class);
	
	@Resource(name = "indvdlinfoAccesHistMapper")
	private IndvdlinfoAccesHistMapper indvdlinfoAccesHistDAO;
	
	@Resource(name = "egovCryptoARIAHelper")
	private EgovCryptoARIAHelper egovCryptoARIAHelper;

	@Override
	public IndvdlinfoAccesHistVO selectObj(IndvdlinfoAccesHistVO vo) throws Exception {
		IndvdlinfoAccesHistVO item = indvdlinfoAccesHistDAO.selectObj(vo);
		
		if(item != null) {
			if(StringUtils.isNotEmpty(item.getUserId())) {
				try {
					item.setUserId(egovCryptoARIAHelper.decrypt(item.getUserId()));
				} catch (Exception e) {
					logger.error("복호화 오류");
				}
			}
			if(StringUtils.isNotEmpty(item.getMngrId())) {
				try {
					item.setMngrId(egovCryptoARIAHelper.decrypt(item.getMngrId()));
				} catch (Exception e) {
					logger.error("복호화 오류");
				}
			}
		}
		return item;
	}

	@Override
	public int selectTCount(IndvdlinfoAccesHistSearchVO searchVO) throws Exception {
		return indvdlinfoAccesHistDAO.selectTCount(searchVO);
	}

	@Override
	public List<IndvdlinfoAccesHistVO> selectPageList(IndvdlinfoAccesHistSearchVO searchVO) throws Exception {
		List<IndvdlinfoAccesHistVO> itemList = indvdlinfoAccesHistDAO.selectPageList(searchVO);
		
		if(itemList != null && itemList.size() > 0) {
			for (IndvdlinfoAccesHistVO item : itemList) {
				if(StringUtils.isNotEmpty(item.getUserId())) {
					try {
						item.setUserId(egovCryptoARIAHelper.decrypt(item.getUserId()));
					} catch (Exception e) {
						logger.error("복호화 오류");
					}
				}
				if(StringUtils.isNotEmpty(item.getMngrId())) {
					try {
						item.setMngrId(egovCryptoARIAHelper.decrypt(item.getMngrId()));
					} catch (Exception e) {
						logger.error("복호화 오류");
					}
				}
			}
		}
		return itemList;
	}

	@Override
	public void insertInfo(IndvdlinfoAccesHistVO vo) throws Exception {
		
		if(StringUtils.isNotEmpty(vo.getUserId())) {
			vo.setUserId(egovCryptoARIAHelper.encrypt(vo.getUserId()));
		}
		
		if(StringUtils.isNotEmpty(vo.getMngrId())) {
			vo.setMngrId(egovCryptoARIAHelper.encrypt(vo.getMngrId()));
		}
		
		indvdlinfoAccesHistDAO.insertInfo(vo);
	}

}
