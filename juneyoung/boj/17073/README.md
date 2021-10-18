# 백준 17073 나무 위의 빗물

[문제 링크](https://www.acmicpc.net/problem/17073)

## 1. 설계 로직

1. 고려해야할 사항

   - 노드의 수 최대 500,000

2. 설계로직
   1. 단말 노드의 수만 구하면 되기 때문에 노드의 수 크기의 배열 생성
   2. 들어오는 노드의 수 카운팅
   3. 카운팅이 1개 일 경우 단말 노드
   4. 전체의 빗물 / 단말 노드의 수 리턴
3. 시간복잡도: O ( n )

## 2. 코드

```kotlin

fun main() = with(System.`in`.bufferedReader()) {
    val (node, water) = readLine().split(' ').map { it.toInt() }
    val counts = IntArray(node + 1)

    for (i in 1 until node)
        readLine().split(' ').forEach { counts[it.toInt()]++ }

    val leafNodeCnt = counts.filterIndexed { idx, value -> idx != 1 && value == 1 }.count().toDouble()
    println(water.toDouble() / leafNodeCnt)
}

```

## 3. 후기

- 트리의 성질을 알면 풀 수 있는 문제입니다.
