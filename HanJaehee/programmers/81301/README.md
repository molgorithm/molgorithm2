# 프로그래머스 81301 숫자 문자열과 영단어

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/81301)

## 1. 설계 로직

1. 한 문자씩 순차적으로 돌면서 알파벳 소문자 'a' ~ 'z' 인 문자를 탐색한다.

2. 알파벳 소문자를 발견할 경우 해당 문자부터 인덱스를 증가시키며 문자를 StringBuilder에 담는다.

3. 문자열의 길이가 3이상일 경우 미리 Map에 담아둔 keySet()과 비교하며 일치할 경우 값을 리턴한다.

4. 반복

- 시간복잡도 : N(문자열의 길이)

## 2. 코드

```java
import java.util.*;

class Solution {
    public int solution(String s) {
        Map<String, Integer> map = new HashMap();
        map.put("one", 1); map.put("six", 6);
        map.put("two", 2);
        map.put("four", 4); map.put("five", 5);
        map.put("nine", 9); map.put("zero", 0);
        map.put("seven", 7); map.put("three", 3);
        map.put("eight", 8);

        StringBuilder result = new StringBuilder();
        StringBuilder tmp = new StringBuilder();
        char chr;
        int num;
        for(int i=0; i<s.length(); ++i){
            chr = s.charAt(i);
            if(chr >= 'a' && chr <= 'z'){
                tmp = new StringBuilder();
                while(chr >= 'a' && chr <= 'z' && i < s.length()){
                    tmp.append(s.charAt(i));
                    if(tmp.length() >= 3){
                        num = search(map, tmp.toString());
                        if(num != -1){
                            result.append(num);
                            break;
                        }
                    }
                    i++;
                }
            }else{
                result.append(chr);
            }
        }

        return Integer.parseInt(result.toString());

    }

    public int search(Map<String, Integer> map, String s){
        for(String str : map.keySet()){
            if(str.equals(s)){
                return map.get(s);
            }
        }
        return -1;
    }
}
```

## 3. 후기

- Map을 나눠 문자열 길이별로 생성할까도 생각했지만, 워낙 작아서 불필요하다고 생각됨
