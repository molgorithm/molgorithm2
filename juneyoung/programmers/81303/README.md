# 프로그래머스 81303 표 편집

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/81303)

## 1. 설계 로직

1. 고려해야할 사항 
   - n (1,000,000), cmd (200,000) -> 완탐 불가능. 
   - cmd에 등장하는 모든 X들의 값을 합친 결과가 1,000,000 이하인 경우만 입력으로 주어집니다.
   - 이동, 삭제 복구 연산.
   - 사용할 자료구조 : 이중연결리스트, 스택
2. 설계로직
   1. 이중연결리스트 자료구조 만들어야 함.
      - 삽입 ( add ), 삭제 (delete), 이동 (prev, next) 매서드 필요.
      - 주의점 : 첫번째 노드 삭제는 다른 노드 삭제와 다름.
   2. 이중연결리스트에 데이터 n개만큼 add.
   3. 마지막 인덱스 기준 현재위치 찾기.
   4. 명령어 연산 실행
      - U : X 수 만큼 prev( )
      - D : X 수 만큼 next( )
      - C : 스택에 해당 노드 추가 -> 현재 노드 delete( )
      - Z :  스택에 top 에 있는 노드 꺼낸 후 리스트에 연결
3. 시간복잡도: O ( n ) 

## 2. 코드

```java
import java.util.*;

class Solution {
     
    static Stack<Point> st = new Stack<>();    
    public String solution(int n, int k, String[] cmd) {
        Point p = new Point(0);
        for(int i = 1; i < n; i++) {
            p = p.add(new Point(i));
        }
        
        // 현재 위치 찾기.
        for(int i = n-1; i > k; i--) {
            p = p.prev(); 
        }
       
        for(String str : cmd){
            char c = str.charAt(0);
            if(c == 'U'){
                int cnt = Integer.valueOf(str.substring(2));
                for(int i = 0; i < cnt; i++) {
                    p = p.prev();    
                }
            }else if( c == 'D'){
                int cnt = Integer.valueOf(str.substring(2));
                for(int i = 0; i < cnt; i++) {
                    p = p.next();    
                }
            }else if( c == 'C') {
                st.push(p);
                p = p.delete();
            }else  {
                reset(st.pop());  
            }
            
        }
        
        boolean[] ans = new boolean[n];
        while(!st.isEmpty()) {
            ans[st.pop().n] = true;
        }
        
        StringBuilder sb =  new StringBuilder();
        for(int i = 0; i < ans.length; i++) {
            if(ans[i]) sb.append('X');
            else sb.append('O');
        }
        
        return sb.toString();
    
    }
    
    public void reset(Point top) {
         if(top.prev == null) {
            // 0번 인덱스 
            top.next.prev = top;
         }else{
             // 0번 인덱스 아닌경우
            top.prev.next = top;
            if(top.next != null) {
                // 마지막 인덱스가 아닌 경우
                top.next.prev = top;    
            }
         }
    }
    
    static public class Point {
        int n;
        Point prev = null;
        Point next = null;
        
        public Point(int n) {
            this.n = n;
        }
        
        public Point delete() {
            // 0번 인덱스 일 때
            if(prev == null) {
                next.prev = null;
                return next;
            }
            
            // 0번 아닐 때
            if(next == null) {
                prev.next = null;
                return prev;
            }else{
                prev.next = next;
                next.prev = prev;
                return next;
            }
            
        }
        
        public Point add(Point p) {
            p.next = this.next;
            p.prev = this;
            this.next = p;
            return p;
        }
        
        public Point next(){
            return this.next;
        }
        
        public Point prev() {
            return this.prev;
        }
    }
}
```

## 3. 후기

- 자료구조 직접 구현 문제라 어려웠음.

- 누적합을 이용해서 이동하는 위치를 빠르게 구할 수 있다면 1차원 배열로 해결 가능한 문제 ( 사실상 불가능 )
