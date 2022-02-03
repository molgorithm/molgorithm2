import kotlin.math.ceil

private fun test(fees: IntArray, records: Array<String>): IntArray {
    return records.map { it.split(' ') }
        .groupBy { it[1] }
        .asSequence()
        .sortedBy { it.key.toInt() }
        .map { it.value }
        .map { computeFee(computeTime(it), fees) }
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


fun main() {
    val fees = intArrayOf(1, 461, 1, 10)
    val records = arrayOf("00:00 1234 IN")
    test(fees, records)
}
