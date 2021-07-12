# 프로그래머스 17685 자동완성

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/17685)

## 1. 설계 로직

0. 문제를 보자마자 `트라이 자료구조`가 생각났습니다.

   트라이 구조로 만들 경우 ["go", "gone", "guild"]가 주어질 때

   ![image-20210705211302487](README.assets/R1280x0)

   이런 식으로 생기게 됩니다.

   그리고, 해당 문자가 존재하는지 여부를 확인하기 위해 trie.search("go") = True, trie.search("gone") = True, trie.search("guild") = True, trie.search("gon") = False와 같이 문자가 끝나는 지점에만 True값을 넣어주는 것과 같이 문자인지 아닌지 넣어주면 됩니다.

1. 일단 문자열을 다 담아줍니다. 이 때 {'g': [1, {'o': [1, ...]}]}과 같이 dict의 key는 문자, value에 해당하는 리스트는 해당 문자가 몇번 쓰였는지, 그리고 그 다음에 나오는 문자는 무엇인지 넣어줍니다.

2. 이렇게 dict를 계속 연결하고 나면, 겹치지 않는 문자의 경우 value의 0번째 값이 1이 됩니다.

3. 따라서 각 문자마다 dict를 계속 확인하며 value의 0번째 값이 1이 될 때의 `idx값 + 1`이 답이 됩니다.



- 시간복잡도: O(L) : 문자열의 총 길이가 L이므로 O(L)입니다.

## 2. 코드

```python
def solution(words):
    answer = 0
    trie = {}
    for word in words:
        cur_trie = trie
        for w in word:
            cur_trie.setdefault(w, [0, {}])
            cur_trie[w][0] += 1
            cur_trie = cur_trie[w][1]

    for word in words:
        cur_trie = trie
        for i in range(len(word)):
            w = word[i]
            if cur_trie[w][0] == 1:
                break
            cur_trie = cur_trie[w][1]
        answer += (i + 1)
    return answer
```



## 3\. 후기

-   Trie문제 배제하고 있었는데 풀게 되었습니다^-^ 직접 풀어보니 정말 다르네용 ~!
-   사실 따로 자료구조를 만들어서 사용할까 고민하다가 setdefault를 사용해서 풀이를 진행했습니다.
    -   `dict.setdefault(a, b)` : dict내에 'a'라는 key가 존재한다면 해당 key의 value를 반환하고, 'a'라는 key가 존재하지 않는다면 dict\['a'\]의 value는 b가 됩니다.

- 문자 하나의 길이가 최대 100만이 되는줄 알고 식겁했는데, 자세히 읽으니

  ![img](README.assets/R1280x0)

  이랬습니다 ^-^

  앞으로 문제 제대로 읽기..(ง •_•)ง