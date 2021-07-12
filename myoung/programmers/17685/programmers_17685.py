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

