# 프로그래머스 67260 동굴 탐험

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/67260)

## 1. 설계 로직

1. 고려해야할 사항 
   - n ( 200,000 ) -> 선형탐색으로 해결 
   - 트리형태의 탐색문제 -> bfs일 확률이 높음.
   - 방문 시 우선순위가 있음. -> 1차원 방문배열하나로 해결하기 힘들 수 있음.
2. 설계로직
   1. 우선순위를 해결하기 위해 HashMap 사용 , 이전 노드를 갔다 왔는지 확인하기 위해 prev 배열 사용
      1. A -> B 인 map과
      2. B -> A 인 revMap 사용 ( 선행 노드를 확인하기 위해 )
   2. 시작점이 0 이기 때문에 0부터 큐에 add
   3. 해당 노드에서 갈 수 있는 노드 확인
      1. 이전 노드를 방문했다고 표시
      2. 이미 방문했거나, 선행노드를 방문하지 않았다면 (revMap 사용) 패스~
      3. 그외 모든 노드들 큐에 저장
   4. 해당 노드가 선행 노드일 경우, 후행 노드의 이전 노드를 방문했다면 큐에 저장
3. 시간복잡도: O ( n  + E ) 

## 2. 코드

```java
import java.util.*;

class Solution {
    static List<Integer>[] list;
    static boolean[] v, prev;
    static Map<Integer,Integer> map = new HashMap<>(), revMap = new HashMap<>();
    public boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = true;
        list = new ArrayList[n];
        prev = new boolean[n];
        v = new boolean[n];
        prev[0] = true; 
        
        for(int i = 0; i < n; i++) 
            list[i] = new ArrayList<>();
        
        for(int i = 0; i < path.length; i++) {
            int start = path[i][0];
            int end = path[i][1];
            list[start].add(end);
            list[end].add(start);
        }
        
        for(int i = 0; i < order.length; i++) {
            map.put(order[i][0],order[i][1]);
            revMap.put(order[i][1],order[i][0]);
        }
            
        if(revMap.containsKey(0)) return false;
        cal();
        for(int i = 0; i < n; i++)
            if(!v[i]) answer = false;
        
        return answer;
    }
    
    public static void cal() {
        Queue<Integer> q = new LinkedList<>();
        v[0] = true;
        q.add(0);
        while(!q.isEmpty()) {
            int p = q.poll();
            int size = list[p].size();
            
            for(int i = 0; i < size; i++) {
                int idx = list[p].get(i);
                prev[idx] = true;
                if(v[idx]) continue;
                if(revMap.containsKey(idx) && !v[revMap.get(idx)]) continue;
                v[idx] = true;
                q.add(idx);
            }
            
            if(map.containsKey(p) && prev[map.get(p)]) {
                v[map.get(p)] = true;
                q.add(map.get(p));
             }
        }
    }
}
```

## 3. 후기

- 조건 중에 0번 노드의 선행노드가 있는지 확인 안해서 오래 걸림. 조심합시다! 
- 이렇게 여러번 방문할 수 있어야 하는 문제들은 보통 3차원 방문배열이나 비트마스킹으로 풀리는데 이 문제는 선형탐색의 시간복잡도로만 풀어야 해서 난이도가 높은 문제라고 생각 함. 
