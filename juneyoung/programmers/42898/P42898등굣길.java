import java.util.*;
public class P42898등굣길 {
    static int map[][] ,dp[][];
    static final int div = 1000000007;
    static int[] dr = { 1, 0 };
    static int[] dc = { 0, 1 };
    public int solution(int m, int n, int[][] puddles) {
        map = new int[n][m];
        dp = new int[n][m];

        for(int i = 0; i < puddles.length; i++)
            map[puddles[i][1]-1][puddles[i][0]-1] = 1;
        

        for(int i= 0; i < dp.length; i++)
            Arrays.fill(dp[i],-1);
        

        int answer = cal(0,0,n,m);

        return answer;
    }

    private static int cal(int r, int c,int n,int m) {
        if(r == n-1 && c == m-1) return 1;

        if(dp[r][c] != -1) return dp[r][c];
        dp[r][c] = 0;

        for(int k = 0; k < 2; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];

            if(nr < 0 || nc < 0 || nr >= n || nc >= m || map[nr][nc] == 1) continue;
            dp[r][c] += (cal(nr,nc,n,m)%div);
        }

        return dp[r][c]%div;

    }
}
 