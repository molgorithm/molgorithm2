# 프로그래머스 42895 N으로표현

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42895)



## 1. 설계 로직

N을 최소로 사용해서 number를 만드는 문제

가능한 사칙연산은 다 쓸 수 있고 ( +, -, *, / ) 추가로 숫자를 이어 붙이는게 가능하다 ex) 55

 

N을 한개씩 늘려가면서 모든 사칙연산을 다 해볼 수 있겠지만

그럴 경우 괄호 계산 때문에 경우의 수가 기하급수적으로 늘어난다.

ex) N = 5이고 4개를 쓴다고 하면

5 * 5 + 5 / 5 에서

5 * ( 5 + 5 ) / 5 의 경우도 신경 써줘야 한다.

 

이런 괄호 계산까지 마친 값들을 순차적으로 미리 저장 해 둔다면 훨씬 빠르게 해결이 가능해진다.

 

만약 N을 4개를 쓴다고 한다면 가능한 경우는

NNNN

N 1개 (사칙연산) N 3개

N 2개 (사칙연산) N 2개

N 3개 (사칙연산) N 1개

이렇게 4가지 밖에 없다.

 

값들을 저장해 놓을 땐 사칙연산 후 중복 되는 값이 있을 수 있으니 Set으로 중복제거 상태로 저장해둔다.

 

모든 계산을 마친 후에는 contains함수로 number가 나왔는지 확인하는데

HashSet을 사용하기 때문에 O(1)로 바로 확인이 가능하다.

 

8개까지 써보고 number를 구했으면 갯수 return

못 구했으면 -1 return 해주면 된다.

- 시간복잡도

  O(8^2 * N^2) 

## 2. 코드

```java
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
```



## 3. 후기

- 처음엔 굳이 저장할 필요없이

  N을 펼쳐놓고 중간중간 들어갈 사칙연산만 중복순열로 뽑아서 계산시키면 되지 않나? 싶어서

  중복순열로 풀어 봤는데 괄호에 대한 연산 경우를 고려 안한 풀이여서 테케 2~3개 정도 틀렸었다..
  
  괄호까지 추가 할까 하다가 5^8 * a 가 되면 터지겠구나 싶어 포기했다.
