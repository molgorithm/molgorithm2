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

