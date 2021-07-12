import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P17686파일명정렬 {
	
	class file implements Comparable<file>{
		String origin;
		String head;
		int num;
		int order;
		


		public file(String origin, String head, int num, int order) {
			super();
			this.origin = origin;
			this.head = head;
			this.num = num;
			this.order = order;
		}



		@Override
		public int compareTo(file o) {
			if(this.head.equals(o.head)) {
				if(this.num == o.num)
					return this.order - o.order;
				return this.num-o.num;
			}
			
			return this.head.compareTo(o.head);
			
		}
		
	}

    public String[] solution(String[] files) {
    	
        List<file> fileList = new ArrayList<file>();
        for(int i = 0 ; i < files.length; i++) {
        	String origin = files[i];
        	int numStart = 0;
        	int numEnd = 0;
        	boolean isNum = false;
        	for(int j =0 ; j < origin.length(); j++) {
        		if(!isNum &&'0'<=origin.charAt(j) && origin.charAt(j)<= '9' ) {
        			numStart = j;
        			isNum = true;
        		}
        		if(isNum &&( '0'>origin.charAt(j) || origin.charAt(j)> '9') ) {
        			numEnd = j;
        			break;
        		}
        	}
            if(numEnd == 0)
                numEnd = origin.length();
        	String head  = origin.substring(0, numStart).toLowerCase();
        	int num = Integer.parseInt(origin.substring(numStart,numEnd));
        	fileList.add(new file(origin, head, num,i));
        }
        Collections.sort(fileList);
        String[] answer = new String[fileList.size()];
        for(int i = 0 ; i < fileList.size(); i++) answer[i] = fileList.get(i).origin;
        
        return answer;
    }

}
