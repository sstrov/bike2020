package fscms.mods.code.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.code.vo.CodeClSearchVO;
import fscms.mods.code.vo.CodeClVO;
import fscms.mods.data.util.DataChghstHelper;
import net.sf.json.JSONArray;

@Service("codeClService")
public class CodeClServiceImpl extends EgovAbstractServiceImpl implements CodeClService {
	
	@Resource(name = "codeClMapper")
	private CodeClMapper codeClDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public CodeClVO selectObj(CodeClVO vo) throws Exception {
		return codeClDAO.selectObj(vo);
	}

	@Override
	public int selectTCount(CodeClSearchVO searchVO) throws Exception {
		return codeClDAO.selectTCount(searchVO);
	}

	@Override
	public List<CodeClVO> selectPageList(CodeClSearchVO searchVO) throws Exception {
		return codeClDAO.selectPageList(searchVO);
	}

	@Override
	public void insertInfo(HttpServletRequest request, CodeClVO vo, String mngrId) throws Exception {
		codeClDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_CODE_CL", null, this.getData(vo.getCodeClId()), null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, CodeClVO vo, String mngrId) throws Exception {
		codeClDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_CODE_CL", bfData, this.getData(vo.getCodeClId()), null, mngrId);
		}
	}
	
	private String getData(String key) throws Exception {
		String rtnData = null;
		
		CodeClVO codeClVO = new CodeClVO();
		codeClVO.setCodeClId(key);
		CodeClVO item = this.selectObj(codeClVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
