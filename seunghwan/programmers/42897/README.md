# 프로그래머스 42897 도둑질

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42897)





## 1. 설계 로직

![img](https://blog.kakaocdn.net/dn/JCWCP/btreJR3etEA/ExnGBKGGGMkJxQQ6EMRgEK/img.png)



도둑을 도와서? 최대한 많은 돈을 훔치는 문제

 

2개의 인접한 집을 훔치면 경보가 울리게 된다.

 

집들이 동그랗게 연결이 되어있어 마지막 인덱스처리가 관건인 문제이다.

 

먼저 집이 1자로 그냥 연결 되어 있다고 생각하면 어렵지 않다.

현재 내 위치를 i라하면 i-2나 i-3 중 값이 더 큰 곳에서 넘어오면 된다.

식으로 보면

```
dp[i] = Math.max(dp[i-2],dp[i-3])+money[i];
```

 

그런데 마지막 집은 첫번째 집하고 함께 도둑질하지 못하니

첫번째 집을 훔쳤는지 안훔쳤는지가 중요해진다.

그래서 dp 2개를 만들어서

첫번째 집을 훔쳤을 때의 경우와

첫번째 집은 안훔쳤을 경우로 2개의 상황에 대해서 dp 계산을 하고

 

마지막 집을 계산할 때는 첫번째 집에서 안훔친 경우에서만 계산해주면 된다.



![img](https://blog.kakaocdn.net/dn/dkD2T5/btreKvrXCim/ZhV1mowzb1VRWTxgWRLBBk/img.png)



그 후 정답으로 나올 수 있는 후보는

0쓴 거에선 마지막에서 첫번째와 두번째 ( 첫번째와 두번째는 서로 영향을 못주니 둘 다 정답이 나올 수 있다.)

0 안 쓴 거에선 마지막과 마지막에서 첫번째 (위와 동일)

총 4개 중 가장 큰 값을 return 해주면 된다.

- 시간복잡도

  O(N) 

## 2. 코드

```java
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new int[] {1,2,3}));
    }
    static public int solution(int[] money) {
        int [][] dp = new int[2][money.length]; // dp[0] = 0번째 받은거, dp[1] = 0번째 안받은거
        dp[0][0] = money[0];
        dp[1][1] = money[1];
        dp[0][2] = money[0]+money[2];
        dp[1][2] = money[2];
        if(money.length == 3)
            return Arrays.stream(money).max().getAsInt();
        for(int i = 3 ; i < money.length ; i++){
            if(i != money.length-1)
                dp[0][i] = Math.max(dp[0][i-2],dp[0][i-3])+money[i];
            dp[1][i] = Math.max(dp[1][i-2],dp[1][i-3])+money[i];
        }
        int max0 = Math.max(dp[0][money.length-2],dp[0][money.length-3]);
        int max1 = Math.max(dp[1][money.length-1],dp[1][money.length-2]);
        return Math.max(max0,max1);
    }
}
```



## 3. 후기

- 내 코드에선 집이 3개인 경우 3개 중 가장 큰 값이 정답이 된다라는 조건을 넣어줘야하는데

  조건을 안넣어줘도 통과가 된다.
  
  프로그래머스 테케 좀 강화해~~
