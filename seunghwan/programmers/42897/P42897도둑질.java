import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        System.out.println(solution(new int[] {1,2,3}));
    }
    static public int solution(int[] money) {
        int [][] dp = new int[2][money.length]; // dp[0] = 0번째 받은거, dp[1] = 0번째 안받은거
        dp[0][0] = money[0];
        dp[1][1] = money[1];
        dp[0][2] = money[0]+money[2];
        dp[1][2] = money[2];
        if(money.length == 3)
            return Arrays.stream(money).max().getAsInt();
        for(int i = 3 ; i < money.length ; i++){
            if(i != money.length-1)
                dp[0][i] = Math.max(dp[0][i-2],dp[0][i-3])+money[i];
            dp[1][i] = Math.max(dp[1][i-2],dp[1][i-3])+money[i];
        }
        int max0 = Math.max(dp[0][money.length-2],dp[0][money.length-3]);
        int max1 = Math.max(dp[1][money.length-1],dp[1][money.length-2]);
        return Math.max(max0,max1);
    }
}