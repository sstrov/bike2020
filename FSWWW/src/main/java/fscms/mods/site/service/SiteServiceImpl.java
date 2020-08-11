package fscms.mods.site.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.site.vo.SiteVO;

@Service("siteService")
public class SiteServiceImpl extends EgovAbstractServiceImpl implements SiteService {
	
	@Resource(name = "siteMapper")
	private SiteMapper siteDAO;
	
	@Override
	public SiteVO selectObj() throws Exception {
		return siteDAO.selectObj();
	}

}
