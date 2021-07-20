# 프로그래머스 60063 블록이동하기

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/60063)



## 1. 설계 로직

![img](https://blog.kakaocdn.net/dn/21cwg/btq9DmNDvP2/fXpqEHFsoloYxKzVqqKUWK/img.png)



풀이 개념 자체는 정말 간단한데

극한의 구현 복잡도를 가진 문제

 

사실 구현 복잡도도 그렇게 복잡하다 할 편은 아니긴 하지만

뭔가 비효율적으로 조건을 따져봐야만 해서 풀이 내내 답답하게 느껴져서 그렇게 느껴지는 것 같다.

 

문제 자체는 단순히 bfs를 이용해 도착지 까지 가장 빠른 경로를 찾는 문제인데,

 

이동 시

4방향 이동과

1번 축 시계,반시계 회전

2번 축 시계,반시계 회전

총 8개의 분기로 나뉘어 들어간다.

 

4방향 이동은 일반적인 bfs같이 이동을 하면 되는데

 

문제는 축 회전시

축과 대각선 칸, 회전 후 자리할 위치 칸 두자리를 검사해야 하는데

회전도 가로로 있을때와 세로로 있을때의 검사 위치가 달라진다.

또 이것이 시계방향, 반시계방향으로 나뉘어진다.

여기에도 규칙성이 있기야 하겠지만 규칙성을 찾아 구현한다해도 복잡한 형태로 나올것 같아,

결국 하드 코딩을 하는데 그 양이 어마어마해서

조건을 각각 생각해보며 풀이하는 것이 머리 아팠다.

 

회전과 관련된 것은 하드코딩 하였으므로 따로 적지는 않도록 하겠다.

 

이제 bfs를 통해

가장 먼저 도착한 분기에서 시간을 return하면 된다.

- 시간복잡도

  O(n^2) 

## 2. 코드

```java
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

```



## 3. 후기

- 2020 문제들이 구현과 관련된 문제들이 많은 것 같다.
