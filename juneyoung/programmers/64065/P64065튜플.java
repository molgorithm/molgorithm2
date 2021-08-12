import java.util.*;

public class P64065튜플 {
    static Map<String, Integer> map = new HashMap<>();

    public int[] solution(String s) {

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '{') {
                int idx = i;
                while (s.charAt(idx) != '}') idx++;
                Arrays.stream(s.substring(i + 1, idx).split(","))
                        .forEach(o -> map.merge(o, 1, Integer::sum));
                i = idx;
            }
        }

        return map.keySet().stream()
                .sorted((o1, o2) -> map.get(o2) - map.get(o1))
                .mapToInt(Integer::parseInt)
                .toArray();

    }
}
 