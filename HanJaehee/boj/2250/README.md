# 백준 2250 트리의너비와높이

[문제 링크](https://www.acmicpc.net/problem/2250)

## 1. 설계 로직

1. 1부터 N까지를 더한 후 자식으로 언급된 모든 노드번호를 제거한다. 그러면, 루트번호만 남게된다.
2. 트리를 중위순회로 탐색하며, 열(인덱스)와 레벨을 기록한다.
3. 기록할 때, 각 레벨에서 최소,최대 인덱스를 구한다.
4. 마지막에 각 레벨의 너비를 구하면서, 최대 너비와 넓이를 구한다.

- 시간복잡도 : N

## 2. 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 트리의너비와높이 {

    static class Node {
        Node left;
        Node right;
    }

    static class Tree {
        void inOrder(Node cur, int level) {
            if (cur != null) {
                if (cur.left != null) inOrder(cur.left, level + 1);
                int curIdx = idx++;
                min_idx[level] = Math.min(min_idx[level], curIdx);
                max_idx[level] = Math.max(max_idx[level], curIdx);
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
            nodeList.add(new Node());
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


```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
