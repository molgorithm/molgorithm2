# 프로그래머스 60061 기둥과 보 설치

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/60061)

## 1. 설계 로직

![img](https://blog.kakaocdn.net/dn/l9t0z/btq9AiZfqMn/cL7dZvF3aW3KA3nsNpOw50/img.png)



이 문제에서 가장 중요한 부분

 

저 말에 맞춰 설치와 삭제가 가능한지만 판단하면 된다.

 

먼저 설치시에는

기둥설치는 밑에 바닥,보,기둥 중 하나가 있는지 확인

보 설치는 양옆에 보가 있거나 설치위치 또는 오른쪽 위치 아래에 기둥이 있는지 확인

 

이렇게만 확인을 하면된다.

 

삭제시에는 좀더 간단하게

현재 기물을 삭제를 하면 영향을 받는 위치에 무언가 설치되어있다면

우선 현재 기물이 삭제된 뒤에 그 영향을 받는 위치에 다시 설치가 가능한지 검사를 하여

현재 기물 없이도 그 기물이 멀쩡 할 수 있다면 삭제되어도 되는 것이다.

 

예를 들어 기둥을 삭제하는데

내 위에 기둥이 있다면

그 기둥이 나없이도 설치가 가능한지 미리 만들어둔 설치검사 함수를 실행해 확인해 보는 것이다.



![img](https://blog.kakaocdn.net/dn/da7VZX/btq9DatkH1b/THHibWORXOufAG8eu9jPm1/img.png)



이 부분이 해당 함수 부분이다.

 

끝

- 시간복잡도

  O(n^2) 

## 2. 코드

```java
import java.util.ArrayList;

public class P60061기둥과보설치 {
	 int[][] pillar;
	 int[][] beam;

	 public int[][] solution(int n, int[][] build_frame) {
		pillar = new int[n+1][n+1];
		beam = new int[n+1][n+1];

		for (int frame[] : build_frame) {
			int x = frame[0];
			int y = frame[1];
			int a = frame[2]; // 0기둥 1프레임
			int b = frame[3]; // 0삭제 1생성
			if (b == 1 && makeCheck(a, x, y)) {
				if (a == 0)
					pillar[y][x] = 1;
				else
					beam[y][x] = 1;
			} else if (b == 0 && !delCheck(a, x, y)) {
				if (a == 0)
					pillar[y][x] = 1;
				else
					beam[y][x] = 1;
			}
		}
		ArrayList<int[]> arr = new ArrayList<int[]>();
		for (int j = 0; j < n+1; j++) {
			for (int i = 0; i < n+1; i++) {
				if (pillar[i][j] == 1) {
					int a[] = new int[3];
					a[0] = j;
					a[1] = i;
					a[2] = 0;
					arr.add(a);
				}
				if (beam[i][j] == 1) {
					int a[] = new int[3];
					a[0] = j;
					a[1] = i;
					a[2] = 1;
					arr.add(a);
				}
			}
		}
		int[][] answer = new int[arr.size()][3];
		for (int i = 0; i < arr.size(); i++)
			answer[i] = arr.get(i);
		return answer;
	}

	 boolean makeCheck(int a, int x, int y) {
		if (a == 0) {
			if (y == 0)
				return true;
			if (pillar[y - 1][x] == 1)
				return true;
			if (beam[y][x] == 1)
				return true;
			if (x != 0 && beam[y][x - 1] == 1)
				return true;
		}
		if (a == 1) {
			if (y == 0)
				return false;
			if (pillar[y - 1][x] == 1 || pillar[y - 1][x + 1] == 1)
				return true;
			if (x - 1 < 0 || x + 1 >= pillar.length)
				return false;
			if (beam[y][x - 1] == 1 && beam[y][x + 1] == 1)
				return true;
		}

		return false;
	}

	 boolean delCheck(int a, int x, int y) {

		if (a == 0) {
			pillar[y][x] = 0;
			if (y + 1 < pillar.length && pillar[y + 1][x] == 1 && !makeCheck(0, x, y + 1))
				return false;
			if (beam[y + 1][x] == 1 && !makeCheck(1, x, y + 1))
				return false;
			if (x - 1 >= 0 && beam[y + 1][x - 1] == 1 && !makeCheck(1, x - 1, y + 1))
				return false;

		} else if (a == 1) {
			beam[y][x] = 0;
			if (pillar[y][x] == 1 && !makeCheck(0, x, y))
				return false;
			if (x + 1 < pillar.length && pillar[y][x + 1] == 1 && !makeCheck(0, x + 1, y))
				return false;
			if (x - 1 >= 0 && beam[y][x - 1] == 1 && !makeCheck(1, x - 1, y))
				return false;
			if (x + 1 < pillar.length && beam[y][x + 1] == 1 && !makeCheck(1, x + 1, y))
				return false;
		}

		return true;
	}

}

```



## 3. 후기

- 삭제 검사부분도 생성 검사 처럼 조건을 따지려다

  생각해보니 얘가 삭제되도 다른 얘들이 멀쩡하면 되는거잖아? 라는 깨달음을 얻고 쉽게 해결할 수 있었다.

