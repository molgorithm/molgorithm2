
# 프로그래머스 64065 튜플

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64065)

## 1. 설계 로직

1. 각 문자를 반복문을 돌면서 숫자인지 확인한다. 숫자를 num이라는 변수에 임시 저장한다.

   1-1. 숫자일경우, 다음의 문자도 숫자일 경우에 **num**에 저장된 값에 10을 곱하고 숫자를 더한다.(자리수를 만들기 위함)

   1-2. 다음 문자가 `,`라면, temp라는 리스트에 지금까지 구한 숫자를 더한다.

   1-3. 다음문자가 `}`라면, temp라는 리스트에 지그까지 구한 숫자를 더한뒤, **myList**라는 변수에 **temp**리스트를 더한다. 

2. **myList**를 길이로 정렬한다.

3. myList의 원소와 구한 answer 값을 비교하며 answer을 하나씩 추가한다.

   3-1. myList의 길이가 항상 answer보다 1크므로, answer과 myList의 원소를 비교해 같은 원소를 myList에서 제거한다.

   3-2. 반복문을 모두 돌 때까지 남아있는 원소를 myList에 추가한다.

    

- 시간복잡도

## 2. 코드

```python
def solution(s):
    answer = []
    temp = []
    myList = []
    num = 0
    for i in range(len(s)):
        if s[i].isdigit():

            if s[i+1] == '}':
                temp.append((num * 10) + int(s[i]))
                num = 0
                myList.append(temp)
                temp = []
            elif s[i+1] == ',':
                temp.append((num*10) + int(s[i]))
                num = 0
            else:
                num = (num * 10) + int(s[i])

    myList.sort(key=len)

    for i in range(len(myList)):
        for a in range(len(answer)):
            for j in range(len(myList[i])):
                if answer[a] == myList[i][j]:
                    myList[i].pop(j)
                    break
        answer.append(myList[i][0])

    return answer
```





## 3. 후기

- 문자열로 표현된 튜플을 반복문을 사용하기 위해서 조건문을 사용해서 리스트 형태로 바꿨는데, 다른 사람들의 풀이를 보니 **정규표현식**을 사용해서 간단하게 바꾸는 방법을 알게됐다. 정규표현식을 암기하지 않아서 검색하지 않으면 사용을 못했는데, 자주 사용해서 검색하지 않고도 사용할 수 있도록 해야겠다.
- 이중리스트로 만든 튜플을 3중 반복문을 이용해서 값을 찾았는데, 다른 사람들의 풀이를 보니, 가장 여러번 반복되는 숫자 순서대로 정렬을 하는 방법이 있었다. `key=lambda x: x[1]`를 사용한 것인데, 알고리즘 수업때, 한번 배웠던 적이 있던 것이다. 이것도 사용하는 법을 익혀봐야겠다. 유용 할 것 같다. 
