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

