package fscms.mods.app.service;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.app.vo.AppVersionVO;

public interface AppVersionService {

	AppVersionVO selectObj(AppVersionVO vo) throws Exception;

	void updateAppVersionInfo(HttpServletRequest request, String bfData, AppVersionVO vo, String mngrId) throws Exception;



}
