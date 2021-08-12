# 프로그래머스 64065 튜플

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64065)

## 1. 설계 로직

1. 고려해야할 사항 
   - s 문자열 1,000,000 -> 선형탐색
   - 카운팅할 자료구조 ( Array, Map ) 
2. 설계로직
   1. HashMap 사용 -> key : 원소, value : 갯수
   2. 괄호 사이의 문자열을 분리해 배열로 만들기
   3. 각 배열의 문자를 Hash맵에 저장 & 카운팅
   4. 역순 정렬
   5. 출력
3. 시간복잡도: O (n x logn) 

## 2. 코드

```java
import java.util.*;
class Solution {
    static Map<String,Integer> map = new HashMap<>();

    public int[] solution(String s) {
        
        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) == '{'){
                int idx = i;
                while(s.charAt(idx) != '}') idx++;
                Arrays.stream(s.substring(i+1,idx).split(","))
                    .forEach(o -> map.merge(o,1,Integer::sum));
                i = idx;
            }
        }
        
        return map.keySet().stream()
            .sorted((o1,o2) -> map.get(o2) - map.get(o1))
            .mapToInt(Integer::parseInt)
            .toArray();

    }
}
```

## 3. 후기

- 문자열만 잘 처리하면 풀리는 문제.
