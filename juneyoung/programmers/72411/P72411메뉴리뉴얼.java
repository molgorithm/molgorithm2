package programmers;
import java.util.*;

public class 메뉴리뉴얼_2021KAKAO_BLIND_REC {
    static Map<String,Integer> map = new HashMap<>();
    static int[] count = new int[11];
    public String[] solution(String[] orders, int[] course) {
        for(String order : orders) {
            powerSet(order,order.length(),course);
        }

        map.forEach((k,v)->{
            count[k.length()] = Math.max(count[k.length()],v);
        });

        String[] answer = map.keySet().stream()
                .filter((k) -> count[k.length()] >= 2 && count[k.length()] == map.get(k)).sorted().toArray(String[]::new);
        return answer;
    }

    public void powerSet(String order,int len,int[] course) {
        char[] orderArr = order.toCharArray();
        Arrays.sort(orderArr);

        for(int i = 0; i < 1 << len; i++) {
            String str = "";
            for(int j = 0; j < len; j++) {
                if ((i & 1 << j) != 0) str += orderArr[j];
            }
            if(str.equals("")) continue;
            int size = str.length();
            for(Integer c : course){
                if(c == size) {
                    map.merge(str,1,Integer::sum);
                    count[str.length()] = Math.max(count[str.length()],map.get(str));
                }
            }
        }
    }
}
 