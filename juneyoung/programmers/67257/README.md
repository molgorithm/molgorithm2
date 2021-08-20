# 프로그래머스 67257 수식 최대화

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/67257)

## 1. 설계 로직

1. 고려해야할 사항 
   - expression 길이 (100) , 연산기호 최대 3개 -> 완탐
2. 설계로직
   1. 인덱스로 계산하기 위해 ArrayList사용
   2. 연산기호와 숫자를 분리해 각각 ArrayList로 저장
   3. 순열을 통해 연산기호의 순서를 정함
   4. 순열의 결과에 따라 연산하여 최대값 갱신
3. 시간복잡도: O (3! x 3 x n) -> 정확하지 않음.  

## 2. 코드

```java
import java.util.*;
import java.util.stream.*;

class Solution {
    static List<Long> numbers;
    static List<String> opers = new ArrayList<>();
    static String[] sign = {"-","*","+"}, arr = new String[3];
    static boolean[] v = new boolean[3];
    static long answer = Long.MIN_VALUE;
    public long solution(String expression) {
        
        for(int i = 0; i < expression.length(); i++) {
            if(expression.charAt(i) - '0' < 0) opers.add(expression.charAt(i)+"");
        }
        
        numbers = Arrays.stream(expression.replaceAll("[^0-9]"," ").split(" "))
			.map(Long::parseLong)
			.collect(Collectors.toList());
        
        permutation(0);
        
        return answer;
    }
    
    public static long cal(long left, long right, String c) {
        if(c.equals("*")){
            return left * right;
        }else if(c.equals("-")) {
            return left - right;
        }else return left + right;
    }
    
    public static void permutation(int idx) {
        if(idx == arr.length) {
            List<Long> calNum = new ArrayList<>();
            List<String> calOpers = new ArrayList<>();
            calNum.addAll(numbers);
            calOpers.addAll(opers);
            
            for(int i = 0; i < arr.length; i++) {
                String op = arr[i];
                
                for(int j = 0; j < calOpers.size(); j++) {
                    if(calOpers.get(j).equals(op)) {
                        long reuslt = cal(calNum.get(j),calNum.get(j+1),op);
                        calNum.remove(j);
                        calNum.remove(j);
                        calNum.add(j,reuslt);
                        calOpers.remove(j);
                        j--;
                    }
                }
                
            }
            answer = Math.max(answer,Math.abs(calNum.get(0)));
            return;
        }
        
        for(int i = 0; i < sign.length; i++) {
            if(v[i]) continue;
            v[i] = true;
            arr[idx] = sign[i];
            permutation(idx + 1);
            v[i] = false;
        }
    }
}

```

## 3. 후기

- 피연산자의 갯수가 적어서 ArrayList를 사용했지만 크다면 다른 방법으로 생각해봐야 함.
