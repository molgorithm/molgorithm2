import java.util.*;

public class P67258보석쇼핑 {

    static public int[] solution(String[] gems) {
        boolean[] nextGem = new boolean[gems.length];
        HashMap<String, Integer> map = new HashMap<>();
        int start = gems.length - 1;
        int end = gems.length - 1;
        for (int i = gems.length - 1; i >= 0; i--) {
            String nowGem = gems[i];
            if (!map.containsKey(nowGem))  //처음 본 문자라면
                start = i;
            else  //이미 본 문자면 nextGem에 체크
                nextGem[map.get(nowGem)] = true;

            int tempend = end;
            while (nextGem[tempend--])


            if (tempend - i <= end - start) {
                start = i;
                end = tempend;
            }
            map.put(nowGem, i);
        }
        return new int[]{start + 1, end + 1};
    }
}
