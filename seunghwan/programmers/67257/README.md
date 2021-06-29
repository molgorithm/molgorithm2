# 프로그래머스 67257 수식최대화

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/67257)



## 1. 설계 로직

우선 입력으로 주어진 식을 수와 연산자로 분리하여

따로 배열에 저장한다.

 

이후 + - * 의 우선순위를 정해야하므로

3가지 원소의 순열을 구한다.

 

순열로 정해진 순서에 따라

연산자 배열에서 해당 연산자를 찾아 꺼낸 뒤

연산자에 해당하는 숫자를 꺼내 계산하고 다시 집어넣는다.

 

이 개념으로 3가지 연산자를 모두 계산하면 해결이 가능하다.

 

주의) 결과 값이 크니 long을 사용해야 한다.



- 시간복잡도

  O(n)  3! * n

## 2. 코드

```java
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

```



## 3. 후기

- 삽질 한 부분

  처음에 연산자를 찾아 계산을 진행할 때

  앞에서부터 빼면 index조정이 필요하므로 뒤에서 부터 연산하도록 했는데

  100 - 600 - 300 과 같은 경우

  (100) - (600) - (300) 으로 보기 때문에

  뒤에서 부터 연산하면

  (100) - (300) = - 200이 된다.

  하지만 실제론

  (-500) - (300) = -800이 되어야 해서 문제가 생김.

