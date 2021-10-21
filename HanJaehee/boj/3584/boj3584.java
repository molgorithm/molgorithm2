import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 가장가까운공통조상 {

    static int[][] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        int N; // 노드의 수

        for (int test = 1; test <= T; test++) {
            N = Integer.parseInt(br.readLine());

            tree = new int[N+1][1];

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int son = Integer.parseInt(st.nextToken());

                // 자식에 부모를 연결한다.
                tree[son][0] = parent;
            }

            st = new StringTokenizer(br.readLine());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());

            System.out.println(getCommonParent(one, two));
        }
    }

    static Set<Integer> parentSet = new HashSet<>();

    public static int getCommonParent(int one, int two){
        parentSet.clear();

        getParent(one);
        return getParent(two);
    }

    public static int getParent(int son){
        if(!parentSet.add(son))
            return son;
        while(true){
            int parent = tree[son][0];

            if(parent == 0) break;
            else{
                if(!parentSet.add(parent))
                    return parent;
                else
                    son = parent;
            }
        }
        return -1;
    }
}
