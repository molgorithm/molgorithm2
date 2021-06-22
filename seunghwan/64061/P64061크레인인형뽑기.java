import java.util.Stack;

public class P64061크레인인형뽑기 {
	
	public int solution(int[][] board, int[] moves) {
		int answer = 0;
		Stack<Integer> basket = new Stack<Integer>();
		basket.push(-1);
		for(int col : moves) { //뽑는 위치
			int num = get(board,col-1);
			if(num == 0)
				continue;
			
			if(basket.peek() == num) {
				basket.pop();
				answer+=2;
			}else 
				basket.push(num);
		}
		
		return answer;
	}
	static int get(int[][] board, int col) {
		int num = 0;
		for(int i = 0 ; i < board.length; i++) 
			if(board[i][col] != 0) {
				num = board[i][col];
				board[i][col]=0;
				break;
			}
		return num;
	}
}

