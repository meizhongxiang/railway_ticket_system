package com.railwayTicketManager.test.fileTest;

import java.io.File;
import java.io.IOException;

import javax.management.RuntimeErrorException;

public class FileProperty {
	
	public static void main(String[] args) {
		//创建文件，不写内容
		//createFileByPath();
		
		//对文件或目录的操作
		getFileOrProperty("F:\\meizx.txt");
		
		//删除文件
		//deleteFile("");
		
		//执行本地文件
		//runFile("D:\\Program Files\\360la\\360se6\\Application\\360se.exe");
	}
	
	/**
	 * @author meizx
	 * @description 创建文件，调用 createNewFile() 方法后才可以创建新的文件
	 * @param 文件路径  
	 * @since 2019-09-07
	 * */
	public static void createFileByPath(){
		File f1 = new File("F:\\","meizx.txt");
		File f2 = new File("F:\\meizx.bat");
		try {
			f1.createNewFile();
			f2.createNewFile();
			System.out.println("文件创建完成");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author meizx
	 * @description 获取文件和目录属性
	 * @param 文件路径  
	 * @since 2019-09-07
	 * */
	public static void getFileOrProperty(String path){
		File f = new File(path);
		System.out.println("文件或目录名："+f.getName());
		System.out.println("文件或目录是否存在："+f.exists());
		System.out.println("是否代表有效文件："+f.isFile());
		System.out.println("是否代表有效目录："+f.isDirectory());
		System.out.println("文件或目录地址："+f.getPath());
		System.out.println("文件或目录绝对地址："+f.getAbsolutePath());
		System.out.println("文件或目录上一级路径："+f.getParent());
		System.out.println("获取文件的字节数："+f.length());
		System.out.println("是否为可读文件："+f.canRead());
		System.out.println("是否为可写文件："+f.canWrite());
		System.out.println("将目录中所有文件名保存在字符串数组中返回："+f.list());
		System.out.println("文件或目录具有隐藏性："+f.isHidden());
		System.out.println("文件最后修改时间："+f.lastModified());
		System.out.println("该路径是否为绝对路径："+f.isAbsolute());
	}
	
	/**
	 * @author meizx
	 * @description 删除文件
	 * @param 文件路径  
	 * @since 2019-09-07
	 * */
	public static void deleteFile(String path){
		File f = new File(path);
		f.delete();
	}
	
	
	/**
	 * @author meizx
	 * @description 执行本地文件
	 * @param 文件路径  
	 * @since 2019-09-07
	 * */
	public static void runFile(String path){
		//创建 Runtimedx 对象
		Runtime ex = Runtime.getRuntime();
		//创建文件对象
		File f = new File(path);
		//执行文件
		try {
			ex.exec(f.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}
