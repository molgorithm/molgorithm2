import re


def solution(dartResult):
    answer = 0

    pluses = re.split('[0-9]', dartResult)
    scores = re.split(r'S|D|T|\*|\#', dartResult)

    game_score = []
    plus_i, score_i = 0, 0
    now_turn = 0

    def game(score, plus):
        nonlocal game_score

        if plus[0] == 'S':
            total = score
        elif plus[0] == 'D':
            total = score ** 2
        else:
            total = score ** 3

        if len(plus) == 2:
            if plus[1] == '*':
                if game_score[-1]:
                    game_score[-1] *= 2
                total *= 2
            else:
                total = -(total)
        return total

    while True:
        if now_turn == 3:
            return answer

        while scores[score_i] == '':
            score_i += 1

        while pluses[plus_i] == '':
            plus_i += 1

        _temp = game(scores[score_i], pluses[plus_i])
        game_score.append(_temp)

        score_i += 1
        plus_i += 1
        now_turn += 1
    return