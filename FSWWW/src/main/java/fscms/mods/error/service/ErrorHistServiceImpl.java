package fscms.mods.error.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.error.vo.ErrorHistVO;

@Service("errorHistService")
public class ErrorHistServiceImpl extends EgovAbstractServiceImpl implements ErrorHistService {
	
	@Resource(name = "errorHistMapper")
	private ErrorHistMapper errorHistDAO;

	@Override
	public ErrorHistVO selectObj(ErrorHistVO vo) throws Exception {
		return errorHistDAO.selectObj(vo);
	}

	@Override
	public void insertInfo(ErrorHistVO vo) throws Exception {
		errorHistDAO.insertInfo(vo);
	}

}
