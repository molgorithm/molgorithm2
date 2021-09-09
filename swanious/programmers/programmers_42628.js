function solution(operations) {
  var queue = [];
  
  operations.forEach((op) => {
      const [command, val] = op.split(' ')
      if (command === "I") {
          // Number로 변환
          queue.push(val * 1)
      }
      else if (command === 'D' && queue.length) {
        queue.sort((a, b) => a - b)
        val === '1' ? queue.pop() : queue.shift()
      }
  })
      
  queue.sort((a, b) => a - b)
  return queue.length ? [queue[queue.length-1], queue[0]] : [0, 0];
}