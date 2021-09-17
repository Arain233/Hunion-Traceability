package com.southwind.springboottest.SM3;

import java.io.*;

public class StreamUtils {
	public static byte[] readInputStream2ByteArray(InputStream inStream) throws IOException{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len=0;
		while ((len=inStream.read(buffer))!=-1){
			outStream.write(buffer,0,len);
		}
		return outStream.toByteArray();
	}
	public static String readInputStream2String(InputStream inStream) throws IOException{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len=0;
		while ((len=inStream.read(buffer))!=-1){
			outStream.write(buffer,0,len);
		}
		return outStream.toString();
	}
	public static File readInputStream2File(InputStream inStream ,File file) throws IOException{
		@SuppressWarnings("resource")
		FileOutputStream outStream = new FileOutputStream(file);
		byte[] buffer = new byte[1024];
		int len=0;
		while ((len=inStream.read(buffer))!=-1){
			outStream.write(buffer,0,len);
		}
		return file;
	}
	public static File readInputStream2File(InputStream inStream ,String filepath , String key) throws IOException{
		File file = File.createTempFile(filepath, key);
		return readInputStream2File(inStream ,file);
		
	}
}
