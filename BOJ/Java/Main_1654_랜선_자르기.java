package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1654_랜선_자르기 {
    static int K, N;
    static long answer;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[K];

        int number = 0;

        for(int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            number = Math.max(number, arr[i]);
        }

        binarySearch(1, number);
        System.out.println(answer);

    }

    static void binarySearch(long left, long right) {
        if(left > right) return;
        int cnt = 0;

        long num = (right + left) / 2;

        for(int tmp : arr) {
            cnt += tmp / num;
        }

        if(cnt < N) {
            binarySearch(left, num - 1);
        } else {
            answer = Math.max(answer, num);
            binarySearch(num + 1, right);
        }

    }

}
