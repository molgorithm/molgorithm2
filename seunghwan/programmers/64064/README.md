# 프로그래머스 64064 불량사용자

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64064)

## 1. 설계 로직

순열 : 순서가 있는 집합

조합 : 순서에 상관 없는 집합



문제에서 요구하는 정답은 조합의 갯수이다.



하지만 user_id에서 바로 조합을 구하여 문제를 해결하려하면



ex) 

user_id : ["frodo","fradi","crodo"]

banned_id : ["*****","fr*d*"]



이 경우

나오는 조합으로 나오는 경우의 수는

frodo fradi  o

frodo crodo x

fradi crodo  x



이렇게 3가지인데 실제로는

frodo fradi  o

crodo frodo o

crodo fradi  o



즉 banned_id에 맞추어 보기 위해 순서를 따져서 확인을 해 볼 필요가 있는 것이다.



그래서 user_id의 순열을 구하여

banned_id와 맞는지 비교를 하고

그 후에 중복제거를 하여 조합을 구하였다.



여기서 중복제거는 HashSet을 이용하여 진행하였다.



- 시간복잡도

  O(N!)

## 2. 코드

```java
import java.util.HashSet;

public class P64064불량사용자 {
	//순열뽑기
	//banned_id랑 비교
	//통과하면 set에 넣기
	//set 갯수 출력
	
	static boolean visit[];
	static String choice[];
	static String g_user_id[];
	static String g_banned_id[];
	static HashSet<HashSet<String>> cases;
	public int solution(String[] user_id, String[] banned_id) {
		visit = new boolean[user_id.length];
		choice = new String[banned_id.length];
		g_user_id = user_id;
		g_banned_id = banned_id;
		cases = new HashSet<HashSet<String>>();
		perm(0);
		return cases.size();
	}
	
	static void perm(int n) {
		if(n == g_banned_id.length) {
			if(check()) {
				HashSet<String> casee = new HashSet<String>();
				for(String s : choice)
					casee.add(s);
				cases.add(casee);
			}
			return;
		}
		for(int i = 0 ; i < g_user_id.length ;i++) {
			if(visit[i])
				continue;
			choice[n] = g_user_id[i];
			visit[i] = true;
			perm(n+1);
			visit[i] = false;
		}
	}
	static boolean check() {
		for(int i = 0 ; i < g_banned_id.length ;i++) {
			if(choice[i].length() != g_banned_id[i].length())
				return false;
			for(int j = 0 ; j < choice[i].length() ; j++) {
				if(g_banned_id[i].charAt(j) == '*')
					continue;
				if(choice[i].charAt(j) != g_banned_id[i].charAt(j))
					return false;
			}
		}
		return true;
	}
}



```



## 3. 후기

- 처음엔 경우의 수를 가지고 계산을 하는 것인가 싶어 삽질을 좀 하다가

  n이 8인 것을 보고 완탐이구나를 깨닫고 순열로 풀이를 하였다.