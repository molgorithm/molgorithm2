# 프로그래머스 64063 호텔방배정

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64063)

## 1. 설계 로직

유니온 파인드를 활용하면 쉽게 해결 가능한 문제

 

기존의 유니온 파인드와는 다르게

미리 모든 값에 대해 배열을 준비하지 않고

HashMap을 이용해 들어오는 값에 대해서만 부모를 준비한다.

 

find 시 경로 압축을 통해 시간을 최소화 시킬 수 있다.

 



![img](https://blog.kakaocdn.net/dn/cvMBwF/btrbPa6bl8l/ZTNuzKLeIkwkeMhdDsEuL1/img.jpg)find 경로 압축

- 시간복잡도

  O(N)

## 2. 코드

```java
import java.util.HashMap;

public class P64063호텔방배정 {
	HashMap<Long, Long> parents = new HashMap<Long, Long>();
	public long[] solution(long k, long[] room_number) {
		long[] answer = new long[room_number.length];
		for (int i = 0; i < room_number.length; i++)
			answer[i] = findRoom(room_number[i]);
		return answer;
	}
	public long findRoom(long num) {
		if (parents.get(num) == null) {
			parents.put(num, num + 1);
			return num;
		}
		long parent = findRoom(parents.get(num));
		parents.put(num, parent);
		return parent;
	}
}

```



## 3. 후기

- 이게 풀릴지 안풀릴지 반신반의 하면서 풀었는데

  바로 풀려서 기분이 좋았다.

