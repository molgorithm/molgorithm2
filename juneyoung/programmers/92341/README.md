# 프로그래머스 92341 주차 요금 계산

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/92341)

## 1. 설계 로직

1. 고려해야할 사항 
   - `⌈`a`⌉` : a보다 작지 않은 최소의 정수를 의미합니다. 즉, `올림`을 의미합니다.
   - 완탐
2. 설계로직
   1. 입/출차 기록 차량번호를 기준으로 그룹핑

   2. 차량번호 오름차순 정렬
   3. 각 차량번호를 기준으로 주차요금 계산
      1. 시간 계산 (시간을 분으로 바꿔) 입차 / 출차 각각 sum
      2. 출차 - 입차  = 누적 주차 시간 [ 단, 음수일 경우 + 1439 ( 23시59분 ) ] 
      3. 누적 주차 시간을 기준으로 주차요금 계산
3. 시간복잡도: O (n x logn) 

## 2. 코드

```kotlin
import kotlin.math.ceil
class Solution {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        return records.map { it.split(' ') }
            .groupBy { it[1] }
            .asSequence()
            .sortedBy { it.key.toInt() }
            .map { it.value } 
            .map { computeFee(computeTime(it), fees)  }
            .toList()
            .toIntArray()
    }
    
    fun computeFee(time: Int, fees: IntArray): Int {
        var result = fees[1]
        if (time > fees[0]) result += (ceil((time - fees[0]) / (fees[2] * 1.0)) * fees[3]).toInt()
        return result
    }

    fun computeTime(times: List<List<String>>): Int {
        var startTime = 0
        var endTime = 0
        times.forEach { i ->
            when (i[2]) {
                "IN" -> {
                    startTime += conversionTime(i[0])
                }
                else -> {
                    endTime += conversionTime(i[0])
                }
            }
        }
        var result = endTime - startTime
        if (result <= 0) result += 1439
        return result
    }

    fun conversionTime(time: String): Int {
        val (h, m) = time.split(':').map { it.toInt() }
        return h * 60 + m
    }
}
```

## 3. 후기

- 시뮬레이션 문제에 아이디어 첨가라 재밌었습니다.
