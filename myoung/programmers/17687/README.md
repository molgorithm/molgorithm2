# 프로그래머스 17687 n진수 게임

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/17687)

## 1. 설계 로직

1. 숫자를 n진법으로 바꿀 수 있는 함수가 필요합니다.
2. `게임에 참가하는 인원 * 미리 구할 숫자의 갯수` 크기의 배열을 하나 만들어서 진법으로 나타낸 숫자를 넣어줍니다.
3. 튜브의 순서에 맞춰 말해야하는 숫자를 answer에 담아줍니다.



- 시간복잡도: O(`N*logN`) : N은 m*t 배열에 들어갈 수 있는 최대 숫자(십진법 기준)입니다. logN이 붙은 이유는 n진법으로 나타낼 때 logN으로 나타낼 수 있기 때문입니다.(로그의 밑은 2~16 가능)

## 2. 코드

```python
def dec2num(d, n):
    dct = {
        10: 'A',
        11: 'B',
        12: 'C',
        13: 'D',
        14: 'E',
        15: 'F'
    }
    _num = ''
    if d == 0:
        return '0'
    while d > 0:
        d, _namuji = divmod(d, n)
        if _namuji >= 10:
            _namuji = dct[_namuji]
        else:
            _namuji = str(_namuji)
        _num = _namuji + _num
    return _num


def solution(n, t, m, p):
    nums = [None] * (m*t)
    val = 0
    idx = 0

    while not nums[-1]:
        now_val = dec2num(val, n)
        if idx + len(now_val) >= m*t:
            for v in range((m*t)-idx):
                nums[idx+v] = now_val[v]
            break
        for v in range(len(now_val)):
            nums[idx+v] = now_val[v]
        idx += len(now_val)
        val += 1
    answer = ''
    for i in range(t):
        answer += nums[(i*m)+p-1]
    return answer
```



## 3. 후기

- 시간 복잡도가 이게 맞는지 모르겠슴니다 ^-^ 