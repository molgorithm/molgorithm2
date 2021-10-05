# 백준 7662 이중우선순위큐

[문제 링크](https://www.acmicpc.net/problem/7662)

## 1. 설계 로직

1. 고려해야할 사항 
   - 연산의 개수 (1,000,000)  -> 선형 시간복잡도로 구현
2. 설계로직
   1. 최대값과 최소값을 같이 다뤄야 하기 때문에 우선순위큐를 2개 사용 (minQueue, maxQueue)
   2. 명령어가 'I' 이면 큐에 각각 add
   3. 명령어가 'D'이면 이후에 들어오는 숫자값에 따라서 (min or max Queue에서 제거)
      1. 해당 노드가 살아있는지 확인 후 살아있으면 큐에서 제거
   4. 모든 명령을 다 수행하면 minQ or maxQ중 하나로 정답을 구함.
      1. minQ나 maxQ나 결과가 동일하기 때문
      2. 살아남은 숫자 필터링 후
      3. Integer로 변환
      4. 리스트로 변환
      5. max = 결과 리스트의 마지막 인덱스 , min은 결과 리스트의 0번 인덱스
3. 시간복잡도: O ( N x logN ) 

## 2. 코드

```java
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {
    static int T, k;
    static PriorityQueue<Point> maxQ = new PriorityQueue<>((o1, o2) -> o2.num - o1.num);
    static PriorityQueue<Point> minQ = new PriorityQueue<>((o1, o2) -> o1.num - o2.num);

    static class Point {
        int num;
        boolean status = false;

        public Point(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }

        public void changeStatus() {
            status = true;
        }

        public boolean delete() {
            if (!status) {
                changeStatus();
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            init();
            k = Integer.parseInt(br.readLine());
            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                executeOrder(st.nextToken().charAt(0), Integer.parseInt(st.nextToken()));
            }

            List<Integer> answer = minQ.stream()
                    .filter(Point::delete)
                    .map(Point::getNum)
                    .sorted()
                    .collect(Collectors.toList());

            int size = answer.size();
            System.out.println(size == 0 ? "EMPTY" : answer.get(size - 1) + " " + answer.get(0));
        }
    }

    private static void init() {
        maxQ.clear();
        minQ.clear();
    }

    private static void executeOrder(Character c, int num) {
        if (c == 'I') {
            Point p = new Point(num);
            minQ.add(p);
            maxQ.add(p);
        } else {
            if (num == 1) cal(maxQ);
            else cal(minQ);
        }
    }

    private static void cal(PriorityQueue<Point> q) {
        while (!q.isEmpty())
            if (q.poll().delete()) break;
    }
}

```

## 3. 후기

- 카카오 문제 풀 때 승환님이 푸신 풀이가 생각나서 비슷하게 풀어봤습니다.
