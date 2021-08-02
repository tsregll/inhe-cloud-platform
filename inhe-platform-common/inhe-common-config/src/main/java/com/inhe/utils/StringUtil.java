package com.inhe.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fp295 on 2018/3/24.
 */
public class StringUtil {


    public static String clearSpace(String str) {
        return str.replaceAll(" ", "");
    }

    public static String[] clearSpace(String... str) {
        String[] temps = new String[str.length];

        for(int i = 0; i < str.length; ++i) {
            temps[i] = str[i].replaceAll(" ", "");
        }

        return temps;
    }
    
    public static String getRemoteAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
	}
    
    /**
	 * 压缩字符串为zip
	 * @param str
	 * @return
	 * @throws IOException
	 */
    public static byte[] compress(String str) throws IOException {
	    if (str == null || str.length() == 0) {
	      return "".getBytes();
	    }
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    GZIPOutputStream gzip = new GZIPOutputStream(out);
	    gzip.write(str.getBytes());
	    gzip.close();
	    return out.toByteArray();
	}
    
    /**
     * 解压缩字符串
     * @param str
     * @return
     * @throws IOException
     */
    public static String uncompress(String str) throws IOException {
		if (str == null || str.length() == 0) {
			return str;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
		GZIPInputStream gunzip = new GZIPInputStream(in);
		byte[] buffer = new byte[256];
		int n;
		while ((n = gunzip.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}
		return out.toString();
    }
}
