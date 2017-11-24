package com.heima.nio;

public class TimerServer {
	
	public static void main(String[] args) {
		int port = 8888;
		
		if (args != null && args.length > 0) {
			try {
				Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		MultiplexerTimerServer timer = new MultiplexerTimerServer(port);
		
		new Thread(timer,"love").start();
	}
	
}
