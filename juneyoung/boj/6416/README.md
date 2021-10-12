# 백준 6416 트리인가?

[문제 링크](https://www.acmicpc.net/problem/6416)

## 1. 설계 로직

1. 고려해야할 사항 
   - root node가 1개

   - 간선의 개수 == 노드 - 1
   - 루트 노드를 제외한 모든 노드는 반드시 단 하나의 들어오는 간선이 존재한다.
2. 설계로직
   1. 입력 받은 두 수가 (0,0), (-1,-1) 이 아닐 때 까지 map에 저장
      - 부모 노드는 키(입력받은 수 ), 값(0) 계속 해서 증가
      - 자식 노드는 키(입력받은 수 ), 값(1) 계속 해서 증가
   2. 정상적으로 입력을 받았다면 count++ (간선의 갯수)
   3. 트리인지 판별
      1. map이 비어있을 경우
      2. 간선의 수 == 노드 -1 && 맵의 값중 가장 큰 값이 2보다 작음(하나의 들어오는 간선만 존재), min값이 0 (루트노드), 값 0인 맵의 개수가 1 (루트노드가 1개) 일 경우
3. 시간복잡도: O ( n ) 

## 2. 코드

```kotlin

import java.util.*

fun main() = with(Scanner(System.`in`)) {
    val map = mutableMapOf<Int, Int>()
    var case = 1
    var count = 0
    while (true) {
        val a = nextInt()
        val b = nextInt()
        count++
        if (a == 0 && b == 0) {
            if (
                map.isEmpty() || (
                        count == map.size
                                && map.values.maxOf { it } < 2
                                && map.values.minOf { it } == 0
                                && map.values.count { it == 0 } == 1
                        )
            ) println("Case $case is a tree.") else println("Case $case is not a tree.")
            map.clear()
            case++
            count = 0
            continue
        }
        map.merge(a, 0, Integer::sum)
        map.merge(b, 1, Integer::sum)
        if (a == -1 && b == -1) break
    }
}

```

## 3. 후기

- 입력이 너무 더럽습니다.
- 문제의 테케가 부실하고 설명도 부실함.
