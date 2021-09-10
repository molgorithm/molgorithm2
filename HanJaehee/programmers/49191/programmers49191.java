import java.util.*;

class Solution {
    
    public int solution(int n, int[][] results) {
        
        int INF = 100000;
        int answer = 0;
        int[][] map = new int[n+1][n+1];
        
        for(int[] tmp : map){
            Arrays.fill(tmp, INF);
        }
        
        for(int[] result : results){
            map[result[0]][result[1]] = 1;
        }
        
        for(int k=1; k<=n; ++k){
            for(int i=1; i<=n; ++i){
                for(int j=1; j<=n; ++j){
                    if(map[i][j] > map[i][k] + map[k][j]){
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
        
        for(int i = 1; i < map.length; i++){
            boolean flag = true;
            for(int j = 1; j < map[i].length; j++) {
                if(i == j) continue;
                if(map[i][j] == INF && map[j][i] == INF) {
                    flag = false; break;
                }
            }
            if(flag) answer++;
        }
        return answer;

    }
}
        