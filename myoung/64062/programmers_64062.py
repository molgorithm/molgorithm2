def solution(stones, k):
    left, right = 1, 200000000

    while left <= right:
        mid = (left + right) // 2
        tmp = stones[:]
        for i in range(len(tmp)):
            tmp[i] -= mid

        cnt = 0
        is_answer = False
        for i in range(len(tmp)):
            if tmp[i] <= 0:
                cnt += 1
            else:
                cnt = 0

            if cnt >= k:
                is_answer = True
                break

        if is_answer:
            right = mid - 1
        else:
            left = mid + 1

    return left


print(solution([2, 4, 5, 3, 2, 1, 4, 2, 5, 1], 3))


