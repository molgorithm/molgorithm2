class Solution {
    public int solution(int[][] t) {
        int len = t[t.length-1].length;
        int[][] result = new int[len][len];
        result[0][0] = t[0][0]; // 초기값 세팅
        
        for(int i=0; i<len-1; ++i){
            for(int j=0; j<t[i].length; ++j){
                result[i+1][j] = Math.max(result[i][j] + t[i+1][j], result[i+1][j]);
                result[i+1][j+1] = Math.max(result[i][j] + t[i+1][j+1], result[i+1][j+1]);
            }
        }
        
        int max = -1;
        for(int i=0; i<len; ++i){
            max = Math.max(max, result[len-1][i]);
        }
        
        // print(result, len);
        
        return max;
    }
    
    public void print(int[][] result, int len){
        for(int i=0; i<len; ++i){
            for(int j=0; j<len; ++j){
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}