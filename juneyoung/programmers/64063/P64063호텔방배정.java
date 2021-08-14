import java.util.*;

public class P64063호텔방배정 {
    static Map<Long, Long> map = new HashMap<>();

    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];

        for (int i = 0; i < room_number.length; i++) {
            answer[i] = cal(room_number[i]);
        }
        return answer;
    }

    public static long cal(long num) {
        if (!map.containsKey(num)) {
            map.put(num, num + 1);
            return num;
        }

        long findNum = cal(map.get(num));
        map.put(num, findNum + 1);
        return findNum;
    }
}
 