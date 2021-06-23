# 프로그래머스 64063 호텔 방 배정

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64063)

## 1. 설계 로직

1. 방이 있으면 해당 방을 넣고, 방이 없다면 find함수를 사용해 방을 찾습니다. 이 때, reserved_rooms도 갱신해줍니다.

   갱신할 때 재귀 호출시마다 모두 remain_room으로 갱신되기 때문에 이 부분에서 시간을 효율적으로 쓸 수 있어서 효율성 통과를 한 것 같슴다.



- 시간복잡도: O(a(N)) , a(N)은 아커만 함수를 의미한다고 합니다. 
  - 아커만 함수에서 N이 2^65536일 때, 아커만 함수의 값은 5가 되므로 상수의 시간 복잡도를 가진다고 봐도 무방합니다. (출처: https://dheldh77.tistory.com/entry/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EC%9C%A0%EB%8B%88%EC%98%A8%ED%8C%8C%EC%9D%B8%EB%93%9CUnion-Find-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-1)

## 2. 코드

```python
import sys
sys.setrecursionlimit(10 ** 7)

def solution(k, room_number):
    answer = []
    reserved_rooms = dict()

    def find(room, reserved_rooms):
        if room not in reserved_rooms:
            reserved_rooms[room] = room + 1
            return room
        reserved_rooms[room] = find(reserved_rooms[room], reserved_rooms)
        return reserved_rooms[room]

    for room in room_number:
        remained_room = find(room, reserved_rooms)
        answer.append(remained_room)

    return answer
```



## 3. 후기

```python
def solution(k, room_number):
    answer = []
    left_rooms = [True] * (k+1)

    for room in room_number:
        if not left_rooms[room]:
            while not left_rooms[room]:
                room += 1
        left_rooms[room] = False
        answer.append(room)
    return answer
```
- 처음에는 이렇게 짜고, 효율성에서 시간초과가 나서 이와 비슷하게 계속 수정했더니 계속 시간 초과가 나서 접근이 잘못되었나 싶어 검색했습니다.

  https://hellominchan.tistory.com/261

  여기를 참고했습니다.

