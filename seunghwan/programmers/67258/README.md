# 프로그래머스 67258 보석쇼핑

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/67258)



## 1. 설계 로직

1. 같은 갯수라면 앞의 것 부터이니 조건 하나 신경안쓰려고 뒤에서부터 탐색.

2. 처음 만나는 문자라면 무조건 포함 되어야함.

3. 두번째 이상 만나는 문자라면 포함하지 않을 수 있음.

3-1. end에서 부터 포함하지 않아도 되는 문자를 넘겨보고

(미포함에서 넘어간 문자 인덱스 - 이번에 들어온 문자 인덱스) >= end - start 라면 start와 end 갱신

- 시간복잡도

  O(n) 최악 2n 

## 2. 코드

```java
import java.util.*;

public class P67258보석쇼핑 {

    static public int[] solution(String[] gems) {
        boolean[] nextGem = new boolean[gems.length];
        HashMap<String, Integer> map = new HashMap<>();
        int start = gems.length - 1;
        int end = gems.length - 1;
        for (int i = gems.length - 1; i >= 0; i--) {
            String nowGem = gems[i];
            if (!map.containsKey(nowGem))  //처음 본 문자라면
                start = i;
            else  //이미 본 문자면 nextGem에 체크
                nextGem[map.get(nowGem)] = true;

            int tempend = end;
            while (nextGem[tempend--])


            if (tempend - i <= end - start) {
                start = i;
                end = tempend;
            }
            map.put(nowGem, i);
        }
        return new int[]{start + 1, end + 1};
    }
}
```



## 3. 후기

- 대부분 투포인터를 이용해서 

  종류 다 가질때까지 right 이동

  다 가지면 left 이동

  이것만 반복하면서 가장 짧은 것을 업데이트 하는 방식으로 풀었는데

  이 방법을 떠올렸으면 훨씬 빨리 풀었을 것 같다..
