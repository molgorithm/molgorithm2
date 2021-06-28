# 프로그래머스 67260 동굴탐험

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/67260)



## 1. 설계 로직

![img](https://blog.kakaocdn.net/dn/c9pnlJ/btq8jK9DOTk/qkwTcwHDFiagosVSNVzy61/img.png)

![img](https://blog.kakaocdn.net/dn/LEWVz/btq8hmnVKi1/rXuCgkHXbKIBDn9nminbM0/img.png)



트리구조에서 모든 정점을 들려야하는데

조건으로 정점 간에 선행관계가 있을 때 이 트리의 모든 정점을 지날 수 있는지 구하는 문제

 

기본 목표는 모든 정점을 도는 것이므로 bfs를 활용하여 풀이하였다.

0번부터 시작하여 bfs를 돌아가는데

정점에 들어 갈 때 3가지 경우가 있다.

1. 선행 정점이 없는 경우

2. 선행 정점이 이미 방문된 경우

3. 선행 정점이 아직 방문되지 않은 경우

 

1번과 2번 경우는 해당 정점을 방문하는데 결격사유가 없으므로 바로 방문을 하면 되고

3번의 경우가 특수한데

해당 정점에 도달은 성공하였지만 선행정점이 아직 방문되지 않은 경우이기 때문에

다르게 보면 선행정점만 방문이 이루어지면 해당정점에도 방문이 가능하다는 소리가 된다.

 

그렇기 때문에 3번상황이 나오게 되면

이후 선행정점을 방문하게 되면 바로 해당정점을 방문하도록 해주면 된다.

 

이것을 제외하고는 일반적인 bfs와 같이 풀이하면 된다.

- 시간복잡도

  O(n) 

## 2. 코드

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class P67260동굴탐험 {
	

	public boolean solution(int n, int[][] path, int[][] order) {
		boolean answer = true;

		ArrayList<ArrayList<Integer>> matList = new ArrayList<ArrayList<Integer>>();
		int[] orderList = new int[n];
		for (int i = 0; i < n; i++)
			matList.add(new ArrayList<Integer>());
		for (int[] i : path) {
			matList.get(i[0]).add(i[1]);
			matList.get(i[1]).add(i[0]);
		}

		for (int[] i : order)
			orderList[i[1]] = i[0];
		int next[] = new int[n];
		boolean visit[] = new boolean[n];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(0);
		int cnt = 0;
		while (!q.isEmpty()) {
			int now = q.poll();
			if (!visit[orderList[now]] && now != orderList[now]) {
				next[orderList[now]] = now;
				continue;
			}
			visit[now] = true;
			cnt++;
			if (next[now] != 0)
				q.add(next[now]);
			for (int i : matList.get(now)) {
				if(!visit[i])
					q.add(i);
			}
		}
		answer = cnt == n ? true : false;
		return answer;
	}

}

```



## 3. 후기

- 처음엔 단순히 order끼리만 비교하여 해결할 수 있지 않을까 싶어 삽질을 했는데

  경로 생성시 순환이 가능하다는 것을 고려하면 그냥 모든 정점을 한번씩 방문이 가능한지만 따지면 되는 문제였다.

   
  
  문제에 함정이 많아 요점을 잡기 까다로웠던 문제였다.
