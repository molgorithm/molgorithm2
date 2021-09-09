def solution(triangle):
    n = len(triangle)
    for i in range(1, n): # 이전행의 값을 더해주기 위해 1행부터 시작
        for j in range(i + 1):
            if (j == 0): # i행의 첫번째 값
                triangle[i][j] += triangle[i-1][j]
            elif (j == i): # i행의 마지막 값
                triangle[i][j] += triangle[i-1][j-1]
            else: # i행의 중간 값
                triangle[i][j] += max(triangle[i-1][j-1], triangle[i-1][j])
                
    return max(triangle[-1])