package fscms.mods.banner.service;

import java.util.List;

import fscms.mods.banner.vo.BannerVO;

public interface BannerService {
	
	BannerVO selectObj(BannerVO vo) throws Exception;
	
	List<BannerVO> selectList(BannerVO vo) throws Exception;
	
}
