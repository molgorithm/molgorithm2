# 백준 1068 트리

[문제 링크](https://www.acmicpc.net/problem/1068)

## 1. 설계 로직

- 2가지 방식으로 풀이하였음.

   
  
  \* dfs를 이용해 루트부터 탐색하는 방식
  
  1. 부모에 자식들을 저장하는 연결리스트 생성
  
  2. 루트부터 dfs 탐색 시작
  
  3. 제거된 노드를 만날경우 없는 노드로 치고 탐색 X
  
  4. 탐색하며 자식이 0개인 노드를 만나면 answer++
  
  5. answer 출력
  
   
  
  - 시간 복잡도 : O(N)
  
   
  
  \* union-find의 find를 이용하는 방식
  
  1. 처음 입력을 받으며 부모로 선택되지 않은 노드를 체크 (단, 제거된 노드는 부모를 자기 자신으로 변경)
  
  2. 부모로 한번도 선택되지 않은 노드는 리프노드 후보가 됨.
  
  3. find 함수를 이용해 리프노드 후보들의 최상위 부모를 찾아감.
  
  4. 최상위 부모는 루트노드이거나 제거된 노드 둘 중 하나.
  
  5. 최상위 부모가 루트노드인 경우만 answer++
  
  6. answer 출력
  
   
  
  - 시간 복잡도 : find 시 경로 압축을 통해 O(N)
  
   
  
   
  
  ** 이 문제에서 유의할 점은 제거될 노드의 부모의 자식이 제거될 노드 하나뿐인 경우 그 부모노드는 리프노드가 된다는 것.
  
  첫번째 풀이에선 제거된 노드를 없는 노드로 치는 것으로 위 경우가 해결이 가능하고
  
  두번째 풀이에선 제거된 노드의 부모를 자기자신으로 바꾸는 것으로 해결 가능.

## 2. 코드

<details>
<summary>dfs 풀이</summary>
<div markdown="1">

```java
package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class G5_1068트리 {

	static ArrayList<Integer>[] nodes;
	static int target;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int root = 0;
		nodes = new ArrayList[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			nodes[i] = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (num == -1) {
				root = i;
				continue;
			}
			nodes[num].add(i);
		}
		target = Integer.parseInt(br.readLine());
		if (target != root)
			dfs(root);
		System.out.println(answer);

	}

	static void dfs(int num) {
		int cnt = 0;
		for (int i = 0; i < nodes[num].size(); i++) {
			if (nodes[num].get(i) == target)
				continue;
			cnt++;
			dfs(nodes[num].get(i));
		}
		if (cnt == 0)
			answer++;

	}

}

```

<details>
<summary>find 풀이</summary>
<div markdown="1">

```java
package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_1068트리_find {

	static int target;
	static int parents[];
	static boolean haveChild[];
	public static void main(String[] args) throws Exception {
		int answer = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		parents = new int [N];
		haveChild = new boolean [N];
		st = new StringTokenizer(br.readLine());
		target = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			if(i==target) {
				st.nextToken();
				parents[i] = i;
				continue;
			}
			parents[i] = Integer.parseInt(st.nextToken());
			if(parents[i] != -1)
				haveChild[parents[i]] = true;
		}
		for(int i = 0 ; i < N ; i++) {
			if(haveChild[i])
				continue;
			if(find(i) != target)
				answer++;
		}
		System.out.println(answer);
	}
	static int find(int num){
		if(parents[num] == -1 || parents[num] == target)
			return parents[num]; 
		return parents[num] = find(parents[num]);
	}
}

```

</div>
</details>



## 3. 후기

- 최근 이진트리 문제를 많이 풀고

  예제 그림도 이진트리라서 처음에 이진트리로 생각하고 풀었다가 틀림..

  일반트리에요