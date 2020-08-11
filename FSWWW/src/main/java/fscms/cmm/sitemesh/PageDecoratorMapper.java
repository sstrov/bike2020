package fscms.cmm.sitemesh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.module.sitemesh.Config;
import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.DecoratorMapper;
import com.opensymphony.module.sitemesh.Page;
import com.opensymphony.module.sitemesh.mapper.AbstractDecoratorMapper;

import fscms.cmm.exception.WrongApproachException;
import fscms.mods.banner.service.BannerService;
import fscms.mods.site.util.SiteHelper;
import fscms.mods.user.util.UserMenuHelper;
import fscms.mods.user.vo.UserMenuVO;
import fscms.mods.user.vo.UserVO;

@Component
public class PageDecoratorMapper extends AbstractDecoratorMapper {
	
	static Logger logger = LogManager.getLogger(PageDecoratorMapper.class);
	
	private List<String> pageProps;
	
	private static UserMenuHelper userMenuHelper;
	
	private static SiteHelper siteHelper;
	
	public PageDecoratorMapper() {
		pageProps = null;
	}
	
	@Autowired
	public void setUserMenuHelper(UserMenuHelper _userMenuHelper) {
		userMenuHelper = _userMenuHelper;
	}

	@Autowired
	public void setSiteHelper(SiteHelper _siteHelper) {
		siteHelper = _siteHelper;
	}

	@SuppressWarnings("rawtypes")
	public void init(Config config, Properties properties, DecoratorMapper parent)
			throws InstantiationException {
		super.init(config, properties, parent);
		pageProps = new ArrayList<String>();
		Iterator i = properties.entrySet().iterator();

		do {
			if(!i.hasNext())
				break;

			Entry entry = (Entry) i.next();
			String key = (String) entry.getKey();

			if(key.startsWith("property"))
				pageProps.add((String) entry.getValue());
		} while (true);
	}
	
	public Decorator getDecorator(HttpServletRequest request, Page page) {
		Decorator decorator = null;
		@SuppressWarnings("rawtypes")
		Iterator i = pageProps.iterator();
		
		do {
			if(!i.hasNext()) {
				break;
			}

			String propName = (String) i.next();
			decorator = getByProperty(request, page, propName);
		} while (decorator == null);
		
		if(decorator == null) {
			decorator = super.getDecorator(request, page);
		}

		if(decorator != null) {
			// 사이트 정보
			try {
				request.setAttribute("siteSession", siteHelper.getSite());
			} catch (IOException | ParseException e) {
				logger.error("사이트정보가 없습니다." + e.getMessage());
				throw new WrongApproachException("D0011");
			}
			
			// 사이트 관리자
			String key = request.getParameter("key");
			
			// 현재 메뉴 정보
			UserMenuVO menu = userMenuHelper.getJson(key);
			
			String uri = request.getRequestURI();
			if(StringUtils.startsWith(uri, "/stats/public")) {
				menu.setMenuNm("공공데이터");
			}
			
			request.setAttribute("currentMenu", menu);
			
			UserVO userSession = (UserVO) request.getSession().getAttribute("userSession");
			request.setAttribute("userSession", userSession);
		}
		
		return decorator;
	}
	
	private Decorator getByProperty(HttpServletRequest request, Page page, String name) {
		return (page.isPropertySet(name))? getNamedDecorator(request, page.getProperty(name)) : null;
	}

}
