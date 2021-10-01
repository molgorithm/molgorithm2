import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 등산로조성 {
    static int N, K, MAX_BONG, MAX_ROUTE;
    static int[][] map;

    public static class Bong{
        int y, x;
        Bong(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    static List<Bong> maxBongs = new ArrayList<>();
    static int[] dy = {1,-1,0,0};
    static int[] dx = {0,0,1,-1};
    static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int test=1; test<=T; test++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            visit = new boolean[N][N];

            MAX_BONG = -1;
            MAX_ROUTE = -1;
            maxBongs.clear();

            for(int i=0; i<N; ++i){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; ++j){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    MAX_BONG = Math.max(MAX_BONG, map[i][j]);
                }
            }

            collectMAXBong();

            for(Bong max : maxBongs) {
                visit[max.y][max.x] = true;
                dfs(max.y, max.x, 1, false);
                visitClear();
            }

            System.out.println("#" + test + " " + MAX_ROUTE);

        }
    }

    private static void visitClear(){
        for(int i=0; i<N; ++i){
            for(int j=0; j<N; ++j){
                visit[i][j] = false;
            }
        }
    }

    private static void dfs(int y, int x, int cnt, boolean isGongSa){
        MAX_ROUTE = Math.max(cnt, MAX_ROUTE);
        int ry, rx;
        for(int i=0; i<4; ++i) {
            ry = y + dy[i];
            rx = x + dx[i];
            if(ry < 0 || ry >= N || rx < 0 || rx >= N || visit[ry][rx]) continue;
            if(map[ry][rx] >= map[y][x]) { // 다음 봉의 높이가 현재보다 크거나 같을 때
                if(!isGongSa && map[ry][rx] - map[y][x] < K){ // 공사를 한적이 없고, 공사 가능한 높이라면 간다.
                    visit[ry][rx] = true;
                    int origin = map[ry][rx];
                    map[ry][rx] = map[y][x] - 1; // 다음 높이를 현재 높이의 한단계 낮은것으로 조정
                    dfs(ry, rx, cnt + 1, true);
                    map[ry][rx] = origin; // 탐색 후 원복
                    visit[ry][rx] = false;
                }
            }else {
                visit[ry][rx] = true;
                dfs(ry, rx, cnt + 1, isGongSa);
                visit[ry][rx] = false;
            }
        }
    }

    private static void collectMAXBong(){
        for(int i=0; i<N; ++i){
            for(int j=0; j<N; ++j){
                if(map[i][j] == MAX_BONG){
                    maxBongs.add(new Bong(i, j));
                }
            }
        }
    }
}
