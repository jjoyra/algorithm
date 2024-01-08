package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2473_세_용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long minResult = Long.MAX_VALUE;
        int a = 0;
        int b = 0;
        int c = 0;

        for(int i= 0; i < N - 2; i++) {
            int start = i + 1;
            int end = N - 1;
            long result = arr[i] + arr[start];
            result += arr[end];

            while(start < end) {
                if(minResult > Math.abs(result)) {
                    minResult = Math.abs(result);
                    a = arr[i];
                    b = arr[start];
                    c = arr[end];
                }

                if(result < 0) {
                    result -= arr[start];
                    start++;
                    result += arr[start];
                } else if(result > 0) {
                    result -= arr[end];
                    end--;
                    result += arr[end];
                } else {
                    break;
                }
            }
        }

        System.out.println(a + " " + b + " " + c);

    }

}
