package fscms.mods.banner.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.banner.vo.BannerEstbsSearchVO;
import fscms.mods.banner.vo.BannerEstbsVO;
import fscms.mods.banner.vo.BannerVO;
import fscms.mods.data.util.DataChghstHelper;
import net.sf.json.JSONArray;

@Service("bannerEstbsService")
public class BannerEstbsServiceImpl extends EgovAbstractServiceImpl implements BannerEstbsService {
	
	@Resource(name = "bannerEstbsMapper")
	private BannerEstbsMapper bannerEstbsDAO;
	
	@Resource(name = "bannerService")
	private BannerService bannerService;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public BannerEstbsVO selectObj(BannerEstbsVO vo) throws Exception {
		return bannerEstbsDAO.selectObj(vo);
	}

	@Override
	public int selectTCount(BannerEstbsSearchVO searchVO) throws Exception {
		return bannerEstbsDAO.selectTCount(searchVO);
	}

	@Override
	public List<BannerEstbsVO> selectPageList(BannerEstbsSearchVO searchVO) throws Exception {
		return bannerEstbsDAO.selectPageList(searchVO);
	}

	@Override
	public void insertInfo(HttpServletRequest request, BannerEstbsVO vo, String mngrId) throws Exception {
		if(StringUtils.isEmpty(vo.getOptnTagAt())) {
			vo.setOptnTagAt("N");
		}
		if(StringUtils.isEmpty(vo.getOptnImageAt())) {
			vo.setOptnImageAt("N");
		}
		if(StringUtils.isEmpty(vo.getOptnBcrnImageAt())) {
			vo.setOptnBcrnImageAt("N");
		}
		if(StringUtils.isEmpty(vo.getOptnClassAt())) {
			vo.setOptnClassAt("N");
		}
		bannerEstbsDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_BANNER_ESTBS", null, this.getData(vo.getBannerEstbsSn()), null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, BannerEstbsVO vo, String mngrId)
			throws Exception {
		if(StringUtils.isEmpty(vo.getOptnTagAt())) {
			vo.setOptnTagAt("N");
		}
		if(StringUtils.isEmpty(vo.getOptnImageAt())) {
			vo.setOptnImageAt("N");
		}
		if(StringUtils.isEmpty(vo.getOptnBcrnImageAt())) {
			vo.setOptnBcrnImageAt("N");
		}
		if(StringUtils.isEmpty(vo.getOptnClassAt())) {
			vo.setOptnClassAt("N");
		}
		bannerEstbsDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BANNER_ESTBS", bfData, this.getData(vo.getBannerEstbsSn()), null, mngrId);
		}
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, BannerEstbsVO vo, String mngrId)
			throws Exception {
		// 배너 정보 삭제
		BannerVO bannerVO = new BannerVO();
		bannerVO.setBannerEstbsSn(vo.getBannerEstbsSn());
		List<BannerVO> bannerList = bannerService.selectList(bannerVO);
		
		if(bannerList != null && bannerList.size() > 0) {
			for (BannerVO banner : bannerList) {
				banner.setUpdtDe(null);
				String bfData_banner = JSONArray.fromObject(banner).toString();
				bannerService.deleteInfo(request, bfData_banner, banner, mngrId);
			}
		}
		
		bannerEstbsDAO.deleteInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BANNER_ESTBS", bfData, null, null, mngrId);
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		BannerEstbsVO bannerEstbsVO = new BannerEstbsVO();
		bannerEstbsVO.setBannerEstbsSn(key);
		BannerEstbsVO item = this.selectObj(bannerEstbsVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
