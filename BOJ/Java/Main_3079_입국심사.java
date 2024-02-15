package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3079_입국심사 {
    static int N, M;
    static long answer;
    static int[] time;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        time = new int[N];

        long fast = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++) {
            time[i] = Integer.parseInt(br.readLine());
            fast = Math.min(fast, time[i]);
        }

        answer = Long.MAX_VALUE;

        solve(fast, fast * M);
        System.out.println(answer);
    }

    static void solve(long left, long right) {
        if(left > right) return;

        long mid = (left + right) / 2;
        long sum = 0;
        for(int test : time) {
            sum += mid / test;
        }

        if(sum >= M) {
            answer = Math.min(answer, mid);
            solve(left, mid - 1);
        } else {
            solve(mid + 1, right);
        }

    }
}
