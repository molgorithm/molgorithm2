# 백준 11000 강의실 배정

[문제 링크](https://www.acmicpc.net/problem/11000)

## 1. 설계 로직

1. 강의실들을 배열에 담고, 시작 시간, 끝 시간 순으로 정렬한다.
2. PQ에 시간시간과 끝시간이 가장 빠른 순으로 수업의 끝 시간을 넣는다.
3. 그럼 PQ에서는 끝 시간을 기준으로 오름차순으로 정렬되는데, 그 중 가장 빨리 끝나는 시간이 항상 peek에 존재한다.
4. peek에서는 다음에 들어올 수업의 시작시간과 비교해 `peek(끝 시간) <= 시작 시간` 일 경우 해당 끝 시간을 poll한다. 그렇지 않은 경우는 poll하지 않는다. 위 조건을 만족하는 경우가 하나의 강의실을 사용한다는 의미이다. (하나 poll -> push : 한개만 남음)
5. 들어오는 모든 끝 시간을 pq에 넣음으로써, 위에서 poll되지 않았다면, 강의실이 추가로 사용됨을 의미한다.
6. 위 과정들을 모두 끝낸 후 pq에 남은 것들을 카운트 하면 최소로 사용된 강의실을 의미한다.

- 시간복잡도 : N

## 2. 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 강의실배정 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[][] subjects = new int[N][2];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            subjects[i][0] = Integer.parseInt(st.nextToken());
            subjects[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(subjects, (o1, o2)-> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(subjects[0][1]);

        for(int i=1; i<N; i++){
            if(!pq.isEmpty() && pq.peek() <=subjects[i][0]){
                pq.poll();
            }

            pq.offer(subjects[i][1]);
        }

        System.out.println(pq.size());
    }
}


```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
