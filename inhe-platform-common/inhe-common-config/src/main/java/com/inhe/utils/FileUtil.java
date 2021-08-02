package com.inhe.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class FileUtil {

	private static int osType = -1;
	
	public static int getOsType(){
		if(FileUtil.osType == -1){
			String os = System.getProperty("os.name");
			if(os.toLowerCase().startsWith("win")){
				FileUtil.osType = 0;
			}
			else{
				FileUtil.osType = 1;
			}
		}
		return FileUtil.osType;
	}
	
	public static boolean createDir(String dirPath){
		if(FileUtil.osType==0){
			dirPath = dirPath.replace("/", "\\");
        }
        else{
        	dirPath = dirPath.replace("\\", "/");
        }
		File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
		return true;
	}
	
	/**
     * 向文件中写入内容
     * @param filepath 文件路径与名称
     * @param newstr  写入的内容
     * @return bool
     * @throws IOException
     */
	public static boolean writeFileContent(String filepath,String newstr, Boolean append) throws Exception{
		boolean bool = false;
		File file = null;
        FileOutputStream fileOut = null;
        if(FileUtil.osType==0){
        	filepath = filepath.replace("/", "\\");
        }
        else{
        	filepath = filepath.replace("\\", "/");
        }
        try {
        	file = new File(filepath);
        	if(!file.exists()){
        		file.createNewFile();
        	}
            fileOut = new FileOutputStream(file, append);
            fileOut.write(newstr.getBytes());
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            fileOut.close();
		}
        return bool;
    }
	
	/**
	 * 读取文件，并转换成数组
	 * @param filePath
	 * @return
	 */
	public static String readToString(String filePath){
        File file = new File(filePath);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String fileContent = new String(filecontent);
        return fileContent;
    }
	
	/**
	 * 删除文件
	 * @param filePath
	 * @return
	 */
	public static boolean delete(String filePath){
		boolean bool = false;
		try {
			File file = new File(filePath);
			if(file.exists()){
				file.delete();
			}
			bool = true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	/**
	 * 删除指定时间之前的文件
	 * @param path
	 * @param s
	 * @return
	 */
	public static boolean deleteAll(String path, Integer s){
		try {
			Date date = new Date(System.currentTimeMillis() - 1000 * s); 
			File folder = new File(path); 
			File[] files = folder.listFiles();  
			for (int i=0;i<files.length;i++){  
				try {
					File file = files[i];  
			        if (new Date(file.lastModified()).before(date)){  
			            file.delete();  
			        }  
				} 
				catch (Exception e) {
					
				}
		    }  
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * 压缩后并返回压缩数据
	 * @param path
	 * @return
	 */
	public static byte[] toZip(String path){
		if(FileUtil.osType==0){
			path = path.replace("/", "\\");
        }
        else{
        	path = path.replace("\\", "/");
        }
		
		ZipUtils.toZip(path, true);
		File file = null;
		InputStream is = null;
		byte[] body = null;
		try {
			file = new File(path + ".zip");
		    is = new FileInputStream(file);
		    body = new byte[is.available()];
		    is.read(body);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileUtil.delete(path + ".zip");
		return body;
	}
}
