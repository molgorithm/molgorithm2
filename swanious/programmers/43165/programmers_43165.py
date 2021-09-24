from collections import deque

def solution(numbers, target):
    idx, ans = 0, 0
    q = deque()
    q.append([-numbers[idx], numbers[idx]])
    idx += 1

    while q:
        arr = q.popleft()
        if idx != len(numbers): # 숫자의 개수만큼 탐색
            temp = []
            for number in arr:
                temp.append(number - numbers[idx])
                temp.append(number + numbers[idx])
            q.append(temp)
            idx += 1
        else: # 탐색 끝
            for number in arr:
                if number == target: ans += 1

    return ans