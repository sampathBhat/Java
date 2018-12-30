package leetcode.simple;

import java.util.Arrays;

public class SearchInsertPosition {
	 public int searchInsert(int[] nums, int target) {
	    
		 //return binarySearch(target,nums);	
		 //return binarySearch(nums, target);		 
		 return binarySearch(nums, target, 0, nums.length-1);
	 }
	 
	 public static int binarySearch(int[] nums, int target, int startIndex, int endIndex) {
		 if(startIndex<endIndex) {
		 int mid = startIndex + (endIndex-startIndex)/2;
		 if(nums[mid]==target)return mid;
		 
		 if(nums[mid]> target) {return binarySearch(nums, target, startIndex, mid-1);}
		 return binarySearch(nums, target, mid+1, endIndex);
	 }
		 if(startIndex==endIndex && nums[startIndex] >= target)return startIndex; else return endIndex+1;
	 }
	 
	 public static int binarySearch(int[] nums, int target) {
		 int res=Arrays.binarySearch(nums, target);
	     if(res<0) {
	    	 return -(res+1);
	     }
	     else return res;
	 }
	 
	 public static int binarySearch(int target, int[] A) {
		 int low = 0, high = A.length-1;
	        while(low<=high){
	            int mid = (low+high)/2;
	            if(A[mid] == target) return mid;
	            else if(A[mid] > target) high = mid-1;
	            else low = mid+1;
	        }
	        return low;
	 }
}
