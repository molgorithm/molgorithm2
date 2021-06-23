# 프로그래머스 64062 징검다리 건너기

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64062)

## 1. 설계 로직

1. 이분탐색을 사용합니다.
   1. 처음 left값은 1, right값은 디딤돌에 적힌 숫자 중 최댓 값인 200000000입니다.
   2. left와 right의 중간 값인 middle값을 구해줍니다.
   3. middle값이 인원이라고 생각하고 디딤돌을 각각 middle로 빼줍니다
   4. 만약 `디딤돌 - middle`이 음수인 값이 k개 이상이라면, 구하고자 하는 답이 왼쪽에 존재하므로 right를 middle-1값으로 바꾸어 다시 middle을 구해줍니다.



- 시간복잡도: O(nlogm) n은 디딤돌의 갯수, m은 디딤돌에 적힌 숫자의 최댓값

## 2. 코드

```python
def solution(stones, k):
    left, right = 1, 200000000

    while left <= right:
        mid = (left + right) // 2
        tmp = stones[:]
        for i in range(len(tmp)):
            tmp[i] -= mid

        cnt = 0
        is_answer = False
        for i in range(len(tmp)):
            if tmp[i] <= 0:
                cnt += 1
            else:
                cnt = 0

            if cnt >= k:
                is_answer = True
                break

        if is_answer:
            right = mid - 1
        else:
            left = mid + 1

    return left


print(solution([2, 4, 5, 3, 2, 1, 4, 2, 5, 1], 3))
```



## 3. 후기

- 처음에 디딤돌의 최댓값이 2억이길래 O(n^2)까지 가면 안되겠다는 생각을 했습니다. 

  슬라이딩 윈도우로 접근하려고 했는데, 좀 아닌거 같아서 음 ~! 어떻게하지 ~! 했었습니다.

  그래서 서치해 본 결과 `이분탐색`을 써야한다고 해서 이렇게 풀어보았습니다. 

  제가 참고한 사이트는 여기입니다요 ~!
  https://covenant.tistory.com/162