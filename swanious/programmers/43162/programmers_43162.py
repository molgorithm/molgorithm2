def dfs(start, computers, visit):
    if start in visit:
        return 0, visit
    # visit에 방문체크
    visit.add(start)
    
    # 재귀(인접 리스트)
    for i in range(len(computers[start])):
        # 자신의 위치거나 연결되지 않은 노드의 경우 continue
        if i == start or not computers[start][i]: continue
            
        # 연결됐고 방문을 안했으면 재귀
        if i not in visit:
            dfs(i, computers, visit)
    
    # 섬의 개수 반환
    return 1, visit

def solution(n, computers):
    answer = 0
    visit = set()
    for start in range(n):
        count, visit = dfs(start, computers, visit)
        answer += count
    return answer