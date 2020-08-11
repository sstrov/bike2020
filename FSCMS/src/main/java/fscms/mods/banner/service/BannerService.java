package fscms.mods.banner.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import fscms.mods.banner.vo.BannerSearchVO;
import fscms.mods.banner.vo.BannerVO;

public interface BannerService {
	
	BannerVO selectObj(BannerVO vo) throws Exception;
	
	List<BannerVO> selectList(BannerVO vo) throws Exception;
	
	int selectTCount(BannerSearchVO searchVO) throws Exception;
	
	List<BannerVO> selectPageList(BannerSearchVO searchVO) throws Exception;
	
	void insertInfo(MultipartHttpServletRequest request, BannerVO vo, String mngrId) throws Exception;
	
	void updateInfo(MultipartHttpServletRequest request, String bfData, BannerVO vo, String mngrId) throws Exception;
	
	void updateInfo(HttpServletRequest request, String bfData, BannerVO vo, String mngrId) throws Exception;
	
	void updateOrder(BannerVO vo) throws Exception;
	
	void deleteInfo(HttpServletRequest request, String bfData, BannerVO vo, String mngrId) throws Exception;

}
