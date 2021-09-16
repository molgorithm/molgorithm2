import java.util.*;

class Solution {
    List<String> answer = new ArrayList<>();
    public String[] solution(String[][] tickets) {

        dfs(tickets, 0, "ICN", "ICN", new boolean[tickets.length]);
        Collections.sort(answer);
        return answer.get(0).split(" ");
    }
    public void dfs(String[][] tickets, int idx, String now, String total, boolean[] visit){
        if(idx == tickets.length){
            answer.add(total);
            return;
        }
        
        for(int i=0; i< tickets.length; ++i){
            if(!visit[i] && tickets[i][0].equals(now)){
                visit[i] = true;
                dfs(tickets, idx+1, tickets[i][1], total + " " + tickets[i][1], visit);
                visit[i] = false;
            }
        }
    }
    
}