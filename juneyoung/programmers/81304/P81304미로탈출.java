import java.util.*;

class P81304미로탈출 {
    static boolean[][] v;
    static int trapCount, arr[][], ans;
    static Map<Integer,Integer> map = new HashMap<>();
    static PriorityQueue<Point> q = new PriorityQueue<>();
    
    static class Point implements Comparable<Point>{
        int start, flag, cnt;
        
        public Point(int start, int flag, int cnt) {
            this.start = start;
            this.flag =  flag;
            this.cnt = cnt;
        }
        
        @Override
        public int compareTo(Point o){
            return this.cnt - o.cnt;
        }
        
    }
    
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        trapCount = traps.length;
        v = new boolean[1 << trapCount][n + 1];
        arr = new int[n + 1][n + 1];
        
        for(int i = 0; i < traps.length; i++) {
            map.put(traps[i],i);
        }
        
        for(int i = 0; i < arr.length; i++){
            Arrays.fill(arr[i],3001);
        }
        
        for(int i = 0; i < roads.length; i++) {
            int P = roads[i][0];
            int Q = roads[i][1];
            int S = roads[i][2];           
            if(arr[P][Q] > S ) arr[P][Q] = S;
        }
        
        cal(start, end);
        return ans;
    }
    
    public static void cal(int start, int end) {
		q.add(new Point(start, (1 << trapCount) - 1, 0));

		Point p = null;
		while (!q.isEmpty()) {
			p = q.poll();

			if (p.start == end) {
				ans = p.cnt;
				return;
			}
            
            if(v[p.flag][p.start]) continue;
            v[p.flag][p.start] = true;

			for (int i = 1; i < arr.length; i++) {
				if(arr[p.start][i] == 3001 && arr[i][p.start] == 3001) continue;
				if (map.containsKey(p.start)) {
					if ((p.flag & 1 << map.get(p.start)) == 0) {
						if (map.containsKey(i)) {
							if ((p.flag & 1 << map.get(i)) == 0) {
								if(arr[p.start][i] == 3001) continue;
								q.add(new Point(i, p.flag & ~(1 << map.get(i)) | 1 << map.get(i), p.cnt + arr[p.start][i]));
							} else {
								if(arr[i][p.start] == 3001) continue;
								q.add(new Point(i, p.flag & ~(1 << map.get(i)), p.cnt + arr[i][p.start]));
							}
							continue;
						}
						if(arr[i][p.start] == 3001) continue;
						q.add(new Point(i, p.flag, p.cnt + arr[i][p.start]));
					}else {
						if (map.containsKey(i)) {
							if ((p.flag & 1 << map.get(i)) == 0) {
								if(arr[i][p.start] == 3001) continue;
								q.add(new Point(i, p.flag & ~(1 << map.get(i)) | 1 << map.get(i), p.cnt + arr[i][p.start]));
							} else {
								if(arr[p.start][i] == 3001) continue;
								q.add(new Point(i, p.flag & ~(1 << map.get(i)), p.cnt + arr[p.start][i]));
							}
							continue;
						}
						if(arr[p.start][i] == 3001) continue;
						q.add(new Point(i, p.flag, p.cnt + arr[p.start][i]));
					}
				} else {
					if (map.containsKey(i)) { 
						if ((p.flag & 1 << map.get(i)) == 0) {
							if(arr[i][p.start] == 3001) continue;
							q.add(new Point(i, p.flag & ~(1 << map.get(i)) | 1 << map.get(i), p.cnt + arr[i][p.start]));
						}else {
							if(arr[p.start][i] == 3001) continue;
							q.add(new Point(i, p.flag & ~(1 << map.get(i)), p.cnt + arr[p.start][i]));
						}
						continue;
					}
					if(arr[p.start][i] == 3001) continue;
					q.add(new Point(i, p.flag, p.cnt + arr[p.start][i]));
				}
			}
		}
	}    
}
 