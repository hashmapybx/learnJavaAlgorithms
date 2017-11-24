package com.heima.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TestUserInfo {

	public static void main(String[] args) throws IOException {
		//创建序列化对象
		UserInfo info = new UserInfo("zhangsan", "10002");
		//字节流
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		//对象流
		ObjectOutputStream os = new ObjectOutputStream(outputStream);
		os.writeObject(info);
		os.flush();
		os.close();
		byte[] bs = outputStream.toByteArray();
		System.out.println("the jdk is seriaiable length is" + bs.length);
		
		outputStream.close();
		System.out.println("the byte is serialable length  " + info.codeC().length);
	}

}
 //java序列化后的码流是没有序列化的2.59陪。所以java序列化在网络传输浪费带宽，导致系统的吞吐量下降。一般在RPC框架不用java的序列化