# 프로그래머스 72411 메뉴 리뉴얼

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64061)

## 1. 설계 로직

1. 스택을 활용해 그대로 구현했다.

- 시간복잡도 : N^2

## 2. 코드

```java
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
```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
