# 프로그래머스 17676 추석트래픽

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/17676)



## 1. 설계 로직

![img](https://blog.kakaocdn.net/dn/TxDKL/btq8f94D3wm/FgypsMumTpCgYJy6xIIpo1/img.png)



1분이란 시간안에 처리중인 요청의 갯수가 가장 많은 것을 찾는 문제

 

00:00:00 ~ 23:59:59.999 까지의 범위 안에서 진행된다.

 

그래서 모든 범위를 ms로 통일 시켜서 진행하였다.

범위는 0 ~ 1000*60*60*24 = 86400000 이다.

 

그런데 범위를 구하고 보니 O(n)으로 모든 범위를 한번만 본다면 해결이 되겠는데? 싶어

0~ 8640만 안을 슬라이딩 윈도우로 한번만 보도록 구현하였다.

 

먼저 입력으로 들어온 종료시간을 ms로 바꾸고

처리시간을 빼서 시작시간 역시 구한다.

 

시작시간을 카운트 해놓는 배열과 종료시간을 카운트 해놓는 배열을 만들고

구한 시작시간과 종료시간에 +1을 해둔다.

 

이후 슬라이딩 윈도우로 이동하며

시작시간에 걸치면 처리량 +1

종료시간에 걸치면 처리량 -1 을 통해

1분동안의 처리량을 구하고

이 중 최댓값을 구하면 된다.

 

- 시간복잡도

  O(1) (86400000) 

## 2. 코드

```java

public class P17676추석트래픽 {
	//1000*60*60*24 = 86400000
	public int solution(String[] lines) {
        int startTable[] = new int[24*60*60*1000];
        int endTable[] = new int[24*60*60*1000];
        for(String time : lines) {
        	int endTime = 0;
        	String[] times = time.split(" ");
        	String[] HMS = times[1].split(":");
        	endTime += Integer.parseInt(HMS[0])*60*60*1000;
        	endTime += Integer.parseInt(HMS[1])*60*1000;
        	String [] ms = HMS[2].split("\\.");
        	endTime += Integer.parseInt(ms[0])*1000;
        	endTime += Integer.parseInt(ms[1]);
        	int sec = (int)(Double.parseDouble(times[2].split("s")[0])*1000);
        	int startTime = endTime - sec +1;
        	if(startTime<0)
        		startTime= 0;
        	startTable[startTime]++;
        	endTable[endTime]++;
        }
        int cnt = 0;
        int max = 0;
        for(int i = 0 ; i < 1000 ; i++) {
        	cnt += startTable[i];
        	max = cnt;
        }
        
        for(int i = 1000 ; i < 24*60*60*1000 ; i++) {
        	cnt += startTable[i];
        	cnt -= endTable[i-1000];
        	max = Math.max(max, cnt);
        }
        
        
        
        return max;
    }

}


```



## 3. 후기

- 8640만번의 작업을 하다보니 터지지 않을까 걱정했는데

  의외로 그냥 해결되어서 다행이었다.
