# 백준 1068 트리

[문제 링크](https://www.acmicpc.net/problem/1068)

## 1. 설계 로직

1. N번째 값의 부모들을 배열에 넣어준다.
2. 삭제할 인덱스의 자식을 지우고, 해당 값을 가지는 부모들에게도 지워준다.

- 시간복잡도 : N

## 2. 코드

```java
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

```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
