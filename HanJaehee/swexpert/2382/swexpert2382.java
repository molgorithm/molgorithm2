import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 미생물군집 {

    static int[] dy = {0, -1, 1, 0, 0};
    static int[] dx = {0, 0, 0, -1, 1};
    static int N, M, K;
    static List<Point> list = new ArrayList<>();

    static class Point implements Comparable<Point> {
        int pos, y, x, num, dir;

        Point(int pos, int y, int x, int num, int dir) {
            this.pos = pos;
            this.y = y;
            this.x = x;
            this.num = num;
            this.dir = dir;
        }

        @Override
        public int compareTo(Point o) {
            if(this.pos == o.pos){
                return o.num - this.num;
            }
            return this.pos - o.pos;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; ++test) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            list.clear();

            int pos, y, x, num, dir;
            for (int i = 0; i < K; ++i) {
                st = new StringTokenizer(br.readLine());
                y = Integer.parseInt(st.nextToken());
                x = Integer.parseInt(st.nextToken());
                num = Integer.parseInt(st.nextToken());
                dir = Integer.parseInt(st.nextToken());
                pos = y * N + x;
                list.add(new Point(pos, y, x, num, dir));
            }

            for (int m = 0; m < M; ++m) {
                for (int i = 0; i < list.size(); ++i) {
                    Point p = list.get(i);
                    p.y += dy[p.dir];
                    p.x += dx[p.dir];
                    p.pos = p.y * N + p.x;

                    if (p.y == 0 || p.x == 0 || p.y == N-1 || p.x == N-1) { // 경계썬
                        p.num /= 2;
                        p.dir = turn(p.dir);
                        if (p.num == 0) {
                            list.remove(i);
                            i--;
                        }
                    }
                }
                // 이동 끝
                Collections.sort(list);

                for (int i = 0; i < list.size() - 1; i++) {
                    Point one = list.get(i);
                    Point two = list.get(i + 1);

                    if (one.pos == two.pos) {
                        one.num += two.num;
                        list.remove(i + 1);
                        i--;
                    }
                }
                //합치기 끝
            }

            int total = 0;
            for (Point point : list) total += point.num;
            System.out.println("#" + test + " " + total);
        }

    }

    private static int turn(int dir) {
        if (dir == 1) return 2;
        else if (dir == 2) return 1;
        else if (dir == 3) return 4;
        else return 3;
    }
}
