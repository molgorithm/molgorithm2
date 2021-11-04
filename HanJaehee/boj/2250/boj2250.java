import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 트리의너비와높이 {

    static class Node {
        int num;
        int level;
        int idx;
        Node left;
        Node right;

        Node(int num) {
            this.num = num;
        }
    }

    static class Tree {
        void inOrder(Node cur, int level) {
            if (cur != null) {
                if (cur.left != null) inOrder(cur.left, level + 1);
                cur.idx = idx++;
                cur.level = level;
                min_idx[level] = Math.min(min_idx[level], cur.idx);
                max_idx[level] = Math.max(max_idx[level], cur.idx);
                if (cur.right != null) inOrder(cur.right, level + 1);
            }
        }
    }

    static int idx = 1;

    static List<Node> nodeList = new ArrayList<>();
    static int[] min_idx, max_idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        min_idx = new int[N+1];
        max_idx = new int[N+1];

        int root = N * (N + 1) / 2;
        for (int i = 0; i <= N; i++){
            nodeList.add(new Node(i));
            min_idx[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < N; i++) {
            String[] tmp = br.readLine().split(" ");
            Node parentNode = nodeList.get(Integer.parseInt(tmp[0]));
            int left = Integer.parseInt(tmp[1]);
            int right = Integer.parseInt(tmp[2]);
            if (left != -1){
                parentNode.left = nodeList.get(left);
                root -= left;
            }
            if (right != -1){
                parentNode.right = nodeList.get(right);
                root -= right;
            }
        }

        Tree tree = new Tree();
        tree.inOrder(nodeList.get(root), 1);

        int maxLevel = -1;
        int maxLength = -1;

        for(int i=1; i<=N; i++){
            if(min_idx[i] == Integer.MAX_VALUE) break;
            int length = max_idx[i] - min_idx[i] + 1;
            if(maxLength < length){
                maxLength = length;
                maxLevel = i;
            }
        }

        System.out.println(maxLevel + " " + maxLength);
    }
}
