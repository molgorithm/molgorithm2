class Solution {
    public int solution(String name) {
        int answer = 0;
        int count = name.length();
        char[] charName = name.toCharArray();
        boolean[] isFinish = new boolean[charName.length];
        
        int now = 0;
        int next = 0;
        for(int i=0; i<charName.length; ++i){ // 알파벳 변경
            if(charName[i] == 'A') count--;
            answer += Math.min(charName[i] - 'A', 'Z' - charName[i] +1);
        }
        
        for(int i=0; i<count; ++i){ // 이동 거리
            int left = 0, right = 0;
            int leftCount = 0, rightCount = 0;
            
            int idx = now;
            while(leftCount < charName.length){
                if(idx == isFinish.length) idx = 0;
                if(isFinish[idx] || charName[idx] == 'A'){
                    idx++;
                    leftCount++;
                }else if(!isFinish[idx]){
                    right = idx;
                    break;
                }
            }
            idx = now;
            while(rightCount < charName.length){
                if(idx == -1) idx = isFinish.length-1;
                if(isFinish[idx] || charName[idx] == 'A'){
                    idx--;
                    rightCount++;
                }else if(!isFinish[idx]){
                    left = idx;
                    break;
                }
            }
            
            if(leftCount >= rightCount){
                answer += rightCount;
                now = right;
            }else{
                answer += leftCount;
                now = left;
            }
            isFinish[now] = true;
        }
        return answer;
    }
}