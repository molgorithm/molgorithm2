import java.util.*;

class Solution {
    
    boolean[] v;
    
    public int solution(int n, int[][] computers) {
        v = new boolean[n];
        int answer = 0;
        for(int i=0; i<n; ++i){
            for(int j=0; j<n; ++j){
                if(computers[i][j] == 1 && !v[i]){
                    dfs(computers, i, n);
                    answer++;
                }
            }
        }
        return answer;
    }
    
    public void dfs(int[][] computers, int idx, int n){
        v[idx] = true;
        for(int i=0; i<n; ++i){
            if(computers[idx][i] == 1 && !v[i]) dfs(computers, i, n);
        }
    }
}