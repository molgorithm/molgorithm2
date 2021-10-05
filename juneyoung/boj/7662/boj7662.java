package baekjoon;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class boj7662 {
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
 