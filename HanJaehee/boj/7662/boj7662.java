import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 이중우선순위큐3 {

    static TreeMap<Integer, Integer> treeMap = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test = 1; test <= T; test++) {
            int k = Integer.parseInt(br.readLine());
            treeMap.clear();

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                process(st.nextToken(), Integer.parseInt(st.nextToken()));
            }

            if (treeMap.isEmpty())
                System.out.println("EMPTY");
            else
                System.out.println(treeMap.lastKey() + " " + treeMap.firstKey());

        }
    }

    public static void process(String cmd, int num) {
        if (cmd.equals("I")) {
            treeMap.merge(num, 1, (ex, init) -> ex + init);
        } else { // D
            if (treeMap.isEmpty()) return;

            int key = num == 1 ? treeMap.lastKey() : treeMap.firstKey();

            if (treeMap.get(key) == 1) treeMap.remove(key);
            else treeMap.merge(key, 1, (ex, init) -> ex - init);
        }
    }
}
