import java.util.Comparator;
import java.util.PriorityQueue;

public class P42628이중우선순위큐 {
	class node{
		int num;
		boolean isok;
		public node(int num, boolean isok) {
			this.num = num;
			this.isok = isok;
		}
	}
    public int[] solution(String[] operations) {
        
        PriorityQueue<node> minPq = new PriorityQueue<node>(new Comparator<node>() {
        	@Override
        	public int compare(node o1, node o2) {
        		return o1.num-o2.num;
        	}
		});
        PriorityQueue<node> maxPq = new PriorityQueue<node>(new Comparator<node>() {
        	@Override
        	public int compare(node o1, node o2) {
        		return o2.num-o1.num;
        	}
		});
        for(String s : operations) {
        	String [] split = s.split(" ");
        	if(split[0].charAt(0) == 'I') {
        		node nNode = new node(Integer.parseInt(split[1]), true);
        		minPq.add(nNode);
        		maxPq.add(nNode);
        	}else {
        		if(split[1].charAt(0) == '1') 
        			poll(maxPq);
        		else 
        			poll(minPq);
        	}
        }
        int max = poll(maxPq);
        int min = poll(minPq);
        min = min == 0 ? min = max : min;
        int[] answer = {max,min};
        return answer;
    }
    int poll(PriorityQueue<node> pq) {
		while(!pq.isEmpty()) {
			node now = pq.poll();
			if(now.isok) {
				now.isok = false;
				return now.num;
			}
		}
		return 0;
    }
}
