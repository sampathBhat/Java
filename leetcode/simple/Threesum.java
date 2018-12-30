package leetcode.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Threesum {
	public static void main(String[] args) {
		int[] nums= {-1,2, 4,6,-3};  
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Arrays.parallelSort(nums);
		int N = nums.length;
        for (int i = 0; i < N-2 && nums[i] <= 0; ++i)
        {
            if (i > 0 && nums[i] == nums[i-1])
                continue; 
            int tempSum = 0 - nums[i];
            int l = i + 1, r = N - 1;
            while (l < r)
            {
                int sum = nums[l] + nums[r];
                if (sum < tempSum) ++l;
                else if (sum > tempSum) --r;
                else {
                    ArrayList<Integer> tmp = new ArrayList<Integer>();
                    tmp.add(nums[l]); tmp.add(nums[i]); tmp.add(nums[r]);
                    res.add(tmp);
                    ++l; --r;
                    while (l < r && nums[l] == nums[l-1]) ++l; 
                    while (l < r && nums[r] == nums[r+1]) --r; 
                }
            }
        }
	}

}
