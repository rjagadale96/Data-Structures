//Time complexity :-O(n) where n is number of element in the array
//Space complexity:-O(n) where n is number of element in the array


class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs == null || strs.length == 0) return new ArrayList();
        
        Map<String, List<String>> map = new HashMap();
        List<List<String>> list = new ArrayList();
        List<String> temp = new ArrayList();
        for(String str : strs){
            
            char [] word = str.toCharArray();
            
            Arrays.sort(word);
            String key = String.valueOf(word);
            
            if(!map.containsKey(key)){
                map.put(key, new ArrayList());
            }
            
            temp = map.get(key);
            temp.add(str);
            map.put(key, temp);
        }
        
        for(String key : map.keySet()){
            list.add(map.get(key));
        }
        return list;
    }
}