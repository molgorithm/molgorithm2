# 프로그래머스 81301 숫자 문자열과 영단어

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/81301)

## 1. 설계 로직

1. 주어진 문자 중 숫자를 의미하는 문자에 해당하는 부분만 숫자로 replace해줍니다.



- 시간복잡도: 모르겠슴다 ㅇ<-<

## 2. 코드

```python
import re

def solution(s):
    answer = 0
    str_to_num = {
        'one': '1',
        'two': '2',
        'three': '3',
        'four': '4',
        'five': '5',
        'six': '6',
        'seven': '7',
        'eight': '8',
        'nine': '9',
        'zero': '0'
    }
    for string in str_to_num.keys():
        s = re.sub(string, str_to_num[string], s)
    return int(s)
```

## 3. 후기

- 요즘 정규표현식에 빠져있기두 하고 주어지는 s의 길이가 최대 50이기 때문에 정규표현식을 사용해도 되겠다 싶어서 사용했습니다.



- 근데 제출하고 보니 re안쓰고도 replace가 되네요

  ```python
  def solution(s):
      answer = 0
      str_to_num = {
          'one': '1',
          'two': '2',
          'three': '3',
          'four': '4',
          'five': '5',
          'six': '6',
          'seven': '7',
          'eight': '8',
          'nine': '9',
          'zero': '0'
      }
      for string in str_to_num.keys():
          s = s.replace(string, str_to_num[string])
      return int(s)
  ```

  