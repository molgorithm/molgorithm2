class P42862체육복 {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        var answer = n
        val reserveSet: HashSet<Int> = hashSetOf()
        val v = Array(n + 1) { true }

        for (i in lost) {
            v[i] = false
            answer--
        }

        for (idx in reserve.indices) {
            reserveSet.add(reserve[idx])
            if (!v[reserve[idx]]) {
                v[reserve[idx]] = true
                reserveSet.remove(reserve[idx])
                answer++
            }
        }

        for (idx in 1..n) {
            if (!v[idx] && reserveSet.contains(idx - 1)) {
                answer++
                reserveSet.remove(idx - 1);
            } else if (!v[idx] && reserveSet.contains(idx + 1)) {
                answer++
                reserveSet.remove(idx + 1);
            }
        }

        return answer
    }
}
 