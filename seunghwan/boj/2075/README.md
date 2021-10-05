# 백준 2075 N번째큰수

[문제 링크](https://www.acmicpc.net/problem/2075)

## 1. 설계 로직

pq활용

\1. 가장 마지막 row의 값들을 pq에 담기

\2. 내림차순으로 하나씩 꺼내기

\3. 꺼낸 수의 윗 값을 pq에 담기

\4. 2-3을 N번 수행 후 나온 값을 출력

 



![img](https://blog.kakaocdn.net/dn/IGfJk/btrguMsxLyX/9PO2LOQKH2Mvlnh5kZyZ7K/img.gif)



- 시간복잡도

  O(NlogN)

## 2. 코드

```java
package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G5_2075N번째큰수 {

	static class node implements Comparable<node> {
		int num;
		int idx;

		public node(int num, int idx) {
			super();
			this.num = num;
			this.idx = idx;
		}

		@Override
		public int compareTo(node o) {
			return Integer.compare(o.num, this.num);
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<node> pq = new PriorityQueue<node>();
		int[] rowIdx = new int[N];
		Arrays.fill(rowIdx, N - 1);
		int map[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			pq.add(new node(map[N-1][i], i));
		}
		int num = 0;
		while (N-- != 0) {
			node now = pq.poll();
			num = now.num;
			rowIdx[now.idx]--;
			if (rowIdx[now.idx] > -1)
				pq.add(new node(map[rowIdx[now.idx]][now.idx], now.idx));
		}
		System.out.println(num);
	}
}
```



## 3. 후기

- 메모리 초과인 풀이들이 있는 것으로 봐선 냅다 다 pq에 넣으면 메모리 초과가 뜨나보다

  근데 다 해봤자 1500 X 1500 = 2250000이고

  int 200만개 쓰면 8MB인데 메모리 제한이 12MB면 흠...