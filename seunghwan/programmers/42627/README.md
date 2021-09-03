# 프로그래머스 42627 디스크컨트롤러

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42627)



## 1. 설계 로직

대기 시간을 가장 줄이는 방법은

현재 시간에 작업을 시작할 수 있는 작업 중 걸리는 시간이 가장 짧은 것 부터 작업을 하면 된다.



![img](https://blog.kakaocdn.net/dn/cTG4TV/btrdKWXJ8NR/D9n5wV2rABg8k7wKuE3kz1/img.png)



걸리는 시간이 가장 짧은 것을 찾기 위해 pq를 활용하면 쉽게 해결 가능하다.

 

\1. jobs를 시작시간에 맞춰 오름차순 정렬한다.

\2. 현재 시간에 작업이 가능한 작업들을 pq에 집어 넣는다.

\3. pq에서 작업 시간이 제일 작은 작업을 빼서

\4. 시간을 지나게 하고 (지나간 시간 - 작업이 들어온 시간) 을 answer에 + 한다.

\5. 작업의 갯수를 cnt에 저장하고

\6. 모든 작업이 끝나고 answer / cnt 를 return 한다.



- 시간복잡도

  O(nlogn)

## 2. 코드

```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P42627디스크컨트롤러 {
	class job {
		int start;
		int end;

		job(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public int solution(int[][] jobs) {
		PriorityQueue<job> pq = new PriorityQueue<>(new Comparator<job>() {
			@Override
			public int compare(job o1, job o2) {
				return o1.end - o2.end;
			}
		});

		Arrays.sort(jobs, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		int answer = 0;
		int cnt = 0;
		int time = jobs[0][0];
		int index = 0;

		while (cnt < jobs.length) {
			while (index < jobs.length && jobs[index][0] <= time)
				pq.offer(new job(jobs[index][0], jobs[index++][1]));

			if (!pq.isEmpty()) {
				job now = pq.poll();
				time += now.end;
				answer += time - now.start;
				cnt++;
			} else
				time = jobs[index][0];

		}

		return answer / cnt;
	}
}

```



## 3. 후기

- 예제로 힌트를 다 줘서 그렇지 힌트 없이 풀려면 생각 좀 했어야 했을 것 같다.
