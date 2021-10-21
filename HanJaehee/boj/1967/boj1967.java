import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 트리의지름 {
    static class Node {
        int n, v;

        Node(int n, int v) {
            this.n = n;
            this.v = v;
        }
    }

    static List<Node>[] list;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if(N == 1){
            System.out.println(0);
            return;
        }

        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

        for(int i=0; i< N-1; i++){
            String[] tmp = br.readLine().split(" ");
            int start = Integer.parseInt(tmp[0]);
            int end = Integer.parseInt(tmp[1]);
            int v = Integer.parseInt(tmp[2]);

            list[start].add(new Node(end, v));
            list[end].add(new Node(start, v));
        }

        visit = new boolean[N+1];
        int maxNode = getMax(1).n;

        visit = new boolean[N+1];
        System.out.println(getMax(maxNode).v);
    }

    public static Node getMax(int s){
        int max = 0;
        int maxNode = 0;
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(s, 0));
        visit[s] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i=0; i<list[now.n].size(); i++){
                Node node = list[now.n].get(i);

                if(!visit[node.n]){
                    int nowWeight = now.v + node.v;
                    q.add(new Node(node.n, nowWeight));

                    if(max < nowWeight){
                        max = nowWeight;
                        maxNode = node.n;
                    }
                    visit[node.n] = true;
                }
            }
        }
        return new Node(maxNode, max);
    }
}
