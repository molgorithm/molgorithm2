# 백준 2250 트리의 너비와 높이

[문제 링크](https://www.acmicpc.net/problem/2250)

## 1. 설계 로직

1. is_root라는 배열을 만들고 입력을 받으면서 자식 노드로 나온 노드들은 False 값으로 바꾼다. -> 배열을 이용해 root 찾음
2. 문제에서 나온 트리의 x축 순서는 중위순회 순서이므로 해당 순서를 column_idx에 저장한다. (key: x 좌표, val: 노드번호)
3. height_lst에 y축 기준으로 저장한다. (key: y축 값, val: 해당 행에 존재하는 노드 배열)
4. y축 기준으로 한 층 씩 너비를 확인하여 최대 너비와 해당 층 값을 구한다.

- 시간복잡도 : O(N)

## 2. 코드

```python
import sys

input = sys.stdin.readline

class Node:
    def __init__(self, val, left, right):
        self.val = val
        self.left = left
        self.right = right


tree = {}
N = int(input())
is_root = [True] * (1+N)
root = 0
for _ in range(N):
    val, left, right = map(int, input().split())
    node = Node(val, left, right)
    tree[val] = node
    if left >= 0:
        is_root[left] = False
    if right >= 0:
        is_root[right] = False

for idx, val in enumerate(is_root):
    if idx == 0:
        continue
    if val:
        root = idx
        break

column_idx = {}
def inorder(node):
    _left, _right, _val = node.left, node.right, node.val
    if _left != -1:
        inorder(tree[_left])
    now_idx = len(column_idx.keys())
    column_idx[_val] = now_idx
    if _right != -1:
        inorder(tree[_right])


height_lst = {i: [] for i in range(N+1)}
def check_height(height, node):
    height_lst[height].append(node.val)
    _left = node.left
    _right = node.right
    if _left >= 0:
        left = tree[_left]
        check_height(height + 1, left)
    if _right >= 0:
        right = tree[_right]
        check_height(height + 1, right)


inorder(tree[root])
check_height(1, tree[root])

_max_val = -1
_max_height = -1
for _height in sorted(height_lst.keys()):
    if not height_lst[_height]:
        continue
    _now_min = 1e9
    _now_max = -1
    for idx in height_lst[_height]:
        _now_min = min(_now_min, column_idx[idx])
        _now_max = max(_now_max, column_idx[idx])
    if _max_val < (_now_max - _now_min):
        _max_height = _height
        _max_val = _now_max - _now_min

print(_max_height, _max_val+1)
```

## 3. 후기

readme를 오랜만에 작성하네요 껄껄
