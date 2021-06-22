def solution(s):
    answer = []
    temp = []
    myList = []
    num = 0
    for i in range(len(s)):
        if s[i].isdigit():

            if s[i+1] == '}':
                temp.append((num * 10) + int(s[i]))
                num = 0
                myList.append(temp)
                temp = []
            elif s[i+1] == ',':
                temp.append((num*10) + int(s[i]))
                num = 0
            else:
                num = (num * 10) + int(s[i])

    myList.sort(key=len)

    for i in range(len(myList)):
        for a in range(len(answer)):
            for j in range(len(myList[i])):
                if answer[a] == myList[i][j]:
                    myList[i].pop(j)
                    break
        answer.append(myList[i][0])
    print(answer)

    return answer

solution("{{20,111},{111}}")