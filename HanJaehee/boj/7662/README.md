# Baekjoon 7662 이중 우선순위 큐

[문제 링크](https://www.acmicpc.net/problem/7662)

## 1. 설계 로직

1. TreeMap을 이용해 오름차순 정렬과 맨마지막, 맨처음 접근, 개수 카운트를 해결했다.
2. key는 우선순위를 의미하고, value는 해당 숫자가 들어간 개수를 의미해 중복된 숫자에 대한 연산을 줄여준다.

## 2. 코드

```java
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

```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
