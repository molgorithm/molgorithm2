package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_1068트리_find {

	static int target;
	static int parents[];
	static boolean haveChild[];
	public static void main(String[] args) throws Exception {
		int answer = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		parents = new int [N];
		haveChild = new boolean [N];
		st = new StringTokenizer(br.readLine());
		target = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			if(i==target) {
				st.nextToken();
				parents[i] = i;
				continue;
			}
			parents[i] = Integer.parseInt(st.nextToken());
			if(parents[i] != -1)
				haveChild[parents[i]] = true;
		}
		for(int i = 0 ; i < N ; i++) {
			if(haveChild[i])
				continue;
			if(find(i) != target)
				answer++;
		}
		System.out.println(answer);
	}
	static int find(int num){
		if(parents[num] == -1 || parents[num] == target)
			return parents[num]; 
		return parents[num] = find(parents[num]);
	}
}
