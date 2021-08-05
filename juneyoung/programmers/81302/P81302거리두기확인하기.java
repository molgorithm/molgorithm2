import java.util.*;

public class P81302거리두기확인하기 {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] v;
    static Queue<Point> q = new LinkedList<>();
    static char[][] map = new char[5][];

    static class Point {
        int r, c, depth;

        public Point(int r, int c, int depth) {
            this.r = r;
            this.c = c;
            this.depth = depth;
        }
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        Arrays.fill(answer, 1);
        for (int i = 0; i < places.length; i++) {
            for (int j = 0; j < places[i].length; j++) {
                map[j] = places[i][j].toCharArray();
            }
            if (cal()) answer[i] = 0;
        }
        return answer;
    }

    public static boolean check(int row, int col) {
        q.clear();
        v = new boolean[5][5];
        v[row][col] = true;
        q.add(new Point(row, col, 0));
        Point p = null;
        while (!q.isEmpty()) {
            p = q.poll();

            if (p.depth == 3) continue;
            if (map[p.r][p.c] == 'P' && p.depth != 0) return true;

            for (int k = 0; k < 4; k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];

                if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5 || v[nr][nc] || map[nr][nc] == 'X') continue;
                v[nr][nc] = true;
                q.add(new Point(nr, nc, p.depth + 1));
            }
        }

        return false;
    }

    public static boolean cal() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (map[row][col] == 'P' && check(row, col)) return true;
            }
        }
        return false;
    }

}
 