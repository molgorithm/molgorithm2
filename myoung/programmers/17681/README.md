# 프로그래머스 17681 비밀지도

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/17681)

## 1. 설계 로직

0. N의 범위가 1~16이라 완전탐색 문제라고 생각했습니다.

1. 각각의 arr들을 이진수로 변환합니다.
2. 이진수로 변환된 맵을 하나씩 확인하면서 맵 합칩니다.
   1. `0`: 공백, `1`: 벽
   2. 두가지 맵 중 하나라도 `1`이면 `1`
   3. 두가지 맵 모두 `0`이면 `0`
   4. `0`인 경우 공백으로 나타내고, `1`인경우 `#`으로 나타내어 출력합니다.



- 시간복잡도: O(N^2) 

## 2. 코드

```python
def solution(n, arr1, arr2):
    
    def trans_map(arr):
        new_arr = []
        for i in range(n):
            val = ''.join(bin(arr[i]))[2:]
            while len(val) < n:
                val = '0' + val
            new_arr.append(val)
        return new_arr
            
    def add_map(map1, map2):
        new = []
        for y in range(n):
            line = ''
            for x in range(n):
                if map1[y][x] == map2[y][x] and map1[y][x] == '0':
                    line += ' '
                else:
                    line += '#'
            new.append(line)
        return new
    
    new1, new2 = trans_map(arr1), trans_map(arr2)
    return add_map(new1, new2)
```



## 3. 후기

저는 n개의 요소를 가진 map형식을 맞춰주기 위해 `while`문을 사용했는데, 다른 사람 중 `rjust`를 사용한 사람의 풀이를 보고 감탄해서 가져왔습니다.

[감탄 이유]

1. 비트 연산자 사용 (`or 연산자`)

1. `rjust`가 뭔지 몰랐음
2. `zip`과 `replace`의 적절한 사용

```python
def solution(n, arr1, arr2):
    answer = []
    for i,j in zip(arr1,arr2):
        a12 = str(bin(i|j)[2:])
        a12=a12.rjust(n,'0')
        a12=a12.replace('1','#')
        a12=a12.replace('0',' ')
        answer.append(a12)
    return answer
```

- `rjust`: 원하는 문자와 갯수를 지정할 수 있음

https://kkamikoon.tistory.com/136

해당 블로그에서는 `rjust`, `zfill`에 대해서 설명해주는데, `zfill`은 예전에 써봤는데, `rjust`는 아예 처음봄.

풀이 보고 감탄