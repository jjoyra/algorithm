package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_14865_곡선_자르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        Queue<long[]> queue = new ArrayDeque<>();
        Stack<long[]> stack = new Stack<>();

        long maxX = Long.MIN_VALUE;
        long minX = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;

        long startX = Long.MAX_VALUE;
        long startY = Long.MAX_VALUE;

        int inCnt = 0;
        int outCnt = 0;

        for(int i = 0; i < N; i++) { // 입력 받기
            st = new StringTokenizer(br.readLine());
            Long x = Long.parseLong(st.nextToken());
            Long y = Long.parseLong(st.nextToken());

            if(startX >= x) {
                startX = x;
                startY = Math.min(startY, y);
            }

            queue.offer(new long[] {x, y});
        }

        // 큐 시작 위치 고정(왼쪽 가장 아래 점)
        while(queue.peek()[0] != startX || queue.peek()[1] != startY) {
            queue.offer(queue.poll());
        }

        // x축 아래 점들 걸러내기
        while(!queue.isEmpty()) {
            long x = queue.peek()[0];
            long y = queue.poll()[1];

            if(y < 0) { // x축 밑의 점일 때
                if(maxX != Long.MIN_VALUE) { // 내려오는 점일 때
                    pq.add(new long[]{maxX, maxY});
                    pq.add(new long[]{minX, maxY});

                    maxX = Long.MIN_VALUE;
                    minX = Long.MAX_VALUE;
                    maxY = Long.MIN_VALUE;
                }
            } else {
                maxX = Math.max(maxX, x);
                minX = Math.min(minX, x);
                maxY = Math.max(maxY, y);
            }
        }
        
        while(!pq.isEmpty()) {
            long[] tmp = pq.poll();
            if(!stack.isEmpty() && stack.peek()[1] == tmp[1]) { // (( )
                stack.pop();
                if(stack.isEmpty()) outCnt++;
            } else if(pq.peek()[1] == tmp[1]) { // (( ()
                pq.poll();
                inCnt++;
                if(stack.isEmpty()) outCnt++;
            } else { // (( (
                stack.push(tmp);
            }

        }

        System.out.println(outCnt + " " + inCnt);
    }
}
