# 프로그래머스 17685 자동완성

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/17685)



## 1. 설계 로직

![img](https://blog.kakaocdn.net/dn/cgOfGb/btq8YXnoU3d/Ni4w9olQBzS37D3NsYkvv1/img.png)



트리와 bfs를 이용해 풀이하였다.

 

단 트리로 만들 시 go, gone 같은경우



![img](https://blog.kakaocdn.net/dn/Irwp6/btq8TsoxZv2/nD5h0tSCYk7WFby6pjZLLK/img.png)https://maeng2world.tistory.com/214에서 훔쳐왔어여



go가 word인지 아닌지 구별할 방법이 없으므로

모든 단어끝에 마지막을 의미하는 E를 붙여주었다.

 



![img](https://blog.kakaocdn.net/dn/oRg8y/btq8VGsY0tM/sFw0QURw9PW5TK4nXLDbv1/img.png)



이렇게 되면

goneE같은경우 n까지만 누르면 E는 자동완성의 한부분이니 신경쓰지 않아도 되고

 

goE에서만 실제론 go만치면 되지만 E까지 치게 되었으므로 이런 때에만 타이핑횟수 계산후 -1 해주면 된다.

 

 

타이핑 횟수 계산은

bfs를 통해 depth와 타이핑 횟수를 저장하며 진행 되고

현재위치에서 가지가 한개라면 depth만 올라가고 타이핑 횟수는 그대로인 상태로 이동

가지가 두개이상이면 타이핑 횟수 = depth로 변경하여 이동하며

E를 만나게 되면 현재까지 타이핑횟수를 answer에 저장하면 된다.

 

주의

dfs로 풀이할 시 깊이가 최대 100만까지 가능하니 스택이 터지게 되므로

bfs로 풀이하여야 한다.

 

실패한 dfs코드도 밑에 첨부해 두겠다.

 

- 시간복잡도

  O(L)

## 2. 코드

```java
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class P17685자동완성bfs {
	static class node{
		HashMap<Character, HashMap> hash;
		int nowDepth;
		int clickDepth;
		public node(HashMap<Character, HashMap> hash, int nowDepth, int clickDepth) {
			super();
			this.hash = hash;
			this.nowDepth = nowDepth;
			this.clickDepth = clickDepth;
		}
	}
	public int solution(String[] words) {
		
		HashMap<Character, HashMap> hash = new HashMap();
		int answer = 0;

		for (String s : words) {
			s += "E";
			HashMap<Character, HashMap>  targetHash = hash;
			for (char c : s.toCharArray()) {
				if (targetHash.get(c) == null)
					targetHash.put(c, new HashMap());
				targetHash =  targetHash.get(c);
			}
		}
		
		Queue<node> que = new LinkedList<node>();
		que.add(new node(hash, 1, 1));
		while(!que.isEmpty()) {
			node now = que.poll();
			for(char c : now.hash.keySet()) {
				int size = now.hash.get(c).size();
				if(size == 1) 
					que.add(new node(now.hash.get(c),now.nowDepth+1,now.clickDepth));
				else if(size ==0)  
					answer += now.nowDepth == now.clickDepth ? now.clickDepth-1: now.clickDepth;
				else
					que.add(new node(now.hash.get(c),now.nowDepth+1,now.nowDepth+1));
			}
		}
		return answer;
	}
}
```

## 

```java
import java.util.HashMap;

public class P17685자동완성dfs {
	int answer = 0;

	public int solution(String[] words) {
		
		HashMap<Character, HashMap> hash = new HashMap();

		for (String s : words) {
			s += "E";
			HashMap<Character, HashMap>  targetHash = hash;
			for (char c : s.toCharArray()) {
				if (targetHash.get(c) == null)
					targetHash.put(c, new HashMap());
				targetHash =  targetHash.get(c);
			}
		}
		dfs(hash,1);
		return answer;
	}
	
	Boolean dfs(HashMap<Character, HashMap> hash,int depth) {
		int size = hash.size();
		if(size == 0) 
			return null;
		
		for(char c : hash.keySet()) {
			Boolean result =dfs(hash.get(c),depth+1);
			if(result == null) {
				if(size != 1)
					answer += depth-1;
				else
					return true;
			}
			else if(result&&size==1)
				return true;
			else if(result && size != 1)
				answer += depth;
		}
		return false;
	}
}
```



## 3. 후기

- 자바 재귀의 최대깊이가 얼마까지 가능한지 궁금해 검색해보니

  정확히 나오는 곳은 없고 자바스크립트의 경우 10만정도인것으로 나왔다.

   

  그래서 직접 실험해보니 11400번 정도 까지는 가능했고

  매번 실행할 때 마다 이 횟수는 차이가 있었다.

   

  대충 깊이는 10000까지 가능이라 생각해야 할 것 같다.
