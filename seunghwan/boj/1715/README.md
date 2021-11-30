# 백준 1715 카드정렬하기

[문제 링크](https://www.acmicpc.net/problem/1715)

## 1. 설계 로직

현재 가지고 있는 카드뭉치 중 가장 작은 것 2개를 뽑아 새 뭉치를 만들면 최소의 비교로 가능하다.

 

여기서 현재 가지고 있는 카드뭉치는 새롭게 합쳐져 만들어진 뭉치까지 포함해서 작은 것 2개이다.

 

1. pq에 모든 카드 갯수 넣기

2. 작은 것 2개를 뽑아 비교횟수를 answer에 저장하고

3. 새로 만들어진 뭉치를 pq에 다시 넣기

4. 뭉치가 하나만 남을 때까지 돌고 answer 출력



- 시간복잡도

  O(N)

## 2. 코드

```java
package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class G4_1715카드정렬하기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++)
			pq.add(Integer.parseInt(br.readLine()));
		int answer = 0;
		while (pq.size() != 1) {
			int cnt = pq.poll() + pq.poll();
			pq.add(cnt);
			answer += cnt;
		}
		System.out.println(answer);
	}

}
```



## 3. 후기

- 많이 겪어본 유형이라 어렵지 않게 해결 가능했다.

