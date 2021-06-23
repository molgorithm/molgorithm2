import sys
sys.setrecursionlimit(10 ** 7)

def solution(k, room_number):
    answer = []
    reserved_rooms = dict()

    def find(room, reserved_rooms):
        if room not in reserved_rooms:
            reserved_rooms[room] = room + 1
            return room
        reserved_rooms[room] = find(reserved_rooms[room], reserved_rooms)
        return reserved_rooms[room]

    for room in room_number:
        remained_room = find(room, reserved_rooms)
        answer.append(remained_room)

    return answer


print(solution(10, [1, 3, 4, 1, 3, 1]))