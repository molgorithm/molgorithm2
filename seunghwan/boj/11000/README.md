# 백준 11000 강의실배정

[문제 링크](https://www.acmicpc.net/problem/11000)

## 1. 설계 로직

1. 시작시간 끝시간 각각 다른 pq에 저장

2. 시작에서 하나씩 꺼내서 class갯수 증가

3. 꺼낸 시작시간과 같거나 작은 끝시간이 있으면 모두 poll 하고 class 개수 감소

4. 갯수 증가 할 때마다 max 확인

5. 시작시간 pq에서 다 꺼내면 더 이상 class가 증가할 일 없으니 종료

6. max 출력



- 시간복잡도

  O(N)

## 2. 코드

```java
package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G5_11000강의실배정 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> start = new PriorityQueue<Integer>();
		PriorityQueue<Integer> end = new PriorityQueue<Integer>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			start.add(Integer.parseInt(st.nextToken()));
			end.add(Integer.parseInt(st.nextToken()));
		}
		int classs = 0;
		int max = 0;
		while (!start.isEmpty()) {
			int s = start.poll();
			while (end.peek() <= s) {
				end.poll();
				classs--;
			}
			max = Math.max(max, ++classs);
		}
		System.out.println(max);
	}
}
```



## 3. 후기

- class가 변수명으로 못써서 classs로 했슴니다.

