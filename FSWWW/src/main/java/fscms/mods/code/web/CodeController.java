package fscms.mods.code.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fscms.cmm.util.A_UtilHelper;
import fscms.cmm.vo.JsonVO;
import fscms.mods.code.util.CodeHelper;
import fscms.mods.code.vo.CodeDetailVO;
import fscms.mods.code.vo.CodeVO;
import net.sf.json.JSONArray;

@Controller
public class CodeController {
	
	static Logger logger = LogManager.getLogger(CodeController.class);
	
	@Resource(name = "a_UtilsHelper")
	private A_UtilHelper a_UtilsHelper;
	
	@Autowired
	private CodeHelper codeHelper;
	
	/**
	 * @Method Name	: codeGetList
	 * @작성일		: 2020. 2. 23.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 코드_세부 정보 가져오기
	 */
	@RequestMapping(value = "/code/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String codeGetList(
			HttpServletRequest request,
			@RequestParam(value = "codeClId", required = true) String codeClId,
			Boolean refAt) {
		
		JsonVO jsonVO = new JsonVO();
		List<CodeVO> itemList = null;
		
		try {
			itemList = codeHelper.getCodeList(codeClId);
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(itemList).toString();
	}
	
	@RequestMapping(value = "/code/detail/getList.do", produces = "application/json; charset=UTF-8")
	public @ResponseBody String codeDetailGetList(
			HttpServletRequest request,
			@RequestParam(value = "codeId", required = true) String codeId,
			Boolean refAt) {
		
		JsonVO jsonVO = new JsonVO();
		List<CodeDetailVO> itemList = null;
		
		try {
			itemList = codeHelper.getList(codeId);
		} catch (Exception e) {
			jsonVO.setMsg(a_UtilsHelper.getAjaxSqlException(logger, "C0002"));
			return JSONArray.fromObject(jsonVO).toString();
		}
		
		return JSONArray.fromObject(itemList).toString();
	}

}
