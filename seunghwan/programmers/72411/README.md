# 프로그래머스 72411 메뉴리뉴얼

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/72411)



## 1. 설계 로직

1. 고려해야 할 사항

- orders의 크기 20

- orders의 각 원소 크기 10

- course의 크기 10

  크기가 작아 완탐 가능

 

2. 설계 로직

- 메뉴 구성에 따라 따로 저장하기 위한 HashMap의 배열 생성

- HashMap의 key는 String , value는 Integer (메뉴구성, 갯수)

- 각 주문목록을 조합으로만 뽑기 위해 알파벳 오름 차순 정렬

- 조합을 뽑고 해당하는 길이의 HashMap배열에서 HashMap을 뽑아 갯수 ++
  - ex) ABC 에서 AB를 뽑았으면 maps[2]의 HashMap에 AB +1

- course에서 찾는 길이의 HashMap을 가져와서 1이상의 최댓값 검색

- 해당하는 key들을 하나의 List에 저장 후 정렬

 

3. 시간 복잡도

- N = 2^10 * 20(조합 최대 갯수)

- O(NlogN)

## 2. 코드

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P72411메뉴리뉴얼 {
    
	
	Map<String, Integer> maps [] = new Map[21];
	int [] Max = new int[21];
	public String[] solution(String[] orders, int[] course) {
        for(int i = 0 ; i < 21 ; i++)
        	maps[i] = new HashMap<String, Integer>();
        for(String order : orders) {
        	char [] orderList = order.toCharArray();
        	Arrays.sort(orderList);
        	comb(orderList, 0);
        }
        List<String> answerList = new ArrayList<String>();
        for(int i : course) {
        	Map<String, Integer> target = maps[i];
        	int max = -1;
        	List<String> MaxList = new ArrayList<String>();
        	for(String key : target.keySet()) {
        		int cnt = target.get(key);
        		if(cnt == 1)
        			continue;
        		if(max < cnt) {
        			MaxList.clear();
        			max = cnt;
        			MaxList.add(key);
        		}else if(max == cnt) {
        			MaxList.add(key);
        		}
        	}
        	answerList.addAll(MaxList);
        }
        Collections.sort(answerList);
        
        return answerList.toArray(new String[answerList.size()]);
    }
	boolean sel[] = new boolean[10];
	void comb(char[] order,int idx) {
		if(idx == order.length) {
			String key = "";
			for(int i = 0 ; i < order.length; i++) {
				if(sel[i])
					key += order[i];
			}
			int value =  maps[key.length()].getOrDefault(key, 0)+1;
			maps[key.length()].put(key, value);
			return;
		}
		sel[idx] = true;
		comb(order,idx+1);
		sel[idx] = false;
		comb(order,idx+1);
	}
    
}
```



## 3. 후기

- N이 작아 완탐으로 풀이가 가능해 어렵지 않게 해결 가능했음.
