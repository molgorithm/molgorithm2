# 백준 7662 이중우선순위큐

[문제 링크](https://www.acmicpc.net/problem/7662)

## 1. 설계 로직

프로그래머스에도 완전히 같은 문제가 있어 한번 풀어봤던 문제이다.

 

자료구조의 선택에 따라 3가지 방식으로 풀어보았다.

\----------------------------------------------------------

1. Deque 활용 (프로그래머스 통과, 백준 시간초과)

삭제 연산이 앞뒤로 일어나기 때문에

앞뒤로 입출력이 가능한 deque를 활용한 풀이이다.

 

출력시에는 앞뒤의 node만 꺼내면 되기 때문에 한번의 연산으로 가능하다.

다만 입력시 정렬 상태를 유지하기 위해 입력 때 마다 매번 sort를 수행하여 O(NlogN)을 수행하게 된다.

 

+) 지금 생각해보면 deque가 아닌 ArrayList를 이용한다면 데이터가 이미 정렬된 상태이므로 이진탐색을 통해 삽입 위치를 찾아 탐색 logN + Array 삽입 N 으로 조금 더 나았지 않을까 싶다.

\----------------------------------------------------------

2. Priority queue 2개 활용 (프로그래머스 ,백준 통과)

max heap과 min heap 2개의 자료구조를 이용한 풀이

 

인풋으로 들어오는 값을 max heap 과 min heap에 모두 넣어서

출력시 최댓값은 max heap, 최솟값은 min heap에서 삭제연산을 수행한다.

 

다만 하나의 데이터가 2개의 자료구조에 들어가기 때문에

max heap에선 삭제 되었지만 min heap에서는 존재하는 상태가 생기게 된다.

 

나는 이 부분을 해결하기 위해

삭제 되었는지 유무를 나타내는 boolean값 하나를 인풋으로 들어온 데이터와 함께 클래스로 감싸서 각 heap에 참조변수 형태로 저장 시켰다.

 

이렇게 되면 max heap에서 삭제된 데이터의 boolean값을 false로 바꿔주면

min heap에 존재하는 해당 데이터가 나오더라도 이미 삭제된 데이터임을 인지할 수 있다.

 

다만 삭제된 데이터를 heap안에 계속 들고 있다보니 N의 크기가 커져 약간의 손실이 발생하게 된다.

입출력 : logN + logN 

\----------------------------------------------------------

3. Binary Search Tree 활용

 사실 이 문제에서 가장 최적의 풀이는 BST를 활용한 풀이 인 것 같다.

 



![img](https://blog.kakaocdn.net/dn/Bt8zR/btrgpKoFIA1/9dANNSly5VYzCrK5PRHhyk/img.png)



BST는 현재 노드의 값보다 작은 값은 left노드 큰 값은 right노드로 연결하여

입출력 모두 평균 logN에 가능 한 자료 구조이다.

(☆중위 순회 시 정렬 된 상태로 데이터를 뽑을 수 있어 항상 같은 순서로 데이터가 나옴)

 

BST로 구현시 가장 왼쪽 노드의 값이 최소값

가장 오른쪽 노드의 값이 최댓값이 된다.

 

BST 구현시 가장 복잡한 부분이 노드의 중간에 삭제연산이 일어날 때 다시 재구성하는 과정인데

이 문제에선 가장 왼쪽노드와 가장 오른쪽 노드에서만 삭제연산이 일어나 구현 자체도 그리 복잡하지 않았다.

 

하나의 자료구조 안에서 풀이가 가능해 pq를 2개 쓰는 것에 비해 효율적인 풀이가 가능하다.

 

다만 BST는 정렬된 순서로 데이터가 들어오면 편향트리가 이루어져 최악 O(N)이 될 수 있는 문제가 있다.

이를 해결하기 위해 AVL 트리, B-트리 등이 있는데 빠르게 풀이해야하는 알고리즘 풀이 특성 상 구현복잡도가 높아서 따로 시도해보진 않았다.

- 시간복잡도

  O(NlogN) 

## 2. 코드

1\. Deque

<details>
<summary>접기/펼치기</summary>
<div markdown="1">

```java

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			int k = Integer.parseInt(br.readLine());
			LinkedList<Integer> deque = new LinkedList<Integer>();
			while (k-- != 0) {
				st = new StringTokenizer(br.readLine());
				String oper = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				
				if (oper.charAt(0) == 'I') {
					deque.add(num);
					Collections.sort(deque);
				} else {
					if (deque.isEmpty())
						continue;
					if (num == 1)
						deque.pollLast();
					else
						deque.pollFirst();
				}
			}
			if (deque.isEmpty())
				System.out.println("EMPTY");
			else {
				System.out.println(deque.peekLast()+" "+deque.peekFirst());
			}
		}
	}
}
```

</div>
</details>


2\. Priority queue

<details>
<summary>접기/펼치기</summary>
<div markdown="1">

```java

package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G5_7662이중우선순위큐 {
	static class node{
		int num;
		boolean isok;
		public node(int num, boolean isok) {
			this.num = num;
			this.isok = isok;
		}
	}
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			int k = Integer.parseInt(br.readLine());
			
	        PriorityQueue<node> minPq = new PriorityQueue<node>(new Comparator<node>() {
	        	@Override
	        	public int compare(node o1, node o2) {
	        		return Integer.compare(o1.num,o2.num);
	        	}
			});
	        PriorityQueue<node> maxPq = new PriorityQueue<node>(new Comparator<node>() {
	        	@Override
	        	public int compare(node o1, node o2) {
	        		return Integer.compare(o2.num, o1.num);
	        	}
			});
			
			while (k-- != 0) {
				st = new StringTokenizer(br.readLine());
				String oper = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
	        	if(oper.charAt(0) == 'I') {
	        		node nNode = new node(num, true);
	        		minPq.add(nNode);
	        		maxPq.add(nNode);
	        	}else {
	        		if(num == 1) 
	        			poll(maxPq);
	        		else 
	        			poll(minPq);
	        	}
			}
			Integer max = poll(maxPq);
			Integer min = poll(minPq);
	        if(max == null )
	        	System.out.println("EMPTY");
	        else {
	        	min = min == null ? min = max : min;
	        	System.out.println(max +" "+min);
	        }
		}
	}
	
    static Integer poll(PriorityQueue<node> pq) {
		while(!pq.isEmpty()) {
			node now = pq.poll();
			if(now.isok) {
				now.isok = false;
				return now.num;
			}
		}
		return null;
    }
}

```

</div>
</details>

3\. Binary Search Tree

<details>
<summary>접기/펼치기</summary>
<div markdown="1">

```java

package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_7662이중우선순위큐_이진탐색트리 {

	static class BST {
		int num;
		int cnt;
		BST left;
		BST right;

		public void put(int number) {
			if (this.num == number)
				this.cnt++;
			else if (this.num > number) {
				if (this.left == null)
					this.left = new BST(number);
				else
					left.put(number);
			} else if (this.num < number) {
				if (this.right == null)
					this.right = new BST(number);
				else
					right.put(number);
			}
		}

		public int popmin() {
			if (this.left == null) {
				this.cnt--;
				return num;
			} else {
				int min = this.left.popmin();
				if (this.left.cnt == 0)
					this.left = this.left.right;
				return min;
			}
		}

		public int popmax() {
			if (this.right == null) {
				this.cnt--;
				return num;
			} else {
				int max = this.right.popmax();
				if (this.right.cnt == 0)
					this.right = this.right.left;
				return max;
			}
		}

		public BST(int num) {
			this.num = num;
			this.cnt = 1;
		}
	}
	BST minnode;
	BST maxnode;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			int k = Integer.parseInt(br.readLine());
			BST bst = null;
			while (k-- != 0) {
				st = new StringTokenizer(br.readLine());
				String oper = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				if (oper.charAt(0) == 'I') {
					if (bst == null)
						bst = new BST(num);
					else
						bst.put(num);
				} else {
					if (bst == null)
						continue;
					if (num == 1) {
						bst.popmax();
					}
					else
						bst.popmin();
					if (bst.cnt == 0) 
						bst = num== 1 ? bst.left : bst.right;
				}
			}
			if (bst == null)
				answer.append("EMPTY").append("\n");
			else {
				int max = bst.popmax();
				int min = bst.popmin();
				answer.append(max).append(" ").append(min).append("\n");
			}
		}
		
		System.out.println(answer);
	}
}

```

</div>
</details>


## 3. 후기

![img](https://blog.kakaocdn.net/dn/cM2C7W/btrgwJuyhDS/kIo4UlPCIO0AuUPb4696MK/img.png)



BST로 자바 효율성 2등!

