package fscms.mods.code.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.code.vo.CodeDetailVO;
import fscms.mods.data.util.DataChghstHelper;
import net.sf.json.JSONArray;

@Service("codeDetailService")
public class CodeDetailServiceImpl extends EgovAbstractServiceImpl implements CodeDetailService {
	
	@Resource(name = "codeDetailMapper")
	private CodeDetailMapper codeDetailDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public CodeDetailVO selectObj(CodeDetailVO vo) throws Exception {
		return codeDetailDAO.selectObj(vo);
	}

	@Override
	public List<CodeDetailVO> selectList(CodeDetailVO vo) throws Exception {
		return codeDetailDAO.selectList(vo);
	}

	@Override
	public void insertInfo(HttpServletRequest request, CodeDetailVO vo, String mngrId) throws Exception {
		codeDetailDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_CODE_DETAIL", null, this.getData(vo.getCodeDetailSn()), null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, CodeDetailVO vo, String mngrId) throws Exception {
		codeDetailDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_CODE_DETAIL", bfData, this.getData(vo.getCodeDetailSn()), null, mngrId);
		}
	}

	private String getData(int key) throws Exception {
		String rtnData = null;
		
		CodeDetailVO codeDetailVO = new CodeDetailVO();
		codeDetailVO.setCodeDetailSn(key);
		CodeDetailVO item = this.selectObj(codeDetailVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
