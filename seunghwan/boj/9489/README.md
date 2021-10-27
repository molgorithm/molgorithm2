# 백준 1000 A+B

[문제 링크](https://www.acmicpc.net/problem/9489)

## 1. 설계 로직

내 노드의 부모와 형제인 노드의 자식(사촌)의 갯수를 구하는 문제

 

일반적으론 내 노드와 부모의 부모가 같은 노드들의 갯수만 구하면 쉽게 풀이 가능하지만

좀 새롭게 풀어보고자 다른 방법으로 풀었다.

 

연속된 수의 갯수를 세서 ArrayList에 하나하나 담고

ArrayList의 내용만 보고 정답을 유추하는 방식으로 풀이했다.

그림으로 설명하면



![img](https://blog.kakaocdn.net/dn/Pcwcs/btri3VAJKwA/VDPKdsefkKTetpOxzh8WC1/img.png)



위 그림에선 1,3,2,1,3 이 ArrayList에 들어간다.

여기서 보면 처음 1개가 루트이고

그 다음 인덱스인 3은 루트의 자식 갯수가 된다.

그 다음 인덱스 부터 3개인 2,1,3은 자식의 자식이고 서로 사촌관계가 된다.

 

여기서 찾는 값 15는 2,1,3중 1에 들어가 있었으니 1을 제외한 2 + 3이 사촌의 수가 된다.

위 방식으로 각각 사촌으로 묶이는 인덱스 범위를 뽑을 수 있고

그 범위 안에 찾는 값이 있으면 그 갯수를 제외한 사촌의 합을 구하면 정답을 구할 수 있다.

 

1. 연속된 수의 갯수를 세고 배열에 저장한다. 타겟인 수가 있으면 그 인덱스를 미리 저장

2. 큐에 루트부터 저장.

3. 큐에서 하나씩 꺼내서 거기 적혀있는 갯수만큼 다음 인덱스가 사촌지간

4. 인덱스를 들리며 큐에 그 값을 넣기

5. 타겟인 인덱스를 만나면 방금 사촌 범위였던 수의 합에서 타겟인덱스의 값을 뺀 수를 출력

- 시간복잡도

  O(N)

## 2. 코드

```java
package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4_9489사촌 {

	static ArrayList<Integer> arrList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			if (n == 0 && k == 0)
				break;
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			if (k == arr[0]) {
				sb.append(0).append("\n");
				continue;
			}
			arrList = new ArrayList<Integer>();
			int cnt = 0;
			int pre = -1;
			int targetIdx = 0;
			for (int i : arr) {
				if (pre + 1 != i) {
					arrList.add(cnt);
					cnt = 1;
					pre = i;
				} else {
					pre = i;
					cnt++;
				}
				if (i == k)
					targetIdx = arrList.size();
			}
			arrList.add(cnt);
			int idx = 1;
			Queue<Integer> que = new LinkedList<Integer>();
			que.add(arrList.get(1));
			while (true) {
				int size = que.poll();
				int cousin = 0;
				while (size-- != 0) {
					idx += 1;
					if (idx >= arrList.size())
						break;
					cousin += arrList.get(idx);
					que.add(arrList.get(idx));
				}
				if (idx >= targetIdx) {
					sb.append(cousin - arrList.get(targetIdx)).append("\n");
					break;
				}
			}
		}
		System.out.println(sb);
	}
}

```



## 3. 후기

- 처음엔 이 방법으로 쉽게 해결될 줄 알고 재귀로 풀었는데

  bfs형태로 탐색이 이뤄져야 한다는 걸 깨닫고 큐를 이용한 풀이로 바꾸었다.