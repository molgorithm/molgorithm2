# 백준 1000 A+B

[문제 링크](https://www.acmicpc.net/problem/9084)

## 1. 설계 로직

동전 갯수 구할 금액 : M

동전 종류 : N1, N2, ... NN

1. 1~M원까지 N1으로 만들수 있는 갯수 구해서 dp배열에 저장

2. 구할 때 dp[구할금액 - N1]에 구해진 갯수를 + 해주면 구하기 쉬움.

3. N1으로 만들어진 dp배열 위에 2번 방법으로 N2도 써서 구할 수 있는 갯수 구하기

4. NN까지 반복

5. dp[M] 출력

- 시간복잡도

  O(NM)

## 2. 코드

```java
package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G5_9084동전 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			br.readLine();
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int money = Integer.parseInt(br.readLine());
			int dp[] = new int[money + 1];
			dp[0] = 1;
			for (int cost : arr)
				for (int i = cost; i < money + 1; i++)
					dp[i] += dp[i - cost];
			System.out.println(dp[money]);
		}
	}
}
```



## 3. 후기

- 오랜만에 디피 풀었더니 조금 헷갈리는 느낌.