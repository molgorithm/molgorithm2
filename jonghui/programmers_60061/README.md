# 프로그래머스 60061 기둥과 보 설치

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/60061)

## 1. 설계 로직

1. 기둥 - 바닥위인지 보의 한쪽 끝 위인지 다른기둥 위인지 체크
2. 보 - 한쪽 끝이 기둥 위인지 또는 양쪽 끝이 다른 보인지 체크
3. 기둥과 보를 순서대로 만들고 가능한지 체크 후 아니면 되돌림



- 시간복잡도 N^2 (1000번 다 생성일 경우 1000X1001X1/2)

## 2. 코드

```python
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
```



## 3. 후기

- 처음에 설치와 삭제 모두 조건 걸어서 board라는 이차원배열에 만들려고 했는데 삭제 시 조건이 너무 까다롭고, 코드한번 날려먹어서 인터넷 검색해서 접근법 확인해보고 효율성은 떨어지지만 좋은 방법인 것 같아서 참고해서 작성했습니다.
- 마지막 answer를 set(tuple, tuple) 이런형식인데 [map(list, answer)]는 안되는데 list(map(list, answer))는 됩니다?

