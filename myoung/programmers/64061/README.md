# 프로그래머스 64061 크레인 인형뽑기 게임

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64061)

## 1. 설계 로직

1. 처음에 주어진 board를 순회하여 열마다 인형의 높이가 얼마인지 확인합니다. 그리고 해당 값을 담아줍니다.
2. moves를 하나씩 확인하면서 
   1. 해당 숫자 열에 인형이 존재한다면  
   2. 인형을 뽑고 bucket에 담습니다.
      1. 담기 전, bucket의 맨 위에 담긴 값 (-1인덱스)이 현재 인형과 동일한지 확인하고, 
      2. 동일할 경우 bucket에 담지 않고, 기존의 맨 위 값도 pop해주고, 점수를 +2해줍니다.
   3. 그리고 해당 열의 높이를 하나 낮춰줍니다.



- 시간복잡도: O(N^2) : 처음에 scan_board에서 2차 배열을 순회합니다요 중간에 break가 있긴 하지만 끝까지 가는 경우가 있을 수 있으므로 O(N^2)이라고 생각했습니다.

## 2. 코드

```python
def solution(board, moves):
    answer = 0
    bucket = []

    def scan_board(_board):
        item_idx = dict()
        for _x in range(len(_board)):
            find_idx = False
            for _y in range(len(_board[_x])):
                if _board[_y][_x] > 0:
                    find_idx = True
                    item_idx[_x] = _y
                    break
            if find_idx:
                continue
            if y == len(_board[_x]) - 1 and not find_idx:
                item_idx[_y] = -1
        return item_idx

    now_item = scan_board(board)
    for order in moves:
        y = order-1
        if now_item[y] != -1 and board[now_item[y]][y] != 0:
            item = board[now_item[y]][y]
            board[now_item[y]][y] = 0
            now_item[y] += 1 if now_item[y] + 1 < len(board) else -1

            if bucket and bucket[-1] == item:
                answer += 2
                bucket.pop()
            else:
                bucket.append(item)
    return answer


print(solution([[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]], [1,5,3,5,1,2,1,4]))
```



## 3. 후기

- 처음에 주어진 배열의 행, 열을 반대로 봐서 헷갈렸습니다.