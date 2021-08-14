
public class P64062징검다리건너기 {
	
	//파라메틱 서치
	public int solution(int[] stones, int k) {
		int min = 987654321;
		int max = 0;
		
		for(int i = 0 ; i < stones.length ; i++) {
			min = Math.min(min, stones[i]);
			max = Math.max(max, stones[i]);
		}
		int answer = 987654321;
		while(min <= max) {
			int mid = (min+max)/2;
			if(check(stones,mid,k)) {
				min = mid+1;
			}else {
				max = mid-1;
				answer = Math.min(mid, answer);
			}
				
		}
		
		return answer;
	}
	public boolean check(int stones[],int mid,int k) {
		int cnt = 0;
		for(int i :stones) {
			if(i - mid <= 0)
				cnt++;
			else
				cnt = 0;
			if(cnt == k)
				return false;
		}
		return true;
	}
}

