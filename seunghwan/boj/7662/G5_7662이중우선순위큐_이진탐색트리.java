package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_7662이중우선순위큐_이진탐색트리 {

	static class BST {
		int num;
		int cnt;
		BST left;
		BST right;

		public void put(int number) {
			if (this.num == number)
				this.cnt++;
			else if (this.num > number) {
				if (this.left == null)
					this.left = new BST(number);
				else
					left.put(number);
			} else if (this.num < number) {
				if (this.right == null)
					this.right = new BST(number);
				else
					right.put(number);
			}
		}

		public int popmin() {
			if (this.left == null) {
				this.cnt--;
				return num;
			} else {
				int min = this.left.popmin();
				if (this.left.cnt == 0)
					this.left = this.left.right;
				return min;
			}
		}

		public int popmax() {
			if (this.right == null) {
				this.cnt--;
				return num;
			} else {
				int max = this.right.popmax();
				if (this.right.cnt == 0)
					this.right = this.right.left;
				return max;
			}
		}

		public BST(int num) {
			this.num = num;
			this.cnt = 1;
		}
	}
	BST minnode;
	BST maxnode;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			int k = Integer.parseInt(br.readLine());
			BST bst = null;
			while (k-- != 0) {
				st = new StringTokenizer(br.readLine());
				String oper = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				if (oper.charAt(0) == 'I') {
					if (bst == null)
						bst = new BST(num);
					else
						bst.put(num);
				} else {
					if (bst == null)
						continue;
					if (num == 1) {
						bst.popmax();
					}
					else
						bst.popmin();
					if (bst.cnt == 0) 
						bst = num== 1 ? bst.left : bst.right;
				}
			}
			if (bst == null)
				answer.append("EMPTY").append("\n");
			else {
				int max = bst.popmax();
				int min = bst.popmin();
				answer.append(max).append(" ").append(min).append("\n");
			}
		}
		
		System.out.println(answer);
	}
}
