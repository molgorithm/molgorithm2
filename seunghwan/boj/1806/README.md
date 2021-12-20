# 백준 1806 부분합

[문제 링크](https://www.acmicpc.net/problem/1806)

## 1. 설계 로직

투포인터로 쉽게 풀이 가능

 

1. start end 0으로 두고 탐색시작

2. 합이 S넘어 갈 때까지 end >> 이동

3. S 넘어가면 갯수 min에 초기화 시키고 start>>이동 (sum에선 이전 수 뺄셈)

4. 2,3 반복 하다가 합이 S가 안넘는데 end가 끝에 도착하면 반복 종료

5. min 출력



- 시간복잡도

  O(N)

## 2. 코드

```java
package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_1806부분합 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int [] arr = new int [N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		int start = 0;
		int end = 0;
		int sum = arr[start];
		int min = 987654321;
		while (true) {
			if (sum >= S) {
				min = Math.min(min, end - start + 1);
				sum -= arr[start++];
			} else {
				if(end == N-1)
					break;
				sum += arr[++end];
			}
		}
		System.out.println(min == 987654321 ? 0 : min);
	}

}
```



## 3. 후기

- 처음에 연속된 수열이래서

  증가수열로 생각하고 자체 하드모드로 삽질했는데

  그냥 인덱스가 붙어있는 것이었음 하...

   

  이 풀이 말고 누적합으로도 풀이할 수 있을 것 같은데

  큰 차이는 없을 것 같으니 패쓰