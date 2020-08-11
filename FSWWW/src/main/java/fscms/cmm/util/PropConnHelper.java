package fscms.cmm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fscms.cmm.exception.WrongApproachException;

/**
 * @FileName  	: PropConnHelper.java
 * @Project   	: FS_CMS
 * @Date      	: 2018. 11. 27.
 * @작성자		: Edmund.J
 * @변경이력    	: 
 * @프로그램 설명 	: Properties 정보 가져오기
 */
public class PropConnHelper {
	
	static Logger logger = LogManager.getLogger(PropConnHelper.class);
	
	/**
	 * @Method Name	: getConn
	 * @작성일   		: 2018. 11. 27.
	 * @작성자   		: Edmund.J
	 * @변경이력  		:
	 * @Method 설명 	: 설정관련 Properties 가져오기
	 * @param propName
	 * @param val
	 * @return
	 * @throws IOException
	 */
	public String getConn(String propName, String val) throws IOException {
		FileInputStream fis = null;
		String rtn = null;
		try {
			String sPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getAbsolutePath();
			
			if(StringUtils.isNotEmpty(sPath)) {
				Properties props = new Properties();
				fis = new FileInputStream(sPath + "/egovframework/egovProps/" + propName + ".properties");
				props.load(fis);
				fis.close();
				
				rtn = props.getProperty(val);
			}
		} catch (IOException e) {
			logger.info("설정 파일이 없습니다." + e.getMessage());
			throw new WrongApproachException("C0021");
		} finally {
			fis.close();
		}
		
		return rtn;
	}
	
	/**
	 * @Method Name	: getMessage
	 * @작성일   		: 2018. 11. 27.
	 * @작성자   		: Edmund.J
	 * @변경이력  		:
	 * @Method 설명 	: 메시지 관련 Properties 가져오기
	 * @param lang
	 * @param val
	 * @return
	 * @throws IOException
	 */
	public String getMessage(String lang, String val) throws IOException {
		FileInputStream fis = null;
		String rtn = null;
		try {
			String sPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getAbsolutePath();
			
			if(StringUtils.isNotEmpty(sPath)) {
				Properties props = new Properties();
				fis = new FileInputStream(sPath + "/egovframework/message/message-common" + lang + ".properties");
				
				//loading properites from properties file
				props.load(fis);
				fis.close();
				
				rtn = props.getProperty(val);
			}
		} catch (IOException e) {
			logger.info("설정 파일이 없습니다." + e.getMessage());
			//throw new WrongApproachException();
		} finally {
			fis.close();
		}
		
		return rtn;
	}

}
