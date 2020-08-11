package fscms.mods.banner.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fscms.mods.banner.vo.BannerEstbsSearchVO;
import fscms.mods.banner.vo.BannerEstbsVO;

@Mapper("bannerEstbsMapper")
public interface BannerEstbsMapper {
	
	BannerEstbsVO selectObj(BannerEstbsVO vo) throws Exception;
	
	int selectTCount(BannerEstbsSearchVO searchVO) throws Exception;
	
	List<BannerEstbsVO> selectPageList(BannerEstbsSearchVO searchVO) throws Exception;
	
	void insertInfo(BannerEstbsVO vo) throws Exception;
	
	void updateInfo(BannerEstbsVO vo) throws Exception;
	
	void deleteInfo(BannerEstbsVO vo) throws Exception;

}
