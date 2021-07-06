import java.util.ArrayList;
import java.util.List;

public class P17687n진수게임 {

	public String solution(int n, int t, int m, int p) {
		String answer = "";
		List<Character> numList = new ArrayList<Character>();
		int num = 1;
		numList.add('0');
		while (numList.size() < t * m) {
			int target = num;
			String s = "";
			while (target != 0) {
				int mod = target % n;
				mod = mod >= 10 ? 'A' + mod - 10 : '0' + mod;
				s = (char) mod + s;
				target = target / n;
			}
			for (char c : s.toCharArray())
				numList.add(c);
			num++;
		}
		for (int i = p-1; answer.length() < t; i += m)
			answer += numList.get(i);

		return answer;
	}
}
