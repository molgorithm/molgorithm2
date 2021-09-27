import java.util.HashSet;
import java.util.Set;

public class P42895N으로표현 {
    public int solution(int N, int number) {
        int answer = 0;
        Set<Integer> list[] = new Set[9]; //숫자모음
        for(int i = 0 ; i < 9 ; i++)
            list[i] = new HashSet<>();
        for(int size = 1 ; size < 9 ; size++){
            int num = 0;
            for(int i = 0 ; i < size; i++)
                num += N*Math.pow(10,i);
            list[size].add(num);
            for(int i = 1 ; i < size ; i++){
                for(int iNum : list[i]){
                    for(int jNum : list[size-i]){
                        list[size].add(iNum+jNum);
                        list[size].add(iNum-jNum);
                        list[size].add(iNum*jNum);
                        if(jNum != 0)
                            list[size].add(iNum/jNum);
                    }
                }
            }
            if(list[size].contains(number))
                return size;
        }
        return -1;
    }
}