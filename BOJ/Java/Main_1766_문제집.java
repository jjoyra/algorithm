package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1766_문제집 {
    static int N, M;
    static int[] in;
    static List<Integer>[] list;

    static class Problem implements Comparable<Problem> {
        int number, priority;

        Problem(int number, int priority) {
            this.number = number;
            this.priority = priority;
        }

        @Override
        public int compareTo(Problem problem) {
            if (this.priority != problem.priority) {
                return this.priority - problem.priority;
            }

            return this.number - problem.number;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        in = new int[N + 1];
        list = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            in[b]++;
        }

        StringBuilder sb = new StringBuilder();
        PriorityQueue<Problem> pq = new PriorityQueue<>();

        for (int i = 1; i < N + 1; i++) {
            pq.offer(new Problem(i, in[i]));
        }


        while (!pq.isEmpty()) {
            Problem problem = pq.poll();
            if (in[problem.number]-- < 0) continue;

            sb.append(problem.number);
            sb.append(" ");

            for (int num : list[problem.number]) {
                in[num]--;
                if(in[num] == 0) {
                    pq.offer(new Problem(num, in[num]));
                }
            }

        }

        sb.delete(sb.length() - 1, sb.length());
        System.out.println(sb);
    }
}
