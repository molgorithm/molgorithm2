import java.util.*;
import java.util.stream.*;

public class P67257수식최대화 {
    static List<Long> numbers;
    static List<String> opers = new ArrayList<>();
    static String[] sign = {"-", "*", "+"}, arr = new String[3];
    static boolean[] v = new boolean[3];
    static long answer = Long.MIN_VALUE;

    public long solution(String expression) {

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) - '0' < 0) opers.add(expression.charAt(i) + "");
        }

        numbers = Arrays.stream(expression.replaceAll("[^0-9]", " ").split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        permutation(0);

        return answer;
    }

    public static long cal(long left, long right, String c) {
        if (c.equals("*")) {
            return left * right;
        } else if (c.equals("-")) {
            return left - right;
        } else return left + right;
    }

    public static void permutation(int idx) {
        if (idx == arr.length) {
            List<Long> calNum = new ArrayList<>();
            List<String> calOpers = new ArrayList<>();
            calNum.addAll(numbers);
            calOpers.addAll(opers);

            for (int i = 0; i < arr.length; i++) {
                String op = arr[i];

                for (int j = 0; j < calOpers.size(); j++) {
                    if (calOpers.get(j).equals(op)) {
                        long reuslt = cal(calNum.get(j), calNum.get(j + 1), op);
                        calNum.remove(j);
                        calNum.remove(j);
                        calNum.add(j, reuslt);
                        calOpers.remove(j);
                        j--;
                    }
                }

            }
            answer = Math.max(answer, Math.abs(calNum.get(0)));
            return;
        }

        for (int i = 0; i < sign.length; i++) {
            if (v[i]) continue;
            v[i] = true;
            arr[idx] = sign[i];
            permutation(idx + 1);
            v[i] = false;
        }
    }
}
 