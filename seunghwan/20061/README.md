# 백준 20061 모노미노도미노2

[문제 링크](https://www.acmicpc.net/problem/20061)

## 1. 설계 로직

이전에 풀었던 모노미노도미노 ( https://lovelyunsh.tistory.com/70 ) 의 좀 더 쉬운 버젼

이 문제도 푼적이 있지만 스터디 문제로 나와 다시 풀어 보았다.

 

이전과 똑같이

초록색이랑 파란색이랑 함수 하나로 처리 할 수 있도록 파란색 영역은 90도 돌리고 좌우 반전하였다고 생각하고 풀었다.



![img](https://blog.kakaocdn.net/dn/mVCWY/btq7I06U0sq/Lt9aBsK0z18mUxDXritTWK/img.png)



이렇게 바꾸면 떨어지는 블록의 좌표와 모양만 신경쓰면 되는데

초록색은 처음 주어지는 그대로 떨어뜨리면 되고

파란색으로 떨어뜨릴 때는 x좌표랑 y좌표만 서로 바꾸고

모양이 가로면 세로로 세로면 가로로 바꿔서 떨어 뜨리면 똑같이 떨어뜨릴 수 있다.

 

나머지는 시뮬레이션으로 하라는 데로만 구현하면 된다.



- 시간복잡도

  O(N^2)      (N^2 = 맵크기)

## 2. 코드

```java
package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G2_20061모노미노도미노2_2 {
	static int bluemap[][];
	static int greenmap[][];
	static int score;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		greenmap = new int[6][4];
		bluemap = new int[6][4];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());// row
			int y = Integer.parseInt(st.nextToken());// col
			drop(greenmap, t, y);
			t = t == 2 ? 3 : t == 3 ? 2 : 1;// t = 2->3,3->2,1->1
			drop(bluemap, t, x);
		}
		int remainBlock = 0;
		remainBlock += count(greenmap);
		remainBlock += count(bluemap);
		System.out.println(score);
		System.out.println(remainBlock);

	}

	static void drop(int[][] map, int t, int col) {
		int placeRow[] = { -2, -2 }; //
		if (t == 2) {
			for (int i = 0; i < 7; i++) {
				if (i == 6) {

				}
				if (i == 6 || map[i][col] == 1 || map[i][col + 1] == 1) {
					map[i - 1][col] = 1;
					map[i - 1][col + 1] = 1;
					placeRow[0] = i - 1;
					break;
				}
			}
		} else {
			for (int i = 0; i < 7; i++) {
				if (i == 6 || map[i][col] == 1) {
					map[i - 1][col] = 1;
					placeRow[0] = i - 1;
					if (t == 3) {
						map[i - 2][col] = 1;
						placeRow[1] = i - 2;
					}
					break;
				}
			}
		}
		boom(map, placeRow);
	}

	static void boom(int[][] map, int placeRow[]) {
		gg: for (int i = 0; i < 2; i++) {
			int row = placeRow[i];
			if (row < 2)
				break;
			for (int j = 0; j < 4; j++) // 한줄이 꽉찼는지 확인
				if (map[row][j] == 0)
					continue gg;
			score++;
			placeRow[1]++;
			for (int j = row; j > 0; j--) // 한줄씩 내리기
				map[j] = map[j - 1].clone();
			Arrays.fill(map[0], 0);
		}
		int sl = 0;
		for (int i = 0; i < 2; i++)// special line 갯수 확인
			if (placeRow[i] == 0 || placeRow[i] == 1)
				sl++;

		if (sl != 0) {
			for (int j = 5; j > 1; j--) {
				map[j] = map[j - sl].clone();
			}
			Arrays.fill(map[0], 0);
			Arrays.fill(map[1], 0);
		}
	}

	static int count(int[][] map) {
		int num = 0; // 남은 갯수
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 4; j++)
				if (map[i][j] == 1)
					num++;
		return num;
	}
}

```



## 3. 후기

- 다 풀고 이전에 풀었던 코드와 비교하면서 푸는 재미가 있었다.