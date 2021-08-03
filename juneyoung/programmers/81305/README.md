# 프로그래머스 81305 시험장 나누기

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/81305)

## 1. 설계 로직

1. 고려해야할 사항 
   - 나눌 그룹의 수 k (10,000) , 응시자 수 num (10,000)
   - 정확성 테스트 케이스는 20, 20 이므로 모든 경우를 탐색할 수 있음 20C10 -> 약 18만
   - 최소화된 최대 그룹의 인원을 계산. -> 파라메트릭 서치
     - 최적화 문제를 결정문제로 바꿈
     - 최대 그룹의 인원을 계산해야 하므로 기준값 이상은 모두 참이라고 생각할 수 있음. -> 이분 탐색
2. 설계로직
   1. 간선 리스트 만들기

      - 이진트리라서 1차원 배열로 최적화 가능

   2. 루트 노드 찾기

   3. 이분탐색 시작

      - 이분탐색의 최소값은 모든 (시험장의 인원 / k)
      - 이분탐색의 최대값은 모든 시험장의 인원

   4. 재귀를 이용해 단말노드부터 계산 시작

      - 시험장 수 만큼 2차원 배열 생성 
        - 0번 -> 나를 포함한 자식노드의 그룹 개수
        - 1번 -> 나를 포함한 그룹의 최소값

      - 자식 노드가 없을 때 ( 단말노드 ) 
      - 자식 노드가 1개
      - 자식 노드가 2개

   5. 공통 계산과정

      - 왼쪽 자식 값 (1번 인덱스)  + 오른쪽 자식의 값 (1번 인덱스) + 현재 시험장의 인원의  <= 기준 값

        -> 같은 그룹으로 묶임.

        -> 그룹수는 자식 노드 그룹 수 합 - 1

      - (왼쪽 자식의 값 + 현재 시험장의 인원)  <= 기준 값 OR (오른쪽 자식의 값 + 현재 시험장의 인원) <= 기준 값

        -> 두 값중 작은 값으로 묶임.

        -> 한 쪽만 잘라내면 되기 때문에 그룹수는 자식 노드의 그룹 수 합

      - 둘다 해당사항 없을 때

        -> 두쪽 다 잘라내야 함.

        -> 자식 노드의 그룹수 합 + 1
3. 시간복잡도: O (n x logn) 

## 2. 코드

```java
import java.util.*;

class Solution {
    static int root, nodeSize, dp[][]; 
    static boolean check, v[];
    static ArrayList<Integer>[] list;
    public int solution(int k, int[] num, int[][] links) {
        
        nodeSize = num.length;
        dp = new int[nodeSize][2];
        v = new boolean[nodeSize];
        
        list = new ArrayList[nodeSize];
        for(int i = 0; i < nodeSize; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < links.length; i++) {
            int left = links[i][0];
            int right = links[i][1];
            if(left != -1) {
                list[i].add(left);    
                v[left] = true;
            }
            if(right != -1){
                list[i].add(right);    
                v[right] = true;
            } 
        }
        
        // searchRootNode        
        for(int i = 0 ; i < nodeSize; i++) {
           if(!v[i]) root = i;
        }
        
        // algo
        int sum = 0;
        for(int i = 0; i < nodeSize; i++) {
            sum += num[i];
        }
        
        int answer = 0;
        int start = sum / k;
        int end = sum;
        while(start <= end) {
            int mid = (start + end) / 2;
            check = false;
            cal(root, mid, num, k);
            if(!check) {
                end = mid - 1;
                answer = mid;
            }else start = mid + 1;
        }
        return answer;
    }
    
    public static void cal(int idx, int max,int[] num,int k) {
        if(check) return;
        
        int size = list[idx].size();
        for(int i = 0; i < size; i++) {
            cal(list[idx].get(i), max, num, k);
        }
        
        if(check) return; 
        if(size == 0) {
            // 단말 노드
            if(num[idx] <= max) {
                dp[idx][0] = 1;
                dp[idx][1] = num[idx];
            }else check = true;
        }else if(size == 1) {
            // 왼
            int left = list[idx].get(0);
            if(num[idx] + dp[left][1] <= max) {
                dp[idx][0] = dp[left][0];
                dp[idx][1] = num[idx] + dp[left][1];
            }else {
                dp[idx][0] = dp[left][0] + 1;
                dp[idx][1] = num[idx];
            }
        }else{
            // 왼 오
            int left = list[idx].get(0);
            int right = list[idx].get(1);
            if(num[idx] + dp[left][1] + dp[right][1] <= max) {
                dp[idx][0] = dp[left][0] + dp[right][0] - 1;
                dp[idx][1] = num[idx] + dp[left][1] + dp[right][1];
            }else if(num[idx] + dp[left][1] <= max || num[idx] + dp[right][1] <= max){
                dp[idx][0] = dp[left][0] + dp[right][0];
                if(dp[left][1] < dp[right][1]) dp[idx][1] = num[idx] + dp[left][1]; // 왼
                else dp[idx][1] = num[idx] + dp[right][1]; // 오
            }else{
                dp[idx][0] = dp[left][0] + dp[right][0] + 1;
                dp[idx][1] = num[idx];
            }
        }
        if(num[idx] > max || dp[idx][0] > k) check = true;
    }
    
}
```

## 3. 후기

- 백준 플레티넘 중 상위급
- 비슷한 문제 BOJ_3090 차이를 최소로
