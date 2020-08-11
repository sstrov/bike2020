package fscms.mods.bbs.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.bbs.vo.BbsCtgryVO;
import fscms.mods.bbs.vo.BbsEstbsSearchVO;
import fscms.mods.bbs.vo.BbsEstbsVO;
import fscms.mods.bbs.vo.BbsFieldVO;
import fscms.mods.bbs.vo.BbsRoleVO;
import fscms.mods.bbs.vo.BbsSearchVO;
import fscms.mods.bbs.vo.BbsThumbVO;
import fscms.mods.bbs.vo.BbsVO;
import fscms.mods.data.util.DataChghstHelper;
import net.sf.json.JSONArray;

@Service("bbsEstbsService")
public class BbsEstbsServiceImpl extends EgovAbstractServiceImpl implements BbsEstbsService {
	
	@Resource(name = "bbsEstbsMapper")
	private BbsEstbsMapper bbsEstbsDAO;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;
	
	@Resource(name = "bbsService")
	private BbsService bbsService;
	
	@Resource(name = "bbsCtgryService")
	private BbsCtgryService bbsCtgryService;
	
	@Resource(name = "bbsFieldService")
	private BbsFieldService bbsFieldService;
	
	@Resource(name = "bbsRoleService")
	private BbsRoleService bbsRoleService;
	
	@Resource(name = "bbsThumbService")
	private BbsThumbService bbsThumbService;

	@Override
	public BbsEstbsVO selectObj(BbsEstbsVO vo) throws Exception {
		return bbsEstbsDAO.selectObj(vo);
	}

	@Override
	public int selectTCount(BbsEstbsSearchVO searchVO) throws Exception {
		return bbsEstbsDAO.selectTCount(searchVO);
	}

	@Override
	public List<BbsEstbsVO> selectPageList(BbsEstbsSearchVO searchVO) throws Exception {
		return bbsEstbsDAO.selectPageList(searchVO);
	}

	@Override
	public void insertInfo(HttpServletRequest request, BbsEstbsVO vo, String mngrId) throws Exception {
		bbsEstbsDAO.insertInfo(vo);
		
		dataChghstHelper.setInsert(request, "FS_BBS_ESTBS", null, this.getData(vo.getBbsEstbsSn()), null, mngrId);
		
		this.setThumb(request, vo.getBbsEstbsSn(), mngrId);		// 썸네일 정보 저장
		this.setField(request, vo.getBbsEstbsSn(), mngrId);		// 필드 정보 저장
		this.setFieldOrderList(request, vo.getBbsEstbsSn());	// 필드 설정 목록 순서 변경
		this.setFieldOrderView(request, vo.getBbsEstbsSn());	// 필드 설정 보기 순서 변경
		this.setCategory(request, vo.getBbsEstbsSn(), mngrId);	// 카테고리 정보 저장
		this.setRole(request, vo.getBbsEstbsSn(), mngrId);		// 역할 정보 저장
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, BbsEstbsVO vo, String mngrId) throws Exception {
		bbsEstbsDAO.updateInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BBS_ESTBS", bfData, this.getData(vo.getBbsEstbsSn()), null, mngrId);
		}
		
		this.setThumb(request, vo.getBbsEstbsSn(), mngrId);		// 썸네일 정보 저장
		this.setField(request, vo.getBbsEstbsSn(), mngrId);		// 필드 정보 저장
		this.setFieldOrderList(request, vo.getBbsEstbsSn());	// 필드 설정 목록 순서 변경
		this.setFieldOrderView(request, vo.getBbsEstbsSn());	// 필드 설정 보기 순서 변경
		this.setCategory(request, vo.getBbsEstbsSn(), mngrId);	// 카테고리 정보 저장
		this.setRole(request, vo.getBbsEstbsSn(), mngrId);		// 역할 정보 저장
	}

	@Override
	public void deleteInfo(HttpServletRequest request, String bfData, BbsEstbsVO vo, String mngrId) throws Exception {
		
		// 게시판 정보삭제
		BbsSearchVO bbsSearchVO = new BbsSearchVO();
		bbsSearchVO.setBbsEstbsSn(vo.getBbsEstbsSn());
		List<BbsVO> bbsList = bbsService.selectList(bbsSearchVO);
		
		if(bbsList != null && bbsList.size() > 0) {
			for (BbsVO bbs : bbsList) {
				bbsService.deleteInfo(request, bbs, mngrId);
			}
		}
		
		// 카테고리 정보삭제
		BbsCtgryVO bbsCtgryVO = new BbsCtgryVO();
		bbsCtgryVO.setBbsEstbsSn(vo.getBbsEstbsSn());
		List<BbsCtgryVO> ctgryList = bbsCtgryService.selectList(bbsCtgryVO);
		
		if(ctgryList != null && ctgryList.size() > 0) {
			for (BbsCtgryVO ctgry : ctgryList) {
				String bfData_ctgry = JSONArray.fromObject(ctgry).toString();
				bbsCtgryService.deleteInfo(request, bfData_ctgry, ctgry, mngrId);
			}
		}
		
		// 필드 정보삭제
		BbsFieldVO bbsFieldVO = new BbsFieldVO();
		bbsFieldVO.setBbsEstbsSn(vo.getBbsEstbsSn());
		List<BbsFieldVO> fieldList = bbsFieldService.selectList(bbsFieldVO);
		
		if(fieldList != null && fieldList.size() > 0) {
			for (BbsFieldVO field : fieldList) {
				String bfData_field = JSONArray.fromObject(field).toString();
				bbsFieldService.deleteInfo(request, bfData_field, field, mngrId);
			}
		}
		
		// 역할 정보삭제
		BbsRoleVO bbsRoleVO = new BbsRoleVO();
		bbsRoleVO.setBbsEstbsSn(vo.getBbsEstbsSn());
		List<BbsRoleVO> roleList = bbsRoleService.selectList(bbsRoleVO);
		
		if(roleList != null && roleList.size() > 0) {
			for (BbsRoleVO role : roleList) {
				String bfData_role = JSONArray.fromObject(role).toString();
				bbsRoleService.deleteInfo(request, bfData_role, role, mngrId);
			}
		}
		
		// 썸네일 정보삭제
		BbsThumbVO bbsThumbVO = new BbsThumbVO();
		bbsThumbVO.setBbsEstbsSn(vo.getBbsEstbsSn());
		List<BbsThumbVO> thumbList = bbsThumbService.selectList(bbsThumbVO);
		
		if(thumbList != null && thumbList.size() > 0) {
			for (BbsThumbVO thumb : thumbList) {
				String bfData_thumb = JSONArray.fromObject(thumb).toString();
				bbsThumbService.deleteInfo(request, bfData_thumb, thumb, mngrId);
			}
		}
		
		bbsEstbsDAO.deleteInfo(vo);
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_BBS_ESTBS", bfData, null, null, mngrId);
		}
	}
	
	private String getData(int key) throws Exception {
		String rtnData = null;
		
		BbsEstbsVO bbsEstbsVO = new BbsEstbsVO();
		bbsEstbsVO.setBbsEstbsSn(key);
		BbsEstbsVO item = this.selectObj(bbsEstbsVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}
	
	/**
	 * @Method Name	: setThumb
	 * @작성일		: 2019. 11. 30.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 썸네일 정보 저장
	 */
	private void setThumb(HttpServletRequest request, int bbsEstbsSn, String mngrId) throws Exception {
		String[] bbsThumbSnList = request.getParameterValues("bbsThumbSn");
		String[] thumbNmList    = request.getParameterValues("thumbNm");
		String[] widthSizeList  = request.getParameterValues("widthSize");
		String[] vrticlSizeList = request.getParameterValues("vrticlSize");
		
		if(thumbNmList != null && thumbNmList.length > 0) {
			// 등록된 데이터 이외의 모든 정보삭제
			BbsThumbVO bbsThumbVO = new BbsThumbVO();
			bbsThumbVO.setBbsEstbsSn(bbsEstbsSn);
			bbsThumbVO.setSc_keyArr(bbsThumbSnList);
			List<BbsThumbVO> thumbList = bbsThumbService.selectList(bbsThumbVO);
			
			if(thumbList != null && thumbList.size() > 0) {
				for (BbsThumbVO thumb : thumbList) {
					String bfData = JSONArray.fromObject(thumb).toString();
					bbsThumbService.deleteInfo(request, bfData, thumb, mngrId);
				}
			}
			
			for(int i=0; i<thumbNmList.length; i++) {
				String bbsThumbSn = bbsThumbSnList[i];
				String thumbNm    = thumbNmList[i];
				String widthSize  = widthSizeList[i];
				String vrticlSize = vrticlSizeList[i];
				
				if(StringUtils.isEmpty(thumbNm)) {
					continue;
				}
				
				bbsThumbVO = new BbsThumbVO();
				bbsThumbVO.setBbsEstbsSn(bbsEstbsSn);
				bbsThumbVO.setThumbNm(thumbNm);
				bbsThumbVO.setWidthSize((StringUtils.isNotEmpty(widthSize))? Integer.parseInt(widthSize) : 100);
				bbsThumbVO.setVrticlSize((StringUtils.isNotEmpty(vrticlSize))? Integer.parseInt(vrticlSize) : 100);
				
				if(StringUtils.isNotEmpty(bbsThumbSn)) {
					bbsThumbVO.setBbsThumbSn(Integer.parseInt(bbsThumbSn));
					BbsThumbVO thumb = bbsThumbService.selectObj(bbsThumbVO);
					
					if(thumb != null) {
						// 정보 수정
						String bfData = JSONArray.fromObject(thumb).toString();
						bbsThumbService.updateInfo(request, bfData, bbsThumbVO, mngrId);
					}
				} else {
					// 정보 저장
					bbsThumbService.insertInfo(request, bbsThumbVO, mngrId);
				}
			}
		}
	}
	
	/**
	 * @Method Name	: setField
	 * @작성일		: 2019. 11. 30.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 필드 정보 저장
	 */
	private void setField(HttpServletRequest request, int bbsEstbsSn, String mngrId) throws Exception {
		String[] bbsFieldSnList          = request.getParameterValues("bbsFieldSn");
		String[] bbsFieldCodeList        = request.getParameterValues("bbsFieldCode");
		String[] bbsFieldNmList          = request.getParameterValues("bbsFieldNm");
		String[] fieldTyList             = request.getParameterValues("fieldTy");
		String[] fieldTyActvtyAtList     = request.getParameterValues("fieldTyActvtyAt");
		String[] fieldTyTextList         = request.getParameterValues("fieldTyText");
		String[] fieldTyTextActvtyAtList = request.getParameterValues("fieldTyTextActvtyAt");
		String[] fieldUseAtList          = request.getParameterValues("fieldUseAt");
		String[] fieldUseActvtyAtList    = request.getParameterValues("fieldUseActvtyAt");
		String[] fieldEssntlAtList       = request.getParameterValues("fieldEssntlAt");
		String[] fieldEssntlActvtyAtList = request.getParameterValues("fieldEssntlActvtyAt");
		String[] fieldSearchAtList       = request.getParameterValues("fieldSearchAt");
		String[] fieldSearchActvtyAtList = request.getParameterValues("fieldSearchActvtyAt");
		String[] fieldListAtList         = request.getParameterValues("fieldListAt");
		String[] fieldListActvtyAtList   = request.getParameterValues("fieldListActvtyAt");
		String[] fieldListSizeList       = request.getParameterValues("fieldListSize");
		String[] fieldListStyleList      = request.getParameterValues("fieldListStyle");
		String[] fieldViewAtList         = request.getParameterValues("fieldViewAt");
		String[] fieldViewActvtyAtList   = request.getParameterValues("fieldViewActvtyAt");
		
		if(bbsFieldCodeList != null && bbsFieldCodeList.length > 0) {
			final List<String> list = new ArrayList<String>();
			Collections.addAll(list, bbsFieldSnList);
			list.remove("{key}");
			
			String[] brdFKeys = new String[list.size()];
			int j=0;
			for (String str : list) {
				brdFKeys[j] = str;
				
				j++;
			}
			
			// 등록될 데이터 이외의 모든 정보삭제
			BbsFieldVO bbsFieldVO = new BbsFieldVO();
			bbsFieldVO.setBbsEstbsSn(bbsEstbsSn);
			bbsFieldVO.setSc_keyArr(brdFKeys);
			List<BbsFieldVO> fieldList = bbsFieldService.selectList(bbsFieldVO);
			
			if(fieldList != null && fieldList.size() > 0) {
				for (BbsFieldVO field : fieldList) {
					String bfData = JSONArray.fromObject(field).toString();
					bbsFieldService.deleteInfo(request, bfData, field, mngrId);
				}
			}
			
			for(int i=0; i<bbsFieldCodeList.length; i++) {
				String bbsFieldSn          = bbsFieldSnList[i];
				String bbsFieldCode        = bbsFieldCodeList[i];
				String bbsFieldNm          = bbsFieldNmList[i];
				String fieldTy             = fieldTyList[i];
				String fieldTyActvtyAt     = fieldTyActvtyAtList[i];
				String fieldTyText         = fieldTyTextList[i];
				String fieldTyTextActvtyAt = fieldTyTextActvtyAtList[i];
				String fieldUseAt          = fieldUseAtList[i];
				String fieldUseActvtyAt    = fieldUseActvtyAtList[i];
				String fieldEssntlAt       = fieldEssntlAtList[i];
				String fieldEssntlActvtyAt = fieldEssntlActvtyAtList[i];
				String fieldSearchAt       = fieldSearchAtList[i];
				String fieldSearchActvtyAt = fieldSearchActvtyAtList[i];
				String fieldListAt         = fieldListAtList[i];
				String fieldListActvtyAt   = fieldListActvtyAtList[i];
				String fieldListSize       = fieldListSizeList[i];
				String fieldListStyle      = fieldListStyleList[i];
				String fieldViewAt         = fieldViewAtList[i];
				String fieldViewActvtyAt   = fieldViewActvtyAtList[i];
				
				if(StringUtils.isEmpty(bbsFieldCode) || StringUtils.equals(bbsFieldCode, "{field}")) {
					continue;
				}
				
				bbsFieldVO = new BbsFieldVO();
				bbsFieldVO.setBbsEstbsSn(bbsEstbsSn);
				bbsFieldVO.setBbsFieldCode(bbsFieldCode);
				bbsFieldVO.setBbsFieldNm(bbsFieldNm);
				bbsFieldVO.setListOrdr(99);
				bbsFieldVO.setViewOrdr(99);
				bbsFieldVO.setFieldTy(fieldTy);
				bbsFieldVO.setFieldTyActvtyAt(fieldTyActvtyAt);
				bbsFieldVO.setFieldTyText(fieldTyText);
				bbsFieldVO.setFieldTyTextActvtyAt(fieldTyTextActvtyAt);
				bbsFieldVO.setFieldUseAt(fieldUseAt);
				bbsFieldVO.setFieldUseActvtyAt(fieldUseActvtyAt);
				bbsFieldVO.setFieldEssntlAt(fieldEssntlAt);
				bbsFieldVO.setFieldEssntlActvtyAt(fieldEssntlActvtyAt);
				bbsFieldVO.setFieldSearchAt(fieldSearchAt);
				bbsFieldVO.setFieldSearchActvtyAt(fieldSearchActvtyAt);
				bbsFieldVO.setFieldListAt(fieldListAt);
				bbsFieldVO.setFieldListActvtyAt(fieldListActvtyAt);
				bbsFieldVO.setFieldListSize((StringUtils.isNotEmpty(fieldListSize))? Integer.parseInt(fieldListSize) : null);
				bbsFieldVO.setFieldListStyle(fieldListStyle);
				bbsFieldVO.setFieldViewAt(fieldViewAt);
				bbsFieldVO.setFieldViewActvtyAt(fieldViewActvtyAt);
				
				if(StringUtils.isNotEmpty(bbsFieldSn)) {
					BbsFieldVO bFVO = new BbsFieldVO();
					bFVO.setBbsFieldSn(Integer.parseInt(bbsFieldSn));
					BbsFieldVO brdFid = bbsFieldService.selectObj(bFVO);
					
					if(brdFid != null) {
						String bfData = JSONArray.fromObject(brdFid).toString();
						bbsFieldVO.setBbsFieldSn(brdFid.getBbsFieldSn());
						bbsFieldService.updateInfo(request, bfData, bbsFieldVO, mngrId);
					}
				} else {
					bbsFieldService.insertInfo(request, bbsFieldVO, mngrId);
				}
			}
		}
	}
	
	/**
	 * @Method Name	: setFieldOrderList
	 * @작성일		: 2019. 11. 30.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 필드_설정 목록 순서 변경
	 */
	private void setFieldOrderList(HttpServletRequest request, int bbsEstbsSn) throws Exception {
		String[] listOrdrList = request.getParameterValues("listOrdr");
		
		BbsFieldVO bbsFieldVO = new BbsFieldVO();
		bbsFieldVO.setBbsEstbsSn(bbsEstbsSn);
		bbsFieldVO.setListOrdr(99);
		
		if(listOrdrList != null && listOrdrList.length > 0) {
			int i=1;
			for (String cd : listOrdrList) {
				bbsFieldVO.setBbsFieldCode(cd);
				bbsFieldVO.setListOrdr(i);
				bbsFieldService.updateOrder(request, null, bbsFieldVO, null);
				
				i++;
			}
		}
	}
	
	/**
	 * @Method Name	: setFieldOrderView
	 * @작성일		: 2019. 11. 30.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 필드_설정 보기 순서 변경
	 */
	private void setFieldOrderView(HttpServletRequest request, int bbsEstbsSn) throws Exception {
		String[] viewOrdrList = request.getParameterValues("viewOrdr");
		
		BbsFieldVO bbsFieldVO = new BbsFieldVO();
		bbsFieldVO.setBbsEstbsSn(bbsEstbsSn);
		bbsFieldVO.setViewOrdr(99);
		
		if(viewOrdrList != null && viewOrdrList.length > 0) {
			int i=1;
			for (String cd : viewOrdrList) {
				bbsFieldVO.setBbsFieldCode(cd);
				bbsFieldVO.setViewOrdr(i);
				bbsFieldService.updateOrder(request, null, bbsFieldVO, null);
				
				i++;
			}
		}
	}
	
	/**
	 * @throws Exception 
	 * @Method Name	: setCategory
	 * @작성일		: 2019. 11. 30.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 카테고리 정보 저장
	 */
	private void setCategory(HttpServletRequest request, int bbsEstbsSn, String mngrId) throws Exception {
		String[] bbsCtgrySnList = request.getParameterValues("bbsCtgrySn");
		String[] ctgryNmList    = request.getParameterValues("ctgryNm");
		
		if(ctgryNmList != null && ctgryNmList.length > 0) {
			// 등록될 데이터 이외의 모든 정보삭제
			BbsCtgryVO bbsCtgryVO = new BbsCtgryVO();
			bbsCtgryVO.setBbsEstbsSn(bbsEstbsSn);
			bbsCtgryVO.setSc_keyArr(bbsCtgrySnList);
			List<BbsCtgryVO> itemList = bbsCtgryService.selectList(bbsCtgryVO);
			
			if(itemList != null && itemList.size() > 0) {
				for (BbsCtgryVO item : itemList) {
					String bfData = JSONArray.fromObject(item).toString();
					bbsCtgryService.deleteInfo(request, bfData, item, mngrId);
				}
			}
			
			for(int i=0; i<ctgryNmList.length; i++) {
				String bbsCtgrySn = bbsCtgrySnList[i];
				String ctgryNm    = ctgryNmList[i];
				
				if(StringUtils.isEmpty(ctgryNm) || StringUtils.equals(ctgryNm, "{name}")) {
					continue;
				}
				
				bbsCtgryVO = new BbsCtgryVO();
				bbsCtgryVO.setBbsEstbsSn(bbsEstbsSn);
				bbsCtgryVO.setCtgryNm(ctgryNm);
				
				if(StringUtils.isNotEmpty(bbsCtgrySn)) {
					bbsCtgryVO.setBbsCtgrySn(Integer.parseInt(bbsCtgrySn));
					BbsCtgryVO cate = bbsCtgryService.selectObj(bbsCtgryVO);
					
					if(cate != null) {
						String bfData = JSONArray.fromObject(cate).toString();
						bbsCtgryService.updateInfo(request, bfData, bbsCtgryVO, mngrId);
					}
				} else {
					bbsCtgryService.insertInfo(request, bbsCtgryVO, mngrId);
				}
			}
		}
	}
	
	/**
	 * @throws Exception 
	 * @Method Name	: setRole
	 * @작성일		: 2019. 11. 30.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판_지정_회원 역할 설정
	 */
	private void setRole(HttpServletRequest request, int bbsEstbsSn, String mngrId) throws Exception {
		// 모든정보 삭제
		BbsRoleVO bbsRoleVO = new BbsRoleVO();
		bbsRoleVO.setBbsEstbsSn(bbsEstbsSn);
		List<BbsRoleVO> itemList = bbsRoleService.selectList(bbsRoleVO);
		
		if(itemList != null && itemList.size() > 0) {
			for (BbsRoleVO item : itemList) {
				String bfData = JSONArray.fromObject(item).toString();
				bbsRoleService.deleteInfo(request, bfData, item, mngrId);
			}
		}
		
		// 기본 권한
		String powList  = request.getParameter("authorList");
		String powRead  = request.getParameter("authorRedng");
		String powWrite = request.getParameter("authorRegist");
		String powReply = request.getParameter("authorAnswer");
		String powCmnt  = request.getParameter("authorCm");
		
		// 연결 역할
		String[] powListsArr  = request.getParameterValues("powLists");
		String[] powReadsArr  = request.getParameterValues("powReads");
		String[] powWritesArr = request.getParameterValues("powWrites");
		String[] powReplysArr = request.getParameterValues("powReplys");
		String[] powCmntsArr  = request.getParameterValues("powCmnts");
		
		// 목록보기 접근권한 등록
		if(powList.equals("A") && powListsArr != null && powListsArr.length > 0) {
			for (String pow : powListsArr) {
				bbsRoleVO.setRoleSn(Integer.parseInt(pow));
				bbsRoleVO.setRoleTy("LIST");
				bbsRoleService.insertInfo(request, bbsRoleVO, mngrId);
			}
		}
		
		// 읽기 접근권한 등록
		if(powRead.equals("A") && powReadsArr != null && powReadsArr.length > 0) {
			for (String pow : powReadsArr) {
				bbsRoleVO.setRoleSn(Integer.parseInt(pow));
				bbsRoleVO.setRoleTy("READ");
				bbsRoleService.insertInfo(request, bbsRoleVO, mngrId);
			}
		}
		
		// 쓰기 접근권한 등록
		if(powWrite.equals("A") && powWritesArr != null && powWritesArr.length > 0) {
			for (String pow : powWritesArr) {
				bbsRoleVO.setRoleSn(Integer.parseInt(pow));
				bbsRoleVO.setRoleTy("WRITE");
				bbsRoleService.insertInfo(request, bbsRoleVO, mngrId);
			}
		}
		
		// 답글 접근권한 등록
		if(powReply.equals("A") && powReplysArr != null && powReplysArr.length > 0) {
			for (String pow : powReplysArr) {
				bbsRoleVO.setRoleSn(Integer.parseInt(pow));
				bbsRoleVO.setRoleTy("REPLY");
				bbsRoleService.insertInfo(request, bbsRoleVO, mngrId);
			}
		}
		
		// 댓글 접근권한 등록
		if(powCmnt.equals("A") && powCmntsArr != null && powCmntsArr.length > 0) {
			for (String pow : powCmntsArr) {
				bbsRoleVO.setRoleSn(Integer.parseInt(pow));
				bbsRoleVO.setRoleTy("CMNT");
				bbsRoleService.insertInfo(request, bbsRoleVO, mngrId);
			}
		}
	}

}
