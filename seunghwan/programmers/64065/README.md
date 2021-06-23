# 프로그래머스 64065 튜플

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64065)

## 1. 설계 로직

처음 튜플의 개념이 이해가 안가 시간 좀 잡아먹은 문제



![img](https://blog.kakaocdn.net/dn/7ydpf/btq7QwxReTH/NBvxYZfXXJO7j3WihnJk9K/img.png)

결국 중요한건

괄호 안에 값이 한개면 첫번째 요소만 들어있고

괄호 안에 값이 두개면 첫번째 요소와 두번째요소가 들어있고 .....



이 개념이 중요하다.



먼저 입력이 string형태로 주어져서 이것을 배열형태로 쪼개야한다.

처음 split은 양옆의 { } 를 제거하고 ,{ 를 기준으로 쪼개 주었다.



쪼개진 string 배열을 string 길이를 기준으로 정렬 시켜서

요소의 갯수를 기준으로 오름차순 정렬 시켰다.



이제 배열에서 하나씩 꺼내

요소들을 int형태로 변경하고

아직 추가되지 않은 숫자를 찾는다면 answerList에 저장한다.

추가됐는지 검사는 boolean 배열을 통해 사용한 숫자를 true로 변경시켜 검사할 수 있도록 하였다.



ArrayList를 배열에 담아 return하면 끝

- 시간복잡도

  O(n^2)     1+2+3+ .... + n

## 2. 코드

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 튜플 {
	// 1 + 2 + 3 + ... + n;
	
	public int[] solution(String s) {
		List<Integer> answerList = new ArrayList<Integer>();
		boolean nums[] = new boolean[100001];
		s = s.substring(2, s.length() - 1);
		String[] elements = s.split(",\\{");
		Arrays.sort(elements, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		});
		for (String element : elements) {
			element = element.substring(0,element.length()-1);
			for (String e : element.split(",")) {
				int num = Integer.parseInt(e);
				if (!nums[num]) {
					nums[num] = true;
					answerList.add(num);
					break;
				}
			}
		}
		int[] answer = new int[answerList.size()];
		for (int i = 0; i < answerList.size(); i++)
			answer[i] = answerList.get(i);
		return answer;
	}

}


```



## 3. 후기

- replaceAll 함수 써서 집합 간의 거리를 두고 split하여 훨씬 깔끔하게 문자열을 처리한 풀이도 있더라,,

  다음에 문자열 처리할 땐 이 방식으로도 해봐야겠다.

