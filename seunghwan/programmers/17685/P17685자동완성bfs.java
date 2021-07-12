import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class P17685자동완성bfs {
	static class node{
		HashMap<Character, HashMap> hash;
		int nowDepth;
		int clickDepth;
		public node(HashMap<Character, HashMap> hash, int nowDepth, int clickDepth) {
			super();
			this.hash = hash;
			this.nowDepth = nowDepth;
			this.clickDepth = clickDepth;
		}
	}
	public int solution(String[] words) {
		
		HashMap<Character, HashMap> hash = new HashMap();
		int answer = 0;

		for (String s : words) {
			s += "E";
			HashMap<Character, HashMap>  targetHash = hash;
			for (char c : s.toCharArray()) {
				if (targetHash.get(c) == null)
					targetHash.put(c, new HashMap());
				targetHash =  targetHash.get(c);
			}
		}
		
		Queue<node> que = new LinkedList<node>();
		que.add(new node(hash, 1, 1));
		while(!que.isEmpty()) {
			node now = que.poll();
			for(char c : now.hash.keySet()) {
				int size = now.hash.get(c).size();
				if(size == 1) 
					que.add(new node(now.hash.get(c),now.nowDepth+1,now.clickDepth));
				else if(size ==0)  
					answer += now.nowDepth == now.clickDepth ? now.clickDepth-1: now.clickDepth;
				else
					que.add(new node(now.hash.get(c),now.nowDepth+1,now.nowDepth+1));
			}
		}
		return answer;
	}
}
