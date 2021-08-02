package com.inhe.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

import com.inhe.build.exception.InheExceptionBase;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PwdUtil {
	
	public static String encodePwd(String pwd){
		Argon2 argon2 = Argon2Factory.create();
		String hash = argon2.hash(2, 65536, 4, pwd, StandardCharsets.UTF_8);
		return Base64Util.encode(hash);
	}
	
	public static boolean checkPwd(String savePwd, String inputPwd){
		Argon2 argon2 = Argon2Factory.create();
		if(argon2.verify(Base64Util.decode(savePwd), inputPwd, StandardCharsets.UTF_8)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static String sha256(String pwd){
		MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pwd.getBytes("UTF-8"));
            String output = Hex.encodeHexString(hash);
            return output;
        } catch (NoSuchAlgorithmException e) {
        	
        	throw new InheExceptionBase(-100000,e.getMessage());
        } catch (UnsupportedEncodingException e) {
        	
        	throw new InheExceptionBase(-100000,e.getMessage());
        }
	}
	
}
