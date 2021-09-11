import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        boolean[] lostMans = new boolean[n+1];
        boolean[] reserveMans = new boolean[n+1];

        Arrays.sort(reserve);
        
        
        for(int num : lost) lostMans[num] = true;
        for(int num : reserve){
            if(lostMans[num]){
                lostMans[num] = false;
                answer++;
            }
            else reserveMans[num] = true;
        }
        
        
        int[] dir = {-1, 1};
        for(int num : reserve){
            if(!reserveMans[num]) continue;
            for(int i=0; i<dir.length; ++i){
                int idx = dir[i] + num;
                if(idx >=1 && idx <= n){
                    if(lostMans[idx]){
                        answer++;
                        lostMans[idx] = false;
                        break;
                    }
                }
            }
        }
        return answer;
    }
}