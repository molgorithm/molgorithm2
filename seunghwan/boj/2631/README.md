# 백준 2631 줄세우기

[문제 링크](https://www.acmicpc.net/problem/2631)

## 1. 설계 로직

가장 적은 수의 이동으로 정렬 상태를 만드는 문제

 

가장 적은 수만 이동시켜 정렬을 시키기 위해선

가장 많이 정렬된 상태의 인원들을 찾아 그 이외의 사람들만 이동시키면 된다.

 

가장 많이 정렬된 상태? -> 가장 긴 정렬 상태? -> 최장 증가 수열 !

 

그렇다 LIS(Longest Incerasing Subsequence)를 구하는 문제였다.

 

LIS를 구하는 방법으로 내가 아는 것은 dp와 이분탐색이 있는데

N의 크기가 200밖에 되지 않으니 N^2으로 충분히 풀이가능하여 dp를 활용하였다.

 

LIS를 구한 뒤 자리를 옮기는 인원의 수를 구해야 하므로

전체인원에서 정렬된 인원의 수를 뺀 값을 출력하면 된다.



- 시간복잡도

  O(N^2)

## 2. 코드

```java
package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class G5_2631줄세우기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		int dp[] = new int[N];
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i - 1; j >= 0; j--)
				if (arr[j] < arr[i])
					dp[i] = Math.max(dp[j] + 1, dp[i]);
			max = Math.max(dp[i], max);
		}
		System.out.println(N - max);
	}
}
```



## 3. 후기

- LIS를 한번이라도 접해 봤다면 빠르게 풀이 가능한 문제 같다.

