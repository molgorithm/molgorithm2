def check(answer):
    for x, y, a in answer:
        if a:
            if (x,y-1,0) in answer or (x+1, y-1, 0) in answer or ((x-1,y,1) in answer and (x+1,y,1) in answer):
                continue
            else:
                return False
        else:
            if y==0 or (x,y-1,0) in answer or (x-1,y,1) in answer or (x,y,1) in answer:
                continue
            else:
                return False
    return True

def solution(n, build_frame):
    answer = set()
    for x,y,a,b in build_frame:
        if b:
            answer.add((x,y,a))
            if not check(answer):
                answer.remove((x,y,a))
        else:
            answer.remove((x,y,a))
            if not check(answer):
                answer.add((x,y,a))
    answer = list(map(list, answer))
    
    return sorted(answer, key=lambda x:(x[0], x[1], x[2]))