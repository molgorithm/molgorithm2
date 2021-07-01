
public class P17676추석트래픽 {
	//1000*60*60*24 = 86400000
	public int solution(String[] lines) {
        int startTable[] = new int[24*60*60*1000];
        int endTable[] = new int[24*60*60*1000];
        for(String time : lines) {
        	int endTime = 0;
        	String[] times = time.split(" ");
        	String[] HMS = times[1].split(":");
        	endTime += Integer.parseInt(HMS[0])*60*60*1000;
        	endTime += Integer.parseInt(HMS[1])*60*1000;
        	String [] ms = HMS[2].split("\\.");
        	endTime += Integer.parseInt(ms[0])*1000;
        	endTime += Integer.parseInt(ms[1]);
        	int sec = (int)(Double.parseDouble(times[2].split("s")[0])*1000);
        	int startTime = endTime - sec +1;
        	if(startTime<0)
        		startTime= 0;
        	startTable[startTime]++;
        	endTable[endTime]++;
        }
        int cnt = 0;
        int max = 0;
        for(int i = 0 ; i < 1000 ; i++) {
        	cnt += startTable[i];
        	max = cnt;
        }
        
        for(int i = 1000 ; i < 24*60*60*1000 ; i++) {
        	cnt += startTable[i];
        	cnt -= endTable[i-1000];
        	max = Math.max(max, cnt);
        }
        
        
        
        return max;
    }

}



