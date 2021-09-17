# 프로그래머스 43163 단어 변환

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/43163)

## 1. 설계 로직

1. dfs를 이용한 완탐을 이용해 구했다.
2. words를 다양하게 조합해 target과 일치할 때, 거쳐온 단어 개수의 최소값을 구했다.

- 시간복잡도 : N^2

## 2. 코드

```java
public class Solution {

    int answer = Integer.MAX_VALUE;
    boolean[] v;
    public int solution(String begin, String target, String[] words) {
        v = new boolean[words.length];
        dfs(begin, target, words, 0);
        if(answer == Integer.MAX_VALUE) return 0;
        return answer;
    }

    public void dfs(String now, String target, String[] words, int count){
        if(now.equals(target)){
            answer = Math.min(answer, count);
            return;
        }

        for(int i=0; i<words.length; ++i){
            if(!v[i] && check(now, words[i])){
                v[i] = true;
                dfs(words[i], target, words, count+1);
                v[i] = false;
            }
        }
    }

    public boolean check(String now, String target){
        int count = 0;
        for(int i=0; i<now.length(); ++i){
            if(now.charAt(i) != target.charAt(i)) count++;
        }
        if(count == 1) return true;
        return false;
    }

}
```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
