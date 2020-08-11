package fscms.mods.banner.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.banner.vo.BannerVO;

@Mapper("bannerMapper")
public interface BannerMapper {
	
	BannerVO selectObj(BannerVO vo) throws Exception;
	
	List<BannerVO> selectList(BannerVO vo) throws Exception;
	
}
