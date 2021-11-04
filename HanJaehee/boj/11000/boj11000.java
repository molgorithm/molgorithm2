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
