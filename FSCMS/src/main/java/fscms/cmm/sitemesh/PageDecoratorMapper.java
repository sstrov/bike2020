package fscms.cmm.sitemesh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

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
import fscms.cmm.util.PropConnHelper;
import fscms.mods.mngr.util.MngrMenuHelper;
import fscms.mods.mngr.vo.MngrMenuVO;
import fscms.mods.site.util.SiteHelper;

@Component
public class PageDecoratorMapper extends AbstractDecoratorMapper {
	
	static Logger logger = LogManager.getLogger(PageDecoratorMapper.class);
	
	private List<String> pageProps;
	
	private static MngrMenuHelper mngrMenuHelper;
	
	private static SiteHelper siteHelper;
	
	public PageDecoratorMapper() {
		pageProps = null;
	}
	
	@Autowired
	public void setMngrMenuHelper(MngrMenuHelper _mngrMenuHelper) {
		mngrMenuHelper = _mngrMenuHelper;
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
			PropConnHelper propConnHelper = new PropConnHelper();
			
			// 사이트 정보
			try {
				request.setAttribute("siteSession", siteHelper.getSite());
			} catch (IOException | ParseException e) {
				logger.error("사이트정보가 없습니다." + e.getMessage());
				throw new WrongApproachException("D0011");
			}
			
			if(StringUtils.equals(decorator.getName(), "adm_template")) {
				// 사이트 관리자
				String key = request.getParameter("key");
				
				// 현재 메뉴 정보
				MngrMenuVO menu = mngrMenuHelper.getJson(key);
				request.setAttribute("currentMenu", menu);
				
				// 관리자 기본경로
				try {
					String admPath = propConnHelper.getConn("fs_config", "Fs_config.admUrl");
					request.setAttribute("admPath", admPath);
				} catch (IOException e) {
					logger.error("설정 파일이 없습니다." + e.getMessage());
					throw new WrongApproachException("B0001");
				}
			} else {
			}
		}
		
		return decorator;
	}
	
	private Decorator getByProperty(HttpServletRequest request, Page page, String name) {
		return (page.isPropertySet(name))? getNamedDecorator(request, page.getProperty(name)) : null;
	}

}
