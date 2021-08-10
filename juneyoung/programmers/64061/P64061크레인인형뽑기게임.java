import java.util.*;

public class P64061크레인인형뽑기게임 {
    static int answer;
    static Stack<Integer> st = new Stack<>();

    public int solution(int[][] board, int[] moves) {
        st.push(-1);
        for (Integer move : moves) {
            cal(board, move - 1);
            System.out.println(answer);
        }

        return answer;
    }

    static void cal(int[][] board, int move) {
        for (int col = 0; col < board[0].length; col++) {
            if (board[col][move] == 0) continue;
            else {
                if (st.peek() == board[col][move]) {
                    answer += 2;
                    st.pop();
                } else st.push(board[col][move]);
                board[col][move] = 0;
                break;
            }
        }
    }
}
 