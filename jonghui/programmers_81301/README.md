# 프로그래머스 81301 숫자 문자열과 영단어

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/81301)

## 1. 설계 로직

1. 문자열을 차례대로 읽으며 문자열에 해당하는 조건 찾으면 조건만큼 건너뛰고 숫자로 반환



- 시간복잡도 s

## 2. 코드

```python
def solution(s):
    i = 0
    len_s = len(s)
    result = ''
    while i < len_s:
        if s[i] == 'z':
            i+=4
            result+='0'
        elif s[i] == 'o':
            i+=3
            result+='1'
        elif s[i] == 't':
            if s[i+1] == 'w':
                i+=3
                result+='2'
            else:
                i+=5
                result+='3'
        elif s[i] == 'f':
            if s[i+1] == 'o':
                i+=4
                result+='4'
            else:
                i+=4
                result+='5'
        elif s[i] == 's':
            if s[i+1] == 'i':
                i+=3
                result+='6'
            else:
                i+=5
                result+='7'
        elif s[i] == 'e':
            i+=5
            result+='8'
        elif s[i] == 'n':
            i+=4
            result+='9'
        else:
            result+=s[i]
            i+=1
            
    return int(result)
```



## 3. 후기

- ...

