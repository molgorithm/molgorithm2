# 프로그래머스 43164 여행 경로

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/43164)

## 1. 설계 로직

1. 최대한 시작부터 걸러보려했지만, 결국 완탐에서 걸러내는 것으로 구현
2. ICN으로 시작하는 모든 지점을 통과하는 경로를 모두 찾아 그 중 알파벳 순으로 정렬해 첫번째 값을 리턴한다.

## 2. 코드

```java
import java.util.*;

class Solution {
    List<String> answer = new ArrayList<>();
    public String[] solution(String[][] tickets) {

        dfs(tickets, 0, "ICN", "ICN", new boolean[tickets.length]);
        Collections.sort(answer);
        return answer.get(0).split(" ");
    }
    public void dfs(String[][] tickets, int idx, String now, String total, boolean[] visit){
        if(idx == tickets.length){
            answer.add(total);
            return;
        }

        for(int i=0; i< tickets.length; ++i){
            if(!visit[i] && tickets[i][0].equals(now)){
                visit[i] = true;
                dfs(tickets, idx+1, tickets[i][1], total + " " + tickets[i][1], visit);
                visit[i] = false;
            }
        }
    }

}
```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
