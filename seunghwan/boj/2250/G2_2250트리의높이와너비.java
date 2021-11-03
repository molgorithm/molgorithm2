package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class G2_2250트리의높이와너비 {
	static class node {
		int left;
		int right;
	}

	static node nodeArr[];
	static ArrayList<Integer> [] depthIdx;
	static int idx = 1;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int root = 0;
		nodeArr = new node[N + 1];
		depthIdx = new ArrayList[N+1];
		for (int i = 1; i < N + 1; i++) {
			nodeArr[i] = new node();
			depthIdx[i] = new ArrayList<Integer>();
		}
		boolean noRoot[] = new boolean[N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			if(left != -1) noRoot[left] = true;
			if(right != -1) noRoot[right] = true; 
			nodeArr[num].left = left;
			nodeArr[num].right = right;
		}

		for (int i = 1; i < N + 1; i++)
			if (!noRoot[i]) {
				root = i;
				break;
			}
		findIdx(root, 1);
		int max = -1;
		int maxDepth = 0;
				
		for(int i = 1 ; i < N+1; i ++) {
			if(depthIdx[i].isEmpty())
				continue;
			int size =depthIdx[i].get(depthIdx[i].size()-1)-depthIdx[i].get(0)+1;
			if(max < size) {
				max = size;
				maxDepth = i;
			}
		}
		System.out.println(maxDepth +" "+max);
	}
	static void findIdx(int num,int depth) {
		node now = nodeArr[num];
		if(now.left != -1) findIdx(now.left, depth+1);
		depthIdx[depth].add(idx++);
		if(now.right != -1) findIdx(now.right, depth+1);
	}
}
