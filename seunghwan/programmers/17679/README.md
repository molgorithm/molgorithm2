# 프로그래머스 17679 프렌즈4블록

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/17679)



## 1. 설계 로직

애니팡같은 문제

 

순수 구현문제라 주어진 요구조건에만 맞추면 풀이가 가능하다.

 

\1. 먼저 블록 중 2*2 모양의 블록을 모두 찾고

\2. 찾은 블록들을 빈칸으로 비운다.

\3. 그 빈칸 위의 칸들을 모두 밑으로 내려준다.

 

이것을 2*2모양의 블록이 찾아지지 않을 때까지 반복하고

없어진 블록의 갯수를 출력한다.

끝

 

- 시간복잡도

  O(n^2)

## 2. 코드

```java
class P17679프렌즈4블록 {
	public int solution(int m, int n, String[] board) {
		int answer = 0;
		char[][] map = new char[m][n];

		for (int i = 0; i < m; ++i)
			map[i] = board[i].toCharArray();

		while (true) {
			int cnt = checkBlock(m, n, map);
			if (cnt == 0)
				break;
			answer += cnt;

			dropBlock(m, n, map);
		}

		return answer;
	}

	private void dropBlock(int m, int n, char[][] map) {
		for (int i = m - 1; i >= 0; i--)
			for (int j = 0; j < n; j++) {
				if (map[i][j] != '.')
					continue;
				for (int row = i - 1; row >= 0; row--) {
					if (map[row][j] != '.') {
						map[i][j] = map[row][j];
						map[row][j] = '.';
						break;
					}
				}

			}

	}

	private int checkBlock(int m, int n, char[][] map) {
		int cnt = 0;
		boolean[][] deleteCheck = new boolean[m][n];

		for (int i = 0; i < m - 1; i++) {
			for (int j = 0; j < n - 1; j++) {
				if (map[i][j] == '.')
					continue;
				fourCheck(map, deleteCheck, i, j);
			}
		}

		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				if (deleteCheck[i][j]) {
					cnt++;
					map[i][j] = '.';
				}

		return cnt;
	}

	private void fourCheck(char[][] map, boolean[][] deleteCheck, int row, int col) {
		char block = map[row][col];

		for (int i = row; i < row + 2; i++) {
			for (int j = col; j < col + 2; j++) {
				if (map[i][j] != block)
					return;
			}
		}

		for (int i = row; i < row + 2; i++)
			for (int j = col; j < col + 2; j++)
				deleteCheck[i][j] = true;

	}
}


```



## 3. 후기

- 순수 시뮬레이션 문제라 개념이 어렵지는 않지만 구현이 조금 복잡했다.

