package Gold;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class G5_6416트리인가 {

	static HashMap<Integer, Integer> parents;
	static Set<Integer> visit = new HashSet<Integer>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		gg: for (int i = 1;; i++) {
			parents = new HashMap<Integer, Integer>();
			boolean isTree = true;
			while (true) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				if (u == 0 && v == 0)
					break;
				if (u == -1 && v == -1)
					break gg;
				if (parents.put(v, u) != null) {
					isTree = false;
				}
			}
			if (isTree) {
				int pre = 0;
				for (int key : parents.keySet()) {
					visit.clear();
					int root = find(key);
					if(root == -1) {
						isTree = false;
						break;
					}
					if(pre == 0)
						pre = root;
					else if(pre != root) {
						isTree = false;
						break;
					}
						
				}
			}

			sb.append("Case ").append(i).append(isTree ? " is a tree.\n" : " is not a tree.\n");
		}
		System.out.println(sb);
	}

	static int find(int i) {
		if (!visit.add(i))
			return -1;
		if (parents.get(i) == null)
			return i;
		int root = find(parents.get(i));
		parents.put(i, root);
		return root;
	}

}
