# 백준 7662 이중 우선순위 큐

[문제 링크](https://www.acmicpc.net/problem/7662)

## 1. 설계 로직

- ### java

1. 입력과 출력에 log(n) 을 원함
2. 정렬된 자료구조가 필요함
3. 중복을 제거해 줄 자료구조가 필요함
4. Treemap으로 한 번에 해결
5. I면 Treemap에 넣고 이미 존재하면 개수만 늘린다
6. D 1 이면 lastkey를 활용해 맨 오른쪽 노드 제거, D -1이면 firstkey를 활용해 맨 왼쪽 노드 제거(개수가 남아 있다면 개수만 1 줄임)
7. 비어있다면 Entry, 존재하면 lastkey, firstkey를 각각 출력한다

- n log(n)



- ### python

1. 입력과 출력에 log(n) 을 원함
2. 정렬된 자료구조가 필요함
3. 중복을 제거해 줄 자료구조가 필요함
4. 작은 수를 기준으로 하는 우선순위 큐, 큰 수를 기준으로 하는 우선순위 큐 이렇게 2개 만든다
5. I이면 두 큐에 모두 저장한다. 단, 인덱스 값을 넣어서 서로가 같은 수임을 인식시킨다
6. D 1 이면 내림차순 큐에서 제거, D -1이면 오름차순 큐에서 제거
7. 하나의 명령이 끝날 때마다 동시에 pop 값이 없는지 양쪽 큐를 확인한다. 있다면 최대한 pop한다.
8. 비어있다면 Entry, 존재하면 각 큐를 pop하여 출력한다

- n log(n)

## 2. 코드

- ### java

```python
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int num = Integer.parseInt(st.nextToken());
                if (command.equals("I")) {
                    if (map.containsKey(num)) {
                        map.put(num, map.get(num) + 1);
                    } else {
                        map.put(num, 1);
                    }
                } else {
                    if (map.size() > 0) {
                        int del_key = 0;
                        if (num == 1) {
                            del_key = map.lastKey();
                        } else {
                            del_key = map.firstKey();
                        }
                        int del_num = map.get(del_key) - 1;
                        if (del_num == 0) {
                            map.remove(del_key);
                        } else {
                            map.put(del_key, del_num);
                        }
                    }
                }
            }
            if (map.size() == 0) {
                System.out.println("EMPTY");
            } else {
                System.out.println(map.lastKey() + " " + map.firstKey());
            }
        }

    }
}
```

- ### python

```
import sys
import heapq

input = sys.stdin.readline
T = int(input())
for _ in range(T):
    n = int(input())
    nq, mq = [], []
    visit = [False] * (n + 1)  # 지워진 번호 (입력된 순서)
    for i in range(n):
        command, num = input().split()
        num = int(num)
        if command == 'I':  # 입력 명령이면
            heapq.heappush(nq, (num, i))  # 작은수우선
            heapq.heappush(mq, (-num, i))  # 큰수우선
            continue
        elif num == 1 and mq:  # 지우라는 명령어에 지울 값이 있으면
            visit[heapq.heappop(mq)[1]] = True  # 입력된 값을 지우고 지웠음을 저장한다
        elif num == -1 and nq:  # 지우라는 명령어에 지울 값이 없으면
            visit[heapq.heappop(nq)[1]] = True  # 입력된 값을 지우고 지웠음을 저장한다
        while nq and visit[nq[0][1]]:  # nq에서 제거할 수 있을만큼 제거하겠다.
            heapq.heappop(nq)
        while mq and visit[mq[0][1]]:  # mq에서 제거할 수 있을만큼 제거하겠다.
            heapq.heappop(mq)
    print(f'{-mq[0][0]} {nq[0][0]}' if nq else 'EMPTY')

```

## 3. 후기

- ### java

- 이 문제는 AVL Tree 혹은 Red-Black Tree를 활용하라는 문제로 보인다.

- 그러나 이 문제에서 자료구조 그 자체를 구현하기에는 무리가 있으므로 있는 자료구조를 활용해서 최대한 문제에 접근하는 것이 중요하겠다.

- Java에는 Treemap이라는 파워풀한 자료구조(RB Tree 기반)가 존재해서 쉽게 풀 수 있었다.

- Treemap은 Map의 Key, Value값을 가지면서 이분탐색트리의 구조를 띄고 있어 정렬된 구조를 갖는다.

- 입력,출력에 log(n) 밖에 걸리지않고, 내부적으로 균형을 맞추기위해 회전도 알아서 해준다.

- 자바 사기

- ### python

- Python에는 정렬이 되며 이분탐색하는 자료구조는 존재하지 않는다. 있다고 하더라도 삭제에 log(n) 이 소모되는 자료구조는 기본적으로 제공하지 않는듯하다.
- 우리가 알아야 할 값은 최소값, 최대값으로 기준이 2개이므로 각각의 기준에 맞는 우선순위큐를 활용한다.
- 서로의 큐가 같은 데이터를 공유하고 있어야 한다는 점에서 인덱스를 활용하는 방법과 dictionary를 활용하는 2가지의 방법이 있겠다
- 삽입에 이분탐색을 적용하고 삭제에 O(n)을 소비시켜도 통과하는 것도 확인했다. 저격케이스 뜨면 바로 시간초과다. 그러나 의도가 중복을 제거하라는 부분이라 삭제 시간 초과는 큰 문제가 아닌 것 같다. 심지어는 속도도 더 빠른 결과가 나와 오히려 당황스럽다. 하하
- 자바 사기

