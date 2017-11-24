package com.heima.nio;

import java.io.Serializable;
import java.nio.ByteBuffer;

public class UserInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userName;
	private String userid;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public UserInfo(String userName, String userid) {
		super();
		this.userName = userName;
		this.userid = userid;
	}
	
	//这种是不经过序列化的对象
	public byte[] codeC() {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		byte[] bytes = this.userName.getBytes();
		buffer.putInt(bytes.length);
		buffer.put(bytes);
		buffer.putInt(Integer.parseInt(userid));
		buffer.flip();
		bytes = null;
		byte[] result = new byte[buffer.remaining()];
		buffer.get(result);
		return result;
		
	}
	
	public byte[] codeC(ByteBuffer buffer) {
		
		buffer.clear();
		byte[] values = this.userName.getBytes();
		buffer.put(values);
		buffer.putInt(Integer.parseInt(userid));
		buffer.flip();
		values = null;
		byte[] result = new byte[buffer.remaining()];
		buffer.get(result);
		return result;
		
	}
}
