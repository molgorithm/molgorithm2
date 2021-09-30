package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G5_7662이중우선순위큐 {
	static class node{
		int num;
		boolean isok;
		public node(int num, boolean isok) {
			this.num = num;
			this.isok = isok;
		}
	}
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			int k = Integer.parseInt(br.readLine());
			
	        PriorityQueue<node> minPq = new PriorityQueue<node>(new Comparator<node>() {
	        	@Override
	        	public int compare(node o1, node o2) {
	        		return Integer.compare(o1.num,o2.num);
	        	}
			});
	        PriorityQueue<node> maxPq = new PriorityQueue<node>(new Comparator<node>() {
	        	@Override
	        	public int compare(node o1, node o2) {
	        		return Integer.compare(o2.num, o1.num);
	        	}
			});
			
			while (k-- != 0) {
				st = new StringTokenizer(br.readLine());
				String oper = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
	        	if(oper.charAt(0) == 'I') {
	        		node nNode = new node(num, true);
	        		minPq.add(nNode);
	        		maxPq.add(nNode);
	        	}else {
	        		if(num == 1) 
	        			poll(maxPq);
	        		else 
	        			poll(minPq);
	        	}
			}
			Integer max = poll(maxPq);
			Integer min = poll(minPq);
	        if(max == null )
	        	System.out.println("EMPTY");
	        else {
	        	min = min == null ? min = max : min;
	        	System.out.println(max +" "+min);
	        }
		}
	}
	
    static Integer poll(PriorityQueue<node> pq) {
		while(!pq.isEmpty()) {
			node now = pq.poll();
			if(now.isok) {
				now.isok = false;
				return now.num;
			}
		}
		return null;
    }
}
