# 프로그래머스 72411 메뉴 리뉴얼

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/72411)

## 1. 설계 로직

1. 각 코스에서 **스카피** 가 요구하는 코스 길이에 해당하는 요리들을 조합으로 구해 모두 Map에 삽입
2. 모든 키를 탐색하며 각 코스 길이의 최대값을 구한다.
3. 해당 최대값과 같은 키들을 모두 정답으로 제출

- 시간복잡도 :

## 2. 코드

```java
import java.util.*;


class Solution {

    public String[] solution(String[] orders, int[] course) {
        List<String> result = new ArrayList<>();

        Map<String, Integer> map = new HashMap<>();

        for(int i=0; i<orders.length; ++i){ // 모든 order들을
            for(int j=0; j<course.length; ++j){ // 문제에서 요구하는 course 수만큼
                char[] orderChar = orders[i].toCharArray();
                Arrays.sort(orderChar);
                getOrders(orderChar, new boolean[orderChar.length], 0, orderChar.length, course[j], map); // 조합
            }
        }

        int[] max = new int[course[course.length-1]+1];
        for(String key : map.keySet()){
            int len = key.length(); // 각 키의 길이는 요리의 개수를 의미
            max[len] = Math.max(max[len], map.get(key)); // 따라서 각 코스 길이 별 최대 선택 개수를 기록한다.
        }

        for(String key : map.keySet()){
            int len = key.length();
            if(max[len] >= 2 && max[len] == map.get(key)) result.add(key); // 위에서 구한 최대값과 같은 요리를 추출
        }

        String[] answer = new String[result.size()];
        result.toArray(answer);

        Arrays.sort(answer);

        return answer;

    }

    public void getOrders(char[] orderChar, boolean[] visit, int start, int n, int r, Map<String, Integer> map){

        if(r == 0){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<visit.length; ++i){
                if(!visit[i]) continue;
                sb.append(orderChar[i]);
            }
            String key = sb.toString();

            if(map.containsKey(key)) map.put(key, map.get(key)+1);
            else map.put(key, 1);

            return;
        }

        for(int i=start; i<n; ++i){
            visit[i] = true;
            getOrders(orderChar, visit, i+1, n, r-1, map);
            visit[i] = false;
        }
    }
}
```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
