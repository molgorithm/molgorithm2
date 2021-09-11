# 프로그래머스 42885 구명보트

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42885)

## 1. 설계 로직

1. 오름차순으로 정렬
2. 오른쪽 사람은 항상 내린다.
3. 왼쪽의 사람은 오른쪽 내리는 사람과 무게를 합했을 때, 제한이 넘지 않아야 내린다.

## 2. 코드

```java
import java.util.*;

class Solution {

    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);

        int end = people.length -1;
        int start = 0;

        while(end >= start){
            int weight = people[end--];
            if(weight + people[start] <= limit)
                start++;

            answer++;
        }

        return answer;
    }

}
```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
