package com.railwayTicketManager.test.fileTest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
//600118190906032171
public class InputAndOutputStream {
	public static void main(String[] args) throws Exception {
		// FileOutputStream 写文件
		//fileOutputStreamTest("F:\\qian.txt");
		
		// FileInputStream 读文件
		//fileInputStreamTest("F:\\meizx.txt");
		//fileInputStreamTest1("F:\\meizx.txt");
		
		// FileInputStream	FileOutputStream  拷贝文件
		//copyFileTest("F:\\qian.txt","F:\\qian1.txt");
		
		// BufferedInputStream  缓冲流 读取文件	
		//bufferedInputStreamTest("F:\\meizx.txt");
		
		// BufferedOutputStream  缓冲流 写文件	
		//bufferedOutputStreamTest("F:\\meizqqx.txt");
		
		// DataInputStream 对象流 读文件
		//dataInputStreamTest("F:\\meizxData.txt");
		
		// DataOutputStream 对象流 写文件
		//dataOutputStreamTest("F:\\meizxData.txt");
		
		// ObjectInputStream 序列化流 读文件
		//objectInputStreamTest("F:\\meizx.txt");
		
		// ObjectOutputStream 序列化流 写文件
		//objectOutputStreamTest("F:\\meizx.txt");
		
		// ObjectInputStream 序列化流 文件读出集合
		//objectInputStreamListTest("F:\\meizx.txt");
		
		// ObjectOutputStream 序列化流 将集合写入文件
		//objectOutputStreamListTest("F:\\meizx.txt");
		
		// InputStreamReader 字节流转字符流  读文件
		inputStreamToReaderTest("F:\\meizx123.txt");
	}

	
	/**
	 * @author meizx
	 * @description 使用直接文件操纵流 FileInputStream 读文件   文件遇到AcsII乱码
	 * @param 文件路径
	 * @since 2019-09-07
	 * */
	public static void fileInputStreamTest(String path) throws Exception{
		//创建文件对象
		File file = new File(path);
		//检查文件是否存在，不存在就创建
		if(file.exists()){
			//file.createNewFile();
			
			//创建流对象
			FileInputStream fis = new FileInputStream(file);
			//获取文件大小
			int len = (int) file.length();
			//设置一次性读一个数组，也可以一个字节的读
			byte[] b = new byte[len];
			//读文件
			int i = fis.read(b);
			//关闭资源
			fis.close();
			//输出文件内容
			System.out.println(new String(b));
		}
	}
	
	
	/**
	 * @author meizx
	 * @description 使用直接文件操纵流 FileInputStream 分步读取文件读文件   文件遇到AcsII乱码
	 * @param 文件路径
	 * @since 2019-09-07
	 * */
	public static void fileInputStreamTest1(String path) throws Exception{
		//创建文件对象
		File file = new File(path);
		//检查文件是否存在，不存在就创建
		if(file.exists()){
			//file.createNewFile();
			
			//创建流对象
			FileInputStream fis = new FileInputStream(file);
			//获取文件大小
			//int len = (int) file.length();
			//设置一次性读一个数组，也可以一个字节的读
			byte[] b = new byte[1024*1024];
			//读文件
			int i = 0;
			while((i = fis.read(b,0,1024)) != -1){
				//输出文件内容
				System.out.println(new String(b));
			}
			//关闭资源
			fis.close();
		}
	}
	
	
	/**
	 * @author meizx
	 * @description 使用直接文件操纵流 FileOutputStream 写文件   
	 * @param 文件路径
	 * @since 2019-09-07
	 * */
	public static void fileOutputStreamTest(String path) throws Exception{
		//创建文件对象
		File file = new File(path);
		if(!file.exists())
			file.createNewFile();
		//创建流
		FileOutputStream fos = new FileOutputStream(file);
		//添加写入内容
		String msg ="文件已经被修改了";
		//写文件,需要字符串转字节
		fos.write(msg.getBytes());
		//释放资源
		fos.close();
	}
	
	
	/**
	 * @author meizx
	 * @description 使用直接文件操纵流 FileInputStream  FileOutputStream 拷贝文件   
	 * @param 文件路径  
	 * @since 2019-09-07
	 * */
	public static void copyFileTest(String path,String copyPath) throws Exception{
		File f = new File(path);
		File ff = new File(copyPath);
		if(f.exists()){
			if(!ff.exists())
				ff.createNewFile();
			InputStream fis = new FileInputStream(f);
			OutputStream os = new FileOutputStream(ff);
			byte[] b = new byte[1024*1024*10];
			int len = -1;
			while((len = fis.read(b)) != -1){
				os.write(b, 0, len);
			}
			fis.close();
			os.close();
		}
	}
	
	
	/**
	 * @author meizx
	 * @description 使用缓冲流 BufferedInputStream   缓冲流读文件 
	 * 				BufferedInputStream是增强流，加上之后能提高输入效率  
	 * @param 文件路径  
	 * @since 2019-09-07
	 * */
	public static void bufferedInputStreamTest(String path) throws Exception{
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		InputStream bis = new BufferedInputStream(fis);
		byte[] b = new byte[(int) file.length()];
		int i = -1;
		while((i = bis.read(b)) != -1){
			System.out.println(new String(b));
		}
		fis.close();
		bis.close();
	}

	
	
	/**
	 * @author meizx
	 * @description 使用缓冲流 BufferedOutputStream   缓冲流写文件 
	 * @param 文件路径  
	 * @since 2019-09-07
	 * */
	public static void bufferedOutputStreamTest(String path) throws Exception{
		File file = new File(path);
		if(!file.exists())
			file.createNewFile();
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		String msg = "1234566789哈哈哈哈哈哈哈哈哈###@@@";
		bos.write(msg.getBytes());
		//使用缓冲流写文件，若不关闭资源，文件内容就写不进去
		bos.close();
	}
	
	
	/**
	 * @author meizx
	 * @description 使用对象流 DataInputStream   读文件 
	 * @param 文件路径  
	 * @since 2019-09-07
	 * */
	public static void dataInputStreamTest(String path) throws Exception{
		File file = new File(path);
		if(file.exists()){
			DataTest dt = new DataTest();
			DataInputStream dis = new DataInputStream(new FileInputStream(file));
			dt.setId(dis.readInt());
			dt.setName(dis.readUTF());
			dis.close();
			dis.close();
			System.out.println(dt.toString());
		}
		
	}

	/**
	 * @author meizx
	 * @description 使用对象流 dataOutputStream   写文件 
	 * @param 文件路径  
	 * @since 2019-09-07
	 * */
	public static void dataOutputStreamTest(String path) throws Exception{
		DataTest dt = new DataTest(10001,"梅忠翔");
		File file = new File(path);
		if(!file.exists())
			file.createNewFile();
		OutputStream out = new FileOutputStream(file);
		DataOutputStream dos = new DataOutputStream(out);
		dos.writeInt(dt.getId());
		dos.writeUTF(dt.gSetName());
		out.close();
		dos.close();
	}

	
	/**
	 * @author meizx
	 * @description 使用序列化流 ObjectInputStream   读文件（反序列化）
	 * @param 文件路径  
	 * @since 2019-09-07
	 * */
	public static void objectInputStreamTest(String path) throws Exception{
		File file = new File(path);
		if(file.exists()){
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			DataTest dt = (DataTest)ois.readObject();
			fis.close();
			ois.close();
			System.out.println(dt.toString());
		}
	}
	
	/**
	 * @author meizx
	 * @description 使用序列化流 ObjectOutputStream   写文件 
	 * @param 文件路径  
	 * @since 2019-09-07
	 * */
	public static void objectOutputStreamTest(String path) throws Exception{
		File file = new File(path);
		if(!file.exists())
			file.createNewFile();
		DataTest dt = new DataTest(10001,"梅忠翔");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oot =new ObjectOutputStream(fos);
		oot.writeObject(dt);
		fos.close();
		oot.close();
	}
	
	
	/**
	 * @author meizx
	 * @description 使用序列化流 ObjectInputStream   文件读出集合（反序列化）
	 * @param 文件路径  
	 * @since 2019-09-07
	 * */
	public static void objectInputStreamListTest(String path) throws Exception{
		File file = new File(path);
		if(file.exists()){
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			List<DataTest> list= (List)ois.readObject();
			fis.close();
			ois.close();
			for(DataTest dt : list){
				System.out.println(dt.toString());
			}
		}
	}
	
	/**
	 * @author meizx
	 * @description 使用序列化流 ObjectOutputStream   将集合写入文件 
	 * @param 文件路径  
	 * @since 2019-09-07
	 * */
	public static void objectOutputStreamListTest(String path) throws Exception{
		File file = new File(path);
		if(!file.exists())
			file.createNewFile();
		List<DataTest> list = new ArrayList<>();
		list.add(new DataTest(10002,"小兔克里比"));
		list.add(new DataTest(10003,"小马佩德罗"));
		list.add(new DataTest(10005,"小狗丹尼"));
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oot =new ObjectOutputStream(fos);
		oot.writeObject(list);
		fos.close();
		oot.close();
	}
	
	/**
	 * @author meizx
	 * @description 字节流转字符流，使用转换流 ObjectOutputStream   读文件 
	 * @param 文件路径  
	 * @since 2019-09-07
	 * */
	public static void inputStreamToReaderTest(String path) throws Exception{
		File file = new File(path);
		if(file.exists()){
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			String s ="";
			while((s = br.readLine())!=null){
				System.out.println(s);
			}
			fis.close();
			isr.close();
			br.close();
		}
			
		
	}
	
}
