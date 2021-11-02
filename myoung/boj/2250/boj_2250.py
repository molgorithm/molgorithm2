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
