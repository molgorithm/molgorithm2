# 프로그래머스 43105 정수 삼각형

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/43105)



## 1. 설계 로직

1. dp의 가장 기본이 되는 문제

    

   길 중에 자신한테 올 수 있는 길은 자신의 왼쪽에서 오거나 오른쪽에서 오거나 둘 중 한 경우 뿐이다.

   그렇다면 둘 중에 더 큰 수를 골라서 자신한테 오는 길로 선택하면 된다.

   모든 위치의 수가 위와 같이 선택해서 자신의 수와 더하면

   맨 마지막 줄의 값 중 가장 큰 값이 제일 큰 값이 될 것이다.

    

   가장 왼쪽의 수는 가장 왼쪽의 수밖에 못고르고

   가장 오른쪽의 수는 가장 오른쪽의 수밖에 못 고른다는 것만 주의해서 풀이하면 된다.

3. 시간 복잡도

- O(N) a배열의 크기만큼

## 2. 코드

```java
import java.util.Arrays;

public class P43105정수삼각형 {

    public int solution(int[][] a) {
        for(int i =1 ; i < a.length ; i++)
            for(int j = 0 ; j < a[i].length ; j++)
                a[i][j] += j==0 ? a[i-1][j] : j == a[i].length-1 ? a[i-1][j-1] : Math.max(a[i-1][j-1],a[i-1][j]);
        return Arrays.stream(a[a.length-1]).max().getAsInt();
    }

}
```



## 3. 후기

- 쉬운 문제라 코드 최대한 줄여봤다.

  파이썬은 한 줄로도 가능하지 않으려나?
