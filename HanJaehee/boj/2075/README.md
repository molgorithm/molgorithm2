# 백준 2075 N번째큰수

[문제 링크](https://www.acmicpc.net/problem/2075)

## 1. 설계 로직

1. 모든 수를 pq에 넣고
2. N번째 수를 빼냈다.

## 2. 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N번째큰수 {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N * N; ++i) {
            if (i % N == 0) st = new StringTokenizer(br.readLine());
            pq.add(Integer.parseInt(st.nextToken()));
        }

        int num = 0;
        for (int i = 0; i < N; i++) num = pq.poll();
        System.out.println(num);
    }
}

```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
