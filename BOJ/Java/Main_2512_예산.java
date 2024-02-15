package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2512_예산 {
    static int[] arr;
    static int N, total, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        int max = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        total = Integer.parseInt(br.readLine());

        solve(1, max);
        System.out.println(answer);
    }

    static void solve(int left, int right) {
        if(left > right) return;

        int mid = (left + right) / 2;
        int sum = 0;

        for(int i = 0; i < N; i++) {
            sum += Math.min(arr[i], mid);
        }

        if(sum <= total) {
            answer = Math.max(answer, mid);
            solve(mid + 1, right);
        } else {
            solve(left, mid - 1);
        }

    }
}
