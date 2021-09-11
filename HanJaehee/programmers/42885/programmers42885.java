import java.util.*;

class Solution {
    
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        
        int end = people.length -1;
        int start = 0;
        
        while(end >= start){
            int weight = people[end--];
            if(weight + people[start] <= limit)
                start++;
            
            answer++;
        }
        
        return answer;
    }
   
}