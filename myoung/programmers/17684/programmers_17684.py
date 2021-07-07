def solution(msg):
    answer = []
    dictionary = {}
    idx = 1
    while idx < 27:
        dictionary[chr(ord('A') + idx - 1)] = idx
        idx += 1

    msg_i = 0
    plus_i = 1
    while True:
        while msg[msg_i:msg_i + plus_i] in dictionary:
            plus_i += 1
            if msg_i + plus_i - 1 > len(msg):
                break
        answer.append(dictionary[msg[msg_i:msg_i + plus_i - 1]])
        if (msg_i + plus_i - 1) > len(msg):
            break
        if msg[msg_i:msg_i + plus_i] not in dictionary:
            dictionary[msg[msg_i:msg_i + plus_i]] = idx
            idx += 1
            msg_i += (plus_i - 1)
            plus_i = 1
    return answer

