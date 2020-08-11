package fscms.mods.banner.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.banner.vo.BannerVO;

@Service("bannerService")
public class BannerServiceImpl extends EgovAbstractServiceImpl implements BannerService {
	
	static Logger logger = LogManager.getLogger(BannerServiceImpl.class);
	
	@Resource(name = "bannerMapper")
	private BannerMapper bannerDAO;
	
	@Override
	public BannerVO selectObj(BannerVO vo) throws Exception {
		return bannerDAO.selectObj(vo);
	}

	@Override
	public List<BannerVO> selectList(BannerVO vo) throws Exception {
		return bannerDAO.selectList(vo);
	}

}
