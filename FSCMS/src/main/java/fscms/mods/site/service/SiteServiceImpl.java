package fscms.mods.site.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.site.util.SiteHelper;
import fscms.mods.site.vo.SiteVO;
import net.sf.json.JSONArray;

@Service("siteService")
public class SiteServiceImpl extends EgovAbstractServiceImpl implements SiteService {
	
	@Resource(name = "siteMapper")
	private SiteMapper siteDAO;
	
	@Resource(name = "siteHelper")
	private SiteHelper siteHelper;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public SiteVO selectObj() throws Exception {
		return siteDAO.selectObj();
	}

	@Override
	public void insertInfo(HttpServletRequest request, SiteVO vo, String mngrId) throws Exception {
		siteDAO.insertInfo(vo);
		
		siteHelper.setJson();
		
		dataChghstHelper.setInsert(request, "FS_SITE", null, this.getData(), null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, SiteVO vo, String mngrId) throws Exception {
		siteDAO.updateInfo(vo);
		
		siteHelper.setJson();
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_SITE", bfData, this.getData(), null, mngrId);
		}
	}
	
	private String getData() throws Exception {
		String rtnData = null;
		
		SiteVO item = this.selectObj();
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
