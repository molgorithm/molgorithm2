# 프로그래머스 17687 n진수 게임

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/17687)



## 1. 설계 로직

처음엔 수리적으로 접근해보려 했는데

m 과 t 가 작으니 모든 숫자를 구하고 각 순서에 해당하는 수를 꺼내오는 식으로 풀이했다.

 

각 진법에 맞춰 1부터 쭉 변환하여 list에 저장한다.

list의 크기가 m*t, 즉 t순번이 모두 돌때까지만 저장하고

 

이제 튜브 순서에 맞는 숫자들만 꺼내서 저장하여 출력하면 된다.

 

- 시간복잡도

  O(nlogn)

## 2. 코드

```java
import java.util.ArrayList;
import java.util.List;

public class P17687n진수게임 {

	public String solution(int n, int t, int m, int p) {
		String answer = "";
		List<Character> numList = new ArrayList<Character>();
		int num = 1;
		numList.add('0');
		while (numList.size() < t * m) {
			int target = num;
			String s = "";
			while (target != 0) {
				int mod = target % n;
				mod = mod >= 10 ? 'A' + mod - 10 : '0' + mod;
				s = (char) mod + s;
				target = target / n;
			}
			for (char c : s.toCharArray())
				numList.add(c);
			num++;
		}
		for (int i = p-1; answer.length() < t; i += m)
			answer += numList.get(i);

		return answer;
	}
}

```



## 3. 후기

- 진법 변환 후 0~F 까지 수를 배열에 저장해서

  매핑하는 방식의 풀이가 있었는데
  
  이게 가장 깔끔한 것 같다.
