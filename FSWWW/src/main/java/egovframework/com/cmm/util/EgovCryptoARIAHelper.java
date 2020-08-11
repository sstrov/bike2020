package egovframework.com.cmm.util;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.cryptography.EgovCryptoService;
import egovframework.rte.fdl.cryptography.EgovPasswordEncoder;

@Service("egovCryptoARIAHelper")
public class EgovCryptoARIAHelper {
	
	@Resource(name = "ARIACryptoService")
	EgovCryptoService cryptoService;
	
	@Resource(name = "passwordEncoder")
	EgovPasswordEncoder passwordEncoder;
	
	public static String plainPassword = "FSCMS*8822";
	public static String passwdAlgorithm = EgovProperties.getProperty("crypto.hashed.password");
	
	/**
	 * @Method Name	: encrypt
	 * @작성일		: 2019. 10. 2.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 기본 암호화 고정된 솔트값 암호화 (아이디 암호화 시 사용)
	 *                시스템에 설정된 암호화 값으로 아이디를 암호화 한다.
	 */
	public String encrypt(String plainText) throws UnsupportedEncodingException {
		
		byte[] encrypted = cryptoService.encrypt(plainText.getBytes("UTF-8"), plainPassword);
		
		String encodeText = Base64.encodeBase64String(encrypted);
		
		return encodeText;
	}
	
	/**
	 * @Method Name	: encrypt
	 * @작성일		: 2019. 10. 2.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 솔트 암호화 (개인정보 암호화 시 사용)
	 *                입력된 정보 + 솔트값으로 암호화를 한다.
	 */
	public String encrypt(String plainText, String salt) throws UnsupportedEncodingException {
		
		byte[] encrypted = cryptoService.encrypt((salt + plainText).getBytes("UTF-8"), plainPassword);
		
		String encodeText = Base64.encodeBase64String(encrypted);
		
		return encodeText;
	}
	
	/**
	 * @Method Name	: decrypt
	 * @작성일		: 2019. 10. 2.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	:기본 복호화 고정된 솔트값 암호화 (아이디 복호화 시 사용)
	 *               시스템에 설정된 복호화 값으로 아이디를 복호화 한다.
	 */
	public String decrypt(String encodeText) throws UnsupportedEncodingException {
		
		byte[] base64dec = Base64.decodeBase64(encodeText);
		byte[] decrypted = cryptoService.decrypt(base64dec, plainPassword);
		
		String plainText = new String(decrypted, "UTF-8");
		
		return plainText;
	}
	
	/**
	 * @Method Name	: decrypt
	 * @작성일		: 2019. 10. 2.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 솔트 복호화 (개인정보 복호화 시 사용)
	 *                입력된 정보 + 솔트값으로 복호화를 한다.
	 */
	public String decrypt(String encodeText, String salt) throws UnsupportedEncodingException {
		
		byte[] base64dec = Base64.decodeBase64(encodeText);
		
		byte[] decrypted = cryptoService.decrypt(base64dec, plainPassword);
		
		String plainText = new String(decrypted, "UTF-8");
		plainText = plainText.replace(salt, "");
		
		return plainText;
	}

}
