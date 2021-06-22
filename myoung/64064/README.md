# 프로그래머스 64064 불량 사용자

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64064)

## 1. 설계 로직

1. 총 user 중 banned_id 갯수에 맞춰 가능한 조합을 모두 뽑아낸다.
2. 조합을 하나씩 확인(check_group)하여 해당 그룹이 유효한지 확인한다.
3. 유효하다면 ok_lst에 담는다. 이 때, list(set())을 위해 list가 아닌 정렬한 문자열로 저장한다.
4. 중복제거해주고 갯수를 구한다.



- 시간복잡도: ㅠㅠ

## 2. 코드

```python
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
```



## 3. 후기

```python
def solution(user_id, banned_id):
    global group_lst, banned_lst
    answer = 0
    group_lst = []
    banned_lst = []

    def make_group(group, start, user_id):
        global group_lst
        if start > len(user_id):
            return
        if len(group) >= len(banned_id):
            group_lst.append(group)
            return
        for i in range(start, len(user_id)):
            make_group(group + [user_id[i]], i + 1, user_id)

    def make_banned(visited, lst, val):
        global banned_lst
        if val is None:
            val = []
        if sum(visited) == len(lst):
            if val not in banned_lst:
                banned_lst.append(val)
            return
        for i in range(len(lst)):
            if not visited[i]:
                visited[i] = True
                make_banned(visited, lst, val + [lst[i]])
                visited[i] = False

    def check_group(group, now_ban_lst):
        for i in range(len(now_ban_lst)):
            if len(now_ban_lst[i]) != len(group[i]):
                return False
            else:
                for j in range(len(group[i])):
                    if now_ban_lst[i][j] != '*' and group[i][j] != now_ban_lst[i][j]:
                        return False
        return True

    for i in range(len(user_id)):
        make_group([user_id[i]], i+1, user_id)
    make_banned([False]*len(banned_id), banned_id, [])
    for group in group_lst:
        is_ok = False
        for ban in banned_lst:
            if is_ok:
                break
            if not is_ok and check_group(group, ban):
                answer += 1
                is_ok = True
    return answer
```
이렇게 했더니 시간초과가 났습니다.
```python
class NODE:
    def __init__(self):
        self.children = {}
        self.endOfWord = False

def inserValue(root,id):
    curNode = root
    for char in id:
        if char not in curNode.children:
            curNode.children[char] = NODE()
        curNode = curNode.children[char]
    curNode.endOfWord = True

def FindPossibleValues(bId, rootNode):
    initChar = bId[0]
    curStack = []  # 원소 노드,여태까지 문자
    if initChar == "*":
        for key in rootNode.children:
            curStack.append((key, rootNode.children[key]))
    else:
        curStack.append((initChar, rootNode.children[initChar]))

    for char in bId[1:]:
        nxtStack = []
        if char == '*':
            while curStack:
                subString, curNode = curStack.pop()
                for key in curNode.children:
                    nxtStack.append((subString + key, curNode.children[key]))
        else:
            while curStack:
                subString, curNode = curStack.pop()
                if char not in curNode.children:
                    continue
                nxtStack.append((subString + char, curNode.children[char]))

        curStack = nxtStack

    possibleValues = []
    for uid,node in curStack:
        possibleValues.append(uid)

    return possibleValues

def solution(user_id, banned_id):
    answer = 0
    rootNode = NODE()

    for uId in user_id:
        inserValue(rootNode,uId)

    banned_id = banned_id
    user_id = set(user_id)

    rst = set()
    def CountAnswer(user_id,banned_id,rootNode):
        nonlocal answer
        if not banned_id:
            user_id = list(user_id)
            user_id.sort()
            rst.add(tuple(user_id))
            return

        bId = banned_id.pop()
        possibleValues = FindPossibleValues(bId,rootNode)

        for possibleUId in possibleValues:
            if possibleUId not in user_id:
                continue
            CountAnswer(user_id-{possibleUId},banned_id.copy(),rootNode)

    CountAnswer(user_id,banned_id,rootNode)

    answer = len(rst)
    return answer
```
프로그래머스에서 클래스 사용하려면 이렇게 하면 된다는 것을 깨달았습니다.

