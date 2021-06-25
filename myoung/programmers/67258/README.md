# 프로그래머스 67258 보석 쇼핑

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/67258)

## 1. 설계 로직

0. 투포인터를 이용해서 풀이를 진행했습니다.

1. 오른쪽으로 가다가 visited의 key 값이 모든 종류의 보석이 되었을 때, 

   left를 오른쪽으로 움직이면서 범위를 줄여나갑니다.

- 시간복잡도 : O(N) 
  - 투포인터의 시간 복잡도는 O(N)입니다.

## 2. 코드

```python
def solution(gems):
    answer = []
    visited = {}

    type_nums = len(set(gems))
    min_dist = len(gems)+1

    _left, _right = 0, 0

    while _right < len(gems):

        if gems[_right] in visited:
            visited[gems[_right]] += 1
        else:
            visited[gems[_right]] = 1

        _right += 1

        if len(visited) == type_nums:
            while _left < _right:
                if visited[gems[_left]] > 1:
                    visited[gems[_left]] -= 1
                    _left += 1

                elif min_dist > _right - _left:
                    min_dist = _right - _left
                    answer = [_left + 1, _right]
                    break

                else:
                    break

    return answer
```



## 3. 후기

- 처음에는 슬라이딩 윈도우로 접근했다가 엄청난 `시간초과`를 맞이했습니다.

  그래서 투포인터로 바꿔서 접근을 했습니다.

- 딕셔너리 데이터 삽입 및 삭제 

  https://wikidocs.net/2857

- https://ssungkang.tistory.com/entry/Algorithm-Two-Pointers-%ED%88%AC-%ED%8F%AC%EC%9D%B8%ED%84%B0 : 투포인터의 시간 복잡도