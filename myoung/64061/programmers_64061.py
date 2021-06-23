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

