# 프로그래머스 42626 더 맵게

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42626)





## 1. 설계 로직

코딩 테스트 연습 heap 1번문제

 

Priority Queue 를 쓰면 쉽게 해결 가능하다.

pq를 이용해 오름차순에서 2개를 꺼내고



![img](https://blog.kakaocdn.net/dn/bEkELl/btrdEi9irqb/qzXoWdaQfuRMdVLEwf0xH0/img.png)



이 조건에 맞춰 다시 넣어주고

만약 pq에서 처음으로 꺼낸 수가

k를 넘어가면 끝내면 된다.



- 시간복잡도

  O(N) 

## 2. 코드

```java
import java.util.PriorityQueue;

public class P42626더맵게 {
	
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for(int i : scoville)
        	pq.add(i);
        boolean flag = true;
        while(!pq.isEmpty()) {
            
        	int f = pq.poll();
        	if(f >=K)
        		break;
        	if(pq.isEmpty()) {
        		flag = false;
        		break;
        	}
        	int s = pq.poll();
        	pq.add(f+s*2);
        	answer++;
        }
        return flag ? answer : -1;
    }

}
```



## 3. 후기

- pq 사용에 가장 기본적인 문제였다.
