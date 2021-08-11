import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int picked, idx, answer = 0;
		Stack<Integer> s = new Stack<Integer>();
		int[] tmp;

		for (int cmd : moves) {
			cmd--;
			tmp = rtoc(board, cmd);
			idx = getDoll(tmp);
			if (idx == -1)
				continue;
			picked = board[idx][cmd];
			board[idx][cmd] = 0;
			if (!s.isEmpty() && s.peek() == picked) {
				answer += 2;
				s.pop();
				continue;
			}
			s.add(picked);
		}
		return answer;
    }
    
    private int[] rtoc(int[][] board, int num) {
		int[] arr = new int[board.length];
		for (int j = 0; j < board.length; j++) {
			arr[j] = board[j][num];
		}

		return arr;
	}

	private int getDoll(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				return i;
			}
		}
		return -1;
	}
}