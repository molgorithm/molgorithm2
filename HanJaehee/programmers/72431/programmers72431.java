import java.util.*;

class Solution {
    public class Point implements Comparable<Point>{
        int v, cost;
        Point(int v, int cost){
            this.v=v;
            this.cost=cost;
        }
        
        @Override
        public int compareTo(Point p){
            return Integer.compare(this.cost, p.cost);
        }
        
    }
    public int solution(int n, int s, int a, int b, int[][] fares) {
        List<Point>[] arr = new ArrayList[n+1];
        for(int i=1; i<=n; ++i) arr[i] = new ArrayList<>();
        
        for(int[] fare: fares){
            arr[fare[0]].add(new Point(fare[1], fare[2]));
            arr[fare[1]].add(new Point(fare[0], fare[2]));
        }
        
        int[][] dis = new int[n+1][n+1];
        
        dijkstra(n, s, arr,dis);
        dijkstra(n, a, arr,dis);
        dijkstra(n, b, arr,dis);
        // ( S - F ) + ( F - A ) + ( F - B )
        
        int min = dis[s][a] + dis[s][b];
        for(int i=1; i<=n; ++i){
            if(dis[s][i] == Integer.MAX_VALUE || dis[a][i] == Integer.MAX_VALUE ||
              dis[b][i] == Integer.MAX_VALUE) continue;
            
            min = Math.min(min, dis[s][i] + dis[a][i] + dis[b][i]);
        }

        return min;
    }
    
    public void dijkstra(int n, int v, List<Point>[] arr, int[][] dis){
        PriorityQueue<Point> pq = new PriorityQueue<Point>();
        boolean[] visit = new boolean[n+1];
        
        Point[] P = new Point[n+1];
        
        for(int i=1; i<=n; ++i){
            if(i == v) P[i] = new Point(i, 0);
            else P[i] = new Point(i, Integer.MAX_VALUE);
            pq.add(P[i]);
        }
        
        Point p;
        while(!pq.isEmpty()){
            p = pq.poll();
            for(Point next : arr[p.v]){
                if(visit[next.v] || P[p.v].cost == Integer.MAX_VALUE) continue;
                if(P[next.v].cost > P[p.v].cost + next.cost){
                    P[next.v].cost = P[p.v].cost + next.cost;
                    pq.remove(P[next.v]);
                    pq.add(P[next.v]);
                }
            }
            visit[p.v] = true;
        }
        
        for(int i=1; i<=n; ++i) dis[v][i] = P[i].cost;
    }
}