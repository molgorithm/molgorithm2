# 프로그래머스 64065 튜플

[문제 링크]([https://programmers.co.kr/learn/courses/30/lessons/67256﻿](https://programmers.co.kr/learn/courses/30/lessons/67256))

## 1. 설계 로직

![img](https://blog.kakaocdn.net/dn/b9r4ng/btq8gngzP44/D2MmRaaWZeMJkw85pU37W0/img.png)



왼손 오른손 두 손가락으로 숫자를 누를 때 어떤 손으로 누를지 순서를 출력하는 문제

 

기본적인 구현 문제로 좌표의 개념만 있다면 쉽게 해결이 가능하다.

왼손과 오른손의 좌표를 계속해서 저장해두고

비교가 필요할 경우

|x1-x2| + |y1-y2| 를 했을 때 더 가까운 손으로

거리가 같으면 왼손잡이 인지 오른손 잡이 인지로 판단하여

누르는 손을 결정하면 된다.

- 시간복잡도

  O(n) 

## 2. 코드

```java
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

```



## 3. 후기

- .

