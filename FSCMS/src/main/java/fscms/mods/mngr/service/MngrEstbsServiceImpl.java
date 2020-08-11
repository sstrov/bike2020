package fscms.mods.mngr.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.data.util.DataChghstHelper;
import fscms.mods.mngr.vo.MngrEstbsVO;
import fscms.mods.mngr.vo.MngrIpEstbsVO;
import fscms.mods.site.util.SiteHelper;
import net.sf.json.JSONArray;

@Service("mngrEstbsService")
public class MngrEstbsServiceImpl extends EgovAbstractServiceImpl implements MngrEstbsService {
	
	@Resource(name = "mngrEstbsMapper")
	private MngrEstbsMapper mngrEstbsDAO;
	
	@Resource(name = "mngrIpEstbsService")
	private MngrIpEstbsService mngrIpEstbsService;
	
	@Resource(name = "siteHelper")
	private SiteHelper siteHelper;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public MngrEstbsVO selectObj() throws Exception {
		return mngrEstbsDAO.selectObj();
	}

	@Override
	public void insertInfo(HttpServletRequest request, MngrEstbsVO vo, String mngrId) throws Exception {
		mngrEstbsDAO.insertInfo(vo);
		
		this.setIp(request, mngrId);
		
		siteHelper.setJson();
		
		dataChghstHelper.setInsert(request, "FS_SITE", null, this.getData(), null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, MngrEstbsVO vo, String mngrId)
			throws Exception {
		mngrEstbsDAO.updateInfo(vo);
		
		this.setIp(request, mngrId);
		
		siteHelper.setJson();
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_SITE", bfData, this.getData(), null, mngrId);
		}
	}
	
	// 아이피 허용/차단 정보 등록
	private void setIp(HttpServletRequest request, String mngrId) throws Exception {
		String[] keyArr = request.getParameterValues("ipEstbsSn");
		String[] tyArr  = request.getParameterValues("ipEstbsTy");
		String[] ipArr  = request.getParameterValues("registIp");
		String[] dcArr  = request.getParameterValues("ipEstbsDc");
		
		// 아이피 정보 삭제
		String keyArrString = "";
		if(keyArr != null && keyArr.length > 0) {
			for (String str : keyArr) {
				if(StringUtils.isNotEmpty(str)) {
					keyArrString += ((StringUtils.isNotEmpty(keyArrString))? "," : "") + str;
				}
			}
		}
		
		MngrIpEstbsVO mngrIpEstbsVO = new MngrIpEstbsVO();
		if(StringUtils.isNotEmpty(keyArrString)) {
			mngrIpEstbsVO.setKeyArr(keyArrString.split(","));
		}
		List<MngrIpEstbsVO> itemList = mngrIpEstbsService.selectList(mngrIpEstbsVO);
		
		if(itemList != null && itemList.size() > 0) {
			for (MngrIpEstbsVO item : itemList) {
				// 데이터 삭제
				String bfData = JSONArray.fromObject(item).toString();
				mngrIpEstbsService.deleteInfo(request, bfData, item, mngrId);
			}
		}
		
		if(ipArr != null && ipArr.length > 0) {
			for(int i=0; i<ipArr.length; i++) {
				if(StringUtils.isEmpty(ipArr[i])) {
					continue;
				}
				
				mngrIpEstbsVO = new MngrIpEstbsVO();
				mngrIpEstbsVO.setIpEstbsTy(tyArr[i]);
				mngrIpEstbsVO.setIpEstbsDc(dcArr[i]);
				mngrIpEstbsVO.setRegistIp(ipArr[i]);
				
				if(StringUtils.isNotEmpty(keyArr[i])) {
					// 키 정보가 있으면 수정
					MngrIpEstbsVO vo = new MngrIpEstbsVO();
					vo.setIpEstbsSn(Integer.parseInt(keyArr[i]));
					MngrIpEstbsVO item = mngrIpEstbsService.selectObj(vo);
					
					if(item != null) {
						mngrIpEstbsVO.setIpEstbsSn(Integer.parseInt(keyArr[i]));
						String bfData = JSONArray.fromObject(item).toString();
						mngrIpEstbsService.updateInfo(request, bfData, mngrIpEstbsVO, mngrId);
					}
				} else {
					mngrIpEstbsService.insertInfo(request, mngrIpEstbsVO, mngrId);
				}
			}
		}
	}
	
	private String getData() throws Exception {
		String rtnData = null;
		
		MngrEstbsVO item = this.selectObj();
		
		if(item != null) {
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}

}
