import re


def make_orders(goal, val):
    global order_lst, total_orders
    if len(val) == goal:
        order_lst.append(val)
        return
    for i in range(goal):
        if total_orders[i] not in val:
            make_orders(goal, val + [total_orders[i]])


def solution(expression):
    global order_lst, total_orders

    answer = 0
    nums = re.split(r"-|\*|\+", expression)
    orders = re.findall("[^0-9]", expression)
    total_orders = list(set(orders))
    order_lst = []
    make_orders(len(total_orders), [])

    for m_order in order_lst:
        temp_nums = nums[:]
        temp_orders = orders[:]
        for now_order in m_order:
            i = 0
            while i < len(temp_orders):
                if temp_orders[i] == now_order:
                    if now_order == '+':
                        _temp = int(temp_nums[i]) + int(temp_nums[i + 1])
                    elif now_order == '-':
                        _temp = int(temp_nums[i]) - int(temp_nums[i + 1])
                    elif now_order == '*':
                        _temp = int(temp_nums[i]) * int(temp_nums[i + 1])
                    temp_nums.pop(i)
                    temp_nums.pop(i)
                    temp_nums.insert(i, _temp)
                    temp_orders.pop(i)
                else:
                    i += 1
        answer = max(abs(temp_nums[0]), answer)
    return answer