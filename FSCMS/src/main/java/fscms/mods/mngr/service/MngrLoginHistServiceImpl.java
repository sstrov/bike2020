package fscms.mods.mngr.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.util.EgovCryptoARIAHelper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.mngr.vo.MngrLoginHistSearchVO;
import fscms.mods.mngr.vo.MngrLoginHistVO;

@Service("mngrLoginHistService")
public class MngrLoginHistServiceImpl extends EgovAbstractServiceImpl implements MngrLoginHistService {
	
	static Logger logger = LogManager.getLogger(MngrLoginHistServiceImpl.class);
	
	@Resource(name = "mngrLoginHistMapper")
	private MngrLoginHistMapper mngrLoginHistDAO;
	
	@Resource(name = "egovCryptoARIAHelper")
	private EgovCryptoARIAHelper egovCryptoARIAHelper;

	@Override
	public MngrLoginHistVO selectObj(MngrLoginHistVO vo) throws Exception {
		MngrLoginHistVO item = mngrLoginHistDAO.selectObj(vo);;
		
		if(item != null) {
			if(StringUtils.isNotEmpty(item.getMngrId())) {
				try {
					item.setMngrId(egovCryptoARIAHelper.decrypt(item.getMngrId()));
				} catch (Exception e) {
					//e.printStackTrace();
					logger.error("복호화 오류");
				}
			}
			
			if(StringUtils.isNotEmpty(item.getMngrNm())) {
				try {
					item.setMngrNm(egovCryptoARIAHelper.decrypt(item.getMngrNm()));
				} catch (Exception e) {
					//e.printStackTrace();
					logger.error("복호화 오류");
				}
			}
		}
		return item;
	}

	@Override
	public int selectTCount(MngrLoginHistSearchVO searchVO) throws Exception {
		return mngrLoginHistDAO.selectTCount(searchVO);
	}

	@Override
	public List<MngrLoginHistVO> selectList(MngrLoginHistSearchVO searchVO) throws Exception {
		return mngrLoginHistDAO.selectList(searchVO);
	}

	@Override
	public List<MngrLoginHistVO> selectPageList(MngrLoginHistSearchVO searchVO) throws Exception {
		List<MngrLoginHistVO> itemList = mngrLoginHistDAO.selectPageList(searchVO);
		
		if(itemList != null && itemList.size() > 0) {
			for (MngrLoginHistVO item : itemList) {
				if(StringUtils.isNotEmpty(item.getMngrId())) {
					try {
						item.setMngrId(egovCryptoARIAHelper.decrypt(item.getMngrId()));
					} catch (Exception e) {
						//e.printStackTrace();
						logger.error("복호화 오류");
					}
				}
				
				if(StringUtils.isNotEmpty(item.getMngrNm())) {
					try {
						item.setMngrNm(egovCryptoARIAHelper.decrypt(item.getMngrNm()));
					} catch (Exception e) {
						//e.printStackTrace();
						logger.error("복호화 오류");
					}
				}
			}
		}
		return itemList;
	}

	@Override
	public void updateEDate(MngrLoginHistVO vo) throws Exception {
		mngrLoginHistDAO.updateEDate(vo);
	}

}
