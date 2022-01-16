import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 동전 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int[] coins, cache;
        for (int test = 0; test < T; test++) {
            int N = Integer.parseInt(br.readLine());
//            coins = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            coins = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) coins[i] = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(br.readLine());

            cache = new int[M + 1];
            cache[0] = 1;
            for (int coin : coins) {
                for (int i = coin; i <= M; i++) {
                    cache[i] += cache[i - coin];
                }
            }

            System.out.println(cache[M]);
        }
    }
}
