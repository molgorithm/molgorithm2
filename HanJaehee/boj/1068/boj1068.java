import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 트리 {

    static int result = 0, N;
    static int[] parent;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        parent = new int[N];
        visited = new boolean[N];

        int root = 0;
        for(int i=0; i<N; i++){
            parent[i] = Integer.parseInt(st.nextToken());
            if(parent[i] == -1) root = i;
        }

        int delete = Integer.parseInt(br.readLine());

        deleteNode(delete);

        solution(root);

        System.out.println(result);
    }

    static void deleteNode(int del){
        parent[del] = -2;
        for(int i=0; i<N; i++){
            if(parent[i] == del) deleteNode(i);
        }
    }

    static void solution(int s){
        boolean isLeaf = true;
        visited[s] = true;
        if(parent[s] != -2){
            for(int i=0; i<N; i++){
                if(parent[i] == s && !visited[i]){
                    solution(i);
                    isLeaf = false;
                }
            }
            if(isLeaf) result++;
        }
    }
}
