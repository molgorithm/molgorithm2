# 프로그래머스 17680 캐시

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/17680)

## 1. 설계 로직

0. deque를 사용하여 LRU를 구현합니다.

1. cache에 존재하면 `cache hit`, cache에 존재하지 않을 경우 `cache miss`이기 때문에 실행시간을 각각의 상황에 맞추어 더해줍니다.
2. `cache miss`의 경우 중 cache가 다 찬 경우, 맨 앞의 요소를 하나 빼주고 현재 요소를 맨 뒤에 넣어줍니다.

3. cache에 존재했던 요소의 경우, 해당 요소를 제거하고 맨 뒤에 넣어줍니다. (우선순위 고려)

   

- 시간복잡도: O(N) 
  - N은 cities 요소의 갯수입니다.

## 2. 코드

```python
from collections import deque


def solution(cacheSize, cities):
    answer = 0
    cache = deque()

    if cacheSize == 0:
        return len(cities) * 5

    for city in cities:
        city = city.lower()
        if city in cache:
            answer += 1
            cache.remove(city)
        else:
            if len(cache) >= cacheSize:
                cache.popleft()
            answer += 5
        cache.append(city)
    return answer
```



## 3. 후기

- `deque`의 `maxlen`을 사용하면 위의 코드가 더욱 간결해질 수 있습니다.

  ```python
  def solution(cacheSize, cities):
      import collections
      cache = collections.deque(maxlen=cacheSize)
      time = 0
      for i in cities:
          s = i.lower()
          if s in cache:
              cache.remove(s)
              cache.append(s)
              time += 1
          else:
              cache.append(s)
              time += 5
      return time
  ```

  - `maxlen`은 `queue`가 찬 상태에서 새 아이템을 넣으면 첫 아이템이 자동으로 삭제되기 때문에 제가 구현한 로직을 이 메서드로 대체할 수 있습니다. 
  - 이 풀이보고 놀래버렸습니다. 세상은 넓고... 천재는 많다 ... 머찐 사람들 ...