# 프로그래머스 17684 압축

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/17684)



## 1. 설계 로직

LZW 압축방식을 직접 구현해보는 문제

 

문자열을 자르고 Hash 개념만 안다면 쉽게 해결이 가능하다.

 

먼저 A ~ Z를 Hash 테이블에 추가하고

 

문자에서 사전에 등록되지 않은 subString을 찾을 때까지 검사하고

찾으면 직전에 찾았던 사전에 등록된 값을 answer에 저장,

subString을 새롭게 사전에 등록.

 

이것을 계속해서 반복해주고

마지막 문자열에 도달하면 더이상 찾을 수 없으므로

바로 사전에 등록된 값을 저장하고 마치면 된다.

 

- 시간복잡도

  O(n)

## 2. 코드

```java
import java.util.ArrayList;
import java.util.HashMap;

public class P17684압축 {

    public int[] solution(String msg) {
    	HashMap<String, Integer> dic = new HashMap<String, Integer>();
    	ArrayList<Integer> answerList = new ArrayList<Integer>();
    	for(char i = 'A' ; i <= 'Z'; i++)
    		dic.put(i+"", i-'A'+1);
    	int dicNum = 27;
    	for(int i = 0 ; i < msg.length();i++) {
    		for(int j = 1 ; i+j<= msg.length() ; j++) {
    			String target = msg.substring(i, i+j);
    			if(dic.get(target) == null) {
    				answerList.add(dic.get(target.substring(0, target.length()-1)));
    				dic.put(target, dicNum++);
    				i += j-2;
    				break;
    			}
    			if(i+j == msg.length()) {
     				answerList.add(dic.get(target));
    				i += j-1;
    			}
    		}
    	}
    	
    	
        int[] answer = new int[answerList.size()];
        for(int i = 0; i < answerList.size(); i++) 
        	answer[i] = answerList.get(i);
        return answer;
    }
    
}

```



## 3. 후기

- .
