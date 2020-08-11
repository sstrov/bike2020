package fscms.mods.app.service;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.app.vo.AppVersionVO;

@Mapper("appVersionMapper")
public interface AppVersionMapper {

	AppVersionVO selectObj(AppVersionVO vo) throws Exception;

	void updateAppVersionInfo(AppVersionVO vo) throws Exception;

	
}
