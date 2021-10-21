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
