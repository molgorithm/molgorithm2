import java.util.Arrays;
import java.util.Comparator;

public class Solution {
	
    public int solution(int[][] routes) {
        int answer = 0;
        int nowPlace = -30001;
        
        Arrays.sort(routes,new Comparator<int []>() {
        	@Override
        	public int compare(int[] o1, int[] o2) {
        		return o1[1] - o2[1];
        	}
		});
        //도착지점 앞인 얘 하나 꺼내고
        //현재 위치보다 시작이 앞서 있으면 버리고
        //시작이 뒤면 현재 위치를 이 녀석의 도착지점으로 바꾸기
        for(int i = 0 ; i < routes.length; i++) {
        	int [] now = routes[i];
        	if(now[0] <= nowPlace)
        		continue;
        	answer++;
        	nowPlace = now[1];
        }
        return answer;
    }
}