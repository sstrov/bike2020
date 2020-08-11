package fscms.mods.banner.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fscms.mods.banner.vo.BannerEstbsSearchVO;
import fscms.mods.banner.vo.BannerEstbsVO;

public interface BannerEstbsService {
	
	BannerEstbsVO selectObj(BannerEstbsVO vo) throws Exception;
	
	int selectTCount(BannerEstbsSearchVO searchVO) throws Exception;
	
	List<BannerEstbsVO> selectPageList(BannerEstbsSearchVO searchVO) throws Exception;
	
	void insertInfo(HttpServletRequest request, BannerEstbsVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, BannerEstbsVO vo, String mngrId) throws Exception;
	
	void deleteInfo(HttpServletRequest request, String bfData, BannerEstbsVO vo, String mngrId) throws Exception;

}
