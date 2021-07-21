# 프로그래머스 72410 신규 아이디 추천

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/72410)

## 1. 설계 로직

1. 명세서에 맞춰서 구현하는 문제입니다.
2. 정규표현식과 replaceAll 함수를 이용하여 불 필요한 문자를 제거했습니다.

- 시간복잡도: 최대 1000 * a ( a <= 1000 ) 
- O(N*a)

## 2. 코드

```java
package programmers;

import java.util.*;

class Solution {
    public String solution(String new_id) {
        StringBuilder sb = new StringBuilder();
        String id = new_id.toLowerCase()
                .replaceAll("[^a-z0-9-_.]", "")
                .replaceAll("[.]{2,}", ".")
                .replaceAll("^[.]|[.]$", "");
        id = id.length() == 0 ? "a" : id;
        id = id.length() >= 16 ? id.substring(0, 15) : id;
        id = id.replaceAll("[.]$", "");
        sb.append(id);
        for (int i = sb.length(); i < 3; i++)
            sb.append(sb.substring(sb.length() - 1));
        return sb.toString();
    }
}

```

## 3. 후기

- 정규표현식으로 풀이하니까 코드가 깔끔해졌습니다.
- 문자열 문제는 되도록 StringBuilder를 사용해서 풀이하는 게 좋은 것 같습니다.
