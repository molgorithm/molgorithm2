# 프로그래머스 42862 체육복

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42862)

## 1. 설계 로직

1. 고려해야할 사항 
   -  전체 학생의 수는 2명 이상 30명 이하
   - 여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다. 이때 이 학생은 체육복을 하나만 도난당했다고 가정하며, 남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.
2. 설계로직
   1. 체크배열을(v) 사용하여 체육복을 도난당한 학생들을 표시.
   2. 여벌 체육복이 있는 학생들의 리스트를 담을 수 있는 HashSet사용
   3. 고려해야할 사항2번째 로직을 처리하기 위해 v배열을 확인하여 HashSet의 정보를 remove
   4. 체육복을 도난당한 학생들의 앞 뒤를 검사하여 빌릴 수 있는지 판단
3. 시간복잡도: O ( n ) 

## 2. 코드

```kotlin
class Solution {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        var answer = n
        val reserveSet: HashSet<Int> = hashSetOf()
        val v = Array(n + 1) { true }

        for (i in lost) {
            v[i] = false
            answer--
        }

        for (idx in reserve.indices) {
            reserveSet.add(reserve[idx])
            if (!v[reserve[idx]]) {
                v[reserve[idx]] = true
                reserveSet.remove(reserve[idx])
                answer++
            }
        }

        for (idx in 1..n) {
            if (!v[idx] && reserveSet.contains(idx - 1)) {
                answer++
                reserveSet.remove(idx - 1);
            } else if (!v[idx] && reserveSet.contains(idx + 1)) {
                answer++
                reserveSet.remove(idx + 1);
            }
        }

        return answer
    }
}
```

## 3. 후기

- 체육복을 읽어버린 학생들도 HashSet으로 관리하면 편하게 구할 수 있음.
- 코틀린으로 풀어봤는데 장점이 많은 언어지만 익숙하지 않아 시간이 오래걸림. 앞으로 문제는 코틀린으로 풀 예정.
- 처음사용해본 언어라 코드가 지저분함.
