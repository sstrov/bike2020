package fscms.mods.push.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.push.vo.PushSearchVO;
import fscms.mods.push.vo.PushVO;

public interface PushService {

	int selectTCount(PushSearchVO searchVO) throws Exception;

	List<PushVO> selectPageList(PushSearchVO searchVO) throws Exception;

	PushVO selectObj(PushVO pushVO) throws Exception;

	void insertInfo(HttpServletRequest request, PushVO vo, String uniqueId, String mngrId) throws Exception;

	void updateInfo(HttpServletRequest request, String bfData, PushVO vo, String uniqueId, String mngrId) throws Exception;

	void deleteInfo(HttpServletRequest request, String bfData, PushVO item, String mngrId) throws Exception;

	int selectMaxPushSn(PushVO pushVO) throws Exception;

}
