# 프로그래머스 17678 셔틀버스

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/17678)



## 1. 설계 로직

해결방법을 떠올리는 것보다 문제 이해하는데 시간이 더 걸린 문제.

 

그림이 있었다면 더 쉽게 이해했을것 같은데

만약 그림이 있었다면 그것만으로도 너무 큰 힌트가 될 수 있을 것 같긴하다.

 

버스는 9시부터 첫차가 출발하고 t분 간격으로 n번 한번에 최대 m명을 데리고 갈 수 있다.

 

여기서 콘이 버스를 타는 방법 중 시간이 가장 뒤인 것을 찾으면 된다.

 

여기서 콘은 같은 시간대에 온 사람들 중 가장 마지막으로 타게 된다는 조건이 있어

첫차를 제외하고는 못타는 경우의 수가 있을 수 있다.

 

그래서 첫 버스부터 콘이 이 버스를 탄다면 탈 수 있는 시간을 구한 뒤 다음 버스로 넘어가는 식으로 풀이를 진행했다.

 

먼저 크루의 도착시간이 뒤죽박죽이므로 정렬을 시켜주고

도착시간을 계산하기 편하게 분을 기준으로 수로 변경해준다.

 

그리고 크루들은 선입선출로 버스를 타고 갈것이니 큐에 저장해주었다.

 

********

이제 각 버스에 대해서

버스는 우선 한자리를 제외하고 크루들을 모두 태우고

 

남은 자리에 대해

1. 한자리가 아닌 두자리 이상이 남거나

2. 다음 순번 크루가 어차피 탈 수 없다면 콘은 버스가 도착하는 시간에만 도착해도 탈 수 있다.

3. 다음 순번 크루가 탈 수 있었다면 그 크루보다 순서가 빨라야하므로 1분 일찍 도착하면 탈 수 있다.

 

이 3가지 경우로 나누어

콘이 버스를 탈 수 있는 경우 시간을 저장해두고

마지막에 HH : MM 형태로 바꾸어 return해주면 된다.

 

- 시간복잡도

  O(n+m)

## 2. 코드

```java
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P17678셔틀버스 {
	// 각각 버스에 탈 수 있는 가장 늦은 시간을 구한다.

	public String solution(int n, int t, int m, String[] timetable) {
		Arrays.sort(timetable);
		Queue<Integer> crew = new LinkedList<Integer>();
		for (String s : timetable) {
			int H = Integer.parseInt(s.substring(0, 2));
			int M = Integer.parseInt(s.substring(3, 5));
			crew.add(H * 60 + M);
		}
		int bustime = 9 * 60;
		int answertime = 0;
		for (int i = 0; i < n; i++) { // i번째 버스
			int men = 0; // 현재까지 탄 숫자
			while (men < m - 1 && crew.size() != 0 && crew.peek() <= bustime) { // 한자리 빼고 태울수 있을때까지 태우기
				crew.poll();
				men++;
			}
			if (men == m - 1) { // 꽉 채워 앉았으면
				if (crew.size() != 0 && crew.peek() - 1 <= bustime) { // 다음에 탈 얘가 탈수 있었으면
					answertime = crew.peek() - 1; // 그 시간보다 1분 빠르게
					crew.poll();
				} else
					answertime = bustime; // 아니면 어차피 한자리 남으니 버스시간에 맞춰서

			} else // 꽉 안찼으면 버스시간에 타면 됨
				answertime = bustime;

			bustime += t;
		}
		int H = answertime / 60;
		int M = answertime % 60;
		String answer = "";
		answer += H / 10 == 0 ? "0" + H : H;
		answer += ":";
		answer += M / 10 == 0 ? "0" + M : M;
		return answer;
	}

}
```



## 3. 후기

- 처음엔 문제 이해가 안되서 고민을 많이 했는데

  손으로 그려가며 따라가다 보니 문제 이해와 함께 바로 해결책까지 찾을 수 있었다.
