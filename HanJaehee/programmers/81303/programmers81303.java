import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmds) {
        Stack<Integer> trash = new Stack<>();
        for(String cmdStr : cmds){
            char cmd = cmdStr.charAt(0);
            
            if(cmd == 'D'){
                k += Integer.valueOf(cmdStr.substring(2));
            }else if(cmd == 'U'){
                k -= Integer.valueOf(cmdStr.substring(2));
            }else if(cmd == 'C'){
                trash.push(k);
                if(k == --n) k--;
            }else if(cmd == 'Z'){
                int removed = trash.pop();
                if(removed <= k) k++;
                n++;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; ++i) sb.append('O');
        while(!trash.isEmpty()) sb.insert(trash.pop().intValue(), 'X');
        
        return sb.toString();
    }
}