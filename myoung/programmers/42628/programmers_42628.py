import heapq

def solution(operations):
    max_q, min_q = [], []
    
    def del_item(item, _type):
        nonlocal max_q, min_q
        if _type == 'max':
            min_q.remove(-item)
        else:
            max_q.remove(-item)
    
    for operation in operations:
        if operation[0] == 'I':
            nums = int(operation[2:])
            heapq.heappush(max_q, -nums)
            heapq.heappush(min_q, nums)
        elif operation == 'D 1':
            if max_q:
                item = heapq.heappop(max_q)
                del_item(item, 'max')
        else:
            if min_q:
                item = heapq.heappop(min_q)
                del_item(item, 'min')
    
    return[-max_q[0], min_q[0]] if max_q and min_q else [0, 0]