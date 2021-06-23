s = "{{1,2,3},{2,1},{1,2,4,3},{2}}"
def solution(s):
    answer = []
    tuples = list(map(str, s[2:-2].split('},{')))
    N = len(tuples)
    temp = [{} for _ in range(N)]
    for t in tuples:
        toint = set(map(int, t.split(',')))
        temp[len(toint)-1] = toint
    answer += list(temp[0])
    for n in range(N-1):
        answer += list(temp[n+1]-temp[n])
    return answer

solution(s)