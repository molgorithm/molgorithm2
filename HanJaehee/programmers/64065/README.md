# 프로그래머스 64064 불량 사용자

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64065)

## 1. 설계 로직

1. 주어진 문자열을 "},{"을 기준으로 분리한다.
2. 분리된 문자열의 길이에 대해 오름차순으로 정렬한다.
3. 정렬된 문자열을 순회하며 Set에 포함되어있지 않은 문자들을 추가한다.

- 시간복잡도 : N!

## 2. 코드

```java
import java.util.*;
class Solution {

    public int[] solution(String s) {
        s = s.substring(2, s.length()-2);
        String[] sList = s.split("\\},\\{");
        Arrays.sort(sList, (a,b)-> a.length() - b.length());
        Set<Integer> set = new HashSet<>();

        int[] result = new int[sList.length];
        int idx = 0;
        for(String tmpString : sList){
            String[] tmpList = tmpString.split(",");
            for(String tmp : tmpList){
                int num = Integer.parseInt(tmp);
                if(!set.contains(num)){
                    result[idx++] = num;
                    set.add(num);
                }
            }
        }

        return result;
    }
}
```

## 3. 후기

- 무난..?
