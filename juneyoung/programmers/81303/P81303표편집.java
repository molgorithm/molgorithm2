import java.util.*;

public class P81303표편집 {
	
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
                for(int i = 0; i < cnt; i++) p = p.prev();
            }else if( c == 'D'){
                int cnt = Integer.valueOf(str.substring(2));
                for(int i = 0; i < cnt; i++) p = p.next();
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
 