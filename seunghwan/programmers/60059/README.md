# 프로그래머스 60059 자물쇠와 열쇠

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/60059)





## 1. 설계 로직



![img](https://blog.kakaocdn.net/dn/drgvgv/btq9zFndzBp/njkutl5v4Q6GKZKeo9h8D1/img.png)



N이 20 밖에 안되는 매우 작은 크기이다.

 

그래서 모든 경우의 수를 가지고 모든 경우의 수에 대입해 확인하는 식으로 풀이를 진행하였다.

 

 

우선 키의 홈이 이동시킬 수 있고 바깥면으로 이동시켜서 안보이게하는 것도 가능하다.

이것을 다르게 보면



![img](https://blog.kakaocdn.net/dn/sjb7Y/btq9AirqveI/CXtZfI5yPDQtEQBj7ujkDK/img.png)



이렇게 키후보가 될수도

 



![img](https://blog.kakaocdn.net/dn/b3f5Ym/btq9x6ZAgrq/RJJGj2auKnVDzF5XolA2K1/img.png)



이렇게 키후보가 될 수도 있는 것이다.

 

이것을 쉽게 구현하기 위해

키를 확장시켜서



![img](https://blog.kakaocdn.net/dn/sIryA/btq9C83gUOE/NrU6aMBcLisOukgNM0w2ok/img.png)



이렇게 만들고 원래 키의 크기에 맞춰 0,0 부터 서브키를 만들어 모든 경우의수를 확인할 수 있다.

회전의 경우에도 이 상태에서 그대로 회전을 시키고 똑같은 방법으로 서브키를 만들어 확인하면 된다.

이렇게 서브키를 만들었으면

 

자물쇠에 그대로 갖다 대보며 맞는지 안맞는지 모두 검사하고

맞는 키가 있다면 true

없다면 false를 리턴하면 된다.

 

 

 

경우의 수를 얼추 따져보면

우선 키후보의 수는

최대 4M^2 * 4 정도

뽑은 키후보를 자물쇠에 비교해보는 경우의수 (N-M+1)^2

각 비교마다 M^2

16M^4 * (N-M+1)^2정도 일 것 같다.

 

최악으로 생각해도

19,360,000 정도로 충분히 완탐으로 풀이가 가능했다.

- 시간복잡도

  O(M^4*(N-M+1)^2) 

## 2. 코드

```java

public class P60059자물쇠와열쇠 {

	public boolean solution(int[][] key, int[][] lock) {
		int keyLength = key.length;
		key = extend(key);
		int blank = 0;
		for (int i = 0; i < lock.length; i++)
			for (int j = 0; j < lock.length; j++)
				if (lock[i][j] == 0)
					blank++;
		for (int rolling = 0; rolling < 4; rolling++) {
			for (int i = 0; i < keyLength * 2 - 1; i++) {
				for (int j = 0; j < keyLength * 2 - 1; j++) {
					if (check(makeSubkey(i, j, keyLength, key), lock, blank))
						return true;
				}
			}
			key = role(key);
		}

		return false;
	}

	int[][] extend(int map[][]) {

		int ex[][] = new int[map.length * 3 - 2][map.length * 3 - 2];
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map.length; j++)
				ex[map.length - 1 + i][map.length - 1 + j] = map[i][j];
		return ex;
	}

	int[][] role(int map[][]) {
		int ch[][] = new int[map.length][map.length];
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map.length; j++)
				ch[j][map.length - 1 - i] = map[i][j];
		return ch;
	}

	int[][] makeSubkey(int row, int col, int keyLength, int[][] map) {
		int[][] subkey = new int[keyLength][keyLength];
		for (int i = row; i < row + keyLength; i++)
			for (int j = col; j < col + keyLength; j++)
				subkey[i - row][j - col] = map[i][j];
		return subkey;
	}

	boolean check(int key[][], int lock[][], int blank) {
		for (int starti = 0; starti <= lock.length - key.length; starti++) {
			gg: for (int startj = 0; startj <= lock.length - key.length; startj++) {
				int fill = 0;
				for (int i = 0; i < key.length; i++) {
					for (int j = 0; j < key.length; j++) {
						if (key[i][j] == 1 && lock[starti + i][startj + j] == 1)
							continue gg;
						if (key[i][j] == 0 && lock[starti + i][startj + j] == 0)
							continue gg;
						if (key[i][j] == 1 && lock[starti + i][startj + j] == 0)
							fill++;
					}
				}
				if (fill == blank)
					return true;
			}
		}
		return false;

	}
}



```



## 3. 후기

- 키를 이동시키거나 회전을 시킬 때 안보이는 부분의 처리,

  즉 서브키의 생성이 중요하지 않았나 싶다.
