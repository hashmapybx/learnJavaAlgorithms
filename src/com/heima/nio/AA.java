package com.heima.nio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AA {
	public static boolean isHUNWEN(String text){
		int length = text.length();
		for(int i =0; i < length /2; i++){
			if (text.toCharArray()[i] != text.toCharArray()[length -i -1]) {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args){
		String text = "abgdd";
		System.out.println(isHUNWEN(text));
		String str = "cdabeefghhgfeiieje478k874lmn";
		String s1 = "678b876";
		String regex = "(\\w+)|(\\w+)";
		Pattern p = Pattern.compile(regex);
		Matcher  ma = p.matcher(str);
//		ma.matches();
		System.out.println(ma.find());
		
	}
	
	
	
}

