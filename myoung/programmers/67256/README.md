# 프로그래머스 67256 키패드 누르기

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/67256)

## 1. 설계 로직

1. 키패드를 오른쪽, 왼쪽으로 나눠서 각각의 숫자를 key로 높이를 value로 해서 dict를 하나 만들어줍니다.

2. 현재 왼손 위치, 오른손 위치 정보를 가지고 있는 변수를 만들어줍니다.

3. 주어진 숫자를 하나씩 누르면서, 

   왼쪽 열이라면 왼쪽 손가락의 위치 정보를 갱신하고

   오른쪽 열이라면 오른쪽 손가락의 위치 정보를 갱신하고

   가운데 열이라면 현재 손가락들의 위치와 비교하여 dist가 더 짧은 손가락의 위치정보를 갱신합니다.



- 시간복잡도 : O(N)

## 2. 코드

```python
def solution(numbers, hand):
    left_pos, right_pos = [0, 3], [2, 3]
    left_lst = {
        1: 0,
        4: 1,
        7: 2
    }
    right_lst = {
        3: 0,
        6: 1,
        9: 2
    }
    center_lst = {
        2: 0,
        5: 1,
        8: 2,
        0: 3
    }
    answer = ''
    for num in numbers:
        if num in left_lst:
            left_pos = [0, left_lst[num]]
            answer += 'L'
        elif num in right_lst:
            right_pos = [2, right_lst[num]]
            answer += 'R'
        else:
            goal = [1, center_lst[num]]
            _left = abs(left_pos[0] - goal[0]) + abs(left_pos[1] - goal[1])
            _right = abs(right_pos[0] - goal[0]) + abs(right_pos[1] - goal[1])
            if _left > _right:
                right_pos = goal
                answer += 'R'
            elif _left < _right:
                left_pos = goal
                answer += 'L'
            else:
                if hand == 'right':
                    right_pos = goal
                    answer += 'R'
                else:
                    left_pos = goal
                    answer += 'L'

    return answer
```



## 3. 후기

- 거리를 어떻게 계산할지 고민했다가 딕셔너리를 사용해서 넣었습니다.
- 다른 분들의 풀이를 보니 x, y좌표 모두를 딕셔너리에 담은 분도 계셔서 놀라웠스빈다 ㄴㅇㄱ