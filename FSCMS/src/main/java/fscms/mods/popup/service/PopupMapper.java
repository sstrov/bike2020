package fscms.mods.popup.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.popup.vo.PopupSearchVO;
import fscms.mods.popup.vo.PopupVO;

@Mapper("popupMapper")
public interface PopupMapper {
	
	PopupVO selectObj(PopupVO vo) throws Exception;
	
	List<PopupVO> selectList(PopupVO vo) throws Exception;
	
	int selectTCount(PopupSearchVO searchVO) throws Exception;
	
	List<PopupVO> selectPageList(PopupSearchVO searchVO) throws Exception;
	
	void insertInfo(PopupVO vo) throws Exception;
	
	void updateInfo(PopupVO vo) throws Exception;
	
	void deleteInfo(PopupVO vo) throws Exception;
}
