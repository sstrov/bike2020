package fscms.mods.popup.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.popup.vo.PopupVO;

@Service("popupService")
public class PopupServiceImpl extends EgovAbstractServiceImpl implements PopupService {
	
	@Resource(name = "popupMapper")
	private PopupMapper popupDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;
	
	@Override
	public PopupVO selectObj(PopupVO vo) throws Exception {
		return popupDAO.selectObj(vo);
	}

	@Override
	public List<PopupVO> selectList(PopupVO vo) throws Exception {
		return popupDAO.selectList(vo);
	}

}
