package fscms.mods.mngr.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import fscms.cmm.util.FsFuncCmmHelper;
import fscms.cmm.util.JsonConnHelper;
import fscms.cmm.util.PropConnHelper;
import fscms.mods.mngr.service.MngrMenuAccService;
import fscms.mods.mngr.service.MngrMenuService;
import fscms.mods.mngr.service.MngrRoleService;
import fscms.mods.mngr.vo.MngrMenuAccVO;
import fscms.mods.mngr.vo.MngrMenuVO;
import fscms.mods.mngr.vo.MngrRoleVO;
import net.sf.json.JSONArray;

@Component
public class MngrMenuHelper {
	
	static Logger logger = LogManager.getLogger(MngrMenuHelper.class);
	
	@Resource(name = "mngrMenuService")
	private MngrMenuService mngrMenuService;
	
	@Resource(name = "mngrMenuAccService")
	private MngrMenuAccService mngrMenuAccService;
	
	@Resource(name = "mngrRoleService")
	private MngrRoleService mngrRoleService;
	
	/**
	 * @Method Name	: setMenu
	 * @작성일		: 2019. 10. 7.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 메뉴 템플릿 셋팅
	 */
	public void setMenu() throws Exception {
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		String rootPath = fsFuncCmmHelper.getAppAbsolutePath() + "/repository/mngr/menu";
		String tplPath  = fsFuncCmmHelper.getAppAbsolutePath() + "/template/mngr/menu";
		
		// 하위 폴더 전체 삭제
		File deleteFolder       = new File(rootPath);
		File[] deleteFolderList = deleteFolder.listFiles();
		
		if(!deleteFolder.exists()) {
			// 경로가 없으면 생성
			deleteFolder.mkdir();
		}
		
		if(deleteFolderList != null && deleteFolderList.length > 0) {
			for (File file : deleteFolderList) {
				new Thread() {
					@Override
					public void run() {
						logger.info("파일 삭제 : " + file.getPath() + "/" + file.getName());
						file.delete();
					}
				}.start();
			}
		}
		
		MngrMenuVO mngrMenuVO = new MngrMenuVO();
		mngrMenuVO.setMenuDp(1);
		List<MngrMenuVO> menuList = mngrMenuService.selectList(mngrMenuVO);
		
		// 네비 메뉴 생성
		File tplFileNavi = new File(tplPath + "/navi.tpl");
		if(fsFuncCmmHelper.checkFileExists(tplFileNavi)) {
			BufferedReader br = null;
			try {
				char[] ch = new char[(int) tplFileNavi.length()];
				br = new BufferedReader(fsFuncCmmHelper.getFileRead(tplFileNavi));
				br.read(ch);
	
				StringBuilder str = new StringBuilder();
				str.append(ch);
	
				br.close();
	
				this.setMenu_navi(rootPath, menuList, str.toString(), fsFuncCmmHelper);
			} finally {
				br.close();
			}
		}
		
		// 메뉴 JSON 생성
		this.setMenu_json(rootPath, menuList);
		
		// 상단 메뉴 생성
		File tplFileTop = new File(tplPath + "/top.tpl");
		if(fsFuncCmmHelper.checkFileExists(tplFileTop)) {
			this.setMenu_top(rootPath, menuList, tplFileTop, fsFuncCmmHelper);
		}
		
	}
	
	/**
	 * @Method Name	: setMenu_top
	 * @작성일		: 2019. 10. 7.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 상단 메뉴 생성
	 */
	private void setMenu_top(String rootPath, List<MngrMenuVO> menuList, File tplFile, FsFuncCmmHelper fsFuncCmmHelper) throws Exception {
		char[] ch = new char[(int) tplFile.length()];
		BufferedReader br = new BufferedReader(fsFuncCmmHelper.getFileRead(tplFile));
		br.read(ch);
		
		StringBuilder str = new StringBuilder();
		str.append(ch);
		
		br.close();
		
		/*File file = new File(rootPath + "/top.jsp");
		BufferedWriter bw = null;
		try {
			file.createNewFile();
			
			String tmpStr = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n" +
					"<%@ include file=\"/WEB-INF/jsp/library.jsp\" %>\n" +
					"<%@ taglib uri=\"/WEB-INF/tld/fs-mngrRole.tld\" prefix=\"roleMngr\" %>\n\n";
			
			bw = new BufferedWriter(fsFuncCmmHelper.setFileWriter(file.getPath()));
			bw.write(tmpStr + content);
			bw.close();
		} finally {
			bw.close();
		}*/
		
		List<MngrRoleVO> roleList = mngrRoleService.selectList(null);
		
		if(roleList != null && roleList.size() > 0) {
			for (MngrRoleVO role : roleList) {
				// 컨텐츠 내용 가져오기
				String content = str.toString();
				
				if(StringUtils.isNotEmpty(content)) {
					Pattern pattern = Pattern.compile(fsFuncCmmHelper.getRegPattern("1depth", 1));
					Matcher match = pattern.matcher(content);
					
					if(match.find()) {
						content = match.replaceAll(Matcher.quoteReplacement(this.setMenu_topTpl(menuList, match.group(1), 1, fsFuncCmmHelper, role.getRoleSn())));
					}
				}
				
				// 상단 메뉴 파일 생성
				File file = new File(rootPath + "/top_" + role.getRoleSn() + ".jsp");
				
				BufferedWriter bw = null;
				try {
					file.createNewFile();
					
					String tmpStr = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n" +
							"<%@ include file=\"/WEB-INF/jsp/library.jsp\" %>\n";
					
					bw = new BufferedWriter(fsFuncCmmHelper.setFileWriter(file.getPath()));
					bw.write(tmpStr + content);
					bw.close();
				} finally {
					bw.close();
				}
			}
		}
	}
	
	/**
	 * @Method Name	: setMenu_topTpl
	 * @작성일		: 2019. 10. 7.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 상단 템플릿 상세내용 가져오기
	 */
	private String setMenu_topTpl(List<MngrMenuVO> menuList, String tpl, int depth, FsFuncCmmHelper fsFuncCmmHelper, int roleSn) throws Exception {
		String patten         = fsFuncCmmHelper.getRegPattern((depth + 1) + "depth", (depth + 1));
		String pattenS        = fsFuncCmmHelper.getRegPattern(depth + "depth_l", depth);
		String activePatten   = fsFuncCmmHelper.getRegPattern("active", null);
		String childPatten    = fsFuncCmmHelper.getRegPattern("child", null);
		String childClsPatten = fsFuncCmmHelper.getRegPattern("childCls", null);
		
		Pattern pt = Pattern.compile(pattenS);
		Matcher mc = pt.matcher(tpl);
		
		if(mc.find()) {
			if(menuList != null && menuList.size() > 0) {
				String tmpStr = "";
				
				for (MngrMenuVO item : menuList) {
					if(StringUtils.equals(item.getUseAt(), "N") || StringUtils.equals(item.getActvtyAt(), "N")) {
						continue;
					}
					
					String depContent = mc.group(1);
					List<MngrMenuVO> childList = null;
					
					Pattern pt_dep = Pattern.compile(patten);
					Matcher mc_dep = pt_dep.matcher(depContent);
					
					if(mc_dep.find()) {
						// 하위 메뉴 정보 셋팅
						MngrMenuVO mngrMenuVO = new MngrMenuVO();
						mngrMenuVO.setMenuUpperSn(item.getMenuSn());
						mngrMenuVO.setUseAt("Y");
						childList = mngrMenuService.selectList(mngrMenuVO);
						
						if(childList != null && childList.size() > 0) {
							depContent = mc_dep.replaceAll(Matcher.quoteReplacement(this.setMenu_topTpl(childList, mc_dep.group(1), item.getMenuDp() + 1, fsFuncCmmHelper, roleSn)));
						} else {
							depContent = mc_dep.replaceAll("");
						}
					}
					
					String link = (childList != null && childList.size() > 0)? "#none" : item.getMenuLink();
					depContent = depContent.replaceAll("\\{link\\}",   link);
					depContent = depContent.replaceAll("\\{target\\}", item.getMenuTrgt());
					depContent = depContent.replaceAll("\\{name\\}",   item.getMenuNm());
					
					// 활성 여부
					Pattern pt_at = Pattern.compile(activePatten);
					Matcher mc_at = pt_at.matcher(depContent);
					
					if(mc_at.find()) {
						String active = "";
						if(item.getMenuDp() == 1) {
							active += ((StringUtils.isNotEmpty(active))? " || " : "") + "(currentMenu.menuSn eq '" + item.getMenuSn() + "' || currentMenu.menuUpperSn eq '" + item.getMenuSn() + "' || currentMenu.menuBestSn eq '" + item.getMenuSn() + "')";
						} else if(item.getMenuDp() == 2) {
							active += ((StringUtils.isNotEmpty(active))? " || " : "") + "(currentMenu.menuSn eq '" + item.getMenuSn() + "' || currentMenu.menuUpperSn eq '" + item.getMenuSn() + "')";
						} else {
							active += ((StringUtils.isNotEmpty(active))? " || " : "") + "currentMenu.menuSn eq '" + item.getMenuSn() + "'";
						}
						
						String activeStr = "<c:if test=\"${ !empty currentMenu && (" + active + ") }\">" +
								mc_at.group(1) +
								"</c:if>";
						depContent = mc_at.replaceAll(Matcher.quoteReplacement(activeStr));
					}
					
					// 하단아이콘 (+)
					Pattern pt_ch = Pattern.compile(childPatten);
					Matcher mc_ch = pt_ch.matcher(depContent);
					
					if(mc_ch.find()) {
						String childStr = (childList != null && childList.size() > 0)? mc_ch.group(1) : "";
						depContent = mc_ch.replaceAll(childStr);
					}
					
					// 하단아이콘 클래스
					Pattern pt_chc = Pattern.compile(childClsPatten);
					Matcher mc_chc = pt_chc.matcher(depContent);
					
					if(mc_chc.find()) {
						String childClsStr = (childList != null && childList.size() > 0)? mc_chc.group(1) : "";
						depContent = mc_chc.replaceAll(childClsStr);
					}
					
					MngrMenuAccVO mngrMenuAccVO = new MngrMenuAccVO();
					mngrMenuAccVO.setMenuSn(item.getMenuSn());
					mngrMenuAccVO.setRoleSn(roleSn);
					MngrMenuAccVO mngrAcc = mngrMenuAccService.selectObj(mngrMenuAccVO);
					
					boolean roleAt = false;
					if(mngrAcc != null && (StringUtils.equals(mngrAcc.getAuthorRedng(), "Y") || StringUtils.equals(mngrAcc.getAuthorRegist(), "Y"))) {
						roleAt = true;
					}
					
					tmpStr += "<c:if test=\"${ " + roleAt + " }\">";
					tmpStr += depContent;
					tmpStr += "</c:if>";
				}
				
				tpl = mc.replaceAll(Matcher.quoteReplacement(tmpStr));
			} else {
				tpl = mc.replaceAll("");
			}
		}
		
		return tpl;
	}
	
	// 네비메뉴 생성
	private void setMenu_navi(String rootPath, List<MngrMenuVO> menuList, String content, FsFuncCmmHelper fsFuncCmmHelper) throws Exception {
		PropConnHelper propConnHelper = new PropConnHelper();
		
		if(menuList != null && menuList.size() > 0) {
			for (MngrMenuVO item : menuList) {
				String cnt     = content;
				String jsonStr = "";
				String pattenS = fsFuncCmmHelper.getRegPattern("1depth_l", 1);
				
				// 관리자 경로 삽입
				String admPath = propConnHelper.getConn("fs_config", "Fs_config.admUrl");
				cnt = cnt.replaceAll("\\{admURI\\}", admPath);
				
				Pattern pt = Pattern.compile(pattenS);
				Matcher mc = pt.matcher(cnt);
				
				if(mc.find()) {
					String tmpStr = "";
					
					// 네비 목록 조회
					List<MngrMenuVO> naviList = this.getNaviList(item.getMenuSn());
					
					if(naviList != null && naviList.size() > 0) {
						int i = 1;
						for (MngrMenuVO navi : naviList) {
							if(StringUtils.equals(navi.getMenuSn(), item.getMenuSn())) {
								continue;
							}
							
							String c = mc.group(1);
							c = c.replaceAll("\\{link\\}",   navi.getMenuLink());
							c = c.replaceAll("\\{target\\}", navi.getMenuTrgt());
							c = c.replaceAll("\\{name\\}",   navi.getMenuNm());
							
							tmpStr += c;
							
							jsonStr += ((jsonStr.length() > 0)? ",\n" : "\n") + "\t\"menuSn" + i + "\" : \"" + navi.getMenuSn() + "\"";
							i++;
						}
					}
					
					cnt = mc.replaceAll(tmpStr);
					cnt = cnt.replaceAll("\\{c_name\\}", item.getMenuNm());
					
					jsonStr += "\n}";
				}
				
				// 네비 파일 생성
				File file = new File(rootPath + "/navi_" + item.getMenuSn() + ".jsp");
				BufferedWriter bw = null;
				try {
					file.createNewFile();
				
					String tmpStr = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n" +
							"<%@ include file=\"/WEB-INF/jsp/library.jsp\" %>\n\n";
					bw = new BufferedWriter(fsFuncCmmHelper.setFileWriter(file.getPath()));
					bw.write(tmpStr + cnt);
					bw.close();
				
					// 네비 JSON 파일 생성
					file = new File(rootPath + "/json_navi_" + item.getMenuSn() + ".json");
					file.createNewFile();
					
					bw = new BufferedWriter(fsFuncCmmHelper.setFileWriter(file.getPath()));
					bw.write("{" + jsonStr);
					bw.close();
				} finally {
					bw.close();
				}
				
				// 하위 정보가 있으면 재귀
				MngrMenuVO mngrMenuVO = new MngrMenuVO();
				mngrMenuVO.setMenuUpperSn(item.getMenuSn());
				List<MngrMenuVO> childList = mngrMenuService.selectList(mngrMenuVO);
				
				if(childList != null && childList.size() > 0) {
					this.setMenu_navi(rootPath, childList, content, fsFuncCmmHelper);
				}
			}
		}
	}
	
	// 네비 목록 조회
	private List<MngrMenuVO> getNaviList(String menuSn) throws Exception {
		List<MngrMenuVO> itemList = new ArrayList<MngrMenuVO>();
		
		if(StringUtils.isNotEmpty(menuSn)) {
			MngrMenuVO mngrMenuVO = new MngrMenuVO();
			mngrMenuVO.setMenuSn(menuSn);
			MngrMenuVO item = mngrMenuService.selectObj(mngrMenuVO);
			
			if(item != null) {
				if(StringUtils.isNotEmpty(item.getMenuUpperSn())) {
					itemList = this.getNaviList(item.getMenuUpperSn());
				}
				
				itemList.add(item);
			}
		}
		
		return itemList;
	}
	
	// 메뉴 JSON 생성
	private void setMenu_json(String rootPath, List<MngrMenuVO> menuList) throws Exception {
		
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		if(menuList != null && menuList.size() > 0) {
			for (MngrMenuVO item : menuList) {
				String str = "{\n" +
						"\t\"accKey\" : \"" + item.getAccSn() + "\",\n" +
						"\t\"fKey\"   : \"" + item.getMenuBestSn() + "\",\n" +
						"\t\"pKey\"   : \"" + item.getMenuUpperSn() + "\",\n" +
						"\t\"menuG\"  : \"" + item.getMenuDp() + "\",\n" +
						"\t\"nm\"     : \"" + item.getMenuNm() + "\",\n" +
						"\t\"page\"   : \"" + item.getMenuCnncTy() + "\",\n" +
						"\t\"target\" : \"" + item.getMenuTrgt() + "\",\n" +
						"\t\"actAt\"  : \"" + item.getActvtyAt() + "\",\n" +
						"\t\"useAt\"  : \"" + item.getUseAt() + "\",\n" +
						"\t\"link\"   : \"" + item.getMenuLink() + "\"\n" +
					"}";
				
				File file = new File(rootPath + "/json_menu_" + item.getMenuSn() + ".json");
				BufferedWriter bw = null;
				try {
					file.createNewFile();
					
					bw = new BufferedWriter(fsFuncCmmHelper.setFileWriter(file.getPath()));
					bw.write(str);
					bw.close();
				} finally {
					bw.close();
				}
				
				// 하위 정보가 있으면 재귀
				MngrMenuVO mngrMenuVO = new MngrMenuVO();
				mngrMenuVO.setMenuUpperSn(item.getMenuSn());
				List<MngrMenuVO> childList = mngrMenuService.selectList(mngrMenuVO);
				
				if(childList != null && childList.size() > 0) {
					this.setMenu_json(rootPath, childList);
				}
			}
		}
	}
	
	/**
	 * @Method Name	: getJson
	 * @작성일		: 2019. 10. 7.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 메뉴 정보 조회 - JSON
	 */
	public MngrMenuVO getJson(String menuKey) {
		
		JSONObject jsonObject = null;
		try {
			jsonObject = new JsonConnHelper().getJsonFile("/mngr/menu/json_menu_" + menuKey);
			
			if(jsonObject != null) {
				String accKey = (String) jsonObject.get("accKey");
				String fKey   = (String) jsonObject.get("fKey");
				String pKey   = (String) jsonObject.get("pKey");
				String menuG  = (String) jsonObject.get("menuG");
				String nm     = (String) jsonObject.get("nm");
				String page   = (String) jsonObject.get("page");
				String actAt  = (String) jsonObject.get("actAt");
				String useAt  = (String) jsonObject.get("useAt");
				String target = (String) jsonObject.get("target");
				String link   = (String) jsonObject.get("link");
				jsonObject.clear();
				
				MngrMenuVO menu = new MngrMenuVO();
				menu.setMenuSn(menuKey);
				menu.setAccSn((StringUtils.isNotEmpty(accKey) && !"null".equals(accKey))? Integer.parseInt(accKey) : null);
				menu.setMenuBestSn(fKey);
				if(StringUtils.isNotEmpty(pKey) && !"null".equals(pKey)) {
					menu.setMenuUpperSn(pKey);
				}
				menu.setMenuDp(Integer.parseInt(menuG));
				menu.setMenuNm(nm);
				menu.setMenuCnncTy(page);
				menu.setMenuTrgt(target);
				menu.setActvtyAt(actAt);
				menu.setUseAt(useAt);
				menu.setMenuLink(link);
				
				return menu;
			}
		} catch (IOException | ParseException e) {
			return null;
		}
		return null;
	}
	
	// 권한 설정
	public void setAcc(HttpServletRequest request, String mngrId, int roleSn) throws Exception {
		
		MngrMenuAccVO mngrMenuAccVO = new MngrMenuAccVO();
		mngrMenuAccVO.setRoleSn(roleSn);
		List<MngrMenuAccVO> accList = mngrMenuAccService.selectList(mngrMenuAccVO);
		
		if(accList != null && accList.size() > 0) {
			for (MngrMenuAccVO item : accList) {
				String bfData = JSONArray.fromObject(item).toString();
				mngrMenuAccService.deleteInfo(request, bfData, item, mngrId);
			}
		}
		
		// 권한 정보 저장
		String[] powR = request.getParameterValues("powR");
		String[] powW = request.getParameterValues("powW");
		
		if(powR != null && powR.length > 0) {
			for (int i=0; i<powR.length; i++) {
				String menuSn = powR[i];
				
				mngrMenuAccVO = new MngrMenuAccVO();
				mngrMenuAccVO.setMenuSn(menuSn);
				mngrMenuAccVO.setRoleSn(roleSn);
				mngrMenuAccVO.setAuthorRedng("Y");
				mngrMenuAccService.insertInfo(request, mngrMenuAccVO, mngrId);
			}
		}
		
		if(powW != null && powW.length > 0) {
			for (int i=0; i<powW.length; i++) {
				String menuSn = powW[i];
				
				mngrMenuAccVO = new MngrMenuAccVO();
				mngrMenuAccVO.setMenuSn(menuSn);
				mngrMenuAccVO.setRoleSn(roleSn);
				MngrMenuAccVO item = mngrMenuAccService.selectObj(mngrMenuAccVO);
				
				if(item != null) {
					item.setAuthorRegist("Y");
					
					String bfData = JSONArray.fromObject(item).toString();
					mngrMenuAccService.updateInfo(request, bfData, item, mngrId);
				} else {
					mngrMenuAccVO.setAuthorRegist("Y");
					mngrMenuAccService.insertInfo(request, mngrMenuAccVO, mngrId);
				}
			}
		}
		
		// 권한 폴더 생성 및 삭제
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		String rootPath = fsFuncCmmHelper.getAppAbsolutePath() + "/repository/mngr/role";
		File deleteFolder = new File(rootPath);
		File[] deleteFolderList = deleteFolder.listFiles();
		
		if(!deleteFolder.exists()) {
			// root 경로가 없으면 생성
			deleteFolder.mkdirs();
		}
		
		if(deleteFolderList != null && deleteFolderList.length > 0) {
			for (File file : deleteFolderList) {
				if(file.getName().indexOf("json_role_" + roleSn + "_") != -1) {
					new Thread() {
						@Override
						public void run() {
							logger.info("파일 삭제 처리");
							file.delete();
						}
					}.start();
				}
			}
		}
		
		mngrMenuAccVO = new MngrMenuAccVO();
		mngrMenuAccVO.setRoleSn(roleSn);
		accList = mngrMenuAccService.selectList(mngrMenuAccVO);
		
		if(accList != null && accList.size() > 0) {
			for (MngrMenuAccVO item : accList) {
				String str = "{\n" +
						"\t\"powR\" : \"" + item.getAuthorRedng() + "\",\n" +
						"\t\"powW\" : \"" + item.getAuthorRegist() + "\"\n" +
					"}";
				
				BufferedWriter bw = null;
				try {
					File file = new File(rootPath + "/json_role_" + roleSn + "_" + item.getMenuSn() + ".json");
					file.createNewFile();
					
					bw = new BufferedWriter(fsFuncCmmHelper.setFileWriter(file.getPath()));
					bw.write(str);
					bw.close();
				} finally {
					bw.close();
				}
			}
		}
	}

}
