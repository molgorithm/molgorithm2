def solution(orders, course):
    answer = []
    menu = dict()
    for order in orders:
        comb = []
        for o in sorted(order):
            len_comb = len(comb)
            for c in comb[:len_comb]:
                comb.append(c+o)
            comb.append(o)
        for com in comb:
            if len(com) in course:
                if com in menu:
                    menu[com] += 1
                else:
                    menu[com] = 1
    
    times = dict()
    for key, val in menu.items():
        if val > 1:
            if len(key) in times:
                if val > times[len(key)][0]:
                    times[len(key)] = [val, key]
                elif val == times[len(key)][0]:
                    times[len(key)] += [key]
                pass
            else:
                times[len(key)] = [val, key]
    for value in times.values():
        answer+=value[1:]
    return sorted(answer)