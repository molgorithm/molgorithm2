import java.util.*;

class Solution {
    public int solution(String s) {
        Map<String, Integer> map = new HashMap();
        map.put("one", 1); map.put("six", 6);
        map.put("two", 2); 
        map.put("four", 4); map.put("five", 5);
        map.put("nine", 9); map.put("zero", 0);
        map.put("seven", 7); map.put("three", 3);
        map.put("eight", 8);
        
        StringBuilder result = new StringBuilder();
        StringBuilder tmp = new StringBuilder();
        char chr;
        int num;
        for(int i=0; i<s.length(); ++i){
            chr = s.charAt(i);
            if(chr >= 'a' && chr <= 'z'){
                tmp = new StringBuilder();
                while(chr >= 'a' && chr <= 'z' && i < s.length()){
                    tmp.append(s.charAt(i));
                    if(tmp.length() >= 3){
                        num = search(map, tmp.toString());
                        if(num != -1){
                            result.append(num);
                            break;
                        }
                    }
                    i++;
                }
            }else{
                result.append(chr);
            }
        }
        
        return Integer.parseInt(result.toString());
        
    }

    public int search(Map<String, Integer> map, String s){
        for(String str : map.keySet()){
            if(str.equals(s)){
                return map.get(s);
            }
        }
        return -1;
    }
}