package baekjoon

fun main() = with(System.`in`.bufferedReader()) {
    val (node, water) = readLine().split(' ').map { it.toInt() }
    val counts = IntArray(node + 1)

    for (i in 1 until node)
        readLine().split(' ').forEach { counts[it.toInt()]++ }

    val leafNodeCnt = counts.filterIndexed { idx, value -> idx != 1 && value == 1 }.count().toDouble()
    println(water.toDouble() / leafNodeCnt)
}
