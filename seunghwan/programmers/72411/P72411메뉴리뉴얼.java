import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P72411메뉴리뉴얼 {
    
	
	Map<String, Integer> maps [] = new Map[21];
	public String[] solution(String[] orders, int[] course) {
        for(int i = 0 ; i < 21 ; i++)
        	maps[i] = new HashMap<String, Integer>();
        for(String order : orders) {
        	char [] orderList = order.toCharArray();
        	Arrays.sort(orderList);
        	comb(orderList, 0);
        }
        List<String> answerList = new ArrayList<String>();
        for(int i : course) {
        	Map<String, Integer> target = maps[i];
        	int max = -1;
        	List<String> MaxList = new ArrayList<String>();
        	for(String key : target.keySet()) {
        		int cnt = target.get(key);
        		if(cnt == 1)
        			continue;
        		if(max < cnt) {
        			MaxList.clear();
        			max = cnt;
        			MaxList.add(key);
        		}else if(max == cnt) {
        			MaxList.add(key);
        		}
        	}
        	answerList.addAll(MaxList);
        }
        Collections.sort(answerList);
        
        return answerList.toArray(new String[answerList.size()]);
    }
	boolean sel[] = new boolean[10];
	void comb(char[] order,int idx) {
		if(idx == order.length) {
			String key = "";
			for(int i = 0 ; i < order.length; i++) {
				if(sel[i])
					key += order[i];
			}
			int value =  maps[key.length()].getOrDefault(key, 0)+1;
			maps[key.length()].put(key, value);
			return;
		}
		sel[idx] = true;
		comb(order,idx+1);
		sel[idx] = false;
		comb(order,idx+1);
	}
    
}


