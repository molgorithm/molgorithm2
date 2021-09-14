import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int [][] min = new int [n][m];
        int [][] cnt = new int [n][m];
        for(int [] i : puddles)
            min[i[1]-1][i[0]-1] = -1;
        cnt[0][0] = 1;
        min[0][0] = 1;
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(0,0));
        int [] dr = {0,0,-1,1};
        int [] dc = {1,-1,0,0};
        int length = 1;
        while(!que.isEmpty()){
            int size = que.size();
            length++;
            while(size-- >0) {
                Point now = que.poll();
                for (int i = 0; i < 4; i++) {
                    int row = now.x + dr[i];
                    int col = now.y + dc[i];
                    if (row < 0 || col < 0 || row >= n || col >= m)
                        continue;
                    if (min[row][col] !=0 && min[row][col] < length)
                        continue;
                    if(min[row][col] == 0) {
                        min[row][col] = length;
                        que.add(new Point(row,col));
                    }
                    cnt[row][col] += cnt[now.x][now.y];
                    cnt[row][col] %= 1000000007;
                }
            }
        }
        return cnt[n-1][m-1];
    }
}