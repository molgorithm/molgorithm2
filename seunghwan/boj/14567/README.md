# 백준 14567 선수과목

[문제 링크](https://www.acmicpc.net/problem/14567)

## 1. 설계 로직

일반적인 풀이는 위상정렬이고

추가로 선수 과목의 조건이 (1≤A<B≤N) 형태로 순차적이기 때문에 dp 풀이가 가능하다.

 

두가지 방법으로 모두 풀어보았다.

 

위상정렬 풀이

1. 부모 -> 자식 으로 연결리스트 생성

2. 연결리스트 생성하며 진입 차수(부모의 수) 저장

3. 진입 차수가 0인 노드 que에 저장

4. 한 뎁쓰가 한 학기가 되니 que의 size를 이용해 뎁쓰 표현

5. 하나 씩 꺼내서 answer[]에 학기 저장하고 자식들의 진입차수를 하나씩 빼기

6. 진입차수가 0이 된 자식은 que에 add

7. que가 비어지면 모든 answer 출력

 

dp 풀이

1. 자식 -> 부모로 연결리스트 생성

2. dp 배열 생성

3. 따로 조건을 안넣어주기 위해 모든 노드에 부모 0을 추가

4. 1부터 순서대로 노드의 부모들 중 가장 큰 수 +1을 dp에 저장

5. dp 출력



- 시간복잡도

  O(N)

## 2. 코드

```java
package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_14567선수과목_위상 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Integer> arr[] = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			arr[i] = new ArrayList<Integer>();
		int parentCnt[] = new int[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from].add(to);
			parentCnt[to]++;
		}
		Queue<Integer> que = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++)
			if (parentCnt[i] == 0)
				que.add(i);
		int answer[] = new int[N + 1];
		int hak = 1;
		while (!que.isEmpty()) {
			int size = que.size();
			while (size-- != 0) {
				int now = que.poll();
				answer[now] = hak;
				for (int child : arr[now])
					if (--parentCnt[child] == 0)
						que.add(child);
			}
			hak++;
		}
		for(int i = 1 ; i <= N ; i++) 
			System.out.print(answer[i]+" ");
		
	}

}
```

```java
package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class G5_14567선수과목_dp {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Integer> parents[] = new ArrayList[N + 1];
		int dp[] = new int [N+1];
		dp[0] = 0;
		for (int i = 1; i <= N; i++) {
			parents[i] = new ArrayList<Integer>();
			parents[i].add(0);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			parents[to].add(from);
		}
		for(int i = 1 ; i <= N ; i++) {
			for(int j : parents[i])
				dp[i] = Math.max(dp[i], dp[j]+1);
			System.out.print(dp[i]+" ");
		}
	}

}
```



## 3. 후기

- 확실히 dp가 코드가 짧네

