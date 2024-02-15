package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2343_기타_레슨 {
    static int N, M, answer;
    static int[] lessons;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        lessons = new int[N];
        for(int i = 0; i < N; i++) {
            lessons[i] = Integer.parseInt(st.nextToken());
        }

        answer = Integer.MAX_VALUE;
        solve(1, N * 10000);

        System.out.println(answer);
    }

    static void solve(int left, int right) {
        if(left > right) return;

        int mid = (left + right) / 2;
        int idx = 0;

        for(int i = 0; i < M; i++) {
            int sum = 0;
            while(idx < N && sum + lessons[idx] <= mid) {
                sum += lessons[idx++];
            }
        }

        if(idx >= N) {
            answer = Math.min(answer, mid);
            solve(left, mid - 1);
        } else {
            solve(mid + 1, right);
        }

    }
}
