# 백준 13164 행복 유치원

[문제 링크](https://www.acmicpc.net/problem/13164)

## 1. 설계 로직

1.  이미 정렬된 사람들을 입력받고, 인접한 두 사람의 키 차이를 배열에 저장하고 오름차순으로 정렬한다.
2.  정렬된 배열에서 N-K (N에서 K를 빼면 K개의 조합의 수이다) 개를 선택해 합하면, 답이나온다.
3.  [1,3,4]를 예로 [1,3] , [3,4] 인접한 두 그룹의 키 차이는 각각 2, 1이다.
4.  그리고 [1,3,4] 가 그룹일 때 키 차이는 3이다. 즉, 두 그룹의 차이의 합은 한 그룹이 되었을 때의 차이와 같다.

- 시간복잡도 : N

## 2. 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 행복유치원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int answer = 0;
        int[] mans = new int[N];
        int[] diff = new int[N - 1];

        st = new StringTokenizer(br.readLine());
        mans[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < N; i++) {
            mans[i] = Integer.parseInt(st.nextToken());
            diff[i - 1] = mans[i] - mans[i - 1];
        }

        Arrays.sort(diff);

        for (int i = 0; i < N - K; i++) answer += diff[i];
        System.out.println(answer);
    }
}


```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
