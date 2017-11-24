package com.heima.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerSocketChan {
	private static Object obj ;
	public static void main(String[] args) throws IOException {
		ServerSocketChannel acceptSer = ServerSocketChannel.open();  //打开服务端的父管道，用于监听客户端的请求你
		
		//绑定客户端,端口号，
		acceptSer.socket().bind(new InetSocketAddress("172.0.0.1", 8888));
		acceptSer.configureBlocking(false);   //设置为非阻塞式的
		
		Selector selector = Selector.open();   //创建selector多路复选器，开启线程
			new Thread("aaa").start();
		//将acceptSer注册到Reactor
			
		acceptSer.register(selector, SelectionKey.OP_ACCEPT, obj);	
		
		int num = selector.select();
		Set<SelectionKey> keys = selector.selectedKeys();
		Iterator<SelectionKey> iterator = keys.iterator();
		
		while(iterator.hasNext()) {
			
			SelectionKey next = iterator.next();
			//.......
		}
		
	}
}
