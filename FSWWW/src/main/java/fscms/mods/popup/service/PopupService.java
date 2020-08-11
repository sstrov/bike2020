package fscms.mods.popup.service;

import java.util.List;

import fscms.mods.popup.vo.PopupVO;

public interface PopupService {
	
	PopupVO selectObj(PopupVO vo) throws Exception;
	
	List<PopupVO> selectList(PopupVO vo) throws Exception;
	
}
