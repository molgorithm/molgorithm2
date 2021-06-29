# 프로그래머스 67257 수식 최대화

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/67257)

## 1. 설계 로직

1. 입력되는 expression의 형태가 문자열이기 때문에 이를 배열로 바꿔줘야합니다.

   사실 입력되는 연산자의 종류가 최대 3개 밖에 되지 않고 문자열 길이도 최대 100이기 때문에

   split을 3번만 해줘도 되지만,

   정규 표현식을 써보고 싶어서 정규 표현식으로 만들어보았습니다.



[연산자 우선순위 정하기]

1. 순열을 사용해서 나올 수 있는 모든 경우의 수를 계산합니다. (최대 6개이므로)
2. 각각의 우선순위에 따라 연산을 합니다.



[연산]

1. 연산자 배열 1개와 숫자 배열 1개를 만들어줍니다. 
   - 중위 표기법으로 표현된 연산식이므로 연산자 배열과 숫자 배열로 만들어줄 경우, 연산자 인덱스 i는 숫자[i]와 숫자[i+1]의 연산을 나타내는 것이기 때문입니다.

2. 같은 연산자인 경우 앞에 있는 것이 우선 순위가 높기 때문에 앞에서부터 순회해줘야합니다.



- 시간복잡도 : 빅오로 나타내는 건 확실치 않아서 숫자로 계산해보겠습니다 ^-^

  - 순열에서 나올 수 있는 최대 값: 6

  - 각각의 순열에서 나올 수 있는 최대 값: 33 (연산자 최대 갯수)

    -> 198

## 2. 코드

```python
import re


def make_orders(goal, val):
    global order_lst, total_orders
    if len(val) == goal:
        order_lst.append(val)
        return
    for i in range(goal):
        if total_orders[i] not in val:
            make_orders(goal, val + [total_orders[i]])


def solution(expression):
    global order_lst, total_orders

    answer = 0
    nums = re.split(r"-|\*|\+", expression)
    orders = re.findall("[^0-9]", expression)
    total_orders = list(set(orders))
    order_lst = []
    make_orders(len(total_orders), [])

    for m_order in order_lst:
        temp_nums = nums[:]
        temp_orders = orders[:]
        for now_order in m_order:
            i = 0
            while i < len(temp_orders):
                if temp_orders[i] == now_order:
                    if now_order == '+':
                        _temp = int(temp_nums[i]) + int(temp_nums[i + 1])
                    elif now_order == '-':
                        _temp = int(temp_nums[i]) - int(temp_nums[i + 1])
                    elif now_order == '*':
                        _temp = int(temp_nums[i]) * int(temp_nums[i + 1])
                    temp_nums.pop(i)
                    temp_nums.pop(i)
                    temp_nums.insert(i, _temp)
                    temp_orders.pop(i)
                else:
                    i += 1
        answer = max(abs(temp_nums[0]), answer)
    return answer
```



## 3. 후기

- 정규 표현식을 사용하기 위해 검색을 했습니다.
  - https://hashcode.co.kr/questions/5637/%ED%8C%8C%EC%9D%B4%EC%8D%AC%EC%97%90%EC%84%9C-split-%EC%9D%84-%EC%97%AC%EB%9F%AC%EB%B2%88-%ED%95%98%EA%B3%A0-%EC%8B%B6%EC%96%B4%EC%9A%94 : `re.split`
  - https://blog.naver.com/PostView.nhn?isHttpsRedirect=true&blogId=popqser2&logNo=221397907165&parentCategoryNo=&categoryNo=46&viewDate=&isShowPopularPosts=true&from=search : `findall`