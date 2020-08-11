package fscms.cmm.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AES256Helper {
	
	private String iv;
	private Key keySpec;
	
	/**
	 * AES256 설정 솔트 일련번호로 암호화 키 생성
	 * @param key : 솔트 일련번호
	 * @throws UnsupportedEncodingException
	 */
	public AES256Helper(String key) throws UnsupportedEncodingException {
		this.iv = key.substring(0, 16);
		
		byte[] keyBytes = new byte[16];
		byte[] b = key.getBytes("UTF-8");
		int len = b.length;
		if (len > keyBytes.length) {
			len = keyBytes.length;
		}
		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		
		this.keySpec = keySpec;
	}
	
	/**
	 * @Method Name	: encode
	 * @작성일		: 2019. 10. 1.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 암호화
	 */
	public String encode(String str) throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
		
		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
		String enStr = new String(Base64.encodeBase64(encrypted));
		
		return enStr;
	}
	
	/**
	 * @Method Name	: decode
	 * @작성일		: 2019. 10. 1.
	 * @작성자		: Edmund
	 * @변경이력		:
	 * @Method 설명	: 복호화
	 */
	public String decode(String str) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));
		
		byte[] byteStr = Base64.decodeBase64(str.getBytes());
		
		return new String(c.doFinal(byteStr), "UTF-8");
	}

}
