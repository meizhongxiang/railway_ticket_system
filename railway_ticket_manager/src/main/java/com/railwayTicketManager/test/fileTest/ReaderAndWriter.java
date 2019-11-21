package com.railwayTicketManager.test.fileTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

public class ReaderAndWriter {
	public static void main(String[] args) throws Exception {
		
		//字符流 FileReader 读文件
		//fileReaderTest("F:\\meizx.txt");
		
		//字符流 FileWriter 写文件
		//fileWriterTest("F:\\meizx123.txt");
		
		//字符缓冲流 BufferedReader 读文件
		//bufferedReaderTest("F:\\meizx.txt");
		
		//字符缓冲流 BufferedWriter 写文件
		//bufferedWriterTest("F:\\meizx121.txt");
	}
	
	
	
	/**
	 * @author meizx
	 * @description 使用直接文件操纵流  字符流 FileReader 读文件   
	 * @param 文件路径  
	 * @since 2019-09-07
	 * */
	public static void fileReaderTest(String path) throws Exception{
		File file = new File(path);
		if(file.exists()){
			FileReader fr = new FileReader(file);
			char[] c = new char[100];
			int i = 0;
			while((i = fr.read(c)) != -1){
				System.out.println(new String(c,0,i));
			}
			fr.close();
		}
	}
	
	
	/**
	 * @author meizx
	 * @description 使用直接文件操纵流  字符流 FileWriter 写文件   
	 * @param 文件路径  
	 * @since 2019-09-07
	 * */
	public static void fileWriterTest(String path) throws Exception{
		File file = new File(path);
		if(!file.exists())
			file.createNewFile();
		Writer fw = new FileWriter(file);
		String msg ="字符流写文件，###@@@￥￥￥%%%…………&&12334djkfhds";
		fw.write(msg);
		fw.close();
	}
	
	
	
	/**
	 * @author meizx
	 * @description 使用缓冲字符流 BufferedReader 读文件   
	 * @param 文件路径  
	 * @since 2019-09-07
	 * */
	public static void bufferedReaderTest(String path) throws Exception{
		File file = new File(path);
		FileReader fr = new FileReader(file);
		BufferedReader reader = new BufferedReader(fr);
		String s = null;
		while((s=reader.readLine()) != null){
			System.out.println(s);
		}
		fr.close();
		reader.close();
	}
	
	/**
	 * @author meizx
	 * @description 使用缓冲字符流 BufferedWriter 写文件   
	 * @param 文件路径  
	 * @since 2019-09-07
	 * */
	public static void bufferedWriterTest(String path) throws Exception{
		File file = new File(path);
		if(!file.exists())
			file.createNewFile();
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("一二三四五");
		bw.newLine();
		bw.write("上山打老虎");
		bw.newLine();
		bw.write("老虎不吃我");
		bw.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
