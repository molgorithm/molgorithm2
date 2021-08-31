# 프로그래머스 42628 이중우선순위큐

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42628)





## 1. 설계 로직

뭔가 Deque와 정렬을 쓰면 쉽게 해결 될 거 같은데

제목이 이중우선순위큐니깐 우선순위큐를 써서 해결해 보려고

우선순위큐 2개를 사용했다.

 

\1. 숫자와 이 숫자가 유효한지 확인하는 isok를 가진 class 정의

\2. 오름차순 minPq와 내림차순 maxPq 준비

\3. operations의 연산자에 따라

3-1. 입력이면 minPq와 maxPq에 모두 넣기

3-2. d 1 이면 maxPq에서 d -1 이면 minPq에서 하나 빼기

3-2-1. Pq에서 뺄 때 isok가 true라면 isok를 false로 바꾸기

3-2-2. isok가 false라면 true를 뽑을 때까지 뽑기

\4. 모든 연산을 마치고 d 1과 d -1 작업을 한번 더 해서 max값과 min값 꺼내기

\5. max min 값 배열에 담아 return



- 시간복잡도

  O(NlogN) 

## 2. 코드

```java
import java.util.Comparator;
import java.util.PriorityQueue;

public class P42628이중우선순위큐 {
	class node{
		int num;
		boolean isok;
		public node(int num, boolean isok) {
			this.num = num;
			this.isok = isok;
		}
	}
    public int[] solution(String[] operations) {
        
        PriorityQueue<node> minPq = new PriorityQueue<node>(new Comparator<node>() {
        	@Override
        	public int compare(node o1, node o2) {
        		return o1.num-o2.num;
        	}
		});
        PriorityQueue<node> maxPq = new PriorityQueue<node>(new Comparator<node>() {
        	@Override
        	public int compare(node o1, node o2) {
        		return o2.num-o1.num;
        	}
		});
        for(String s : operations) {
        	String [] split = s.split(" ");
        	if(split[0].charAt(0) == 'I') {
        		node nNode = new node(Integer.parseInt(split[1]), true);
        		minPq.add(nNode);
        		maxPq.add(nNode);
        	}else {
        		if(split[1].charAt(0) == '1') 
        			poll(maxPq);
        		else 
        			poll(minPq);
        	}
        }
        int max = poll(maxPq);
        int min = poll(minPq);
        min = min == 0 ? min = max : min;
        int[] answer = {max,min};
        return answer;
    }
    int poll(PriorityQueue<node> pq) {
		while(!pq.isEmpty()) {
			node now = pq.poll();
			if(now.isok) {
				now.isok = false;
				return now.num;
			}
		}
		return 0;
    }
}

```



## 3. 후기

- 나중에 시간 되면 deque로도 풀어봐야겠다.
