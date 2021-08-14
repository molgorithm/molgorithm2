# 프로그래머스 72411 메뉴리뉴얼

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/72411)

## 1. 설계 로직

1. 손님별로 주어진 메뉴로 모든 조합을 만듬
2. 딕셔너리에 추가 시 없으면 1 있으면 +1 해서 중복 체크
3. 코스에 맞는 조합 별로 최대 값(중복 포함)하여 딕셔너리에 추가
4. 정렬한 값 리턴

- 시간복잡도 숫자가 작아서 계산안함

## 2. 코드

```python
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
```



## 3. 후기

- 출력 값이 뭔지 모르고 무지성 풀이하다가 2보다 큰 것을 출력하고 왜 틀렸지 했습니다

