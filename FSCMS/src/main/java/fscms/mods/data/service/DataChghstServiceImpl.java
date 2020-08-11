package fscms.mods.data.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.util.EgovCryptoARIAHelper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.data.vo.DataChghstSearchVO;
import fscms.mods.data.vo.DataChghstVO;

@Service("dataChghstService")
public class DataChghstServiceImpl extends EgovAbstractServiceImpl implements DataChghstService {
	
	static Logger logger = LogManager.getLogger(DataChghstServiceImpl.class);
	
	@Resource(name = "dataChghstMapper")
	private DataChghstMapper dataChghstDAO;
	
	@Resource(name = "egovCryptoARIAHelper")
	private EgovCryptoARIAHelper egovCryptoARIAHelper;

	@Override
	public DataChghstVO selectObj(DataChghstVO vo) throws Exception {
		DataChghstVO item = dataChghstDAO.selectObj(vo);
		
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
	public int selectTCount(DataChghstSearchVO searchVO) throws Exception {
		return dataChghstDAO.selectTCount(searchVO);
	}

	@Override
	public List<DataChghstVO> selectPageList(DataChghstSearchVO searchVO) throws Exception {
		List<DataChghstVO> itemList = dataChghstDAO.selectPageList(searchVO);
		
		if(itemList != null && itemList.size() > 0) {
			for (DataChghstVO item : itemList) {
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
	public void insertInfo(DataChghstVO vo) throws Exception {
		
		if(StringUtils.isNotEmpty(vo.getUserId())) {
			vo.setUserId(egovCryptoARIAHelper.encrypt(vo.getUserId()));
		}
		
		if(StringUtils.isNotEmpty(vo.getMngrId())) {
			vo.setMngrId(egovCryptoARIAHelper.encrypt(vo.getMngrId()));
		}
		
		dataChghstDAO.insertInfo(vo);
	}

}
