import java.util.*;

public class P64064불량사용자 {
    static Map<String, Integer> map = new HashMap<>();
    static Set<Integer> set = new HashSet<>();
    static List<Integer> key = Arrays.asList(8, 80, 800, 8000, 80000, 800000, 8000000, 80000000);
    static String[] result;
    static int size;
    static boolean[] v;

    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        size = banned_id.length;
        result = new String[size];
        v = new boolean[user_id.length];
        for (int i = 0; i < user_id.length; i++) {
            map.put(user_id[i], key.get(i));
        }

        permutation(0, user_id, banned_id);

        return set.size();
    }

    public static boolean check(String banned_id, String user_id) {
        for (int i = 0; i < banned_id.length(); i++) {
            if (banned_id.charAt(i) == '*') continue;
            if (banned_id.charAt(i) != user_id.charAt(i)) return false;
        }
        return true;
    }

    public static void permutation(int idx, String[] user_id, String[] banned_id) {
        if (idx == size) {
            int count = 0;
            boolean[] idCheck = new boolean[size];
            L:
            for (int i = 0; i < size; i++) {
                int bandSize = banned_id[i].length();
                for (int j = 0; j < result.length; j++) {
                    if (bandSize == result[j].length() && !idCheck[j] && check(banned_id[i], result[j])) {
                        idCheck[j] = true;
                        count++;
                        continue L;
                    }
                }
            }

            if (count == size) {
                set.add(Arrays.stream(result)
                        .mapToInt(s -> map.get(s))
                        .sum());
            }
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            if (v[i]) continue;
            v[i] = true;
            result[idx] = user_id[i];
            permutation(idx + 1, user_id, banned_id);
            v[i] = false;
        }
    }
}
 