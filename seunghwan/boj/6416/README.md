# 백준 6416 트리인가?

[문제 링크](https://www.acmicpc.net/problem/6416)

## 1. 설계 로직

주어진 상태가 트리인지 확인하는 문제

 

검증을 위해 3가지를 검사해야함

\1. 자식은 하나의 부모만 가지는지

\2. 루트는 하나만 있는지

\3. 모든 노드들이 연결되어 있는지

 

보통은 루트노드를 찾고 루트노드부터 dfs를 이용해 모두 연결되어 있는지 찾아야 할 것 같는데

루트를 찾는 과정이 번거로울 것 같아 find 함수를 써서 2번과 3번을 한번에 해결하였다.

 

\1. hashmap에 key : 자식 value : 부모로 저장

\2. 저장 중 이미 저장된 자식이 다시 들어오면 트리가 아니다 출력

\3. 모든 노드들에서 find 수행

\4. 부모를 찾아 가는 중 순환이 발생하면 트리가 아니다 출력

\5. 찾은 루트노드들이 모두 같다면 트리다 다른게 있다면 아니다 출력

- 시간복잡도

  O(N) 

## 2. 코드


```java
package Gold;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class G5_6416트리인가 {

	static HashMap<Integer, Integer> parents;
	static Set<Integer> visit = new HashSet<Integer>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		gg: for (int i = 1;; i++) {
			parents = new HashMap<Integer, Integer>();
			boolean isTree = true;
			while (true) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				if (u == 0 && v == 0)
					break;
				if (u == -1 && v == -1)
					break gg;
				if (parents.put(v, u) != null) {
					isTree = false;
				}
			}
			if (isTree) {
				int pre = 0;
				for (int key : parents.keySet()) {
					visit.clear();
					int root = find(key);
					if(root == -1) {
						isTree = false;
						break;
					}
					if(pre == 0)
						pre = root;
					else if(pre != root) {
						isTree = false;
						break;
					}
						
				}
			}

			sb.append("Case ").append(i).append(isTree ? " is a tree.\n" : " is not a tree.\n");
		}
		System.out.println(sb);
	}

	static int find(int i) {
		if (!visit.add(i))
			return -1;
		if (parents.get(i) == null)
			return i;
		int root = find(parents.get(i));
		parents.put(i, root);
		return root;
	}

}
```




## 3. 후기

풀고 보니 dfs쓰는게 더 간단했을 것 같기도 하고?
