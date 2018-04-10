package com.machine.utils.io.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.machine.utils.parameters.CommonParameters;

public class FileUtils {

	public static void string2FileBytes(String filePath, String content) throws IOException {
		FileOutputStream fos = new FileOutputStream(filePath);
		fos.write(content.getBytes());
		fos.close();
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @throws IOException
	 */
	public static String readFileByLines(String fileName) throws IOException {
		StringBuilder sbResult = new StringBuilder();
		File file = new File(fileName);
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader(file));
		String strLine = null;
		while ((strLine = reader.readLine()) != null) {
			sbResult.append(strLine).append(CommonParameters.LINE_SEPRATER);
		}
		reader.close();
		return sbResult.toString();
	}
	
	/**
	 * 递归删除文件夹
	 */
	public static void delDir(File f) {
	    // 判断是否是一个目录, 不是的话跳过, 直接删除; 如果是一个目录, 先将其内容清空.
	    if(f.isDirectory()) {
	        // 获取子文件/目录
	        File[] subFiles = f.listFiles();
	        // 遍历该目录
	        for (File subFile : subFiles) {
	            // 递归调用删除该文件: 如果这是一个空目录或文件, 一次递归就可删除. 如果这是一个非空目录, 多次
	            // 递归清空其内容后再删除
	            delDir(subFile);
	        }
	    }
	    // 删除空目录或文件
	    f.delete();
	}
	
	/**
	 * 递归创建文件夹
	 */
	public static void mkDir(File file) {  
        if (file.getParentFile().exists()) {  
            file.mkdir();  
        } else {  
            mkDir(file.getParentFile());  
            file.mkdir();    
        }  
    }  

}
