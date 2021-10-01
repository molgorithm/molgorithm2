# SW Exprt 1953 탈주범 검거

[문제 링크](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpLlKAQ4DFAUq)

## 1. 설계 로직

1. 거리 측정이 들어가야 해서 BFS로 측정
2. 각 구조물과 방향에 따른 조건이 가장 복잡했다.

## 2. 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈주범검거 {

    static int[][] map;
    static int N, M, R, C, L;
    static int[][] dy =
            {
                    {4, 4, 4, 4}, // 상 하 좌 우
                    {-1, 1, 0, 0}, // 1, 상하좌우
                    {-1, 1, 4, 4}, // 2, 상하
                    {4, 4, 0, 0}, // 3, 좌우
                    {-1, 4, 4, 0}, // 4, 상우
                    {4, 1, 4, 0}, // 5, 하우
                    {4, 1, 0, 4}, // 6, 하좌
                    {-1, 4, 0, 4}, // 7, 상좌
            };

    static int[][] dx =
            {
                    {4, 4, 4, 4}, // 상 하 좌 우
                    {0, 0, -1, 1},
                    {0, 0, 4, 4},
                    {4, 4, -1, 1},
                    {0, 4, 4, 1},
                    {4, 0, 4, 1},
                    {4, 0, -1, 4},
                    {0, 4, -1, 4},
            };
    static Queue<Point> q = new LinkedList<>();

    static class Point {
        int y, x, dis;

        Point(int y, int x, int dis) {
            this.y = y;
            this.x = x;
            this.dis = dis;
        }
    }

    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            q.clear();
            map = new int[N][M];
            visit = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println("#" + test + " " + bfs(R, C));
        }

    }

    private static int bfs(int R, int C) {
        q.add(new Point(R, C, 1));
        visit[R][C] = true;

        int type, ry, rx;
        int cnt = 1;
        while (!q.isEmpty()) {
            Point p = q.poll();
            if (p.dis == L) continue;

            for (int i = 0; i < 4; ++i) {
                type = map[p.y][p.x];
                if (dy[type][i] == 4) continue;

                ry = p.y + dy[type][i];
                rx = p.x + dx[type][i];

                if (ry < 0 || ry >= N || rx < 0 || rx >= M || map[ry][rx] == 0
                        || visit[ry][rx] || !isConnected(type, map[ry][rx], i)) continue;

                q.add(new Point(ry, rx, p.dis + 1));
                visit[ry][rx] = true;
                cnt++;
            }
        }

        return cnt;
    }

    static boolean isConnected(int now, int next, int dir){
        // dir 상 하 좌 우
        if((dir == 0 && next != 3 && next != 4 && next != 7) && (now == 1 || now == 2 || now == 4 || now == 7)){
            return true;
        }else if((dir == 1 && next != 3 && next != 5 && next != 6) && (now == 1 || now == 2 || now == 5 || now == 6)){
            return true;
        }else if((dir == 2 && next != 2 && next != 6 && next != 7) && (now == 1 || now == 3 || now == 6 || now == 7)){
            return true;
        }else if((dir == 3 && next != 2 && next != 4 && next != 5) && (now == 1 || now == 3 || now == 4 || now == 5)){
            return true;
        }
        return false;
    }
}

```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
