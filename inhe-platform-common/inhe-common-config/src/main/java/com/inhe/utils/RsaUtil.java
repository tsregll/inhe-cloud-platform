package com.inhe.utils;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;

public class RsaUtil {
	
	
	/**
	 * 获取公钥和私钥
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getKeys() throws NoSuchAlgorithmException{
		
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        
        //公钥和私钥
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        
        
        //生成秘钥的字符串
        String rsaPublicKeyStr = Base64.encodeBase64String(rsaPublicKey.getEncoded());
        String rsaPrivateKeyStr = Base64.encodeBase64String(rsaPrivateKey.getEncoded());
        
        return "{\"public\":\""+rsaPublicKeyStr+"\",\"private\":\""+rsaPrivateKeyStr+"\"}";
	}
	
	/**
     * 私钥加密
     * @param key 私钥
     * @param securityStr 要加密的字符串
     * @return
     * @throws Exception 
     */
    public static String RSAEncodeForPrivate(String key,String securityStr) throws Exception{
        byte[] rsaPrivateKeyByte = Base64.decodeBase64(key);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKeyByte);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(securityStr.getBytes());
        String resultStr = Base64.encodeBase64String(result);
        return resultStr;
    }
    
    /**
     * 公钥解密
     * @param key 公钥
     * @param securityStr 加密的字符串
     * @return
     */
    public static String RSADecodeForPublic(String key,String securityStr) throws Exception{
        byte[] rsaPublicKeyByte = Base64.decodeBase64(key);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKeyByte);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(Base64.decodeBase64(securityStr));
        String resultStr = new String(result);
        return resultStr;
    }
    
    /**
     * 公钥加密
     * @param key 公钥
     * @param securityStr 加密的字符串
     * @return
     */
    public static String RSAEncodeForPublic(String key,String securityStr) throws Exception{
        byte[] rsaPublicKeyByte = Base64.decodeBase64(key);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKeyByte);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(securityStr.getBytes());
        return Base64.encodeBase64String(result);
    }
    
    
    /**
     * 私钥解密
     * @param key 私钥
     * @param securityStr 要加密的字符串
     * @return
     * @throws Exception 
     */
    public static String RSADecodeForPrivate(String key,String securityStr) throws Exception{
        byte[] rsaPrivateKeyByte = Base64.decodeBase64(key);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKeyByte);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(Base64.decodeBase64(securityStr));
        return new String(result);
    }
}
