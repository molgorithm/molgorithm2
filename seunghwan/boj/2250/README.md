# 백준 2250 트리의 높이와 너비

[문제 링크](https://www.acmicpc.net/problem/2250)

## 1. 설계 로직

이 문제는 설명은 복잡하지만 이해하고 나면 굉장히 간단한 문제이다.



![img](https://blog.kakaocdn.net/dn/nlAfX/btrjCTofC0F/KLYN7g4Hn5bcEyZAEXYMWK/img.jpg)



특징을 생각해보면

1. 열 하나에 하나의 노드만 들어간다.

2. 내 노드의 인덱스는 나의 왼쪽 노드가 다 완성된 후 다음 인덱스가 된다.

특징을 보면 왼쪽 -> 나 -> 오른쪽인 중위순회의 형태를 띄게 된다.

즉, 중위순회로 트리를 탐색 했을 때 나오는 순서대로 열 인덱스를 매긴 것이 된다.

 

행 인덱스는 깊이가 되니 탐색을 하며 각 깊이의 열 인덱스들을 저장해놓고

마지막에 각 깊이의 최소 인덱스와 최대 인덱스의 차이를 구해 가장 너비가 긴 길이와 깊이를 찾으면 된다.

- 시간복잡도

  O(N)

## 2. 코드

```java
package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class G2_2250트리의높이와너비 {
	static class node {
		int left;
		int right;
	}

	static node nodeArr[];
	static ArrayList<Integer> [] depthIdx;
	static int idx = 1;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int root = 0;
		nodeArr = new node[N + 1];
		depthIdx = new ArrayList[N+1];
		for (int i = 1; i < N + 1; i++) {
			nodeArr[i] = new node();
			depthIdx[i] = new ArrayList<Integer>();
		}
		boolean noRoot[] = new boolean[N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			if(left != -1) noRoot[left] = true;
			if(right != -1) noRoot[right] = true; 
			nodeArr[num].left = left;
			nodeArr[num].right = right;
		}

		for (int i = 1; i < N + 1; i++)
			if (!noRoot[i]) {
				root = i;
				break;
			}
		findIdx(root, 1);
		int max = -1;
		int maxDepth = 0;
				
		for(int i = 1 ; i < N+1; i ++) {
			if(depthIdx[i].isEmpty())
				continue;
			int size =depthIdx[i].get(depthIdx[i].size()-1)-depthIdx[i].get(0)+1;
			if(max < size) {
				max = size;
				maxDepth = i;
			}
		}
		System.out.println(maxDepth +" "+max);
	}
	static void findIdx(int num,int depth) {
		node now = nodeArr[num];
		if(now.left != -1) findIdx(now.left, depth+1);
		depthIdx[depth].add(idx++);
		if(now.right != -1) findIdx(now.right, depth+1);
	}
}
```



## 3. 후기

- 그림만 보면 조금 풀기 싫게 생겼는데 글 이해만 하면 쉬운문제

  국어지문 느낌..

