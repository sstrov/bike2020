package fscms.mods.bbs.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.bbs.vo.BbsSknVO;
import fscms.mods.data.util.DataChghstHelper;
import net.sf.json.JSONArray;

@Service("bbsSknService")
public class BbsSknServiceImpl extends EgovAbstractServiceImpl implements BbsSknService {
	
	@Resource(name = "bbsSknMapper")
	private BbsSknMapper bbsSknDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public BbsSknVO selectObj(BbsSknVO vo) throws Exception {
		return bbsSknDAO.selectObj(vo);
	}

	@Override
	public List<BbsSknVO> selectList(BbsSknVO vo) throws Exception {
		return bbsSknDAO.selectList(vo);
	}

	@Override
	public List<BbsSknVO> selectList_C(BbsSknVO vo) throws Exception {
		return bbsSknDAO.selectList_C(vo);
	}
	
	@Override
	public int selectMaxKey() throws Exception {
		return bbsSknDAO.selectMaxKey();
	}

	@Override
	public int selectMaxO(BbsSknVO vo) throws Exception {
		return bbsSknDAO.selectMaxO(vo);
	}

	@Override
	public void insertInfo(HttpServletRequest request, BbsSknVO vo, String mngrId) throws Exception {
		bbsSknDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_BBS_SKN", null, this.getData(vo.getBbsSknSn()), null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, BbsSknVO vo, String mngrId) throws Exception {
		bbsSknDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BBS_SKN", bfData, this.getData(vo.getBbsSknSn()), null, mngrId);
		}
	}

	@Override
	public void deleteInfo(HttpServletRequest request, BbsSknVO vo, String mngrId) throws Exception {
		
		BbsSknVO bbsSknVO = new BbsSknVO();
		bbsSknVO.setBbsSknUpperSn(vo.getBbsSknSn());
		List<BbsSknVO> childList = this.selectList(bbsSknVO);
		
		if(childList != null && childList.size() > 0) {
			// 하위 정보 삭제
			this.deleteChildren(request, childList, mngrId);
		}
		
		this.deleteProc(request, vo.getBbsSknSn(), mngrId);
	}
	
	/**
	 * @Method Name	: deleteChildren
	 * @작성일		: 2019. 11. 8.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 하위 목록 조회 후 삭제
	 */
	private void deleteChildren(HttpServletRequest request, List<BbsSknVO> menuList, String mngrId) throws Exception {
		
		if(menuList != null && menuList.size() > 0) {
			for (BbsSknVO item : menuList) {
				BbsSknVO bbsSknVO = new BbsSknVO();
				bbsSknVO.setBbsSknUpperSn(item.getBbsSknSn());
				List<BbsSknVO> childList = this.selectList(bbsSknVO);
				
				if(childList != null && childList.size() > 0)  {
					// 하위 정보 삭제
					this.deleteChildren(request, childList, mngrId);
				}
				
				this.deleteProc(request, item.getBbsSknSn(), mngrId);
			}
		}
	}
	
	/**
	 * @Method Name	: deleteProc
	 * @작성일		: 2019. 11. 8.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 정보 삭제
	 */
	private void deleteProc(HttpServletRequest request, int bbsSknSn, String mngrId) throws Exception {
		
		BbsSknVO bbsSknVO = new BbsSknVO();
		bbsSknVO.setBbsSknSn(bbsSknSn);
		BbsSknVO item = this.selectObj(bbsSknVO);
		
		if(item != null) {
			// 정보 삭제
			bbsSknDAO.deleteInfo(item);
			
			item.setUpdtDe(null);
			String bfData = JSONArray.fromObject(item).toString();
			dataChghstHelper.setInsert(request, "FS_BBS_SKN", bfData, null, null, mngrId);
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		BbsSknVO bbsSknVO = new BbsSknVO();
		bbsSknVO.setBbsSknSn(key);
		BbsSknVO item = this.selectObj(bbsSknVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
