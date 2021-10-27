package Gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class G5_14675단절점과단절선 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int cnt[] = new int[N + 1];
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cnt[Integer.parseInt(st.nextToken())]++;
			cnt[Integer.parseInt(st.nextToken())]++;
		}
		int q = Integer.parseInt(br.readLine());
		for (int i = 0; i < q; i++) {
			st= new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			bw.write(a == 2 ? "yes" : cnt[b] > 1 ? "yes" : "no");
			bw.newLine();
		}
		bw.close();
	}
}
