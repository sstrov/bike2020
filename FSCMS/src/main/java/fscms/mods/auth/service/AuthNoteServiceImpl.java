package fscms.mods.auth.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.auth.vo.AuthNoteSearchVO;
import fscms.mods.auth.vo.AuthNoteVO;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.user.service.UserMapper;
import fscms.mods.user.vo.UserVO;
import net.sf.json.JSONArray;

@Service("authNoteService")
public class AuthNoteServiceImpl extends EgovAbstractServiceImpl implements AuthNoteService {

	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;
	
	@Resource(name = "authNoteMapper")
	private AuthNoteMapper authNoteDAO;
	
	@Resource(name = "userMapper")
	private UserMapper userDAO;

	@Override
	public AuthNoteVO selectObj(AuthNoteVO vo) throws Exception {
		return authNoteDAO.selectObj(vo);
	}

	@Override
	public void deleteAuthNote(AuthNoteVO vo) throws Exception {
		authNoteDAO.deleteAuthNote(vo);
	}

	@Override
	public List<AuthNoteVO> checkNoteSn(AuthNoteSearchVO searchVO) throws Exception {
		return authNoteDAO.checkNoteSn(searchVO);
	}

	@Override
	public List<AuthNoteVO> selectNoteList(AuthNoteSearchVO searchVO) throws Exception {
		return authNoteDAO.selectNoteList(searchVO);
	}


	@Override
	public void insertNote(HttpServletRequest request, String bfData, AuthNoteVO vo, String mngrId) throws Exception {
		authNoteDAO.insertNote(vo);
		
		AuthNoteSearchVO searchVO = new AuthNoteSearchVO();
		searchVO.setUserSn(vo.getUserSn());
		
		List<AuthNoteVO> itemList = this.selectNoteList(searchVO);
		
		dataChghstHelper.setInsert(request, "FS_NOTE_LIST", bfData, JSONArray.fromObject(itemList).toString(), null, mngrId);
	}

	@Override
	public void updateAuthNote(HttpServletRequest request, String bfData, AuthNoteVO vo, String mngrId)
			throws Exception {
		
		UserVO userVO = new UserVO();
		userVO.setUserSn(vo.getUserSn());
		userVO.setNoteSn(vo.getNoteSn());
		
		authNoteDAO.updateAuthNote(vo);
		
		AuthNoteSearchVO searchVO = new AuthNoteSearchVO();
		searchVO.setUserSn(vo.getUserSn());
		
		AuthNoteVO item = this.selectObj(searchVO);
		
		dataChghstHelper.setInsert(request, "FS_NOTE_LIST", bfData, JSONArray.fromObject(item).toString(), null, mngrId);
		
		System.out.println("impl-------------------------------");
		System.out.println(userVO.getUserSn());
		System.out.println(userVO.getNoteSn());
		bfData = JSONArray.fromObject(userDAO.selectObj(userVO)).toString();
		
		System.out.println("2222-------------------------------");
		System.out.println(userVO.getUserSn());
		System.out.println(userVO.getNoteSn());
		if(vo.getAuthYn().equals("Y")) {
			userDAO.updateInfo(userVO);
			
			String afData = JSONArray.fromObject(userDAO.selectObj(userVO)).toString();
			
			dataChghstHelper.setInsert(request, "FS_USER", bfData, afData, null, mngrId);
		}
		
	}
}
