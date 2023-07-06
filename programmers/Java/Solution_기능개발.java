package algorithm.programmers.Java;

import java.util.*;

public class Solution_기능개발 {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();

        Queue<int[]> queue = new ArrayDeque<>();

        for(int i = 0; i < progresses.length; i++) {
            queue.offer(new int[] {progresses[i], speeds[i]});
        }

        while(!queue.isEmpty()) {
            int cnt = 1;
            int[] tmp = queue.poll();
            int time = (100 - tmp[0]) % tmp[1] == 0 ? (100 - tmp[0]) / tmp[1]
                    : (100 - tmp[0]) / tmp[1] + 1;
            while(!queue.isEmpty()) {
                tmp = queue.peek();
                if((tmp[0] + tmp[1] * time) >= 100) {
                    queue.poll();
                    cnt++;
                } else break;
            }
            list.add(cnt);
        }

        int[] answer = new int[list.size()];

        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

}