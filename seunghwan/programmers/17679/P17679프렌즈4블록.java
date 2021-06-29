
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

