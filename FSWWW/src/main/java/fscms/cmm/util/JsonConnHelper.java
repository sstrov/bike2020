package fscms.cmm.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public class JsonConnHelper {
	
	/**
	 * @Method Name	: getJsonFile
	 * @작성일   		: 2018. 11. 27.
	 * @작성자   		: Edmund.J
	 * @변경이력  		:
	 * @Method 설명 	: JSON 파일 호출
	 * @param fileNm
	 * @return
	 * @throws ParseException 
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 * @throws IOException
	 */
	public JSONObject getJsonFile(String fileNm) throws UnsupportedEncodingException, FileNotFoundException, IOException, ParseException {
		FsFuncCmmHelper fsFuncCmmHelper = new FsFuncCmmHelper();
		JSONParser parser = new JSONParser();
		File file = new File(fsFuncCmmHelper.getAppAbsolutePath() + "/repository" + fileNm + ".json");
		
		Reader re = fsFuncCmmHelper.getFileRead(file);
		if(re == null) {
			return null;
		}
		Object obj = parser.parse(re);
		
		JSONObject jsonObject = (JSONObject) obj;
		return jsonObject;
	}

}
