package fscms.mods.cntnts.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.cntnts.vo.CntntsChghstSearchVO;
import fscms.mods.cntnts.vo.CntntsChghstVO;
import fscms.mods.data.util.DataChghstHelper;
import net.sf.json.JSONArray;

@Service("cntntsChghstService")
public class CntntsChghstServiceImpl extends EgovAbstractServiceImpl implements CntntsChghstService {
	
	@Resource(name = "cntntsMapper")
	private CntntsChghstMapper cntntsDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public CntntsChghstVO selectObj(CntntsChghstVO vo) throws Exception {
		return cntntsDAO.selectObj(vo);
	}

	@Override
	public int selectTCount(CntntsChghstSearchVO searchVO) throws Exception {
		return cntntsDAO.selectTCount(searchVO);
	}

	@Override
	public List<CntntsChghstVO> selectPageList(CntntsChghstSearchVO searchVO) throws Exception {
		return cntntsDAO.selectPageList(searchVO);
	}

	@Override
	public void insertInfo(HttpServletRequest request, CntntsChghstVO vo, String mngrId) throws Exception {
		cntntsDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_CNTNTS_CHGHST", null, this.getData(vo.getCntntsChghstSn()), null, mngrId);
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		CntntsChghstVO cntntsChghstVO = new CntntsChghstVO();
		cntntsChghstVO.setCntntsChghstSn(key);
		CntntsChghstVO item = this.selectObj(cntntsChghstVO);
		
		if(item != null) {
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
