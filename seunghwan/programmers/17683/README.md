# 프로그래머스 17683 방금그곡

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/17683)



## 1. 설계 로직

이 문제 역시 문자열 관리를 잘해주면 된다.

 

먼저 사용되는 각 문자열중에 C# 과 같이 #이 붙은 친구들은 길이는 2인데 음은 하나이므로

계산시에 불편함이 있다.

그래서 C#과 같은 음들은 소문자로 변경하여 풀이하였다.

 

사전작업을 마친 뒤 풀이를 진행해 보면

1. 플레이타임 구하기

2. 플레이타임에 맞춰 총 실행된 음들 구하기

3. 네오가 구한 멜로디가 포함되었는지 contains함수로 비교

4. 기존에 찾은 멜로디와 플레이타임 비교하여 정답 저장

5. 끝~

 

위 순서대로 진행하면 된다.

 

- 시간복잡도

  O(n+m) contains 함수의 문자열 비교 알고리즘.

## 2. 코드

```java
public class P17683방금그곡 {
	
	public String solution(String m, String[] musicinfos) {
		m = changeShap(m);
		int time = 0;
 		String answer = "(None)";
		for (String music : musicinfos) {
			String[] musicinfo = music.split(",");

			int H1 = Integer.parseInt(musicinfo[0].substring(0, 2));
			int M1 = Integer.parseInt(musicinfo[0].substring(3, 5));
			int H2 = Integer.parseInt(musicinfo[1].substring(0, 2));
			int M2 = Integer.parseInt(musicinfo[1].substring(3, 5));
			int playtime = (H2 - H1) * 60 + M2 - M1;
			String sound = changeShap(musicinfo[3]);
			String fullSound = "";
			for (int i = 0; i < playtime; i++)
				fullSound += sound.charAt(i % sound.length());
			if (fullSound.contains(m)) {
				if (time >= playtime)
					continue;
				answer = musicinfo[2];
				time = playtime;

			}

		}
		return answer;

	}

	public String changeShap(String s) {
		StringBuilder sb = new StringBuilder(s);
		for (int i = 0; i < sb.length(); i++)
			if (sb.charAt(i) == '#')
				sb.replace(i - 1, i + 1, "" + (char)(sb.charAt(i-1) + 32));

		return sb.toString();
	}

}

```



## 3. 후기

- 처음엔 문자열 비교를 어떻게 해야하나 KMP를 직접구현해야하나.. 하고 막막했는데

  생각해보니 잘 짜놓은 contains함수가 있는데

  굳이 저 알고리즘을 구현하고 있는 것이 시간낭비구나라는 것을 깨달았다.
