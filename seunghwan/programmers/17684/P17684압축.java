import java.util.ArrayList;
import java.util.HashMap;

public class P17684압축 {

    public int[] solution(String msg) {
    	HashMap<String, Integer> dic = new HashMap<String, Integer>();
    	ArrayList<Integer> answerList = new ArrayList<Integer>();
    	for(char i = 'A' ; i <= 'Z'; i++)
    		dic.put(i+"", i-'A'+1);
    	int dicNum = 27;
    	for(int i = 0 ; i < msg.length();i++) {
    		for(int j = 1 ; i+j<= msg.length() ; j++) {
    			String target = msg.substring(i, i+j);
    			if(dic.get(target) == null) {
    				answerList.add(dic.get(target.substring(0, target.length()-1)));
    				dic.put(target, dicNum++);
    				i += j-2;
    				break;
    			}
    			if(i+j == msg.length()) {
     				answerList.add(dic.get(target));
    				i += j-1;
    			}
    		}
    	}
    	
    	
        int[] answer = new int[answerList.size()];
        for(int i = 0; i < answerList.size(); i++) 
        	answer[i] = answerList.get(i);
        return answer;
    }
    
}

