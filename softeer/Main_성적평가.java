package algorithm.softeer;

import java.util.*;
import java.io.*;


public class Main_성적평가 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[4][N];
        int[] sum = new int[N];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return b[1] - a[1];
        });

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                pq.offer(new int[]{j, tmp});
                sum[j] += tmp;
            }

            int idx = 1;

            while (!pq.isEmpty()) {
                int[] tmp = pq.poll();
                map[i][tmp[0]] = idx;

                while (!pq.isEmpty() && tmp[1] == pq.peek()[1]) {
                    tmp = pq.poll();
                    map[i][tmp[0]] = idx;
                }

                idx = N - pq.size() + 1;
            }

        }

        for (int j = 0; j < N; j++) {
            pq.offer(new int[]{j, sum[j]});
        }

        int idx = 1;

        while (!pq.isEmpty()) {
            int[] tmp = pq.poll();
            map[3][tmp[0]] = idx;

            while (!pq.isEmpty() && tmp[1] == pq.peek()[1]) {
                tmp = pq.poll();
                map[3][tmp[0]] = idx;
            }

            idx = N - pq.size() + 1;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
}
