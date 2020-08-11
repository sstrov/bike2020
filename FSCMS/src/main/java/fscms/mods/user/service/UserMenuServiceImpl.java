package fscms.mods.user.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.cmm.util.FsFuncCmmHelper;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.user.util.UserMenuHelper;
import fscms.mods.user.vo.UserMenuAccVO;
import fscms.mods.user.vo.UserMenuVO;
import net.sf.json.JSONArray;

@Service("userMenuService")
public class UserMenuServiceImpl extends EgovAbstractServiceImpl implements UserMenuService {
	
	@Resource(name = "userMenuMapper")
	private UserMenuMapper userMenuDAO;
	
	@Resource(name = "userMenuHelper")
	private UserMenuHelper userMenuHelper;
	
	@Resource(name = "userMenuAccService")
	private UserMenuAccService userMenuAccService;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public UserMenuVO selectObj(UserMenuVO vo) throws Exception {
		return userMenuDAO.selectObj(vo);
	}

	@Override
	public List<UserMenuVO> selectList(UserMenuVO vo) throws Exception {
		return userMenuDAO.selectList(vo);
	}

	@Override
	public List<UserMenuVO> selectList_C(UserMenuVO vo) throws Exception {
		return userMenuDAO.selectList_C(vo);
	}

	@Override
	public List<UserMenuVO> selectList_acc(UserMenuVO vo) throws Exception {
		return userMenuDAO.selectList_acc(vo);
	}

	@Override
	public int selectMaxO(UserMenuVO vo) throws Exception {
		return userMenuDAO.selectMaxO(vo);
	}
	
	@Override
	public int selectMaxG(UserMenuVO vo) throws Exception {
		return userMenuDAO.selectMaxG(vo);
	}

	@Override
	public void insertInfo(HttpServletRequest request, UserMenuVO vo, String mngrId) throws Exception {
		userMenuDAO.insertInfo(vo);
		
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		// 콘텐츠 파일 생성
		String rootPath = fsFuncCmmHelper.getAppAbsolutePath() + "/repository/www/content";
		
		File deleteFolder = new File(rootPath);
		if(!deleteFolder.exists()) {
			// root 경로가 없으면 생성
			deleteFolder.mkdirs();
		}
		
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(fsFuncCmmHelper.setFileWriter(rootPath + "/" + vo.getMenuSn() + ".jsp"));
			bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n\n<div style=\"text-align:center\"><img src=\"/resource/bike/img/img_ready1.gif\" alt=\"콘텐츠 준비중입니다.\" /></div>");
			bw.close();
		} finally {
			bw.close();
		}
		
		dataChghstHelper.setInsert(request, "FS_USER_MENU", null, this.getData(vo.getMenuSn()), null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, UserMenuVO vo, String mngrId)
			throws Exception {
		userMenuDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_USER_MENU", bfData, this.getData(vo.getMenuSn()), null, mngrId);
		}
	}

	@Override
	public void deleteInfo(HttpServletRequest request, UserMenuVO vo, String mngrId)
			throws Exception {
		
		UserMenuVO userMenuVO = new UserMenuVO();
		userMenuVO.setMenuUpperSn(vo.getMenuSn());
		List<UserMenuVO> childList = this.selectList(userMenuVO);
		
		if(childList != null && childList.size() > 0)  {
			// 하위 정보 삭제
			this.deleteChildren(request, childList, mngrId);
		}
		
		this.deleteProc(request, vo.getMenuSn(), mngrId);
	}
	
	/**
	 * @throws IOException 
	 * @Method Name	: deleteChildren
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 하위 목록 조회 후 삭제
	 */
	private void deleteChildren(HttpServletRequest request, List<UserMenuVO> menuList, String mngrId) throws Exception {
		
		if(menuList != null && menuList.size() > 0) {
			for (UserMenuVO item : menuList) {
				UserMenuVO userMenuVO = new UserMenuVO();
				userMenuVO.setMenuUpperSn(item.getMenuSn());
				List<UserMenuVO> childList = this.selectList(userMenuVO);
				
				if(childList != null && childList.size() > 0)  {
					// 하위 정보 삭제
					this.deleteChildren(request, childList, mngrId);
				}
				
				this.deleteProc(request, item.getMenuSn(), mngrId);
			}
		}
	}
	
	/**
	 * @throws IOException 
	 * @Method Name	: deleteProc
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 정보 삭제
	 */
	private void deleteProc(HttpServletRequest request, String menuSn, String mngrId) throws Exception {
		
		UserMenuVO userMenuVO = new UserMenuVO();
		userMenuVO.setMenuSn(menuSn);
		UserMenuVO item = this.selectObj(userMenuVO);
		
		if(item != null) {
			// 메뉴 연결 정보 삭제
			UserMenuAccVO userMenuAccVO = new UserMenuAccVO();
			userMenuAccVO.setMenuSn(item.getMenuSn());
			List<UserMenuAccVO> accList = userMenuAccService.selectList(userMenuAccVO);
			
			if(accList != null && accList.size() > 0) {
				for (UserMenuAccVO acc : accList) {
					String bfData = JSONArray.fromObject(acc).toString();
					userMenuAccService.deleteInfo(request, bfData, acc, mngrId);
				}
			}
			
			// 첨부 파일 삭제
			userMenuHelper.deleteFile(item.getMenuSn());
			
			// 연결 역할 삭제 예정
			userMenuDAO.deleteInfo(item);
			
			item.setUpdtDe(null);
			String bfData = JSONArray.fromObject(item).toString();
			dataChghstHelper.setInsert(request, "FS_USER_MENU", bfData, null, null, mngrId);
		}
	}
	
	private String getData(String key) throws Exception {
		String rtnData = null;
		
		UserMenuVO userMenuVO = new UserMenuVO();
		userMenuVO.setMenuSn(key);
		UserMenuVO item = this.selectObj(userMenuVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
