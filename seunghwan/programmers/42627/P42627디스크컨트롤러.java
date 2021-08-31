import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P42627디스크컨트롤러 {
	class job {
		int start;
		int end;

		job(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public int solution(int[][] jobs) {
		PriorityQueue<job> pq = new PriorityQueue<>(new Comparator<job>() {
			@Override
			public int compare(job o1, job o2) {
				return o1.end - o2.end;
			}
		});

		Arrays.sort(jobs, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		int answer = 0;
		int cnt = 0;
		int time = jobs[0][0];
		int index = 0;

		while (cnt < jobs.length) {
			while (index < jobs.length && jobs[index][0] <= time)
				pq.offer(new job(jobs[index][0], jobs[index++][1]));

			if (!pq.isEmpty()) {
				job now = pq.poll();
				time += now.end;
				answer += time - now.start;
				cnt++;
			} else
				time = jobs[index][0];

		}

		return answer / cnt;
	}
}
