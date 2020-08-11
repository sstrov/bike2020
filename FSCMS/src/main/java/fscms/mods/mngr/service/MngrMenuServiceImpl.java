package fscms.mods.mngr.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.mngr.vo.MngrMenuAccVO;
import fscms.mods.mngr.vo.MngrMenuVO;
import net.sf.json.JSONArray;

@Service("mngrMenuService")
public class MngrMenuServiceImpl extends EgovAbstractServiceImpl implements MngrMenuService {
	
	@Resource(name = "mngrMenuMapper")
	private MngrMenuMapper mngrMenuDAO;
	
	@Resource(name = "mngrMenuAccService")
	private MngrMenuAccService mngrMenuAccService;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public MngrMenuVO selectObj(MngrMenuVO vo) throws Exception {
		return mngrMenuDAO.selectObj(vo);
	}

	@Override
	public List<MngrMenuVO> selectList(MngrMenuVO vo) throws Exception {
		return mngrMenuDAO.selectList(vo);
	}

	@Override
	public List<MngrMenuVO> selectList_C(MngrMenuVO vo) throws Exception {
		return mngrMenuDAO.selectList_C(vo);
	}

	@Override
	public List<MngrMenuVO> selectList_acc(MngrMenuVO vo) throws Exception {
		return mngrMenuDAO.selectList_acc(vo);
	}

	@Override
	public int selectMaxO(MngrMenuVO vo) throws Exception {
		return mngrMenuDAO.selectMaxO(vo);
	}

	@Override
	public void insertInfo(HttpServletRequest request, MngrMenuVO vo, String mngrId) throws Exception {
		mngrMenuDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_MNGR_MENU", null, this.getData(vo.getMenuSn()), null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, MngrMenuVO vo, String mngrId)
			throws Exception {
		mngrMenuDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_MNGR_MENU", bfData, this.getData(vo.getMenuSn()), null, mngrId);
		}
	}

	@Override
	public void deleteInfo(HttpServletRequest request, MngrMenuVO vo, String mngrId)
			throws Exception {
		
		MngrMenuVO mngrMenuVO = new MngrMenuVO();
		mngrMenuVO.setMenuUpperSn(vo.getMenuSn());
		List<MngrMenuVO> childList = this.selectList(mngrMenuVO);
		
		if(childList != null && childList.size() > 0)  {
			// 하위 정보 삭제
			this.deleteChildren(request, childList, mngrId);
		}
		
		this.deleteProc(request, vo.getMenuSn(), mngrId);
	}
	
	/**
	 * @Method Name	: deleteChildren
	 * @작성일		: 2019. 10. 7.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 하위 목록 조회 후 삭제
	 */
	private void deleteChildren(HttpServletRequest request, List<MngrMenuVO> menuList, String mngrId) throws Exception {
		
		if(menuList != null && menuList.size() > 0) {
			for (MngrMenuVO item : menuList) {
				MngrMenuVO mngrMenuVO = new MngrMenuVO();
				mngrMenuVO.setMenuUpperSn(item.getMenuSn());
				List<MngrMenuVO> childList = this.selectList(mngrMenuVO);
				
				if(childList != null && childList.size() > 0)  {
					// 하위 정보 삭제
					this.deleteChildren(request, childList, mngrId);
				}
				
				this.deleteProc(request, item.getMenuSn(), mngrId);
			}
		}
	}
	
	/**
	 * @Method Name	: deleteProc
	 * @작성일		: 2019. 10. 7.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 정보 삭제
	 */
	private void deleteProc(HttpServletRequest request, String menuSn, String mngrId) throws Exception {
		
		MngrMenuVO mngrMenuVO = new MngrMenuVO();
		mngrMenuVO.setMenuSn(menuSn);
		MngrMenuVO item = this.selectObj(mngrMenuVO);
		
		if(item != null) {
			// 메뉴 연결 정보 삭제
			MngrMenuAccVO mngrMenuAccVO = new MngrMenuAccVO();
			mngrMenuAccVO.setMenuSn(item.getMenuSn());
			List<MngrMenuAccVO> accList = mngrMenuAccService.selectList(mngrMenuAccVO);
			
			if(accList != null && accList.size() > 0) {
				for (MngrMenuAccVO acc : accList) {
					String bfData = JSONArray.fromObject(acc).toString();
					mngrMenuAccService.deleteInfo(request, bfData, acc, mngrId);
				}
			}
			
			// 연결 역할 삭제 예정
			mngrMenuDAO.deleteInfo(item);
			
			item.setUpdtDe(null);
			String bfData = JSONArray.fromObject(item).toString();
			dataChghstHelper.setInsert(request, "FS_MNGR_MENU", bfData, null, null, mngrId);
		}
	}
	
	private String getData(String key) throws Exception {
		String rtnData = null;
		
		MngrMenuVO mngrMenuVO = new MngrMenuVO();
		mngrMenuVO.setMenuSn(key);
		MngrMenuVO item = this.selectObj(mngrMenuVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
