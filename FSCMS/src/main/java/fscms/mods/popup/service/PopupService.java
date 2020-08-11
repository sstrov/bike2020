package fscms.mods.popup.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.popup.vo.PopupSearchVO;
import fscms.mods.popup.vo.PopupVO;

public interface PopupService {
	
	PopupVO selectObj(PopupVO vo) throws Exception;
	
	List<PopupVO> selectList(PopupVO vo) throws Exception;
	
	int selectTCount(PopupSearchVO searchVO) throws Exception;
	
	List<PopupVO> selectPageList(PopupSearchVO searchVO) throws Exception;
	
	void insertInfo(HttpServletRequest request, PopupVO vo, String uniqueId, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, PopupVO vo, String uniqueId, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, String bfData, PopupVO vo, String mngrId) throws Exception;

}
