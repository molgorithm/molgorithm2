import java.util.PriorityQueue;

public class P42626더맵게 {
	
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for(int i : scoville)
        	pq.add(i);
        boolean flag = true;
        while(!pq.isEmpty()) {
            
        	int f = pq.poll();
        	if(f >=K)
        		break;
        	if(pq.isEmpty()) {
        		flag = false;
        		break;
        	}
        	int s = pq.poll();
        	pq.add(f+s*2);
        	answer++;
        }
        return flag ? answer : -1;
    }

}