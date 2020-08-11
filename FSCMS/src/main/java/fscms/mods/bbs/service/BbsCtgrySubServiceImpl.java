package fscms.mods.bbs.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.bbs.vo.BbsCtgrySubVO;
import fscms.mods.bbs.vo.BbsCtgryVO;
import fscms.mods.data.util.DataChghstHelper;
import net.sf.json.JSONArray;

@Service("bbsCtgrySubService")
public class BbsCtgrySubServiceImpl extends EgovAbstractServiceImpl implements BbsCtgrySubService {
	
	@Resource(name = "bbsCtgrySubMapper")
	private BbsCtgrySubMapper bbsCtgrySubDAO;
	
	@Resource(name = "bbsCtgrySubService")
	private BbsCtgrySubService bbsCtgrySubService;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public BbsCtgrySubVO selectObj(BbsCtgrySubVO vo) throws Exception {
		return bbsCtgrySubDAO.selectObj(vo);
	}

	@Override
	public List<BbsCtgrySubVO> selectList(BbsCtgrySubVO vo) throws Exception {
		return bbsCtgrySubDAO.selectList(vo);
	}

	@Override
	public void insertInfo(HttpServletRequest request, BbsCtgrySubVO vo, String mngrId) throws Exception {
		bbsCtgrySubDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_BBS_CTGRY_SUB", null, this.getData(vo.getBbsCtgrySn()), null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, BbsCtgrySubVO vo, String mngrId) throws Exception {
		bbsCtgrySubDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BBS_CTGRY_SUB", bfData, this.getData(vo.getBbsCtgrySn()), null, mngrId);
		}
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, BbsCtgrySubVO vo, String mngrId) throws Exception {
		bbsCtgrySubDAO.deleteInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BBS_CTGRY", bfData, null, null, mngrId);
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		BbsCtgrySubVO bbsCtgrySubVO = new BbsCtgrySubVO();
		bbsCtgrySubVO.setBbsCtgrySubSn(key);
		BbsCtgrySubVO item = this.selectObj(bbsCtgrySubVO);
		
		if(item != null) {
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}
	
	/**
	 * @throws Exception 
	 * @Method Name	: setCategory
	 * @작성일		: 2019. 11. 30.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 카테고리 정보 저장
	 */
	private void setCategory(HttpServletRequest request, int bbsCtgrySn, String mngrId) throws Exception {
		String[] bbsCtgrySubSnList = request.getParameterValues("bbsCtgrySubSn");
		String[] ctgrySubNmSList    = request.getParameterValues("ctgrySubNm");
		
		if(ctgrySubNmSList != null && ctgrySubNmSList.length > 0) {
			// 등록될 데이터 이외의 모든 정보삭제
			BbsCtgrySubVO bbsCtgrySubVO = new BbsCtgrySubVO();
			bbsCtgrySubVO.setBbsCtgrySn(bbsCtgrySn);
			bbsCtgrySubVO.setSc_keyArr(bbsCtgrySubSnList);
			List<BbsCtgrySubVO> itemList = bbsCtgrySubService.selectList(bbsCtgrySubVO);
			
			System.out.println("11111111111111111111111111");
			System.out.println("11111111111111111111111111");
			
			if(itemList != null && itemList.size() > 0) {
				System.out.println("ddddddddddddddddddddddddddddddddddddd");
				for (BbsCtgrySubVO item : itemList) {
					String bfData = JSONArray.fromObject(item).toString();
					bbsCtgrySubService.deleteInfo(request, bfData, item, mngrId);
				}
			}
			
			for(int i=0; i<ctgrySubNmSList.length; i++) {
				String bbsCtgrySubSn = bbsCtgrySubSnList[i];
				String ctgrySubNm    = ctgrySubNmSList[i];
				
				if(StringUtils.isEmpty(ctgrySubNm) || StringUtils.equals(ctgrySubNm, "{name}")) {
					continue;
				}
				
				bbsCtgrySubVO = new BbsCtgrySubVO();
				bbsCtgrySubVO.setBbsCtgrySn(bbsCtgrySn);
				bbsCtgrySubVO.setCtgrySubNm(ctgrySubNm);
				
				if(StringUtils.isNotEmpty(bbsCtgrySubSn+"")) {
					bbsCtgrySubVO.setBbsCtgrySubSn(Integer.parseInt(bbsCtgrySubSn));
					BbsCtgrySubVO cate = bbsCtgrySubService.selectObj(bbsCtgrySubVO);
					System.out.println("22222222222222222222222222222");
					System.out.println("22222222222222222222222222222");
					if(cate != null) {
						System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
						System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
						String bfData = JSONArray.fromObject(cate).toString();
						bbsCtgrySubService.updateInfo(request, bfData, bbsCtgrySubVO, mngrId);
					}
				} else {
					bbsCtgrySubService.insertInfo(request, bbsCtgrySubVO, mngrId);
				}
			}
		}
	}

	@Override
	public void insertSubCtgry(HttpServletRequest request, BbsCtgrySubVO cvo, String mngrId) throws Exception {
		this.setCategory(request, cvo.getBbsCtgrySn(), mngrId);
		
	}

	@Override
	public void updateSubCtgry(HttpServletRequest request, BbsCtgrySubVO cvo, String mngrId) throws Exception {
		System.out.println("333333333333333333333333333333333333");
		System.out.println("333333333333333333333333333333333333");
		
		this.setCategory(request, cvo.getBbsCtgrySn(), mngrId);
		
		System.out.println("4444444444444444444444444444444444444");
		System.out.println("4444444444444444444444444444444444444");
	}
	
	
}
