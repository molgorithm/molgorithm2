# 백준 3584 가장 가까운 공통 조상

[문제 링크](https://www.acmicpc.net/problem/3584)

## 1. 설계 로직

![img](https://blog.kakaocdn.net/dn/bpsNAf/btrisLKVWiJ/oAFOLfS9POS7hqiRNrFA8K/img.png)



두 노드에서 가장 가까운 조상 찾는 문제

 

한 노드에서 부모 따라 쭉 올라가면서 visit체크 해주고

다른 노드에서 부모 따라 쭉 올라가다 visit체크 된 곳이 있으면 해당 노드가

가장 가까운 공통조상이 된다.

 

1. par 배열에 부모 자식 저장

2. 첫번째 노드에서 루트노드까지 부모 찾아가기 실행

3. 들린 노드들은 visit 배열에 체크

4. 두번째 노드에서 부모 찾아가기 실행

5. 들린 노드가 visit 체크 되어 있다면 StringBuilder에 저장

6. 다음 테스트케이스 실행

7. 모든 테스트 케이스 돈 후 StringBuilder 출력



- 시간복잡도

  O(N)

## 2. 코드

```java
package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_3584가장가까운공통조상 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		 
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0 ; tc <T ; tc++) {
			int N = Integer.parseInt(br.readLine());
			int par [] = new int [10001];
			boolean visit[] = new boolean[10001];
			for(int i = 0 ; i < N-1 ; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				par[b] = a;
			}
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			while(true) {
				visit[a] = true;
				if(par[a] == 0)
					break;
				a = par[a];
			}
			while(true) {
				if(visit[b]) {
					sb.append(b).append("\n");
					break;
				}
				b = par[b];
			}
		}
		System.out.println(sb);
	}
}
```



## 3. 후기

- 루트가 아닌 중간 노드가 가장 가까운 노드라면

  중간까지만 탐색하는 방식이 효율성을 좀 더 올릴 수 있겠지만

  n이 10000뿐이 안되니 그냥 이렇게 풀이했다.

  중간까지만 탐색을 하더라도 최악에선 동일.