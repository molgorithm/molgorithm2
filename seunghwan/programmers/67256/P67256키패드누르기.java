import java.awt.Point;

public class P67256키패드누르기 {

	public String solution(int[] numbers, String hand) {
		String answer = "";
		Point left = new Point(3, 0);
		Point right = new Point(3, 2);
		Point numPoint[] = new Point[11];
		boolean isLeft = false;
		if (hand.equals("left")) {
			isLeft = true;
		}
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				numPoint[i * 3 + j + 1] = new Point(i, j);
		numPoint[0] = new Point(3, 1);

		for (int i : numbers) {
			Point target = numPoint[i];
			if (i == 1 || i == 4 || i == 7) {
				answer += 'L';
				left = target;
			} else if (i == 3 || i == 6 || i == 9) {
				answer += 'R';
				right = target;
			} else {
				int leftDis = Math.abs(target.x - left.x) + Math.abs(target.y - left.y);
				int rightDis = Math.abs(target.x - right.x) + Math.abs(target.y - right.y);
				if (leftDis > rightDis) {
					answer += 'R';
					right = target;
				} else if (leftDis < rightDis) {
					answer += 'L';
					left = target;
				} else {
					if (isLeft) {
						answer += 'L';
						left = target;
					} else {
						answer += 'R';
						right = target;
					}
				}
			}

		}

		return answer;
	}
}

