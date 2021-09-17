def solution(m, n, puddles):
    mapp = [[1] * m for _ in range(n)]
    for x, y in puddles: # 첫행, 첫열에 함정이 있으면 그곳부터 끝까지 갈 수 없음(우측, 아래로만 움직일 수 있으므로)
        mapp[y-1][x-1] = 0
        if y-1 == 0:
            for k in range(x-1, m):
                mapp[0][k] = 0
        if x-1 == 0:
            for k in range(y-1, n):
                mapp[k][0] = 0
        
    for i in range(1, n):
        for j in range(1, m):
            if mapp[i][j] != 0:
                mapp[i][j] = mapp[i-1][j] + mapp[i][j-1]
    
    return mapp[n-1][m-1]  % 1000000007