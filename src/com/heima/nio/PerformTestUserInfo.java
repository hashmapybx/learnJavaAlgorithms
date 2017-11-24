package com.heima.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class PerformTestUserInfo {

	public static void main(String[] args) throws IOException {
		UserInfo info = new UserInfo("hello", "10002");
		int loop = 1000000;
		ByteArrayOutputStream bas = null;
		ObjectOutputStream os = null;
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < loop; i++) {
			bas = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bas);
			os.writeObject(info);
			os.flush();
			os.close();
			byte[] array = bas.toByteArray();
			bas.close();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("the jdk serilaable cost time is " + (endTime - startTime) +"ms");
		
		System.out.println("----------------------------");
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		startTime = System.currentTimeMillis();
		for (int i = 0; i < loop; i++) {
			byte[] arr = info.codeC(buffer);
		}
		endTime = System.currentTimeMillis();
		System.out.println("the byte array cost time is" + (startTime - endTime) + "ms");		
	}

}
