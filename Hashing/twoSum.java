// Link to the problem:- https://leetcode.com/problems/two-sum/
// time complexity :-O(n)
// Space complexity :- O(n)

class Solution {
    public int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return new int[0];
        }
        int [] res = new int [2];
        
        Map <Integer, Integer> map = new HashMap();
        
        for(int i =0 ;i< nums.length; i++){
            int val = target - nums[i];
            
            if(map.containsKey(val)){
                res[0]=i;
                res[1]=map.get(val);
                break;
            }else{
                map.putIfAbsent(nums[i],i);
            }
        }
        return res;
    }
}