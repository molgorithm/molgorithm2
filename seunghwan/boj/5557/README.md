# 백준 5557 1학년

[문제 링크](https://www.acmicpc.net/problem/5557)

## 1. 설계 로직

등식의 갯수가 2^63-1개(long) 까지 가능하다고 한다.

완탐으론 불가능하고 dp를 이용해야 한다.

 

dp로 풀이하기 편하도록 하나의 장치가 있다.

상근이가 아는 숫자가 0부터 20까지 뿐이라 이 밖의 숫자가 중간 연산 중에 나오면 무시된다.는 조건이다.

 

연산에서 나올 수 있는 수를 20까지만 고려하면 되니 dp로 풀이가 가능한 것이다.

 

점화식으로

이번 숫자(arr[i])에 + - 를 해서 0~20을 만들 수 있는 경우의 수를 구하면된다.

dp 배열로 보면 아래와 같이 되고

​    0 1 2 .... 19 20

arr[0]

arr[1]

arr[2]

...

arr[N]

 

arr[0]의 경우 -를 넣으면 음수가 되니 arr[0]인 수 하나만 가능하고

 

그 다음 부터인 arr[1]부터는

dp[1] [0] = dp[0] [0 - arr[1]] + dp[0] [0 + arr[1]]

dp [1] [1] = dp[0] [1 - arr[1]] + dp[0] [1 + arr[1]]

...

dp[1] [20] = dp[0] [1-arr[20]] + dp[0] [1+arr[20]]

이런 식이 될 것이다.

 

예를 들어 이번 수가 3일 때

dp[i] [15]의 갯수는 12+3 = 15 , 18 - 3 = 15 이므로

이전 연산까지 만들어진 12와 18의 갯수를 더한 값이 되는 것이다.

 

설명이 조금 복잡한데 코드로 보면 간단하다.

- 시간복잡도

  O(N) 

## 2. 코드


```java
package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G5_5557a1학년 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr [] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		long dp[][] = new long[N][21];
		dp[0][arr[0]]++;
		for(int i = 1 ; i < N -1 ; i++) {
			int num = arr[i];
			for(int j = 0 ; j<21 ; j++ ) {
				if(j-num >= 0)
					dp[i][j]+=dp[i-1][j-num];
				if(j+num <21)
					dp[i][j]+=dp[i-1][j+num];
			}
		}
		System.out.println(dp[N-2][arr[N-1]]);
	}
}
```




## 3. 후기

20 조건을 생각못하고 이걸 어떻게 풀지 하고 삽질을 많이 했다..
