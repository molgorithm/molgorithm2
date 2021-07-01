import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P17678셔틀버스 {
	// 각각 버스에 탈 수 있는 가장 늦은 시간을 구한다.

	public String solution(int n, int t, int m, String[] timetable) {
		Arrays.sort(timetable);
		Queue<Integer> crew = new LinkedList<Integer>();
		for (String s : timetable) {
			int H = Integer.parseInt(s.substring(0, 2));
			int M = Integer.parseInt(s.substring(3, 5));
			crew.add(H * 60 + M);
		}
		int bustime = 9 * 60;
		int answertime = 0;
		for (int i = 0; i < n; i++) { // i번째 버스
			int men = 0; // 현재까지 탄 숫자
			while (men < m - 1 && crew.size() != 0 && crew.peek() <= bustime) { // 한자리 빼고 태울수 있을때까지 태우기
				crew.poll();
				men++;
			}
			if (men == m - 1) { // 꽉 채워 앉았으면
				if (crew.size() != 0 && crew.peek() - 1 <= bustime) { // 다음에 탈 얘가 탈수 있었으면
					answertime = crew.peek() - 1; // 그 시간보다 1분 빠르게
					crew.poll();
				} else
					answertime = bustime; // 아니면 어차피 한자리 남으니 버스시간에 맞춰서

			} else // 꽉 안찼으면 버스시간에 타면 됨
				answertime = bustime;

			bustime += t;
		}
		int H = answertime / 60;
		int M = answertime % 60;
		String answer = "";
		answer += H / 10 == 0 ? "0" + H : H;
		answer += ":";
		answer += M / 10 == 0 ? "0" + M : M;
		return answer;
	}

}

