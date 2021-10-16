# 백준 14675 단절점과 단절선

[문제 링크](https://www.acmicpc.net/problem/14675)

## 1. 설계 로직

흠.. 풀고나니 문제가 조금... 흠...

 

우선 단절선은



![img](https://blog.kakaocdn.net/dn/bIznoJ/btrhTvi5x9f/bmjXMkMUp9bUM926OSOoWK/img.png)



간선 자체가 양쪽에 노드가 있으니 간선이 있을 수 있다.

즉, 간선을 제거하면 반드시 2개의 그래프로 나뉠 수 밖에 없다.

= 모든 간선은 단절선이다.

 



![img](https://blog.kakaocdn.net/dn/lsJwO/btrhX1Azc9A/nNFYceRSmhGettKwE7ifz0/img.png)



단절점은 노드에 하나의 노드만 연결되어 있으면 혼자 사라지고 말지만



![img](https://blog.kakaocdn.net/dn/E795Z/btrhVZ4i3ZN/dG10fbHYuuNqJ1Y5jKKKE1/img.png)



2개 이상 연결되어 있으면 연결되어 있던 노드의 갯수만큼 그래프가 나뉘게 된다.

즉, 연결된 노드가 2개 이상이면 단절점이다.

 

1. 간선 정보에 각 노드가 몇번 나오는지 count

2. 쿼리에서 단절선을 물어보면 무조건 yes

3. 단절점을 물어보면 count가 2이상일 때 yes 아니라면 no 출력

4. 단, 출력이 많으므로 출력하는데 리소스가 많이 드는 자바는 StringBuilder나 Bufferedwriter를 쓰는게 좋아보임.



- 시간복잡도
- O(N)

## 2. 코드

```java
package Gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class G5_14675단절점과단절선 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int cnt[] = new int[N + 1];
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cnt[Integer.parseInt(st.nextToken())]++;
			cnt[Integer.parseInt(st.nextToken())]++;
		}
		int q = Integer.parseInt(br.readLine());
		for (int i = 0; i < q; i++) {
			st= new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			bw.write(a == 2 ? "yes" : cnt[b] > 1 ? "yes" : "no");
			bw.newLine();
		}
		bw.close();
	}
}
```



## 3. 후기

- 트리의 개념만 안다면 로직이 너무 간단해서 쉽게 풀이 가능한 문제

  파이썬 잘하시는 분들은 한줄로도 풀 수 있을 것 같다.