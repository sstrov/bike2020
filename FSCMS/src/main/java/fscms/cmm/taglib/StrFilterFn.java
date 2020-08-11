package fscms.cmm.taglib;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class StrFilterFn {
	
	public static String getColumnNm(String str) {
		String tmpStr = "";
		if(StringUtils.isNotEmpty(str)) {
			char[] chs = str.toCharArray();
			
			for (char c : chs) {
				if(Character.isUpperCase(c)) {
					tmpStr += "_" + c;
				} else {
					tmpStr += ("" + c).toUpperCase();
				}
			}
		}
		
		return tmpStr;
	}

}
