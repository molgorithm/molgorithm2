package programmers;

import java.util.*;

public class P43164여행경로 {
    static Map<String, List<String>> map = new HashMap<>();
    static Map<String, boolean[]> visited = new HashMap<>();
    static boolean flag;
    static String ans;

    public String[] solution(String[][] tickets) {
        for (int i = 0; i < tickets.length; i++) {
            String start = tickets[i][0];
            String end = tickets[i][1];
            map.merge(start, new ArrayList<String>(Arrays.asList(end)), (v1, v2) -> {
                v1.add(end);
                return v1;
            });
        }

        map.forEach((k, v) -> {
            Collections.sort(v);
            visited.put(k, new boolean[v.size()]);
        });

        dfs("ICN", "ICN", 1, tickets.length + 1);
        return ans.split(" ");
    }

    public void dfs(String prev, String result, int count, int maxCount) {
        if (flag) return;
        if (maxCount == count) {
            flag = true;
            ans = result;
            return;
        }

        if (map.get(prev) == null) return;

        for (int i = 0; i < map.get(prev).size(); i++) {
            String next = map.get(prev).get(i);
            if (!visited.get(prev)[i]) {
                visited.get(prev)[i] = true;
                dfs(next, result + " " + next, count + 1, maxCount);
                visited.get(prev)[i] = false;
            }
        }
    }
}
 