package fscms.mods.mngr.service;

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
import fscms.mods.mngr.vo.MngrSearchVO;
import fscms.mods.mngr.vo.MngrVO;
import net.sf.json.JSONArray;

@Service("mngrService")
public class MngrServiceImpl extends EgovAbstractServiceImpl implements MngrService {
	
	static Logger logger = LogManager.getLogger(MngrServiceImpl.class);
	
	@Resource(name = "mngrMapper")
	private MngrMapper mngrDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;
	
	@Resource(name = "egovCryptoARIAHelper")
	private EgovCryptoARIAHelper egovCryptoARIAHelper;
	
	@Resource(name = "indvdlinfoAccesHistHelper")
	private IndvdlinfoAccesHistHelper indvdlinfoAccesHistHelper;

	@Override
	public MngrVO selectObj(MngrVO vo) throws Exception {
		MngrVO item = mngrDAO.selectObj(vo);
		
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
	public int selectTCount(MngrSearchVO searchVO) throws Exception {
		return mngrDAO.selectTCount(searchVO);
	}

	@Override
	public List<MngrVO> selectPageList(MngrSearchVO searchVO) throws Exception {
		List<MngrVO> itemList = mngrDAO.selectPageList(searchVO);
		
		if(itemList != null && itemList.size() > 0) {
			for (MngrVO item : itemList) {
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
	public void insertInfo(HttpServletRequest request, MngrVO vo, String mngrId) throws Exception {
		String userId = vo.getMngrId();
		String userNm = vo.getMngrNm();
		if(StringUtils.isNotEmpty(vo.getMngrId())) {
			vo.setMngrId(egovCryptoARIAHelper.encrypt(vo.getMngrId()));
		}
		
		if(StringUtils.isNotEmpty(vo.getMngrPw())) {
			vo.setMngrPw(CryptoHelper.encrypt(vo.getMngrPw(), vo.getMngrSalt()));
		}
		
		if(StringUtils.isNotEmpty(vo.getMngrNm())) {
			vo.setMngrNm(egovCryptoARIAHelper.encrypt(vo.getMngrNm()));
		}
		
		mngrDAO.insertInfo(vo);
		
		Integer dataChghstSn = dataChghstHelper.setInsert(request, "FS_MNGR", null, this.getData(vo.getMngrSn()), null, mngrId);
		
		if(dataChghstSn != null) {
			// 개인정보_접근_이력 저장
			String indvdlinfoAccesHistCn = "관리자 (" + userId + " | " + userNm + ") 정보를 등록 하였습니다.";
			indvdlinfoAccesHistHelper.setInsert(request, null, mngrId, indvdlinfoAccesHistCn, dataChghstSn);
		}
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, MngrVO vo, String mngrId) throws Exception {
		MngrVO mngrVO = new MngrVO();
		mngrVO.setMngrSn(vo.getMngrSn());
		MngrVO item = this.selectObj(mngrVO);
		
		if(item != null) {
			String userId = item.getMngrId();
			String userNm = item.getMngrNm();
			if(StringUtils.isNotEmpty(vo.getMngrId())) {
				vo.setMngrId(egovCryptoARIAHelper.encrypt(vo.getMngrId()));
			}
			
			if(StringUtils.isNotEmpty(vo.getMngrPw())) {
				vo.setMngrPw(CryptoHelper.encrypt(vo.getMngrPw(), vo.getMngrSalt()));
			}
			
			if(StringUtils.isNotEmpty(vo.getMngrNm())) {
				vo.setMngrNm(egovCryptoARIAHelper.encrypt(vo.getMngrNm()));
			}
			
			mngrDAO.updateInfo(vo);
			
			if(StringUtils.isNotEmpty(bfData)) {
				Integer dataChghstSn = dataChghstHelper.setInsert(request, "FS_MNGR", bfData, this.getData(vo.getMngrSn()), null, mngrId);
				
				if(dataChghstSn != null) {
					// 개인정보_접근_이력 저장
					String indvdlinfoAccesHistCn = "관리자 (" + userId + " | " + userNm + ") 정보를 수정 하였습니다.";
					indvdlinfoAccesHistHelper.setInsert(request, null, mngrId, indvdlinfoAccesHistCn, dataChghstSn);
				}
			}
		}
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, MngrVO vo, String mngrId) throws Exception {
		mngrDAO.deleteInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			Integer dataChghstSn = dataChghstHelper.setInsert(request, "FS_MNGR", bfData, null, null, mngrId);
			
			if(dataChghstSn != null) {
				// 개인정보_접근_이력 저장
				String indvdlinfoAccesHistCn = "관리자 (" + vo.getMngrId() + " | " + vo.getMngrNm() + ") 정보를 삭제 하였습니다.";
				indvdlinfoAccesHistHelper.setInsert(request, null, mngrId, indvdlinfoAccesHistCn, dataChghstSn);
			}
		}
	};
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		MngrVO mngrVO = new MngrVO();
		mngrVO.setMngrSn(key);
		MngrVO item = this.selectObj(mngrVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
