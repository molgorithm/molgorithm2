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
