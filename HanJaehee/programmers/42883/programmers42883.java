import java.util.*;

class Solution {
    public String solution(String number, int k){
        
        StringBuilder result = new StringBuilder();
        char[] numbers = number.toCharArray();
        int len = numbers.length - k;
        int pos = 0;
        
        while(len > 0){
            
            char max = ' ';
            
            for(int i=pos; i<=numbers.length - len; ++i){
                if(numbers[i] > max){
                    max = numbers[i];
                    pos = i;
                }
            }
            
            result.append(max);
            pos++;
            len--;
        }
        return result.toString();
    }
}