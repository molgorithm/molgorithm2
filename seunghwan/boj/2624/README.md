# 백준 2624 동전 바꿔주기

[문제 링크](https://www.acmicpc.net/problem/2624)

## 1. 설계 로직

아주 dp 인 문제

 

1. [동전인덱스] [금액] 인 2차원 dp 배열 생성

2. dp[i] [0] 은 0원 만드는 가짓수는 1개니깐 1로 바꿔주기

3. 현재 인덱스의 동전을 0개 1개 ... n개 까지 써서 각 0~T원 까지 만들 수 있는 갯수 구하기

4. 동전을 다 쓰기전에 현재 금액을 넘긴다면 다음 금액으로 넘어가기

 



![img](https://blog.kakaocdn.net/dn/cFeF8T/btrmDPWOJPC/frTKpiwl2XxmmaLHy9Ennk/img.png)



코드로 봐서 i번째 coin.x원 동전을 가지고

동전의 갯수 m이 가지고 있는 갯수 coin.y를 안넘어가고

m*coin.x가 현재 금액 j를 안넘어 갈때까지

동전 갯수를 증가 시키면서 이전까지 구했던 방법의 갯수를 + 해가면 된다.



- 시간복잡도

  O(k*T)

## 2. 코드

```java
package Gold;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_2624동전바꿔주기 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		int dp [][] = new int[k+1][T+1];
		
		Point coin;
		for(int i = 1 ; i < k+1 ; i++) {
			dp[i-1][0] = 1;
			StringTokenizer st = new StringTokenizer(br.readLine());
			coin = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			for(int j = 1 ; j < T+1 ; j++) 
				for(int m = 0 ; m<=coin.y && m*coin.x<=j;m++)
					dp[i][j] += dp[i-1][j-m*coin.x];
		}
		System.out.println(dp[k][T]);
	}
}
```



## 3. 후기

- dp는 뭔가 설명하기가 어려워,.