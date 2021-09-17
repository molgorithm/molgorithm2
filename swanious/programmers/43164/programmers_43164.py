def solution(tickets):
    l = len(tickets)
    visit = [False] * l
    ans = []
    
    tickets.sort()
    
    def dfs(start, cnt):
        global answer
        ans.append(start)
        
        if cnt == l: 
            return True
        
        for i in range(l):
            if not visit[i] and tickets[i][0] == start:
                visit[i] = True
                if dfs(tickets[i][1], cnt + 1): return True
                visit[i] = False
                
        ans.pop()
        return False
    
    dfs('ICN', 0)
    return ans