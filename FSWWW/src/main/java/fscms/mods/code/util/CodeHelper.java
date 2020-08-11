package fscms.mods.code.util;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import fscms.mods.code.service.CodeDetailService;
import fscms.mods.code.service.CodeService;
import fscms.mods.code.vo.CodeDetailVO;
import fscms.mods.code.vo.CodeVO;

@Component
public class CodeHelper {
	
	@Resource(name = "codeDetailService")
	private CodeDetailService codeDetailService;
	
	@Resource(name = "codeService")
	private CodeService codeService;
	
	/**
	 * @Method Name	: getList
	 * @작성일		: 2019. 10. 11.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 코드 목록 가져오기
	 */
	public List<CodeDetailVO> getList(String codeId) throws Exception {
		
		CodeDetailVO codeDetailVO = new CodeDetailVO();
		codeDetailVO.setCodeId(codeId);
		codeDetailVO.setDeleteAt("N");
		return codeDetailService.selectList(codeDetailVO);
	}
	
	public List<CodeVO> getCodeList(String codeClId) throws Exception {
		CodeVO codeVO = new CodeVO();
		codeVO.setCodeClId(codeClId);
		codeVO.setUseAt("Y");
		return codeService.selectList(codeVO);
	}

}
