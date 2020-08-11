package fscms.mods.banner.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.banner.vo.BannerSearchVO;
import fscms.mods.banner.vo.BannerVO;

@Mapper("bannerMapper")
public interface BannerMapper {
	
	BannerVO selectObj(BannerVO vo) throws Exception;
	
	List<BannerVO> selectList(BannerVO vo) throws Exception;
	
	int selectTCount(BannerSearchVO searchVO) throws Exception;
	
	List<BannerVO> selectPageList(BannerSearchVO searchVO) throws Exception;
	
	void insertInfo(BannerVO vo) throws Exception;
	
	void updateInfo(BannerVO vo) throws Exception;
	
	void updateOrder(BannerVO vo) throws Exception;
	
	void deleteInfo(BannerVO vo) throws Exception;

}
