# 프로그래머스 64063 호텔 방 배정

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64063)

## 1. 설계 로직

1. 고려해야할 사항 
   - k 엄청 큰 수, room_number 배열( 200,000 ) -> 완탐 불가능, 배열생성 불가능
   - 자료구조 필요.
2. 설계로직
   1. HashMap 사용 -> key : 현재 방, value : 가능한 다음 방
   2. 재귀함수를 통해 Union - Find 알고리즘 구현
      1. map에 현재 값이 없으면 저장

      2. 있으면 재귀를 돌면서 가능한 방을 찾음. 

         -> 입력받은 숫자와 다음 가능한 방으로 연결 ( 최적화 )
3. 시간복잡도: O ( n ) 

## 2. 코드

```java
import java.util.*;

class Solution {
    static Map<Long,Long> map = new HashMap<>();
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        
        for(int i = 0; i < room_number.length; i++) {
            answer[i] = cal(room_number[i]);
        }
        return answer;
    }
    
    public static long cal(long num){
        if(!map.containsKey(num)){
            map.put(num,num+1);
            return num;
        }
        
        long findNum = cal(map.get(num));
        map.put(num, findNum+1);
        return findNum;
    }
}
```

## 3. 후기

- k 값이  크기 때문에 배열을 생성할 수 없어서 자료구조가 필요하다는것을 유추할 수 있음.
- 문제 설명을 읽어보면 Map을 사용해야 한다고 알 수 있음.
- Union-Find 알고리즘을 생각하기 어려움.
