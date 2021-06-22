
# 프로그래머스 64061 크레인 인형뽑기 게임

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64061)

## 1. 설계 로직

1. 주어진 **board**의 높이를 구해서 **height**에 그 높이를 저장한다.

2. 반복문을 돌면서 **moves**의 이동에 따라 각 칸의 가장 높은 값을 **temp**에 저장한다.

   2-1. 이동한 칸의 높이가 board의 전체 길이보다 작을 때(인형이 존재할 때) 인형을 뽑는다. 인형을 뽑았다면, 그 칸의 **height** 값을 변경하고 뽑은 인형을 **board**에서 지운다.

   2-2. 그 외(인형이 없을 때)에는 무시한다.

3. 뽑은 인형을 **s**라는 변수에 저장한다.

   3-1. s가 비어있거나, s가 비어있지 않으면서 마지막 인형이 뽑은 인형과 다르다면, 뽑은 인형을 추가한다.

   3-2. s가 비어있지 않고 마지막 인형이 뽑은 인형과 같다면, 마지막 인형을 없앤다.

4. 3-3의 경우 **answer**에 2를 더한다.



- 시간복잡도

## 2. 코드

```python
def solution(board, moves):
    answer = 0
    N = len(board)
    height = [0] *N
    for j in range(N):
        for i in range(N):
            if board[i][j] != 0:
                height[j] = i
                break
    s = []
    for m in moves:
        move = m -1
        if height[move] < N:
            temp = board[height[move]][move]
            if len(s) and s[-1] == temp:
                s.pop(-1)
                answer += 2
            else :
                s.append(temp)
            board[height[move]][move] = 0
            height[move] += 1

    return answer
```



## 3. 후기

- 반복문과 stack을 이용하는 문제였다. 문제를 설계할 때는 반복문을 여러번 돌지 않기 위해서 height를 구하고 시작했는데, 막상 다 풀고 나니까 height를 미리 안구하고 바로 풀어도 될 것 같았다. 설계를 좀 더 신중하게 해봐야 겠다.
