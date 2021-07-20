import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class P60063블록이동하기 {
	
	
	 class robot{
		int x1;
		int y1;
		int x2;
		int y2;
		int t;
		public robot(int x1, int y1, int x2, int y2, int t) {
			super();
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.t = t;
		}
		
	}
	
	 public int solution(int[][] board) {
        int answer = 0;
        Queue<robot> robotList = new LinkedList<robot>();
        robotList.add(new robot(0, 0, 0, 1,0));
        int dr[] = new int[] {0,0,1,-1};
        int dc[] = new int[] {1,-1,0,0};
        int N = board.length;
        HashSet<String> visit = new HashSet<String>();
        while(!robotList.isEmpty()) {
        	robot now = robotList.poll();
        	if(now.x1 == N-1 && now.y1 == N-1)
        		return now.t;
        	if(now.x2 == N-1 && now.y2 == N-1)
        		return now.t;
        	
        	//4방 이동
        	for(int i = 0 ; i < 4 ; i++) {
        		int row1 = now.x1+dr[i];
        		int row2 = now.x2+dr[i];
        		int col1 = now.y1+dc[i];
        		int col2 = now.y2+dc[i];
        		String visitCheck = ""+row1+row2+col1+col2;
        		if(row1<0 || col1 <0 || row2<0 || col2<0)
        			continue;
        		if(row1>=N || row2>=N || col1>=N || col2>=N)
        			continue;
        		if(board[row1][col1] == 1 || board[row2][col2] == 1)
        			continue;
        		if(!visit.add(visitCheck))
        			continue;
        		robotList.add(new robot(row1, col1, row2, col2, now.t+1));
        	}
        	//회전
        	//1축 시계회전
        	if(now.x1 == now.x2) {
        		if(now.x1+1 < N && now.y1+1 < N && board[now.x1+1][now.y1+1] != 1 && board[now.x1+1][now.y1] != 1) {
        			int row1 = now.x1;
        			int col1 = now.y1;
        			int row2 = now.x1+1;
        			int col2 = now.y1;
        			String visitCheck = ""+row1+row2+col1+col2;
        			if(visit.add(visitCheck))
        				robotList.add(new robot(row1, col1, row2, col2, now.t+1));
        		}
        	}else if(now.y1 == now.y2) {
        		if(now.x1+1 <N && now.y1 -1 >=0 && board[now.x1+1][now.y1-1]!=1 && board[now.x1][now.y1-1] != 1) {
        			int row1 = now.x1;
        			int col1 = now.y1-1;
        			int row2 = now.x1;
        			int col2 = now.y1;
        			String visitCheck = ""+row1+row2+col1+col2;
        			if(visit.add(visitCheck))
        				robotList.add(new robot(row1, col1, row2, col2, now.t+1));
        		}
        	}
        	//1축 반시계 회전
        	if(now.x1 == now.x2) {
        		if(now.x1-1 >= 0 && now.y1+1 < N && board[now.x1-1][now.y1+1] != 1 && board[now.x1-1][now.y1] != 1) {
        			int row1 = now.x1-1;
        			int col1 = now.y1;
        			int row2 = now.x1;
        			int col2 = now.y1;
        			String visitCheck = ""+row1+row2+col1+col2;
        			if(visit.add(visitCheck))
        				robotList.add(new robot(row1, col1, row2, col2, now.t+1));
        		}
        	}else if(now.y1 == now.y2) {
        		if(now.x1+1 <N && now.y1 +1 <N && board[now.x1+1][now.y1+1]!=1 && board[now.x1][now.y1+1] != 1) {
        			int row1 = now.x1;
        			int col1 = now.y1;
        			int row2 = now.x1;
        			int col2 = now.y1+1;
        			String visitCheck = ""+row1+row2+col1+col2;
        			if(visit.add(visitCheck))
        				robotList.add(new robot(row1, col1, row2, col2, now.t+1));
        		}
        	}
        	
        	//2축 시계회전
        	if(now.x1 == now.x2) {
        		if(now.x2-1 >= 0 && now.y2-1 >= 0 && board[now.x2-1][now.y2-1] != 1 && board[now.x2-1][now.y2] != 1) {
        			int row1 = now.x2-1;
        			int col1 = now.y2;
        			int row2 = now.x2;
        			int col2 = now.y2;
        			String visitCheck = ""+row1+row2+col1+col2;
        			if(visit.add(visitCheck))
        				robotList.add(new robot(row1, col1, row2, col2, now.t+1));
        		}
        	}else if(now.y1 == now.y2) {
        		if(now.x2-1 >=0 && now.y2 +1 <N && board[now.x2-1][now.y2+1]!=1 && board[now.x2][now.y2+1] != 1) {
        			int row1 = now.x2;
        			int col1 = now.y2;
        			int row2 = now.x2;
        			int col2 = now.y2+1;
        			String visitCheck = ""+row1+row2+col1+col2;
        			if(visit.add(visitCheck))
        				robotList.add(new robot(row1, col1, row2, col2, now.t+1));
        		}
        	}
        	//2축 반시계회전
        	if(now.x1 == now.x2) {
        		if(now.x2+1 < N && now.y2-1 >= 0 && board[now.x2+1][now.y2-1] != 1 && board[now.x2+1][now.y2] != 1) {
        			int row1 = now.x2;
        			int col1 = now.y2;
        			int row2 = now.x2+1;
        			int col2 = now.y2;
        			String visitCheck = ""+row1+row2+col1+col2;
        			if(visit.add(visitCheck))
        				robotList.add(new robot(row1, col1, row2, col2, now.t+1));
        		}
        	}else if(now.y1 == now.y2) {
        		if(now.x2-1 >=0 && now.y2 -1 >=0 && board[now.x2-1][now.y2-1]!=1 && board[now.x2][now.y2-1] != 1) {
        			int row1 = now.x2;
        			int col1 = now.y2-1;
        			int row2 = now.x2;
        			int col2 = now.y2;
        			String visitCheck = ""+row1+row2+col1+col2;
        			if(visit.add(visitCheck))
        				robotList.add(new robot(row1, col1, row2, col2, now.t+1));
        		}
        	}
        	
        }
        return answer;
    }
}
