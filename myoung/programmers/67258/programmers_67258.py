def solution(gems):
    answer = []
    visited = {}
    type_nums = len(set(gems))
    min_dist = len(gems)+1
    _left, _right = 0, 0
    while _right < len(gems):
        if gems[_right] in visited:
            visited[gems[_right]] += 1
        else:
            visited[gems[_right]] = 1
        _right += 1
        if len(visited) == type_nums:
            while _left < _right:
                if visited[gems[_left]] > 1:
                    visited[gems[_left]] -= 1
                    _left += 1
                elif min_dist > _right - _left:
                    min_dist = _right - _left
                    answer = [_left + 1, _right]
                    break
                else:
                    break
    return answer

