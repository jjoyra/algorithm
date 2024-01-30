package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_11000_강의실_배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            arr[i][0] = S;
            arr[i][1] = T;
        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]); // 강의 시작 시간이 빠른 순으로 정렬

       PriorityQueue<Integer> pq = new PriorityQueue<>(); // 강의실 별 마지막 강의 종료 시간을 저장하는 우선 순위 큐
       pq.offer(arr[0][1]);

        for(int i = 1; i < N; i++) {
            int[] next = arr[i];
            int end = pq.poll();

            if(end > next[0]) { // 우선 순위 큐의 가장 빠른 마지막 종료 시간보다 다음 강의 시작 시간이 더 빠르다면 새로 강의실 배정
                pq.offer(end);
                pq.offer(next[1]);
            } else {
                pq.offer(next[1]);
            }
        }

        System.out.println(pq.size());
    }
}
