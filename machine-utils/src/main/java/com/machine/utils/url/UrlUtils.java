package com.machine.utils.url;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import com.machine.utils.parameters.CommonParameters;

public class UrlUtils {

	public static String getHtmlByUrl(String url) {
		StringBuilder sbResult = new StringBuilder();
		try {
			InputStream inputStream;// 接收字节输入流
			InputStreamReader inputStreamReader;// 将字节输入流转换成字符输入流
			BufferedReader bufferedReader;// 为字符输入流加缓冲
			inputStream = new URL(url).openStream();
			inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			bufferedReader = new BufferedReader(inputStreamReader);

			String strLine = null;
			while ((strLine = bufferedReader.readLine()) != null) {
				sbResult.append(strLine).append(CommonParameters.LINE_SEPRATER);
			}
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sbResult.toString();
	}
}
