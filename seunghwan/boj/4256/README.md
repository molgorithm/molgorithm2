# 백준 4256 트리

[문제 링크](https://www.acmicpc.net/problem/4256)

## 1. 설계 로직

트리의 전위순회 중위순회를 보고 후위순회를 찾는 문제

 

전위와 중위를 보고 어떻게 후위를 찾을지 고민해봐야 한다.

 

전위는 나 -> 왼쪽 -> 오른쪽

중위는 왼쪽 -> 나 -> 오른쪽

 

여기서 전위에서 나의 위치가 먼저 나오고

중위에선 나를 기준으로 왼쪽 오른쪽으로 나뉘는 것을 볼 수 있다.



![img](https://blog.kakaocdn.net/dn/bNtPd4/btriAsxSr4l/78wmbLTAkIxNRLUqdWb3j1/img.png)

![img](https://blog.kakaocdn.net/dn/CzuJZ/btriybqhBVx/vTdHHXw0aIzkyykYfwM2DK/img.png)

​																	위가 전위 아래가 중위



전위의 맨 첫 수 3이 루트가 되고

중위의 3을 기준으로

왼쪽 수들은 루트의 왼쪽에 위치하고

오른쪽 수들은 루트의 오른쪽에 위치하게 된다.



![img](https://blog.kakaocdn.net/dn/bFVtDq/btrivCvaGyf/fH0qfF8e4SCjoQz6cUZcA1/img.png)



그러면 2개의 서브트리가 생기게 되고

전위의 다음 순서인 6은 왼쪽서브트리의 루트가 될 것이다.



![img](https://blog.kakaocdn.net/dn/bdIpwH/btrivCBXniD/Kkxealfh4Mf9JmCheds9V0/img.png)



6을 기준으로 왼쪽에 5 오른쪽에 8,4 가 남았다.

왼쪽은 5를 기준으로 왼쪽 오른쪽 나누어도 더 이상 나뉘지 않게 됐다.

 

이쯤에서 후위순회에 대해 생각해보면

왼쪽 -> 오른쪽 -> 나 가 된다.

 

현재 들어가는 순서를 보면 왼쪽으로 쭉 들어왔다.

가장 왼쪽인 5를 찾았으니 5를 출력하고

그 다음은 오른쪽이니 8,4트리를 찾아간다.

전위순회 상 루트는 4이니

4를 기준으로 왼쪽은 8 오른쪽은 없다.

그러니 8 출력(왼쪽) 없음(오른쪽) 4 출력(나)

 

이제 패턴이 보일 것이다.

 

전위 순회의 순서를 가지고 중위순회에서 각 서브트리의 루트를 찾을 수 있고

그 루트를 기준으로 왼쪽 오른쪽 서브트리로 나눌 수 있다.

그렇다면 후위순회의 왼쪽->오른쪽->나 순서로 탐색이 가능해진다.



![img](https://blog.kakaocdn.net/dn/cM60gs/btriuZjKsf5/kYhjiFUziNcvdqllBUFk90/img.png)



코드에서 보면

재귀적으로

전위순회의 값을 중위순회에서 찾고

그 위치를 기준으로 왼쪽 탐색 -> 오른쪽 탐색 -> 나 출력이 된다.



- 시간복잡도

  O(N)

## 2. 코드

```java
package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G4_4256트리 {
	static int[] preOrder;
	static int[] inOrder;
	static int preIdx;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		while (T-- != 0) {
			int n = Integer.parseInt(br.readLine());
			sb = new StringBuilder();
			preIdx = 0;
			preOrder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			inOrder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			go(0,n-1);
			System.out.println(sb);
		}
	}


	static void go(int s, int e) {
		if(s>e)
			return;
		int num = preOrder[preIdx++];
		int inIdx = -1;
		for (int i = s; i <= e; i++) {
			if (inOrder[i] == num) {
				inIdx = i;
				break;
			}
		}
		go(s, inIdx - 1);
		go(inIdx + 1, e);
		sb.append(num).append(" ");
	}
}
```



## 3. 후기

- 처음엔 쉽게 봤는데

  생각보다 머리속으로 잘 안그려져서

  손으로 직접 그려가며 겨우 풀었다.