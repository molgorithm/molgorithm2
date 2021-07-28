# 프로그래머스 72411 메뉴 리뉴얼

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/72411)

## 1. 설계 로직

1. 고려해야할 사항 
   - orders 배열 (20), orders 원소 (10), course 배열 (10) -> 완탐
   - 문자열 조합 -> 카운팅 -> 정렬 -> 결과 순
   - HashMap을 사용하여 key 는 문자열, value는 개수
   - 해당 문자열 길이의 최대 개수를 담는 배열 필요.
   
2. 설계로직
   1. HashMap을 사용하여 key 값은 문자열, value는 개수

   2. 최대 문자열 길이가 10 이므로 부분집합으로 구해도 충분함.

      - 부분집합을 구하는 방법 
        1. 방문배열 사용한 재귀
        2. 비트마스킹 ( 문자열 길이 제한 있음.)

      - 리팩토링 : 조합으로 구하면 연산이 더 적음.

   3. 부분집합으로 구한 모든 문자열중 course의 값에 해당하는 문자열 길이만 Map에 저장

   4. 해당 문자열 길이의(코스요리의 개수) 최대 개수 갱신.

   5. Map의 key값과 value값을 이용하여 코스요리의 개수가 2개 이상이고 최대값인 값들 추출 -> 정렬 -> String 배열로 변환

3. 시간복잡도

   - 2^10(문자열 최대 길이) x 10 ( course 배열 길이) + NlogN(정렬) 

## 2. 코드

```java
import java.util.*;
class Solution {
    static Map<String,Integer> map = new HashMap<>();
    static int[] count = new int[11];
    public String[] solution(String[] orders, int[] course) {
        for(String order : orders) {
            powerSet(order,order.length(),course);
        }
        
        map.forEach((k,v)->{
            count[k.length()] = Math.max(count[k.length()],v);
        });
        
        String[] answer = map.keySet().stream()
            .filter((k) -> count[k.length()] >= 2 && count[k.length()] == map.get(k)).sorted().toArray(String[]::new);
        return answer;
    }
    
    public void powerSet(String order,int len,int[] course) {
        char[] orderArr = order.toCharArray();
        Arrays.sort(orderArr);
        
        for(int i = 0; i < 1 << len; i++) {
            String str = "";
            for(int j = 0; j < len; j++) {
                if ((i & 1 << j) != 0) str += orderArr[j];
            }
            if(str.equals("")) continue;
            int size = str.length();
            for(Integer c : course){
                if(c == size) {
                    map.merge(str,1,Integer::sum);
                    count[str.length()] = Math.max(count[str.length()],map.get(str));
                }
            }
        }
    }
}

```

## 3. 후기

- 문제를 잘못 읽어서 조합을 안쓰고 부분집합 사용. 그래도 충분히 빠른 알고리즘
- 시간복잡도가 낮기 때문에 Stream 사용해도 무리없을거같음.
- Java 8 에서 지원해주는 함수 사용하면 유리함.
