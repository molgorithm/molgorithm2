# 백준 3584 가장 가까운 공통 조상

[문제 링크](https://www.acmicpc.net/problem/3584)

## 1. 설계 로직

1. 고려해야할 사항 
   - 노드의 수 ( 10,000 )

2. 설계로직
   1. 자식과 부모가 1대1 이므로 Map 을 사용해 트리 구성
   2. A노드부터 탐색해서 연결된 노드 방문체크
   3. B노드 탐색, 이미 방문 처리 된 노드일 경우 공통조상
3. 시간복잡도: O ( n ) 

## 2. 코드

```kotlin
fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()
    for (i in 1..t) {
        val n = readLine().toInt()
        val map = mutableMapOf<Int, Int>()
        val check = BooleanArray(n + 1)

        for (i in 1 until n) {
            val (parent, child) = readLine().split(' ').map { it.toInt() }
            map[child] = parent
        }

        val (a, b) = readLine().split(' ').map { it.toInt() }
        dfs(a, check, map)
        println(dfs(b, check, map))
    }
}

private fun dfs(idx: Int, check: BooleanArray, map: MutableMap<Int, Int>): Int {
    if (check[idx]) return idx
    check[idx] = true
    return dfs(map[idx] ?: return -1, check, map)
}
```

## 3. 후기

- 노드의 수가 크면 다른방법을 생각해 봐야할 문제 인거 같습니다.
