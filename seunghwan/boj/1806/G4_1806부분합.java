package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_1806부분합 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int [] arr = new int [N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		int start = 0;
		int end = 0;
		int sum = arr[start];
		int min = 987654321;
		while (true) {
			if (sum >= S) {
				min = Math.min(min, end - start + 1);
				sum -= arr[start++];
			} else {
				if(end == N-1)
					break;
				sum += arr[++end];
			}
		}
		System.out.println(min == 987654321 ? 0 : min);
	}

}
