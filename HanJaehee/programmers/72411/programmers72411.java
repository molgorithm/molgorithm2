import java.util.*;


class Solution {
    
    public String[] solution(String[] orders, int[] course) {
        List<String> result = new ArrayList<>();
        
        Map<String, Integer> map = new HashMap<>();
        
        for(int i=0; i<orders.length; ++i){
            for(int j=0; j<course.length; ++j){
                char[] orderChar = orders[i].toCharArray();
                Arrays.sort(orderChar);
                getOrders(orderChar, new boolean[orderChar.length], 0, orderChar.length, course[j], map);
            }
        }
        
        int[] max = new int[course[course.length-1]+1];
        for(String key : map.keySet()){
            int len = key.length();
            max[len] = Math.max(max[len], map.get(key));
        }
        
        for(String key : map.keySet()){
            int len = key.length();
            if(max[len] >= 2 && max[len] == map.get(key)) result.add(key);
        }
        
        String[] answer = new String[result.size()];
        result.toArray(answer);
        
        Arrays.sort(answer);
        
        return answer;
        
    }
    
    public void getOrders(char[] orderChar, boolean[] visit, int start, int n, int r, Map<String, Integer> map){
        
        if(r == 0){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<visit.length; ++i){
                if(!visit[i]) continue;
                sb.append(orderChar[i]);
            }
            String key = sb.toString();
            
            if(map.containsKey(key)) map.put(key, map.get(key)+1);
            else map.put(key, 1);
            
            return;
        }
        
        for(int i=start; i<n; ++i){
            visit[i] = true;
            getOrders(orderChar, visit, i+1, n, r-1, map);
            visit[i] = false;
        }
    }
}