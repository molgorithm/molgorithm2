# 백준 3584 가장 가까운 공통 조상

[문제 링크](https://www.acmicpc.net/problem/3584)

## 1. 설계 로직

1. 배열을 자식 -> 부모 로 연결해주고 상향식으로 탐색. ex) tmp[son] = parent
2. 두 노드 중 첫번째 노드의 모든 부모를 set에 넣어준다.( 본인 포함, 두 노드가 부모, 자식 관계일 시 부모가 리턴됨 )
3. 두 번째 노드는 본인을 포함한 부모들이 set에 담겨있는지 확인하고, 담겨 있다면 그 수를 정답으로 리턴한다.

- 시간복잡도 : N

## 2. 코드

```java
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

    static Queue<Integer> q = new LinkedList<>();
    static Set<Integer> parentSet = new HashSet<>();

    public static int getCommonParent(int one, int two){
        q.clear();
        parentSet.clear();

        getParent(one);
        return getParent(two);
    }

    public static int getParent(int son){
        if(!parentSet.add(son))
            return son;
        q.add(son);
        while(!q.isEmpty()){
            int tmp = q.poll();
            int parent = tree[tmp][0];

            if(parent == 0) break;
            else{
                if(!parentSet.add(parent))
                    return parent;
                else
                    q.add(parent);
            }
        }
        return -1;
    }
}


```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
