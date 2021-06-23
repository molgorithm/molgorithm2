# 프로그래머스 64061 크레인인형뽑기

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64061)

## 1. 설계 로직

시키는 대로 인형을 뽑고 바구니에 담은 뒤 같은 인형 2개가 붙어있으면 폭발,

시키는 대로 다 뽑은 뒤 폭발한 인형 갯수 출력하는 문제



\1. 인형을 뽑는 것은 각 라인에서 0이 아닌 숫자를 찾을 때까지 찾아가서 뽑는다.

(극한까지 효율을 뽑는다고 생각하면 각 라인의 인형 갯수를 미리 배열하나에 저장 해놓을 수도 있을 것 같다)



\2. 인형을 담는 바구니는 하나씩 쌓이는 형태이기 때문에 Stack을 활용하였다.

이전에 담긴 인형이 현재 담을 인형과 같으면 같이 사라지는 형태이기 때문에

현재 인형을 담기전 이전 인형을 먼저 확인하고(peek) 같으면

이전인형 pop 다르면 현재인형 push 하는 형태로 구현

![img](https://blog.kakaocdn.net/dn/VSQDK/btq7SMfFjHG/8sp6YZnmGokDe2fJR85BnK/img.gif)

\3. 최종 사라진 인형의 갯수 출력



- 시간복잡도

  최악 30 * 1000

## 2. 코드

```java
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


```



## 3. 후기

- 어렵지 않게 해결하였다.