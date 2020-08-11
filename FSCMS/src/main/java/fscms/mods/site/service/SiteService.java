package fscms.mods.site.service;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.site.vo.SiteVO;

public interface SiteService {
	
	SiteVO selectObj() throws Exception;
	
	void insertInfo(HttpServletRequest request, SiteVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, SiteVO vo, String mngrId) throws Exception;

}
