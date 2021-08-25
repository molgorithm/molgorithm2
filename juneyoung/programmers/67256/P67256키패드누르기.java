import java.util.*;
import java.util.stream.*;

public class P67256키패드누르기 {
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
 