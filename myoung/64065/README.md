# 프로그래머스 64065 튜플

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64065)

## 1. 설계 로직

1. 입력된 문자열을 잘라서 숫자를 담은 리스트 형태로 바꿔줍니다.
2. 숫자의 갯수가 작은 것부터 정렬을 해줍니다.
3. 앞의 요소에 담겨있지 않은 요소가 answer에 들어갈 요소이기 때문에 해당 요소를 찾아 answer에 넣어줍니다.



- 시간복잡도: O(N) 맞나요 ㅠ

## 2. 코드

```python
def solution(s):
    answer = []
    s = s[2:-2].split('},{')
    s.sort(key=lambda x: len(x))
    prev = []
    for i in range(len(s)):
        now = list(map(int, s[i].split(',')))
        val = [x for x in now if x not in prev]
        prev = now
        answer.append(val[0])
    return answer
```



## 3. 후기

- 다른 사람들의 풀이를 보다가 더 짧게 접근할 수 있는 로직을 찾아 확인해봤습니다.

  ```python
  from collections import Counter
  
  def solution(s):
      s = s[2:-2].replace('},{', ',').split(',')
      count = Counter(s)
      return [int(i[0]) for i in count.most_common()]
  ```

  counter를 사용해서 제일 많이 쓰인 숫자부터 차곡차곡 answer에 담아줍니다.



- 문자열을 어떻게 더 빠르게 자를 수 있을 까 찾아보았습니다.

  https://hashcode.co.kr/questions/493/%EC%97%AC%EB%9F%AC%EA%B0%9C-%EB%AC%B8%EC%9E%90%EB%A5%BC-%EA%B8%B0%EC%A4%80%EC%9C%BC%EB%A1%9C-%EB%AC%B8%EC%9E%90%EC%97%B4%EC%9D%84-%EC%9E%90%EB%A5%B4%EB%8A%94-%EB%B0%A9%EB%B2%95%EC%9D%B4-%EC%9E%88%EB%82%98%EC%9A%94

  에서 3가지 방법을 찾을 수 있었습니다.