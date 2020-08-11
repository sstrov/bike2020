package fscms.cmm.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import egovframework.rte.fdl.idgnr.impl.Base64;

public class CryptoHelper {
	
	/**
	 * @Method Name	: encrypt
	 * @작성일		: 2019. 10. 2.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: SHA-512 암호화 (단방향 : 비밀번호 암호화 시 사용)
	 */
	public static String encrypt(String s, String key) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if(StringUtils.isEmpty(s)) {
			return "";
		}
		
		byte[] a = s.getBytes();
		byte[] b = key.getBytes("UTF-8");
		byte[] bytes = new byte[a.length + b.length];
		
		System.arraycopy(a, 0, bytes, 0, a.length);
		System.arraycopy(b, 0, bytes, a.length, b.length);
		
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(bytes);
		
		byte[] hashValue = md.digest();

		return new String(Base64.encode(hashValue));
	}
	
	/**
	 * @Method Name	: generateSalt
	 * @작성일		: 2019. 10. 1.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 솔트 일련번호 생성
	 */
	public static String generateSalt() {
		SecureRandom secRandom = new SecureRandom();

		byte[] salt = new byte[16];
		secRandom.nextBytes(salt);

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < salt.length; i++) {
			sb.append(String.format("%02x", salt[i]));
		}

		return sb.toString();
	}

}
