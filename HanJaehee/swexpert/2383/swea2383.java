import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 점심식사시간 {
    static int N;
    static int[][] map;

    static class Person {
        int y, x, stair, arriveTime, inTime;

        Person(int y, int x) {
            this.y = y;
            this.x = x;
        }

        void calArriveTime(){
            this.arriveTime = Math.abs(stairs[this.stair].y - y) + Math.abs(stairs[this.stair].x - x);
        }
    }

    static class Stair {
        int y, x, time;

        Stair(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }

    static List<Person> people = new ArrayList<>();
    static Queue<Person>[] qs = new Queue[2];
    static Stair[] stairs = new Stair[2];
    static boolean[] visited;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        qs[0] = new LinkedList<>();
        qs[1] = new LinkedList<>();

        for (int test = 1; test <= T; test++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            people.clear();
            for(Queue<Person> q : qs) q.clear();
            result = Integer.MAX_VALUE;

            int stairIdx = 0;
            for (int i = 0; i < N; ++i) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; ++j) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        people.add(new Person(i, j));
                    } else if (map[i][j] >= 2) {
                        stairs[stairIdx++] = new Stair(i, j, map[i][j]);
                    }
                }
            }

            dfs(0);

            System.out.println("#" + test + " " + result);

        }
    }

    private static void dfs(int idx) {
        if(idx == people.size()){
            visited = new boolean[idx];
            result = Math.min(result, simulation());
            return;
        }

        people.get(idx).stair = 0;
        people.get(idx).calArriveTime();
        dfs(idx + 1);

        people.get(idx).stair = 1;
        people.get(idx).calArriveTime();
        dfs(idx + 1);
    }

    private static int simulation(){
        int cnt = 0;
        int time = 1;

        while(true){
            // 계단 내려감
            for(Queue<Person> q :qs){
                int size = q.size();

                for(int i=0; i<size; i++){
                    Person p = q.poll();
                    Stair s = stairs[p.stair];

                    if(p.inTime + s.time <= time){ // 나갈시간 됐음
                        continue;
                    }

                    q.add(p);
                }
            }
            
            if(cnt == people.size() && qs[0].isEmpty() && qs[1].isEmpty()){ // 모든 사람들이 계단에서 나가고, 모든 큐가 비어있으면 끝
                return time;
            }

            // 계단 진입함
            for(int i=0; i<people.size(); i++){
                if(visited[i]) continue;

                Person p = people.get(i); // 사람 세팅
                Queue<Person> q = qs[p.stair];

                if(p.arriveTime + 1 <= time && q.size() < 3){ // 사람이 계단으로 들어갈 수 있는 시간이면서, 계단에도 사람이 정원 내임  
                    p.inTime = time; // 계단으로 사람 진입
                    visited[i] = true;
                    q.offer(p);
                    cnt++;
                }
            }

            time++; // 한시간 경과
        }
    }
}
