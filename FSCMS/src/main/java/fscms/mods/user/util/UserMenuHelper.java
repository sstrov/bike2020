package fscms.mods.user.util;

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
import fscms.mods.user.service.UserMenuAccService;
import fscms.mods.user.service.UserMenuService;
import fscms.mods.user.vo.UserMenuAccVO;
import fscms.mods.user.vo.UserMenuVO;
import net.sf.json.JSONArray;

@Component
public class UserMenuHelper {
	
	static Logger logger = LogManager.getLogger(UserMenuHelper.class);
	
	@Resource(name = "userMenuService")
	private UserMenuService userMenuService;
	
	@Resource(name = "userMenuAccService")
	private UserMenuAccService userMenuAccService;
	
	/**
	 * @Method Name	: setMenu
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 메뉴 템플릿 셋팅
	 */
	public void setMenu() throws Exception {
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		String rootPath = fsFuncCmmHelper.getAppAbsolutePath() + "/repository/www/menu";
		String tplPath  = fsFuncCmmHelper.getAppAbsolutePath() + "/template/www/menu";
		
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
		
		UserMenuVO userMenuVO = new UserMenuVO();
		userMenuVO.setMenuDp(1);
		List<UserMenuVO> menuList = userMenuService.selectList(userMenuVO);
		
		// 상단 메뉴 생성
		File tplFileTop = new File(tplPath + "/top.tpl");
		if(fsFuncCmmHelper.checkFileExists(tplFileTop)) {
			this.setMenu_top(rootPath, menuList, tplFileTop, fsFuncCmmHelper);
		}
		
		// 사이트맵 생성
		File tplFileSitemap = new File(tplPath + "/sitemap.tpl");
		if(fsFuncCmmHelper.checkFileExists(tplFileSitemap)) {
			this.setMenu_sitemap(rootPath, menuList, tplFileSitemap, fsFuncCmmHelper);
		}
		
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
		
		// 왼쪽 메뉴 생성
		File tplFileLeft = new File(tplPath + "/left.tpl");
		if(fsFuncCmmHelper.checkFileExists(tplFileLeft)) {
			BufferedReader br = null;
			try {
				char[] ch = new char[(int) tplFileLeft.length()];
				br = new BufferedReader(fsFuncCmmHelper.getFileRead(tplFileLeft));
				br.read(ch);
	
				StringBuilder str = new StringBuilder();
				str.append(ch);
	
				br.close();
				
				String content = str.toString();
				content = content.replaceAll("[^\\u0009\\u000A\\u000D\\u0020-\\uD7FF\\uE000-\\uFFFD\\u10000-\\u10FFF]+", "");
	
				this.setMenu_left(rootPath, menuList, content, fsFuncCmmHelper);
			} finally {
				br.close();
			}
		}
		
		// 왼쪽 탭 메뉴 생성
		File tplFileLeftTab = new File(tplPath + "/leftTab.tpl");
		if(fsFuncCmmHelper.checkFileExists(tplFileLeftTab)) {
			BufferedReader br = null;
			try {
				char[] ch = new char[(int) tplFileLeftTab.length()];
				br = new BufferedReader(fsFuncCmmHelper.getFileRead(tplFileLeftTab));
				br.read(ch);
	
				StringBuilder str = new StringBuilder();
				str.append(ch);
	
				br.close();
				
				String content = str.toString();
				content = content.replaceAll("[^\\u0009\\u000A\\u000D\\u0020-\\uD7FF\\uE000-\\uFFFD\\u10000-\\u10FFF]+", "");
				
				int maxG = userMenuService.selectMaxG(null);
	
				this.setMenu_leftTab(rootPath, menuList, content, maxG, fsFuncCmmHelper);
			} finally {
				br.close();
			}
		}
		
		// 메뉴 JSON 생성
		this.setMenu_json(rootPath, menuList);
	}
	
	/**
	 * @Method Name	: setMenu_top
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 상단 메뉴 생성
	 */
	private void setMenu_top(String rootPath, List<UserMenuVO> menuList, File tplFile, FsFuncCmmHelper fsFuncCmmHelper) throws Exception {
		char[] ch = new char[(int) tplFile.length()];
		BufferedReader br = new BufferedReader(fsFuncCmmHelper.getFileRead(tplFile));
		br.read(ch);
		
		StringBuilder str = new StringBuilder();
		str.append(ch);
		
		br.close();
		
		String content = str.toString();
		// 유니코드 문자열 삭제 처리 (해당 처리를하지 않으면 특수문자가 추가되어 실행에 오류를 낼 수 있다.)
		content = content.replaceAll("[^\\u0009\\u000A\\u000D\\u0020-\\uD7FF\\uE000-\\uFFFD\\u10000-\\u10FFF]+", "");
		
		if(StringUtils.isNotEmpty(content)) {
			Pattern pattern = Pattern.compile(fsFuncCmmHelper.getRegPattern("1depth", 1));
			Matcher match = pattern.matcher(content);
			
			if(match.find()) {
				content = match.replaceAll(Matcher.quoteReplacement(this.setMenu_topTpl(menuList, match.group(1), 1, fsFuncCmmHelper)));
			}
		}
		
		File file = new File(rootPath + "/top.jsp");
		BufferedWriter bw = null;
		try {
			file.createNewFile();
			
			String tmpStr = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n" +
					"<%@ include file=\"/WEB-INF/jsp/library.jsp\" %>\n" +
					"<%@ taglib uri=\"/WEB-INF/tld/fs-userRole.tld\" prefix=\"roleUser\" %>\n\n";
			
			bw = new BufferedWriter(fsFuncCmmHelper.setFileWriter(file.getPath()));
			bw.write(tmpStr + content);
			bw.close();
		} finally {
			bw.close();
		}
	}
	
	/**
	 * @Method Name	: setMenu_topTpl
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 상단 템플릿 상세내용 가져오기
	 */
	private String setMenu_topTpl(List<UserMenuVO> menuList, String tpl, int depth, FsFuncCmmHelper fsFuncCmmHelper) throws Exception {
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
				
				for (UserMenuVO item : menuList) {
					if(StringUtils.equals(item.getUseAt(), "N") || StringUtils.equals(item.getActvtyAt(), "N")) {
						continue;
					}
					
					String depContent = mc.group(1);
					List<UserMenuVO> childList = null;
					
					Pattern pt_dep = Pattern.compile(patten);
					Matcher mc_dep = pt_dep.matcher(depContent);
					
					if(mc_dep.find()) {
						// 하위 메뉴 정보 셋팅
						UserMenuVO userMenuVO = new UserMenuVO();
						userMenuVO.setMenuUpperSn(item.getMenuSn());
						userMenuVO.setUseAt("Y");
						childList = userMenuService.selectList(userMenuVO);
						
						if(childList != null && childList.size() > 0) {
							depContent = mc_dep.replaceAll(Matcher.quoteReplacement(this.setMenu_topTpl(childList, mc_dep.group(1), item.getMenuDp() + 1, fsFuncCmmHelper)));
						} else {
							depContent = mc_dep.replaceAll("");
						}
					}
					
					String link = item.getMenuLink();
					depContent  = depContent.replaceAll("\\{link\\}",   link);
					depContent  = depContent.replaceAll("\\{target\\}", item.getMenuTrgt());
					depContent  = depContent.replaceAll("\\{name\\}",   item.getMenuNm());
					depContent  = depContent.replaceAll("\\{class\\}",  StringUtils.isNotEmpty(item.getMenuClass())? item.getMenuClass() : "");
					
					// 활성 여부
					Pattern pt_at = Pattern.compile(activePatten);
					Matcher mc_at = pt_at.matcher(depContent);
					
					if(mc_at.find()) {
						String activeStr = "<c:if test=\"${ !empty currentMenu && (currentMenu.menuSn eq '" + item.getMenuSn() + "' || roleUser:use(currentMenu.menuSn, '" + item.getMenuSn() + "')) }\">" +
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
					
					//tmpStr += "<c:if test=\"${ roleUser:acc('" + item.getMenuSn() + "', userSession.roleSn) }\">";
					tmpStr += depContent;
					//tmpStr += "</c:if>";
				}
				
				tpl = mc.replaceAll(Matcher.quoteReplacement(tmpStr));
			} else {
				tpl = mc.replaceAll("");
			}
		}
		
		return tpl;
	}
	
	/**
	 * @Method Name	: setMenu_sitemap
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 사이트맵 메뉴 생성
	 */
	public void setMenu_sitemap(String rootPath, List<UserMenuVO> menuList, File tplFile, FsFuncCmmHelper fsFuncCmmHelper) throws Exception {
		char[] ch = new char[(int) tplFile.length()];
		BufferedReader br = new BufferedReader(fsFuncCmmHelper.getFileRead(tplFile));
		br.read(ch);
		
		StringBuilder str = new StringBuilder();
		str.append(ch);
		
		br.close();
		
		String content = str.toString();
		// 유니코드 문자열 삭제 처리 (해당 처리를하지 않으면 특수문자가 추가되어 실행에 오류를 낼 수 있다.)
		content = content.replaceAll("[^\\u0009\\u000A\\u000D\\u0020-\\uD7FF\\uE000-\\uFFFD\\u10000-\\u10FFF]+", "");

		if(StringUtils.isNotEmpty(content)) {
			Pattern pattern = Pattern.compile(fsFuncCmmHelper.getRegPattern("1depth", 1));
			Matcher match = pattern.matcher(content);
			
			if(match.find()) {
				content = match.replaceAll(Matcher.quoteReplacement(this.setMenu_topTpl(menuList, match.group(1), 1, fsFuncCmmHelper)));
			}
		}
				
		String tmpStr = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n" +
				"<%@ include file=\"/WEB-INF/jsp/library.jsp\" %>\n" +
				"<%@ taglib uri=\"/WEB-INF/tld/fs-userRole.tld\" prefix=\"roleUser\" %>\n\n";

		File file = new File(rootPath + "/sitemap.jsp");
		BufferedWriter bw = null;
		try {
			file.createNewFile();
			
			bw = new BufferedWriter(fsFuncCmmHelper.setFileWriter(file.getPath()));
			bw.write(tmpStr + content);
			bw.close();
		} finally {
			bw.close();
		}
	}
	
	/**
	 * @Method Name	: setMenu_navi
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 네비 메뉴 생성
	 */
	private void setMenu_navi(String rootPath, List<UserMenuVO> menuList, String content, FsFuncCmmHelper fsFuncCmmHelper) throws Exception {
		if(menuList != null && menuList.size() > 0) {
			for (UserMenuVO item : menuList) {
				String cnt     = content;
				String jsonStr = "";
				String pattenS = fsFuncCmmHelper.getRegPattern("1depth_l", 1);
				
				Pattern pt = Pattern.compile(pattenS);
				Matcher mc = pt.matcher(cnt);
				
				if(mc.find()) {
					String tmpStr = "";
					
					// 네비 목록 조회
					List<UserMenuVO> naviList = this.getNaviList(item.getMenuSn());
					
					if(naviList != null && naviList.size() > 0) {
						int i = 1;
						for (UserMenuVO navi : naviList) {
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
				UserMenuVO userMenuVO = new UserMenuVO();
				userMenuVO.setMenuUpperSn(item.getMenuSn());
				List<UserMenuVO> childList = userMenuService.selectList(userMenuVO);
				
				if(childList != null && childList.size() > 0) {
					this.setMenu_navi(rootPath, childList, content, fsFuncCmmHelper);
				}
			}
		}
	}
	
	/**
	 * @Method Name	: getNaviList
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 네비 목록 조회
	 */
	private List<UserMenuVO> getNaviList(String menuSn) throws Exception {
		List<UserMenuVO> itemList = new ArrayList<UserMenuVO>();
		
		if(StringUtils.isNotEmpty(menuSn)) {
			UserMenuVO userMenuVO = new UserMenuVO();
			userMenuVO.setMenuSn(menuSn);
			UserMenuVO item = userMenuService.selectObj(userMenuVO);
			
			if(item != null) {
				if(StringUtils.isNotEmpty(item.getMenuUpperSn())) {
					itemList = this.getNaviList(item.getMenuUpperSn());
				}
				
				itemList.add(item);
			}
		}
		
		return itemList;
	}
	
	/**
	 * @Method Name	: setMenu_left
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 왼쪽 메뉴 생성
	 */
	private void setMenu_left(String rootPath, List<UserMenuVO> menuList, String content, FsFuncCmmHelper fsFuncCmmHelper) throws Exception {
		if(menuList != null && menuList.size() > 0) {
			for (UserMenuVO item : menuList) {
				String cnt = content;
				
				Pattern pt = Pattern.compile(fsFuncCmmHelper.getRegPattern("1depth", 1));
				Matcher mc = pt.matcher(cnt);
				
				if(mc.find()) {
					// 네비 목록 조회
					List<UserMenuVO> naviList = this.getNaviList(item.getMenuSn());
					cnt = mc.replaceAll(Matcher.quoteReplacement(this.setMenu_leftTpl(menuList, naviList, mc.group(1), 1, fsFuncCmmHelper)));
				}
				
				// 왼쪽메뉴 파일 생성
				File file = new File(rootPath + "/left_" + item.getMenuSn() + ".jsp");
				BufferedWriter bw = null;
				try {
					file.createNewFile();
					
					String tmpStr = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n" +
							"<%@ include file=\"/WEB-INF/jsp/library.jsp\" %>\n" +
							"<%@ taglib uri=\"/WEB-INF/tld/fs-userRole.tld\" prefix=\"roleUser\" %>\n\n";
					
					bw = new BufferedWriter(fsFuncCmmHelper.setFileWriter(file.getPath()));
					bw.write(tmpStr + cnt);
					bw.close();
				} finally {
					bw.close();
				}
			}
		}
	}
	
	/**
	 * @Method Name	: setMenu_leftTpl
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 왼쪽 템플릿 상세내용 가져오기
	 */
	private String setMenu_leftTpl(List<UserMenuVO> menuList, List<UserMenuVO> naviList, String tpl, int depth, FsFuncCmmHelper fsFuncCmmHelper) throws Exception {
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
				
				for (UserMenuVO item : menuList) {
					if(StringUtils.equals(item.getUseAt(), "N")
							|| (item.getMenuDp() > 1 && StringUtils.equals(item.getActvtyAt(), "N"))
							|| naviList.get(0) == null
							|| !StringUtils.equals(item.getMenuBestSn(), naviList.get(0).getMenuBestSn())) {
						continue;
					}
					
					String depContent = mc.group(1);
					List<UserMenuVO> childList = null;
					
					Pattern pt_dep = Pattern.compile(patten);
					Matcher mc_dep = pt_dep.matcher(depContent);
					
					if(mc_dep.find()) {
						// 하위 메뉴 정보 셋팅
						UserMenuVO userMenuVO = new UserMenuVO();
						userMenuVO.setMenuUpperSn(item.getMenuSn());
						userMenuVO.setUseAt("Y");
						childList = userMenuService.selectList(userMenuVO);
						
						if(childList != null && childList.size() > 0) {
							depContent = mc_dep.replaceAll(Matcher.quoteReplacement(this.setMenu_leftTpl(childList, naviList, mc_dep.group(1), item.getMenuDp() + 1, fsFuncCmmHelper)));
						} else {
							depContent = mc_dep.replaceAll("");
						}
					}
					
					String link = item.getMenuLink();
					depContent  = depContent.replaceAll("\\{link\\}",   link);
					depContent  = depContent.replaceAll("\\{target\\}", item.getMenuTrgt());
					depContent  = depContent.replaceAll("\\{name\\}",   item.getMenuNm());
					
					// 활성 여부
					Pattern pt_at = Pattern.compile(activePatten);
					Matcher mc_at = pt_at.matcher(depContent);
					
					if(mc_at.find()) {
						String activeStr = "<c:if test=\"${ !empty currentMenu && (currentMenu.menuSn eq '" + item.getMenuSn() + "' || roleUser:use(currentMenu.menuSn, '" + item.getMenuSn() + "')) }\">" +
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
					
					//tmpStr += "<c:if test=\"${ roleUser:acc('" + item.getMenuSn() + "', userSession.roleSn) }\">";
					tmpStr += depContent;
					//tmpStr += "</c:if>";
				}
				
				tpl = mc.replaceAll(Matcher.quoteReplacement(tmpStr));
			} else {
				tpl = mc.replaceAll("");
			}
		}
		
		return tpl;
	}
	
	/**
	 * @Method Name	: setMenu_leftTab
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 왼쪽 탭 메뉴 생성
	 */
	private void setMenu_leftTab(String rootPath, List<UserMenuVO> menuList, String content, int maxG, FsFuncCmmHelper fsFuncCmmHelper) throws Exception {
		if(menuList != null && menuList.size() > 0) {
			for (UserMenuVO item : menuList) {
				String cnt = content;
				
				for(int i=1; i<=maxG; i++) {
					Pattern pt = Pattern.compile(fsFuncCmmHelper.getRegPattern(i + "depth", i));
					Matcher mc = pt.matcher(cnt);
					
					if(mc.find()) {
						if(i > item.getMenuDp()) {
							cnt = mc.replaceAll("");
						} else {
							// 네비 목록 조회
							List<UserMenuVO> naviList = this.getNaviList(item.getMenuSn());
							
							cnt = mc.replaceAll(Matcher.quoteReplacement(this.setMenu_leftTabTpl(menuList, naviList, mc.group(1), i, fsFuncCmmHelper)));
						}
					}
				}
				
				// 왼쪽메뉴 파일 생성
				File file = new File(rootPath + "/leftTab_" + item.getMenuSn() + ".jsp");
				BufferedWriter bw = null;
				try {
					file.createNewFile();
				
					String tmpStr = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n" +
							"<%@ include file=\"/WEB-INF/jsp/library.jsp\" %>\n" +
							"<%@ taglib uri=\"/WEB-INF/tld/fs-userRole.tld\" prefix=\"roleUser\" %>\n\n";
					
					bw = new BufferedWriter(fsFuncCmmHelper.setFileWriter(file.getPath()));
					bw.write(tmpStr + cnt);
					bw.close();
				} finally {
					bw.close();
				}
				
				// 하위 정보가 있으면 재귀
				UserMenuVO userMenuVO = new UserMenuVO();
				userMenuVO.setMenuUpperSn(item.getMenuSn());
				List<UserMenuVO> childList = userMenuService.selectList(userMenuVO);
				
				if(childList != null && childList.size() > 0) {
					this.setMenu_leftTab(rootPath, childList, content, maxG, fsFuncCmmHelper);
				}
			}
		}
	}
	
	/**
	 * @Method Name	: setMenu_leftTabTpl
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 왼쪽 탭 템플릿 상세내용 가져오기
	 */
	private String setMenu_leftTabTpl(List<UserMenuVO> menuList, List<UserMenuVO> naviList, String tpl, int depth, FsFuncCmmHelper fsFuncCmmHelper) throws Exception {
		String pattenS        = fsFuncCmmHelper.getRegPattern(depth + "depth_l", depth);
		String activePatten   = fsFuncCmmHelper.getRegPattern("active", null);
		String naviMenuPatten = fsFuncCmmHelper.getRegPattern("naviMenu", null);
		
		Pattern pt = Pattern.compile(pattenS);
		Matcher mc = pt.matcher(tpl);
		
		if(mc.find()) {
			if(depth == 1) {
				String tmpStr = "";
				UserMenuVO userMenuVO = new UserMenuVO();
				userMenuVO.setMenuDp(1);
				List<UserMenuVO> itemList = userMenuService.selectList(userMenuVO);
				
				if(itemList != null && itemList.size() > 0) {
					for (UserMenuVO item : itemList) {
						if(StringUtils.equals(item.getUseAt(), "N")
								|| StringUtils.equals(item.getActvtyAt(), "N")
								|| naviList.get(0) == null
								/*|| StringUtils.equals(item.getMenuSn(), naviList.get(0).getMenuSn())*/) {
							continue;
						}
						
						String depContent = mc.group(1);
		
						String link = item.getMenuLink();
						depContent = depContent.replaceAll("\\{link\\}",   link);
						depContent = depContent.replaceAll("\\{target\\}", item.getMenuTrgt());
						depContent = depContent.replaceAll("\\{name\\}",   item.getMenuNm());
						
						// 활성 여부
						Pattern pt_at = Pattern.compile(activePatten);
						Matcher mc_at = pt_at.matcher(depContent);
						
						if(mc_at.find()) {
							String activeStr = "<c:if test=\"${ !empty currentMenu && (currentMenu.menuSn eq '" + item.getMenuSn() + "' || roleUser:use(currentMenu.menuSn, '" + item.getMenuSn() + "')) }\">" +
									mc_at.group(1) +
									"</c:if>";
							depContent = mc_at.replaceAll(Matcher.quoteReplacement(activeStr));
						}
						
						//tmpStr += "<c:if test=\"${ roleUser:acc('" + item.getMenuSn() + "', userSession.roleSn) }\">";
						tmpStr += depContent;
						//tmpStr += "</c:if>";
					}
					
					tpl = mc.replaceAll(Matcher.quoteReplacement(tmpStr));
				} else {
					tpl = mc.replaceAll("");
				}
			} else {
				List<UserMenuVO> itemList = null;
				if(naviList.size() > depth - 1) {
					UserMenuVO userMenuVO = new UserMenuVO();
					userMenuVO.setMenuUpperSn(naviList.get(depth - 1).getMenuUpperSn());
					userMenuVO.setMenuDp(depth);
					itemList = userMenuService.selectList(userMenuVO);
				}
				
				String tmpStr = "";
				if(itemList != null && itemList.size() > 0) {
					for (UserMenuVO item : itemList) {
						if(StringUtils.equals(item.getUseAt(), "N")
								|| StringUtils.equals(item.getActvtyAt(), "N")
								|| naviList.get(0) == null
								/*|| StringUtils.equals(item.getMenuSn(), naviList.get(0).getMenuSn())*/) {
							continue;
						}
						
						String depContent = mc.group(1);
						
						String link = item.getMenuLink();
						depContent = depContent.replaceAll("\\{link\\}",   link);
						depContent = depContent.replaceAll("\\{target\\}", item.getMenuTrgt());
						depContent = depContent.replaceAll("\\{name\\}",   item.getMenuNm());
						
						// 활성 여부
						Pattern pt_at = Pattern.compile(activePatten);
						Matcher mc_at = pt_at.matcher(depContent);
						
						if(mc_at.find()) {
							String activeStr = "<c:if test=\"${ !empty currentMenu && (currentMenu.menuSn eq '" + item.getMenuSn() + "' || roleUser:use(currentMenu.menuSn, '" + item.getMenuSn() + "')) }\">" +
									mc_at.group(1) +
									"</c:if>";
							depContent = mc_at.replaceAll(Matcher.quoteReplacement(activeStr));
						}
						
						//tmpStr += "<c:if test=\"${ roleUser:acc('" + item.getMenuSn() + "', userSession.roleSn) }\">";
						tmpStr += depContent;
						//tmpStr += "</c:if>";
					}
					
					tpl = mc.replaceAll(Matcher.quoteReplacement(tmpStr));
				} else {
					tpl = mc.replaceAll("");
				}
			}
		}
		
		// 타이틀 메뉴 정보
		Pattern ptNavi = Pattern.compile(naviMenuPatten);
		Matcher mcNavi = ptNavi.matcher(tpl);
		
		if(mcNavi.find()) {
			if(naviList.size() > depth - 1) {
				UserMenuVO menu    = naviList.get(depth - 1);
				String depContent = mcNavi.group(1);
				
				String link = menu.getMenuLink();
				depContent = depContent.replaceAll("\\{link\\}",   link);
				depContent = depContent.replaceAll("\\{target\\}", menu.getMenuTrgt());
				depContent = depContent.replaceAll("\\{name\\}",   menu.getMenuNm());
				
				tpl = mcNavi.replaceAll(Matcher.quoteReplacement(depContent));
			} else {
				tpl = mcNavi.replaceAll("");
			}
		}
		
		return tpl;
	}
	
	// 메뉴 JSON 생성
	private void setMenu_json(String rootPath, List<UserMenuVO> menuList) throws Exception {
		
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		if(menuList != null && menuList.size() > 0) {
			for (UserMenuVO item : menuList) {
				
				// 최상위 데이터 조회
				UserMenuVO userMenuVO = new UserMenuVO();
				userMenuVO.setMenuSn(item.getMenuBestSn());
				UserMenuVO fItem = userMenuService.selectObj(userMenuVO);
				
				// 상위 데이터 조회
				String pNm = "";
				if(StringUtils.isNotEmpty(item.getMenuUpperSn())) {
					// 상위 일련번호가 있으면 상위 정보 조회
					userMenuVO = new UserMenuVO();
					userMenuVO.setMenuSn(item.getMenuUpperSn());
					UserMenuVO pItem = userMenuService.selectObj(userMenuVO);
					
					if(pItem != null) {
						pNm = pItem.getMenuNm();
					}
				}
				
				// 서브 이미지 조회
				String subImg = "";
				if(StringUtils.isNotEmpty(item.getMenuFileImage())) {
					subImg = item.getMenuFlpth() + "/" + item.getMenuFileImage();
				} else {
					// 현재 메뉴에서 정보가 없다면 네비에서 가져온다.
					List<UserMenuVO> naviList = this.getNaviList(item.getMenuSn());
					
					if(naviList != null && naviList.size() > 0) {
						for (UserMenuVO navi : naviList) {
							if(StringUtils.equals(navi.getMenuSn(), item.getMenuSn())) {
								continue;
							}
							
							if(StringUtils.isNotEmpty(navi.getMenuFileImage())) {
								subImg = navi.getMenuFlpth() + "/" + navi.getMenuFileImage();
								break;
							}
						}
					}
				}
				
				String str = "{\n" +
						"\t\"accKey\"   : \"" + item.getAccSn() + "\",\n" +
						"\t\"fKey\"     : \"" + item.getMenuBestSn() + "\",\n" +
						"\t\"fNm\"      : \"" + fItem.getMenuNm() + "\",\n" +
						"\t\"pKey\"     : \"" + item.getMenuUpperSn() + "\",\n" +
						"\t\"pNm\"      : \"" + pNm + "\",\n" +
						"\t\"menuG\"    : \"" + item.getMenuDp() + "\",\n" +
						"\t\"nm\"       : \"" + item.getMenuNm() + "\",\n" +
						"\t\"page\"     : \"" + item.getMenuCnncTy() + "\",\n" +
						"\t\"target\"   : \"" + item.getMenuTrgt() + "\",\n" +
						"\t\"tag\"      : \"" + item.getMenuTag() + "\",\n" +
						"\t\"class\"    : \"" + item.getMenuClass() + "\",\n" +
						"\t\"fileImage\": \"" + item.getMenuFileImage() + "\",\n" +
						"\t\"filePath\" : \"" + item.getMenuFlpth() + "\",\n" +
						"\t\"actAt\"    : \"" + item.getActvtyAt() + "\",\n" +
						"\t\"useAt\"    : \"" + item.getUseAt() + "\",\n" +
						"\t\"link\"     : \"" + item.getMenuLink() + "\",\n" +
						"\t\"subImg\"   : \"" + subImg + "\"\n" +
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
				userMenuVO = new UserMenuVO();
				userMenuVO.setMenuUpperSn(item.getMenuSn());
				List<UserMenuVO> childList = userMenuService.selectList(userMenuVO);
				
				if(childList != null && childList.size() > 0) {
					this.setMenu_json(rootPath, childList);
				}
			}
		}
	}
	
	/**
	 * @Method Name	: getJson
	 * @작성일		: 2019. 10. 15.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 메뉴 정보 조회 - JSON
	 */
	public UserMenuVO getJson(String menuKey) {
		
		JSONObject jsonObject = null;
		try {
			jsonObject = new JsonConnHelper().getJsonFile("/www/menu/json_menu_" + menuKey);
			
			if(jsonObject != null) {
				String accKey    = (String) jsonObject.get("accKey");
				String fKey      = (String) jsonObject.get("fKey");
				String fNm       = (String) jsonObject.get("fNm");
				String pKey      = (String) jsonObject.get("pKey");
				String pNm       = (String) jsonObject.get("pNm");
				String menuG     = (String) jsonObject.get("menuG");
				String nm        = (String) jsonObject.get("nm");
				String page      = (String) jsonObject.get("page");
				String target    = (String) jsonObject.get("target");
				String tag       = (String) jsonObject.get("tag");
				String menuClass = (String) jsonObject.get("class");
				String fileImage = (String) jsonObject.get("fileImage");
				String filePath  = (String) jsonObject.get("filePath");
				String actAt     = (String) jsonObject.get("actAt");
				String useAt     = (String) jsonObject.get("useAt");
				String link      = (String) jsonObject.get("link");
				String subImg    = (String) jsonObject.get("subImg");
				jsonObject.clear();
				
				UserMenuVO menu = new UserMenuVO();
				menu.setMenuSn(menuKey);
				menu.setAccSn((StringUtils.isNotEmpty(accKey) && !"null".equals(accKey))? Integer.parseInt(accKey) : null);
				menu.setMenuBestSn(fKey);
				if(StringUtils.isNotEmpty(pKey) && !"null".equals(pKey)) {
					menu.setMenuUpperSn(pKey);
				}
				menu.setMenuDp(Integer.parseInt(menuG));
				menu.setMenuNm(nm);
				menu.setfNm(fNm);
				menu.setpNm(pNm);
				menu.setMenuCnncTy(page);
				menu.setMenuTrgt(target);
				menu.setMenuTag((StringUtils.isNotEmpty(tag) && !StringUtils.equals(tag, "null"))? tag : "");
				menu.setMenuClass((StringUtils.isNotEmpty(menuClass) && !StringUtils.equals(menuClass, "null"))? menuClass : "");
				menu.setMenuFileImage((StringUtils.isNotEmpty(fileImage) && !StringUtils.equals(fileImage, "null"))? fileImage : "");
				menu.setMenuFlpth((StringUtils.isNotEmpty(filePath) && !StringUtils.equals(filePath, "null"))? filePath : "");
				menu.setActvtyAt(actAt);
				menu.setUseAt(useAt);
				menu.setMenuLink(link);
				menu.setSubImg((StringUtils.isNotEmpty(subImg) && !StringUtils.equals(subImg, "null"))? subImg : "");
				
				return menu;
			}
		} catch (IOException | ParseException e) {
			return null;
		}
		return null;
	}
	
	// 권한 설정
	public void setAcc(HttpServletRequest request, String mngrId, int roleSn) throws Exception {
		
		UserMenuAccVO userMenuAccVO = new UserMenuAccVO();
		userMenuAccVO.setRoleSn(roleSn);
		List<UserMenuAccVO> accList = userMenuAccService.selectList(userMenuAccVO);
		
		if(accList != null && accList.size() > 0) {
			for (UserMenuAccVO item : accList) {
				String bfData = JSONArray.fromObject(item).toString();
				userMenuAccService.deleteInfo(request, bfData, item, mngrId);
			}
		}
		
		// 권한 정보 저장
		String[] pow = request.getParameterValues("pow");
		
		if(pow != null && pow.length > 0) {
			for (int i=0; i<pow.length; i++) {
				String menuSn = pow[i];
				
				userMenuAccVO = new UserMenuAccVO();
				userMenuAccVO.setMenuSn(menuSn);
				userMenuAccVO.setRoleSn(roleSn);
				userMenuAccService.insertInfo(request, userMenuAccVO, mngrId);
			}
		}
		
		// 권한 폴더 생성 및 삭제
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		String rootPath = fsFuncCmmHelper.getAppAbsolutePath() + "/repository/www/role";
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
		
		userMenuAccVO = new UserMenuAccVO();
		userMenuAccVO.setRoleSn(roleSn);
		accList = userMenuAccService.selectList(userMenuAccVO);
		
		if(accList != null && accList.size() > 0) {
			for (UserMenuAccVO item : accList) {
				String str = "{\n" +
						"\t\"pow\" : \"Y\"\n" +
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
	
	// 파일 삭제
	public void deleteFile(String menuKey) throws Exception {
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		
		UserMenuVO userMenuVO = new UserMenuVO();
		userMenuVO.setMenuSn(menuKey);
		UserMenuVO item = userMenuService.selectObj(userMenuVO);
		
		if(item != null) {
			String cPath = fsFuncCmmHelper.getAppAbsolutePath();	// 컨텍스트 절대경로
			
			// 파일 정보가 있으면 삭제
			if(StringUtils.isNotEmpty(item.getMenuFileImage())) {
				File file = new File(cPath + item.getMenuFlpth() + "/" + item.getMenuFileImage());
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

}
