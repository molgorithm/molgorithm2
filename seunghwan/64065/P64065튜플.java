import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class P64065튜플 {
	// 1 + 2 + 3 + ... + n;
	
	public int[] solution(String s) {
		List<Integer> answerList = new ArrayList<Integer>();
		boolean nums[] = new boolean[100001];
		s = s.substring(2, s.length() - 1);
		String[] elements = s.split(",\\{");
		Arrays.sort(elements, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		});
		for (String element : elements) {
			element = element.substring(0,element.length()-1);
			for (String e : element.split(",")) {
				int num = Integer.parseInt(e);
				if (!nums[num]) {
					nums[num] = true;
					answerList.add(num);
					break;
				}
			}
		}
		int[] answer = new int[answerList.size()];
		for (int i = 0; i < answerList.size(); i++)
			answer[i] = answerList.get(i);
		return answer;
	}

}

