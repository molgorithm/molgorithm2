import java.util.Arrays;

public class P43105정수삼각형 {

    public int solution(int[][] a) {
        for(int i =1 ; i < a.length ; i++)
            for(int j = 0 ; j < a[i].length ; j++)
                a[i][j] += j==0 ? a[i-1][j] : j == a[i].length-1 ? a[i-1][j-1] : Math.max(a[i-1][j-1],a[i-1][j]);
        return Arrays.stream(a[a.length-1]).max().getAsInt();
    }

}