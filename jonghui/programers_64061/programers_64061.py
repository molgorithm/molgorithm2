

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
