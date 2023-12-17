package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2467_용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int end = N - 1;
        long a = arr[0];
        long b = arr[end];
        long sum = Math.abs(arr[0] + arr[end]);

        for(int i = 0; i < end; i++) {
            while(end - 1 > i && Math.abs(arr[i] + arr[end]) > Math.abs(arr[i] + arr[end - 1])) {
                end--;
            }

            if(sum > Math.abs(arr[i] + arr[end])) {
                a = arr[i];
                b = arr[end];
                sum = Math.abs(a + b);
            }
        }

        System.out.println(a + " " + b);
    }
}
