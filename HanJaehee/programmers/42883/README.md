# 프로그래머스 42883 큰 수 만들기

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42883)

## 1. 설계 로직

1. 먼저, 어떤 수에서 k개의 수를 제거한 수의 길이를 result_len이라고 할 때,
2. 어떤 수의 가장 앞자리부터 result_len까지 중 가장 큰 수를 찾는다. 그것이 우리가 구하려는 가장 큰 수 의 가장 앞자리가 된다.
3. 이후 구한 수의 가장 뒷자리부터 지속적으로 큰수를 찾아 result_len만큼 반복하면 가장 큰 숫자를 구할 수 있다.

- 시간복잡도 : N

## 2. 코드

```java
import java.util.*;

class Solution {
    public String solution(String number, int k){

        StringBuilder result = new StringBuilder();
        char[] numbers = number.toCharArray();
        int len = numbers.length - k;
        int pos = 0;

        while(len > 0){

            char max = ' ';

            for(int i=pos; i<=numbers.length - len; ++i){
                if(numbers[i] > max){
                    max = numbers[i];
                    pos = i;
                }
            }

            result.append(max);
            pos++;
            len--;
        }
        return result.toString();
    }
}
```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
