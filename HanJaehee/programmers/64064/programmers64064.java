import java.util.*;

class Solution {
    Map<String, Integer> map = new HashMap<>();
    Set<Integer> set = new HashSet<>();
    List<String>[] arr;
    
    public int solution(String[] user_id, String[] banned_id) {
        
        arr = new ArrayList[banned_id.length];
        
        for(int i=0; i<user_id.length; ++i){
            map.put(user_id[i], i);
        }
        
        for(int i=0; i<banned_id.length; ++i){
            arr[i] = new ArrayList<>();
            getBannedList(arr, user_id, banned_id, i);
        }
     
        recursive(0, 0);
        
        return set.size();
    }
    
    public void recursive(int idx, int sum){
        if(idx == arr.length){
            set.add(sum);
            return;
        }
        
        for(int i=0; i<arr[idx].size(); ++i){
            int now = map.get(arr[idx].get(i));
            
            if( (sum & (1 << now)) > 0 ) continue;
            
            recursive(idx+1, sum | ( 1 << now ));
        }
    }
    
    public void getBannedList(List<String>[] arr, String[] user_id, String[] banned_id, int idx){
        
        String banId = banned_id[idx];
        boolean flag = false;
        
        for(int i=0; i< user_id.length; ++i){
            String userId = user_id[i];
            if(banId.length() != userId.length()) continue;
            
            flag = false;
            
            for(int j=0; j< banId.length(); ++j){
                char ch = banId.charAt(j);
                if(ch != '*' && userId.charAt(j) != ch){
                    flag = true;
                    break;
                } 
            }
            
            if(flag) continue;
            
            arr[idx].add(userId);
        }
    }
}