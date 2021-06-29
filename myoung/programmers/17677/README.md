# 프로그래머스 17677 뉴스 클러스터링

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/17677)

## 1. 설계 로직

0. "AB"와 "ab"는 같은 문자 취급하기 때문에, `str1`과 `str2`를 모두 소문자로 만들어서 시작했습니다.
1. 각 문자를 2개씩 쪼개서 dict형으로 만들어 `key`: '문자 이름', `value`: '문자가 등장한 갯수'를 넣어줍니다. 
   - 이 때, 문자의 형식이 올바르지 않은 경우는 제외해줍니다.
2. 각각의 key값들이 등장한 문자이기 때문에, `set` 메서드를 이용하여 교집합(`&`)과 합집합(`|`)을 만들어줍니다.
3. 교집합 요소의 경우 `str1`, `str2` 중 더 적은 count 값을 가지므로, min값을 분자에 더해줍니다
4. 합집합 요소의 경우 `str1`, `str2` 중 등장한 집합에서의 count값을 가지는데, 이 때 두 집합 모두에서 등장한 경우에는 더 많은 count 값을 가지므로, max값을 분모에 더해줍니다.
5. 분자/분모를 해주는데, 나눗셈이 정의되지 않아(zero에러 등) 계산이 불가할 경우는 문제에 정의된 대로 1값을 가지고, 나머지 경우는 연산을 진행하여 답을 도출합니다.



- 시간복잡도: O(N+M) 
  - N은 str1의 길이, M은 str2의 길이입니다.
  - 2개씩 쪼개서 모두 확인해야하기 때문에 N개의 문자열을 2개씩 쪼갤 경우 총 (N-1)개가 되어서 O(N+M)이라고 생각했습니다.

## 2. 코드

```python
import re


def solution(str1, str2):
    str1, str2 = str1.lower(), str2.lower()

    def make_words(s):
        _dict = {}
        p = re.compile('[a-z]{2}')
        for i in range(len(s) - 1):
            _word = s[i:i + 2]
            if p.match(_word):
                if _word in _dict:
                    _dict[_word] += 1
                else:
                    _dict[_word] = 1
        return _dict

    def cal_answer(dict1, dict2):
        set1, set2 = set(dict1.keys()), set(dict2.keys())

        set_a = set1 & set2
        set_b = set1 | set2

        _a, _b = 0, 0
        for i in set_a:
            _a += min(dict1[i], dict2[i])
        for i in set_b:
            if i in dict1 and i in dict2:
                _b += max(dict1[i], dict2[i])
            elif i in dict1:
                _b += dict1[i]
            else:
                _b += dict2[i]
        try:
            answer = int(65536 * (_a / _b))
        except:
            answer = 65536
        return answer

    return cal_answer(make_words(str1), make_words(str2))
```



## 3. 후기

- `match`는 처음 써봐서 https://wikidocs.net/4308#match 를 참고했습니다.
- 교집합, 합집합의 경우 종희님이 저번에 알려주신 `set`메서드를 사용해서 수월하게 풀 수 있었습니다 ~! 굳굳



```python
import re
import math

def solution(str1, str2):
    str1 = [str1[i:i+2].lower() for i in range(0, len(str1)-1) if not re.findall('[^a-zA-Z]+', str1[i:i+2])]
    str2 = [str2[i:i+2].lower() for i in range(0, len(str2)-1) if not re.findall('[^a-zA-Z]+', str2[i:i+2])]

    gyo = set(str1) & set(str2)
    hap = set(str1) | set(str2)

    if len(hap) == 0 :
        return 65536

    gyo_sum = sum([min(str1.count(gg), str2.count(gg)) for gg in gyo])
    hap_sum = sum([max(str1.count(hh), str2.count(hh)) for hh in hap])

    return math.floor((gyo_sum/hap_sum)*65536)
```

- 저와 같은 로직인데 `re.findall`을 사용해서 한 줄로 푼 사람의 코드임다 ,,



```python
from collections import Counter
def solution(str1, str2):
    # make sets
    s1 = [str1[i:i+2].lower() for i in range(len(str1)-1) if str1[i:i+2].isalpha()]
    s2 = [str2[i:i+2].lower() for i in range(len(str2)-1) if str2[i:i+2].isalpha()]
    if not s1 and not s2:
        return 65536
    c1 = Counter(s1)
    c2 = Counter(s2)
    answer = int(float(sum((c1&c2).values()))/float(sum((c1|c2).values())) * 65536)
    return answer
```

- 이 코드를 보고 굳이 정규표현식을 쓰지 않고 `isalpha()`를 써도 되겠다는 생각이 들었습니다. !!!!!
- 그리고 `Counter`도 `&`와 `|`이 존재함을 알게된 코드입니다.