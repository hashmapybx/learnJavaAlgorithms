package com.ybx.designmodel;

import java.util.Arrays;

public class QuaiPai {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {2, 3, 56, 1, 23, 24, 87, 12, 90};
		//Arrays.sort(array);
		QuaiPai.bubble(array);
		QuaiPai.print(array);
		int[] arr = QuaiPai.quickSorted(array, 0, array.length -1);
		for(int i: arr){
			System.out.print(i + " ");
		}
	}
	
	//冒泡算法排序（轻的上浮，重的下沉）
	public static void bubble(int[] array) {
		int temp = 0;
		for(int i = 0; i< array.length-1; i++){
			for(int j = 0; j< array.length -1; j++){
				if(array[j] > array[j+1]) {
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
	}
	//打印
	public static void print(int[] array){
		for(int ele: array){
			System.out.println(ele);
		}
	}
	//快排算法
	public static int[] quickSorted(int[] array, int left, int right){
		int start = left;
		int end = right;
		int key = array[start];
		
		while(start < end) {
			//从后往前找，直到找到比key小的array[j]，则交换位置array[i]与array[j];
			while(start < end && array[end] >= key){
				end--; //直到遇到小于key的尾部值。尾部向前移动
			}
			if(start < end) {
				int temp = array[end];
				array[end] = array[start];
				array[start] = temp;
				start++; //交换位置后，此刻的起始位置的元素已然小于key, 所以起始位置向后移动。
			}
			//从前往后移动，找到大于key的值，则交换位置
			while(start < end && array[start] <= key) {
				start++;
			}
			if(start < end){
				int temp = array[end];
				array[end] = array[start];
				array[start] = temp;
				end--;
			}
		}
		if(start > left) {
			quickSorted(array, left, right -1);
		}
		if(end < right){
			quickSorted(array, left+1, right);
		}
		return array;
	}
}
