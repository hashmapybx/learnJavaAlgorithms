package com.ybx.designmodel;


/**
 * 设计模式之单例模式
 * 1、私有构造
 * 2、指向自己实例的私有静态引用
 * 3、返回自己实例引用的公有静态方法
 * 懒汉式与饿汉式
 * 
 * */
//饿汗式  一上来就创建对象
 class Singleton {
	 private byte[] a= new byte[6*1024* 1024];
	private static Singleton singleton = new Singleton();
	private Singleton(){}
	public static Singleton getSingleton(){
		return singleton;
	}
}

class Obj{
	private byte[] a = new byte[3 * 2014 * 1024];
	
}


	



//懒汉式，先不创建对象的，在需要的时候再去创建对象

/*public class Singleton{
	private static Singleton singleton;
	private Singleton(){}
	
	public static Singleton getSingleton(){
		return new Singleton();
	}
}*/