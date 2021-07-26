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
