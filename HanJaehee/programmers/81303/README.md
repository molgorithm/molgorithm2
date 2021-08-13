# 프로그래머스 81303 표편집

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/81303)

## 1. 설계 로직

1. D, U일 땐 각각 수만큼 증가
2. C일 땐 쓰레기통에 push 후 인덱스 - 1
3. Z일 떈 쓰레기통에서 꺼내와 삽입 후 현재 위치보다 작은 위치에 삽입되었으면 k 증가
4. 그 후 n 크기의 O으로 이루어진 문자열을 만든 후, 쓰레기통에 남아있는 숫자를 인덱스삼아 X삽입

- 시간복잡도 : N

## 2. 코드

```java
import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmds) {
        Stack<Integer> trash = new Stack<>();
        for(String cmdStr : cmds){
            char cmd = cmdStr.charAt(0);

            if(cmd == 'D'){
                k += Integer.valueOf(cmdStr.substring(2));
            }else if(cmd == 'U'){
                k -= Integer.valueOf(cmdStr.substring(2));
            }else if(cmd == 'C'){
                trash.push(k);
                if(k == --n) k--;
            }else if(cmd == 'Z'){
                int removed = trash.pop();
                if(removed <= k) k++;
                n++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; ++i) sb.append('O');
        while(!trash.isEmpty()) sb.insert(trash.pop().intValue(), 'X');

        return sb.toString();
    }
}
```

## 3. 후기

- 문제 그대로 수행, sb.insert() 함수를 추후에도 잘 활용해야겠다.
