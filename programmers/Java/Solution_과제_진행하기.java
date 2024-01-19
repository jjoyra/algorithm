package algorithm.programmers.Java;

import java.util.*;
public class Solution_과제_진행하기 {
    public class Task {
        String name;
        int start;
        int progress;

        Task(String name, int start, int progress) {
            this.name = name;
            this.start = start;
            this.progress = progress;
        }

        public void updateProgress(int progress) {
            this.progress = progress;
        }
    }

    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        PriorityQueue<Task> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.start));
        Stack<Task> stack = new Stack<>();

        for(String[] plan : plans) {
            String name = plan[0];
            int start = Integer.parseInt(plan[1].substring(0, 2)) * 60
                    + Integer.parseInt(plan[1].substring(3));
            int progress = Integer.parseInt(plan[2]);

            pq.offer(new Task(name, start, progress));
        }

        int idx = 0;
        int time = pq.peek().start;
        stack.push(pq.poll());

        while(!pq.isEmpty()) {
            Task cur = stack.pop();
            Task next = pq.poll();
            int update = next.start - time;

            if(cur.progress < update) { // 한 과제를 하고 시간이 남을 때
                answer[idx++] = cur.name;
                time += cur.progress;
                if(!stack.isEmpty()) {
                    pq.offer(next);
                    continue;
                }
            } else if(cur.progress == update) { // 과제를 마치고 바로 다음 과제를 할 시간일 때
                answer[idx++] = cur.name;
            } else { // 한 과제를 하는 도중 다른 과제를 할 시간일 때
                cur.updateProgress(cur.progress - update);
                stack.push(cur);
            }

            time = next.start;
            stack.push(next);
        }

        while(!stack.isEmpty()) {
            answer[idx++] = stack.pop().name;
        }

        return answer;
    }
}