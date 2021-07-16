
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
