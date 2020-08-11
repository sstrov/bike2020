package fscms.mods.site.service;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.site.vo.SiteVO;

@Mapper("siteMapper")
public interface SiteMapper {

	SiteVO selectObj() throws Exception;

}
