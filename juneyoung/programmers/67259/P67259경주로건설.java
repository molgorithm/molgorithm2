import java.util.*;

public class P67259경주로건설 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int answer = Integer.MAX_VALUE;
    static Queue<Point> q = new LinkedList<>();

    static class Point {
        int r, c, cnt, dir;

        public Point(int r, int c, int cnt, int dir) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.dir = dir;
        }

    }

    public int solution(int[][] board) {
        cal(board);
        return answer;
    }

    public static void cal(int[][] board) {
        int N = board.length;
        int[][] v = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(v[i], Integer.MAX_VALUE);
        }
        v[0][0] = 0;
        q.add(new Point(0, 0, 0, -1));

        Point p = null;
        while (!q.isEmpty()) {
            p = q.poll();
            if (p.r == N - 1 && p.c == N - 1) {
                answer = Math.min(answer, p.cnt);
            }

            for (int k = 0; k < 4; k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N || board[nr][nc] == 1) continue;
                if (p.dir != -1 && p.dir != k) {
                    if (v[nr][nc] < p.cnt + 600) continue;
                    v[nr][nc] = p.cnt + 600;
                    q.add(new Point(nr, nc, p.cnt + 600, k));
                } else {
                    if (v[nr][nc] < p.cnt + 100) continue;
                    v[nr][nc] = p.cnt + 100;
                    q.add(new Point(nr, nc, p.cnt + 100, k));
                }

            }
        }
    }
}
 