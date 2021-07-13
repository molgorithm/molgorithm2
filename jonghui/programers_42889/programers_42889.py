def solution(N, stages):
    fail = dict()
    for n in range(1,N+1):
        fail[n] = 0
    S = len(stages)
    for s in stages:
        if s <= N:
            fail[s] += 1
    for key, value in fail.items():
        if value:
            new_value = value/S
            S -= value
            fail[key] = new_value
    answer = sorted(fail, key=lambda x:fail[x], reverse=True)
    return answer