import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 행복유치원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int answer = 0;
        int[] mans = new int[N];
        int[] diff = new int[N - 1];

        st = new StringTokenizer(br.readLine());
        mans[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < N; i++) {
            mans[i] = Integer.parseInt(st.nextToken());
            diff[i - 1] = mans[i] - mans[i - 1];
        }

        Arrays.sort(diff);

        for (int i = 0; i < N - K; i++) answer += diff[i];
        System.out.println(answer);
    }
}