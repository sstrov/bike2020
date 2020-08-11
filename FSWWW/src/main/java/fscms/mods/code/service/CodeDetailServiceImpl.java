package fscms.mods.code.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.code.vo.CodeDetailVO;

@Service("codeDetailService")
public class CodeDetailServiceImpl extends EgovAbstractServiceImpl implements CodeDetailService {
	
	@Resource(name = "codeDetailMapper")
	private CodeDetailMapper codeDetailDAO;
	
	@Override
	public CodeDetailVO selectObj(CodeDetailVO vo) throws Exception {
		return codeDetailDAO.selectObj(vo);
	}

	@Override
	public List<CodeDetailVO> selectList(CodeDetailVO vo) throws Exception {
		return codeDetailDAO.selectList(vo);
	}

}
