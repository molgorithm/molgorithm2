import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class boj7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
            // TreeMap은 정렬과 동시에 Map의 역할을 수행 key는 입력받은 숫자, value는 개수
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int num = Integer.parseInt(st.nextToken());
                if (command.equals("I")) {
                    if (map.containsKey(num)) { // 이미 존재하는 값이면
                        map.put(num, map.get(num) + 1); // 개수만 증가
                    } else {
                        map.put(num, 1); // 없으면 추가
                    }
                } else {
                    if (map.size() > 0) {
                        int del_key = 0; // 지울 key 초기화
                        if (num == 1) {
                            del_key = map.lastKey(); // 가장 뒤에 있는 = 가장 키값이 큰
                        } else {
                            del_key = map.firstKey(); // 가장 앞에 있는 = 가장 키값이 작은
                        }
                        int del_num = map.get(del_key) - 1; // 개수를 줄여보고
                        if (del_num == 0) { // 0이면 삭제
                            map.remove(del_key);
                        } else {
                            map.put(del_key, del_num); // 그 외엔 업데이트
                        }
                    }
                }
            }
            if (map.size() == 0) {
                System.out.println("EMPTY");
            } else {
                System.out.println(map.lastKey() + " " + map.firstKey());
            }
        }
    }
}