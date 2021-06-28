import java.util.ArrayList;

public class P67257수식최대화 {
	static ArrayList<Long> numlist = new ArrayList<Long>();
	static ArrayList<Character> opList = new ArrayList<Character>();
	static char[] choice = new char[3];
	static boolean[] visit = new boolean[3];
	static char[] booho = { '+', '-', '*' };
	static long max = Long.MIN_VALUE;

	public long solution(String expression) {
		String s = "";
		for (char a : expression.toCharArray()) {
			if (a == '-' || a == '+' || a == '*') {
				numlist.add(Long.parseLong(s));
				opList.add(a);
				s = "";
				continue;
			}
			s += a;
		}
		numlist.add(Long.parseLong(s));
		perm(0);
		return max;
	}

	public void perm(int num) {
		if (num == 3) {
			max = Long.max(calc(), max);
			return;
		}
		for (int i = 0; i < 3; i++) {
			if (visit[i])
				continue;
			choice[num] = booho[i];
			visit[i] = true;
			perm(num + 1);
			visit[i] = false;
		}
	}

	public long calc() {
		ArrayList<Long> calcNum = (ArrayList<Long>) numlist.clone();
		ArrayList<Character> calcOp = (ArrayList<Character>) opList.clone();
		for (char operate : choice) {
			for (int i = 0; i < calcOp.size(); i++) {
				if (calcOp.get(i) == operate) {
					long calcResult = 0;
					if (operate == '*')
						calcResult = calcNum.get(i) * calcNum.get(i + 1);
					else if (operate == '+')
						calcResult = calcNum.get(i) + calcNum.get(i + 1);
					else if (operate == '-')
						calcResult = calcNum.get(i) - calcNum.get(i + 1);
					calcNum.remove(i + 1);
					calcNum.set(i, calcResult);
					calcOp.remove(i);
					i = i - 1;
				}
			}
		}
		return Math.abs(calcNum.get(0));
	}

}


