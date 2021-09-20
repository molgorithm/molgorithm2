# 프로그래머스 43164 여행경로

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/43164)

## 1. 설계 로직

1. 고려해야할 사항 
   - 주어진 공항 수 ( 10,000 이하 )
   - tickets의 수는 알 수 없음.
   - 만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
2. 설계로직
   1. 연결 리스트 생성 ( Map으로 구현 )
   2. 연결리스트의 티켓 알파벳순 정렬
   3. 깊이 우선 탐색 
      1. 탈출 조건 : 티켓의 수보다 경로가 1개 더 많을 경우, 정답이 이미 구해졌을 경우 (처음 정답이 나오면 더 이상 탐색을 안해도 됨.)
      2. 현재 위치에서 더 이상 갈 수 있는 곳이 없는 경우 , 이미 방문했을 경우 return 를 제외하고 모든 경로 탐색
3. 시간복잡도: O ( E x logN ) 

## 2. 코드

```java
import java.util.*;
class Solution {
    Map<String, List<String>> map = new HashMap<>();
    Map<String, boolean[]> visited = new HashMap<>();
    boolean flag;
    String ans;
    public String[] solution(String[][] tickets) {
        for(int i = 0; i < tickets.length; i++) {
            String start = tickets[i][0];
            String end = tickets[i][1];
            map.merge(start,new ArrayList<String>(Arrays.asList(end)),(v1,v2) -> {
                v1.add(end);
                return v1;
            });
        }
        
        map.forEach((k,v) -> {
            Collections.sort(v);
            visited.put(k,new boolean[v.size()]);
        });
        
        dfs("ICN","ICN",1,tickets.length + 1);
        return ans.split(" ");
    }
    
    public void dfs(String prev, String result,int count, int maxCount) {
        if(flag) return;
        if(maxCount == count) {
            flag = true;
            ans = result; 
            return;
        }
        
        if(map.get(prev) == null) return;
        
        for(int i = 0; i < map.get(prev).size(); i++) {
            String next = map.get(prev).get(i);
            if(!visited.get(prev)[i]){
                visited.get(prev)[i] = true;
                dfs(next, result+" "+next, count+1, maxCount);
                visited.get(prev)[i] = false;
            }
        }
    }
}
```

## 3. 후기

- 문제의 조건이 부실한 느낌.
- 너비우선탐색인지 깊이우선탐색인지 판단하기 어려울 수 있음.
