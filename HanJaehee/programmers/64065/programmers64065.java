import java.util.*;
class Solution {
    
    public int[] solution(String s) {
        s = s.substring(2, s.length()-2);
        String[] sList = s.split("\\},\\{");
        Arrays.sort(sList, (a,b)-> a.length() - b.length());
        Set<Integer> set = new HashSet<>();
        
        int[] result = new int[sList.length];
        int idx = 0;
        for(String tmpString : sList){
            String[] tmpList = tmpString.split(",");
            for(String tmp : tmpList){
                int num = Integer.parseInt(tmp);
                if(!set.contains(num)){
                    result[idx++] = num;
                    set.add(num);
                }
            }
        }
        
        return result;
    }
}