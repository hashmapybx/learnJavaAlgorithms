package com.ybx.designmodel;



/*
 *测试jvm垃圾回收机制是否会回收单例对象 
 * 
 * */
public class Client {
	public static void main(String[] args) {
		Singleton.getSingleton();
		while(true){
			new Obj();
		}
	}
}
/**
 * [Full GC (Allocation Failure)  12744K->6670K(19968K), 0.0018970 secs]
[GC (Allocation Failure)  12825K->12744K(19968K), 0.0002356 secs]
[GC (Allocation Failure)  12744K->12744K(19968K), 0.0002143 secs]
[Full GC (Allocation Failure)  12744K->6670K(19968K), 0.0021384 secs]
[GC (Allocation Failure)  12825K->12744K(19968K), 0.0002599 secs]
[GC (Allocation Failure)  12744K->12744K(19968K), 0.0002090 secs]
[Full GC (Allocation Failure)  12744K->6670K(19968K), 0.0020181 secs]
 * 可以看出没有回收单例对象
 * 
 * */


