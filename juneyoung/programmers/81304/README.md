# 프로그래머스 81304 미로탈출

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/81304)

## 1. 설계 로직

1. 고려해야할 사항

   - 방의 갯수 n(1000) , traps의 길이 (10)

   - 함정으로 이동하면, 이동한 함정과 연결된 모든 화살표의 방향이 바뀝니다.

     - 간선의 방향이 수시로 변함. 

     - 트랩의 상태를 가지고 있어야 함.

2. 설계로직

   1. 해시맵을 사용하여 함정을 저장함. -> Set이나 1차원 배열로 가능

   2. 간선 배열 만들기

   3. 다익스트라 알고리즘 시작

      - 현재 노드가 함정일 경우,  현재 노드가 함정이 아닐 경우. 

      - 다음 노드가 함정일 경우, 다음 노드가 함정이 아닐 경우.
      - 함정이 아닐 경우 간선 배열의 행 사용
      - 함정일 경우는 간선 배열의  열 사용

3. 시간복잡도: O ( 1024 x  E xlogV )

## 2. 코드

```
import java.util.*;


class Solution {
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
								q.add(new Point(i, p.flag | (1 << map.get(i)), p.cnt + arr[p.start][i]));
							} else {
								if(arr[i][p.start] == 3001) continue;
								q.add(new Point(i, p.flag - (1 << map.get(i)), p.cnt + arr[i][p.start]));
							}
							continue;
						}
						if(arr[i][p.start] == 3001) continue;
						q.add(new Point(i, p.flag, p.cnt + arr[i][p.start]));
					}else go(p,i);
                
                } else go(p,i);
			}
		}
	}    
    
    public static void go(Point p,int i) {
        if (map.containsKey(i)) {
            if ((p.flag & 1 << map.get(i)) == 0) {
                if(arr[i][p.start] == 3001) return;
                q.add(new Point(i, p.flag | (1 << map.get(i)), p.cnt + arr[i][p.start]));
            } else {
                if(arr[p.start][i] == 3001) return;
                q.add(new Point(i, p.flag - (1 << map.get(i)), p.cnt + arr[p.start][i]));
            }
            return;
        }
        if(arr[p.start][i] == 3001) return;
        q.add(new Point(i, p.flag, p.cnt + arr[p.start][i]));
    }
}
```

## 3. 후기

- 그냥 다익스트라만 풀어봤다면 해결하기 힘들다.
- 비트마스킹이 들어가서 난이도가 높음.