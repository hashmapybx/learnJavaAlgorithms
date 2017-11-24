package com.heima.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimerServer implements Runnable{
	
	
	private Selector selector;
	private ServerSocketChannel serChannel;
	private volatile boolean stop;
	
	/**
	 * 初始化多路复用开关
	 * 
	 * */
	
	public MultiplexerTimerServer(int port) {
		
		try {
			selector = Selector.open();
			serChannel = ServerSocketChannel.open();
			serChannel.configureBlocking(false);   //设置为非阻塞式
			//serChannel.bind(new InetSocketAddress("172.16.0.24", 8888));
			serChannel.socket().bind(new InetSocketAddress(port), 1024); 
			serChannel.register(selector, SelectionKey.OP_ACCEPT); //注册ServerSocketChannel到Reactor上
			System.out.println("the timer server is starting port= "+port);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	public void stop() {
		this.stop = true;
	}
	
	
	
	@Override
	public void run() {
		while(!stop) {    //在while循环中遍历selector
			try {
				selector.select(1000);   //休眠时间是1s.无论是否有读写事件发生,每个1S唤醒一次,
				Set<SelectionKey> selectedKeys = selector.selectedKeys();   //返回选择器的键集.
				Iterator<SelectionKey> iterator = selectedKeys.iterator();  //获取迭代器
				SelectionKey key = null;    //每次向选择器注册通道时就会创建一个选择键。通过调用某个键的 cancel 方法、关闭其通道
				while(iterator.hasNext()) {
					
					 key = iterator.next();
					iterator.remove();
					
					try {
						handleInput(key);
					} catch (Exception e) {
						if (key != null) {
							key.cancel();   //请求取消此键的通道到其选择器的注册。
							if (key.channel() != null) {    //判断返回创建键的通道是否为null
								key.channel().close();  
							}
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (selector != null) {
			try {
				selector.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//处理请接入的请求
	private void handleInput(SelectionKey key) throws IOException {
		
		if (key.isValid()) {
			//处理介入的请求
			if (key.isAcceptable()) {
				//accept a channel
				ServerSocketChannel channel = (ServerSocketChannel) key.channel();
				SocketChannel sc = channel.accept();  //接受来自客户端的请求
				sc.configureBlocking(false);
				sc.register(selector, SelectionKey.OP_ACCEPT);
				
			}
			if (key.isReadable()) {   //读取来自客户端的请求信息
				//读数据
				SocketChannel channel = (SocketChannel) key.channel();
				ByteBuffer dst = ByteBuffer.allocate(1024);  //创建一个byteBUffer缓冲区
				int num = channel.read(dst);
				if (num > 0) {   //大于0,说明读取到了字节,
					dst.flip();  //将缓冲区当前的limit设置为position,将position设置为0
					byte[] arr = new byte[dst.remaining()];
					dst.get(arr);
					String line = new String(arr, "utf-8");   //将字节数组转化为字符串
					System.out.println("the timer server reading "+line);
					
					String currentTime = "".equalsIgnoreCase(line) ? new java.util.Date(System.currentTimeMillis())
					.toString(): "dao order";
					
					doWrite(channel,currentTime);
				} else if (num < 0) {
					key.cancel();
					channel.close();
				}
			}
		}
		
	}

	private void doWrite(SocketChannel channel, String response) throws IOException {
		if (response != null && response.trim().length()  > 0) {
			byte[] bytes = response.getBytes();
			
			ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);  
			
			writeBuffer.put(bytes);
			writeBuffer.flip();
			channel.write(writeBuffer);
		}
	}

}
