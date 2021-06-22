def solution(s):
    answer = []
    s = s[2:-2].split('},{')
    s.sort(key=lambda x: len(x))
    prev = []
    for i in range(len(s)):
        now = list(map(int, s[i].split(',')))
        val = [x for x in now if x not in prev]
        prev = now
        answer.append(val[0])
    return answer

print(solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"))

