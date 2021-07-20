# 프로그래머스 60060 가사검색

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/60060)

## 1. 설계 로직

words와 queries 모두 총 길이 2 ~ 1000000으로 선형적으로 검사할 시 시간초과가 날 수 밖에 없다.

 

그래서 이 문제는 트라이 자료구조를 활용해서 풀이를 진행해야 한다.

 



![img](https://blog.kakaocdn.net/dn/EWYT1/btq97ms0NWR/37PvF6Ggsmj0w5YAnkGlRk/img.png)draw.io에서 만듦, 아이패드로 공부하고 싶다아



트라이에 insert와 find 함수를 구현하며

이 문제에서는 특이하게 ?를 만나면 그 아래로는 모든 노드가 가능이다.

그래서 각 노드에 HashMap을 이용해 그 노드에 도달 했으면서 단어의 길이가 key인 갯수를 value로 저장해 주었다.

 

이렇게 하면 나중에 find를 할 때 ?를 만나면 해당하는 value만 return해주면 된다.

 

또 유의할 점으로

???do 같이 ?가 앞에서 부터 나오는 경우가 있으므로

트라이를 완전 거꾸로 보는 형태로도 하나 더 만들어

frodo -> odorf

???do -> od???

와 같이 작동하게 만들어 줘야 한다.

- 시간복잡도

  O(N) word의 총길이 + 쿼리의 총길이

## 2. 코드

```java
import java.util.HashMap;
import java.util.Map;

public class P60060가사검색 {

	class Trie {
		Trie[] child = new Trie[26];
		Map<Integer, Integer> lenMap = new HashMap<Integer, Integer>();

		void insert(String s) {
			Trie node = this;
			lenMap.put(s.length(), lenMap.getOrDefault(s.length(), 0) + 1);
			for (char ch : s.toCharArray()) {
				if (node.child[ch - 'a'] == null)
					node.child[ch - 'a'] = new Trie();
				node = node.child[ch - 'a'];
				node.lenMap.put(s.length(), node.lenMap.getOrDefault(s.length(), 0) + 1);
			}
		}

		int find(String s) {
			Trie node = this;
			for (char ch : s.toCharArray()) {
				if (node == null)
					return 0;
				if (ch == '?')
					return node.lenMap.getOrDefault(s.length(), 0);
				else
					node = node.child[ch - 'a'];
			}
			return 0;
		}
	}

	public int[] solution(String[] words, String[] queries) {
		Trie front = new Trie();
		Trie back = new Trie();
		for (String s : words) {
			StringBuilder sb = new StringBuilder(s);
			front.insert(s);
			back.insert(sb.reverse().toString());
		}

		int[] answer = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			String query = queries[i];
			answer[i] = query.charAt(0) == '?' ? back.find(new StringBuilder(query).reverse().toString())
					: front.find(query);
		}
		return answer;
	}

}

```



## 3. 후기

- 트라이인건 한번에 알았는데 트라이를 제대로 써본적이 없어서

  트라이가 뭔지 개념 부터 다시 찾아보면서 풀이를 진행하였음.