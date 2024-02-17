package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16401_과자_나눠주기 {
    static int M, N, answer;
    static int[] snack;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        snack = new int[N];

        st = new StringTokenizer(br.readLine());
        int max = 0;


        for(int i = 0; i < N; i++) {
            snack[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, snack[i]);
        }

        binarySearch(1, max);

        System.out.println(answer);
    }

    static void binarySearch(int left, int right) {
        if(left > right) return;

        int mid = (left + right) / 2;
        int sum = 0;

        for(int i = 0; i < N; i++) {
            sum += snack[i] / mid;
        }

        if(sum >= M) {
            answer = mid;
            binarySearch(mid + 1, right);
        } else {
            binarySearch(left, mid - 1);
        }
    }
}
