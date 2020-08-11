package fscms.mods.code.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.code.vo.CodeVO;

@Service("codeService")
public class CodeServiceImpl extends EgovAbstractServiceImpl implements CodeService {
	
	@Resource(name = "codeMapper")
	private CodeMapper codeDAO;
	
	@Resource(name = "codeDetailService")
	private CodeDetailService codeDetailService;
	
	@Override
	public List<CodeVO> selectList(CodeVO vo) throws Exception {
		return codeDAO.selectList(vo);
	}

}
