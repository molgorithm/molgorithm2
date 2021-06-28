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


    print(answer)
    return answer

solution([[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]], [1,5,3,5,1,2,1,4])


