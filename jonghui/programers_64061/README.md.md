# 프로그래머스 64061 크레인 인형뽑기 게임

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64061)

## 1. 설계 로직

1. moves를 하나씩 확인하면서
   1. 해당 열에 인형이 존재하고
   2. basket이 비어있지 않고, 가장 위에 담긴 인형과 같은 인형이면 2점 획득
   3. basket이 비어있으면 인형을 담기

- 시간복잡도: 최대 30x1000

## 2. 코드

```python
board = [[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]]
moves = [1,5,3,5,1,2,1,4]
def solution(board, moves):
    answer = 0
    basket = []
    for move in moves:
        for line in board:
            if line[move-1] != 0:
                if len(basket) and basket[-1] == line[move-1]:
                    line[move-1] = 0
                    basket.pop()
                    answer += 2
                else:
                    basket.append(line[move-1])
                    line[move-1] = 0
                break
        
    return answer

solution(board, moves)
```

## 3. 후기

- 주어진 제한사항이 적어서 효율 생각 안하고 풀었습니다

