package fscms.mods.app.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.app.vo.AppVersionVO;
import fscms.mods.data.util.DataChghstHelper;
import net.sf.json.JSONArray;

@Service("appVersionService")
public class AppVersionServiceImpl extends EgovAbstractServiceImpl implements AppVersionService {
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;
	
	@Resource(name = "appVersionMapper")
	private AppVersionMapper appVersionDAO;

	@Override
	public AppVersionVO selectObj(AppVersionVO vo) throws Exception {
		return appVersionDAO.selectObj(vo);
	}
	
	@Override
	public void updateAppVersionInfo(HttpServletRequest request, String bfData, AppVersionVO vo, String mngrId)
			throws Exception {
		appVersionDAO.updateAppVersionInfo(vo);
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_APP_VERSION", bfData, null, null, mngrId);
		}
	}
	
	private String getData(int key) throws Exception {
		
		String rtnData = null;
		AppVersionVO vo = new AppVersionVO();
		AppVersionVO item = this.selectObj(vo);
		
		if(item != null) {
			item.setUpdtDt(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}
}
