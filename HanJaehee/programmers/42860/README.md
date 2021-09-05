# 프로그래머스 42860 조이스틱

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42860)

## 1. 설계 로직

1. 전체 반복횟수는 name의 길이에서 A의 갯수를 뺀 횟수
2. 먼저, 알파벳 변경 횟수를 answer에 모두 더해놓으면서, 전체 반복횟수를 구한다.
3. 왼쪽 이동거리와 오른쪽 이동거리를 측정해 더 짧은 쪽으로 이동하면서 이동 횟수를 누적한다.
4. 두 경우가 같을경우엔 오른쪽을 우선으로 한다.

- 시간복잡도 : N

## 2. 코드

```java
class Solution {
    public int solution(String name) {
        int answer = 0;
        int count = name.length();
        char[] charName = name.toCharArray();
        boolean[] isFinish = new boolean[charName.length];

        int now = 0;
        int next = 0;
        for(int i=0; i<charName.length; ++i){ // 알파벳 변경
            if(charName[i] == 'A') count--;
            answer += Math.min(charName[i] - 'A', 'Z' - charName[i] +1);
        }

        for(int i=0; i<count; ++i){ // 이동 거리
            int left = 0, right = 0;
            int leftCount = 0, rightCount = 0;

            int idx = now;
            while(leftCount < charName.length){
                if(idx == isFinish.length) idx = 0;
                if(isFinish[idx] || charName[idx] == 'A'){
                    idx++;
                    leftCount++;
                }else if(!isFinish[idx]){
                    right = idx;
                    break;
                }
            }
            idx = now;
            while(rightCount < charName.length){
                if(idx == -1) idx = isFinish.length-1;
                if(isFinish[idx] || charName[idx] == 'A'){
                    idx--;
                    rightCount++;
                }else if(!isFinish[idx]){
                    left = idx;
                    break;
                }
            }

            if(leftCount >= rightCount){
                answer += rightCount;
                now = right;
            }else{
                answer += leftCount;
                now = left;
            }
            isFinish[now] = true;
        }
        return answer;
    }
}
```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
