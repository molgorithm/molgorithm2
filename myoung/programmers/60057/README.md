# 프로그래머스 60057 문자열 압축

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/60057)

## 1. 설계 로직

1. 1개부터 (문자열의 길이//2)까지 문자열을 토막내서 압축 가능 여부를 확인합니다
2. 압축이 가능한 경우 압축시켜 새로운 문자열에 담아줍니다.

3. 만들어진 문자열 중 가장 짧은 문자열의 길이를 반환합니다.



- 시간복잡도 : O(N^2) 
- 문자열의 길이 N의 반만큼 반복하는데, 반복할 때마다 N개의 문자열을 모두 확인하기 때문에 O(N^2)입니다.

## 2. 코드

```python
def solution(s):
    if len(s) == 1:
        return 1
    answer = 1000
    for word_len in range(1, len(s)//2+1):
        prev = s[:word_len]
        cnt = 1
        temp_word = ''
        for j in range(word_len, len(s)+word_len, word_len):
            now = s[j:j+word_len]
            if prev == now:
                cnt += 1
            elif prev != now and cnt > 1:
                temp_word += (str(cnt)+prev)
                cnt = 1
                prev = now
            else:
                temp_word += prev
                prev = now
        if cnt > 1:
            temp_word += (str(cnt)+prev)
        answer = min(len(temp_word), answer)
    return answer
```



## 3. 후기

