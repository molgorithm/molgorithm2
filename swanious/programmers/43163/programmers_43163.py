import sys

answer = sys.maxsize

def checkDiffOneWord(str1, str2):
    cnt = 0
    for i in range(len(str1)):
        if str1[i] != str2[i]:
            cnt += 1
    return cnt == 1


def dfs(start, count, target, visit, words):
    global answer

    if start == target:
        answer = min(count, answer)
        return True

    for i in range(len(words)):
        if not visit[i] and checkDiffOneWord(start, words[i]):
            visit[i] = True
            if dfs(words[i], count + 1, target, visit, words): return True
            visit[i] = False
            count -= 1

    return False


def solution(begin, target, words):
    l = len(words)
    visit = [False] * len(words)
    
    return 0 if not dfs(begin, 0, target, visit, words) else answer