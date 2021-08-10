# 프로그래머스 64061 크레인 인형뽑기 게임

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64061)

## 1. 설계 로직

1. 고려해야할 사항 
   - board 배열 (30 x 30), moves (1,000) -> 완탐 가능
   - 스택 사용
2. 설계로직
   1. board 배열에서 해당 열(moves 값) 검색
      - 0일 경우 패스
      - 0이 아닐경우 stack의 peek값을 확인
        - 값이 같으면 answer+2, pop
        - 값이 다르면 push
        - 배열 0으로 초기화
3. 시간복잡도: O (1,000 x 30) 

## 2. 코드

```java
import java.util.*;

class Solution {
    static int answer;
    static Stack<Integer> st = new Stack<>();
    public int solution(int[][] board, int[] moves) {
        st.push(-1);
        for(Integer move : moves) {
            cal(board,move-1);
            System.out.println(answer);
        }
        
        return answer;
    }
    
    static void cal(int[][] board, int move) {
        for(int col = 0; col < board[0].length; col++) {
            if(board[col][move] == 0) continue;
            else {
                if(st.peek() == board[col][move]) {
                    answer += 2;
                    st.pop();
                }else st.push(board[col][move]);
                board[col][move] = 0;
                break;
            }
        }
    }
}
```

## 3. 후기

- 스택을 사용했다면 쉽게 구현할 수 있는 문제.
