def solution(user_id, banned_id):
    global group_lst, visited
    group_lst = []
    ok_lst = []
    visited = [False] * len(user_id)

    def make_group(group, user_id):
        global group_lst, visited
        if len(group) >= len(banned_id):
            group_lst.append(group[:])
            return
        for i in range(len(user_id)):
            if not visited[i]:
                visited[i] = True
                make_group(group + [user_id[i]], user_id)
                visited[i] = False

    def check_group(group):
        for i in range(len(banned_id)):
            if len(banned_id[i]) != len(group[i]):
                return False
            else:
                for j in range(len(group[i])):
                    if banned_id[i][j] != '*' and group[i][j] != banned_id[i][j]:
                        return False
        return True

    make_group([], user_id)
    for group in group_lst:
        if check_group(group):
            ok_lst.append(''.join(sorted(group)[:]))
    ok_lst = list(set(ok_lst))
    return len(ok_lst)

print(solution(["frodo", "fradi", "crodo", "abc123", "frodoc"], ["fr*d*", "*rodo", "******", "******"]))
