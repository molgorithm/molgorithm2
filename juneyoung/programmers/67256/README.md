# 프로그래머스 67256 키패드 누르기

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/67256)

## 1. 설계 로직

1. 고려해야할 사항 
   - numbers 배열 (1,000) -> 완탐
   - Map사용
2. 설계로직
   1. HashMap 사용 -> key : 키패드 숫자, value : 좌표 ( row, col )
   2. 키패드의 좌표를 map에 저장
   3. 계산
      1. 숫자 2, 5, 8, 0 일 경우 좌표의 절대값 계산으로 결과를 구함.
3. 시간복잡도: O ( n ) 

## 2. 코드

```java
import java.util.*;
import java.util.stream.*;

class Solution {
    static int leftNum = 10, rightNum = 11;
    static Map<Integer,Point> map = new HashMap<>();
    static class Point{
        int r,c;
        
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    
    public String solution(int[] numbers, String hand) {
        init();        
        return Arrays.stream(numbers)
            .mapToObj(i -> cal(i,hand))
            .collect(Collectors.joining());
    }
    
    public static void init() {
        map.put(1,new Point(1,1)); map.put(2,new Point(1,2)); map.put(3,new Point(1,3));
        map.put(4,new Point(2,1)); map.put(5,new Point(2,2)); map.put(6,new Point(2,3));
        map.put(7,new Point(3,1)); map.put(8,new Point(3,2)); map.put(9,new Point(3,3)); 
        map.put(10,new Point(4,1)); map.put(0,new Point(4,2)); map.put(11,new Point(4,3));
    }
    
    
    public static String cal(int num,String hand) {
        if(num == 1 || num == 4 || num == 7) {
            leftNum = num;
            return "L";
        }else if(num == 3 || num == 6 || num == 9){
            rightNum = num;
            return "R";
        }else{
            Point left = map.get(leftNum);
            Point right = map.get(rightNum);
            Point number = map.get(num);
            int leftCnt = Math.abs(number.r - left.r) + Math.abs(number.c - left.c);
            int rightCnt = Math.abs(number.r - right.r) + Math.abs(number.c - right.c);
            if(leftCnt > rightCnt) {
                rightNum = num;
                return "R";
            }else if(leftCnt < rightCnt){
                leftNum = num;
                return "L";
            }else{
                if(hand.equals("right")){
                    rightNum = num;
                    return "R";
                }else {
                    leftNum = num;
                    return "L";
                }
            }
            
        }
    }
}
```

## 3. 후기

- 단순 계산문제라서 요령없이 풀어 시간을 아낄 수 있었습니다.
