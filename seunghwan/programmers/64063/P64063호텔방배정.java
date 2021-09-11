import java.util.HashMap;

public class P64063호텔방배정 {
	HashMap<Long, Long> parents = new HashMap<Long, Long>();
	public long[] solution(long k, long[] room_number) {
		long[] answer = new long[room_number.length];
		for (int i = 0; i < room_number.length; i++)
			answer[i] = findRoom(room_number[i]);
		return answer;
	}
	public long findRoom(long num) {
		if (parents.get(num) == null) {
			parents.put(num, num + 1);
			return num;
		}
		long parent = findRoom(parents.get(num));
		parents.put(num, parent);
		return parent;
	}
}
