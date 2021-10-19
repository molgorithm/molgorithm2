import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 문추시1_1 {

    static TreeSet<Problem> treeSet = new TreeSet<>();
    static Map<Integer, Integer> map = new HashMap<>();
    static class Problem implements Comparable<Problem>{
        int num, nan2do;
        Problem(int num, int nan2do){
            this.num = num;
            this.nan2do = nan2do;
        }

        @Override
        public int compareTo(Problem p){
            if(this.nan2do == p.nan2do)
                return this.num - p.num;
            return this.nan2do - p.nan2do;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int nan2do = Integer.parseInt(st.nextToken());
            add(num, nan2do);
        }

        int M = Integer.parseInt(br.readLine());
        String cmd;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            cmd = st.nextToken();
            if(cmd.equals("add")){
                int num = Integer.parseInt(st.nextToken());
                int nan2do = Integer.parseInt(st.nextToken());
                add(num, nan2do);
            }else{
                process(cmd, Integer.parseInt(st.nextToken()));
            }
        }
    }

    public static void add(int num, int nan2do){
        treeSet.add(new Problem(num ,nan2do));
        map.put(num, nan2do);
    }

    public static void process(String cmd, int target){
        if(cmd.equals("recommend")){
            if(target == 1){ // 최대
                System.out.println(treeSet.last().num);
            }else{
                System.out.println(treeSet.first().num);
            }
        }else{ //solved
            treeSet.remove(new Problem(target, map.get(target)));
            map.remove(target);
        }
    }
}
