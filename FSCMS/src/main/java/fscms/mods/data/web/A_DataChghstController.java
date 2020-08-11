package fscms.mods.data.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.util.EgovCryptoARIAHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import fscms.cmm.exception.WrongApproachException;
import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.vo.JsonVO;
import fscms.cmm.vo.RoleVO;
import fscms.mods.data.service.DataChghstService;
import fscms.mods.data.vo.DataChghstSearchVO;
import fscms.mods.data.vo.DataChghstVO;
import fscms.mods.mngr.util.MngrMenuAccFn;
import fscms.mods.mngr.vo.MngrVO;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/{admURI}")
public class A_DataChghstController {
	
	static Logger logger = LogManager.getLogger(A_DataChghstController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Resource(name = "dataChghstService")
	private DataChghstService dataChghstService;
	
	@Resource(name = "mngrMenuAccFn")
	private MngrMenuAccFn mngrMenuAccFn;
	
	@Resource(name = "egovCryptoARIAHelper")
	private EgovCryptoARIAHelper egovCryptoARIAHelper;
	
	/**
	 * @Method Name	: aDataChghstList
	 * @작성일		: 2019. 10. 11.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 데이터_변경이력 목록 화면
	 */
	@RequestMapping(value = "/data/chghst/list.do")
	public String aDataChghstList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") DataChghstSearchVO searchVO,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		model.addAttribute("role",   role);
		model.addAttribute("admURI", admURI);
		return "mngr/data/chghst_list";
	}
	
	/**
	 * @Method Name	: aDataChghstGetList
	 * @작성일		: 2019. 10. 11.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 데이터_변경이력 목록 조회
	 */
	@RequestMapping(value = "/data/chghst/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String aDataChghstGetList(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") DataChghstSearchVO searchVO,
			Boolean refAtAjax) {
		
		JsonVO jsonVO = new JsonVO();
		if(!refAtAjax) {
			// 관리자 Ajax 체크 시 필요 리턴에 따른 처리는 VIEW 단에서 처리할 것
			jsonVO.setMsg(a_UtilsHelper.getAjaxSsoExfired(logger));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		List<DataChghstVO> itemList = null;
		if(StringUtils.isNotEmpty(searchVO.getSw())) {
			try {
				searchVO.setSw_entry(egovCryptoARIAHelper.encrypt(searchVO.getSw()));
			} catch (UnsupportedEncodingException e) {
				searchVO.setSw_entry(searchVO.getSw());
			}
		}
		try {
			int tCount = dataChghstService.selectTCount(searchVO);
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getMaxList());
			paginationInfo.setPageSize(searchVO.getPageSize());
			paginationInfo.setTotalRecordCount(tCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			itemList = dataChghstService.selectPageList(searchVO);
			
			if(itemList != null && itemList.size() > 0) {
				itemList.get(0).settCount(tCount);
			}
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(itemList).toString();
	}
	
	/**
	 * @Method Name	: aDataChghstView
	 * @작성일		: 2019. 10. 11.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 데이터_변경이력 상세보기
	 */
	@RequestMapping(value = "/data/chghst/view.do")
	public String aDataChghstView(
			HttpServletRequest request,
			@PathVariable String admURI,
			boolean admChk,
			@ModelAttribute("searchVO") DataChghstSearchVO searchVO,
			@RequestParam(value = "dataChghstSn", required = true) int dataChghstSn,
			MngrVO admSession,
			String key,
			ModelMap model) {
		
		RoleVO role = mngrMenuAccFn.checkRole(key, admSession.getRoleSn());
		if(StringUtils.isEmpty(role.getPowR()) || !StringUtils.equals(role.getPowR(), "Y")) {
			logger.error("잘못된 접근 입니다. (오류코드 : C0041)");
			throw new WrongApproachException("C0041");
		}
		
		DataChghstVO item = new DataChghstVO();
		try {
			// 일련번호가 있으면 수정
			DataChghstVO dataChghstVO = new DataChghstVO();
			dataChghstVO.setDataChghstSn(dataChghstSn);
			item = dataChghstService.selectObj(dataChghstVO);
			
			JSONObject beJsonObj = null;
			
			if(StringUtils.isNotEmpty(item.getBfchgData())) {
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(item.getBfchgData());
				org.json.simple.JSONArray jArray = (org.json.simple.JSONArray) obj;
				beJsonObj = (JSONObject) jArray.get(0);
				
				String str = "";
				for (Object oKey : beJsonObj.keySet()) {
					String keyStr = (String) oKey;
					String keyVal = beJsonObj.get(keyStr).toString();
					
					str += "<tr>" +
							"<td>" + keyStr + "</td>" +
							"<td>" + keyVal + "</td>" +
						"</tr>\n";
				}
				
				model.addAttribute("beJson", str);
			}
			
			if(StringUtils.isNotEmpty(item.getAftchData())) {
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(item.getAftchData());
				org.json.simple.JSONArray jArray = (org.json.simple.JSONArray) obj;
				JSONObject JsonObj = (JSONObject) jArray.get(0);
				
				String str = "";
				for (Object oKey : JsonObj.keySet()) {
					String keyStr = (String) oKey;
					String keyVal = JsonObj.get(keyStr).toString();
					
					String bgColor = "";
					if(beJsonObj != null && !StringUtils.equals(beJsonObj.get(keyStr).toString(), keyVal)) {
						bgColor = "style=\"background-color:red; color:#fff;\"";
					}
					
					str += "<tr>" +
							"<td " + bgColor + ">" + keyStr + "</td>" +
							"<td " + bgColor + ">" + keyVal + "</td>" +
						"</tr>\n";
				}
				
				model.addAttribute("afJson", str);
			}
		} catch (Exception e) {
			logger.error("데이터 조회 오류 : " + e.getMessage());
			throw new WrongApproachException("C0002");
		}
		
		model.addAttribute("item",   item);
		model.addAttribute("admURI", admURI);
		return "mngr/data/chghst_view";
	}

}
