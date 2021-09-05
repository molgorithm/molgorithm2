# 프로그래머스 42862 체육복

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42862)

## 1. 설계 로직

1. 체육복을 잃은 사람들과, 여분을 가진 사람들의 배열을 만든 후
2. 순서를 보장받지 않았기 때문에 reserve를 정렬한다.
3. 잃은 사람들을 먼저 lostMans에 체크한다.
4. 여분을 가진 사람들을 체크하면서 각 사람이 잃어버린 사람이라면, 잃어버리지 않았다고 체크하고, 체육복 입은 사람을 증가시킨다. 잃어버린 사람이 아니라면, 여분 체육복을 가진 사람으로 체크한다.
5. 여분을 가진 사람들을 좌, 우로 체크하며 빌려줄 수 있는지 확인한다.

- 시간복잡도 : N

## 2. 코드

```java
import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        boolean[] lostMans = new boolean[n+1];
        boolean[] reserveMans = new boolean[n+1];

        Arrays.sort(reserve);


        for(int num : lost) lostMans[num] = true;
        for(int num : reserve){
            if(lostMans[num]){
                lostMans[num] = false;
                answer++;
            }
            else reserveMans[num] = true;
        }


        int[] dir = {-1, 1};
        for(int num : reserve){
            if(!reserveMans[num]) continue;
            for(int i=0; i<dir.length; ++i){
                int idx = dir[i] + num;
                if(idx >=1 && idx <= n){
                    if(lostMans[idx]){
                        answer++;
                        lostMans[idx] = false;
                        break;
                    }
                }
            }
        }
        return answer;
    }
}
```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
