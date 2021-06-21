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
