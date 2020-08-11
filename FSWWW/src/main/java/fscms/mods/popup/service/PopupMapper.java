package fscms.mods.popup.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.popup.vo.PopupVO;

@Mapper("popupMapper")
public interface PopupMapper {
	
	PopupVO selectObj(PopupVO vo) throws Exception;
	
	List<PopupVO> selectList(PopupVO vo) throws Exception;
	
}
