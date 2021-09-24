# 프로그래머스 43165 타겟 넘버

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/43165)

## 1. 설계 로직

1. dfs를 활용, 각각 +,-를 케이스를 모두 탐색한다.

- 시간복잡도 : N^2

## 2. 코드

```java
class Solution {
    int answer = 0;
    public int solution(int[] numbers, int target) {

        dfs(numbers, target, 0, 0);
        return answer;
    }

    public void dfs(int[] numbers, int target, int idx, int sum){
        if(idx == numbers.length){
            if(sum == target) answer++;
            return;
        }

        dfs(numbers, target, idx+1, sum + numbers[idx]);
        dfs(numbers, target, idx+1, sum - numbers[idx]);
    }
}
```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
