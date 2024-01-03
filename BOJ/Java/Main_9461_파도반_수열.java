package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_9461_파도반_수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        long[] arr = new long[101];

        arr[1] = 1;
        arr[2] = 1;
        arr[3] = 1;
        arr[4] = 2;
        arr[5] = 2;

        for(int t = 0; t < tc; t++) {
            int N = Integer.parseInt(br.readLine());

            for(int i = 6; i < N + 1; i++) {
                arr[i] = arr[i - 1] + arr[i - 5];
            }

            System.out.println(arr[N]);
        }
    }
}
