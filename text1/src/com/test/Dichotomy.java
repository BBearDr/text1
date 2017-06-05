package com.test;

import java.util.ArrayList;
import java.util.List;

public class Dichotomy {

	public static void main(String[] args) {
		int[] A = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		binary(A,3);
	}

	private static int binary(int[] a, int key) {
		List<String> numList = new ArrayList<String>();
		int start = 0;
		int end = a.length - 1;
		
		while(start <= end){
			int middle = (end - start)/2 + 1;
			numList.add(middle + " ");
			if(key < a[middle]){
				end = middle - 1;
			}else if(key > a[middle]){
				start = middle + 1;
			}else{
				System.out.println("A[2] 的下标是："+numList);
				return middle;
			}
		}
		return -1;
	}

}
