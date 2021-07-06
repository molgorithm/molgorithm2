# 프로그래머스 17686파일명정렬

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/17686)



## 1. 설계 로직

![img](https://blog.kakaocdn.net/dn/d6rgRM/btq8TvTrGBE/JYcxmjgYZud3hRwMekn2C1/img.png)



문자열 형태의 구현 문제

 

시키는데로만 구현하면 어려울 것 없는 문제다.

 

먼저 들어온 파일명에서 숫자를 찾고 그 숫자가 끝나는 지점을 찾는다.

 

찾은 숫자 이전까지를 head, 숫자가 number가 되도록 저장을 하고

 

compareTo 함수를 이용해 정렬 방식을 문제에 맞게 세팅해주면 된다.

 

나는 head와 숫자가 모두 같으면 순번 그대로 유지가 되어야 하므로

확실히 하기위해 order도 같이 저장하여 compare 조건에 넣어 두었다.

 

주의할 점으로

숫자의 끝지점을 찾을 때 수가아닌 다른 문자가 나오면 그 부분을 끝으로 찾는 식으로 구현하면

수 뒤에 아무것도 없을 경우 Runtime error 가 뜨게 된다.

 

이 부분에 유의하면서 풀이하도록 하자.

- 시간복잡도

  O(NlogN)

## 2. 코드

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P17686파일명정렬 {
	
	class file implements Comparable<file>{
		String origin;
		String head;
		int num;
		int order;
		


		public file(String origin, String head, int num, int order) {
			super();
			this.origin = origin;
			this.head = head;
			this.num = num;
			this.order = order;
		}



		@Override
		public int compareTo(file o) {
			if(this.head.equals(o.head)) {
				if(this.num == o.num)
					return this.order - o.order;
				return this.num-o.num;
			}
			
			return this.head.compareTo(o.head);
			
		}
		
	}

    public String[] solution(String[] files) {
    	
        List<file> fileList = new ArrayList<file>();
        for(int i = 0 ; i < files.length; i++) {
        	String origin = files[i];
        	int numStart = 0;
        	int numEnd = 0;
        	boolean isNum = false;
        	for(int j =0 ; j < origin.length(); j++) {
        		if(!isNum &&'0'<=origin.charAt(j) && origin.charAt(j)<= '9' ) {
        			numStart = j;
        			isNum = true;
        		}
        		if(isNum &&( '0'>origin.charAt(j) || origin.charAt(j)> '9') ) {
        			numEnd = j;
        			break;
        		}
        	}
            if(numEnd == 0)
                numEnd = origin.length();
        	String head  = origin.substring(0, numStart).toLowerCase();
        	int num = Integer.parseInt(origin.substring(numStart,numEnd));
        	fileList.add(new file(origin, head, num,i));
        }
        Collections.sort(fileList);
        String[] answer = new String[fileList.size()];
        for(int i = 0 ; i < fileList.size(); i++) answer[i] = fileList.get(i).origin;
        
        return answer;
    }

}

```



## 3. 후기

- 더 복잡하게 만들려면 더 복잡해질 건덕지가 많아 보이는 문제였는데

  처음 읽을 때 받은 느낌보다 훨씬 쉬워서 당황한 문제였다.
