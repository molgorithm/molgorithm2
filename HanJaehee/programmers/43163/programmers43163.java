public class Solution {
    
    int answer = Integer.MAX_VALUE;
    boolean[] v;
    public int solution(String begin, String target, String[] words) {
        v = new boolean[words.length];
        dfs(begin, target, words, 0);
        if(answer == Integer.MAX_VALUE) return 0;
        return answer;
    }
    
    public void dfs(String now, String target, String[] words, int count){
        if(now.equals(target)){
            answer = Math.min(answer, count);
            return;
        }
        
        for(int i=0; i<words.length; ++i){
            if(!v[i] && check(now, words[i])){
                v[i] = true;
                dfs(words[i], target, words, count+1);
                v[i] = false;
            }
        }
    }
    
    public boolean check(String now, String target){
        int count = 0;
        for(int i=0; i<now.length(); ++i){
            if(now.charAt(i) != target.charAt(i)) count++;
        }
        if(count == 1) return true;
        return false;
    }
     
}