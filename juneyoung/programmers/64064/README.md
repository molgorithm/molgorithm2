# 프로그래머스 64064 불량 사용자

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64064)

## 1. 설계 로직

1. 고려해야할 사항 
   - user_id 배열 ( 8 ) & 8이하의 문자열, banned_id 배열 ( 8 ) -> 완탐
   - 순열 or 조합 사용해서 풀이할  수 있음.
2. 설계로직
   1. 중복을 피하기위해 Set과 특정 값 배열 사용 
      - 비트마스킹과 같은 원리
   2. 순열로 모든 문자열 경우의 수 구하기
   3. banned_id와 비교 -> 완탐
   4. 문자열 조합이 될 경우 값으로 변환 후 set에 저장
   5. set 갯수 리턴
3. 시간복잡도: O (n!) 

## 2. 코드

```java
import java.util.*;

class Solution {
    static Map<String,Integer> map = new HashMap<>();
    static Set<Integer> set = new HashSet<>();
    static List<Integer> key = Arrays.asList(8,80,800,8000,80000,800000,8000000,80000000);
    static String[] result;
    static int size;
    static boolean[] v;
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        size = banned_id.length;
        result = new String[size];
        v = new boolean[user_id.length];
        for(int i = 0; i < user_id.length; i++) {
            map.put(user_id[i],key.get(i));
        }
        
        permutation(0, user_id, banned_id);
        
        return set.size();
    }
    
    public static boolean check(String banned_id,String user_id) {
        for(int i = 0; i < banned_id.length(); i++) {
            if(banned_id.charAt(i) == '*') continue;
            if(banned_id.charAt(i) != user_id.charAt(i)) return false;
        }
        return true;
    }
    
    public static void permutation(int idx, String[] user_id, String[] banned_id){
        if(idx == size) {
            int count = 0;
            boolean[] idCheck = new boolean[size];
           L:for(int i = 0; i < size; i++) {
                int bandSize = banned_id[i].length();
                for(int j = 0; j < result.length; j++)  {
                    if(bandSize == result[j].length() && !idCheck[j] && check(banned_id[i],result[j])){
                        idCheck[j] = true;
                        count++; 
                        continue L;
                    }    
                }
            }
            
            if(count == size){
                set.add(Arrays.stream(result)
                    .mapToInt(s -> map.get(s))
                    .sum());
            }
            return;
        }
        
        for(int i = 0; i < user_id.length; i++) {
            if(v[i]) continue;
            v[i] = true;
            result[idx] = user_id[i];
            permutation(idx+1, user_id, banned_id);
            v[i] = false;
        }
    }
}
```

## 3. 후기

- 조합으로 풀려면 고려해야할 부분이 많아서 순열로 구현했음.
- 자료구조를 잘 사용하면 쉬운문제
