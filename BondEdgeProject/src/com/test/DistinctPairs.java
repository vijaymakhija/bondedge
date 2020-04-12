package com.test;

import java.util.Arrays;
//Approach involves sorting array and then iterating array only till smallest number after which pairs would start repeating
//For ex: In [1,2,3,4,5,6], we would iterate only till 3rd index.
//[-3,-1,1,0,3,6,7,7,8,10] will iterate till 6th index.

public class DistinctPairs {
	private static void printPairs(int[] inpArr, int sum) {
		//Buffer to store all pairs
		StringBuffer strBuf = new StringBuffer("Pairs: ");
		//Sort array
		Arrays.sort(inpArr);
		int len = inpArr.length;
		int lowestSumIndx = len-1;
		for(int i=0;i<len;i++) {
			//If lowest index is achieved, break the loop
			if(i >= lowestSumIndx) {
				break;
			}
			//Get Other number needed to form pair and check if it exists in the array
			int secNum = sum - inpArr[i];
			int secNumIndx = Arrays.binarySearch(inpArr, secNum);
			
			//If number exists, print te pair and set lowest sum index to the index of larger number in pair
			if(secNumIndx > 0) {
				strBuf.append(("("+inpArr[i]+","+secNum+") "));
				lowestSumIndx = secNumIndx;
			}
		}
		//Print buffer
		System.out.println(strBuf.toString());
	}
	
	public static void main(String[] args) {
		//Case 1: Sorted array
		int[] inpArr = new int[] {1,2,3,4,5,6};
		printPairs(inpArr, 7);
		//Case 2: Negatives in array
		inpArr = new int[] {-3,-1,1,0,3,6,7,7,8,10};
		printPairs(inpArr, 7);
		//Case 3: 0 length array
		inpArr = new int[] {};
		printPairs(inpArr, 7);
		//Case 4: Out of order and repeats in Array
		inpArr = new int[] {8,7,0,3,1,8,5};
		printPairs(inpArr, 7);
		//Case 5: No pairs in array
		inpArr = new int[] {-1};
		printPairs(inpArr, 7);
	}

}
