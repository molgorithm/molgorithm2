# 프로그래머스 64062 징검다리건너기

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64062)

## 1. 설계 로직

삽질

처음엔 슬라이딩 윈도우로

K크기 만큼 보면서 K크기 안의 최댓값 중 최소값을 구하는 방법으로 풀이했는데

K * (N-K) 만큼 연산을 하므로

N = 20만 K = 10만 인 경우

100억이 나오므로 시간초과가 떳다.

 

그런데 다른 사람의 풀이에서 보니

매번 최대값 검색 후 다음 슬라이딩윈도우 시작 위치를 최대값 위치로 옮겨줘서 풀린 코드가 있었는데

내림차순으로 정렬된 N=20만 K=10만이면 똑같이 시간초과가 나야하는데 풀린걸 보니

테케가 조금 부실했나보다

\-----------------------------------------------------------------------------

이 문제는 처음 겪어본 문제라 조금 당황한 문제이다.

 

Stones의 길이 20만에 집중할 것이 아닌

Stones의 크기 범위 2억을 줄여 풀이해야 하는 문제

 

즉 파라메틱 서치를 이용하는데

조건에 만족하는 최적의 값을 구하는 이분탐색? 정도로 보면 될 것 같다.

 

조건은 20만개안에서 K 크기 만큼의 빈칸이 생겨났는지 이고

 

값은 1 ~ 2억에서 반씩 줄여가면서

1억에서 가능한지 불가능한지

 

가능하다면 1억 이전의 값들도 모두 가능하므로

left를 1억으로 옮기고 1억+1 ~ 2억

불가능하다면 1억 이후의 값들은 모두 불가능하므로

right를 1억으로 옮겨 1 ~ 1억 -1

 

이런식으로 불가능한 최소값을 이분탐색으로 찾아간다.

 

실제로는 1~2억을 다 할 필요없이

배열을 한번 돌면서 최소값 ~ 최대값 형태로 하면 된다.



![img](https://blog.kakaocdn.net/dn/GU9jR/btrbJInA1se/koxhE2j6QGQL8x8qDzqO5k/img.jpg)



이제 불가능 했던 숫자들 중 최소값을 구하면 정답을 구할 수 있다.



- 시간복잡도

  O(N* log K) N = stones 배열 길이 K = stones 원소 중 가장 큰 값

## 2. 코드

```java

public class P64062징검다리건너기 {
	
	//파라메틱 서치
	public int solution(int[] stones, int k) {
		int min = 987654321;
		int max = 0;
		
		for(int i = 0 ; i < stones.length ; i++) {
			min = Math.min(min, stones[i]);
			max = Math.max(max, stones[i]);
		}
		int answer = 987654321;
		while(min <= max) {
			int mid = (min+max)/2;
			if(check(stones,mid,k)) {
				min = mid+1;
			}else {
				max = mid-1;
				answer = Math.min(mid, answer);
			}
				
		}
		
		return answer;
	}
	public boolean check(int stones[],int mid,int k) {
		int cnt = 0;
		for(int i :stones) {
			if(i - mid <= 0)
				cnt++;
			else
				cnt = 0;
			if(cnt == k)
				return false;
		}
		return true;
	}
}


```



## 3. 후기

- 파라메틱 서치에 대해 이 문제를 풀면서 알게 되었는데

  굉장히 좋은 문제 인 것 같다.