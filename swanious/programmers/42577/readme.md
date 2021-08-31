# 프로그래머스 42577 전화번호 목록

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42577)

## 1. 설계 로직

1. `sort()` 로 ascii 순 정렬(이렇게 되면 반복문 하나로 앞뒤만 검사해주면 됨)
   - [1, 13, 12, 10000, 11] -> [1, 10000, 11, 12, 13]
2. 반복문으로 앞뒤만 검사

## 2. 코드

```python
import re

def solution(phone_book):
    phone_book.sort()
    for i in range(len(phone_book) - 1):
        # if re.search(f"^{phone_book[i]}", phone_book[i+1]):
        #     return False
        # if re.match(phone_book[i], phone_book[i+1]):
        #     return False
        if phone_book[i+1].startswith(phone_book[i]):
            return False

    return True
```

## 3. 후기

- `import re`로 모듈불러와서 정규표현식 사용했는데 효율성 2개 실패한다.
  - `re.match(a, b)`, `re.search(^a, b)` 둘다 시간초과
  - 참고 
    - `re.match()`는 문자열의 시작 부분에서만 일치 검사
    - `re.search()`는 아무곳에서 찾기때문에 찾는 문자열에 `^`가 붙여줘야함
- `startswith()` 함수 쓰니까 바로 풀렸다!

