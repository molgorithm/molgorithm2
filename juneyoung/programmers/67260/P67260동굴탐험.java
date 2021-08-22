import java.util.*;

class P67260동굴탐험 {
    static List<Integer>[] list;
    static boolean[] v, prev;
    static Map<Integer, Integer> map = new HashMap<>(), revMap = new HashMap<>();

    public boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = true;
        list = new ArrayList[n];
        prev = new boolean[n];
        v = new boolean[n];
        prev[0] = true;

        for (int i = 0; i < n; i++)
            list[i] = new ArrayList<>();

        for (int i = 0; i < path.length; i++) {
            int start = path[i][0];
            int end = path[i][1];
            list[start].add(end);
            list[end].add(start);
        }

        for (int i = 0; i < order.length; i++) {
            map.put(order[i][0], order[i][1]);
            revMap.put(order[i][1], order[i][0]);
        }

        if (revMap.containsKey(0)) return false;
        cal();
        for (int i = 0; i < n; i++)
            if (!v[i]) answer = false;

        return answer;
    }

    public static void cal() {
        Queue<Integer> q = new LinkedList<>();
        v[0] = true;
        q.add(0);
        while (!q.isEmpty()) {
            int p = q.poll();
            int size = list[p].size();

            for (int i = 0; i < size; i++) {
                int idx = list[p].get(i);
                prev[idx] = true;
                if (v[idx]) continue;
                if (revMap.containsKey(idx) && !v[revMap.get(idx)]) continue;
                v[idx] = true;
                q.add(idx);
            }

            if (map.containsKey(p) && prev[map.get(p)]) {
                v[map.get(p)] = true;
                q.add(map.get(p));
            }
        }
    }
}
 