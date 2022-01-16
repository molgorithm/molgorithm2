# 백준 9084 동전

[문제 링크](https://www.acmicpc.net/problem/9084)

## 1. 설계 로직

    1. C원 짜리 동전으로 M원을 만들 수 있는 가지 수를 배열로 만들었을 때, cache[M] = cache[M-C]가 성립한다.
    2. 2원 짜리로 만들 수 있는 가지 수가 2원, 4원, 6원, 8원...이 같다.
    3. 그리고 3원짜리로 6원을 만들 수 있는 가지수도 3원, 6원, 9원...이 같다.
    4. 그럼 2원, 3원짜리로 6원을 만들 수 있는 가지수는 2원, 3원으로 각각 6원짜리까지 가지수를 구한 후 누적한다.

- 시간복잡도 : N

## 2. 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 동전 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int[] coins, cache;
        for (int test = 0; test < T; test++) {
            int N = Integer.parseInt(br.readLine());
//            coins = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            coins = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) coins[i] = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(br.readLine());

            cache = new int[M + 1];
            cache[0] = 1;
            for (int coin : coins) {
                for (int i = coin; i <= M; i++) {
                    cache[i] += cache[i - coin];
                }
            }

            System.out.println(cache[M]);
        }
    }
}


```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
