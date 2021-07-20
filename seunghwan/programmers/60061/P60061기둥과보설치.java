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
