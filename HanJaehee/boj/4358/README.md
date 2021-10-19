# 백준 4358 생태학

[문제 링크](https://www.acmicpc.net/problem/4358)

## 1. 설계 로직

1.  트라이를 이용해서 알파벳의 알파벳 트리를 재귀적으로 만들어 트리모양의 단어표를 만든다.
2.  각 노드에 알파벳 배열을 둬서 따로 정렬을 하지 않고 정렬된 탐색을 할 수 있음.
3.  하지만, 각 알파벳별로 배열을 알파벳 대소문자 52개 외 특수문자도 커버해야해서 좀 많은 공간을 써야하는 것이 단점

- 시간복잡도 : N

## 2. 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 생태학1 {

    static int total = 0;
    static final int MAX_LEN = 128;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Trie trie = new Trie();
        String name;
        while(true) {
            name = br.readLine();
            if (name == null || name.isEmpty()) break;
            trie.insert(name);
            total++;
        }

        System.out.println(trie.start());
    }

    static class Node{
        int cnt;
        Node[] next;
        boolean isEnd;

        Node(){
            this.next = new Node[MAX_LEN];
            this.isEnd = false;
            this.cnt = 0;
        }
    }

    static class Trie{
        Node head = new Node();
        StringBuilder sb = new StringBuilder();

        void insert(String str){
            Node cur = head;

            for(int i=0; i<str.length(); i++){
                int idx = str.charAt(i);

                if(cur.next[idx] == null)
                    cur.next[idx] = new Node();
                cur = cur.next[idx];
            }

            cur.cnt++;
            cur.isEnd = true;
        }

        String start(){
            search(head, "");
            return sb.toString();
        }

        void search(Node cur, String now){
            if(cur.isEnd){
                sb
                        .append(now)
                        .append(String.format(" %.4f", cur.cnt*100.0/total))
                        .append("\n");
            }

            for(int i=0; i<MAX_LEN; i++){
                if(cur.next[i] == null) continue;
                search(cur.next[i], now + (char)i);
            }
        }
    }
}

```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
