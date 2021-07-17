# 90도 시계 방향 회전
def rot(key):
    N = len(key)
    new_key = [[0] * N for i in range(N)]
    for y in range(N):
        for x in range(N):
            new_key[y][x] = key[N - x - 1][y]
    return new_key


def solution(key, lock):
    N = len(key)
    M = len(lock)
    key_len = 0
    lock_len = 0
    for y in range(N):
        for x in range(N):
            if key[y][x] == 1:
                key_len += 1
    for y in range(M):
        for x in range(M):
            if lock[y][x] == 0:
                lock_len += 1

    if key_len < lock_len:
        return False

    if M == N and lock_len == key_len:
        # 회전만 하기
        for i in range(4):
            key = rot(key)
            cnt = 0
            for y in range(M):
                for x in range(M):
                    if key[y][x] != lock[y][x]:
                        cnt += 1
                    else:
                        cnt = 0
            if cnt == (M * M):
                return True
        return False

    else:
        big_lock = [[2] * ((N - 1) * 2 + M) for i in range((N - 1) * 2 + M)]
        for y in range(M):
            for x in range(M):
                big_lock[y + N - 1][x + N - 1] = lock[y][x]

        # 회전
        for i in range(4):
            key = rot(key)
            for k in range(len(big_lock) - N + 1):
                for t in range(len(big_lock) - N + 1):
                    open_key = 0
                    cnt = 0
                    for y in range(N):
                        for x in range(N):
                            if key[y][x] != big_lock[y + k][x + t]:
                                cnt += 1
                                if key[y][x] == 1 and big_lock[y + k][x + t] == 0:
                                    open_key += 1
                            elif key[y][x] == 1 and big_lock[y + k][x + t] == 1:
                                cnt = 0
                            else:
                                cnt += 1
                    if cnt == (N * N) and open_key == lock_len:
                        return True
    return False

print(solution([[0, 0, 0], [1, 0, 0], [0, 1, 1]], [[1, 1, 1], [1, 1, 0], [1, 0, 1]]))
print(solution([[0, 0, 0, 0], [0, 1, 0, 0], [0, 1, 0, 0], [0, 0, 0, 0]], [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 1, 0], [0, 0, 0, 1]]))
print(solution([[1, 0, 0], [0, 1, 0], [0, 1, 0]], [[1, 1, 1, 1], [1, 1, 1, 0], [1, 0, 0, 1], [1, 1, 1, 1]]))